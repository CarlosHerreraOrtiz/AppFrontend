<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	
	<head>
		<meta charset="ISO-8859-1">
		<title> Formulario de Ingreso </title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Grupo83">
        <meta name="description" content="Formulario de Inicio">
        <meta name="keywords" content="Login, Acceso, Inicio">
        
        <!-- Link archivo de estilos css -->
        <link rel="stylesheet" href="./CSS/Login.css">		 
		<style type="text/css">  </style> <!--indica que el contenido es CSS -->
        
        <script type="text/javascript"> 
	        function mostrarMensaje() {
	        	alert('Alerta');
	        }
        </script>
	</head>
	<body>
		<div id="contenedor">
            <div id="central">
                <div id="login">
                    <div class="titulo"> Bienvenido </div>
                    <div class="titulo2"> a su Tienda Virtual </div>
                    
                    <form id="loginform" action="./Login_Servlet" method="get">
                        <input type="text" name="txtusuario"  placeholder="Usuario" required>
                        <input type="password" name="txtpassword" placeholder="Contraseña" required>
                        
                        <button type="submit" name="accion" value="Ingresar" title="Ingresar" > Login</button>
                    </form>
                    <div class="pie-form">
                        <a href="#">¿Perdiste tu contraseña?</a>
                        <a href="registrarvista.jsp">¿No tienes Cuenta? Registrate</a>
                        
                    </div>
                </div>
                <div class="inferior">
                	<a href="javascript:history.back()"> Regresar</a>
                </div>
            </div>
        </div>
	</body>
</html>