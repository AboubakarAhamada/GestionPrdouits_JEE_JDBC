<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Aboubakar | Produits</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>

	<div class="container">
		<div class="col-md-6 col-xm-12 col-sm-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">Liste des produits présents</div>
				<div class="panel-body">
					<form action="chercher" method="get">
						<label>Mot clé : </label> <input type="text" name="motCle"
							value="${model.motCle}" style="height:34px" placeholder="Entrer un mot clé">
						<button type="submit" class="btn btn-success">Afficher les produits</button>
					</form>
					<br>
					<table class="table table-striped ">
						<tr>
							<td>ID</td>
							<td>Designation</td>
							<td>Prix</td>
							<td>Quantité</td>
							<td>Modification</td>
							<td>Suppression</td>
						</tr>
						<c:forEach items="${model.listProduit}" var="produit">
							<tr>
								<td>${produit.id}</td>
								<td>${produit.designation}</td>
								<td>${produit.prix}</td>
								<td>${produit.quantite}</td>
								<td><a href="modifier?id=${produit.id}">Modifier</a></td>
								<td><a href="supprimer?id=${produit.id}">Supprimer</a> </td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //HTTP.1.1 
	response.setHeader("Pragma", "no-cache");	// HTTP 1.0
	response.setHeader("Expires","0");		// Proxies

	if(session.getAttribute("username")==null)	
		response.sendRedirect("login.jsp");
%> 
</body>
</html>