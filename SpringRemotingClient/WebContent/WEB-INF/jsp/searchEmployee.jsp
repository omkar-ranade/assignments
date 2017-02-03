 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

 <%@ page isELIgnored="false"%>
<html>
<head>
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

      </script>


</head>
<body>
    <div align="center">
        <form:form method="post" id="employeeForm" name="employeeForm" commandName="employee">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Spring MVC Form Demo - Hessian Remoting + MongoDb</h2></td>
                </tr>
                <tr>
                    <td>Id:</td>

                    <td>Employee Name:</td>

                    <td>Last Name:</td>

                </tr>


            
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
                    
                    </tr>
          
         </table>

        </form:form>
    </div>
</body>
</html>
