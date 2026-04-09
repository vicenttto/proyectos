
(function () {

    const form = document.getElementById('contact-form');
    if (!form) return;

    const submitBtn = document.getElementById('submit-btn');
    const btnIcon = document.getElementById('btn-icon');
    const btnText = document.getElementById('btn-text');
    const feedback = document.getElementById('form-feedback');

    function validateField(input) {
        const errorEl = document.getElementById('error-' + input.id);
        let msg = '';

        if (input.required && !input.value.trim()) {
            msg = 'Este campo es obligatorio';
        } else if (input.type === 'email' && input.value.trim()) {
            const emailRE = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRE.test(input.value.trim())) {
                msg = 'Introduce un email válido';
            }
        }

        if (msg) {
            input.classList.add('input-error');
            if (errorEl) errorEl.textContent = msg;
            return false;
        } else {
            input.classList.remove('input-error');
            if (errorEl) errorEl.textContent = '';
            return true;
        }
    }

    ['nombre', 'email', 'asunto', 'mensaje'].forEach(id => {
        const el = document.getElementById(id);
        if (el) {
            el.addEventListener('blur', () => validateField(el));
            el.addEventListener('input', () => {
                if (el.classList.contains('input-error')) validateField(el);
            });
        }
    });

    function setLoading(loading) {
        submitBtn.disabled = loading;
        if (loading) {
            btnIcon.outerHTML = '<span class="btn-spinner" id="btn-icon"></span>';
            btnText.textContent = 'Enviando…';
        } else {
            const spinnerEl = document.getElementById('btn-icon');
            if (spinnerEl) {
                const newIcon = document.createElement('i');
                newIcon.setAttribute('data-lucide', 'send');
                newIcon.className = 'w-4 h-4';
                newIcon.id = 'btn-icon';
                spinnerEl.replaceWith(newIcon);
                lucide.createIcons();
            }
            btnText.textContent = 'Enviar mensaje';
        }
    }

    function showFeedback(type, msg) {
        feedback.textContent = msg;
        feedback.className = 'form-feedback ' + type;
        feedback.classList.remove('hidden');
        feedback.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const fields = ['nombre', 'email', 'asunto', 'mensaje'];
        const allValid = fields.every(id => validateField(document.getElementById(id)));
        if (!allValid) return;

        setLoading(true);
        feedback.classList.add('hidden');

        const data = {
            nombre: document.getElementById('nombre').value.trim(),
            email: document.getElementById('email').value.trim(),
            asunto: document.getElementById('asunto').value.trim(),
            mensaje: document.getElementById('mensaje').value.trim(),
        };

        try {
            const response = await fetch('send.php', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });

            const result = await response.json();

            if (result.ok) {
                showFeedback('success', '✓ Mensaje enviado correctamente. Nos pondremos en contacto contigo pronto.');
                form.reset();
                fields.forEach(id => document.getElementById(id).classList.remove('input-error'));
            } else {
                showFeedback('error', result.msg || 'Ha ocurrido un error. Por favor, inténtalo de nuevo.');
            }

        } catch (err) {
            showFeedback('error', 'No se ha podido conectar con el servidor. Inténtalo más tarde.');
        } finally {
            setLoading(false);
        }
    });

})();