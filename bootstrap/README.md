# 🌐 Maqueta Cisco NetAcad - Práctica Bootstrap 5

<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="Bootstrap" src="https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img alt="FontAwesome" src="https://img.shields.io/badge/FontAwesome-339AF0?style=for-the-badge&logo=fontawesome&logoColor=white">
</p>

Este proyecto consiste en la maquetación responsive de la landing page de **Cisco Networking Academy**, utilizando el framework **Bootstrap 5** para la estructura y el sistema de rejilla, junto con **CSS personalizado** para la identidad visual corporativa.

El objetivo principal es dominar el uso de componentes, utilidades de visualización (`display`) y la adaptabilidad a diferentes dispositivos (Mobile First).

---

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura de Archivos](#-estructura-de-archivos)

---

## 🚀 Características Principales

- **Sistema Grid Avanzado (Damero):**
  - Implementación de un diseño alterno (Imagen/Texto - Texto/Imagen) utilizando filas y columnas de Bootstrap.
  - Comportamiento responsive: 2 columnas en escritorio (`col-md-6`) y 1 columna en móvil (`col-12`).

- **Componentes de Navegación:**
  - **Navbar:** Cabecera dividida en 3 secciones lógicas (Menú hamburguesa, Logo centrado, Herramientas derecha).
  - **Footer:** Adaptable según el dispositivo. En escritorio muestra iconos sociales en fila; en móvil se transforma en un componente **Accordion** para ahorrar espacio.

- **Interactividad y Efectos:**
  - **Collapse:** Sección de categorías donde al hacer clic en los iconos (imágenes), se despliega información adicional.
  - **Animaciones al Scroll (AOS):** Integración de la librería *Animate On Scroll* para efectos de aparición (`fade-up`, `zoom-in`).
  - **Micro-interacciones:** Efectos *hover* personalizados en imágenes y botones con CSS (elevación y sombras).

- **Optimización:**
  - Uso del atributo `loading="lazy"` en las imágenes para mejorar el rendimiento de carga inicial.
  - Separación de preocupaciones: HTML para estructura, CSS externo para estilos.

---

## 🛠️ Tecnologías Utilizadas

- **HTML5:** Estructura semántica.
- **Bootstrap 5.3:** Framework principal (Grid, Navbar, Cards, Utilities).
- **CSS3:** Variables CSS (`:root`), gradientes y personalización de colores corporativos (Cisco Blue).
- **JavaScript:**
  - `bootstrap.bundle.js` para la lógica de componentes (modales, colapsables).
  - Librería **AOS** (Animate On Scroll) para animaciones visuales.
- **FontAwesome:** Iconografía vectorial.

---

## 📁 Estructura de Archivos

El proyecto consta de una estructura plana donde todos los recursos principales se encuentran en el directorio raíz para facilitar la práctica y el despliegue rápido.

```text
/
├── index.html       # Estructura principal, componentes Bootstrap y scripts
├── styles.css       # Estilos personalizados, variables de color y efectos hover
└── README.md        # Documentación del proyecto