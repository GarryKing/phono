$(function () {
    initGlobalParameter();
    loadImages();
});

function initGlobalParameter() {
    clientVisibleWidth = document.body.clientWidth;
    clientVisibleHeight = document.body.clientHeight;
}

function loadImages() {
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                window.sc = "<img src=" + data[i].sourceUrl + "?" + Math.random() + ">";
                var str = "<iframe  src='javascript:parent.sc' style='border:none; overflow: hidden;' scrolling='no' frameborder='0'></iframe>";
                $("#content").append(str);
            }

        },
        error: function () {

        }
    });
}

