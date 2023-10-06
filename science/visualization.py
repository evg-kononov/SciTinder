import matplotlib.pyplot as plt
import numpy as np

def plot_entourage_coverage(source_entourages: list,  plt_style="seaborn-bright", font_size=12, dpi=500):
    """Строит график зависимости порядка окружения от количества находящихся в нем ученых

    :param source_entourages: список формы [k, n], где k - индекс окружения, n - индекс ученого для которого посчитана статистика
    :param plt_style: стиль графика (можно выбрать из matplotlib plot styles)
    :param font_size: размер шрифта на графике
    :param dpi: качество изображение в DPI
    :return: график
    """
    plt.style.use(plt_style)
    plt.rcParams["font.size"] = f"{font_size}"

    source_entourages = np.array(source_entourages)
    mean = np.mean(source_entourages, axis=0)
    std = np.std(source_entourages, axis=0)
    x = list(range(1, len(mean) + 1))

    fig, ax = plt.subplots(1, 1, figsize=(7, 5), dpi=dpi)
    ax.plot(x, mean)
    ax.fill_between(x, mean - std, mean + std, alpha=.1)
    ax.set_xlabel("Окружение")
    ax.set_ylabel("Охват")
    ax.set_xticks(x)
    ax.grid(linestyle="--")
    plt.show()

