// 1. INICIALIZAR ICONOS LUCIDE
// Esto busca las etiquetas <i data-lucide="..."> y dibuja el vector
lucide.createIcons();

// 2. INICIALIZAR ANIMACIONES AOS
AOS.init({
    duration: 1000, // Duración de la animación (ms)
    once: true,     // Que solo se anime la primera vez que bajamos
    offset: 100     // Distancia para que se active al bajar
});

// 3. LÓGICA DEL MODO OSCURO / CLARO
const themeToggle = document.getElementById('theme-toggle');
const htmlElement = document.documentElement;

themeToggle.addEventListener('click', () => {
    // Si el HTML tiene la clase 'dark', la quita. Si no, la pone.
    if (htmlElement.classList.contains('dark')) {
        htmlElement.classList.remove('dark');
        htmlElement.classList.add('light');
        localStorage.setItem('theme', 'light'); // Guarda la elección del usuario
    } else {
        htmlElement.classList.remove('light');
        htmlElement.classList.add('dark');
        localStorage.setItem('theme', 'dark');
    }
});

// 4. MANTENER EL TEMA AL RECARGAR
// Esto evita que la web "parpadee" al recargar si el usuario eligió un modo
const savedTheme = localStorage.getItem('theme');
if (savedTheme) {
    htmlElement.classList.remove('dark', 'light');
    htmlElement.classList.add(savedTheme);
}
const menuToggle = document.getElementById('menu-toggle');
const mobileMenu = document.getElementById('mobile-menu');
const menuOverlay = document.getElementById('menu-overlay');
const menuIcon = document.getElementById('menu-icon');

function toggleMenu() {
    // Comprobamos si el menú está abierto mirando la clase de transformación
    const isOpen = mobileMenu.classList.contains('translate-x-0');

    if (!isOpen) {
        // ABRIR
        mobileMenu.classList.remove('translate-x-full');
        mobileMenu.classList.add('translate-x-0');
        menuOverlay.classList.remove('opacity-0', 'pointer-events-none');
        menuOverlay.classList.add('opacity-100', 'pointer-events-auto');
        menuIcon.setAttribute('data-lucide', 'x'); // Cambia a Cruz
    } else {
        // CERRAR
        mobileMenu.classList.remove('translate-x-0');
        mobileMenu.classList.add('translate-x-full');
        menuOverlay.classList.remove('opacity-100', 'pointer-events-auto');
        menuOverlay.classList.add('opacity-0', 'pointer-events-none');
        menuIcon.setAttribute('data-lucide', 'menu'); // Cambia a Hamburguesa
    }
    // ESTA LÍNEA ES VITAL: Vuelve a dibujar el icono para que cambie visualmente
    lucide.createIcons();
}

menuToggle.addEventListener('click', toggleMenu);
menuOverlay.addEventListener('click', toggleMenu); // Cerrar al tocar el oscurecido