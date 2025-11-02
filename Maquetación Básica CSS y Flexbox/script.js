// Espera a que todo el contenido HTML esté cargado antes de ejecutar el script
document.addEventListener('DOMContentLoaded', () => {

    // 1. Seleccionar los elementos del DOM
    const mobileNavToggle = document.querySelector('.mobile-nav-toggle');
    const mainNav = document.querySelector('.main-nav');
    const toggleIcon = mobileNavToggle.querySelector('.material-symbols-outlined');

    // 2. Verificar que los elementos existen (buena práctica)
    if (mobileNavToggle && mainNav && toggleIcon) {

        // 3. Añadir el "escuchador de eventos" (Event Listener) al botón
        mobileNavToggle.addEventListener('click', () => {
            
            // 4. Alternar la clase 'nav-open' en el <body>
            // Esta clase será la que active/desactive el menú en CSS
            document.body.classList.toggle('nav-open');

            // 5. Bonus: Cambiar el icono y la accesibilidad (ARIA)
            const isNavOpen = document.body.classList.contains('nav-open');
            
            if (isNavOpen) {
                // Si el menú está abierto
                toggleIcon.textContent = 'close'; // Cambia el icono a 'cerrar'
                mobileNavToggle.setAttribute('aria-label', 'Cerrar menú');
                mobileNavToggle.setAttribute('aria-expanded', 'true');
            } else {
                // Si el menú está cerrado
                toggleIcon.textContent = 'menu'; // Cambia el icono a 'menú'
                mobileNavToggle.setAttribute('aria-label', 'Abrir menú');
                mobileNavToggle.setAttribute('aria-expanded', 'false');
            }
        });
    }

});