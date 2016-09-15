function getData() {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:8080/rest/oving4/",
        success: function (data) {
            $("div#content").html(data);
        }
    });
}

$(document).ready(function () {
    setInterval(getData, 1000);
    $("#messageButton").click(function () {
        sendData();
    });
});

function sendData() {
    var $data = $("input#messageInput").val();
    console.log($data);

    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:8080/rest/oving4/",
        data: $data,
        contentType: "text/plain"
    });
}