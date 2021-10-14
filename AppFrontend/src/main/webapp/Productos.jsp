<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="Servlet.Productos"%>
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
		
	    <title> Productos </title>	
	</head>
	<body>
		
		<div class="row">
			<div class="card col-md-4">
				<div class="card-body">
					<h5 class="card-title">Productos</h5>
					<h6 class="card-subtitle mb-2 mx-auto text-muted">En este panel podras gestionar los datos de los Productos</h6>
					<div>
						<form class="form-horizontal was-validated" method="GET" action="Controlador" autocomplete="on">
							
							<input type="hidden" name="menu" value="Productos">
							
							<div class="form-group">
								<div class="input-group">
									<label for="Codigo_producto" class="control-label col-md-3"> Código:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Código Producto" id="Codigo_producto" type="text" name="txtcodigo_producto" value="${productoSeleccionado.getCodigo_producto()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
     									 
									</div>
								</div>
							</div>
	
	
							<div class="form-group">
								<div class="input-group">
									<label for="Nombre_producto" class="control-label col-md-3">Nombre Producto:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Nombre Producto" id="Nombre_producto" type="text" name="txtnombre_producto" value="${productoSeleccionado.getNombre_producto()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>	
	
	
							<div class="form-group">
								<div class="input-group">
									<label for="Nit_Proveedor" class="control-label col-md-3"> Nit Proveedor:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Nit Proveedor" id="Nit_Proveedor" type="text" name="txtnit_proveedor" value="${productoSeleccionado.getNit_proveedor()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="input-group">
									<label for="Precio_Compra" class="control-label col-md-3">Precio Compra:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Precio Compra" type="text" name="txtprecio_compra" value="${productoSeleccionado.getPrecio_compra()}"required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>

		
	
							<div class="form-group">
								<div class="input-group">
									<label for="Iva" class="control-label col-md-3">Iva:</label> 
									<div class="col-md-9">
										<input class="form-control" placeholder="Iva" type="text" name="txtiva_compra" value="${productoSeleccionado.getIva_compra()}" required>
										<div class="valid-feedback"> </div> <div class="invalid-feedback"> </div>
									</div>
								</div>
							</div>
							

									
							<div class="form-group">
								<div class="input-group">
									<label for="Precio_Venta" class="control-label col-md-3">Precio Venta:</label> 
									<div class="col-md-9">			
										<input class="form-control" placeholder="Precio Venta" type="text" name="txtprecio_venta" value="${productoSeleccionado.getPrecio_venta()}"required>
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
							<th scope="col">Codigo_producto</th>
							<th scope="col">Nombre_producto</th>				
							<th scope="col">Nit_proveedor</th>
							<th scope="col">Precio_compra</th>
							<th scope="col">Iva_compra</th>
							<th scope="col">Precio_venta</th>

						</tr>
					</thead>
	
				<tbody>
					<%
					ArrayList<Productos> lista = (ArrayList<Productos>) request.getAttribute("lista");
					for (Productos producto : lista) {
					%>
					<tr>
						<td><%=producto.getCodigo_producto()%></td>
						<td><%=producto.getNombre_producto()%></td>				
						<td><%=producto.getNit_proveedor()%></td>		
						<td><%=producto.getPrecio_compra()%></td>
						<td><%=producto.getIva_compra()%></td>
						<td><%=producto.getPrecio_venta()%></td>
						


						<td><a class="btn btn-outline-warning"
							href="Controlador?menu=Productos&accion=Cargar&id=<%=producto.getCodigo_producto()%>">Editar</a>
							<a class="btn btn-outline-danger"
							href="Controlador?menu=Productos&accion=Eliminar&id=<%=producto.getCodigo_producto()%>">Eliminar</a>
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