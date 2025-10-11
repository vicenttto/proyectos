package fantasy_lol.interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de inicio de la aplicación Fantasy LOL.
 * 
 * Muestra un título y dos botones para iniciar sesión o registrarse.
 * Cada botón abre una ventana distinta y cierra la actual.
 * 
 * @author Vicente y Gonzalo
 */
public class VentanaInicio extends JFrame {

    /**
     * Constructor que inicializa la ventana de inicio.
     */
    public VentanaInicio() {
        setTitle("Fantasy LOL");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel para el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 30, 30));
        panelTitulo.setLayout(new BorderLayout());
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel lblTitulo = new JLabel("Bienvenido a Fantasy LOL", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        add(panelTitulo, BorderLayout.NORTH);

        // Panel de Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(30, 30, 30));
        panelBotones.setLayout(new GridLayout(2, 1, 20, 20));

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistro = new JButton("Registrarse");

        // Personalizar botones
        Dimension btnSize = new Dimension(200, 50);
        btnLogin.setPreferredSize(btnSize);
        btnRegistro.setPreferredSize(btnSize);

        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnRegistro.setBackground(new Color(34, 139, 34));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFocusPainted(false);
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);

        // Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(30, 30, 30));
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        // Acciones
        btnRegistro.addActionListener(e -> {
            new VentanaRegistro();
            dispose();
        });

        btnLogin.addActionListener(e -> {
            new VentanaLogin();
            dispose();
        });

        setVisible(true);
    }
}
