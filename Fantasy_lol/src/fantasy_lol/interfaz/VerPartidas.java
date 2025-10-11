package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Ventana que muestra el listado de partidas en la aplicación Fantasy LOL.
 * 
 * Permite visualizar las partidas registradas, su fecha, equipos y ganador.
 * También se puede acceder a detalles adicionales de cada partida.
 * 
 * @autor Vicente y Gonzalo
 */
public class VerPartidas extends JFrame {

    private JTable tablaPartidas;

    /**
     * Constructor que inicializa la ventana de visualización de partidas.
     */
    public VerPartidas() {
        setTitle("Listado de Partidas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Título
        JLabel lblTitulo = new JLabel("Listado de Partidas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        tablaPartidas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaPartidas);
        add(scrollPane, BorderLayout.CENTER);

        cargarPartidas();
        personalizarTabla();

        // Panel de Botones
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 30, 30));

        JButton btnInfoAdicional = new JButton("Info Adicional");
        personalizarBoton(btnInfoAdicional, new Color(218, 165, 32)); // Dorado
        btnInfoAdicional.addActionListener(e -> mostrarInformacionAdicional());

        JButton btnVolver = new JButton("Volver");
        personalizarBoton(btnVolver, new Color(178, 34, 34));
        btnVolver.addActionListener(e -> dispose());

        panelBoton.add(btnInfoAdicional);
        panelBoton.add(btnVolver);

        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Carga las partidas desde la base de datos y las muestra en la tabla.
     */
    private void cargarPartidas() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID"); // ID oculto
        model.addColumn("Fecha");
        model.addColumn("Equipo Azul");
        model.addColumn("Equipo Rojo");
        model.addColumn("Ganador");

        String sql = "SELECT " +
                     "p.id_partida, " +
                     "p.fecha, " +
                     "e1.nombre AS equipo_azul, " +
                     "e2.nombre AS equipo_rojo, " +
                     "e3.nombre AS ganador " +
                     "FROM partida p " +
                     "JOIN equipo e1 ON p.id_equipo_azul = e1.id_equipo " +
                     "JOIN equipo e2 ON p.id_equipo_rojo = e2.id_equipo " +
                     "LEFT JOIN equipo e3 ON p.id_equipo_ganador = e3.id_equipo " +
                     "ORDER BY p.fecha ASC";

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPartida = rs.getInt("id_partida");
                String fechaOriginal = rs.getString("fecha");
                String fechaFormateada = formatearFechaEuropea(fechaOriginal);

                String equipoAzul = rs.getString("equipo_azul");
                String equipoRojo = rs.getString("equipo_rojo");
                String ganador = rs.getString("ganador");

                if (ganador == null) {
                    ganador = "Pendiente";
                }

                model.addRow(new Object[]{idPartida, fechaFormateada, equipoAzul, equipoRojo, ganador});
            }

            tablaPartidas.setModel(model);

            // Ocultar columna ID
            tablaPartidas.getColumnModel().getColumn(0).setMinWidth(0);
            tablaPartidas.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaPartidas.getColumnModel().getColumn(0).setWidth(0);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar partidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Formatea una fecha del formato BD (yyyy-MM-dd) al formato europeo (dd/MM/yyyy).
     * 
     * @param fechaOriginal Fecha en formato original de la base de datos.
     * @return Fecha en formato europeo.
     */
    private String formatearFechaEuropea(String fechaOriginal) {
        try {
            SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoEuropeo = new SimpleDateFormat("dd/MM/yyyy");
            return formatoEuropeo.format(formatoBD.parse(fechaOriginal));
        } catch (ParseException e) {
            e.printStackTrace();
            return fechaOriginal; // Si falla, muestra la original
        }
    }

    /**
     * Muestra información adicional sobre una partida seleccionada.
     */
    private void mostrarInformacionAdicional() {
        int filaSeleccionada = tablaPartidas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una partida primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idPartida = (int) tablaPartidas.getValueAt(filaSeleccionada, 0); // Columna 0 es el ID oculto

        new DetallesPartida(idPartida);
        dispose();
    }

    /**
     * Personaliza el estilo de la tabla de partidas.
     */
    private void personalizarTabla() {
        tablaPartidas.setBackground(new Color(50, 50, 50));
        tablaPartidas.setForeground(Color.WHITE);
        tablaPartidas.setFont(new Font("Verdana", Font.PLAIN, 16));
        tablaPartidas.setRowHeight(30);

        tablaPartidas.setSelectionBackground(new Color(100, 149, 237));
        tablaPartidas.setSelectionForeground(Color.BLACK);

        tablaPartidas.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaPartidas.getTableHeader().setForeground(Color.WHITE);
        tablaPartidas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 18));

        tablaPartidas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });
    }
}
