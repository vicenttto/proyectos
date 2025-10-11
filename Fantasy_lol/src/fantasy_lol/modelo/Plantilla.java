package fantasy_lol.modelo;

/**
 * Clase que crea la plantilla de un equipo personal (Fantasy Team) en Fantasy LOL.
 * 
 * Relaciona el equipo fantasy con sus jugadores seleccionados.
 * Cada registro indica qué jugador pertenece a qué equipo fantasy.
 * 
 * @autor Vicente y Gonzalo
 */

public class Plantilla {

	private int id_fantasy;
	private int id_jugador;

	public Plantilla(int id_fantasy, int id_jugador) {
		super();
		this.id_fantasy = id_fantasy;
		this.id_jugador = id_jugador;
	}

	public int getId_fantasy() {
		return id_fantasy;
	}

	public void setId_fantasy(int id_fantasy) {
		this.id_fantasy = id_fantasy;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}

}
