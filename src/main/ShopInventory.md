# Shop Inventory API
What you need for this project

- MySQL Server running locally
- Postman (or similar) for testing endpoints

### How the Project Was Created
- via Spring Initializr with:
- Project: Maven
- Language: Java
- Spring Boot version: 4.1.0
- Dependencies: Spring Web, Spring Data JPA, MySQL Driver, Lombok

1) After this downloaded the generated project zip file, extract it to a local folder, then opened that folder it in your IDE
3) Add the OpenAPI/Swagger dependency manually to `pom.xml`

 ```java
<dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
       <version>3.0.3</version>
   </dependency>
```

4) Create the database and connect it to your project.
5) Build out the model (Apple), repository (AppleRepository), and controller (AppleController) see on How to Create Endpoints below.

## How to Create Endpoints

Endpoints are defined in AppleController.java using Spring's `@RestController` annotation on the class, with individual HTTP methods mapped using `@GetMapping / @PostMapping` on each method.

### Steps to add a new endpoint:

1) Add a method inside AppleController.
2) Annotate it with the relevant HTTP mapping and route, e.g.:
   `@GetMapping("/shopInventory/fruits/apple/search")`

3) Use `@RequestParam` to accept query parameters, or `@RequestBody` to accept a JSON body (for POST requests).
   Call the repository (appleRepository) to interact with the database e.g. .findByVariety() or .save().
   Wrap the return value in ResponseEntity.ok() to send it back as an HTTP response.

### Example of a search Endpoint
```java
@GetMapping("/shopInventory/fruits/apple/braeburn")
public ResponseEntity<List<Apple>> searchApples(@RequestParam String variety) {
    log.info("search apples by variety");
    List<Apple> results = appleRepository.findByVariety(variety);
    if (results.isEmpty()) {
        log.warn("no apples found");
    }
    return ResponseEntity.ok(results);

}
```
### How to Run

### 1. Set up your database and seed it with data

Open MySQL and run the script located at:

`shopInventory/src/main/resources/database/schema.sql`

This will:
- Create the `shop_db` database
- Create the `apple` table
- Insert sample data

### 2. Configure the application

Open `shopInventory/src/main/resources/application.yaml` and update the following with your own MySQL credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/shop_db
    username: YOUR_MYSQL_USERNAME
    password: YOUR_MYSQL_PASSWORD
    driver-class-name: com.mysql.cj.jdbc.Driver

shop:
  name: "Apple Market"
```

- `username` / `password` your local MySQL credentials and should never be pushed to git hub

### 3. Run the application from

`com.cfg.shopInventory.ShopInventoryApplication`


The app will start on:

`http://localhost:8080`

## API Endpoints

### Search apples by variety

`GET /shopInventory/fruits/apple/braeburn?variety=Braeburn`

Returns matching apples from the database.

### Add a new apple

```
POST /shopInventory/fruits/apple
```

Body (raw JSON):

```json
{
  "variety": "Mustu",
  "origin": "Japan",
  "colour": "red",
  "weightGrams": 140,
  "inSeason": true
}
```

## API Documentation (OpenAPI / Swagger)

Once the app is running, view the full API spec at:

`http://localhost:8080/swagger-ui/index.html`

## Application Flow

//add application flow using mermaid
