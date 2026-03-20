import java.util.*;

/**
 * Gere um catalogo de itens multimédia (musicas, podcasts, albums).
 * - Impede IDs duplicados (Regra Adicional #2: evitar duplicados).
 * - Fornece metodos de percurso (Funcionalidade A) e calculo (Funcionalidade B).
 */
public class MusicCatalog {

    private List<Identifiable> items = new ArrayList<>();
    private Set<String> idsUsados    = new HashSet<>();

    /**
     * Adiciona um item ao catalogo.
     * Lanca IllegalArgumentException se o ID ja existir.
     */
    public void addItem(Identifiable item) {
        if (idsUsados.contains(item.getId())) {
            throw new IllegalArgumentException("ID duplicado: \"" + item.getId() + "\" ja existe no catalogo.");
        }
        items.add(item);
        idsUsados.add(item.getId());
        System.out.println("  [+] Adicionado: " + item);
    }

    // --- Funcionalidade A: Percurso ---

    /** Percorre e reproduz todos os items Playable do catalogo. */
    public void playAll() {
        System.out.println("\n=== Reproduzir Todos os Items ===");
        boolean encontrou = false;
        for (Identifiable item : items) {
            if (item instanceof Playable) {
                ((Playable) item).play();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("  (sem items reproduziveis)");
    }

    /** Filtra e lista todos os items Rateable do catalogo. */
    public void listRateable() {
        System.out.println("\n=== Items Avaliáveis ===");
        boolean encontrou = false;
        for (Identifiable item : items) {
            if (item instanceof Rateable) {
                System.out.println("  " + item);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("  (sem items avaliáveis)");
    }

    // --- Funcionalidade B: Calculos ---

    /** Calcula a duracao total (em segundos) de todos os items Playable. */
    public int totalDuration() {
        int total = 0;
        for (Identifiable item : items)
            if (item instanceof Playable)
                total += ((Playable) item).getDuration();
        return total;
    }

    /** Calcula a avaliacao media de todos os items Rateable com pelo menos uma avaliacao. */
    public double averageRating() {
        double soma = 0; int conta = 0;
        for (Identifiable item : items) {
            if (item instanceof Rateable && ((Rateable) item).getRating() > 0) {
                soma += ((Rateable) item).getRating();
                conta++;
            }
        }
        return conta == 0 ? 0.0 : soma / conta;
    }

    /** Devolve o item Rateable com a avaliacao mais alta. */
    public Identifiable topRated() {
        Identifiable melhor = null; double max = -1;
        for (Identifiable item : items) {
            if (item instanceof Rateable) {
                double r = ((Rateable) item).getRating();
                if (r > max) { max = r; melhor = item; }
            }
        }
        return melhor;
    }

    /** Lista todas as Musicas ordenadas alfabeticamente pelo titulo (Comparable). */
    public void listSortedSongs() {
        System.out.println("\n=== Musicas ordenadas por Titulo (Comparable) ===");
        List<Song> musicas = new ArrayList<>();
        for (Identifiable item : items)
            if (item instanceof Song) musicas.add((Song) item);
        Collections.sort(musicas);
        if (musicas.isEmpty()) System.out.println("  (sem musicas)");
        else musicas.forEach(s -> System.out.println("  " + s));
    }

    /** Lista todas as Musicas ordenadas por duracao usando um Comparator (Regra Adicional #3: ordenacao alternativa). */
    public void listSongsByDuration() {
        System.out.println("\n=== Musicas ordenadas por Duracao (Comparator) ===");
        List<Song> musicas = new ArrayList<>();
        for (Identifiable item : items)
            if (item instanceof Song) musicas.add((Song) item);
        musicas.sort(Comparator.comparingInt(Song::getDuration));
        if (musicas.isEmpty()) System.out.println("  (sem musicas)");
        else musicas.forEach(s -> System.out.println("  " + s));
    }

    public int size() { return items.size(); }
}
