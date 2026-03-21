// 1. INICIALIZAR ICONOS LUCIDE
lucide.createIcons();

// 2. INICIALIZAR ANIMACIONES AOS
AOS.init({
    duration: 1000,
    once: true,
    offset: 100
});

// 3. LÓGICA DEL MODO OSCURO / CLARO
const themeToggle = document.getElementById('theme-toggle');
const htmlElement = document.documentElement;

// Aplicar tema guardado antes de cualquier render
const savedTheme = localStorage.getItem('theme');
if (savedTheme) {
    htmlElement.classList.remove('dark', 'light');
    htmlElement.classList.add(savedTheme);
}

themeToggle.addEventListener('click', () => {
    if (htmlElement.classList.contains('dark')) {
        htmlElement.classList.replace('dark', 'light');
        localStorage.setItem('theme', 'light');
    } else {
        htmlElement.classList.replace('light', 'dark');
        localStorage.setItem('theme', 'dark');
    }
});


// 4. MENÚ HAMBURGUESA MÓVIL
const menuToggle = document.getElementById('menu-toggle');
const mobileMenu = document.getElementById('mobile-menu');
const menuOverlay = document.getElementById('menu-overlay');
const iconMenu = document.getElementById('icon-menu');
const iconClose = document.getElementById('icon-close');

let menuOpen = false;

function openMenu() {
    menuOpen = true;
    // Deslizar menú
    mobileMenu.classList.remove('translate-x-full');
    // Mostrar overlay
    menuOverlay.classList.remove('opacity-0', 'pointer-events-none');
    menuOverlay.classList.add('opacity-100');
    // Animar icono: ocultar hamburguesa, mostrar X
    iconMenu.classList.add('menu-icon-hidden');
    iconMenu.classList.remove('menu-icon-visible');
    iconClose.classList.remove('menu-icon-hidden');
    iconClose.classList.add('menu-icon-visible');
    // Evitar scroll del body
    document.body.style.overflow = 'hidden';
}

function closeMenu() {
    menuOpen = false;
    // Ocultar menú
    mobileMenu.classList.add('translate-x-full');
    // Ocultar overlay
    menuOverlay.classList.add('opacity-0', 'pointer-events-none');
    menuOverlay.classList.remove('opacity-100');
    // Animar icono: mostrar hamburguesa, ocultar X
    iconClose.classList.add('menu-icon-hidden');
    iconClose.classList.remove('menu-icon-visible');
    iconMenu.classList.remove('menu-icon-hidden');
    iconMenu.classList.add('menu-icon-visible');
    // Restaurar scroll
    document.body.style.overflow = '';
}

menuToggle.addEventListener('click', (e) => {
    e.stopPropagation();
    menuOpen ? closeMenu() : openMenu();
});

// Cerrar al pulsar el overlay (fuera del menú)
menuOverlay.addEventListener('click', closeMenu);

// Cerrar al pulsar en un enlace del menú
document.querySelectorAll('.mobile-link').forEach(link => {
    link.addEventListener('click', closeMenu);
});

// Cerrar con tecla Escape
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && menuOpen) closeMenu();
});