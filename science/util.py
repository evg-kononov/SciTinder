def find_tree_for_stats(adjacency_list: dict, source_id: int, knees_num: int):
    source_entourages = {}
    source_family_tree = {}

    source_family_tree[source_id] = adjacency_list[source_id]
    source_entourages[1] = adjacency_list[source_id]
    for knee in range(knees_num - 1):
        source_family_tree = {
            value: adjacency_list[value] for key, values in source_family_tree.items() for value in values
        }
        source_entourages[knee + 1] = source_family_tree
    return list(map(lambda x: len(source_entourages[x]), source_entourages.keys()))


# TODO: Удалять из N колена представителей из N-1 колена (иначе N колено содержит в себе представителей N-1 колена)
def find_tree_from_list(adjacency_list: dict, source_id: int, target_id: int, knees_num: int):
    source_tree = {}
    target_tree = {}

    for i in range(knees_num):
        if i == 0:
            source_tree[i] = set(adjacency_list[source_id])
            target_tree[i] = set(adjacency_list[target_id])
        else:
            temp = set()
            for item in target_tree[i - 1]:
                temp.update(adjacency_list[item])
            target_tree[i] = temp

        inter = source_tree[i].intersection(target_tree[i])
        if len(inter) > 0:
            return source_tree, target_tree, inter
        else:
            temp = set()
            for item in source_tree[i]:
                temp.update(adjacency_list[item])
            source_tree[i + 1] = temp

            inter = source_tree[i + 1].intersection(target_tree[i])
            if len(inter) > 0:
                return source_tree, target_tree, inter

    return source_tree, target_tree, None