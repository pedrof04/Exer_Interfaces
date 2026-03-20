import java.util.ArrayList;
import java.util.List;

/**
 * Representa um album de musica.
 * Implementa: Rateable, Identifiable.
 */
public class Album implements Rateable, Identifiable {

    private String id;
    private String title;
    private String artist;
    private int year;
    private List<Integer> ratings;

    public Album(String id, String title, String artist, int year) {
        // Validacao de dados - Regra Adicional #1
        if (id == null || id.isBlank())       throw new IllegalArgumentException("O ID nao pode ser vazio.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("O titulo nao pode ser vazio.");
        if (year < 1900 || year > 2100)       throw new IllegalArgumentException("Ano invalido: " + year);
        this.id      = id;
        this.title   = title;
        this.artist  = artist;
        this.year    = year;
        this.ratings = new ArrayList<>();
    }

    @Override public String getId()    { return id; }
    @Override public String getTitle() { return title; }
    public String getArtist()          { return artist; }
    public int getYear()               { return year; }

    @Override
    public void rate(int estrelas) {
        if (estrelas < 1 || estrelas > 5)
            throw new IllegalArgumentException("A avaliacao tem de ser entre 1 e 5.");
        ratings.add(estrelas);
    }

    @Override
    public double getRating() {
        if (ratings.isEmpty()) return 0.0;
        return ratings.stream().mapToInt(i -> i).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Album[%s] \"%s\" - %s (%d) | Avaliacao: %.1f", id, title, artist, year, getRating());
    }
}
