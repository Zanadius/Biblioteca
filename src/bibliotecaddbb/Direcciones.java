package bibliotecaddbb;

public class Direcciones {

	private long id;
	private String tipoDireccion;
	private String direccion;
	private String ciudad;
	private String provincia;
	private int cp;
	
	/**
	 * @param id
	 * @param tipoDireccion
	 * @param direccion
	 * @param ciudad
	 * @param provincia
	 * @param cp
	 */
	public Direcciones(long id, String tipoDireccion, String direccion, String ciudad, String provincia, int cp) {
		super();
		this.id = id;
		this.tipoDireccion = tipoDireccion;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.cp = cp;
	}

	public String getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(String tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Direcciones [" + (tipoDireccion != null ? "tipoDireccion=" + tipoDireccion + ", " : "")
				+ (direccion != null ? "direccion=" + direccion + ", " : "")
				+ (ciudad != null ? "ciudad=" + ciudad + ", " : "")
				+ (provincia != null ? "provincia=" + provincia + ", " : "") + "cp=" + cp + "]";
	}
	
	
	
	
}
