<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bondesjakk</title>
    <style type="text/css">
        #bondesjakkbrett {
            width: 300px;
            height: 300px;
            border: 3px solid black;
        }

        .tabellrute {
            background-color: #ccc;
        }

        .p1 {
            background-color: #F66;
        }

        .p2 {
            background-color: lawngreen;
        }
    </style>
    <script>
        function changeClass(tableElement, playerClass) {
            if (tableElement.className !== 'tabellrute') {
                tableElement.className = 'tabellrute';
            } else {
                tableElement.className = playerClass;
            }
        }

        window.onload = function () {
            var squares = document.getElementsByClassName("tabellrute");
            for (var i = 0; i < squares.length; i++) {
                var square = squares[i];
                square.onclick = function () {
                    var currentSquare = square;
                    return function () {
                        changeClass(currentSquare, 'p1')
                    }
                }(square);
                square.ondblclick = function () {
                    var currentSquare = square;
                    return function () {
                        changeClass(currentSquare, 'p2')
                    }
                }(square);
            }
        }
    </script>
</head>

<body>
<h1>Spill bondesjakk! </h1>
<p>
    Her kan to personer spille bondesjakk. N&aring;r
    f&oslash;rstemann (r&oslash;dt) velger rute, klikker han en gang, og
    n&aring;r andremann (gr&oslash;nt) velger rute, dobbeltklikker han.
</p>

<table id="bondesjakkbrett">
    <tr>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
    </tr>
    <tr>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
    </tr>
    <tr>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
        <td class="tabellrute"></td>
    </tr>
</table>
<p>Du må laste inn siden på nytt for å starte spillet på nytt.</p>

</body>
</html>