<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>


<head>
<link href="<c:url value="/staticcontent/css/style.css"/>" rel="stylesheet"/>
<link href="<c:url value="/staticcontent/css/jquery-ui.css"/>" rel="stylesheet"/>
<script src="<c:url value="/staticcontent/js/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="/staticcontent/js/jquery-ui.js"/>"></script>
<title>Welcome: Dealer.com</title>
<script>

function searchEmployee()
{
	 var empId = document.getElementById('searchId').value; 
	 document.getElementById('search-emp').action =  "getEmployeeById?employeeId="+empId;
	 document.getElementById('search-emp').submit();
}



$(document).ready(function() {

});
</script>
</head>
<body>
<div id="content">
	<div id="search-employee-block">
	<form:form name="search-emp" method="post" commandName="employee">
		<h2>Search Employee</h2>
		<div id="add-emp-link">
		<!-- 	<span><img src="https://cc3.dealer.com/shared-assets/images/icons_buttons/grey/16/add_black.png"></span> -->
			<!-- <a href="index.html">Add Employee</a> -->
		</div>
		<table>
			<tr>
	 			<td class="title">ID</td><td><form:input path="empId" cssClass="input-text" id="searchId"/></td>
	 		</tr>
	 		<!-- <tr>
	 			<td class="middle" colspan=2>AND / OR </td>
	 		</tr>
	 		<tr>
				<td class="title">Name</td><td><input class="input-text" type="text" id="emp-name" name="emp-name"></td>
			</tr> -->
		</table>
		<div class="form-buttons">
		 <!-- <input type="button" value="Search Employee" onclick="searchEmployee()"/> -->
		</div>
		
		
		<div>
			 <table border="0">
                <tr>
                    <td>Id</td>

                    <td>Employee Name</td>

                    <td>Last Name</td>

					<td>Date of birth</td>
					
					<td>City</td>
                </tr>


            
                 <tr>
                     <td>
                           <li><c:out value="${employee.empId}" /></li>
                        </td>
                     <td>
                           <li>${employee.empFirstName}</li>
                         </td>
                     <td>
                            <li>${employee.empLastName}</li>
                     </td>
                   <td>
                   		<li>${employee.dob}</li>
                     </td>
                      <td>
                            <li>${employee.city}</li>
                     </td>
                    
                    </tr>
          
         </table>
		</div>
		
		
		
		
	</form:form>
	</div>
</div>	
		<script src="<c:url value="/staticcontent/js/yui-min.js"/> "></script>
	


</body>
</html>