package bibliotecaddbb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BibliotecaMain {

	public static void main(String[] args) {
		BibliotecaMain bibliotecaMain = new BibliotecaMain();
		try {
			bibliotecaMain.iniciarMenu();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private List<Biblioteca> obtenerBibliotecas() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
		String username = "root";
		String password = "password";

		Connection connection = null;
		PreparedStatement prepareStament = null;
		ResultSet rs = null;

		List<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();

		try {

			System.out.println("Estableciendo conexión");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión establecida");

			prepareStament = connection.prepareStatement("SELECT * FROM TB_BIBLIOTECA");

			rs = prepareStament.executeQuery();

			while (rs.next()) {
				long id = (rs.getLong("id"));
				String nombreBiblioteca = (rs.getString("nombre"));
				int bibliotecaDireccion = (rs.getInt("fk_direccion"));

				Biblioteca biblioteca = new Biblioteca(id, nombreBiblioteca, bibliotecaDireccion);
				bibliotecas.add(biblioteca);
			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error " + e.getMessage());

		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStament != null)
					prepareStament.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bibliotecas;
	}

	private void iniciarMenu() throws SQLException {

		List<Biblioteca> bibliotecas = obtenerBibliotecas();
		int opcion = 0;
		Biblioteca bibliotecaElegida = null;
		do {
			int i = 1;
			for (Biblioteca biblioteca : bibliotecas) {
				System.out.println(i + ". " + biblioteca.getNombreBiblioteca());
				i++;
			}
			opcion = Utilidades.pintarMenu(bibliotecas.size() + 1 + ". Salir");
			bibliotecaElegida = bibliotecas.get(opcion - 1);

			switch (opcion) {
			case 1:
				submenuLibreria(bibliotecaElegida);
				break;
			case 2:
				submenuLibreria(bibliotecaElegida);
				break;
			case 3:
				submenuLibreria(bibliotecaElegida);
				break;
			case 4:
				System.out.println("Adiós");
				break;
			default:
				System.out.println("Opción incorrecta");
			}
		} while (opcion != bibliotecas.size() + 1);

	}

	private void submenuLibreria(Biblioteca biblioteca) throws SQLException {
		int opcion = 0;
		do {
			// Pinta Menu
			String[] preguntas = { "1. Mostrar info biblioteca", "2. Mostrar libros de la biblioteca ",
					"3. Buscar libro", "4. Insertar libro", "5. Modificar.", "6.Borrar", "7.Volver" };
			opcion = Utilidades.pintarMenu(preguntas, "Elige una opcion");

			switch (opcion) {
			case 1:
				infoBiblioteca(biblioteca);
				break;
			case 2:
				catalogoBiblioteca(biblioteca);
				break;
			case 3:
				buscarLibro(biblioteca);
				break;
			case 4:
				insertarLibro(biblioteca);
				break;
			case 5:
				modificarLibro(biblioteca);
				break;
			case 6: borrarLibro(biblioteca); break;
			case 7: iniciarMenu(); break;
			default:
				System.out.println("Opción incorrecta");
			}

		} while (opcion != 7);

	}

	private void infoBiblioteca(Biblioteca biblioteca) {
		String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
		String username = "root";
		String password = "password";

		Connection connection = null;
		PreparedStatement prepareStament = null;
		ResultSet rs = null;

		int direccionBiblioteca = biblioteca.getBibliotecaDireccion();
		try {

			System.out.println("Estableciendo conexión");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión establecida");

			prepareStament = connection.prepareStatement("SELECT ID, TIPO_DIRECCION, DIRECCION, PROVINCIA, CIUDAD, CP FROM TB_DIRECCION WHERE ID=?");
			prepareStament.setInt(1, direccionBiblioteca);

			rs = prepareStament.executeQuery();

			while (rs.next()) {
				long id = (rs.getLong("id"));
				String tipoDireccion = (rs.getString("tipo_direccion"));
				String direccion = (rs.getString("direccion"));
				String ciudad = (rs.getString("ciudad"));
				String provincia = (rs.getString("provincia"));
				int cp = (rs.getInt("cp"));

				Direcciones direccionBiblio = new Direcciones(id, tipoDireccion, direccion, ciudad, provincia, cp);
				System.out.println(biblioteca.getNombreBiblioteca() + "\n" + direccionBiblio);

			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error " + e.getMessage());

		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStament != null)
					prepareStament.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void catalogoBiblioteca(Biblioteca biblioteca) {
		String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
		String username = "root";
		String password = "password";

		Connection connection = null;
		PreparedStatement prepareStament = null;
		ResultSet rs = null;

		long fk_biblioteca = biblioteca.getId();
		try {

			System.out.println("Estableciendo conexión");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión establecida");

			prepareStament = connection.prepareStatement("SELECT ID, TIPO_DIRECCION, DIRECCION, PROVINCIA, CIUDAD, CP FROM TB_LIBROS WHERE FK_BIBLIOTECA=?");
			prepareStament.setInt(1, (int) fk_biblioteca);

			rs = prepareStament.executeQuery();

			while (rs.next()) {
				long id = (rs.getLong("id"));
				String titulo = (rs.getString("titulo"));
				String autor = (rs.getString("autor"));
				String codigoLibro = (rs.getString("ISBN"));
				long libroBiblioteca = (rs.getLong("fk_biblioteca"));

				Libros ejemplar = new Libros(id, titulo, autor, codigoLibro, libroBiblioteca);
				System.out.println(ejemplar);

			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error " + e.getMessage());

		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStament != null)
					prepareStament.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Libros buscarLibro(Biblioteca biblioteca) {
		String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
		String username = "root";
		String password = "password";

		Connection connection = null;
		PreparedStatement prepareStament = null;
		ResultSet rs = null;

		long fk_biblioteca = biblioteca.getId();
		String ejemplarBuscado = Utilidades.pideDatoTexto("Introduce el título del libro");
		boolean ejemplarExiste = false;
		Libros ejemplar = null;

		try {

			System.out.println("Estableciendo conexión");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión establecida");

			prepareStament = connection
					.prepareStatement("SELECT ID, TIPO_DIRECCION, DIRECCION, PROVINCIA, CIUDAD, CP FROM TB_LIBROS WHERE FK_BIBLIOTECA=? AND TITULO=?");
			prepareStament.setInt(1, (int) fk_biblioteca);
			prepareStament.setString(2, ejemplarBuscado);

			rs = prepareStament.executeQuery();

			while (rs.next()) {
				long id = (rs.getLong("id"));
				String titulo = (rs.getString("titulo"));
				String autor = (rs.getString("autor"));
				String codigoLibro = (rs.getString("ISBN"));
				long libroBiblioteca = (rs.getLong("fk_biblioteca"));

				ejemplar = new Libros(id, titulo, autor, codigoLibro, libroBiblioteca);
				System.out.println(ejemplar);
				ejemplarExiste = true;
			}

			if (!ejemplarExiste) {
				System.out.println("El ejemplar no existe en dicha biblioteca");
			}

		} catch (SQLException e) {
			System.err.println("Ha habido un error " + e.getMessage());

		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStament != null)
					prepareStament.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ejemplar;
	}

	private void insertarLibro(Biblioteca biblioteca) {
		String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
		String username = "root";
		String password = "password";

		Connection connection = null;
		PreparedStatement prepareStament = null;
		ResultSet rs = null;

		long fk_biblioteca = biblioteca.getId();
		String tituloLibro = Utilidades.pideDatoTexto("Introduce el título del libro");
		String autorLibro = Utilidades.pideDatoTexto("Introduce el autor del libro");
		String isbnLibro = Utilidades.pideDatoTexto("Introduce el isbn del libro");

		try {

			System.out.println("Estableciendo conexión");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión establecida");

			String insert = "INSERT INTO TB_LIBROS " + "(TITULO,AUTOR,ISBN,FK_BIBLIOTECA) " + "VALUES (?,?,?,?)";

			prepareStament = connection.prepareStatement(insert);
			prepareStament.setString(1, tituloLibro);
			prepareStament.setString(2, autorLibro);
			prepareStament.setString(3, isbnLibro);
			prepareStament.setLong(4, fk_biblioteca);

			boolean insertado = prepareStament.execute();
			System.out.println("Insertado: " + insertado);

		} catch (SQLException e) {
			System.err.println("Ha habido un error " + e.getMessage());

		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStament != null)
					prepareStament.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void modificarLibro(Biblioteca biblioteca) {

		Libros libroModificado = buscarLibro(biblioteca);

		if (libroModificado != null) {

			String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
			String username = "root";
			String password = "password";

			Connection connection = null;
			PreparedStatement prepareStament = null;
			ResultSet rs = null;

			try {

				System.out.println("Estableciendo conexión");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexión establecida");
				
				String tituloACambiar = Utilidades.pideDatoTexto("Introduce nuevo titulo, si no quieres cambiar este campo pulsa Enter");
				String autorACambiar = Utilidades.pideDatoTexto("Introduce nuevo autor, si no quieres cambiar este campo pulsa Enter");
				String isbnACambiar = Utilidades.pideDatoTexto("Introduce nuevo ISBN, si no quieres cambiar este campo pulsa Enter");
				
				 StringBuilder updateQuery = new StringBuilder("UPDATE TB_LIBROS SET ");
		            List<Object> params = new ArrayList<>();

		            if (!tituloACambiar.isEmpty()) {
		                updateQuery.append("TITULO=?, ");
		                params.add(tituloACambiar);
		            }
		            if (!autorACambiar.isEmpty()) {
		                updateQuery.append("AUTOR=?, ");
		                params.add(autorACambiar);
		            }
		            if (!isbnACambiar.isEmpty()) {
		                updateQuery.append("ISBN=?, ");
		                params.add(isbnACambiar);
		            }	
	
		            // Eliminar la última coma y espacio
		            updateQuery.setLength(updateQuery.length() - 2);

		            // Agregar la cláusula WHERE para identificar el libro específico
		            updateQuery.append(" WHERE TITULO=?");
		            params.add(libroModificado.getTitulo());

		            prepareStament = connection.prepareStatement(updateQuery.toString());

		            // Establecer los valores de los parámetros
		            for (int i = 0; i < params.size(); i++) {
		                prepareStament.setObject(i + 1, params.get(i));
		            }

		            // Ejecutar la actualización
		            int elementosModificados = prepareStament.executeUpdate();


			} catch (SQLException e) {
				System.err.println("Ha habido un error " + e.getMessage());

			} finally {

				try {
					if (connection != null) {
						connection.close();
					}
					if (prepareStament != null)
						prepareStament.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void borrarLibro(Biblioteca biblioteca) {

		Libros libroBorrar = buscarLibro(biblioteca);
		System.out.println("j"+libroBorrar);
		
		if (libroBorrar != null) {

			String url = "jdbc:mysql://localhost:3306/curso?serverTimezone=Europe/Madrid";
			String username = "root";
			String password = "password";

			Connection connection = null;
			PreparedStatement prepareStament = null;
			ResultSet rs = null;

			try {

				System.out.println("Estableciendo conexión");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexión establecida");

				prepareStament = connection
						.prepareStatement("DELETE FROM TB_LIBROS WHERE TITULO=?");
				prepareStament.setString(1, libroBorrar.getTitulo());
				
				int elementosEliminados = prepareStament.executeUpdate();
				
				System.out.println("Se ha eliminado: " + elementosEliminados + " libro/s.");
			} catch (SQLException e) {
				System.err.println("Ha habido un error " + e.getMessage());

			} finally {

				try {
					if (connection != null) {
						connection.close();
					}
					if (prepareStament != null)
						prepareStament.close();
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("El libro no se puede eliminar porque no existe.");
		}
	}

}
