**FILTRO DE RESEÑA IA**

Aplicación web reactiva desarrollada con Spring Boot WebFlux y Java 23. Este sistema integra el modelo de lenguaje de Google Gemini (a través de LangChain4j) para realizar análisis de sentimiento y detección de bots en reseñas de usuarios de forma automatizada y no bloqueante.

**TECNOLOGÍAS Y ARQUITECTURA**

El proyecto está construido bajo una arquitectura reactiva, garantizando alta concurrencia y tiempos de respuesta óptimos:

- Backend: Java 23, Spring Boot (WebFlux).
- Seguridad: Spring Security (Protección de rutas y autenticación).
- Persistencia: Spring Data R2DBC (Acceso a base de datos reactiva).
- Inteligencia Artificial: LangChain4j con Google Gemini API.
- Frontend: HTML5, Thymeleaf y Tailwind CSS.
- Testing: JUnit 5, WebTestClient, Mockito (TDD).
- CI/CD: GitHub Actions.

**REQUISITOS PREVIOS**

Antes de ejecutar el proyecto localmente, asegúrate de tener instalado:

- Java Development Kit (JDK) 23
- Apache Maven
- Una clave de API válida de Google AI Studio.

**INSTALACIÓN Y CONFIGURACIÓN**

1. Clonar el repositorio Abre tu terminal y ejecuta: git clone [https://github.com/tu-usuario/ResenasReactiveGem.git](https://www.google.com/search?q=https://github.com/tu-usuario/ResenasReactiveGem.git) cd ResenasReactiveGem
1. Configurar la API Key de Gemini Navega hasta el archivo de propiedades ubicado en src/main/resources/application.properties y configura tu clave de acceso reemplazando el texto de ejemplo: gemini.api.key=TU\_CLAVE\_API\_DE\_GEMINI\_AQUI langchain4j.googleai.gemini.api-key=TU\_CLAVE\_API\_DE\_GEMINI\_AQUI
1. Compilar e instalar dependencias En la raíz del proyecto, ejecuta: mvn clean install

**EJECUCIÓN DEL PROYECTO**

Para levantar el servidor localmente, ejecuta el siguiente comando: mvn spring-boot:run

Una vez que la consola indique que el servidor Netty ha iniciado, la aplicación estará disponible en: http://localhost:8080

Nota: La ruta principal redirigirá automáticamente al sistema de inicio de sesión (/login) configurado por Spring Security.

**PRUEBAS (TESTING)**

El proyecto fue desarrollado aplicando la metodología Test-Driven Development (TDD) con el ciclo Red-Green-Refactor. Cuenta con una batería de pruebas unitarias y de integración que validan:

- Inyección de dependencias mediante MockBean (evitando consumo de cuota de la API en pruebas).
- Reglas de enrutamiento y filtros de Spring Security.
- Manejo de excepciones globales y validación de formatos HTTP.

Para ejecutar la batería de pruebas automatizadas: mvn test

**INTEGRACIÓN CONTINUA (CI/CD)**

El repositorio cuenta con un pipeline configurado en GitHub Actions (ci.yml). Cada vez que se realiza un push o se abre un Pull Request hacia la rama main, un entorno virtual clona el código y ejecuta automáticamente los tests (mvn clean test) para garantizar que las nuevas integraciones no rompan las funcionalidades existentes.

**AUTORES** 

Desarrollado como proyecto académico de ingeniería por Freddy Jiménez y Edith Chuico.


 
**LO QUE ESTÁ ACÁ ABAJO ES EL README ANTERIOR**

 
 
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



