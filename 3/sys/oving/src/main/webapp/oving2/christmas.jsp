<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Christmas countdown</title>
    <script>
        window.onload = function () {
            var today = new Date();
            var christmasDay = new Date(today.getFullYear().valueOf(), 11, 24); // js months: 0-11
            if (today > christmasDay) { // if we have passed christmas this year, use next year
                // this way monthsLeft and daysLeft will never be negative
                christmasDay.setFullYear(christmasDay.getFullYear() + 1)
            }
            console.log(today);
            console.log(christmasDay);

            var monthsLeft = christmasDay.getMonth() - today.getMonth()
                             + (12 * (christmasDay.getFullYear() - today.getFullYear()));

            var one_day = 1000 * 60 * 60 * 24;
            // Diff between today and christmas, and convert to days
            var daysLeft = Math.ceil((christmasDay.getTime() - today.getTime()) / (one_day));

            var parElement = document.getElementById("countdown");
            parElement.innerHTML = "Today: " + today.toDateString() +
                                   ". Days left until christmas: " + daysLeft;

            if (monthsLeft <= 1) {
                parElement.style = "color: #F00";
            } else if (monthsLeft <= 3) {
                parElement.style = "color: #0F0";
            }
        }
    </script>
</head>
<body>
<p id="countdown"></p>
</body>
</html>
