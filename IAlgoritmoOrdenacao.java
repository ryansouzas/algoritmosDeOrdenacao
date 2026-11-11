package ed2.projeto.algoritmosordenacao;

/**
 * Contrato comum a todos os algoritmos de ordenação do projeto.
 */
public interface IAlgoritmoOrdenacao {

  /**
   * Ordena o vetor em ordem crescente, modificando o próprio vetor recebido.
   * Quem chama é responsável por passar uma cópia caso precise do original.
   */
  void ordenarVetor(int[] vetor);

}
