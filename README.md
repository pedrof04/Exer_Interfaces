# 01. Exercicio Interfaces - Catalogo de Musicas

**UC:** Algoritmia e Programacao | ECGM - IPVC
**Tema:** Catalogo de Musicas em Streaming (Musicas, Podcasts, Albums)

---

## Interfaces Criadas

| Interface      | Metodos                                            | Proposito                               |
|----------------|----------------------------------------------------|-----------------------------------------|
| interfaces.Identifiable   | getId(), getTitle()                                | Objectos com identidade unica           |
| interfaces.Playable       | play(), getDuration(), printPlayInfo() (default)   | Itens que podem ser reproduzidos        |
| interfaces.Rateable       | rate(int), getRating()                             | Itens que podem receber avaliacoes      |

A interface interfaces.Playable tem 3 metodos (cumpre: pelo menos 1 interface com mais do que 1 metodo).

---

## Classes e Interfaces Implementadas

| Classe  | Interfaces Implementadas                                   |
|---------|------------------------------------------------------------|
| Song    | interfaces.Playable, interfaces.Rateable, interfaces.Identifiable, Comparable<Song>         |
| Podcast | interfaces.Playable, interfaces.Identifiable, Cloneable                          |
| Album   | interfaces.Rateable, interfaces.Identifiable                                     |

Pelo menos 2 classes implementam mais do que uma interface.

---

## Interfaces Built-in do Java Utilizadas

1. Comparable<Song> - Song implementa compareTo() para ordenar alfabeticamente por titulo.
   Utilizado em MusicCatalog.listSortedSongs() via Collections.sort().

2. Cloneable - Podcast sobrepoe clone() para criar uma copia superficial.
   Demonstrado no Cenario 2 (Demonstracao Cloneable).

---

## Funcionalidades Implementadas

### A. Percurso
- playAll()      - percorre o catalogo e chama play() em cada item interfaces.Playable.
- listRateable() - filtra e imprime todos os items interfaces.Rateable.

### B. Calculos
- totalDuration()       - soma getDuration() de todos os items interfaces.Playable.
- averageRating()       - media das avaliacoes de todos os items interfaces.Rateable avaliados.
- topRated()            - devolve o item com a avaliacao media mais alta.
- listSongsByDuration() - ordenacao alternativa por duracao usando Comparator.

---

## Regras Adicionais Utilizadas

1. Validacao de dados + tratamento de erros
   Os construtores de Song, Podcast e Album lancam IllegalArgumentException para:
   - ID ou titulo vazio/nulo
   - Duracao negativa ou zero
   - Ano invalido no Album (fora de 1900-2100)
   - Avaliacao fora do intervalo 1-5

2. Evitar duplicados
   MusicCatalog.addItem() usa um HashSet<String> de IDs.
   Adicionar um item com um ID ja existente lanca IllegalArgumentException.

3. Ordenacao alternativa com Comparator
   listSongsByDuration() ordena por duracao (crescente) como alternativa
   a ordem natural alfabetica (Comparable).

4. Metodo default numa interface
   interfaces.Playable.printPlayInfo() e um metodo default que formata e imprime
   a duracao; disponivel automaticamente em todas as classes que implementam interfaces.Playable.

---

## Cenários de Teste

### Cenário 1 - Simples (2 objetos)
2 objetos Song adicionados, avaliados, reproduzidos e ordenados.
Demonstra: playAll(), listSortedSongs(), totalDuration(), averageRating().

### Cenário 2 - Médio (10 objetos)
5 Songs + 2 Podcasts + 3 Albums.
Demonstra: todos os métodos de percurso e cálculo, ordenação por Comparable,
ordenação por Comparator, demonstração do Cloneable.

### Cenário 3 - Complexo (Casos Limite)
- Catálogo vazio (sem erros)
- ID duplicado -> IllegalArgumentException apanhada
- Dados inválidos: ID vazio, duração negativa, avaliação inválida, ano inválido

---

## Como Compilar e Executar

  javac *.java
  java Main

Todos os ficheiros .java devem estar na mesma pasta.
