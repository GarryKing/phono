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
    $("#content").ajaxStop(function(){
        $("#content").append("Ω· ¯¡À")
    });
}

function loadImages() {
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var str = createImageDiv(i, data[i]);
                $("#content").append(str);
                setImageCss(i);
            }
        },
        error: function () {

        }
    });

    function createImageDiv(order, data) {
        window.sc = "<img src=" + data.sourceUrl + "?" + Math.random() + ">";
        return "<div id='image_" + order + "' class='image_wrapper'>"
            + "<iframe scrolling='no' src='javascript:parent.sc'></iframe>"
            + "</div>";
    }

    function setImageCss(order){
        var currIframe = document.getElementById("image_"+order).getElementsByTagName("iframe")[0];
        var currDocument = currIframe.contentWindow.document;
        currIframe.onload= "javascript:var x=document.getElementById('image_"+order+"').getElementsByTagName('iframe')[0].contentWindow.document.images[0];this.width=x.width;this.height=x.height;";
        currDocument.body.style.margin="0px";
        currDocument.images[0].width="214";
//        currIframe.height=currDocument.images[0].height;
    }
}

