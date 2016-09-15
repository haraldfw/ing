<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="http://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script src="jquery.form.min.js"></script>
    <title>Kunder</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        input {
            display: block;
            margin: 10px;
        }

        form {
            margin: 20px 0;
        }
    </style>
    <script>
        $(document).ready(function () {
            // Bind opp tabellen mot rest-ressursen '/kunder'
            $('#myTable').DataTable({
                ajax: {
                    url: '/rest/oving3/kunder',
                    dataSrc: ''
                },
                columns: [
                    {data: 'id'},
                    {data: 'navn'}
                ]
            });

            $("#deleteForm").ajaxSubmit({
                url: '/rest/oving3/kunder/' + $("#deleteId").val(),
                type: 'delete'
            });

            // Slett rest-ressursen '/kunder/kundeId'
            $("#delete").click(function () {
                $.ajax({
                    url: '/rest/oving3/kunder/' + $("#deleteId").val(),
                    type: 'DELETE',
                    success: function (result) {
                        $('#myTable').DataTable().ajax.reload();
                    }
                });
            });

            // Lag ny rest-ressursen under '/kunder/'
            $("#create").click(function () {
                $.ajax({
                    url: '/rest/oving3/kunder',
                    type: 'POST',
                    data: JSON.stringify({
                        id: $("#newId").val(),
                        navn: $("#newName").val()
                    }),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    success: function (result) {
                        $('#myTable').DataTable().ajax.reload();
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>Kunder</h1>
<table id="myTable" class="display compact">
    <thead>
    <tr>
        <th>ID</th>
        <th>Navn</th>
    </tr>
    </thead>
</table>
<form>
    <input id="deleteId" type="text" value="deleteId" title="delete">
    <input id="delete" type="submit" value="Delete">
</form>

<form>
    <input id="newId" type="text" value="newId" title="newId">
    <input id="newName" type="text" value="newName" title="newName">
    <input id="create" type="submit" value="Create new">
</form>

<form id="deleteForm">
    <input id="updateId" type="text" value="updateId" title="updateId">
    <input id="updateName" type="text" value="Update Name" title="updateName">
    <input id="update" type="submit" value="Update">
</form>
<br>
</body>
</html>
