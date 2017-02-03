<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Form</title>
		<head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/knockout-3.4.1.js"></script>
<title>Welcome: Dealer.com</title>
<script>
$(document).ready(function() {
	$( function() {
		$( "#emp-dob" ).datepicker({
		
		});
	});
});
function calAge(value) {
	if(value == null) {
		return 0; //should not throw js isNaN error
	}
	var today = new Date(),
		dob = new Date(value),
		age = today.getFullYear() - dob.getFullYear(); //This is the update
	return age;
}
		
function AppViewModel() {
	this.dob = ko.observable();
	this.totalage = ko.computed(function() {
		return calAge(this.dob());
	}, this);
}

function onLoad()
{
	ko.applyBindings(new AppViewModel());
}

</script>
</head>
		
	</head>
	<body onload="onLoad()">
<div id="content">
	<div id="create-employee-block">
	<g:form controller="employee" action="save">
		<h2>Create Employee</h2>
		<div id="search-emp-lnk">
				<span></span>
				<g:link action="search">Search Employee</g:link>
			</div>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>City</th>
				<th>DOB</th>
				<th>Age</th>
			</tr>
			<tr>
				<td><g:textField name="firstName"/></td>
				<td><g:textField name="lastName"/><br/><br/></td>
				<td><g:textField name="city"/><br/><br/></td>
				<td><g:textField name="DOB" id="emp-dob" data-bind="value: dob" name="emp-dob" placeholder="MM/DD/YYYY"/></td>
				<td><g:textField name="age" readonly id="emp-age" data-bind="value: totalage" name="emp-age"/></td>
			</tr>
		</table>
		<div class="form-buttons">
			<g:actionSubmit value="Save"/>
            <g:field type="reset" name="myReset" value="Clear" />
		</div>
	</g:form>
	</div>
</div>	
	<script src="../js/yui-min.js" type="text/javascript"></script>
	
</body>
	
	<%--<body>
        <g:form controller="employee" action="save">
        	<div id="search-emp-lnk">
				<span></span>
				<g:link action="search">Search Employee</g:link>
			</div>
        
            <label>First Name: </label>
            <g:textField name="firstName"/><br/><br/>
            <label>Last Name: </label>
            <g:textField name="lastName"/><br/><br/>
            <label>City: </label>
            <g:textField name="city"/><br/><br/>
            <label>DOB: </label>
            <g:textField name="dob"/><br/><br/>
            <g:actionSubmit value="Save"/>
            <g:field type="reset" name="myReset" value="Clear" />
        </g:form>
	</body>
--%></html>