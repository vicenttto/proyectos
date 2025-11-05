// 'DOMContentLoaded' espera a que todo el HTML esté cargado antes de ejecutar el script
document.addEventListener('DOMContentLoaded', () => {

    /* ============================================
       CÓDIGO DEL MENÚ HAMBURGUESA
    ============================================ */

    // 1. Seleccionar los elementos HTML que necesitamos
    const mobileNavToggle = document.querySelector('.mobile-nav-toggle'); // El botón
    const mainNav = document.querySelector('.main-nav'); // El menú
    const toggleIcon = mobileNavToggle.querySelector('.material-symbols-outlined'); // El icono

    // 2. Verificar que los elementos existen (buena práctica)
    if (mobileNavToggle && mainNav && toggleIcon) {

        // 3. Añadir un "escuchador de clics" al botón
        mobileNavToggle.addEventListener('click', () => {

            // 4. La magia: Pone o quita la clase 'nav-open' en el <body>
            // CSS reacciona a esta clase para mostrar/ocultar el menú
            document.body.classList.toggle('nav-open');

            // 5. Cambiar el icono (de 'menu' a 'close' y viceversa)
            const isNavOpen = document.body.classList.contains('nav-open');

            if (isNavOpen) {
                // Si el menú está abierto
                toggleIcon.textContent = 'close'; // Cambia el icono a 'cerrar' (X)
                mobileNavToggle.setAttribute('aria-label', 'Cerrar menú');
            } else {
                // Si el menú está cerrado
                toggleIcon.textContent = 'menu'; // Cambia el icono a 'menú' (☰)
                mobileNavToggle.setAttribute('aria-label', 'Abrir menú');
            }
        });
    }

    /* ============================================
       CÓDIGO PARA ANIMAR EL LOGO AL CARGAR
    ============================================ */

    // 1. Seleccionamos el logo (el <h1> en el header)
    const logo = document.querySelector('.site-header h1');

    // 2. Verificamos que existe
    if (logo) {
        // 3. Añadimos la clase 'logo-visible' para activar la animación CSS.
        // Usamos un pequeño retardo (100ms) para asegurar que el navegador 
        // vea el cambio y ejecute la animación.
        setTimeout(() => {
            logo.classList.add('logo-visible');
        }, 100);
    }

    // 1. Seleccionamos TODOS los iconos de "ojo"
    const toggleIcons = document.querySelectorAll('.password-toggle-icon');

    // 2. Recorremos cada icono encontrado
    toggleIcons.forEach(icon => {

        // 3. Añadimos un "escuchador de clics" a cada uno
        icon.addEventListener('click', () => {

            // 4. Buscamos el 'input' que está "hermano" (en el mismo div)
            const inputWrapper = icon.parentElement;
            const input = inputWrapper.querySelector('input');

            // 5. Verificamos que encontramos el input
            if (input) {

                // 6. Comprobamos el tipo de input y lo cambiamos
                if (input.type === 'password') {
                    input.type = 'text';
                    icon.textContent = 'visibility_off'; // Cambia el icono a "ojo tachado"
                } else {
                    input.type = 'password';
                    icon.textContent = 'visibility'; // Cambia el icono a "ojo"
                }
            }
        });
    });



});