# 01. Exercicio Interfaces - Catálogo de Músicas

**UC:** Algoritmia e Programação | ECGM - IPVC
**Tema:** Catálogo de Músicas em Streaming (Músicas, Podcasts, Álbuns)

---

## Interfaces Criadas

| Interface      | Métodos                                            | Propósito                               |
|----------------|----------------------------------------------------|-----------------------------------------|
| interfaces.Identifiable   | getId(), getTitle()                                | Objetos com identidade única           |
| interfaces.Playable       | play(), getDuration(), printPlayInfo() (default)   | Itens que podem ser reproduzidos        |
| interfaces.Rateable       | rate(int), getRating()                             | Itens que podem receber avaliações      |

A interface interfaces.Playable tem 3 métodos (cumpre: pelo menos 1 interface com mais do que 1 método).

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

1. Comparable<Song> - Song implementa compareTo() para ordenar alfabeticamente por título.
   Utilizado em MusicCatalog.listSortedSongs() via Collections.sort().

2. Cloneable - Podcast sobrepoe clone() para criar uma cópia superficial.
   Demonstrado no Cenário 2 (Demonstração Cloneable).

---

## Funcionalidades Implementadas

### A. Percurso
- playAll()      - percorre o catálogo e chama play() em cada item interfaces.Playable.
- listRateable() - filtra e imprime todos os items interfaces.Rateable.

### B. Cálculos
- totalDuration()       - soma getDuration() de todos os items interfaces.Playable.
- averageRating()       - média das avaliações de todos os items interfaces.Rateable avaliados.
- topRated()            - devolve o item com a avaliação média mais alta.
- listSongsByDuration() - ordenação alternativa por duração usando Comparator.

---

## Regras Adicionais Utilizadas

1. Validação de dados + tratamento de erros
   Os construtores de Song, Podcast e Album lançam IllegalArgumentException para:
   - ID ou título vazio/nulo
   - Duração negativa ou zero
   - Ano inválido no Album (fora de 1900-2100)
   - Avaliação fora do intervalo 1-5

2. Evitar duplicados
   MusicCatalog.addItem() usa um HashSet<String> de IDs.
   Adicionar um item com um ID já existente lança IllegalArgumentException.

3. Ordenação alternativa com Comparator
   listSongsByDuration() ordena por duração (crescente) como alternativa
   a ordem natural alfabética (Comparable).

4. Método default numa interface
   interfaces.Playable.printPlayInfo() é um método default que formata e imprime
   a duração; disponível automaticamente em todas as classes que implementam interfaces.Playable.

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

## Como Executar

Ao executar, aparece um menu interactivo com as seguintes opções:
  1 - Cenário Simples  (2 músicas)
  2 - Cenário Médio    (10 objetos)
  3 - Cenário Complexo (casos limite)
  0 - Sair

Introduza o número do cenário pretendido e prima Enter.
O menu repete-se até o utilizador escolher a opção 0 para sair.

