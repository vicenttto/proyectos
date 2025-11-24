# Proyecto: Bento Grid Anidado con CSS Moderno

Este proyecto es una implementación de un "Bento Grid" (similar al de Apple) utilizando técnicas avanzadas de CSS.

El objetivo es demostrar el uso de **CSS Grid anidado**, **variables CSS** para "theming" (modo claro/oscuro) y **CSS anidado** para una mejor organización del código.

## 🚀 Demo en Vivo

Puedes ver el proyecto desplegado y funcionando aquí:

[![Ver Demo](https://img.shields.io/badge/GitHub_Pages-Ver_Demo_Online-2ea44f?style=for-the-badge&logo=github)](https://vicenttto.github.io/proyectos/proyecto-bento/)

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
## 📸 Preview 

<img width="1427" height="825" alt="preview" src="https://github.com/user-attachments/assets/5148782a-aaeb-447b-92b6-1477ba3e3cdb" />


