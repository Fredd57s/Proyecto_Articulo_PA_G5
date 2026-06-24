**INSTRUCCIONES DE EJECUCION - PROYECTO B (ARQUITECTURA REACTIVA WEBFLUX)**

**GRUPO 5**

PRERREQUISITOS:

- Java Development Kit (JDK) 21 o superior instalado en el sistema.
- MySQL Server instalado y ejecutándose de manera local (puerto 3306).
- Apache JMeter descargado para las pruebas de estrés.
- IDE (IntelliJ IDEA, Eclipse, etc.) con soporte para Maven.

PASO 1: PREPARACION DE LA BASE DE DATOS

1. Abre tu cliente de MySQL (ej. MySQL Workbench).
1. Asegúrese de tener creada la base de datos llamada: resenas\_proyecto
1. Nota: Como R2DBC no auto-genera tablas, asegúrese de que la tabla "reviews\_history" exista (se debió generar previamente al correr el Proyecto A del primer parcial).

PASO 2: CONFIGURACION DE CREDENCIALES

1. En su IDE, navegue a la carpeta src/main/resources y abre el archivo application.properties.
1. Modifique la linea spring.r2dbc.password= y coloca la contraseña de tu base de datos MySQL local.
1. Modifique la línea gemini.api.key= y coloca tu clave de API valida de Google Gemini.

PASO 3: INICIO DEL SERVIDOR

1. Abra la clase principal ResenasApplication.java.
1. Ejecute la aplicación.
1. Verifica en la consola que no existan errores rojos y busca la línea que confirme que el servidor arranco correctamente: "Netty started on port 8080".

PASO 4: ACCESO AL SISTEMA (INTERFAZ GRAFICA)

1. Abre un navegador web e ingresa a: http://localhost:8080/dashboard
1. Serás redirigido a la pantalla de login.
1. Ingresa con tus credenciales usando el usuario "admin" y la contraseña “admin123”.
1. Ya puede utilizar el dashboard para analizar reseñas manualmente.

PASO 5: EJECUCION DE PRUEBAS DE ESTRÉS

1. Utilizar el proyecto de prueba (es necesario hacerlo con esto, ya que se tiene una simulación de las Peticiones a la API de Google, ya q si se hace directamente con la API real, esta se bloqueará): <https://github.com/Fredd57s/Proyecto_Articulo_PA_G5_PARA_EVALUAR.git>
1. Abra Apache JMeter y cargue su plan de pruebas (Test Plan).
1. En el "Thread Group", configure el numero de usuarios (Number of Threads) a 100 o 500.
1. Asegúrese de que la petición HTTP apunte al método POST en la ruta /api/analyze.
1. Ejecuta la prueba (botón Play) y observa los resultados en tiempo real en los módulos "View Results Tree" y "Summary Report".

