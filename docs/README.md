# Proyecto de Maquetación Básica con Firebase

<p align="center">
  <a href="https://vicenttto.github.io/proyectos/docs/">
    <img alt="Ver Sitio en Vivo" src="https://img.shields.io/badge/Ver_Sitio_en_Vivo-007AFF?style=for-the-badge&logo=githubpages&logoColor=white">
  </a>
</p>
<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img alt="Firebase" src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black">
</p>

Este es un proyecto web _boilerplate_ (plantilla de inicio) que combina una maquetación HTML/CSS moderna con una integración funcional de backend usando Firebase.

El proyecto incluye una página de inicio estática (`index.html`) y un flujo de autenticación de usuarios con páginas de registro (`registro.html`) y login (`login.html`).

---

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Estructura de Archivos](#-estructura-de-archivos)

---

## 🚀 Características Principales

- **Maquetación con Flexbox:** El layout principal de la web y los formularios está construido con Flexbox.
- **Diseño Coherente:** Se utilizan variables CSS para mantener una paleta de colores y tipografía consistentes en todo el sitio.
- **Interactividad con JS:**
  - Menú de cabecera flotante (hamburguesa) que se activa con JavaScript.
  - Animación de "fade-in" en el logo al cargar la página.
  - Botón de "mostrar/ocultar" contraseña en los formularios de autenticación.
- **Autenticación con Firebase:**
  - **Registro de Usuarios:** Los usuarios nuevos pueden crear una cuenta. El sistema crea el usuario en **Firebase Authentication**.
  - **Inicio de Sesión:** Los usuarios existentes pueden iniciar sesión.
- **Integración con Firestore (Base de Datos):**
  - Al **registrarse**, los datos del usuario (nombre, email, dirección, año de nacimiento) se guardan en una colección `users` en Cloud Firestore.
  - Al **iniciar sesión**, se guarda un registro (fecha y hora) en una colección `login_history` en Cloud Firestore.
- **Validación de Formularios:**
  - El JavaScript comprueba que los correos tengan un formato válido (expresión regular).
  - Comprueba que las contraseñas coincidan y tengan al menos 6 caracteres.
  - Muestra mensajes de error claros al usuario.

---

## 🛠️ Tecnologías Utilizadas

- **HTML5** (Semántico)
- **CSS3** (Variables, Flexbox)
- **JavaScript (ES6+)**
- **Firebase Authentication** (para gestión de usuarios)
- **Cloud Firestore** (para base de datos NoSQL)
- **Google Fonts**
- **Material Symbols** (para iconos)

---

## ⚙️ Instalación y Configuración

<details>
<summary><strong>Haz clic para ver los pasos de instalación y configuración</strong></summary>
<br>
Para ejecutar este proyecto localmente y conectarlo a tu propio backend de Firebase, sigue estos pasos:

1.  **Clona o descarga este repositorio.**

2.  **Crea un proyecto en Firebase:**

    - Ve a [firebase.google.com](https://firebase.google.com/) y crea un nuevo proyecto.
    - En el panel de control, ve a **Authentication** -> **Sign-in method** y activa **"Correo electrónico/Contraseña"**.
    - Ve a **Cloud Firestore** -> **Crear base de datos** y configúrala en **"Modo de prueba"**.

3.  **Obtén tu Configuración de Firebase:**

    - En la "Configuración del proyecto" (icono de ⚙️), ve a la pestaña "General".
    - Baja a "Tus apps" y haz clic en el icono web (`</>`) para registrar tu aplicación.
    - Copia el objeto `firebaseConfig` que te proporciona Firebase.

4.  **Pega tu Configuración:**

    - Abre el archivo `js/auth.js`.
    - Pega tu objeto `firebaseConfig` en la sección indicada (líneas 6-14).

5.  **Ejecuta el proyecto:** \* Abre el archivo `index.html` en tu navegador (se recomienda usar una extensión como "Live Server" en VS Code).
</details>

---

## 📁 Estructura de Archivos

<details>
<summary><strong>Haz clic para ver la estructura</strong></summary>

<br>

```
/
├─ index.html
├─ README.md
├─ css/
│  ├─ styles.css
│  └─ forms.css
├─ js/
│  ├─ script.js
│  └─ auth.js
├─ pages/
│  ├─ login.html
│  └─ registro.html
└─ media/
   ├─ setup.jpg
   ├─ codigo.jpg
   └─ favicon.ico
```

</details>
