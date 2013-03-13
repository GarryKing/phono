$(function () {

    bindBasicStyle();

    $("#login-robot").click(function () {
        adaptLoginToFull();
    });

    $("#login-body-out-click").click(function () {
        adaptLoginToCenter();
    });

    $(window).resize(function () {
        bindBasicStyle();
        adaptLoginToCenter();
    });

    //说话
    $("#iSayButton").click(function () {
        var iSay = $("#iSay").val();
        $("#chat-list").html("小黄鸡思考中。。。");
        $.ajax({
            url: "http://localhost:8080/phono-server/login/chatting.crab?iSay=" + iSay,
            dataType: "html",
            success: function (msg) {
                var obj = jQuery.parseJSON(msg);
                $("#chat-list").html("小黄鸡说：" + obj.response.trim());
            },
            error: function () {
                $("#chat-list").html("获取错误，请稍后重试");
            }
        });
    });
})
;

function bindBasicStyle() {
    var winWidth = document.body.clientWidth;
    var winHeight = document.body.clientHeight;
    $("#login-body-out-click").css("width", winWidth)
        .css("height", winHeight);
}

function adaptLoginToFull() {
    var winHeight = document.body.clientHeight;
    var bodyTopMargin = -winHeight / 2;
    var chatHeight = (winHeight - $("#login-robot").height() - 20 );
    var chatListHeight = chatHeight - 46;
    changeLoginCss(bodyTopMargin, winHeight, chatHeight, chatListHeight);
    bindBasicStyle();
}

function adaptLoginToCenter() {
    changeLoginCss(-196, 392, 121, 75);
    bindBasicStyle();
}

function changeLoginCss(bodyTopMargin, skinHeight, chatHeight, chatListHeight) {
    $("#login-body").css("margin-top", bodyTopMargin);
    $("#login-skin").css("height", skinHeight);
    $("#login-chat").css("height", chatHeight);
    $("#chat-list").css("height", chatListHeight);
}