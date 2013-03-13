main();

function main() {
    initThreeJS();
    drawSphere();
    renderer.render(scene, camera);
}

var scene, camera, renderer, pointLight;

function initThreeJS() {
    var WIDTH = 400, HEIGHT = 300;
    initScene();
    initCamera();
    initRenderer();
    initLight();
    scene.add(camera);
    scene.add(pointLight);
    renderer.setSize(WIDTH, HEIGHT);
    var $container = $('#container');
    $container.append(renderer.domElement);

    function initScene() {
        scene = new THREE.Scene();
    }

    function initCamera() {
        // set some camera attributes
        var VIEW_ANGLE = 45, ASPECT = WIDTH / HEIGHT, NEAR = 0.1, FAR = 10000;
        camera = new THREE.PerspectiveCamera(VIEW_ANGLE, ASPECT, NEAR, FAR);
        camera.position.z = 300;
    }

    function initRenderer() {
        renderer = new THREE.WebGLRenderer();
    }

    function initLight() {
        pointLight = new THREE.PointLight(0xFFFFFF);
        pointLight.position.x = 10;
        pointLight.position.y = 50;
        pointLight.position.z = 130;
    }
}

function drawSphere() {
    var radius = 50, segments = 16, rings = 16;
    var sphereMaterial = new THREE.MeshLambertMaterial({
        color: 0xFF0000
    });
    var sphere = new THREE.Mesh(
        new THREE.SphereGeometry(radius, segments, rings),
        sphereMaterial
    );
    // set the geometry to dynamic
    // so that it allow updates
    sphere.geometry.dynamic = true;

    // changes to the vertices
    sphere.geometry.__dirtyVertices = true;

    // changes to the normals
    sphere.geometry.__dirtyNormals = true;

    scene.add(sphere);
}