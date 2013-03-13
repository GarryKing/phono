var phi = 270, theta = 270;
var target = new THREE.Vector3();
function rotateCameraX() {
    var currX = camera.position.x;
    var currY = camera.position.y;
    var currZ = camera.position.z;
    var radian = THREE.Math.degToRad(theta);
    target.x = currX + Math.cos(radian);
    target.y = currY;
    target.z = currZ + Math.sin(radian);
    camera.lookAt(target);
    renderer.render(scene, camera);
}
function rotateCameraY() {
    var currX = camera.position.x;
    var currY = camera.position.y;
    var currZ = camera.position.z;
    var radian = THREE.Math.degToRad(phi);
    target.x = currX;
    target.y = currY + Math.cos(radian);
    target.z = currZ + Math.sin(radian);
    camera.lookAt(target);
    renderer.render(scene, camera);
}
