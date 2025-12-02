# ⚡ Neo-Tokyo Experience - Integración Web + P5.js

<p align="center">
  <a href="https://vicenttto.github.io/proyectos/web-p5-interactive/index.html">
    <img alt="Ver Sitio en Vivo" src="https://img.shields.io/badge/Ver_Sitio_en_Vivo-007AFF?style=for-the-badge&logo=githubpages&logoColor=white">
  </a>
</p>

<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="P5.js" src="https://img.shields.io/badge/p5.js-ED225D?style=for-the-badge&logo=p5dotjs&logoColor=white">
  <img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
</p>

Este proyecto es una exploración de **Diseño Generativo Integrado**. Consiste en una landing page de estética _Cyberpunk/Sci-Fi_ donde el contenido web tradicional (HTML/CSS) flota sobre un lienzo interactivo animado en tiempo real mediante la librería **P5.js**.

El objetivo es lograr que el componente multimedia aporte espectacularidad y profundidad sin interferir con la legibilidad del contenido, fusionando ambos mundos de forma natural mediante técnicas de superposición.

---

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Referencias y Modificaciones P5.js](#-referencias-y-modificaciones-p5js)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura de Archivos](#-estructura-de-archivos)

---

## 🚀 Características Principales

- **Fondo Interactivo (Canvas P5.js):**

  - Sistema de partículas que simula una red neuronal o constelación de datos.
  - **Interacción:** Las partículas reaccionan a la posición del ratón, creando conexiones dinámicas ("rayos de energía") cuando el usuario explora la web.
  - **Estética:** Uso de colores neón (Cian y Magenta) sobre fondo oscuro profundo.

- **Diseño Glassmorphism (CSS):**

  - Uso de paneles semitransparentes con `backdrop-filter: blur()` en CSS. Esto permite leer el texto claramente mientras se percibe el movimiento de las partículas por detrás.
  - Efectos de brillo, sombras y tipografía futurista.

- **Responsive Design:**
  - El Canvas se redimensiona automáticamente (`windowResized`) para ajustarse a cualquier dispositivo sin deformar la animación.
  - Grid Layout fluido para la galería de imágenes y formularios adaptables.

---

## 🎨 Referencias y Modificaciones P5.js

Siguiendo las buenas prácticas de desarrollo creativo open-source, este proyecto toma como punto de partida algoritmos comunitarios, añadiendo capas de complejidad e integración web:

- **Fuente Original (Inspiración):**

  - **Sketch:** "Constellation" (ID: 434620) en OpenProcessing.
  - **URL:** [https://www.openprocessing.org/sketch/434620](https://www.openprocessing.org/sketch/434620)
  - **Lógica Base:** Algoritmo de comparación de distancias euclidianas (`dist()`) entre arrays de partículas para dibujar conexiones dinámicas.

- **Modificaciones Realizadas (Aporte Propio):**
  1.  **Integración DOM (Canvas sobre HTML):** El código original dibuja en un canvas de pantalla completa por defecto. Se ha reescrito el `setup()` utilizando `canvas.parent('canvas-container')` para inyectar el gráfico dentro de un contenedor web específico con `z-index: -1`, permitiendo que el contenido HTML flote por encima.
  2.  **Estética Cyberpunk (Neon Palette):** Se ha sustituido el sistema de color monocromático del original por una lógica ternaria aleatoria (`random() > 0.5`) que asigna colores corporativos (Cian `#00f3ff` y Magenta `#ff00ff`) a cada instancia de la clase `Particle`.
  3.  **Interacción Vectorial:** Se ha implementado una nueva función `interactWithMouse()`, inexistente en el original. Esta función calcula vectores de proximidad con el cursor del usuario, generando conexiones visuales únicas que siguen el movimiento del ratón, mejorando la experiencia inmersiva.
  4.  **Optimización de Rendimiento:** Ajuste de la opacidad (`alpha`) de las líneas en función de la distancia para suavizar el renderizado en navegadores web.

---

## 🛠️ Tecnologías Utilizadas

- **HTML5:** Estructura semántica (`nav`, `header`, `section`, `footer`).
- **CSS3:** Variables CSS (`:root`), Flexbox, Grid Layout y efectos de cristal (`backdrop-filter`).
- **P5.js:** Librería JavaScript para la creación gráfica generativa.
- **JavaScript (ES6):** Lógica del sketch y manipulación del DOM.

---

## 📁 Estructura de Archivos

El proyecto sigue una estructura ordenada, separando los recursos estáticos del código lógico para facilitar el despliegue:

```text
/
├── assets/
│   └── img/           # Imágenes locales (Cyberpunk aesthetic)
├── css/
│   └── styles.css     # Estilos Glassmorphism, Neon effects y Z-Index layering
├── js/
│   └── sketch.js      # Lógica de P5.js (Partículas, red neuronal e interacción)
├── index.html         # Estructura semántica principal y canvas container
└── README.md          # Documentación del proyecto
```
