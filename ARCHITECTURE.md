# EcoLoop - Schema logică

Aplicația urmează patternul **MVC** (Model-View-Controller) al Spring Boot:
browserul trimite o cerere HTTP, un **Controller** o primește, apelează un
**Service** care returnează date din lista in-memory (Model), iar **Thymeleaf**
randează HTML-ul final folosind template-urile din `templates/`.

## Schema de ansamblu (ASCII)

```
                           +---------------------+
                           |      BROWSER        |
                           | (Chrome / Edge...)  |
                           +----------+----------+
                                      |
                        HTTP GET/POST | HTML raspuns
                                      v
+---------------------------------------------------------------+
|                      SPRING BOOT (Tomcat :8080)               |
|                                                               |
|   +-------------------+        routing        +-----------+   |
|   |    CONTROLLERS    | <-------------------> | THYMELEAF |   |
|   |                   |     Model + view      | TEMPLATES |   |
|   |  HomeController   |  ------------------>  |           |   |
|   |  ToolController   |                       | index     |   |
|   |  BookingController|                       | tools     |   |
|   |  UserController   |                       | tool-det. |   |
|   +---------+---------+                       | new-tool  |   |
|             |                                 | history   |   |
|             | apeleaza                        | bookings  |   |
|             v                                 | fragments |   |
|   +-------------------+                       +-----------+   |
|   |     SERVICES      |                                       |
|   |                   |        seed @PostConstruct            |
|   |  ToolService      | ------------------------------+       |
|   |  BookingService   |                               |       |
|   |  UserService      |                               v       |
|   |  RentalHistoryS.  |                        +-------------+|
|   +---------+---------+                        |  MOCK DATA  ||
|             |                                  | ArrayList<> ||
|             v                                  |  in-memory  ||
|   +-------------------+                        +-------------+|
|   |    DOMAIN MODEL   |                                       |
|   |                   |                                       |
|   |  Tool   Booking   |                                       |
|   |  User   RentalH.  |                                       |
|   |  Category enum    |                                       |
|   |  ToolStatus enum  |                                       |
|   +-------------------+                                       |
+---------------------------------------------------------------+
```

## Flux pentru o rezervare (exemplu concret)

```
  utilizator                browser                 EcoLoop server
      |                        |                          |
      | click "Book now"       |                          |
      |----------------------->|                          |
      |                        | POST /tools/5/book       |
      |                        |------------------------->|
      |                        |                          |--+
      |                        |                          |  | BookingController
      |                        |                          |  | primeste cererea
      |                        |                          |<-+
      |                        |                          |--+
      |                        |                          |  | bookingService
      |                        |                          |  | .create(...)
      |                        |                          |<-+
      |                        |                          |--+
      |                        |                          |  | toolService
      |                        |                          |  | .findById(5)
      |                        |                          |<-+
      |                        |                          |--+
      |                        |                          |  | new Booking(...)
      |                        |                          |  | bookings.add(b)
      |                        |                          |<-+
      |                        |    302 redirect /history |
      |                        |<-------------------------|
      |                        | GET /history             |
      |                        |------------------------->|
      |                        |    HTML istoric rezervari|
      |                        |<-------------------------|
      |    pagina afisata      |                          |
      |<-----------------------|                          |
```

## Rutele si ce returneaza fiecare

| Metoda | Ruta                  | Controller         | Template randat    |
|--------|-----------------------|--------------------|--------------------|
| GET    | `/`                   | HomeController     | `index.html`       |
| GET    | `/tools`              | ToolController     | `tools.html`       |
| GET    | `/tools/new`          | ToolController     | `new-tool.html`    |
| POST   | `/tools/new`          | ToolController     | redirect `/tools`  |
| GET    | `/tools/{id}`         | ToolController     | `tool-details.html`|
| POST   | `/tools/{id}/book`    | BookingController  | redirect `/history`|
| GET    | `/history`            | UserController     | `history.html`     |
| GET    | `/bookings`           | BookingController  | `bookings.html`    |

## Relatii intre clasele de domeniu

```
              +----------+
              |   User   |       (un singur "Demo User" seedat)
              +----------+
                    |
                    | face rezervari
                    v
  +--------+    +---------+    +----------------------+
  |  Tool  |<---| Booking |--->|  RentalHistoryEntry  |
  +--------+    +---------+    +----------------------+
      |              |
      | are o        | are un status
      v              v
  +----------+   ACTIVE / UPCOMING / COMPLETED
  | Category |
  +----------+
   (enum)

  Tool are si: ToolStatus (AVAILABLE / RENTED / UNAVAILABLE)
```

- **Tool** este obiectul listat de cineva (contine `ownerName`, pret, categorie, imagine).
- **Booking** leaga un `Tool` de un utilizator pe un interval de date.
- **RentalHistoryEntry** este o vedere simplificata peste `Booking`, afisata la `/history` (generata dinamic de `RentalHistoryService`).
- **Category** si **ToolStatus** sunt enum-uri ca sa evitam string-uri magice.

## Seed-ul datelor la pornire

La pornire, fiecare serviciu care tine o lista in memorie foloseste `@PostConstruct`
ca sa populeze ArrayList-ul cu date de exemplu. Astfel, cand deschizi aplicatia
pentru prima oara, vezi deja 8 unelte si 3 rezervari demo fara sa fie nevoie
de vreo interactiune sau de o baza de date.

```
  +-----------------+        +-----------------+        +----------------+
  |  Spring porneste| -----> |@PostConstruct   | -----> | ArrayList<Tool>|
  |  ToolService    |        | init() adauga   |        | 8 elemente     |
  |                 |        | 8 unelte mock   |        |                |
  +-----------------+        +-----------------+        +----------------+

  +-----------------+        +-----------------+        +-------------------+
  | BookingService  | -----> |@PostConstruct   | -----> | ArrayList<Booking>|
  |                 |        | init() adauga   |        | 3 rezervari demo  |
  |                 |        | 3 rezervari mock|        |                   |
  +-----------------+        +-----------------+        +-------------------+
```

## Ce NU avem inca (si unde s-ar adauga)

| Feature lipsa        | Unde s-ar adauga                                   |
|----------------------|----------------------------------------------------|
| Baza de date reala   | inlocuim `ArrayList` cu JPA repositories           |
| Autentificare        | adaugam Spring Security + `User` real              |
| Upload imagini       | `MultipartFile` in `ToolController#createTool`     |
| Recenzii             | clasa noua `Review` legata de `Tool` si `User`     |
| Mesagerie owner/chir.| `MessageService` + pagina noua `/messages`         |
