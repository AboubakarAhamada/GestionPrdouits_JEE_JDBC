<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">

<title>Aboubakar | Confirmation</title>


</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">Confirmation</div>
				<div class="panel-body">
					<form action="home" method="post">
						<div>
							<span> Votre produit est bien enrégistré</span><br>
						</div>
						<hr>
						<div class="form-group">
							<label class="control-label">ID : </label> ${produit.id}
						</div>
						<div class="form-group">
							<label class="control-label">Désignation : </label>
							${produit.designation}
						</div>
						<div class="form-group">
							<label class="control-label">Prix : </label> ${produit.prix} DH
						</div>
						<div class="form-group">
							<label class="control-label">Quantité : </label>
							${produit.quantite}
						</div>
						<button class=" btn btn-default">Retourner</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP.1.1 
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies

		if (session.getAttribute("username") == null)
			response.sendRedirect("login.jsp");
	%>
</body>
</html>