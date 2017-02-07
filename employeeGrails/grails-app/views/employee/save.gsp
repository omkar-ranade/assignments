<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Success</title>
</head>
<style>
    .columns {
    -webkit-column-count: 5;
    -moz-column-count: 5;
    column-count: 5;
}
    </style>
<body>
Success
<table>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>DOB</th>
				<th>City</th>

			</tr>
<g:each in="${employeeLst}" var="employee">
			<tr>
				
   <td>    ${employee.empId} </td>
       <td> ${employee.empFirstName}</td>
      <td>  ${employee.empLastName}</td>
      <td>  ${employee.dob}</td>
      <td>  ${employee.city}</td>
    
    </tr>
    </g:each>
		</table>
<div id="back-button">
				<span></span>
				<g:link action="index">Back</g:link>
			</div>
</body>
</html>