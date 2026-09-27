# Online Grocery Order Management API

A RESTful backend built with Spring Boot, Spring Data JPA, and MySQL for managing customers, grocery items, and grocery orders.

## Tech stack
- Java 17
- Spring Boot 3
- Spring Web, Spring Data JPA, Bean Validation
- MySQL
- Maven

## Run locally
1. Install Java 17+, Maven, and MySQL.
2. Create a MySQL user/database or use the defaults in `application.properties`.
3. Set environment variables if needed:
   - `DB_URL` (default `jdbc:mysql://localhost:3306/grocery_db?createDatabaseIfNotExist=true`)
   - `DB_USERNAME` (default `root`)
   - `DB_PASSWORD` (default empty)
4. Run: `mvn spring-boot:run`

Hibernate creates/updates tables automatically with `spring.jpa.hibernate.ddl-auto=update`.

## API endpoints

### Customers
- `POST /api/customers`
- `GET /api/customers`
- `GET /api/customers/{id}`
- `PUT /api/customers/{id}`
- `DELETE /api/customers/{id}`

Example customer JSON:
```json
{"name":"Rahul","email":"rahul@example.com","address":"Kochi","phone":"9876543210"}
```

### Grocery items
- `POST /api/items`
- `GET /api/items`
- `GET /api/items/{id}`
- `PUT /api/items/{id}`
- `DELETE /api/items/{id}`

Example item JSON:
```json
{"name":"Rice","category":"Grains","price":60.00,"quantity":25}
```

### Orders
- `POST /api/orders`
- `GET /api/orders`
- `GET /api/orders/{id}`
- `PUT /api/orders/{id}`
- `DELETE /api/orders/{id}`

Example order JSON:
```json
{"customerId":1,"items":[{"groceryItemId":1,"quantity":2},{"groceryItemId":2,"quantity":3}]}
```
The order total is calculated from the current grocery item prices and requested quantities. Each order line stores the unit price at order time.

## Error handling
- 404 for missing customer, item, or order
- 400 for invalid request fields
- 409 for database integrity conflicts

## Notes
This is a basic CRUD assignment. Inventory is recorded, but stock is not deducted when an order is placed.
# online_grocery_ordermanagement
Spring Boot REST API for managing customers, grocery items, and orders using Spring Data JPA and MySQL.
