$(document).ready(function() {
    var $gamebox = $("div#gamebox");
    $gamebox.on("mouseover", function(e) {
        e.preventDefault();
        var $div = $("div#gamebox");
        $div.animate({
            top: '100%',
            marginTop: Math.random() * 800,
            marginLeft: Math.random() * 800
        });
    });

    $gamebox.on("click", function(e) {
        e.preventDefault();
        var $div = $("div#gamebox");
        $div.stop();
        $div.fadeOut("slow");
    });
});