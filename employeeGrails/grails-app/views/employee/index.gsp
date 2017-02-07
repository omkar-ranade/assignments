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
<script src="../js/yui-min.js"></script>
<title>Welcome: Dealer.com</title>
<script>
$(document).ready(function() {
	$( function() {
		$( "#dob" ).datepicker({
		
		});
	});
});
YUI().use("node","event",function(Y) {
	var button = Y.one("#btn-submit");
	button.on('click', function (e) {
		Y.one('#loader').show();
		ajaxRequest();
		e.target.get('#myform');
		e.preventDefault();
	});
});
function ajaxRequest() {
	YUI().use("io-form", function(Y) {
    var uri = document.location.href;
	var cfg = {
        method: 'POST',
        form: {
            id: myform,
            useDisabled: true
        }
    };

    // Define a function to handle the response data.
    function complete(id, o, args) {
        var id = myform; // Transaction ID.
        var data = o.responseText; // Response data.
		$("#loader").hide();
		var myurl = document.location.origin + "/employeeGrails/employee/save/myform";
		$("#message").html("Record added. <a href='"+ myurl +"'>Go to List.</a>");
	};
    // Subscribe to event "io:complete", and pass an array
    // as an argument to the event handler "complete", since
    // "complete" is global.   At this point in the transaction
    // lifecycle, success or failure is not yet known.
    Y.on('io:complete', complete, Y, ['lorem', 'ipsum']);
    // Make an HTTP request to 'get.php'.
    // NOTE: This transaction does not use a configuration object.
    var request = Y.io(uri,cfg);
	});
}
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
	<g:form name="myform" id="myform" controller="employee" action="save">
		<h2>Create Employee</h2>
		<div id="search-emp-lnk">
				<span></span>
				<g:link action="search"> &#9906; Search Employee</g:link>
			</div>
		<table>
			<tr>
				<th>Id</th>
				<th>Last Name</th>
				<th>First Name</th>
				<th>City</th>
				<th>DOB</th>
			</tr>
			<tr>
				<td><g:textField name="empId"/><br/><br/></td>
				<td><g:textField name="empLastName"/><br/><br/></td>
				<td><g:textField name="empFirstName"/></td>
				<td><g:textField name="city"/><br/><br/></td>
				<td><g:textField name="dob"  id="dob" data-bind="value: dob" placeholder="MM/DD/YYYY"/></td>
				<%--<td><g:textField name="age" id="emp-age" data-bind="value: totalage" name="emp-age"/></td>
			--%></tr>
		</table>
		<div class="form-buttons">
			<g:actionSubmit name="submit" id="btn-submit" value="Save"/>
            <g:field type="reset" name="myReset" value="Clear" />
            <span id="loader" style="display:none">&nbsp;&nbsp;&nbsp;  Please wait...</span>
            <span id="message"></span>
		</div>
	</g:form>
	</div>
</div>	

	
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