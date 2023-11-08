package bibliotecaddbb;

public class Libros {
	
	private long id;
	private String titulo;
	private String autor;
	private String isbn;
	private long libroBiblioteca;
	
	/**
	 * @param id
	 * @param titulo
	 * @param autor
	 * @param isbn
	 * @param libroBiblioteca
	 */
	public Libros(long id, String titulo, String autor, String isbn, long libroBiblioteca) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.libroBiblioteca = libroBiblioteca;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public long getLibroBiblioteca() {
		return libroBiblioteca;
	}

	public void setLibroBiblioteca(int libroBiblioteca) {
		this.libroBiblioteca = libroBiblioteca;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Libros [" + (titulo != null ? "titulo=" + titulo + ", " : "")
				+ (autor != null ? "autor=" + autor + ", " : "") + "isbn=" + isbn + "]";
	}
	
	

}
