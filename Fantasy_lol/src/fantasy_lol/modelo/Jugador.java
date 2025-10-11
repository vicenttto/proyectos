package fantasy_lol.modelo;

/**
 * Clase que crea un jugador en Fantasy LOL.
 * 
 * Contiene información sobre el ID del jugador, su nombre, rol y el equipo real al que pertenece.
 * 
 * @autor Vicente y Gonzalo
 */

public class Jugador {

	private int id_jugador;
	private String nombre;
	private String rol;
	private int id_equipo;

	public Jugador(int id_jugador, String nombre, String rol, int id_equipo) {
		super();
		this.id_jugador = id_jugador;
		this.nombre = nombre;
		this.rol = rol;
		this.id_equipo = id_equipo;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

}
