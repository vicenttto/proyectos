package fantasy_lol.modelo;

/**
 * Clase que representa las estadísticas KDA (Kills, Deaths, Assists) 
 * de un jugador en una partida concreta usando un campeón específico.
 * 
 * Contiene información sobre el ID del jugador, la partida, el campeón, 
 * así como el número de asesinatos, muertes y asistencias.
 * 
 * @autor Vicente y Gonzalo
 */

public class Kda {

	private int id_jugador;
	private int id_partida;
	private int id_campeon;
	private int kills;
	private int deaths;
	private int assists;

	public Kda(int id_jugador, int id_partida, int id_campeon, int kills, int deaths, int assists) {
		super();
		this.id_jugador = id_jugador;
		this.id_partida = id_partida;
		this.id_campeon = id_campeon;
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}

	public int getId_partida() {
		return id_partida;
	}

	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}

	public int getId_campeon() {
		return id_campeon;
	}

	public void setId_campeon(int id_campeon) {
		this.id_campeon = id_campeon;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

}
