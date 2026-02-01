# iWeather Dashboard - Visualizacion Meteorologica

<p align="center">
  <a href="#">
    <img alt="Ver Sitio en Vivo" src="https://img.shields.io/badge/Ver_Sitio_en_Vivo-007AFF?style=for-the-badge&logo=githubpages&logoColor=white">
  </a>
</p>

<p align="center">
  <img alt="HTML5" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img alt="CSS3" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img alt="Leaflet" src="https://img.shields.io/badge/Leaflet-199900?style=for-the-badge&logo=leaflet&logoColor=white">
  <img alt="Chart.js" src="https://img.shields.io/badge/Chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=white">
</p>

Este proyecto es una aplicacion web de visualizacion meteorologica interactiva. Consiste en un panel de control estilo 'Bento Grid' (inspirado en iOS) que integra mapas de geolocalizacion y graficas de prediccion, consumiendo datos en tiempo real de la API de OpenWeatherMap para mostrar el clima actual y el pronostico de 5 dias.

---

## Tabla de Contenidos

- [Caracteristicas Principales](#caracteristicas-principales)
- [Configuracion de API y Visualizacion](#configuracion-de-api-y-visualizacion)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estructura de Archivos](#estructura-de-archivos)

---

## Caracteristicas Principales

- **Interfaz Estilo iOS (Bento Grid):**
  - Diseño modular basado en tarjetas con bordes redondeados y sombras suaves.
  - Implementacion de variables CSS para un sistema de Modo Oscuro/Claro completo.
  - Efectos de interaccion (hover) que elevan las tarjetas y aumentan la profundidad visual.

- **Geolocalizacion y Mapas:**
  - Integracion de Leaflet.js con capas de mapas vectoriales estilo 'Voyager' (CartoDB).
  - Geolocalizacion automatica del navegador para obtener las coordenadas del usuario al inicio.
  - Navegacion interactiva que permite seleccionar cualquier punto del planeta para consultar su clima.

- **Visualizacion de Datos:**
  - Panel principal con temperatura actual, minimas, maximas e iconos descriptivos.
  - Widgets independientes para metricas especificas: Humedad, Presion, Viento (velocidad y angulo) y Precipitacion.
  - Grafica lineal interactiva renderizada con Chart.js para la evolucion de temperatura.

---

## Configuracion de API y Visualizacion

Para garantizar el rendimiento y la estetica, se han implementado las siguientes tecnicas:

- **Consumo de API Asincrono:**
  - Uso de Fetch API con `Promise.all` para realizar llamadas paralelas (Clima Actual y Pronostico) reduciendo el tiempo de carga.
  - Manejo de errores integrado para alertar al usuario en caso de fallos de red o geolocalizacion denegada.

- **Sistema de Iconos Vectoriales:**
  - Implementacion de la libreria RemixIcon para mantener una estetica de linea fina y minimalista consistente en toda la interfaz.
  - Iconos dinamicos que cambian segun el estado de la aplicacion (ej. boton de tema Sol/Luna).

- **Graficos Adaptativos:**
  - Configuracion de Chart.js con degradados lineales en el relleno (`createLinearGradient`) para un acabado visual moderno.
  - Adaptacion automatica de los colores de los ejes y cuadriculas segun el tema activo (Oscuro/Claro).

---

## Tecnologias Utilizadas

- **HTML5:** Estructura semantica y contenedores del dashboard.
- **CSS3:** CSS Grid para el layout Bento, Flexbox para alineaciones y Variables CSS para temas.
- **JavaScript (ES6):** Logica de control, manipulacion del DOM y gestion de APIs.
- **Leaflet.js:** Libreria para mapas interactivos.
- **Chart.js:** Libreria para renderizado de graficos en HTML5 Canvas.
- **OpenWeatherMap API:** Fuente de datos meteorologicos.
- **RemixIcon:** Sistema de iconos open source.

---

## Estructura de Archivos

```text
/
├── styles.css             # Estilos visuales, grid y temas (Dark/Light)
├── main.js                # Logica de API, mapa y graficas
├── index.html             # Estructura principal y enlaces a librerias
└── README.md              # Documentacion del proyecto