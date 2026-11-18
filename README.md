# Algoritmos de Ordenação

Projeto da disciplina **Estrutura de Dados II (ED2)**, em **Java**, que implementa cinco algoritmos clássicos de ordenação **escritos à mão** (sem `Arrays.sort`, `Collections.sort` ou qualquer biblioteca de ordenação) e permite compará-los na prática: o programa carrega um arquivo com muitos números, deixa você escolher o algoritmo por um menu e mostra a entrada, a saída ordenada e o **tempo real de execução**.

Pacote: `ed2.projeto.algoritmosordenacao`

## Algoritmos implementados

| Algoritmo | Melhor caso | Caso médio | Pior caso |
|---|---|---|---|
| BubbleSort | O(n) | O(n²) | O(n²) |
| InsertionSort | O(n) | O(n²) | O(n²) |
| SelectionSort | O(n²) | O(n²) | O(n²) |
| MergeSort | O(n log n) | O(n log n) | O(n log n) |
| QuickSort | O(n log n) | O(n log n) | O(n²) |

Todos implementam a interface `IAlgoritmoOrdenacao` e ordenam em **ordem crescente**.

## Funcionalidades

- **Leitura de arquivo `.txt`**: carrega os números (um por linha ou separados por espaço) para um `int[]` em memória. Valores inválidos são ignorados e contados em um aviso; arquivo ausente ou sem números válidos gera mensagem de erro.
- **Menu interativo**: montado a partir do `toString()` de cada algoritmo, sem `if` repetido por algoritmo.
- **Entrada original preservada**: cada ordenação é feita em uma **cópia** do vetor, então todas as opções do menu partem exatamente da mesma entrada.
- **Exibição limitada**: entrada e saída mostram apenas os **100 primeiros elementos** quando o vetor é grande, indicando que a lista foi truncada e o total de elementos.
- **Tempo de execução real**, medido com `System.nanoTime()` e exibido em milissegundos.
- **Gerador de dados**: `Utils.gerarVetor(tamanho, limiteSuperior)` cria vetores aleatórios (usado para gerar o `entrada.txt` de exemplo).

## Estrutura do projeto

```
algoritmosordenacao/
├── IAlgoritmoOrdenacao.java   # interface comum: void ordenarVetor(int[] vetor)
├── BubbleSort.java
├── InsertionSort.java
├── SelectionSort.java
├── MergeSort.java             # vetor auxiliar com o tamanho do intervalo
├── QuickSort.java             # partição de Hoare, pivô no elemento central
├── Utils.java                 # imprimirVetor e gerarVetor
├── Main.java                  # leitura do arquivo, menu e medição de tempo
├── entrada.txt                # exemplo com 50.000 inteiros aleatórios
└── COMPARATIVO.md             # tabela comparativa de desempenho
```

## Como compilar e executar

### Pela IDE
Importe o projeto no Eclipse, IntelliJ, NetBeans ou similar e execute a classe `Main`. Não há ferramenta de build externa nem dependências.

### Pelo terminal
A partir da pasta que contém `algoritmosordenacao/`. Os arquivos-fonte originais estão em ISO-8859-1, por isso o `-encoding`:

```bash
javac -encoding ISO-8859-1 -d out algoritmosordenacao/*.java
java -cp out ed2.projeto.algoritmosordenacao.Main
```

Por padrão o programa lê `entrada.txt` do diretório em que for executado. Para usar outro arquivo, passe o caminho como argumento:

```bash
java -cp out ed2.projeto.algoritmosordenacao.Main caminho/do/arquivo.txt
```

## Exemplo de uso

```
Arquivo carregado: entrada.txt (50000 números).

=== Algoritmos de Ordenação ===
1 - BubbleSort
2 - InsertionSort
3 - SelectionSort
4 - MergeSort
5 - QuickSort
0 - Sair
Escolha: 5

--- QuickSort ---
Entrada:
  {124906, 335003, 526925, ...}
  (lista truncada: exibindo os 100 primeiros de 50000 elementos)
Saída (ordenada):
  {...}
  (lista truncada: exibindo os 100 primeiros de 50000 elementos)
Tempo de execução (QuickSort): ... ms
```

## Comparativo de desempenho

O arquivo [COMPARATIVO.md](COMPARATIVO.md) traz a tabela comparativa com o tempo medido para n = 50.000 e as estimativas teóricas para entradas de tamanho n², n³ e n¹⁰ (estas últimas são extrapolações, não medições, pois não são executáveis na prática), além da metodologia usada.

Resumo: em n = 50.000, MergeSort e QuickSort ordenam em dezenas de milissegundos, enquanto os algoritmos quadráticos levam segundos (o BubbleSort é o mais lento).

## Integrantes

- Ryan de Souza Sebadelhe
- Daniel da Costa Dias
- Enzo Morais Lucena
- Erick Mauricio Soares Almeida
- Nelson Francisco Suassuna Neto
- Lucas Andrade Velozo Andriola

## Autoria

Os algoritmos-base são de autoria de Hilario Tomaz Alves de Oliveira (professor), conforme os `@author` nos arquivos.
