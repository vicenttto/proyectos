package fantasy_lol.modelo;

/**
 * Clase que pertenece a una partida en Fantasy LOL.
 * 
 * Contiene información sobre el ID de la partida, fecha,
 * ID de los equipos participantes y el ID del equipo ganador.
 * 
 * El equipo ganador puede ser nulo si la partida no se ha jugado aún.
 * 
 * @autor Vicente y Gonzalo
 */

import java.time.LocalDate;

public class Partida {

	private int id_partida;
	private LocalDate fecha;
	private int id_equipo_azul;
	private int id_equipo_rojo;
	private Integer id_equipo_ganador;

	public Partida(int id_partida, LocalDate fecha, int id_equipo_azul, int id_equipo_rojo, Integer id_equipo_ganador) {
		super();
		this.id_partida = id_partida;
		this.fecha = fecha;
		this.id_equipo_azul = id_equipo_azul;
		this.id_equipo_rojo = id_equipo_rojo;
		this.id_equipo_ganador = id_equipo_ganador;
	}

	public Partida(int id_partida, LocalDate fecha, int id_equipo_azul, int id_equipo_rojo) {
		super();
		this.id_partida = id_partida;
		this.fecha = fecha;
		this.id_equipo_azul = id_equipo_azul;
		this.id_equipo_rojo = id_equipo_rojo;
	}


	public int getId_partida() {
		return id_partida;
	}

	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getId_equipo_azul() {
		return id_equipo_azul;
	}

	public void setId_equipo_azul(int id_equipo_azul) {
		this.id_equipo_azul = id_equipo_azul;
	}

	public int getId_equipo_rojo() {
		return id_equipo_rojo;
	}

	public void setId_equipo_rojo(int id_equipo_rojo) {
		this.id_equipo_rojo = id_equipo_rojo;
	}

	public Integer getId_equipo_ganador() {
		return id_equipo_ganador;
	}

	public void setId_equipo_ganador(Integer id_equipo_ganador) {
		this.id_equipo_ganador = id_equipo_ganador;
	}
}
