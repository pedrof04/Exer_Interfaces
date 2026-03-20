import interfaces.Identifiable;
import interfaces.Playable;

/**
 * Representa um episodio de podcast.
 * Implementa: interfaces.Playable, interfaces.Identifiable, Cloneable (interface built-in do Java).
 */
public class Podcast implements Playable, Identifiable, Cloneable {

    private String id;
    private String title;
    private String host;
    private int duration; // em segundos

    public Podcast(String id, String title, String host, int duration) {
        // Validacao de dados - Regra Adicional #1
        if (id == null || id.isBlank())       throw new IllegalArgumentException("O ID nao pode ser vazio.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("O titulo nao pode ser vazio.");
        if (duration <= 0)                    throw new IllegalArgumentException("A duracao tem de ser positiva.");
        this.id       = id;
        this.title    = title;
        this.host     = host;
        this.duration = duration;
    }

    @Override public String getId()    { return id; }
    @Override public String getTitle() { return title; }
    public String getHost()            { return host; }

    @Override
    public void play() {
        System.out.println("A reproduzir podcast: \"" + title + "\" apresentado por " + host);
        printPlayInfo(); // metodo default da interface interfaces.Playable
    }

    @Override public int getDuration() { return duration; }

    // Cloneable: cria uma copia superficial deste Podcast
    @Override
    public Podcast clone() {
        try {
            return (Podcast) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Podcast[%s] \"%s\" - Apresentador: %s (%ds)", id, title, host, duration);
    }
}
