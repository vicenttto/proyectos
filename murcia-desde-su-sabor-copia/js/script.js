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
const iconMenu = document.getElementById('icon-menu');
const iconClose = document.getElementById('icon-close');

menuToggle.addEventListener('click', () => {
    // 1. Deslizar el menú
    mobileMenu.classList.toggle('translate-x-full');

    // 2. Intercambiar visibilidad de los iconos
    const isOpen = !mobileMenu.classList.contains('translate-x-full');

    if (isOpen) {
        iconMenu.classList.replace('block', 'hidden');
        iconClose.classList.replace('hidden', 'block');
    } else {
        iconMenu.classList.replace('hidden', 'block');
        iconClose.classList.replace('block', 'hidden');
    }
});

// Cerrar menú al pulsar en un enlace (Inicio)
document.querySelectorAll('.mobile-link').forEach(link => {
    link.addEventListener('click', () => {
        mobileMenu.classList.add('translate-x-full');
        iconMenu.classList.replace('hidden', 'block');
        iconClose.classList.replace('block', 'hidden');
    });
});