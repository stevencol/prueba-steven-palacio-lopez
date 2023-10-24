# Prueba Técnica: Aplicativo Java con Spring Boot

# Introducción

Esta aplicación ha sido desarrollada en Java 17 con Spring Boot 3 para ofrecer una solución efectiva para registrar, gestionar y acceder a tutoriales de diversos temas.

Asegúrate de que Java 17 esté correctamente instalado y configurado en tu sistema. Puedes usar tanto el JDK oficial como OpenJDK.

## Requisitos previos

- Java 17 debe estar instalado. Asegúrate de configurar las variables de entorno JAVA_HOME y PATH correctamente.

- Se requiere una base de datos con el nombre "pruebaTecnica". Spring Data JPA se encargará de crear las tablas necesarias.

- En el archivo `application.properties` ubicado en `prueba-steven-palacio-lopez/src/main/resources`, asegúrate de reemplazar las siguientes líneas con los datos de tu base de datos:
- spring.datasource.username=nombreUsuario
- spring.datasource.password=contraseña

## 1 Opcion de ejecuion : Entornos de desarrollo recomendados

- Se recomienda usar IntelliJ IDEA como entorno de desarrollo, pero también puedes utilizar Visual Studio Code con la extensión de Java o NetBeans.

## 2 Opcion de ejecuion : Compilación con Apache Maven

Para compilar el proyecto, sigue estos pasos:

1. Descarga Apache Maven desde [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

2. Descomprime Maven en la ubicación deseada.

3. Agrega la carpeta `bin` de Maven a las variables del sistema en la variable PATH.

4. Reinicia tu sistema si es necesario.

5. Para verificar que Maven esté configurado correctamente, abre una terminal y ejecuta `mvn -v`. Deberías ver la versión de Maven.

6. Ahora puedes compilar el proyecto desde la raíz del proyecto usando el siguiente comando:

   - mvn clean package -DfinalName=nombreQueDesees

7. Una vez que el proceso haya terminado, encontrarás el archivo JAR en la carpeta `target`. Puedes ejecutar la aplicación desde la línea de comandos con el siguiente comando (asegúrate de reemplazar `mi-aplicacion.jar` con el nombre de tu archivo JAR):

## Puerto de la aplicación

La aplicación se ejecuta en el puerto 8080 de forma predeterminada. Si deseas cambiar el puerto, puedes modificar el archivo `application.properties`.

---
