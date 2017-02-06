<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/knockout-3.4.1.js"></script>
<script src="../js/yui-min.js" type="text/javascript"></script>
<title>Welcome: Dealer.com</title>
<script>
$(document).ready(function() {
	$( function() {
		$( "#emp-dob" ).datepicker({
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
    var uri = "https://reqres.in/api/users";
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
		console.log("databelow");
		console.log(data);
		$("#loader").hide();
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
<body onload="onLoad()">
<div id="content">
	<div id="create-employee-block">
	<form name="create-emp" id="myform" method="POST">
		<h2>Create Employee</h2>
		<div id="search-emp-lnk">
			<span></span>
			<a href="search.html">	&#128269; Search Employee</a>
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
				<td><input class="input-text" type="text" id="emp-name" name="emp-name"></td>
				<td><input class="input-text" type="text" id="emp-name" name="emp-name"></td>
				<td><input class="input-text" type="text" id="emp-address" name="emp-address"></td>
				<td><input class="input-text" type="text" id="emp-dob" data-bind="value: dob" name="emp-dob" placeholder="MM/DD/YYYY"></td>
				<td><input class="input-text" type="text" readonly id="emp-age" data-bind="value: totalage" name="emp-age"></td>
			</tr>
		</table>
		<div class="form-buttons">
			
			<input type="submit" name="submit" id="btn-submit" onSubmit="ajaxRequest();" value="Add Employee">
			<input type="reset" name="cancel" value="Clear">
			<span id="loader" style="display:none">&nbsp;&nbsp;&nbsp;  Please wait...</span>
		</div>
	</form>
	</div>
</div>	
	
	
</body>
</html>
