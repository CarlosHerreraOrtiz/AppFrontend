package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter write = null;

	// *****variables generales dentro de la clase contralador
	// *******
	long subtotal = 0, totalapagar = 0;
	long codProducto = 0, precio = 0, valor_iva = 0, iva = 0, subtotaliva = 0, acusubtotal = 0;
	long numfac = 0;
	int cantidad = 0, item = 0;
	String descripcion, cedulaCliente;
	List<Detalle_Venta> listaVentas = new ArrayList<>();
	Usuarios usuarios = new Usuarios();
	Detalle_Venta detalle_venta = new Detalle_Venta();

	// ***************************

	
	public Controlador() {
		super();
	}

	// ****Metodos locales para buscar clientes y productos ******
		public void buscarCliente(Long id, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				ArrayList<Clientes> listac = TestJSON_Clientes.getJSON();
				for (Clientes clientes : listac) {
					if (clientes.getCedula_cliente() == (id)) {
						request.setAttribute("clienteSeleccionado", clientes);//temporal
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void buscarProducto(Long cod, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				ArrayList<Productos> listap = TestJSON_Productos.getJSON();
				for (Productos productos : listap) {
					if (productos.getCodigo_producto() == (cod)) {
						request.setAttribute("productoSeleccionado", productos); // envio a ventas
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void buscarFactura(String numFact, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			if (numFact == null) {
				numfac = Long.parseLong(numFact) + 1; // variable declarada arriba con valor 0
				//
			} else {
				numfac = Long.parseLong(numFact) + 1; // variable declarada arriba con valor 0
			}
			request.setAttribute("numerofactura", numfac);

		}

		public void grabarDetalle(Long numFact, HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			for (int i = 0; i < listaVentas.size(); i++) {
				detalle_venta = new Detalle_Venta();
				detalle_venta.setCodigo_detalle_venta(String.valueOf(i + 1));
				detalle_venta.setCodigo_venta(numFact);
				detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
				detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
				detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
				detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
				detalle_venta.setValor_iva(listaVentas.get(i).getValor_iva());

				int respuesta = 0;
				try {
					respuesta = TestJSON_Detalle_Venta.postJSON(detalle_venta);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
						// linea de codigo para que vuelva a cargar la venta
						System.out.println("Registros Grabados detalle ventas");
					} else {
						write.println("Error Detalle venta" + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				listaVentas.clear();
				item = 0;
				totalapagar = 0;
				subtotal = 0;
				valor_iva = 0;
				acusubtotal = 0;
				subtotaliva = 0;
				totalapagar = 0;

			}
		}
		// ***************************
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");

		// ********variable para recibir la cedula del
		// usuario********
		// String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		// usuarios.setCedula_usuario(Long.parseLong(cedula_usuario_activo));
		 //request.setAttribute("usuarioSeleccionado", usuarios); //se envia a ventas
		// *****************************

		
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
			
			if (accion.equals("Listar")) {
				try {
					ArrayList<Productos> lista = TestJSON_Productos.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(Long.parseLong(request.getParameter("txtcodigo_producto")));
				producto.setNombre_producto(request.getParameter("txtnombre_producto"));
				producto.setNit_proveedor(Long.parseLong(request.getParameter("txtnit_proveedor")));				
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecio_compra")));
				producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva_compra")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecio_venta")));


				int respuesta = 0;
				try {
					respuesta = TestJSON_Productos.postJSON(producto);

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
								response);

					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Actualizar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(Long.parseLong(request.getParameter("txtcodigo_producto")));
				producto.setNombre_producto(request.getParameter("txtnombre_producto"));
				producto.setNit_proveedor(Long.parseLong(request.getParameter("txtnit_proveedor")));				
				producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecio_compra")));
				producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva_compra")));
				producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecio_venta")));

				int respuesta = 0;
				try {
					respuesta = TestJSON_Productos.putJSON(producto, producto.getCodigo_producto());
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
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
					ArrayList<Productos> lista1 = TestJSON_Productos.getJSON();
					System.out.println("Parametro: " + id);
					for (Productos productos : lista1) {
						if (productos.getCodigo_producto() == id) {
							request.setAttribute("productoSeleccionado", productos);
							request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
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
					respuesta = TestJSON_Productos.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			break;
			
			
		case "Ventas":

			// ******** enviaremos la cedula del usuario al formulario ventas
			request.setAttribute("usuarioSeleccionado", usuarios);
			// *************************

			// ******* enviaremos la Numero de Factura *******
			request.setAttribute("numerofactura", numfac);
			// *************************
			if (accion.equals("BuscarCliente")) {
				String id = request.getParameter("cedulacliente");// como esta en ventas
				this.buscarCliente(Long.parseLong(id), request, response);
			} else if (accion.equals("BuscarProducto")) {
				String id = request.getParameter("cedulacliente");// como esta en ventas y lo repite
				this.buscarCliente(Long.parseLong(id), request, response);

				String cod = request.getParameter("codigoproducto");// como esta en ventas
				this.buscarProducto(Long.parseLong(cod), request, response);

			} else if (accion.equals("AgregarProducto")) {
				String id = request.getParameter("cedulacliente");// como esta en ventas y lo repite
				this.buscarCliente(Long.parseLong(id), request, response);

				detalle_venta = new Detalle_Venta();
				item++; // contador
				acusubtotal = 0;
				subtotaliva = 0;
				totalapagar = 0;
				codProducto = Long.parseLong(request.getParameter("codigoproducto"));
				descripcion = request.getParameter("nombreproducto");
				precio = Long.parseLong(request.getParameter("precioproducto"));
				cantidad = Integer.parseInt(request.getParameter("cantidadproducto"));
				iva = Long.parseLong(request.getParameter("ivaproducto"));

				subtotal = (precio * cantidad);
				valor_iva = subtotal * iva / 100;
				// almacena temporalmente cada producto
				detalle_venta.setCodigo_detalle_venta(String.valueOf(item));
				detalle_venta.setCodigo_producto(codProducto);
				detalle_venta.setDescripcion_producto(descripcion);
				detalle_venta.setPrecio_producto(precio);
				detalle_venta.setCantidad_producto(cantidad);
				detalle_venta.setCodigo_venta(numfac);
				detalle_venta.setValor_iva(valor_iva);
				detalle_venta.setValor_venta(subtotal);
				listaVentas.add(detalle_venta);

				for (int i = 0; i < listaVentas.size(); i++) {
					acusubtotal += listaVentas.get(i).getValor_venta();
					subtotaliva += listaVentas.get(i).getValor_iva();
				}
				totalapagar = acusubtotal + subtotaliva;
				detalle_venta.setValor_total(totalapagar);
				// una vez hecho todos los calculos ahora hacemos el envio de la info al
				// formulario ventas seccion2
				request.setAttribute("listaventas", listaVentas); //temporal
				request.setAttribute("totalsubtotal", acusubtotal);
				request.setAttribute("totaliva", subtotaliva);
				request.setAttribute("totalapagar", totalapagar);

			} else if (accion.equals("GenerarVenta")) {
				cedulaCliente = request.getParameter("cedulacliente");
				String numFact = request.getParameter("numerofactura");
				Ventas ventas = new Ventas();
				ventas.setCodigo_venta(Long.parseLong(numFact));
				ventas.setCedula_cliente(Long.parseLong(cedulaCliente));
				ventas.setCedula_usuario(usuarios.getCedula_usuario());
				ventas.setIva_venta(subtotaliva);
				ventas.setValor_venta(acusubtotal);
				ventas.setTotal_venta(totalapagar);

				int respuesta = 0;
				try {
					respuesta = TestJSON_Ventas.postJSON(ventas);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						System.out.println("Grabacion Exitosa " + respuesta);
						this.grabarDetalle(ventas.getCodigo_venta(), request, response);
					} else {
						write.println("error ventas");
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				String factura = request.getParameter("numerofactura");
				if (factura == null) {
					factura = "1";
					this.buscarFactura(factura, request, response);
				}

			}

			request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
			break;
			
		case "Reportes":
			int opcion=0;
			
			if (accion.equals("ReporteUsuarios")) {
				opcion=1;
				try {
					ArrayList<Usuarios> lista = TestJSON.getJSON();
					request.setAttribute("listaUsuarios", lista); //envio el arraylist 
					request.setAttribute("opcion", opcion); //variable creada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (accion.equals("ReporteClientes")) {
				opcion=2;
				try {
					ArrayList<Clientes> lista = TestJSON_Clientes.getJSON();
					request.setAttribute("listaClientes", lista); //envio el arraylist 
					request.setAttribute("opcion", opcion); //variable crada
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (accion.equals("ReporteVentas")) {
				opcion=3;
				try {
					ArrayList<Ventas> lista = TestJSON_Ventas.getJSON();
					request.setAttribute("listaVentas", lista); //envio el arraylist 
					request.setAttribute("opcion", opcion); //variable crada
				} catch (Exception e) {
					e.printStackTrace();
			}
			}
			request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
			break;
		}

	}
}