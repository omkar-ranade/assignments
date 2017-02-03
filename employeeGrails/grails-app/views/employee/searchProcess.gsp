<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Render Domain</title>
</head>
<body>
Last Name: ${employee.lastName} <br/>
First Name: ${employee.firstName} <br/>
City: ${employee.city} <br/><%--
DOB: ${employee.dob} <br/>
--%>
<div id="back-button">
				<span></span>
				<g:link action="search">Back</g:link>
			</div>
</body>
</html>