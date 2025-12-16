// Variable para almacenar nuestras partículas
let particles = [];
// Cantidad de partículas (ajustable según potencia del PC)
const particleCount = 100;

function setup() {
    // 1. Creamos el Canvas
    let canvas = createCanvas(windowWidth, windowHeight);

    // 2. Metemos el canvas dentro del div que preparamos en HTML
    // Esto es lo que "integra de forma natural" la web y el P5
    canvas.parent('canvas-container');

    // 3. Inicializamos las partículas
    for (let i = 0; i < particleCount; i++) {
        particles.push(new Particle());
    }
}

function draw() {
    // Pintamos el fondo de casi negro (valor 5) en cada fotograma.
    background(5);

    // Recorremos todas las partículas
    for (let i = 0; i < particles.length; i++) {
        let p = particles[i];
        // Calculamos su nueva posición basándonos en su velocidad
        p.update();
        //  RENDERIZADO: Dibujamos el círculo en la nueva posición
        p.draw();
        // Comprobamos si está cerca de otras partículas para dibujar líneas.
        p.joinParticles(particles.slice(i)); // Comprobar conexiones con las demás
        p.interactWithMouse(); // INTERACCIÓN CON EL RATÓN
    }
}

// Si el usuario cambia el tamaño de la ventana, ajustamos el canvas
function windowResized() {
    resizeCanvas(windowWidth, windowHeight);
}

// --- CLASE PARTÍCULA (El objeto que se mueve) ---
class Particle {
    constructor() {
        this.x = random(width);
        this.y = random(height);

        // Velocidad aleatoria (movimiento lento y fluido)
        this.vx = random(-0.5, 0.5);
        this.vy = random(-0.5, 0.5);

        // Tamaño aleatorio
        this.size = random(2, 4);

        // Color aleatorio entre Cian y Magenta 
        // random() > 0.5 
        this.color = random() > 0.5 ? color(0, 243, 255) : color(255, 0, 255);
    }

    // Actualizar posición
    update() {
        this.x += this.vx;
        this.y += this.vy;

        // Rebote en los bordes (si toca pared, invierte velocidad)
        if (this.x < 0 || this.x > width) this.vx *= -1;
        if (this.y < 0 || this.y > height) this.vy *= -1;
    }

    // Dibujar el punto
    draw() {
        noStroke();
        fill(this.color);
        circle(this.x, this.y, this.size);
    }

    // Dibujar línea si está cerca de otras partículas
    joinParticles(particles) {
        particles.forEach(element => {
            // Calculamos la distancia entre esta partícula y las demás
            let dis = dist(this.x, this.y, element.x, element.y);

            // Si están a menos de 120px, dibujamos una línea
            if (dis < 120) {
                // La opacidad (alpha) depende de la distancia:
                // Más cerca = más visible. Más lejos = más transparente.
                // map(valor, entradaMin, entradaMax, salidaMin, salidaMax)
                let alpha = map(dis, 0, 120, 255, 0); // De opaco a transparente

                stroke(255, alpha * 0.5); // Línea blanca semitransparente
                strokeWeight(1);
                line(this.x, this.y, element.x, element.y);
            }
        });
    }

    // INTERACCIÓN: Conectar con el ratón
    interactWithMouse() {
        let mouseDis = dist(this.x, this.y, mouseX, mouseY);

        // Si el ratón está cerca (menos de 150px)
        if (mouseDis < 150) {
            stroke(this.color); // La línea toma el color de la partícula
            strokeWeight(1.5);  // Un poco más gruesa
            line(this.x, this.y, mouseX, mouseY);
        }
    }
}