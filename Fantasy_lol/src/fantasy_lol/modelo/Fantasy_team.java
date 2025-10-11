package fantasy_lol.modelo;

/**
 * Clase que crea un equipo personal por usuario (Fantasy Team) en Fantasy LOL.
 * 
 * Contiene el ID del equipo de fantasía, el ID del usuario propietario y el nombre del equipo.
 * 
 * @author Vicente y Gonzalo
 */

public class Fantasy_team {

	private int id_fantasy;
	private int id_usuario;
	private String nombre;

	public Fantasy_team(int id_fantasy, int id_usuario, String nombre) {
		super();
		this.id_fantasy = id_fantasy;
		this.id_usuario = id_usuario;
		this.nombre = nombre;
	}

	public int getId_fantasy() {
		return id_fantasy;
	}

	public void setId_fantasy(int id_fantasy) {
		this.id_fantasy = id_fantasy;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
