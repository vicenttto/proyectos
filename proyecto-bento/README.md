# Proyecto: Bento Grid Anidado con CSS Moderno

Este proyecto es una implementación de un "Bento Grid" (similar al de Apple) utilizando técnicas avanzadas de CSS.

El objetivo es demostrar el uso de **CSS Grid anidado**, **variables CSS** para "theming" (modo claro/oscuro) y **CSS anidado** para una mejor organización del código.

## ✨ Características

- **Bento Grid Layout:** Un diseño de dashboard visualmente atractivo.
- **Grid Anidado (Nivel 2):** El layout principal (Nivel 1) contiene 3 áreas, y cada área es su propio CSS Grid (Nivel 2), permitiendo layouts complejos y asimétricos.
- **100% Responsive:**
  - **Desktop (> 700px):** Layout de 3 columnas.
  - **Móvil (< 700px):** Layout de 1 columna (stack).
- **Modo Claro / Oscuro:** Utiliza `prefers-color-scheme` para adaptarse automáticamente a la configuración del sistema operativo.
- **CSS Moderno:**
  - Variables CSS (`:root`) centralizadas en un archivo parcial.
  - CSS Anidado (`&` y `@media` dentro de selectores) para un código más limpio.

## 📁 Estructura del Proyecto

```
/
├── index.html          # Estructura semántica del grid
├── css/
│   ├── _variables.css  # Almacena los "Design Tokens" (colores, fuentes, etc.)
│   └── main.css        # Estilos principales, layout y componentes. Importa _variables.css
└── images/             # Assets visuales
```

## 🚀 Cómo Empezar

1.  Clona este repositorio.
2.  Abre `index.html` en tu navegador.
3.  ¡No se requiere compilación! Todo es HTML y CSS nativo.
