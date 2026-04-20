# EcoLoop - Pitic Andrei, Constantinescu Miriam, Piciu Diana

A community-focused tool sharing MVP built with **Java + Spring Boot + Thymeleaf**.
Neighbors can browse tools, view details, list their own tools, and book rentals.

All data is held in memory - there is no real database, so restarting the app resets everything.

## Run the project

### Prerequisites
- Java 17+
- Maven 3.8+ (or use the Maven wrapper from your IDE)

### Start the app

```bash
mvn spring-boot:run
```

Then open http://localhost:8080 in your browser.

### Package and run as a jar

```bash
mvn clean package
java -jar target/ecoloop-0.0.1-SNAPSHOT.jar
```

## Routes

| Path                  | Description                           |
|-----------------------|---------------------------------------|
| `/`                   | Home page (hero, how it works, featured tools) |
| `/tools`              | Browse tools with search & filter     |
| `/tools/{id}`         | Tool detail + booking box             |
| `/tools/new`          | Form to list a new tool               |
| `/tools/{id}/book`    | POST booking for a tool               |
| `/history`            | Current user's rental history         |
| `/bookings`           | All bookings in the system            |

## Project structure

```
iwp/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/ecoloop/
    │   ├── EcoLoopApplication.java
    │   ├── controller/
    │   │   ├── HomeController.java
    │   │   ├── ToolController.java
    │   │   ├── BookingController.java
    │   │   └── UserController.java
    │   ├── model/
    │   │   ├── User.java
    │   │   ├── Tool.java
    │   │   ├── Booking.java
    │   │   ├── RentalHistoryEntry.java
    │   │   ├── Category.java
    │   │   └── ToolStatus.java
    │   └── service/
    │       ├── ToolService.java
    │       ├── BookingService.java
    │       ├── UserService.java
    │       └── RentalHistoryService.java
    └── resources/
        ├── application.properties
        ├── static/css/style.css
        └── templates/
            ├── fragments/
            │   ├── navbar.html
            │   └── footer.html
            ├── index.html
            ├── tools.html
            ├── tool-details.html
            ├── new-tool.html
            ├── history.html
            └── bookings.html
```

## Notes for a university presentation

- Controllers receive requests and populate the `Model`; Thymeleaf templates render HTML on the server.
- `@Service` classes hold mock data in simple `ArrayList`s seeded at startup via `@PostConstruct`.
- Fragments (`navbar`, `footer`) are reused across pages using `th:replace="~{fragments/... :: name}"`.
- Form handling is done with `@ModelAttribute` binding onto domain classes - no DTOs needed.
- Styling is a single handwritten `style.css` - no frontend framework.

## Possible next steps

- Swap the in-memory lists for a JPA / H2 database.
- Add login and real users with Spring Security.
- Upload tool images instead of linking to URLs.
- Add reviews and messaging between renters and owners.
