def flatten(l: list):
    return [item for sublist in l for item in sublist]


def intersection(l1, l2):
    return list(set(l1) & set(l2))


def difference(l1, l2):
    return list(set(l1) - set(l2))


def find_entourage_for_stats(adjacency_list: dict, source_id: int, knees_num: int):
    """Проводит поиск окружений вплодь до knees_num порядка для ученого с source_id. Причем ученые из i окружения
    не встречаются в i+1...knees_num окружениях (другими словами, в окружениях нет повторяющихся ученых).
    Данная функция позволяет узнать, сколько новых знакомств можно завести, если идти через каждого ученого
    в каждом окружении, пробираясь вглубь.

    :param adjacency_list: список смежности (словарь, где key - индекс автора, value - индексы соавторов)
    :param source_id: идентификатор ученого из базы данных (счет начинается с 1)
    :param knees_num: количество окружений для поиска
    :return: список с количеством уникальных ученых в каждом окружении у ученого с source_id
    """
    source_entourages = {}
    source_family_tree = {}

    unique_keys = set()
    source_family_tree[source_id] = adjacency_list[source_id]
    for knee in range(0, knees_num):
        new_source_family_tree = {}
        # TODO: это можно ускорить, оставляя только уникальные values (они могут пересекаться, поэтому идет перезаписывание одного и того же ключа в dict)
        for key, values in source_family_tree.items():
            values = set(values)
            inter = unique_keys & values
            if len(inter) != 0:
                values = values - unique_keys
                unique_keys = unique_keys | values
            else:
                unique_keys = unique_keys | values

            for value in values:
                new_source_family_tree[value] = adjacency_list[value]

        source_entourages[knee + 1] = new_source_family_tree
        source_family_tree = new_source_family_tree

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


def parallel_find_tree_from_list(adjacency_list: dict, source_id: int, target_id: int, knees_num: int):
    source_tree, target_tree, inter = find_tree_from_list(adjacency_list, source_id, target_id, knees_num)
    print(target_id)
    if inter is not None:
        return len(source_tree.keys()) + len(target_tree.keys())
    return None
