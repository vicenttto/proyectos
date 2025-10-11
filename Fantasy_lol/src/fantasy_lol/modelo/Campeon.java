package fantasy_lol.modelo;

/**
 * Clase que crea un campeón en Fantasy LOL.
 * 
 * Contiene información básica como su ID y nombre.
 * 
 * @author Vicente y Gonzalo
 */


public class Campeon {

	private int id_campeon;
	private String nombre;

	public Campeon(int id_campeon, String nombre) {
		super();
		this.id_campeon = id_campeon;
		this.nombre = nombre;
	}

	public int getId_campeon() {
		return id_campeon;
	}

	public void setId_campeon(int id_campeon) {
		this.id_campeon = id_campeon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
