## Spring Boot

1. Se incluye código fuente
2. Se incluye archivo Postman para pruebas y generación de token de autenticación
3. No se incluye script de base datos pues se crea en forma automática en memoria

# How to build and deploy
1. Bajar codigo fuente
2. Ejecutar mvn spring-boot:run
3. Importar archivo Postman incluido
4. Crear un usuario (/auth/signup) y luego logearse (/auth/login) para obtener token
5. Llamar a "crear task" y "list all" usuando el token generado, se incluyen json request para hacer pruebas

Swagger disponible en http://localhost:8080/swagger-ui.html
