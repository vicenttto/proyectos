package fantasy_lol.modelo;

/**
 * Clase que crea un equipo en Fantasy LOL.
 * 
 * Contiene información básica como su ID y nombre.
 * 
 * @author Vicente y Gonzalo
 */

public class Equipo {

	private int id_equipo;
	private String nombre;

	public Equipo(int id_equipo, String nombre) {
		super();
		this.id_equipo = id_equipo;
		this.nombre = nombre;
	}

	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
