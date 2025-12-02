# 🌐 Pinterest Murcia - Práctica Layout Grid & Flexbox

<p align="center">
  <a href="https://vicenttto.github.io/proyectos/bootstrap/index.html">
    <img alt="Ver Sitio en Vivo" src="https://img.shields.io/badge/Ver_Sitio_en_Vivo-007AFF?style=for-the-badge&logo=githubpages&logoColor=white">
  </a>
</p>

<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img alt="FontAwesome" src="https://img.shields.io/badge/FontAwesome-339AF0?style=for-the-badge&logo=fontawesome&logoColor=white">
</p>

Este proyecto consiste en el desarrollo de un **tablero estilo Pinterest** centrado en localizaciones turísticas de la Región de Murcia, utilizando **CSS Grid** para el layout estructural y **Flexbox** para la galería de contenido, sin depender de frameworks externos.

El objetivo principal es dominar la maquetación fluida, la nueva sintaxis de **Media Queries (Level 4)** y la manipulación del DOM con **Vanilla JavaScript**.

---

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura de Archivos](#-estructura-de-archivos)

---

## 🚀 Características Principales

- **Layout Híbrido (Grid + Flex):**

  - Estructura principal (Cabecera, Menú, Cuerpo, Pie) controlada mediante **CSS Grid** y `grid-template-areas`.
  - Galería de imágenes fluida controlada con **Flexbox** (`flex-wrap`, `grow`, `shrink`) para adaptarse al contenido.

- **Diseño Responsive (3 Etapas):**

  - **Móvil (< 600px):** Diseño vertical en una sola columna.
  - **Tablet (600-800px):** Menú lateral estrecho y cabecera completa.
  - **Escritorio (> 800px):** Sidebar fija ancha y contenido expandido. Uso de la sintaxis moderna `width >= 800px`.

- **Interactividad y Lógica JS:**

  - **Efecto "Me Gusta":** Al pulsar el corazón, cambia de estado (relleno rojo) mediante manipulación de clases (`classList`).
  - **Reordenación Dinámica:** La tarjeta con "Like" se mueve automáticamente al inicio de la galería utilizando el método `prepend()` del DOM.
  - **Overlay:** Efecto de oscurecimiento y aparición de botones al hacer _hover_ sobre las imágenes.

- **Optimización y Estética:**
  - Diseño moderno con bordes muy redondeados ("Pill buttons"), sombras suaves y paleta de colores corporativa.
  - Uso de imágenes locales para garantizar la carga y rendimiento.

---

## 🛠️ Tecnologías Utilizadas

- **HTML5:** Estructura semántica (`header`, `aside`, `main`, `article`).
- **CSS3:** Variables CSS (`:root`), Grid Layout, Flexbox y transiciones personalizadas (Curvas Bézier).
- **JavaScript (ES6):**
  - Manipulación del DOM (`querySelector`, `closest`).
  - Event Listeners para la interacción del usuario.
- **FontAwesome:** Iconografía vectorial para la interfaz.

---

## 📁 Estructura de Archivos

El proyecto consta de una estructura organizada separando lógica, estilos y recursos (assets) para facilitar el mantenimiento.

```text
/
├── assets/
│   └── img/         # Imágenes locales (catedral.jpg, lorca.jpg, etc.)
├── css/
│   └── styles.css   # Hoja de estilos principal (Grid, Flex, Variables)
├── js/
│   └── script.js    # Lógica de likes y reordenación del DOM
├── index.html       # Estructura semántica principal
└── README.md        # Documentación del proyecto
```
