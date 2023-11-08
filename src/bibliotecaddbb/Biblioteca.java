package bibliotecaddbb;

import java.util.Map;

public class Biblioteca {
	
	private long id;
	private String nombreBiblioteca;
	private int bibliotecaDireccion;
	private Map<String, Libros> catalogo;
	
	/**
	 * @param id
	 * @param nombreBiblioteca
	 * @param bibliotecaDireccion
	 */
	public Biblioteca(long id, String nombreBiblioteca, int bibliotecaDireccion) {
		super();
		this.id = id;
		this.nombreBiblioteca = nombreBiblioteca;
		this.bibliotecaDireccion = bibliotecaDireccion;
	}

	/**
	 * @param id
	 * @param nombreBiblioteca
	 * @param bibliotecaDireccion
	 * @param catalogo
	 */
	public Biblioteca(long id, String nombreBiblioteca, int bibliotecaDireccion, Map<String, Libros> catalogo) {
		super();
		this.id = id;
		this.nombreBiblioteca = nombreBiblioteca;
		this.bibliotecaDireccion = bibliotecaDireccion;
		this.catalogo = catalogo;
	}

	public Biblioteca() {
		// TODO Auto-generated constructor stub
	}

	public String getNombreBiblioteca() {
		return nombreBiblioteca;
	}

	public void setNombreBiblioteca(String nombreBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
	}

	public int getBibliotecaDireccion() {
		return bibliotecaDireccion;
	}

	public void setBibliotecaDireccion(int bibliotecaDireccion) {
		this.bibliotecaDireccion = bibliotecaDireccion;
	}

	public long getId() {
		return id;
	}

	public Map<String, Libros> getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Map<String, Libros> catalogo) {
		this.catalogo = catalogo;
	}
	
		

}
