package fantasy_lol.interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para gestionar el equipo de Fantasy LOL.
 * 
 * Permite crear un nuevo equipo, ver el equipo actual o volver al menú anterior.
 * 
 * @author Vicente y Gonzalo
 */
public class GestionarFantasy extends JFrame {

    private int idUsuario; // ID del usuario

    /**
     * Constructor que inicializa la ventana de gestión del equipo.
     * 
     * @param idUsuario ID del usuario que está gestionando su equipo.
     */
    public GestionarFantasy(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Gestionar Mi Fantasy Team");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(30, 30, 30));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel lblTitulo = new JLabel("Gestionar Mi Fantasy Team", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);

        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 20, 20));
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnCrear = crearBoton("Crear Equipo", new Color(70, 130, 180)); // Azul
        JButton btnVer = crearBoton("Ver Equipo", new Color(34, 139, 34)); // Verde
        JButton btnVolver = crearBoton("Volver", new Color(178, 34, 34)); // Rojo

        panelBotones.add(btnCrear);
        panelBotones.add(btnVer);
        panelBotones.add(btnVolver);

        // Panel central para centrar botones
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(30, 30, 30));
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        // Acciones de los botones
        btnCrear.addActionListener(e -> new CrearEquipo(idUsuario)); // Crear nuevo equipo
        btnVer.addActionListener(e -> new VerEquipo(idUsuario));     // Ver equipo existente
        btnVolver.addActionListener(e -> dispose());                 // Cerrar ventana

        setVisible(true);
    }

    /**
     * Crea y personaliza un botón con el texto y color de fondo indicados.
     * 
     * @param texto Texto del botón.
     * @param colorFondo Color de fondo del botón.
     * @return Botón personalizado.
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
        Dimension btnSize = new Dimension(300, 60); // Botón grande
        boton.setPreferredSize(btnSize);

        // Efecto hover
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
}
