#  Digital Twin - Integracion Web + Three.js

<p align="center">
  <a href="https://vicenttto.github.io/proyectos/3d-proyect/index.html">
    <img alt="Ver Sitio en Vivo" src="https://img.shields.io/badge/Ver_Sitio_en_Vivo-007AFF?style=for-the-badge&logo=githubpages&logoColor=white">
  </a>
</p>

<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="Three.js" src="https://img.shields.io/badge/Three.js-000000?style=for-the-badge&logo=three.js&logoColor=white">
  <img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
</p>

Este proyecto es una exploracion de Visualizacion 3D Interactiva en tiempo real. Consiste en un panel de control de un Gemelo Digital donde un modelo tridimensional es renderizado mediante la libreria Three.js, permitiendo al usuario interactuar con diferentes ciclos de animacion mediante una interfaz de usuario avanzada.


---

## Tabla de Contenidos

- [Caracteristicas Principales](#caracteristicas-principales)
- [Configuracion de Escena y Luz](#configuracion-de-escena-y-luz)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estructura de Archivos](#estructura-de-archivos)

---

## Caracteristicas Principales

- **Motor 3D (Three.js):**
  - Renderizado de modelos en formato GLB con soporte para jerarquias de mallas complejas.
  - Sistema de sombras dinamicas utilizando PCFSoftShadowMap para bordes suavizados.
  - Control de camara orbital (OrbitControls) que permite rotacion, zoom y paneo alrededor del objetivo.

- **Interfaz Glassmorphism:**
  - Paneles de control basados en tecnicas de cristal esmerilado con backdrop-filter: blur(12px).
  - Efectos de barrido de luz (shimmer) mediante gradientes lineales animados en el hover de los botones.
  - Sistema de estados activos que resalta la animacion que se esta reproduciendo en el momento.

- **Escenario Infinito:**
  - Integracion de un suelo de dimensiones extensas (2000x2000) sincronizado cromaticamente con el fondo de la escena.
  - Eliminacion visual del horizonte para centrar la atencion en el modelo y sus proyecciones de sombra.

---

## Configuracion de Escena y Luz

Para garantizar la maxima visibilidad del modelo y un acabado profesional, se han implementado los siguientes parametros tecnicos:

- **Iluminacion de Triple Capa:**
  1. **Luz Ambiental:** Intensidad de 1.0 para asegurar que no existan areas de negro absoluto en el modelo.
  2. **Luz de Hemisferio:** Intensidad de 0.6 para simular rebote de luz desde el suelo y el cielo.
  3. **Luz Direccional:** Intensidad de 1.5 con posicionamiento estrategico para definir volumenes y sombras claras.

- **Posicionamiento de Camara:** - Configuracion de campo de vision optimizado con una posicion de retroceso (distancia 12) para permitir una vista completa del personaje desde el inicio.
  - Objetivo de enfoque (Target) fijado en el centro de masa del modelo para rotaciones equilibradas.

---

## Tecnologias Utilizadas

- **HTML5:** Estructura de la aplicacion y contenedores del canvas.
- **CSS3:** Maquetacion flexible, efectos de transparencia y animaciones de interfaz.
- **Three.js:** Motor de renderizado grafico 3D y gestion de archivos GLTF.
- **JavaScript (ES6):** Logica de animacion, carga de assets y gestion de eventos.

---

## Estructura de Archivos

```text
/
├── assets/
│   ├── Capoeira.glb       # Modelo y animacion principal
│   ├── Drunk_Walk.glb     # Animacion de caminata
│   └── Dying.glb          # Animacion de caida
├── style.css              # Estilos Glassmorphism y efectos de luz
├── main.js                # Logica del motor 3D y sistema de luces
├── index.html             # Estructura principal y controles UI
└── README.md              # Documentacion del proyecto
```
