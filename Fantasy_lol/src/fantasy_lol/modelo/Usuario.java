package fantasy_lol.modelo;

/**
 * Clase que representa un usuario en Fantasy LOL.
 * 
 * Contiene información básica como el ID, nombre, email y contraseña del usuario.
 * 
 * @autor Vicente y Gonzalo
 */

public class Usuario {

	private int id_usuario;
	private String nombre;
	private String email;
	private String contrasena;

	public Usuario(int id_usuario, String nombre, String email, String contrasena) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
