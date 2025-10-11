package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Ventana que muestra los detalles de una partida de Fantasy LOL.
 * 
 * Muestra las estadísticas de jugadores de los equipos azul y rojo.
 * 
 * @author Vicente y Gonzalo
 */
public class DetallesPartida extends JFrame {

    private JTable tablaAzul;
    private JTable tablaRojo;
    private int idPartida;

    /**
     * Constructor que inicializa la ventana de detalles de la partida.
     * 
     * @param idPartida ID de la partida cuyos detalles se van a mostrar.
     */
    public DetallesPartida(int idPartida) {
        this.idPartida = idPartida;

        setTitle("Detalles de la Partida");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel título
        JLabel lblTitulo = new JLabel("Detalles de la Partida", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel para las tablas y el botón
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(new Color(30, 30, 30));

        JPanel panelTablas = new JPanel(new GridLayout(1, 2, 20, 0));
        panelTablas.setBackground(new Color(30, 30, 30));
        panelTablas.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        tablaAzul = new JTable();
        tablaRojo = new JTable();

        JScrollPane scrollAzul = new JScrollPane(tablaAzul);
        JScrollPane scrollRojo = new JScrollPane(tablaRojo);

        // Fondo del viewport
        scrollAzul.getViewport().setBackground(new Color(30, 30, 30));
        scrollRojo.getViewport().setBackground(new Color(30, 30, 30));

        // Scroll personalizados
        personalizarScroll(scrollAzul);
        personalizarScroll(scrollRojo);

        panelTablas.add(scrollAzul);
        panelTablas.add(scrollRojo);
        panelCentral.add(panelTablas);

        // Panel botón volver
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton btnVolver = new JButton("Volver");
        personalizarBoton(btnVolver, new Color(178, 34, 34));

        // Acción volver
        btnVolver.addActionListener(e -> {
            new VerPartidas();
            dispose();
        });

        panelBoton.add(btnVolver);
        panelCentral.add(panelBoton);

        add(panelCentral, BorderLayout.CENTER);

        cargarDatosEquipos();
        personalizarTabla(tablaAzul, new Color(70, 130, 180)); // Azul
        personalizarTabla(tablaRojo, new Color(220, 20, 60));  // Rojo

        setVisible(true);
    }

    /**
     * Carga los datos de los jugadores de los equipos azul y rojo desde la base de datos.
     */
    private void cargarDatosEquipos() {
        try (Connection conn = new MySQLConnection().mySQLConnect()) {
            String sqlEquipos = "SELECT id_equipo_azul, id_equipo_rojo FROM partida WHERE id_partida = ?";
            PreparedStatement stmtEquipos = conn.prepareStatement(sqlEquipos);
            stmtEquipos.setInt(1, idPartida);
            ResultSet rsEquipos = stmtEquipos.executeQuery();

            int idEquipoAzul = 0;
            int idEquipoRojo = 0;

            if (rsEquipos.next()) {
                idEquipoAzul = rsEquipos.getInt("id_equipo_azul");
                idEquipoRojo = rsEquipos.getInt("id_equipo_rojo");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la partida seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Modelo equipo azul
            DefaultTableModel modelAzul = new DefaultTableModel(
                new Object[]{"Jugador", "Campeón", "Kills", "Deaths", "Assists"}, 0);

            String sqlAzul = """
                SELECT j.nombre AS jugador, c.nombre AS campeon, k.kills, k.deaths, k.assists 
                FROM kda k 
                JOIN jugador j ON k.id_jugador = j.id_jugador 
                JOIN campeon c ON k.id_campeon = c.id_campeon 
                WHERE k.id_partida = ? AND j.id_equipo = ?
            """;
            PreparedStatement stmtAzul = conn.prepareStatement(sqlAzul);
            stmtAzul.setInt(1, idPartida);
            stmtAzul.setInt(2, idEquipoAzul);
            ResultSet rsAzul = stmtAzul.executeQuery();

            while (rsAzul.next()) {
                modelAzul.addRow(new Object[]{
                        rsAzul.getString("jugador"),
                        rsAzul.getString("campeon"),
                        rsAzul.getInt("kills"),
                        rsAzul.getInt("deaths"),
                        rsAzul.getInt("assists")
                });
            }
            tablaAzul.setModel(modelAzul);

            // Modelo equipo rojo
            DefaultTableModel modelRojo = new DefaultTableModel(
                new Object[]{"Jugador", "Campeón", "Kills", "Deaths", "Assists"}, 0);

            String sqlRojo = """
                SELECT j.nombre AS jugador, c.nombre AS campeon, k.kills, k.deaths, k.assists 
                FROM kda k 
                JOIN jugador j ON k.id_jugador = j.id_jugador 
                JOIN campeon c ON k.id_campeon = c.id_campeon 
                WHERE k.id_partida = ? AND j.id_equipo = ?
            """;
            PreparedStatement stmtRojo = conn.prepareStatement(sqlRojo);
            stmtRojo.setInt(1, idPartida);
            stmtRojo.setInt(2, idEquipoRojo);
            ResultSet rsRojo = stmtRojo.executeQuery();

            while (rsRojo.next()) {
                modelRojo.addRow(new Object[]{
                        rsRojo.getString("jugador"),
                        rsRojo.getString("campeon"),
                        rsRojo.getInt("kills"),
                        rsRojo.getInt("deaths"),
                        rsRojo.getInt("assists")
                });
            }
            tablaRojo.setModel(modelRojo);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los detalles de la partida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Personaliza el aspecto de la tabla.
     * 
     * @param tabla Tabla a personalizar.
     * @param headerColor Color del encabezado.
     */
    private void personalizarTabla(JTable tabla, Color headerColor) {
        tabla.setBackground(new Color(50, 50, 50));
        tabla.setForeground(Color.WHITE);
        tabla.setFont(new Font("Verdana", Font.PLAIN, 16));
        tabla.setRowHeight(30);

        tabla.setSelectionBackground(new Color(100, 149, 237));
        tabla.setSelectionForeground(Color.BLACK);

        tabla.getTableHeader().setBackground(headerColor);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 18));

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    /**
     * Personaliza el estilo de un botón.
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
        boton.setFont(new Font("Verdana", Font.PLAIN, 18));
        Dimension btnSize = new Dimension(200, 50);
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
    }

    /**
     * Personaliza la apariencia de los scroll de las tablas.
     * 
     * @param scroll ScrollPane a personalizar.
     */
    private void personalizarScroll(JScrollPane scroll) {
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100);
                this.trackColor = new Color(30, 30, 30);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
    }
}
