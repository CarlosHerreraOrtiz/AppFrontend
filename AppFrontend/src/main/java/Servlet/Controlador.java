package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter write = null;

	public Controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");

		switch (menu) {
		case "Principal":
			request.getRequestDispatcher("/Prueba.jsp").forward(request, response);
			break;
		case "Usuarios":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Usuarios> lista = TestJSON.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setApellido_usuario(request.getParameter("txtapellido"));
				
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));
				
				usuario.setTipo_usuario(Integer.parseInt(request.getParameter("txttipo")));
				usuario.setEstado_usuario(Integer.parseInt(request.getParameter("txtestado")));

				int respuesta = 0;
				try {
					respuesta = TestJSON.postJSON(usuario);

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Actualizar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setApellido_usuario(request.getParameter("txtapellido"));				
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));
				usuario.setTipo_usuario(Integer.parseInt(request.getParameter("txttipo")));
				usuario.setEstado_usuario(Integer.parseInt(request.getParameter("txtestado")));

				int respuesta = 0;
				try {
					respuesta = TestJSON.putJSON(usuario, usuario.getCedula_usuario());
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					ArrayList<Usuarios> lista1 = TestJSON.getJSON();
					System.out.println("Parametro: " + id);
					for (Usuarios usuarios : lista1) {
						if (usuarios.getCedula_usuario() == id) {
							request.setAttribute("usuarioSeleccionado", usuarios);
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			else if (accion.equals("Eliminar")) {
				long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
			break;
		case "Clientes":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Clientes> lista = TestJSON_Clientes.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setApellido_cliente(request.getParameter("txtapellido"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));				
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));


				int respuesta = 0;
				try {
					respuesta = TestJSON_Clientes.postJSON(cliente);

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Actualizar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setApellido_cliente(request.getParameter("txtapellido"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));				
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));


				int respuesta = 0;
				try {
					respuesta = TestJSON_Clientes.putJSON(cliente, cliente.getCedula_cliente());
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					ArrayList<Clientes> lista1 = TestJSON_Clientes.getJSON();
					System.out.println("Parametro: " + id);
					for (Clientes clientes : lista1) {
						if (clientes.getCedula_cliente() == id) {
							request.setAttribute("clienteSeleccionado", clientes);
							request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			else if (accion.equals("Eliminar")) {
				long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSON_Clientes.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
			break;
			
		case "Proveedores":

			if (accion.equals("Listar")) {
				try {
					ArrayList<Proveedores> lista = TestJSON_Proveedores.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNit_proveedor(Long.parseLong(request.getParameter("txtnit")));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));				
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));


				int respuesta = 0;
				try {
					respuesta = TestJSON_Proveedores.postJSON(proveedor);

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Actualizar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNit_proveedor(Long.parseLong(request.getParameter("txtnit")));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));				
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));


				int respuesta = 0;
				try {
					respuesta = TestJSON_Proveedores.putJSON(proveedor, proveedor.getNit_proveedor());
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				long id = Long.parseLong(request.getParameter("id"));
				try {
					ArrayList<Proveedores> lista1 = TestJSON_Proveedores.getJSON();
					System.out.println("Parametro: " + id);
					for (Proveedores proveedores : lista1) {
						if (proveedores.getNit_proveedor() == id) {
							request.setAttribute("proveedorSeleccionado", proveedores);
							request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			else if (accion.equals("Eliminar")) {
				long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSON_Proveedores.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	
		
			request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
			break;
		case "Productos":
			request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			break;
		case "Ventas":
			request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
			break;
		}

	}
}