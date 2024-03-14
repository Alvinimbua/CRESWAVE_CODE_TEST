# Blogging platform
## Description
The Blogging platform is API Implementation for performing CRUD Operations on BlogPosts and Comments and also implementing authentication.

## Features
- Users should be able to register, login, and manage their profiles.
- Authenticated users can create, read, update, and delete blog
posts.
- Users can comment on blog posts, and comments can be edited
and deleted.
- Implement role-based access control (RBAC) where only
authorized users can perform certain actions (e.g., only
administrators can delete posts).
- Implement pagination and sorting for blog posts and comments.
- Implement search functionality to search for blog posts by title or
content.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL (storing user data, blog posts, & comments)
- Maven (dependency management)
- Postman (testing endpoints)
- Swagger-UI (API Documentation)
- Spring Security (Authentication, Authorization & Roles- (RBAC)
- Pageables (Pagination and Sorting)

## Setup
Clone the repository:

Copy code
```zsh
git clone <repository_url>
```
Navigate to the project directory:


Copy code
```zsh
cd CRESWAVE_CODE_TEST
```
Build the project using Maven:


Copy code
```zsh
mvn clean install
```
Run the application:


Copy code
```zsh
java -jar target/CRESWAVE_CODE_TEST.jar
```
The application will start running on http://localhost:8080.

To test the API uisng Swagger, run http://localhost:8080/swagger-ui/index.html

## Author
Alvin Imbuka

## License
[MIT](https://choosealicense.com/licenses/mit/)
