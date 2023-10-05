import matplotlib.pyplot as plt
import numpy as np

def plot_entourage_coverage(source_entourages: list):
    """Строит график зависимости порядка окружения от количества находящихся в нем ученых

    :param source_entourages: список формы [k, n], где k - индекс окружения, n - индекс ученого для которого посчитана статистика
    :return: график
    """
    source_entourages = np.array(source_entourages)
    mean = np.mean(source_entourages, axis=0)
    std = np.std(source_entourages, axis=0)
    x = list(range(1, len(mean) + 1))
    plt.plot(x, mean)
    plt.fill_between(x, mean - std, mean + std, alpha=0.3)
    plt.xlabel("Окружение")
    plt.xlabel("Охват")
    plt.xticks(x)
    plt.show()

