// --- CONFIGURACIÓN ---
const API_KEY = '3efbac6888b9790f38493f3d11e5f1c3'; // <--- ¡PEGA TU API KEY AQUÍ!

let map, marker, chartInstance;
let userLat, userLon;
let isDarkMode = false;

// 1. INICIALIZACIÓN
document.addEventListener('DOMContentLoaded', () => {

    document.getElementById('theme-toggle').addEventListener('click', toggleTheme);
    document.getElementById('recenter-btn').addEventListener('click', resetLocation);

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (pos) => {
                userLat = pos.coords.latitude;
                userLon = pos.coords.longitude;
                loadApp(userLat, userLon);
            },
            (err) => {
                console.warn(err);
                userLat = 40.4168; // Madrid
                userLon = -3.7038;
                alert("Ubicación no disponible. Mostrando Madrid.");
                loadApp(userLat, userLon);
            }
        );
    } else {
        userLat = 40.4168;
        userLon = -3.7038;
        loadApp(userLat, userLon);
    }
});

function loadApp(lat, lon) {
    if (!map) initMap(lat, lon);
    else {
        map.setView([lat, lon], 13);
        updateMarker(lat, lon);
    }
    fetchData(lat, lon);
}

// 2. MODO OSCURO (Con Iconos RemixIcon)
function toggleTheme() {
    isDarkMode = !isDarkMode;
    document.body.classList.toggle('dark-mode');

    // Cambiar icono del botón
    const btnIcon = document.querySelector('#theme-toggle i');
    if (isDarkMode) {
        btnIcon.className = 'ri-sun-line'; // Sol
    } else {
        btnIcon.className = 'ri-moon-line'; // Luna
    }

    if (chartInstance) updateChartColors();
}

// 3. MAPA
function initMap(lat, lon) {
    map = L.map('map', { zoomControl: false }).setView([lat, lon], 13);

    L.tileLayer('https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png', {
        attribution: '&copy; CartoDB'
    }).addTo(map);

    L.control.zoom({ position: 'bottomright' }).addTo(map);
    updateMarker(lat, lon);

    map.on('click', (e) => {
        updateMarker(e.latlng.lat, e.latlng.lng);
        fetchData(e.latlng.lat, e.latlng.lng);
    });
}

function updateMarker(lat, lon) {
    if (marker) map.removeLayer(marker);
    marker = L.marker([lat, lon]).addTo(map);
    map.panTo([lat, lon]);
}

function resetLocation() {
    if (userLat && userLon) {
        updateMarker(userLat, userLon);
        fetchData(userLat, userLon);
    }
}

// 4. DATOS API
async function fetchData(lat, lon) {
    try {
        const [curr, fore] = await Promise.all([
            fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&lang=es&appid=${API_KEY}`),
            fetch(`https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&units=metric&lang=es&appid=${API_KEY}`)
        ]);

        if (!curr.ok || !fore.ok) throw new Error("Error API");

        updateUI(await curr.json());
        updateChart(await fore.json());

    } catch (error) {
        console.error(error);
        alert("Error de conexión con el servicio meteorológico.");
    }
}

// 5. INTERFAZ UI
function updateUI(data) {
    document.getElementById('location-name').textContent = data.name || 'Ubicación';

    // Tiempo Principal
    const mainDiv = document.getElementById('weather-main');
    const iconUrl = `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;

    mainDiv.innerHTML = `
        <div style="text-align:center;">
            <img src="${iconUrl}" width="100" height="100" alt="icono">
            <div class="weather-status">${data.weather[0].description}</div>
        </div>
        <div style="text-align:right;">
            <div class="temp-big">${Math.round(data.main.temp)}°</div>
            <div class="high-low">Mín:${Math.round(data.main.temp_min)}° Máx:${Math.round(data.main.temp_max)}°</div>
        </div>
    `;

    // Widgets
    document.getElementById('val-wind').textContent = `${data.wind.speed} m/s`;
    document.getElementById('val-wind-dir').textContent = `${data.wind.deg}°`;
    document.getElementById('val-humidity').textContent = `${data.main.humidity}%`;
    document.getElementById('val-pressure').textContent = `${data.main.pressure} hPa`;

    const rain = (data.rain && data.rain['1h']) ? data.rain['1h'] : '0';
    document.getElementById('val-rain').textContent = `${rain} mm`;
}

// 6. GRÁFICA
function updateChart(data) {
    const ctx = document.getElementById('forecastChart').getContext('2d');
    const list = data.list;

    const labels = list.map(item => {
        const d = new Date(item.dt * 1000);
        return d.toLocaleDateString('es-ES', { weekday: 'short' }) + ' ' + d.getHours() + 'h';
    });
    const temps = list.map(i => i.main.temp);

    if (chartInstance) chartInstance.destroy();

    const gradient = ctx.createLinearGradient(0, 0, 0, 300);
    gradient.addColorStop(0, 'rgba(0, 122, 255, 0.4)');
    gradient.addColorStop(1, 'rgba(0, 122, 255, 0.0)');

    chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Temp',
                data: temps,
                borderColor: '#007AFF',
                borderWidth: 3,
                backgroundColor: gradient,
                tension: 0.4,
                fill: true,
                pointRadius: 0,
                pointHoverRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: { legend: { display: false } },
            scales: {
                x: {
                    grid: { display: false },
                    ticks: { color: isDarkMode ? '#98989D' : '#8E8E93', maxTicksLimit: 7 }
                },
                y: {
                    grid: { color: isDarkMode ? '#2C2C2E' : '#E5E5EA' },
                    ticks: { color: isDarkMode ? '#98989D' : '#8E8E93' }
                }
            },
            interaction: { intersect: false, mode: 'index' }
        }
    });
}

function updateChartColors() {
    const textColor = isDarkMode ? '#98989D' : '#8E8E93';
    const gridColor = isDarkMode ? '#2C2C2E' : '#E5E5EA';

    if (chartInstance && chartInstance.options.scales.x) {
        chartInstance.options.scales.x.ticks.color = textColor;
        chartInstance.options.scales.y.ticks.color = textColor;
        chartInstance.options.scales.y.grid.color = gridColor;
        chartInstance.update();
    }
}