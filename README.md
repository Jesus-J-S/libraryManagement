# Biblioteca Java: Primeras Aventuras en la Programación

## Descripción

Bienvenidos a **Biblioteca Java: Primeras aventuras en la Programación**. 
En esta práctica, desarrollarás una aplicación en Java para gestionar una biblioteca. Implementarás clases y métodos para manejar libros y usuarios, y utilizarás JUnit para verificar el correcto funcionamiento del código. Además, se incluyen desafíos adicionales para los estudiantes más avanzados.

## Instrucciones

### 1. Hacer Fork del Repositorio

1. Ve al repositorio original en GitHub.
2. Haz clic en el botón "Fork" en la esquina superior derecha para crear una copia del repositorio en tu cuenta de GitHub.
3. Clona el repositorio forkeado a tu máquina local:
   ```bash
   git clone https://github.com/rafaelsua/libraryManagement.git
   ```
### 2. Instalación
   Abre el proyecto en tu IDE (IntelliJ, Eclipse, Visual Studio Code).
   Asegúrate de tener instalado Java y Maven.

### 3. Ejecución

1.  Ejecuta la clase Biblioteca para probar la funcionalidad básica.
2.  Ejecuta los tests de JUnit en la clase BibliotecaTest para verificar el correcto funcionamiento.

### 4.  Ejercicios a Realizar

#### 1. Clase Book
      En la clase Book los atributos son: titulo (String), autor (String), isbn (String), disponible (boolean)
      Implementa un constructor para inicializar los atributos.
      Implementa métodos getters y setters para los atributos.
      Implementa un método toString para mostrar la información del libro.
#### 2. Clase User      
      Implementa un método borrowBook para añadir un libro al array de libros prestados.
      Implementa un método returnBook para eliminar un libro del array de libros prestados.
      Implementa un método reserveBook para reservar libros que no están disponibles.
#### 3. Clase Biblioteca
      Crea una clase Biblioteca con los siguientes atributos: libros (Array de Libro), usuarios (Array de Usuario).
      Implementa un constructor para inicializar los atributos.
      Implementa métodos para añadir libros y usuarios.
      Implementa métodos para prestar y devolver libros.
      Implementa métodos para buscar libros por título y autor.
#### 4. JUnit Tests
      Crea una clase de pruebas BibliotecaTest utilizando JUnit para verificar el correcto funcionamiento de los métodos.
      Desafíos Adicionales

#### 5. Implementar búsqueda de libros:

      Añade un método en la clase Biblioteca para buscar libros por título o autor.

#### 6. Implementar reserva de libros:

      Añade un método en la clase Usuario para reservar libros que no están disponibles.

#### 7. Desafío para los más avanzados:
      
      Implementa una conexión a una base de datos utilizando JDBC para almacenar y recuperar información de libros y usuarios. Haz uso de la clase LibraryJDBC

### 5. Contacto
Si tienes dudas, puedes enviar un email a [tu-email@example.com].

