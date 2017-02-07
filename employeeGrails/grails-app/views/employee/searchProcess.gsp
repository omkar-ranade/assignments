<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Render Domain</title>
</head>
<body><table>
<tr>

<th>Last Name</th>
<th>First Name</th>
<th>City</th>
<th>DOB</th>
</tr>
<tr>

<td>${employee.empLastName} </td>
<td>${employee.empFirstName} </td>
<td>${employee.city} </td>
<td>${employee.dob} </td>
</tr>
</table>
<div id="back-button">
				<span></span>
				<g:link action="search">Back</g:link>
			</div>
</body>
</html>