$(function(){
    $.ajax({
        url: "redcrab/json/indexImage.crab",
        dataType: "json",
        success:function(data){
            alert(data[0]alert();.sourceUrl);
        },
        error: function(){

        }
    });
})