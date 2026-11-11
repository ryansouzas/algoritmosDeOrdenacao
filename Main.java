package ed2.projeto.algoritmosordenacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Programa principal: carrega um arquivo .txt com números, exibe um menu
 * para escolher o algoritmo de ordenação e mostra entrada, saída e tempo.
 */
public class Main {

  /** Caminho padrão do arquivo de entrada (pode ser trocado por argumento). */
  private static final String ARQUIVO_PADRAO = "entrada.txt";

  /** Quantidade máxima de elementos exibidos no console. */
  private static final int LIMITE_EXIBICAO = 100;

  public static void main(String[] args) {

    String caminho = ( args.length > 0 ) ? args[0] : ARQUIVO_PADRAO;

    int[] dados;

    try {

      dados = carregarArquivo( caminho );

    } catch (IOException e) {

      System.out.println( "Erro: não foi possível ler o arquivo '" + caminho + "' (" + e.getMessage() + ")." );
      System.out.println( "Uso: java ed2.projeto.algoritmosordenacao.Main [caminho_do_arquivo.txt]" );
      return;

    }

    if( dados.length == 0 ) {

      System.out.println( "Erro: o arquivo '" + caminho + "' não contém números válidos." );
      return;

    }

    System.out.println( "Arquivo carregado: " + caminho + " (" + dados.length + " números)." );

    IAlgoritmoOrdenacao[] algoritmos = {
      new BubbleSort(),
      new InsertionSort(),
      new SelectionSort(),
      new MergeSort(),
      new QuickSort()
    };

    Scanner teclado = new Scanner( System.in );

    int opcao;

    do {

      exibirMenu( algoritmos );

      opcao = lerOpcao( teclado );

      if( opcao >= 1 && opcao <= algoritmos.length ) {

        executar( algoritmos[opcao - 1], dados );

      } else if( opcao != 0 ) {

        System.out.println( "Opção inválida." );

      }

    } while( opcao != 0 );

    System.out.println( "Encerrando." );

  }

  private static void exibirMenu(IAlgoritmoOrdenacao[] algoritmos) {

    System.out.println();
    System.out.println( "=== Algoritmos de Ordenação ===" );

    for(int i = 0; i < algoritmos.length; i++) {

      System.out.println( ( i + 1 ) + " - " + algoritmos[i] );

    }

    System.out.println( "0 - Sair" );
    System.out.print( "Escolha: " );

  }

  /** Lê a opção do usuário; retorna -1 se a entrada não for um número ou o teclado fechar. */
  private static int lerOpcao(Scanner teclado) {

    if( !teclado.hasNextLine() ) {

      return 0; // fim da entrada: sai do menu

    }

    try {

      return Integer.parseInt( teclado.nextLine().trim() );

    } catch (NumberFormatException e) {

      return -1;

    }

  }

  /** Ordena uma cópia dos dados, medindo o tempo, e exibe antes/depois. */
  private static void executar(IAlgoritmoOrdenacao algoritmo, int[] dados) {

    int[] copia = dados.clone(); // o vetor original nunca é alterado

    System.out.println();
    System.out.println( "--- " + algoritmo + " ---" );
    System.out.println( "Entrada:" );
    exibir( dados );

    long inicio = System.nanoTime();

    algoritmo.ordenarVetor( copia );

    long fim = System.nanoTime();

    System.out.println( "Saída (ordenada):" );
    exibir( copia );

    System.out.printf( "Tempo de execução (%s): %.3f ms%n", algoritmo, ( fim - inicio ) / 1_000_000.0 );

  }

  /** Mostra o vetor; se for grande, apenas os 100 primeiros elementos. */
  private static void exibir(int[] vetor) {

    int quantidade = Math.min( vetor.length, LIMITE_EXIBICAO );

    StringBuilder sb = new StringBuilder( "  {" );

    for(int i = 0; i < quantidade; i++) {

      if( i > 0 ) {

        sb.append( ", " );

      }

      sb.append( vetor[i] );

    }

    sb.append( "}" );

    System.out.println( sb );

    if( vetor.length > LIMITE_EXIBICAO ) {

      System.out.println( "  (lista truncada: exibindo os " + LIMITE_EXIBICAO
          + " primeiros de " + vetor.length + " elementos)" );

    }

  }

  /**
   * Lê um .txt com números inteiros (um por linha ou separados por espaço).
   * Tokens inválidos são ignorados e contados.
   */
  static int[] carregarArquivo(String caminho) throws IOException {

    int[] buffer = new int[1024];
    int total = 0;
    int invalidos = 0;

    try (BufferedReader leitor = new BufferedReader( new FileReader( caminho ) )) {

      String linha;

      while( ( linha = leitor.readLine() ) != null ) {

        for( String token : linha.trim().split( "\\s+" ) ) {

          if( token.isEmpty() ) {

            continue;

          }

          try {

            int numero = Integer.parseInt( token );

            if( total == buffer.length ) { // cresce o vetor manualmente

              int[] maior = new int[buffer.length * 2];

              System.arraycopy( buffer, 0, maior, 0, total );

              buffer = maior;

            }

            buffer[total++] = numero;

          } catch (NumberFormatException e) {

            invalidos++;

          }

        }

      }

    }

    if( invalidos > 0 ) {

      System.out.println( "Aviso: " + invalidos + " valor(es) inválido(s) ignorado(s)." );

    }

    int[] resultado = new int[total];

    System.arraycopy( buffer, 0, resultado, 0, total );

    return resultado;

  }

}
