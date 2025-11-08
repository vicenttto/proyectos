// 'DOMContentLoaded' espera a que todo el HTML esté cargado
document.addEventListener('DOMContentLoaded', () => {

    // =================================================================
    // 1. CONFIGURACIÓN DE FIREBASE
    // =================================================================
    const firebaseConfig = {
        apiKey: "AIzaSyAbnlCiu8_x1uBaekb4R4aiwof-bc688VU",
        authDomain: "maquetacion-basica.firebaseapp.com",
        projectId: "maquetacion-basica",
        storageBucket: "maquetacion-basica.firebasestorage.app",
        messagingSenderId: "992695792767",
        appId: "1:992695792767:web:6a51c5a928b3c402963b4a"
    };
    // =================================================================

    // Inicializar Firebase (solo si no se ha hecho antes)
    if (!firebase.apps.length) {
        try {
            firebase.initializeApp(firebaseConfig);
        } catch (e) {
            console.error("Error al inicializar Firebase: ", e);
        }
    }

    // Obtener referencias a los servicios de Autenticación y Base de Datos
    const auth = firebase.auth();
    const db = firebase.firestore();


    // --- LÓGICA DE REGISTRO (Solo se ejecuta si está el formulario de registro) ---
    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', (event) => {
            event.preventDefault(); // Prevenir envío automático

            // 1. Obtener los datos del formulario
            const name = document.getElementById('nombre').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const password2 = document.getElementById('password_confirm').value;
            const lopd = document.getElementById('lopd').checked;
            const direccion = document.getElementById('direccion').value;
            const nacimiento = document.getElementById('nacimiento').value;

            // 2. Limpiar errores previos
            document.querySelectorAll('.form-error-message').forEach(el => el.textContent = '');
            document.querySelectorAll('input.input-error').forEach(el => el.classList.remove('input-error'));

            // 3. Validaciones del Frontend
            let isValid = true;
            if (password !== password2) {
                document.getElementById('password2-error').textContent = 'Las contraseñas no coinciden.';
                document.getElementById('password_confirm').classList.add('input-error');
                isValid = false;
            }
            if (password.length < 6) {
                document.getElementById('password-error').textContent = 'La contraseña debe tener al menos 6 caracteres.';
                document.getElementById('password').classList.add('input-error');
                isValid = false;
            }
            if (!lopd) {
                document.getElementById('lopd-error').textContent = 'Debes aceptar la política de privacidad.';
                isValid = false;
            }
            if (!isValid) return; // Si algo falla, no continuar

            // 4. Crear el usuario en Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .then((userCredential) => {
                    // ¡Usuario creado en el sistema de Auth!
                    const user = userCredential.user;

                    // 5. REQUISITO: Guardar los datos del registro en la Base de Datos (Firestore)
                    return db.collection("users").doc(user.uid).set({
                        name: name,
                        email: email,
                        direccion: direccion,
                        ano_nacimiento: nacimiento,
                        createdAt: new Date() // Guardamos la fecha de creación
                    });
                })
                .then(() => {
                    // ¡Datos guardados en Firestore con éxito!
                    registerForm.innerHTML = `<h3 style="text-align: center; color: var(--color-accent);">¡Registro completado!</h3><p style="text-align: center;">Ahora puedes iniciar sesión.</p>`;
                    setTimeout(() => {
                        window.location.href = 'login.html';
                    }, 2000);
                })
                .catch((error) => {
                    // Manejar errores (ej. email ya en uso)
                    console.error("Error en el registro:", error);
                    if (error.code == 'auth/email-already-in-use') {
                        document.getElementById('email-error').textContent = 'Este correo ya está registrado.';
                        document.getElementById('email').classList.add('input-error');
                    } else {
                        document.getElementById('email-error').textContent = 'Error en el registro: ' + error.message;
                    }
                });
        });
    }


    // --- LÓGICA DE LOGIN (Solo se ejecuta si está el formulario de login) ---
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const emailError = document.getElementById('email-error');
            const passwordError = document.getElementById('password-error');

            // Limpiar errores
            emailError.textContent = '';
            passwordError.textContent = '';
            document.getElementById('email').classList.remove('input-error');
            document.getElementById('password').classList.remove('input-error');

            // 4. Iniciar sesión con Firebase Authentication
            auth.signInWithEmailAndPassword(email, password)
                .then((userCredential) => {
                    // ¡Login correcto!
                    const user = userCredential.user;

                    // 5. REQUISITO: Guardar la fecha y hora de entrada al sistema
                    return db.collection("login_history").add({
                        userId: user.uid,
                        email: user.email,
                        loginTime: new Date() // La fecha y hora actual
                    });
                })
                .then(() => {
                    // ¡Registro de login guardado!
                    loginForm.innerHTML = `<h3 style="text-align: center; color: var(--color-accent);">¡Bienvenido de nuevo!</h3><p style="text-align: center;">Redirigiendo a la página principal...</p>`;
                    setTimeout(() => {
                        window.location.href = '../index.html';
                    }, 2000);
                })
                .catch((error) => {
                    // Manejar errores (ej. usuario no encontrado, contraseña incorrecta)
                    console.error("Error en el login:", error);
                    document.getElementById('email-error').textContent = 'Correo o contraseña incorrectos.';
                    document.getElementById('email').classList.add('input-error');
                    document.getElementById('password').classList.add('input-error');
                });
        });
    }


    // --- LÓGICA DE MOSTRAR/OCULTAR CONTRASEÑA 
    const toggleIcons = document.querySelectorAll('.password-toggle-icon');
    toggleIcons.forEach(icon => {
        icon.addEventListener('click', () => {
            const inputWrapper = icon.parentElement;
            const input = inputWrapper.querySelector('input');
            if (input) {
                if (input.type === 'password') {
                    input.type = 'text';
                    icon.textContent = 'visibility_off';
                } else {
                    input.type = 'password';
                    icon.textContent = 'visibility';
                }
            }
        });
    });

});