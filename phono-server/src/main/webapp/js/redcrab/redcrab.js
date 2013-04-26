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
    var content = $("#content_images");
    for (var i = 0; i < columnNumber; i++) {
        content.append("<ul id='image_ul_" + i + "' class='image_ul'></ul>");
    }
    content.css("width", (imageDivWidth + imageDivMargin * 2) * columnNumber)
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
        var content = "<div class='image_wrapper image_" + data.picId + "' style='display:none;'>"
            + "<iframe scrolling='no' src='javascript:parent.sc'></iframe>"
            + "</div>";
        var content_2 = "<div><div class='image_wrapper image_" + data.picId + "' style='display:;'></div></div>";
        $("#image_cache").append(content);
        var frame = document.getElementById("image_cache_iframe");
        var frameBody = $(frame).contents().find("body");
        frameBody.append(content_2);
        frameBody.find(".image_" + data.picId).append(window.sc);
    }

    function setImageCss(data) {
        var currClass = ".image_" + data.picId;
//        var frame = document.getElementsByClassName("image_" + data.picId)[0].getElementsByTagName("iframe")[0];
        var frame = document.getElementById("image_cache_iframe");
        var frameContent = $(frame).contents();
//        frameContent.find("body").css("margin", "0");
        var img = frameContent.find(currClass);
        img.css("width", imageDivContentWidth);
        $(img).load(function () {
//            var content = "<li><div class='image_wrapper image_" + data.picId + "'></div></li>";
            var content = img.parent().parent().html();
            var column = getShortestList();
            $("#image_ul_" + column).append(content);
            var newImageDiv = $("#image_ul_" + column + " li " + currClass);
            $(currClass).css("height", img.height());
            newImageDiv.append(img);
//            $("#image_cache " + currClass).remove();
//            $("#image_cache_iframe ").children().remove();
//            $("#holder").append(img.height() + "¡¢");
        });
    }
}

function getShortestList() {
    var shortestId = Math.floor(columnNumber * Math.random());
    var shortestHeight = $("#image_ul_" + shortestId).height(), tempHeight = 0;
    for (var i = 0; i < columnNumber; i++) {
        tempHeight = $("#image_ul_" + i).height();
        if (tempHeight <= shortestHeight) {
            shortestHeight = tempHeight;
            shortestId = i;
        }
    }
    return shortestId;
}
