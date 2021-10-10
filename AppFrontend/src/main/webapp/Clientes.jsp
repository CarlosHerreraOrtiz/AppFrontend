<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="Servlet.Clientes"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
	<head>
		 <!-- Required meta tags -->
	    <meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">

    	<!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
		<link rel= "stylesheet" href="estilos.css">
		
	    <title>Clientes</title>	
	</head>
	<body>
		
		<div class="row">
			<div class="card col-md-4">
				<div class="card-body">
					<h5 class="card-title">Clientes</h5>
					<h6 class="card-subtitle mb-2 mx-auto text-muted">En este panel podras gestionar los datos de los usuarios del sistema</h6>
					<div>
						<form class="form-horizontal was-validated" method="GET" action="Controlador" autocomplete="on">
							
							<input type="hidden" name="menu" value="Clientes">
							
							<div class="form-group">
								<div class="input-group">
									<label for="Cedula" class="control-label col-md-3"> Cédula:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Cedula" id="Cedula" type="text" name="txtcedula" value="${clienteSeleccionado.getCedula_cliente()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
     									 
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<div class="input-group">
									<label for="Nombre" class="control-label col-md-3">Nombre:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Nombre" type="text" name="txtnombre" value="${clienteSeleccionado.getNombre_cliente()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<label for="Apellido" class="control-label col-md-3">Apellido:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Apellido" type="text" name="txtapellido" value="${clienteSeleccionado.getApellido_cliente()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<label for="Direccion" class="control-label col-md-3">Dirección:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Dirección" type="text" name="txtdireccion" value="${clienteSeleccionado.getDireccion_cliente()}"required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<label for="Email" class="control-label col-sm-3">Email:</label> 
									<div class="col-sm-9">			
										<input class="form-control is-valid" id="Email" placeholder="@" type="text" name="txtemail" value="${clienteSeleccionado.getEmail_cliente()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<label for="Telefono" class="control-label col-md-3">Teléfono:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Teléfono" type="text" name="txttelefono" value="${clienteSeleccionado.getTelefono_cliente()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<div class="input-group">
									<div class="col-md-3 "></div>
									<div class="col-md-9 ">
										<input type="submit" class="btn btn-outline-primary" name="accion" 	value="Agregar"> 
										<input type="submit" class="btn btn-outline-success" name="accion" value="Actualizar">
									</div>
								</div>	
							</div>
						
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Cedula</th>
							<th scope="col">Nombre</th>
							<th scope="col">Apellido</th>
							<th scope="col">Direccion</th>							
							<th scope="col">Email</th>
							<th scope="col">Telefono</th>

						</tr>
					</thead>
	
				<tbody>
					<%
					ArrayList<Clientes> lista = (ArrayList<Clientes>) request.getAttribute("lista");
					for (Clientes cliente : lista) {
					%>
					<tr>
						<td><%=cliente.getCedula_cliente()%></td>
						<td><%=cliente.getNombre_cliente()%></td>
						<td><%=cliente.getApellido_cliente()%></td>		
						<td><%=cliente.getDireccion_cliente()%></td>				
						<td><%=cliente.getEmail_cliente()%></td>
						<td><%=cliente.getTelefono_cliente()%></td>
						


						<td><a class="btn btn-outline-warning"
							href="Controlador?menu=Clientes&accion=Cargar&id=<%=cliente.getCedula_cliente()%>">Editar</a>
							<a class="btn btn-outline-danger"
							href="Controlador?menu=Clientes&accion=Eliminar&id=<%=cliente.getCedula_cliente()%>">Eliminar</a>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
				</table>
			</div>
		</div>
		
			<!-- Latest compiled and minified JavaScript -->
			<!-- Option 2: Separate Popper and Bootstrap JS -->
			<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.min.js" integrity="sha384-PsUw7Xwds7x08Ew3exXhqzbhuEYmA2xnwc8BuD6SEr+UmEHlX8/MCltYEodzWA4u" crossorigin="anonymous"></script>
	</body>
</html>