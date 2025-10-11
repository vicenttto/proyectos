package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ventana de inicio de sesión de usuarios.
 * 
 * Permite al usuario introducir su email y contraseña para acceder al sistema.
 * 
 * @author Vicente y Gonzalo
 */
public class VentanaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtContrasena;

    /**
     * Constructor que inicializa la ventana de login.
     */
    public VentanaLogin() {
        setTitle("Login de Usuario");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 30, 30));
        panelTitulo.setLayout(new BorderLayout());
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        add(panelTitulo, BorderLayout.NORTH);

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtEmail = new JTextField();
        txtContrasena = new JPasswordField();

        configurarCampo(txtEmail);
        configurarCampo(txtContrasena);

        Dimension fieldSize = new Dimension(250, 30);
        txtEmail.setPreferredSize(fieldSize);
        txtContrasena.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(crearEtiqueta("Email:"), gbc);
        gbc.gridy++;
        panelCampos.add(txtEmail, gbc);

        gbc.gridy++;
        panelCampos.add(crearEtiqueta("Contraseña:"), gbc);
        gbc.gridy++;
        panelCampos.add(txtContrasena, gbc);

        add(panelCampos, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnLogin = crearBoton("Login", new Color(70, 130, 180));
        JButton btnVolver = crearBoton("Volver", new Color(178, 34, 34));

        panelBotones.add(btnLogin);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acciones botones
        btnLogin.addActionListener(e -> loginUsuario());
        btnVolver.addActionListener(e -> {
            new VentanaInicio();
            dispose();
        });

        setVisible(true);
    }

    /**
     * Crea una etiqueta.
     * 
     * @param texto Texto de la etiqueta.
     * @return Etiqueta configurada.
     */
    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Verdana", Font.PLAIN, 18));
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        return etiqueta;
    }

    /**
     * Configura el estilo de un campo de texto.
     * 
     * @param campo Campo de texto a configurar.
     */
    private void configurarCampo(JTextField campo) {
        campo.setBackground(new Color(50, 50, 50));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Verdana", Font.PLAIN, 18));
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

    /**
     * Método que realiza el login del usuario validando los datos de la base de datos.
     */
    private void loginUsuario() {
        String email = txtEmail.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (email.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = new MySQLConnection().mySQLConnect()) {
            String sql = "SELECT id_usuario, nombre FROM USUARIO WHERE email = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nombreUsuario = rs.getString("nombre");

                new VentanaPrincipal(nombreUsuario, idUsuario);
                dispose(); // Cerrar ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos 🚫", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
