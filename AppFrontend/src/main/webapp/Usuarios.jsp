<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="Servlet.Usuarios"%>
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
		
	    <title>Usuarios</title>	
	</head>
	<body>
		
		<div class="row">
			<div class="card col-md-4">
				<div class="card-body">
					<h5 class="card-title">Usuarios</h5>
					<h6 class="card-subtitle mb-2 mx-auto text-muted">En este panel podras gestionar los datos de los usuarios del sistema</h6>
					<div>
						<form class="form-horizontal was-validated" method="GET" action="Controlador" autocomplete="on">
							
							<input type="hidden" name="menu" value="Usuarios">
							
							<div class="form-group">
								<div class="input-group">
									<label for="Cedula" class="control-label col-md-3"> Cedula:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Cedula" id="Cedula" type="text" name="txtcedula" value="${usuarioSeleccionado.getCedula_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
	

	
							<div class="form-group">
								<div class="input-group">
									<label for="Nombre" class="control-label col-md-3">Nombre:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Nombre" type="text" name="txtnombre" value="${usuarioSeleccionado.getNombre_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<label for="Apellido" class="control-label col-md-3">Apellido:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Apellido" type="text" name="txtapellido" value="${usuarioSeleccionado.getApellido_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>		
							
							
							<div class="form-group">
								<div class="input-group">
									<label for="Email" class="control-label col-sm-3">Email:</label> 
									<div class="col-sm-9">			
										<input class="form-control is-valid" id="Email" placeholder="@" type="text" name="txtemail" value="${usuarioSeleccionado.getEmail_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>	
	

							<div class="form-group">
								<div class="input-group">
									<label for="Tipo" class="control-label col-md-3">Tipo:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Tipo" type="text" name="txttipo" value="${usuarioSeleccionado.getTipo_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="input-group">
									<label for="Estado" class="control-label col-sm-3">Estado:</label> 
									<div class="col-sm-9">			
										<input class="form-control is-valid" id="Estado" placeholder="Estado" type="text" name="txtestado" value="${usuarioSeleccionado.getEstado_usuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>


							<div class="form-group">
								<div class="input-group">
									<label for="Usuario" class="control-label col-sm-3">Usuario:</label> 
									<div class="col-sm-9">			
										<input class="form-control is-valid" id="Usuario" placeholder="Usuario" type="text" name="txtusuario" value="${usuarioSeleccionado.getUsuario()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>								
							
							<div class="form-group">
								<div class="input-group">
									<label for="Password" class="control-label col-md-3">Password:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Password" type="text" name="txtpassword" value="${usuarioSeleccionado.getPassword()}"required>
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
							<th scope="col">Email</th>
							<th scope="col">Tipo</th>
							<th scope="col">Estado</th>
							<th scope="col">Usuario</th>
							<th scope="col">Password</th>
						</tr>
					</thead>
	
				<tbody>
					<%
					ArrayList<Usuarios> lista = (ArrayList<Usuarios>) request.getAttribute("lista");
					for (Usuarios usuario : lista) {
					%>
					<tr>
						<td><%=usuario.getCedula_usuario()%></td>
						
						<td><%=usuario.getNombre_usuario()%></td>
						<td><%=usuario.getApellido_usuario()%></td>	
						<td><%=usuario.getEmail_usuario()%></td>
						<td><%=usuario.getTipo_usuario()%></td>	
						<td><%=usuario.getEstado_usuario()%></td>
						<td><%=usuario.getUsuario()%></td>	
						<td><%=usuario.getPassword()%></td>	
						


						<td><a class="btn btn-outline-warning"
							href="Controlador?menu=Usuarios&accion=Cargar&id=<%=usuario.getCedula_usuario()%>">Editar</a>
							<a class="btn btn-outline-danger"
							href="Controlador?menu=Usuarios&accion=Eliminar&id=<%=usuario.getCedula_usuario()%>">Eliminar</a>
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