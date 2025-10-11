package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ventana para crear un nuevo equipo Fantasy LOL.
 * 
 * Permite al usuario crear su equipo o renombrarlo si ya existe.
 * Gestiona las operaciones en la base de datos.
 * 
 * @author Vicente y Gonzalo
 */
public class CrearEquipo extends JFrame {

    private int idUsuario;

    /**
     * Constructor que inicializa la ventana para crear o renombrar un equipo.
     * 
     * @param idUsuario ID del usuario que crea el equipo.
     */
    public CrearEquipo(int idUsuario) {
        this.idUsuario = idUsuario;

        if (tieneEquipo(idUsuario)) {
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "Ya tienes un equipo creado. ¿Quieres cambiarle el nombre?",
                    "Equipo existente",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                mostrarVentanaRenombrar();
            }

            dispose(); // Cierra esta ventana
            return;
        }

        setTitle("Crear Nuevo Equipo Fantasy");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel para el título
        JLabel lblTitulo = new JLabel("Nuevo Equipo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 28));
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel del formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(new Color(30, 30, 30));
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblNombre = new JLabel("Nombre del Equipo:");
        lblNombre.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblNombre.setForeground(Color.WHITE);

        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 25));
        txtNombre.setFont(new Font("Verdana", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(txtNombre, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Botón guardar
        JButton btnGuardar = new JButton("Guardar");
        
        // Personalizar botón
        btnGuardar.setBackground(new Color(34, 139, 34));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Verdana", Font.PLAIN, 18));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Acción al pulsar guardar
        btnGuardar.addActionListener(e -> {
            String nombreEquipo = txtNombre.getText().trim();
            if (nombreEquipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del equipo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (guardarEquipoEnBD(nombreEquipo)) {
                    JOptionPane.showMessageDialog(this, "¡Equipo '" + nombreEquipo + "' creado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Panel del botón
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.add(btnGuardar);

        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Comprueba si el usuario ya tiene un equipo creado.
     * 
     * @param idUsuario ID del usuario.
     * @return  true si el usuario tiene un equipo, false en caso contrario.
     */
    private boolean tieneEquipo(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM FANTASY_TEAM WHERE id_usuario = ?";
        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Guarda un nuevo equipo en la base de datos.
     * 
     * @param nombreEquipo Nombre del equipo a crear.
     * @return true si se crea correctamente, false en caso de error.
     */
    private boolean guardarEquipoEnBD(String nombreEquipo) {
        String sql = "INSERT INTO FANTASY_TEAM (nombre, id_usuario) VALUES (?, ?)";

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreEquipo);
            stmt.setInt(2, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Muestra una ventana para que el usuario introduzca un nuevo nombre para su equipo.
     */
    private void mostrarVentanaRenombrar() {
        String nuevoNombre = JOptionPane.showInputDialog(
                null,
                "Introduce el nuevo nombre para tu equipo:",
                "Renombrar equipo",
                JOptionPane.PLAIN_MESSAGE
        );

        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            if (actualizarNombreEquipo(nuevoNombre.trim())) {
                JOptionPane.showMessageDialog(null, "¡Nombre del equipo actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el nombre del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se introdujo un nombre válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Actualiza el nombre del equipo en la base de datos.
     * 
     * @param nuevoNombre Nuevo nombre del equipo.
     * @return true si se actualiza correctamente, false en caso contrario.
     */
    private boolean actualizarNombreEquipo(String nuevoNombre) {
        String sql = "UPDATE FANTASY_TEAM SET nombre = ? WHERE id_usuario = ?";

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
