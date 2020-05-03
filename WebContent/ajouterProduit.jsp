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

<title>Aboubakar | Create Product</title>


</head>
<body>

	<%@include file="header.jsp"%>

	<div class="container">
		<div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">Ajouter un nouveau produit</div>
				<div class="panel-body">

					<form action="enregister" method="post">

						<div class="form-group">
							<label class="control-label">Désignation</label> <input
								class="form-control" type="text" name="designation"
								value="${produit.designation}" required>
						</div>
						<div class="form-group">
							<label class="control-label">Prix unitaire (DH)</label> <input
								class="form-control" type="text" name="prix"
								value="${produit.prix}" required>
						</div>
						<div class="form-group">
							<label class="control-label">Quantité</label> <input
								class="form-control" type="text" name="quantite"
								value="${produit.quantite}" required>
						</div>
						<button class=" btn btn-primary">Enregisterr</button>
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