// 'DOMContentLoaded' espera a que todo el HTML esté cargado antes de ejecutar el script
document.addEventListener('DOMContentLoaded', () => {

    /* ============================================
       CÓDIGO DEL MENÚ HAMBURGUESA
    ============================================ */

    // 1. Seleccionar los elementos HTML que necesitamos
    const mobileNavToggle = document.querySelector('.mobile-nav-toggle'); // El botón
    const mainNav = document.querySelector('.main-nav'); // El menú

    // Verificamos si el botón existe (evita errores en páginas sin menú)
    if (mobileNavToggle && mainNav) {
        const toggleIcon = mobileNavToggle.querySelector('.material-symbols-outlined'); // El icono

        // 3. Añadir un "escuchador de clics" al botón
        mobileNavToggle.addEventListener('click', () => {

            // 4. La magia: Pone o quita la clase 'nav-open' en el <body>
            document.body.classList.toggle('nav-open');

            // 5. Cambiar el icono (de 'menu' a 'close' y viceversa)
            const isNavOpen = document.body.classList.contains('nav-open');

            if (isNavOpen) {
                toggleIcon.textContent = 'close'; // Cambia el icono a 'cerrar' (X)
                mobileNavToggle.setAttribute('aria-label', 'Cerrar menú');
            } else {
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
        setTimeout(() => {
            logo.classList.add('logo-visible');
        }, 100);
    }

});