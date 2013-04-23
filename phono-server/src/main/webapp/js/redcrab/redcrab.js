$(function () {
    initGlobalParameter();
    initContent();
    loadImages();
});

function initGlobalParameter() {
    clientVisibleWidth = document.body.clientWidth;
    clientVisibleHeight = document.body.clientHeight;
    imageDivWidth = 234;
    imageDivMargin = 8;
    columnNumber = Math.floor(clientVisibleWidth % (imageDivWidth + imageDivMargin * 2));
}

function initContent() {
    clientVisibleWidth
}

function loadImages() {
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var str = createImageDiv(i, data[i]);
                $("#content").append(str);
            }

        },
        error: function () {

        }
    });

    function createImageDiv(order, data) {
        window.sc = "<img src=" + data.sourceUrl + "?" + Math.random() + ">";
        return "<div id='image_'" + order + " class='image_wrapper'>"
            + "<iframe  src='javascript:parent.sc'></iframe>"
            + "</div>";
    }
}

