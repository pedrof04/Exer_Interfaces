import interfaces.Identifiable;
import interfaces.Playable;
import interfaces.Rateable;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma musica.
 * Implementa: interfaces.Playable, interfaces.Rateable, interfaces.Identifiable, Comparable<Song> (interface built-in do Java).
 * Ordem natural (Comparable): ordem alfabetica pelo titulo.
 */
public class Song implements Playable, Rateable, Identifiable, Comparable<Song> {

    private String id;
    private String title;
    private String artist;
    private int duration; // em segundos
    private List<Integer> ratings;

    public Song(String id, String title, String artist, int duration) {
        // Validacao de dados - Regra Adicional #1
        if (id == null || id.isBlank())       throw new IllegalArgumentException("O ID nao pode ser vazio.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("O titulo nao pode ser vazio.");
        if (duration <= 0)                    throw new IllegalArgumentException("A duracao tem de ser positiva.");
        this.id       = id;
        this.title    = title;
        this.artist   = artist;
        this.duration = duration;
        this.ratings  = new ArrayList<>();
    }

    @Override public String getId()    { return id; }
    @Override public String getTitle() { return title; }
    public String getArtist()          { return artist; }

    @Override
    public void play() {
        System.out.println("A reproduzir musica: \"" + title + "\" de " + artist);
        printPlayInfo(); // metodo default da interface interfaces.Playable
    }

    @Override public int getDuration() { return duration; }

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

    // Comparable<Song>: ordem natural alfabetica pelo titulo
    @Override
    public int compareTo(Song outro) {
        return this.title.compareToIgnoreCase(outro.title);
    }

    @Override
    public String toString() {
        return String.format("Musica[%s] \"%s\" - %s (%ds) | Avaliacao: %.1f", id, title, artist, duration, getRating());
    }
}
