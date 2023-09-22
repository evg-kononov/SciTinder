import asyncio
import random
import pickle
import networkx as nx
import pandas as pd
import numpy as np
import plotly.io as pio
import plotly.graph_objects as go
from sklearn import preprocessing
from entourage_search import find_tree_from_list, unpack, get_data, author_preparation, async_get_data_from_list

from config import *


def scaler(x, min, max):
    eps = 10e-6
    if isinstance(x, list):
        x = np.array(x)
    x_std = (x - x.min()) / (x.max() - x.min() + eps)
    x_scaled = x_std * (max - min) + min
    return x_scaled


def get_graph_laying(tree: dict):
    pos = {}
    for key, values in tree.items():
        for i, v in enumerate(values):
            if len(tree) > 1 and len(values) > 1:
                pos[v] = [(2. * key) / (len(tree) - 1) - 1, (2. * i) / (len(values) - 1) - 1]
            elif len(values) == 1:
                pos[v] = [(2. * key) / (len(tree) - 1) - 1, 0]
            else:
                # Отрисовываем окружение таргета какое-то (первое допустим)
                pass
    return pos


def graph_visualization(nodes: pd.DataFrame, edges: pd.DataFrame, pos: dict):
    G = nx.from_pandas_edgelist(edges, source="source", target="target", edge_attr="weight")
    nx.set_node_attributes(G, pos, "pos")
    nx.set_node_attributes(
        G=G,
        values=pd.Series(nodes["name"].to_list(), index=nodes["id"]).to_dict(),
        name="name"
    )
    nx.set_node_attributes(
        G=G,
        values=pd.Series(nodes["h_index"].to_list(), index=nodes["id"]).to_dict(),
        name="h_index"
    )
    nx.set_node_attributes(
        G=G,
        values=pd.Series(nodes["organization_name"].to_list(), index=nodes["id"]).to_dict(),
        name="organization_name"
    )

    edge_x = [[G.nodes[edge[0]]["pos"][0], G.nodes[edge[1]]["pos"][0], None] for edge in G.edges()]
    edge_y = [[G.nodes[edge[0]]["pos"][1], G.nodes[edge[1]]["pos"][1], None] for edge in G.edges()]

    link_size = [edge[-1]["weight"] for edge in G.edges.data()]
    link_size = scaler(link_size, 0.5, 10)
    edge_traces = {}
    for i in range(0, len(edge_x)):
        edge_traces["trace_" + str(i)] = go.Scatter(
            x=edge_x[i],
            y=edge_y[i],
            line=dict(width=link_size[i], color="#888"),
            mode="lines"
        )
    edge_traces = list(edge_traces.values())

    node_x = []
    node_y = []
    for node in G.nodes():
        x, y = G.nodes[node]["pos"]
        node_x.append(x)
        node_y.append(y)

    node_trace = go.Scatter(
        x=node_x, y=node_y,
        mode='markers',
        hoverinfo='text',
        marker=dict(
            showscale=True,
            # colorscale options
            # 'Greys' | 'YlGnBu' | 'Greens' | 'YlOrRd' | 'Bluered' | 'RdBu' |
            # 'Reds' | 'Blues' | 'Picnic' | 'Rainbow' | 'Portland' | 'Jet' |
            # 'Hot' | 'Blackbody' | 'Earth' | 'Electric' | 'Viridis' |
            colorscale='YlGnBu',
            reversescale=True,
            color=[],
            size=10,
            colorbar=None,
            line_width=2))

    node_color = []
    node_size = []
    node_text = []
    for node_values in G.nodes.values():
        node_color.append(node_values['organization_name'])
        node_size.append(node_values['h_index'])
        node_text.append(
            f"<b>Name:</b> {node_values['name']}<br>"
            f"<b>h-index:</b> {node_values['h_index']}<br>"
            f"<b>Organization:</b> {node_values['organization_name']}"
        )
    label_encoder = preprocessing.LabelEncoder()
    node_color = label_encoder.fit_transform(node_color)

    node_trace.marker.size = scaler(node_size, 10, 50)
    node_trace.marker.line = dict(color='Black', width=2)
    random.seed(40)
    unique_colors = ["#" + ''.join([random.choice('0123456789ABCDEF') for j in range(6)]) for i in
                     range(len(np.unique(node_color)))]
    node_color = [unique_colors[i] for i in node_color]
    node_trace.marker.color = node_color
    node_trace.text = node_text

    fig = go.Figure(
        data=edge_traces + [node_trace],
        layout=go.Layout(
            paper_bgcolor="rgb(255, 255, 255)",
            plot_bgcolor="rgb(255, 255, 255)",
            showlegend=False,
            hovermode='closest',
            margin=dict(b=20, l=5, r=5, t=40),
            xaxis=dict(showgrid=False, zeroline=False, showticklabels=False),
            yaxis=dict(showgrid=False, zeroline=False, showticklabels=False))
    )

    fig.update_traces(marker_showscale=False)

    return fig


def create_nodes(node_idxs: list, url: str):
    urls = [url.format(author) for author in node_idxs]
    data = asyncio.run(async_get_data_from_list(urls))
    data = list(map(lambda x: author_preparation(x), data))
    nodes = pd.DataFrame(data)
    return nodes


def create_edges(node_idxs: list, publication_author: pd.DataFrame):
    authors = sorted(node_idxs)

    # TODO: две таблицы, одну group by publications и join с второй
    edges = []
    for source in authors:
        l = publication_author[publication_author['author_id'] == source]['publication_id']
        targets = publication_author[publication_author['publication_id'].isin(l)].groupby('author_id',
                                                                                           as_index=False).count()

        targets.rename(columns={'author_id': 'target', 'publication_id': 'weight'}, inplace=True)
        targets.drop(targets[targets['target'] <= source].index, inplace=True)
        if source % 5000000000 == 0:
            publication_author.drop(publication_author[publication_author['author_id'] <= source].index,
                                    inplace=True)
        targets["source"] = source
        edges.append(targets)

    edges = pd.concat(edges)
    edges = edges[edges["target"].isin(authors)]
    edges["type"] = "Undirected"
    return edges


def graph_data_preparation(adjacency_list: dict, source_id: int, target_id: int, knees_num: int = 5):
    source_tree, target_tree, inter = find_tree_from_list(adjacency_list, source_id, target_id, knees_num)
    if inter is not None:
        source_tree, target_tree, tree = unpack(adjacency_list, source_tree, target_tree, inter, source_id, target_id)
    else:
        tree = {0: {source_id}, 1: source_tree[0], 2: target_tree[0],
                3: {target_id}}  # TODO: Что здесь визуализировать?

    pos = get_graph_laying(tree)
    node_idxs = list(pos.keys())

    return pos, node_idxs


if __name__ == "__main__":
    pio.renderers.default = "browser"

    get_publication_author_by_author_id = SPRING_API + "publicationAuthor/findByAuthorId"
    get_author_by_name_like = SPRING_API + "author/findByNameLike"
    get_author_by_id = SPRING_API + "author/findById"

    with open(adjacency_list_path, "rb") as f:
        adjacency_list = pickle.load(f)

    publication_author = pd.read_parquet(publication_author_path)

    params = {"firstname": "P", "lastname": "Trusov"}
    source = get_data(get_author_by_name_like, params)
    source = author_preparation(source[0])

    params = {"id": 666}
    target = get_data(get_author_by_id, params)
    target = author_preparation(target)

    knees_num = 5
    source_id = 247337
    target_id = 1298

    pos, node_idxs = graph_data_preparation(adjacency_list, source_id, target_id, knees_num)

    nodes = create_nodes(node_idxs, get_author_by_id + "?id={}")
    edges = create_edges(node_idxs, publication_author)

    graph_visualization(nodes, edges, pos)
