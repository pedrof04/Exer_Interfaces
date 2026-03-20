import java.util.Scanner;

/**
 * Classe principal com menu interactivo para escolha do cenario de teste.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n==========================================");
            System.out.println("  Catalogo Musical - Exercicio Interfaces");
            System.out.println("==========================================");
            System.out.println("  1. Cenario Simples  (2 musicas)");
            System.out.println("  2. Cenario Medio    (10 objectos)");
            System.out.println("  3. Cenario Complexo (casos limite)");
            System.out.println("  0. Sair");
            System.out.println("==========================================");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("  Opcao invalida. Introduza um numero.");
                scanner.next();
                continue;
            }

            switch (opcao) {
                case 1 -> cenarioSimples();
                case 2 -> cenarioMedio();
                case 3 -> cenarioComplexo();
                case 0 -> System.out.println("\nA sair... Ate logo!");
                default -> System.out.println("\n  Opcao invalida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ----------------------------------------------------------
    // CENARIO 1 - Simples (2 objectos)
    // ----------------------------------------------------------
    static void cenarioSimples() {
        System.out.println("\n\n--- CENARIO 1: Simples (2 musicas) ---");

        MusicCatalog catalogo = new MusicCatalog();

        Song s1 = new Song("S001", "Blinding Lights", "The Weeknd", 200);
        Song s2 = new Song("S002", "Starboy",          "The Weeknd", 230);
        s1.rate(5); s1.rate(4);
        s2.rate(4);

        catalogo.addItem(s1);
        catalogo.addItem(s2);

        catalogo.playAll();
        catalogo.listSortedSongs();

        System.out.println("\nDuracao total    : " + catalogo.totalDuration() + "s");
        System.out.printf("Avaliacao media  : %.2f%n", catalogo.averageRating());
    }

    // ----------------------------------------------------------
    // CENARIO 2 - Medio (10 objectos mistos)
    // ----------------------------------------------------------
    static void cenarioMedio() {
        System.out.println("\n\n--- CENARIO 2: Medio (10 objectos) ---");

        MusicCatalog catalogo = new MusicCatalog();

        Song[] musicas = {
            new Song("S001", "Bohemian Rhapsody",      "Queen",      354),
            new Song("S002", "Hotel California",        "Eagles",     391),
            new Song("S003", "Shape of You",            "Ed Sheeran", 234),
            new Song("S004", "Smells Like Teen Spirit", "Nirvana",    301),
            new Song("S005", "Lose Yourself",           "Eminem",     326),
        };
        musicas[0].rate(5); musicas[0].rate(5); musicas[0].rate(4);
        musicas[1].rate(5); musicas[1].rate(4);
        musicas[2].rate(3); musicas[2].rate(3);
        musicas[3].rate(5); musicas[3].rate(5); musicas[3].rate(5);
        musicas[4].rate(4); musicas[4].rate(5);
        for (Song s : musicas) catalogo.addItem(s);

        Podcast p1 = new Podcast("P001", "Tech Talks #1",   "Lex Fridman", 3600);
        Podcast p2 = new Podcast("P002", "Code and Coffee", "DHH",         1800);
        catalogo.addItem(p1);
        catalogo.addItem(p2);

        Album a1 = new Album("A001", "A Night at the Opera", "Queen",      1975);
        Album a2 = new Album("A002", "Hotel California",      "Eagles",     1976);
        Album a3 = new Album("A003", "Divide",                "Ed Sheeran", 2017);
        a1.rate(5); a1.rate(5);
        a2.rate(4); a2.rate(5);
        a3.rate(3); a3.rate(4);
        catalogo.addItem(a1); catalogo.addItem(a2); catalogo.addItem(a3);

        catalogo.playAll();
        catalogo.listRateable();
        catalogo.listSortedSongs();
        catalogo.listSongsByDuration();

        int totalSeg = catalogo.totalDuration();
        System.out.printf("%nDuracao total reproduzivel : %ds (%dmin %ds)%n",
                totalSeg, totalSeg / 60, totalSeg % 60);
        System.out.printf("Avaliacao media            : %.2f%n", catalogo.averageRating());
        System.out.println("Melhor avaliado            : " + catalogo.topRated());

        System.out.println("\n=== Demonstracao Cloneable ===");
        Podcast clonado = p1.clone();
        System.out.println("Original : " + p1);
        System.out.println("Clone    : " + clonado);
        System.out.println("Sao o mesmo objecto? " + (p1 == clonado));
    }

    // ----------------------------------------------------------
    // CENARIO 3 - Complexo (casos limite)
    // ----------------------------------------------------------
    static void cenarioComplexo() {
        System.out.println("\n\n--- CENARIO 3: Complexo (Casos Limite) ---");

        MusicCatalog catalogo = new MusicCatalog();

        System.out.println("-- Catalogo vazio --");
        catalogo.playAll();
        catalogo.listRateable();
        System.out.println("Duracao total: " + catalogo.totalDuration() + "s");

        System.out.println("\n-- Teste de ID duplicado --");
        catalogo.addItem(new Song("S001", "Musica Valida", "Artista A", 180));
        try {
            catalogo.addItem(new Song("S001", "Musica Duplicada", "Artista B", 200));
        } catch (IllegalArgumentException e) {
            System.out.println("  Erro apanhado: " + e.getMessage());
        }

        System.out.println("\n-- Teste de dados invalidos --");
        try { new Song("", "Sem ID", "Artista", 120); }
        catch (IllegalArgumentException e) { System.out.println("  Erro apanhado: " + e.getMessage()); }

        try { new Song("S002", "Duracao Negativa", "Artista", -10); }
        catch (IllegalArgumentException e) { System.out.println("  Erro apanhado: " + e.getMessage()); }

        try { Song s = new Song("S003", "Avaliacao Invalida", "Artista", 200); s.rate(7); }
        catch (IllegalArgumentException e) { System.out.println("  Erro apanhado: " + e.getMessage()); }

        try { new Album("A001", "Album Ano Invalido", "Artista", 1800); }
        catch (IllegalArgumentException e) { System.out.println("  Erro apanhado: " + e.getMessage()); }

        System.out.println("\nTamanho final do catalogo: " + catalogo.size() + " item(s).");
    }
}
