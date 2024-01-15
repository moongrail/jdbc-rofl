<html>
<head>
    <title>Projects</title>
</head>
<script>
    function addproject(name) {
        let body = {
            "name": name,
        }

        let request = new XMLHttpRequest();

        request.open('POST', '/project/add/json', false);
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

            document.getElementById('project_table').innerHTML = html;
        }

    }
</script>
<script>
    function submitDeleteForm() {
        var id = document.getElementById('id').value;
        var url = '/project/delete/' + encodeURIComponent(id);
        document.getElementById('deleteForm').action = url;
        document.getElementById('deleteForm').submit();
    }
</script>
<script>
    function submitUpdateForm() {
        var id = document.getElementById('id').value;
        var name = document.getElementById('name').value;
        var url = '/project/' + encodeURIComponent(id);
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
                    alert('Employee updated successfully');
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
        var id = document.getElementById('projectId').value;
        var name = document.getElementById('projectName').value;
        var url = '/project/' + encodeURIComponent(id);
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
                    alert('Project updated successfully');
                    location.reload();
                } else {
                    alert('Failed to update employee');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
<body>
<table id="project_table">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <#list projects as project>
        <tr>
            <td>${project.id}</td>
            <td>${project.name}</td>
        </tr>
    </#list>
</table>
<div>
    <input id="name" placeholder="Enter Name">
    <button onclick="addproject(
        document.getElementById('name').value)">
        Add
    </button>
</div>
<h1>Project Deletion</h1>
<form id="deleteForm" method="post">
    <label for="id">Project ID:</label>
    <input type="text" id="id" name="id" required>
    <button type="button" onclick="submitDeleteForm()">Delete</button>
</form>
<h1>Project Update</h1>
<form id="employeeRequest" method="POST" action="/project/{id}">
    <input type="hidden" name="_method" value="PUT">
    <label for="projectId">Project ID:</label>
    <input type="text" id="projectId" name="id" required>
    <label for="projectName">Project Name:</label>
    <input type="text" id="projectName" name="name" required>
    <button type="button" onclick="updateFormAction()">Update</button>
</form>
</body>
</html>