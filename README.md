# 🚀 InvexTest - Employee Management API

RESTful API for managing employee data, developed with:

- 🧩 Spring Boot  
- 🔐 Spring Security (JWT)  
- 🗄️ Spring Data JPA (Hibernate)  
- 🛢️ H2 / MySQL support  

This project supports **CRUD operations**, secure **JWT authentication**, and **name-based search**.

---

## 📦 Requirements

- ☕ Java 17 or higher  
- 🧰 Maven or Gradle  
- 🧪 Git  
- 💾 (Optional) MySQL (H2 is used by default)  

---

## ⚙️ Installation & Execution

### 🔁 Clone the repository

```bash
git clone https://github.com/CarlosDesarrolloJordan/InvexTest.git
cd InvexTest


▶️ Run the project
./mvnw spring-boot:run

📌 The application will start at:
http://localhost:8080



🔐 Authentication (JWT)
POST /auth/login

Request Body
{
  "username": "admin",
  "password": "1234"
}

Response
{
  "token": "Bearer eyJhbGciOiJIUzI1NiJ9..."
}


🔐 Use this token in every secured request:
Authorization: Bearer <your_token>



📚 API Endpoints

Method	Endpoint	Description
GET	/employees	Get all employees
GET	/employees/{id}	Get employee by ID
POST	/employees	Create one or multiple employees
PUT	/employees/{id}	Update an existing employee
DELETE	/employees/{id}	Delete an employee (hard delete)
GET	/employees/search?name=x	Search employees by name

    🔐 All endpoints require authentication, except /auth/login.


🧪 Testing
This will execute all unit tests using:

    JUnit 5

    Mockito

    Service and controller test coverage



📖 Swagger API Documentation
Access the Swagger UI at:
http://localhost:8080/swagger-ui.html


Or (if using SpringDoc):
http://localhost:8080/swagger-ui/index.html


