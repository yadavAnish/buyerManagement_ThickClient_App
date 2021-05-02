<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="com.buyer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buyers Management</title>

<link rel="stylesheet" href="view/bootstrap.min.css">

</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h2>Buyers Management </h2>
<form id="formItem" name="formItem">
 Buyers Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Buyers Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br> Buyers Phone:
 <input id="phone" name="phone" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 buyer buyerObj = new buyer(); 
 out.print(buyerObj.readBuyer()); 
 %>
</div>
</div> </div> </div> 


<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/buyer.js"></script>


</body>
</html>