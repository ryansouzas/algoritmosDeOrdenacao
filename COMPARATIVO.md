# Comparativo de desempenho dos algoritmos de ordenação

## Metodologia

- **Base de medição:** `entrada.txt` com **n = 50.000** números inteiros aleatórios, ordenados em uma cópia do vetor.
- **Tempo real (coluna n):** mediana de 5 execuções por algoritmo na JVM (Java 25), já aquecida pelo JIT.
- **Tempo estimado (colunas n², n³ e n¹⁰):** extrapolação teórica a partir da complexidade de cada algoritmo, calibrada pela constante medida em n = 50.000:
  - algoritmos O(n²): `T(N) = c · N²`, com `c = T(n) / n²`
  - algoritmos O(n log n): `T(N) = c · N · log₂N`, com `c = T(n) / (n · log₂ n)`
- **Tamanhos simulados:** n = 5·10⁴, n² = 2,5·10⁹, n³ = 1,25·10¹⁴ e n¹⁰ ≈ 9,8·10⁴⁶ elementos.

> ⚠️ **Os valores para n² (parcialmente), n³ e n¹⁰ são estimativas teóricas, não medições.** Eles não são executáveis na prática: já com n² = 2,5·10⁹ elementos o vetor ocuparia cerca de 10 GB, e n³ e n¹⁰ excedem de longe qualquer memória existente. As estimativas ignoram efeitos de cache, memória e paginação, então servem só para comparar a ordem de grandeza entre os algoritmos.

## Tabela comparativa

| Algoritmo | Complexidade (melhor / médio / pior) | n = 50.000 (**medido**) | n² (estimado) | n³ (estimado) | n¹⁰ (estimado) |
|---|---|---|---|---|---|
| BubbleSort | O(n) / O(n²) / O(n²) | 14,5 s | 1,15·10³ anos | 2,88·10¹² anos | 1,75·10⁷⁸ anos |
| InsertionSort | O(n) / O(n²) / O(n²) | 2,1 s | 165,4 anos | 4,14·10¹¹ anos | 2,52·10⁷⁷ anos |
| SelectionSort | O(n²) / O(n²) / O(n²) | 2,3 s | 179,9 anos | 4,50·10¹¹ anos | 2,74·10⁷⁷ anos |
| MergeSort | O(n log n) / O(n log n) / O(n log n) | 17,4 ms | 29,0 min | 4,1 anos | 1,08·10³⁴ anos |
| QuickSort | O(n log n) / O(n log n) / O(n²) | 13,1 ms | 21,8 min | 3,1 anos | 8,09·10³³ anos |

Observações:

- O BubbleSort é o mais lento dos quadráticos porque faz muito mais trocas que o InsertionSort e o SelectionSort.
- MergeSort e QuickSort ficam na mesma ordem de grandeza; o QuickSort leva vantagem por trabalhar no próprio vetor, sem intercalar em vetor auxiliar.
- Para n¹⁰ a diferença entre O(n²) e O(n log n) passa de 40 ordens de grandeza, o que mostra por que a escolha do algoritmo importa mais que o hardware.

## IA utilizada

- **IA:** Claude (Anthropic), modelo **Claude Sonnet 5.5** (`claude-sonnet-5-5`), via Claude Code.
- **Como foi usada:** a IA recebeu o código de cada algoritmo do projeto, mediu o tempo real em n = 50.000 e calculou as extrapolações descritas na metodologia.

**Prompt usado:**

> Para cada algoritmo do projeto (BubbleSort, InsertionSort, SelectionSort, MergeSort e QuickSort), estime o tempo de execução para entradas de tamanho n, n², n³ e n¹⁰, registre a complexidade teórica e monte uma tabela comparativa. Deixe claro que n³ e n¹⁰ são estimativas teóricas.
