package interfaces;

/**
 * Interface para objectos que podem ser reproduzidos (musicas, podcasts, etc.).
 * Contem metodos para reproduzir e obter a duracao.
 * Inclui um metodo default para imprimir informacao de reproducao (Regra Adicional: metodo default).
 */
public interface Playable {
    void play();
    int getDuration(); // duracao em segundos

    default void printPlayInfo() {
        int mins = getDuration() / 60;
        int secs = getDuration() % 60;
        System.out.println("   Duracao: " + mins + "min " + secs + "s");
    }
}
