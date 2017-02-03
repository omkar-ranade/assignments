 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dealer.com->home</title>
<head>
<link href="<c:url value="/staticcontent/css/style.css"/>" rel="stylesheet"/>
<link href="<c:url value="/staticcontent/css/jquery-ui.css"/>" rel="stylesheet"/>
<script src="<c:url value="/staticcontent/js/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="/staticcontent/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/staticcontent/js/knockout-3.4.1.js"/>"></script>
<title>Welcome: Dealer.com</title>
<script>
$(document).ready(function() {
 $( function() {
		$( "#emp-dob" ).datepicker();
	}); 
});



function deleteEmployee(employeeId){
    document.getElementById('employeeForm').action = "deleteEmployee?employeeId="+employeeId;
    document.getElementById('employeeForm').submit();
}

function openAddEmployeeDiv(){
   document.getElementById('addEmployee').style.display = 'block';
}
function addEmployee(){
   document.getElementById('employeeForm').action = "addEmployee";
   document.getElementById('employeeForm').submit();
}

function searchEmployee()
{
	 var emplId = document.getElementById('searchId').value; 
	 document.getElementById('employeeForm').action =  "getEmployeeById?employeeId="+emplId;
	 document.getElementById('employeeForm').submit();
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
	 <form:form method="post" id="employeeForm" name="employeeForm" commandName="employee">
		<h2>Create Employee</h2>
		<div id="search-emp-lnk">
           	 Employee Id 
           		<form:input path="empId" id="searchId"/> <input type="button" value="SEARCH" onclick="searchEmployee()"/>           	</td>
		</div>
		<table>
			<tr>
				<th>Employee Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>City</th>
				<th>DOB</th>
				<th>Age</th>
			</tr>
			<tr>
				<td><form:input path="empId" cssClass="input-text"/></td>
				<td><form:input path="empFirstName" cssClass="input-text"/></td>
				<td><form:input path="empLastName" cssClass="input-text"/></td>
				<td><form:input path="city" cssClass="input-text"/></td>
				<td><form:input path="dob" cssClass="input-text" id="emp-dob" data-bind="value: dob"/></td>
				<td><input class="input-text" type="text" readonly id="emp-age" data-bind="value: totalage" name="emp-age"></td>
			</tr>
		</table>
		<div class="form-buttons">
			 <input type="button" value="Save" id="btn-submit" onclick="addEmployee()"/>
			<!-- <input type="submit" name="submit" id="btn-submit" value="Add Employee"> -->
			<input type="reset" name="cancel" value="Clear">
		</div>
		
		<div>
			<table>
			 </tr>    
                <tr>
                    <th>Id</th>

                    <th>Employee Name</th>

                    <th>Last Name</th>
					
					<th>City</th>
					
					<th>Date of birth</th>
					
					<th>age</th>

                    <th>Action</th>

                </tr>


             <c:forEach items="${empList}" var="emp">
                 <tr>
                     <td>
                           <li><c:out value="${emp.empId}"/></li>
                        </td>
                     <td>
                           <li>${emp.empFirstName}</li>
                         </td>
                     <td>
                            <li>${emp.empLastName}</li>
                     </td>
                     
                     <td>
                            <li>${emp.city}</li>
                     </td>
                     
                      <td>
                            <li>${emp.dob}</li>
                     </td>
                      
                     <td>
                     	
                     </td>
                     <td>
                            <input type="button" value="DELETE" onclick="deleteEmployee('${emp.empId}')"/>
                     </td>
                    </tr>
            </c:forEach>
         </table>
		</div>
		
		
		
		
	</form:form>
	</div>
</div>	
	<script src="<c:url value="/staticcontent/js/yui-min.js"/> "></script>
	
</body>
</html>
