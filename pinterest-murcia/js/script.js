// Esperamos a que todo el HTML se cargue antes de ejecutar el script
document.addEventListener('DOMContentLoaded', () => {

    // 1. Seleccionamos todos los botones de "Me gusta"
    const likeButtons = document.querySelectorAll('.btn-like');

    // 2. Seleccionamos el contenedor de la galería (para poder reordenar los hijos)
    const galleryContainer = document.getElementById('gallery');

    // 3. Añadimos un "escucha" (listener) a cada botón
    likeButtons.forEach(button => {
        button.addEventListener('click', function (event) {

            // Evitamos que el clic se propague si hubiera otros enlaces
            event.preventDefault();

            // --- LÓGICA VISUAL (Cambio de icono y color) ---

            // 'this' se refiere al botón que fue pulsado
            // toggle añade la clase si no existe, o la quita si ya existe
            this.classList.toggle('liked');

            // Buscamos el icono <i> dentro del botón
            const icon = this.querySelector('i');

            // Si el botón tiene la clase 'liked', cambiamos a corazón relleno
            if (this.classList.contains('liked')) {
                icon.classList.remove('fa-regular'); // Quita borde
                icon.classList.add('fa-solid');      // Pone relleno

                // --- LÓGICA DE REORDENACIÓN (Mover al principio) ---
                // Buscamos la tarjeta padre más cercana (closest busca hacia arriba en el HTML)
                const card = this.closest('.card');

                // Mover al principio con animación suave
                // prepend() MUEVE el elemento del DOM, no lo clona.
                galleryContainer.prepend(card);

                // Opcional: Hacer scroll suave hacia la tarjeta movida para que el usuario no se pierda
                card.scrollIntoView({ behavior: 'smooth', block: 'center' });

            } else {
                // Si quitamos el like, volvemos al corazón hueco
                icon.classList.remove('fa-solid');
                icon.classList.add('fa-regular');

                // NOTA: El ejercicio no especifica qué hacer al quitar el like.
                // Por defecto, la tarjeta se quedará donde está (arriba).
            }
        });
    });
});