### 1 Development tools

* IntelliJ or any preferred IDE
* JAVA 11
* Maven 3.8.6
* Docker
*******

### 2 Run application

- mvn clean package -DskipTests
- docker build .
-docker images    -> copiar el id de la imagen
- docker run  -p  8080:8080   id_de_la_imagen

After the above, the application will be executed in http://localhost:8080/

### 2 Access the swagger
#### Swagger http://localhost:8080/swagger-ui/index.html#/
