<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Search Employee</title>
	</head>
	<body>
        <g:form controller="employee" action="searchProcess">
        	<div id="add-emp-lnk">
				<span></span>
				<g:link action="index">Add Employee</g:link>
			</div>
        
            <label>Id: </label>
            <g:textField name="empId"/><br/><br/>
            <g:actionSubmit value="Search Process"/>
            <g:field type="reset" name="myReset" value="Clear" />
        </g:form>
	</body>
</html>