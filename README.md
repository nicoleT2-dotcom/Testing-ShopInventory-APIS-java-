# Shop Inventory API

A Spring Boot REST API for managing shop inventory.

## Requirements
- MySQL Server running locally
- Maven 
- Postman (or similar) for testing endpoints

## Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/nicoleT2-dotcom/cfg-assignment-3.git
cd cfg-assignment-3
```

### 2. Open the project

Open the cloned folder in the IDEA. It should automatically detect the Maven project and download dependencies. If not, right-click `pom.xml` **Maven**  then **Reload project**.

### 3. Set up the database

Before running the application, you need to create the database and table it expects.

1. Make sure MySQL Server is running locally
2. Open a MySQL client (MySQL Workbench, or the command line)
3. Run the SQL script located in the project at: src/main/resources/database/schema.sql
4. 
**Via command line:**

```bash
mysql -u your_username -p < src/main/resources/database/schema.sql
```
This script will:
- Create the `shop_db` database
- Create the `apple` table
- Insert sample data

Once this has run successfully, `shop_db` should exist in your MySQL server, ready for the app to connect to.   

### 4. Configure your credentials

Open `src/main/resources/application.yaml` and update the following with your own MySQL credentials:

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

**Important:** never commit your real credentials to GitHub so add your application to your gitignore.

### 5. Run the application

Open `CfgAssignment3Application.java` and run the project

The app will start on: http://localhost:8080
---------

You'll know it's working if your terminal shows `Started CfgAssignment3Application` and `Tomcat started on port 8080`.

## How to Use the API

### Search apples by variety

GET /shopInventory/fruits/apple/search?variety=Braeburn

Returns matching apples from the database. Returns `404 Not Found` if no matches exist.

### Add a new apple 
POST /shopInventory/fruits/apple
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

### Interactive API docs (Swagger)

Once the app is running, view the full API spec at: http://localhost:8080/swagger-ui/index.html

-----

# Running Tests

Unit tests for `AppleService` are located at:src/test/java/com/cfg_assignment_3/service/AppleServiceTest.java

Run 'AppleServiceTest

Tests mock `AppleRepository` using Mockito, so no real database connection is required to run them.
-----
## Project Concepts (for reference)

This section briefly notes where key Java/Spring concepts are implemented:

- **Streams** – `AppleService.filterByVariety()` filters apples by variety using `.stream().filter()`
- **Generics** – `AppleService.requireNonEmpty()` is a generic `<T>` method reused across list checking logic
- **Exceptions** – `AppleException` is thrown in `AppleService` and caught in `AppleController`
- **Logging** – SLF4J logging is used in both the service and controller layers
