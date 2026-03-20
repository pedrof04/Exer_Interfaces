package interfaces;

/**
 * Interface para objectos que podem receber uma avaliacao de 1 a 5 estrelas.
 * Fornece metodos para avaliar e obter a media das avaliacoes.
 */
public interface Rateable {
    void rate(int estrelas);
    double getRating();
}
