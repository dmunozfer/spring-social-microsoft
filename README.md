# spring-social-microsoft v0.1.1

## Información
Spring social microsoft es un conector de **spring-social** para la plataforma de Microsoft.

Con este conector podrás acceder a la api REST de Live y autenticar a los usuarios de aplicación directamente con la cuenta de Microsoft.

## Módulos
El proyecto se compone de los siguientes módulos:

- **spring-social-microsoft**: módulo principal que permite la integración de spring-social con la plataforma de Microsoft.
- **spring-social-microsoft-autoconfigure**: proyecto para permitir la autoconfiguración con Spring Boot.
- **spring-social-microsoft-demo**: aplicación web de demostración.


## Configuración
Gracias a las capacidades de autoconfiguración de Spring Boot basta con añadir al fichero application.properties dos entradas con la información del id y el código de la aplicación para que se instancien los servicios necesarios.

```
spring.social.microsoft.appId=ID_APLICACION
spring.social.microsoft.appSecret=CONTRASEÑA_APLICACION
```

## Ejecución
En el proyecto **spring-social-demo** tenemos una aplicación de demostración en la que podemos ver el funcionamiento del conector. Pero antes de ejecutarla tenemos que darnos de alta en el programa de desarrollo de Microsoft, crear una aplicación y configurar nuestra aplicación con los datos correctos.

1. Creando cuenta desarrollador en Microsoft

   Lo primero es registrarse en el programa de desarrollador de Microsoft, para ello acceder a la URL [https://apps.dev.microsoft.com/](https://apps.dev.microsoft.com/) y entrar con tu cuenta de usuario.

2. Dando de alta la aplicación

   Una vez que hemos accedido al programa debemos crear una nueva aplicación.
   Poner un nombre a la aplicación y generar una nueva contraseña, anotarla en un lugar seguro ya que no podremos volver a visualizarla.
   Agregar una nueva plataforma web y añadir la URL [http://localhost:8080/connect/microsoft](http://localhost:8080/connect/microsoft) cono URL de redireccionamiento. Esta será la dirección en la que se ejecutará por defecto nuestra aplicación de ejemplo.
   
3. Configurando aplicación de ejemplo

   Editar el fichero spring-social-microsoft-demo/src/main/resources/application.properties
   Introducir el identificador y el password de la aplicación.

4. Probando la aplicación

   Al ejecutar la aplicación spring-social-microsoft-demo se podrá acceder a la aplicación desde la dirección [http://localhost:8080/](http://localhost:8080/)
   
   Al pulsar en el botón "Connect to Microsoft" seremos redirigidos a la página de Microsoft en la que se nos pedirá autorización para conceder los permisos a la aplicación para acceder a la información básica y email de nuestro perfil de microsoft. Una vez concedidos los permisos seremos redirigidos a de nuevo a nuestra aplicación de demo, en la que se nos mostrará la información de nuestro perfil de Microsoft.