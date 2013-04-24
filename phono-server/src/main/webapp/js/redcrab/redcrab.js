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
    imageDivPadding = 10;
    imageDivContentWidth = imageDivWidth - imageDivPadding * 2;
    columnNumber = Math.floor(clientVisibleWidth % (imageDivWidth + imageDivMargin * 2));
}

function initContent() {

}

function loadImages() {
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                createImageDiv(i, data[i]);
                setImageCss(i);
            }
        },
        error: function () {

        }
    });

    function createImageDiv(order, data) {
        window.sc = "<img src=" + data.sourceUrl + "?" + Math.random() + ">";
        var content = "<div id='image_" + order + "' class='image_wrapper'>"
            + "<iframe scrolling='no' src='javascript:parent.sc'></iframe>"
            + "</div>";
        $("#content").append(content);
    }

    function setImageCss(order) {
        var frame = document.getElementById("image_" + order).getElementsByTagName("iframe")[0];
        var frameContent = $(frame).contents();
        frameContent.find("body").css("margin", "0");
        var img = $(frameContent.find("img"));
        img.css("width", imageDivContentWidth);
        $(frame.contentWindow).load(function () {
            $(frame).css("height", img.height());
            $("#image_" + order).css("height", img.height());
        });
    }
}

