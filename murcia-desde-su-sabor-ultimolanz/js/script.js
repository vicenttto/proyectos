// 2. INICIALIZAR ANIMACIONES AOS
AOS.init({
    duration: 1000,
    once: true,
    offset: 100
});

// 3. LÓGICA DEL MODO OSCURO / CLARO
const themeToggle = document.getElementById('theme-toggle');
const htmlElement = document.documentElement;

// El tema ya fue aplicado en el <head> antes de pintar.
// Aquí solo gestionamos el botón de toggle.
themeToggle.addEventListener('click', () => {
    if (htmlElement.classList.contains('dark')) {
        htmlElement.classList.replace('dark', 'light');
        localStorage.setItem('theme', 'light');
    } else {
        htmlElement.classList.replace('light', 'dark');
        localStorage.setItem('theme', 'dark');
    }
});

// Si el usuario cambia el tema del SO sin haber elegido manualmente → seguimos al sistema
window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
    if (!localStorage.getItem('theme')) {
        htmlElement.classList.remove('dark', 'light');
        htmlElement.classList.add(e.matches ? 'dark' : 'light');
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
    mobileMenu.classList.remove('translate-x-full');
    menuOverlay.classList.remove('opacity-0', 'pointer-events-none');
    menuOverlay.classList.add('opacity-100');
    iconMenu.classList.add('menu-icon-hidden');
    iconMenu.classList.remove('menu-icon-visible');
    iconClose.classList.remove('menu-icon-hidden');
    iconClose.classList.add('menu-icon-visible');
    document.body.style.overflow = 'hidden';
}

function closeMenu() {
    menuOpen = false;
    mobileMenu.classList.add('translate-x-full');
    menuOverlay.classList.add('opacity-0', 'pointer-events-none');
    menuOverlay.classList.remove('opacity-100');
    iconClose.classList.add('menu-icon-hidden');
    iconClose.classList.remove('menu-icon-visible');
    iconMenu.classList.remove('menu-icon-hidden');
    iconMenu.classList.add('menu-icon-visible');
    document.body.style.overflow = '';
}

menuToggle.addEventListener('click', (e) => {
    e.stopPropagation();
    menuOpen ? closeMenu() : openMenu();
});

menuOverlay.addEventListener('click', closeMenu);

document.querySelectorAll('.mobile-link').forEach(link => {
    link.addEventListener('click', closeMenu);
});

document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && menuOpen) closeMenu();
});

// 1. INICIALIZAR ICONOS LUCIDE
lucide.createIcons();

// 5. PARALLAX SUTIL EN LA IMAGEN DEL PROYECTO
const proyectoWrapper = document.querySelector('.proyecto-wrapper');

if (proyectoWrapper) {
    window.addEventListener('scroll', () => {
        const rect = proyectoWrapper.getBoundingClientRect();
        const windowH = window.innerHeight;

        // Solo aplicar cuando el elemento es visible
        if (rect.bottom > 0 && rect.top < windowH) {
            // Progreso de 0 (recién aparece) a 1 (ya salió)
            const progress = (windowH - rect.top) / (windowH + rect.height);
            // Movimiento suave de ±20px
            const offset = (progress - 0.5) * 40;
            proyectoWrapper.style.transform = `translateY(${offset}px)`;
        }
    }, { passive: true });
}