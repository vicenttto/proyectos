package fantasy_lol.interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal tras iniciar sesión en Fantasy LOL.
 * 
 * Muestra las opciones principales del sistema: partidas, jugadores y gestión de equipo.
 * 
 * @author Vicente y Gonzalo
 */
public class VentanaPrincipal extends JFrame {

    private String nombreUsuario;
    private int idUsuarioActual;

    /**
     * Constructor que inicializa la ventana principal con las opciones de navegación.
     * 
     * @param nombreUsuario Nombre del usuario logueado.
     * @param idUsuarioActual ID del usuario logueado.
     */
    public VentanaPrincipal(String nombreUsuario, int idUsuarioActual) {
        this.nombreUsuario = nombreUsuario;
        this.idUsuarioActual = idUsuarioActual;

        setTitle("Fantasy LOL - Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel Título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(30, 30, 30));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel lblTitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);

        JLabel lblSubtitulo = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblSubtitulo.setForeground(Color.LIGHT_GRAY);

        panelTitulo.add(lblTitulo, BorderLayout.NORTH);
        panelTitulo.add(lblSubtitulo, BorderLayout.SOUTH);

        add(panelTitulo, BorderLayout.NORTH);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 20, 20));
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnVerPartidas = crearBoton("Partidas", new Color(70, 130, 180));
        JButton btnVerEquipos = crearBoton("Jugadores", new Color(34, 139, 34));
        JButton btnGestionarFantasy = crearBoton("Gestionar Mi Fantasy Team", new Color(218, 165, 32));

        panelBotones.add(btnVerPartidas);
        panelBotones.add(btnVerEquipos);
        panelBotones.add(btnGestionarFantasy);

        // Panel Central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(30, 30, 30));
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        // Panel Inferior (Cerrar sesión y salir)
        JPanel panelAbajo = new JPanel(new FlowLayout());
        panelAbajo.setBackground(new Color(30, 30, 30));

        JButton btnCerrarSesion = crearBoton("Cerrar Sesión", new Color(128, 0, 128));
        JButton btnSalir = crearBoton("Salir", new Color(178, 34, 34));

        panelCerrarAbajo(btnCerrarSesion);
        panelCerrarAbajo(btnSalir);

        panelAbajo.add(btnCerrarSesion);
        panelAbajo.add(btnSalir);

        add(panelAbajo, BorderLayout.SOUTH);

        // Acciones de los botones
        btnVerPartidas.addActionListener(e -> new VerPartidas());
        btnVerEquipos.addActionListener(e -> new VentanaJugadores());
        btnGestionarFantasy.addActionListener(e -> new GestionarFantasy(idUsuarioActual));
        btnCerrarSesion.addActionListener(e -> {
            new VentanaInicio();
            dispose();
        });
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    /**
     * Crea y personaliza un botón.
     * 
     * @param texto Texto del botón.
     * @param colorFondo Color de fondo del botón.
     * @return Botón configurado.
     */
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        boton.setMargin(new Insets(10, 20, 10, 20));
        boton.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension btnSize = new Dimension(300, 60);
        boton.setPreferredSize(btnSize);

        // Hover efecto
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });

        return boton;
    }

    /**
     * Ajusta el tamaño de los botones del panel inferior.
     * 
     * @param boton Botón a configurar.
     */
    private void panelCerrarAbajo(JButton boton) {
        boton.setPreferredSize(new Dimension(200, 50));
    }
}
