package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ventana de registro de nuevos usuarios en Fantasy LOL.
 * 
 * Permite registrar un nuevo usuario introduciendo nombre, email y contraseña.
 * 
 * @author Vicente y Gonzalo
 */
public class VentanaRegistro extends JFrame {

    private JTextField txtNombre;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;

    /**
     * Constructor que inicializa la ventana de registro de usuario.
     */
    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel Título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 30, 30));
        panelTitulo.setLayout(new BorderLayout());
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JLabel lblTitulo = new JLabel("Registro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        add(panelTitulo, BorderLayout.NORTH);

        // Panel de Campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtContrasena = new JPasswordField();

        configurarCampo(txtNombre);
        configurarCampo(txtEmail);
        configurarCampo(txtContrasena);

        Dimension fieldSize = new Dimension(250, 30);
        txtNombre.setPreferredSize(fieldSize);
        txtEmail.setPreferredSize(fieldSize);
        txtContrasena.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(crearEtiqueta("Nombre de Usuario:"), gbc);
        gbc.gridy++;
        panelCampos.add(txtNombre, gbc);

        gbc.gridy++;
        panelCampos.add(crearEtiqueta("Email:"), gbc);
        gbc.gridy++;
        panelCampos.add(txtEmail, gbc);

        gbc.gridy++;
        panelCampos.add(crearEtiqueta("Contraseña:"), gbc);
        gbc.gridy++;
        panelCampos.add(txtContrasena, gbc);

        add(panelCampos, BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnVolver = new JButton("Volver");

        personalizarBoton(btnAceptar, new Color(34, 139, 34)); // Verde aceptar
        personalizarBoton(btnVolver, new Color(178, 34, 34));   // Rojo volver

        Dimension btnSize = new Dimension(300, 60);
        btnAceptar.setPreferredSize(btnSize);
        btnVolver.setPreferredSize(btnSize);

        panelBotones.add(btnAceptar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción de los botones
        btnAceptar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> {
            new VentanaInicio();
            dispose();
        });

        setVisible(true);
    }

    /**
     * Crea una etiqueta estilizada.
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
     * Personaliza un botón con un estilo dado.
     * 
     * @param boton Botón a personalizar.
     * @param colorFondo Color de fondo del botón.
     */
    private void personalizarBoton(JButton boton, Color colorFondo) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        boton.setMargin(new Insets(10, 20, 10, 20));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     * 
     * Valida que los campos estén completos y que el email no esté registrado.
     */
    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = new MySQLConnection().mySQLConnect()) {

            // Validar si el email ya está registrado
            String sqlCheck = "SELECT * FROM USUARIO WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(sqlCheck);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "El email ya está registrado. Usa otro email.", "Error", JOptionPane.ERROR_MESSAGE);
                txtNombre.setText("");
                txtEmail.setText("");
                txtContrasena.setText("");
                return;
            }

            // Insertar nuevo usuario
            String sqlInsert = "INSERT INTO USUARIO (nombre, email, contrasena) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(sqlInsert);
            insertStmt.setString(1, nombre);
            insertStmt.setString(2, email);
            insertStmt.setString(3, contrasena);

            int filasAfectadas = insertStmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
                new VentanaInicio();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
