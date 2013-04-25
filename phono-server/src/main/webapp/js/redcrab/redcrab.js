$(function () {
    initGlobalParameter();
    initBaseContent();
    loadImages();
});

function initGlobalParameter() {
    clientVisibleWidth = document.body.clientWidth;
    clientVisibleHeight = document.body.clientHeight;
    imageDivWidth = 234;
    imageDivMargin = 8;
    imageDivPadding = 10;
    imageDivContentWidth = imageDivWidth - imageDivPadding * 2;
    columnNumber = Math.floor(clientVisibleWidth / (imageDivWidth + imageDivMargin * 2));
}

function initBaseContent() {
    for (var i = 0; i < columnNumber; i++) {
        $("#content_images").append("<ul id='image_ul_" + i + "' class='image_ul'></ul>");
    }
    $("#content_images").css("width", (imageDivWidth + imageDivMargin * 2) * columnNumber)
        .css("margin", "0 auto");
    $(".image_ul").css("width", imageDivWidth)
        .css("margin", "0px " + imageDivMargin).css("padding", 0);
}

function loadImages() {
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                createImageDiv(data[i]);
                setImageCss(data[i]);
            }
        },
        error: function () {

        }
    });

    function createImageDiv(data) {
        window.sc = "<img src=" + data.sourceUrl + "?" + Math.random() + ">";
        var content = "<div class='image_wrapper image_" + data.picId + "' style='display:;'>"
            + "<iframe scrolling='no' src='javascript:parent.sc'></iframe>"
            + "</div>";
        $("#image_cache").append(content);
    }

    function setImageCss(data) {
        var currClass = ".image_" + data.picId;
        var frame = document.getElementsByClassName("image_" + data.picId)[0].getElementsByTagName("iframe")[0];
        var frameContent = $(frame).contents();
        frameContent.find("body").css("margin", "0");
        var img = $(frameContent.find("img"));
        img.css("width", imageDivContentWidth);
        $(frame.contentWindow).load(function () {
            var content = "<li><div class='image_wrapper clearfix image_" + data.picId + "'></div></li>";
            var column = getShortestList();
            $("#image_ul_" + column).append(content);
            $("#image_ul_" + column + " li " + currClass).append(img);
//            $("#content_images " + currClass).append(img);
//            $("#image_cache " + currClass).remove();
            $(currClass).css("height", img.height());
            /* $(currId).display();*/
        });
    }
}

function getShortestList() {
    var shortestId = Math.floor(columnNumber * Math.random());
    var shortestHeight = 0, tempHeight = 0;
    for (var i = 0; i < columnNumber; i++) {
        tempHeight = $("#image_ul_" + i).css("height");
        if (tempHeight <= shortestHeight) {
            shortestHeight = tempHeight;
            shortestId = i;
        }
    }
    return shortestId;
}
