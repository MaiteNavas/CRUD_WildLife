<%@page import="com.ipartek.modelo.DAO_Constantes"%>
<%@page import="com.ipartek.modelo.dto.V_Animal"%>
<%@page import="com.ipartek.modelo.dto.Especie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
	List<V_Animal> listaV_Animales= new ArrayList<V_Animal>();
	
	if(request.getAttribute(DAO_Constantes.ATR_V_ANIMALES) !=null){
		
		listaV_Animales= (List<V_Animal>)request.getAttribute(DAO_Constantes.ATR_V_ANIMALES);
		
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
		<title>Wild Life</title>
		<link rel="stylesheet" href="styles/style.css">
		<%@include file="includes/bootstrap_link.jsp" %>	
	</head>
	<body class="body">
		<%@include file="includes/cabecera.jsp" %>
		<main id="main">
			<div id="main-form-container">
				<div id="form-container">
					<h3>Añadir animal</h3>
					<form id="form" method="post" action="InsertarAnimal">
				
						<div class="mb-3">
						    <label for="nombre" class="form-label">Nombre</label>
						    <input type="text" name="nombre" class="form-control" id="nombre" required>
						</div>
						
						<div class="mb-3">
						    <label for="peso" class="form-label">Peso</label>
							<input type="text" name="peso" class="form-control" id="peso" pattern="^\d+(\.\d{1,2})?$">
						</div>
						
						<div class="mb-3">
						    <label for="edad" class="form-label">Edad</label>
							<input type="text" name="edad" class="form-control" id="edad"required>
						</div>
						
						<div class="mb-3">
							<label for="fk_id_especie" class="form-label">Especie</label>
							<select name="fk_id_especie" id="fk_id_especie" class="form-select" aria-label="fk_id_categoria">
							  <% for(Especie especie : lista_especies){ %>  
							  <option value="<%= especie.getId() %>"><%= especie.getEspecie() %></option>
							  <% } %> 
							</select>
						</div>
						
					  	<button type="submit" class="btn btn-outline-success">Enviar</button>
					</form>
				</div>
				
				<div id="form-container">
					<h3>Buscar</h3>
					<form method="post" action="BuscarAnimal" id="form">
						<div class="mb-3">
							<label for="texto" class="form-label">Buscar animal</label>
					        <input class="form-control me-2" type="search" name="texto" aria-label="Search">
				        </div>
     					<div class="mb-3">
							<label for="fk_id_especie" class="form-label">Especie</label>
							<select name="fk_id_especie" id="fk_id_especie" class="form-select" aria-label="fk_id_categoria">
							  <option value="0">Todas las especies</option>
							  <% for(Especie especie : lista_especies){ %>  
							  <option value="<%= especie.getId() %>"><%= especie.getEspecie() %></option>
							  <% } %> 
							</select>
						</div>
				        <button class="btn btn-outline-success" type="submit">Buscar</button>
			        </form>
				</div>	
			</div>

			<div id="table" class="container-fluid">
				<h3>LISTA ANIMALES</h3>
				<table class="table">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">NOMBRE</th>
				      <th scope="col">PESO</th>
				      <th scope="col">EDAD</th>
				      <th scope="col">ESPECIE</th>
				      <th scope="col">OPCIONES</th>
				    </tr>
				  </thead>
				  <tbody>
				  
				  	<% for(V_Animal animal : listaV_Animales){ %>  
				
				    <tr>
				      <th scope="row"><%= animal.getId() %></th>
				      <td><%= animal.getNombre() %></td>
				      <td><%= animal.getPeso() %></td>
				      <td><%= animal.getEdad() %></td>
				      <td><%= animal.getEspecie() %></td>
				      <td> 
				      	 <a class="btn btn-danger" href="EliminarAnimal?id=<%= animal.getId() %>">Borrar </a>
				      	 <a class="btn btn-success" href="EditarAnimal?id=<%= animal.getId() %>">Modificar</a>
				      </td>
				     <% 
				     } 
				     %> 
		
				  </tbody>
				</table>
			</div>
		</main>
	</body>
</html>