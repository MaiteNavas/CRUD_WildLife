<%@page import="com.ipartek.modelo.DAO_Constantes"%>
<%@page import="com.ipartek.modelo.dto.Animal"%>
<%@page import="com.ipartek.modelo.dto.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
	Animal animal= new Animal();
	
	if(request.getAttribute(DAO_Constantes.ATR_ANIMAL) !=null){
		
		animal= (Animal)request.getAttribute(DAO_Constantes.ATR_ANIMAL);
		
	}
	
	List<Especie> lista_especies= new ArrayList<Especie>();
	
	if(request.getAttribute(DAO_Constantes.ATR_ESPECIES) !=null){
		
		lista_especies= (List<Especie>)request.getAttribute(DAO_Constantes.ATR_ESPECIES);
		
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Editar animal</title>
		<link rel="stylesheet" href="styles/style.css">
		<%@include file="includes/bootstrap_link.jsp" %>	
	</head>
	<body>
		<%@include file="includes/cabecera.jsp" %>
		<div class="container-fluid">
			<h3>Editar animal</h3>
			<form method="post" action="ActualizarAnimal">
			
				<div class="mb-3">
				    <label for="id" class="form-label">Id</label>
				    <input type="text" name="id" value="<%= animal.getId() %>" class="form-control" id="id" readonly>
				</div>
			
				<div class="mb-3">
				    <label for="nombre" class="form-label">Nombre</label>
				    <input type="text" name="nombre" value="<%= animal.getNombre() %>" class="form-control" id="nombre" required>
				</div>
				
				<div class="mb-3">
				    <label for="peso" class="form-label">Peso</label>
					<input type="text" name="peso" value="<%= animal.getPeso() %>" class="form-control" id="peso" pattern="^\d+(\.\d{1,2})?$">
				</div>
				
				<div class="mb-3">
				    <label for="edad" class="form-label">Edad</label>
					<input type="text" name="edad" value="<%= animal.getEdad() %>" class="form-control" id="edad"required>
				</div>
				
				<div class="mb-3">
				<label for="fk_id_especie" class="form-label">Categoria</label>
					<select name="fk_id_especie" id="fk_id_especie" class="form-select" aria-label="fk_id_categoria">
					  <% for(Especie especie : lista_especies){ %>  
					  	<option value="<%= especie.getId() %>"<%= animal.getFk_id_especie() == especie.getId() ? "selected" : "" %>><%= especie.getEspecie() %></option>
					  <% } %> 
					</select>
				</div>
				
			  <button type="submit" class="btn btn-primary">Enviar</button>
			</form>
		
		</div>		

	</body>
</html>