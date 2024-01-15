<html>
<head>
    <title>Customers</title>
</head>
<script>
    function addCustomer(name) {
        let body = {
            "name": name,
        }

        let request = new XMLHttpRequest();

        request.open('POST', '/customer/add/json', false);
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(JSON.stringify(body));

        if (request.status !== 200) {
            alert("Ошибка!")
        } else {
            let html =
                '<tr>' +
                '<th>ID</th>' +
                '<th>Name</th>' +
                '</tr>';

            let json = JSON.parse(request.response);

            for (let i = 0; i < json.length; i++) {
                html += '<tr>'
                html += '<td>' + json[i]['id'] + '</td>'
                html += '<td>' + json[i]['name'] + '</td>'
                html += '</tr>'
            }

            document.getElementById('customer_table').innerHTML = html;
        }

    }
</script>
<script>
    function submitDeleteForm() {
        var id = document.getElementById('id').value;
        var url = '/customer/delete/' + encodeURIComponent(id);
        document.getElementById('deleteForm').action = url;
        document.getElementById('deleteForm').submit();
    }
</script>
<script>
    function submitUpdateForm() {
        var id = document.getElementById('id').value;
        var name = document.getElementById('name').value;
        var url = '/customer/' + encodeURIComponent(id);
        var requestData = {
            id: id,
            name: name
        };

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
            .then(response => {
                if (response.ok) {
                    alert('customer updated successfully');
                } else {
                    alert('Failed to update employee');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
<script>
    function updateFormAction() {
        var id = document.getElementById('customerId').value;
        var name = document.getElementById('customerName').value;
        var url = '/customer/' + encodeURIComponent(id);
        var requestData = {
            id: id,
            name: name
        };

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        })
            .then(response => {
                if (response.ok) {
                    alert('customer updated successfully');
                    location.reload();
                } else {
                    alert('Failed to update customer');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
<body>
<table id="customer_table">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <#list customers as customer>
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
        </tr>
    </#list>
</table>
<div>
    <input id="name" placeholder="Enter Name">
    <button onclick="addCustomer(
        document.getElementById('name').value)">
        Add
    </button>
</div>
<h1>Customer Deletion</h1>
<form id="deleteForm" method="post">
    <label for="id">Customer ID:</label>
    <input type="text" id="id" name="id" required>
    <button type="button" onclick="submitDeleteForm()">Delete</button>
</form>
<h1>Customer Update</h1>
<form id="customerRequest" method="POST" action="/customer/{id}">
    <input type="hidden" name="_method" value="PUT">
    <label for="customerId">Customer ID:</label>
    <input type="text" id="customerId" name="id" required>
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="name" required>
    <button type="button" onclick="updateFormAction()">Update</button>
</form>
</body>
</html>
