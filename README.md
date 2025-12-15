# Clínica de Adopciones Mascotas - Proyecto Diseño de Software

Bienvenido al repositorio del proyecto Clínica de Adopciones Mascotas.

Este proyecto ha sido desarrollado como parte del curso de Diseño de Software. Se trata de una aplicación de escritorio construida en Java que tiene como objetivo facilitar el proceso de adopción de mascotas. La aplicación permite a los usuarios registrarse, explorar un catálogo de mascotas disponibles y gestionar solicitudes de adopción. Para los administradores, ofrece herramientas para gestionar el inventario de mascotas y revisar las solicitudes entrantes.

## Estructura y Tecnologías

La aplicación está construida sobre una arquitectura modular para facilitar su mantenimiento y escalabilidad. Las principales tecnologías utilizadas son:

*   **Java 21**: Lenguaje principal de desarrollo.
*   **Apache Maven**: Utilizado para la gestión de dependencias y construcción del proyecto.
*   **Java Swing**: Para la interfaz gráfica de usuario.
*   **MongoDB**: Base de datos NoSQL utilizada para la persistencia de usuarios, mascotas y solicitudes.

El proyecto se divide en los siguientes módulos:
*   **Presentacion**: Contiene toda la lógica de la interfaz gráfica.
*   **Negocio**: Alberga la lógica de negocio y reglas del sistema.
*   **Persistencia**: Maneja la conexión y operaciones con la base de datos MongoDB.
*   **Infraestructura**: Servicios comunes y utilidades.
*   **Módulos de funcionalidad**: gestion-catalogo, gestion-MascotasArchivadas, CUAceptarSolicitudes, CUBuscarMascotaIdeal.

## Puntos Clave del Sistema

El sistema cuenta con autenticación de usuarios y roles diferenciados (usuario común y administrador). Se hace uso de `mongodb-driver-sync` para la conexión a datos y `javax.mail` para notificaciones por correo electrónico.

## Instalación y Ejecución

Para ejecutar este proyecto en tu entorno local, necesitarás tener instalado Java JDK 21 y Maven, además de una instancia de MongoDB en ejecución (por defecto en el puerto 27017).

1.  Clona este repositorio en tu máquina.
2.  Ejecuta `mvn clean install` en la raíz del proyecto para descargar las librerías necesarias y compilar los módulos.
3.  **Ejecución:** El punto de entrada de la aplicación se encuentra en el módulo **Presentacion**.
    Deberás ejecutar la clase `gui.FrmInicioSesion` (referida como InicioSesion). Esta clase inicializa la ventana de login y da acceso al resto del sistema.

Si utilizas un IDE como NetBeans o IntelliJ, asegúrate de establecer el módulo `Presentacion` y la clase `gui.FrmInicioSesion` como la configuración de ejecución principal.

## Contribución

Este es un proyecto académico. Si deseas probarlo o revisar el código, siéntete libre de clonarlo. Asegúrate de configurar correctamente la conexión a la base de datos antes de iniciar.
