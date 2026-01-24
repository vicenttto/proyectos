import * as THREE from 'three';
import { GLTFLoader } from 'three/addons/loaders/GLTFLoader.js';
import { OrbitControls } from 'three/addons/controls/OrbitControls.js';

let scene, camera, renderer, mixer, currentAction, model, controls;
const actions = {};
const bgColor = 0x0a0a0a;

init();
animate();

function init() {
    scene = new THREE.Scene();
    scene.background = new THREE.Color(bgColor);

    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 5000);
    camera.position.set(0, 3, 12);

    const canvas = document.querySelector('#three-canvas');
    renderer = new THREE.WebGLRenderer({ canvas, antialias: true });
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.shadowMap.enabled = true;

    scene.add(new THREE.AmbientLight(0xffffff, 1.0));
    const sun = new THREE.DirectionalLight(0xffffff, 1.2);
    sun.position.set(5, 15, 10);
    sun.castShadow = true;
    sun.shadow.mapSize.set(2048, 2048);
    scene.add(sun);

    const floor = new THREE.Mesh(
        new THREE.PlaneGeometry(2000, 2000),
        new THREE.MeshStandardMaterial({ color: bgColor, roughness: 1 })
    );
    floor.rotation.x = -Math.PI / 2;
    floor.receiveShadow = true;
    scene.add(floor);

    controls = new OrbitControls(camera, renderer.domElement);
    controls.target.set(0, 1.5, 0);

    const loader = new GLTFLoader();
    loader.load('./assets/Capoeira.glb', (gltf) => {
        model = gltf.scene;
        model.traverse(n => { if (n.isMesh) n.castShadow = true; });
        scene.add(model);

        mixer = new THREE.AnimationMixer(model);
        actions['capoeira'] = mixer.clipAction(gltf.animations[0]);
        actions['capoeira'].play();
        currentAction = actions['capoeira'];

        loadExtra(loader, './assets/Drunk_Walk.glb', 'walk');
        loadExtra(loader, './assets/Dying.glb', 'die');
    });
}

function loadExtra(loader, path, id) {
    loader.load(path, (gltf) => {
        if (gltf.animations.length > 0) actions[id] = mixer.clipAction(gltf.animations[0]);
    });
}

window.changeAnimation = function (name) {
    if (!actions[name] || actions[name] === currentAction) return;
    if (currentAction) currentAction.fadeOut(0.3);
    actions[name].reset().fadeIn(0.3).play();
    currentAction = actions[name];
}

function animate() {
    requestAnimationFrame(animate);
    if (mixer) mixer.update(0.016);
    if (controls) controls.update();
    renderer.render(scene, camera);
}

window.addEventListener('resize', () => {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
});