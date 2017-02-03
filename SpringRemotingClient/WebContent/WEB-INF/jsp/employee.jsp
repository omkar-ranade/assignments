 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

 <%@ page isELIgnored="false"%>
<html>
<head>
<link href="<c:url value="/staticcontent/css/style.css"/>" rel="stylesheet"/>
<link href="<c:url value="/staticcontent/css/jquery-ui.css"/>" rel="stylesheet"/>
<link href="<c:url value="/staticcontent/js/jquery-1.12.4.js"/>" />
<link href="<c:url value="/staticcontent/js/jquery-ui.js"/>" />
<!-- <link rel="stylesheet" href="/style.css" type="text/css"> -->
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js"></script>
<title>Welcome: Dealer.com</title>
<script>
$(document).ready(function() {
	$( function() {
		$( "#emp-dob" ).datepicker();
	});
});
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee</title>

      <script type="text/javascript">

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
        	 var empId = document.getElementById('searchId').value; 
        	 document.getElementById('employeeForm').action =  "getEmployeeById?employeeId="+empId;
        	 document.getElementById('employeeForm').submit();
         }
         
      </script>


</head>
<body>
    <div align="center">
        <form:form method="post" id="employeeForm" name="employeeForm" commandName="employee">
   <div id="content">
	<div id="create-employee-block"> 
           
            <table border="0">
                <tr>
                    <td colspan="4" align="center"><h2>Spring MVC Form Demo - Hessian Remoting + MongoDb</h2></td>
                </tr>
           <tr>
           	<th>
           	 Employee Id:
           	</th>
           	<td>
           		<form:input path="empId" id="searchId"/> <input type="button" value="SEARCH" onclick="searchEmployee()"/>           	</td>
           </tr>    
                <tr>
                    <th>Id:</th>

                    <th>Employee Name:</th>

                    <th>Last Name:</th>

                    <th>Action</th>

                </tr>


             <c:forEach items="${empList}" var="employee">
                 <tr>
                     <td>
                           <li><c:out value="${employee.empId}"/></li>
                        </td>
                     <td>
                           <li>${employee.empFirstName}</li>
                         </td>
                     <td>
                            <li>${employee.empLastName}</li>
                     </td>
                     <td>
                            <input type="button" value="DELETE" onclick="deleteEmployee('${employee.empId}')"/>
                     </td>
                    </tr>
            </c:forEach>
         </table>
         </div>
         </div>
<div class="form-buttons">
            <input type="button" value="Add Employee" id="btn-submit" onclick="openAddEmployeeDiv()"/>
</div>
         <div id="addEmployee" style="display: none">
             <table>
                 <tr>
                     Enter Employee Details
                 </tr>
                 <tr>
                     <td>Employee Id</td>
                     <td><form:input path="empId"/></td>
                 </tr>
                 <tr>
                     <td>Employee First Name</td>
                     <td><form:input path="empFirstName"/></td>
                 </tr>
                 <tr>
                     <td>Employee Last Name</td>
                     <td><form:input path="empLastName"/></td>
                 </tr>
                 <tr>
                     <input type="button" value="Save" onclick="addEmployee()"/>
                 </tr>
             </table>
         </div>


        </form:form>
    </div>
    <script src="../js/yui-min.js" type="text/javascript"></script>
</body>
</html>
