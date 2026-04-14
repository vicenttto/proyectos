AOS.init({
    duration: 1000,
    once: true,
    offset: 100
});

const themeToggle = document.getElementById('theme-toggle');
const htmlElement = document.documentElement;


themeToggle.addEventListener('click', () => {
    if (htmlElement.classList.contains('dark')) {
        htmlElement.classList.replace('dark', 'light');
        localStorage.setItem('theme', 'light');
    } else {
        htmlElement.classList.replace('light', 'dark');
        localStorage.setItem('theme', 'dark');
    }
});

window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
    if (!localStorage.getItem('theme')) {
        htmlElement.classList.remove('dark', 'light');
        htmlElement.classList.add(e.matches ? 'dark' : 'light');
    }
});


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

lucide.createIcons();

const proyectoWrapper = document.querySelector('.proyecto-wrapper');

if (proyectoWrapper) {
    window.addEventListener('scroll', () => {
        const rect = proyectoWrapper.getBoundingClientRect();
        const windowH = window.innerHeight;

        if (rect.bottom > 0 && rect.top < windowH) {
            const progress = (windowH - rect.top) / (windowH + rect.height);
            const offset = (progress - 0.5) * 40;
            proyectoWrapper.style.transform = `translateY(${offset}px)`;
        }
    }, { passive: true });
}

const ilustGrandes = document.querySelectorAll('.ilust-grande');
const ilustPequenas = document.querySelectorAll('.ilust-pequena');

if (ilustGrandes.length && ilustPequenas.length) {
    window.addEventListener('scroll', () => {
        const scrollY = window.scrollY;
        const docHeight = document.documentElement.scrollHeight - window.innerHeight;
        const progress = scrollY / docHeight;

        if (progress >= 0.7) {
            ilustGrandes.forEach(el => el.classList.add('ilust-swapped'));
            ilustPequenas.forEach(el => el.classList.add('ilust-swapped'));
        } else {
            ilustGrandes.forEach(el => el.classList.remove('ilust-swapped'));
            ilustPequenas.forEach(el => el.classList.remove('ilust-swapped'));
        }
    }, { passive: true });
}