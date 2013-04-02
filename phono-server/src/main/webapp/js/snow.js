$(function () {
    init();
});

function init() {
    initGlobalParameter();
    initBaseStyle();
    draw();
}

function initGlobalParameter() {
    clientVisibleWidth = document.body.clientWidth;
    clientVisibleHeight = document.body.clientHeight;

    myCanvas = $("#myCanvas").get(0);
    myContext = myCanvas.getContext("2d");
}

function initBaseStyle() {
    myCanvas.width = clientVisibleWidth;
    myCanvas.height = clientVisibleHeight;

    $("#sayWords").css("left", (clientVisibleWidth - $("#sayWords").width()) / 2);
    $("#sayWords").css("top", (clientVisibleHeight - $("#sayWords").height()) / 2);
}

function draw() {
    snowPool = new Array();
    for (var i = 1; i <= 400; i++) {
        var size = 4 + 5 * Math.random()
        snowPool[i - 1] = new Snow(size);
    }
    rotateSnowAngle = 0;
    rotatingAndMoveSnows(snowPool);

    carray = new Array();
    for (var j = 0; j < 20; j++) {
        carray[j] = new Circle(clientVisibleWidth * Math.random(), clientVisibleHeight * Math.random(), 10);
    }
    drawWord();
}

function rotatingAndMoveSnows() {
    var rotateSnowAngle = Math.PI / 180;
    repaint();

    for (var i = 1; i <= snowPool.length; i++) {
//        moveObject(snowPool[i - 1]);
        rotateObject(snowPool[i - 1], rotateSnowAngle);
        moveObject(snowPool[i - 1]);
        drawSnow(snowPool[i - 1]);
    }
    window.setTimeout(rotatingAndMoveSnows, 1000 / 24);
}

function moveObject(snow) {
    var changeValue = snow.speedY;
    if (snow.positionY > clientVisibleHeight) {
        changeValue = -clientVisibleHeight;
    }
    snow.positionY += changeValue;
    for (var i = 0; i < snow.skeleton.length; i++) {
        var skeleton = snow.skeleton[i]
        skeleton.positionY += changeValue;
        for (var j = 0; j < skeleton.pointArray.length; j++) {
            var point = skeleton.pointArray[j];
            point.positionY += changeValue;
        }
    }
}

function repaint() {
    myContext.clearRect(0, 0, clientVisibleWidth, clientVisibleHeight);
}

function rotateObject(obj, angle) {
    var realAngle = angle % (Math.PI * 2);
    for (var i = 0; i < obj.skeleton.length; i++) {
        rotateSkeleton(obj.skeleton[i], realAngle);
    }
}

function rotateSkeleton(skeleton, realAngle) {
    var points = skeleton.pointArray;
    for (var j = 0; j < points.length; j++) {
        var point = points[j];
        var x1 = skeleton.positionX, y1 = skeleton.positionY;
        var x2 = point.positionX, y2 = point.positionY;
        var x3, y3, beta;
        var radius = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
        var asinValue = Math.asin((y2 - y1) / radius);
        var theta = (x2 - x1) < 0 ? (Math.PI - asinValue) : (asinValue);
        if (realAngle <= theta && realAngle > 0) {
            beta = theta - realAngle;
        } else if (realAngle > theta && realAngle < (2 * Math.PI)) {
            beta = Math.PI * 2 - realAngle + theta;
        }
        point.positionX = radius * Math.cos(beta) + x1;
        point.positionY = radius * Math.sin(beta) + y1;
    }
}

function drawSnow(snow) {
    myContext.strokeStyle = "#FFF";
    myContext.fillStyle = "#FFF"
    myContext.shadowBlur = 0;
    myContext.globalAlpha = snow.alpha;
    myContext.beginPath();
    var skeleton = snow.skeleton[0].pointArray;
    for (var i = 0; i < snow.skeleton.length; i++) {
        var pointArray = snow.skeleton[i].pointArray;
        for (var j = 0; j < pointArray.length; j++) {
            if (j == 0) {
                myContext.moveTo(pointArray[j].positionX, pointArray[j].positionY);
            } else {
                myContext.lineTo(pointArray[j].positionX, pointArray[j].positionY);
            }
        }
    }
    myContext.stroke();
}

function Snow(size) {
    var snow = new Object();
    snow.size = size;
    snow.alpha = 0.25;
    snow.positionX = (clientVisibleWidth ) * Math.random();
    snow.positionY = (clientVisibleHeight) * Math.random();
    snow.speedX = 100 * Math.random() / 100 + 0.2;
    snow.speedY = 100 * Math.random() / 100 + 0.2;
    var pentagram = new Pentagram(size, snow.positionX, snow.positionY);
    var pentagram2 = new Pentagram2(size, snow.positionX, snow.positionY);
    rotateSkeleton(pentagram2, Math.PI);
    snow.skeleton = [pentagram, pentagram2];
    return snow;
}

function Polygon(sideLength, sideNum, positionX, positionY) {
    var polygon = new Object();
    polygon.sideLength = sideLength;
    polygon.sideNum = sideNum;
    polygon.positionX = positionX;
    polygon.positionY = positionY;
    polygon.pointArray = new Array();
    return polygon;
}

function Polygon5(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX + outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[2] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[3] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[4] = new Point(positionX - outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[5] = new Point(positionX + 0, positionY + outRadius);
    return polygon;
}


function Pentagram(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[2] = new Point(positionX + outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[3] = new Point(positionX - outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[4] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[5] = new Point(positionX + 0, positionY + outRadius);
    return polygon;
}

function Pentagram2(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    var oriental = new Point(positionX, positionY);
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX, positionY);
    polygon.pointArray[2] = new Point(positionX + outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[3] = new Point(positionX, positionY);
    polygon.pointArray[4] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[5] = new Point(positionX, positionY);
    polygon.pointArray[6] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[7] = new Point(positionX, positionY);
    polygon.pointArray[8] = new Point(positionX - outRadius * Math.cos(innerAngle - Math.PI / 2), positionY + outRadius * Math.sin(innerAngle - Math.PI / 2));
    polygon.pointArray[9] = new Point(positionX, positionY);
    return polygon;
}


function OxHead(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX + outRadius * Math.cos(innerAngle - Math.PI), positionY + outRadius * Math.sin(innerAngle - Math.PI));
    polygon.pointArray[2] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[3] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[4] = new Point(positionX - outRadius * Math.cos(innerAngle - Math.PI), positionY + outRadius * Math.sin(innerAngle - Math.PI));
    return polygon;
}


function Shield(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX + outRadius * Math.cos(Math.PI - innerAngle), positionY + outRadius * Math.sin(Math.PI - innerAngle));
    polygon.pointArray[2] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[3] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[4] = new Point(positionX - outRadius * Math.cos(Math.PI - innerAngle), positionY + outRadius * Math.sin(Math.PI - innerAngle));
    return polygon;
}


function Diamond(sideLength, positionX, positionY) {
    var sideNum = 5;
    var polygon = new Polygon(sideLength, sideNum, positionX, positionY)
    var outRadius = sideLength / (2 * Math.sin(Math.PI / sideNum));
    var innerRadius = sideLength / (2 * Math.tan(Math.PI / sideNum));
    var innerAngle = Math.PI * (sideNum - 2) / sideNum;
    polygon.pointArray[0] = new Point(positionX + 0, positionY + outRadius);
    polygon.pointArray[1] = new Point(positionX + outRadius * Math.cos(Math.PI / 2 - innerAngle), positionY + outRadius * Math.sin(Math.PI / 2 - innerAngle));
    polygon.pointArray[2] = new Point(positionX + sideLength / 2, positionY - innerRadius);
    polygon.pointArray[3] = new Point(positionX - sideLength / 2, positionY - innerRadius);
    polygon.pointArray[4] = new Point(positionX - outRadius * Math.cos(Math.PI / 2 - innerAngle), positionY + outRadius * Math.sin(Math.PI / 2 - innerAngle));
    return polygon;
}

function Point(positionX, positionY) {
    var point = new Object();
    point.positionX = positionX;
    point.positionY = positionY;
    return point;
}

function Line2D(x1, y1, x2, y2) {
    var line = new Object();
    line.positionX = positionX;
    line.positionY = positionY;
    line.pointArray = [new Point(x1, y1), new Point(x2, y2)];
    return line;
}

function Circle(x, y, r) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.destX = -1;
    this.destY = -1;
    this.alpha = 0;
    this.vx = Math.random() - .5 * 5;
    this.vy = Math.random() - .5 * 5
}

function drawWord() {

    var nGeneralWindX = Math.sin(Math.random() * 360) * 3;
    var nGeneralWindY = Math.cos(Math.random() * 360) * 3;
    var bGravity = false;
    var bFade = true;

    for (var i = 0; i < carray.length; i++) {
        var C = carray[i];

        if (C.destX > -1) {
            C.x += (C.destX - C.x) / 4 + ((C.destX - C.x) / 90 * C.vx) + nGeneralWindX;
            C.y += (C.destY - C.y) / 4 + ((C.destY - C.y) / 90 * C.vy) + nGeneralWindY;
            C.alpha += (.1 - C.alpha) / 2;
        }
        else {
            C.x += C.vx + nGeneralWindX;
            C.y += C.vy + nGeneralWindY;
            if (bGravity)
                C.vy += 1.0;
            if (bFade)
                C.alpha *= (.9 + C.alpha) * .98
            if (C.alpha < 0)C.alpha = 0;
        }

        nGeneralWindX *= .9999;
        nGeneralWindY *= .9999;


        if (C.x < 0) {
            C.x = -C.x;
            C.vx = -C.vx;
        }
        if (C.y < 0) {
            C.y = -C.y;
            C.vy = -C.vy;
        }
        ;
        if (C.x > clientVisibleWidth) {
            C.x = clientVisibleWidth - (C.x - clientVisibleWidth);
            C.vx = -C.vx;
        }
        if (C.y > clientVisibleHeight) {
            C.y = clientVisibleHeight - (C.y - clientVisibleHeight);
            C.vy = -C.vy * .45;
        }


        myContext.globalAlpha = C.alpha;
        $("#sayWords").html(parseInt($("#sayWords").html()) + 1);
        myContext.beginPath();
        myContext.fillStyle = "#FFF";
        myContext.arc(C.x, C.y, C.r, 0, Math.PI * 2, true);
        myContext.closePath();
        myContext.fill();
    }
    window.setTimeout(drawWord, 1000);
}