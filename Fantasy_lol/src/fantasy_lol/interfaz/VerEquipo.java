package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ventana para visualizar y gestionar el equipo de Fantasy LOL de un usuario.
 * 
 * Permite ver el equipo, añadir jugadores y eliminar jugadores.
 * 
 * @author Vicente y Gonzalo
 */
public class VerEquipo extends JFrame {

    private int idUsuario;
    private int idFantasyTeam;
    private JLabel lblNombreEquipo;
    private JPanel panelJugadores;

    /**
     * Constructor que inicializa la ventana para ver y gestionar el equipo.
     * 
     * @param idUsuario ID del usuario logueado.
     */
    public VerEquipo(int idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Mi Equipo Fantasy");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Panel superior
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        panelTop.setBackground(new Color(30, 30, 30));

        JLabel lblTitulo = new JLabel("Mi Fantasy Team", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblNombreEquipo = new JLabel("Cargando nombre del equipo...", SwingConstants.CENTER);
        lblNombreEquipo.setFont(new Font("Verdana", Font.PLAIN, 24));
        lblNombreEquipo.setForeground(Color.LIGHT_GRAY);
        lblNombreEquipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombreEquipo.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));

        panelTop.add(Box.createRigidArea(new Dimension(0, 20)));
        panelTop.add(lblTitulo);
        panelTop.add(Box.createRigidArea(new Dimension(0, 10)));
        panelTop.add(lblNombreEquipo);

        add(panelTop, BorderLayout.NORTH);

        // Panel de jugadores
        panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(0, 2, 40, 40));
        panelJugadores.setBackground(new Color(30, 30, 30));
        panelJugadores.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JScrollPane scrollPane = new JScrollPane(panelJugadores);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnAnadir = crearBoton("➕ Añadir Jugador", new Color(34, 139, 34));
        JButton btnEliminar = crearBoton("➖ Eliminar Jugador", new Color(178, 34, 34));
        JButton btnVolver = crearBoton("⬅️ Volver", new Color(128, 0, 128));

        panelBotones.add(btnAnadir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acciones de botones
        btnAnadir.addActionListener(e -> anadirJugador());
        btnEliminar.addActionListener(e -> eliminarJugador());
        btnVolver.addActionListener(e -> dispose());

        cargarNombreEquipo();
        cargarJugadores();

        setVisible(true);
    }

    /**
     * Carga el nombre del equipo del usuario desde la base de datos.
     */
    private void cargarNombreEquipo() {
        String sql = "SELECT id_fantasy, nombre FROM FANTASY_TEAM WHERE id_usuario = ?";

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idFantasyTeam = rs.getInt("id_fantasy");
                String nombreEquipo = rs.getString("nombre");
                lblNombreEquipo.setText(nombreEquipo);
            } else {
                lblNombreEquipo.setText("Equipo no encontrado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            lblNombreEquipo.setText("Error al cargar el equipo.");
        }
    }

    /**
     * Carga los jugadores que forman parte del equipo.
     */
    private void cargarJugadores() {
        List<JugadorInfo> jugadores = new ArrayList<>();

        String sql = """
            SELECT 
                j.nombre AS nombre_jugador,
                j.rol,
                e.nombre AS nombre_equipo,
                AVG(k.kills) AS avg_kills,
                AVG(k.deaths) AS avg_deaths,
                AVG(k.assists) AS avg_assists,
                SUM(k.kills + k.assists) AS puntos
            FROM PLANTILLA p
            JOIN JUGADOR j ON p.id_jugador = j.id_jugador
            JOIN EQUIPO e ON j.id_equipo = e.id_equipo
            LEFT JOIN KDA k ON j.id_jugador = k.id_jugador
            WHERE p.id_fantasy = ?
            GROUP BY j.id_jugador
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idFantasyTeam);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_jugador");
                String rol = rs.getString("rol");
                String equipoReal = rs.getString("nombre_equipo");
                double avgKills = rs.getDouble("avg_kills");
                double avgDeaths = rs.getDouble("avg_deaths");
                double avgAssists = rs.getDouble("avg_assists");
                int puntos = rs.getInt("puntos");

                String kda = String.format("%.1f/%.1f/%.1f", avgKills, avgDeaths, avgAssists);

                jugadores.add(new JugadorInfo(nombre, rol, equipoReal, kda, puntos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarJugadores(jugadores);
    }

    /**
     * Muestra las tarjetas de los jugadores en el panel.
     * 
     * @param jugadores Lista de jugadores.
     */
    private void mostrarJugadores(List<JugadorInfo> jugadores) {
        panelJugadores.removeAll();

        for (JugadorInfo jugador : jugadores) {
            panelJugadores.add(crearTarjetaJugador(jugador));
        }

        panelJugadores.revalidate();
        panelJugadores.repaint();
    }

    /**
     * Crea una tarjeta visual para un jugador.
     * 
     * @param jugador Información del jugador.
     * @return Panel con la tarjeta del jugador.
     */
    private JPanel crearTarjetaJugador(JugadorInfo jugador) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(new Color(0, 0, 0, 0));
        tarjeta.setBorder(null);

        tarjeta.setMaximumSize(new Dimension(400, 140));
        tarjeta.setMinimumSize(new Dimension(400, 140));

        // Foto
        JLabel lblFoto = new JLabel();
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            String rutaImagen = "src/recursos/img/jugadores/" + jugador.getNombre() + ".png";
            ImageIcon icono = new ImageIcon(rutaImagen);
            Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblFoto.setText("Foto");
            lblFoto.setForeground(Color.LIGHT_GRAY);
        }

        JPanel panelFoto = new JPanel();
        panelFoto.setBackground(new Color(0, 0, 0, 0));
        panelFoto.add(lblFoto);

        // Info
        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(0, 0, 0, 0));
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

        JLabel lblNombre = new JLabel(jugador.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblRol = new JLabel("Rol: " + jugador.getRol());
        lblRol.setForeground(Color.LIGHT_GRAY);
        lblRol.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblEquipo = new JLabel("Equipo: " + jugador.getEquipoReal());
        lblEquipo.setForeground(Color.LIGHT_GRAY);
        lblEquipo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblKDA = new JLabel("KDA: " + jugador.getKda());
        lblKDA.setForeground(Color.LIGHT_GRAY);
        lblKDA.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblPuntos = new JLabel("Puntos: " + jugador.getPuntos());
        lblPuntos.setForeground(Color.LIGHT_GRAY);
        lblPuntos.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelInfo.add(lblNombre);
        panelInfo.add(lblRol);
        panelInfo.add(lblEquipo);
        panelInfo.add(lblKDA);
        panelInfo.add(lblPuntos);

        tarjeta.add(panelFoto, BorderLayout.WEST);
        tarjeta.add(panelInfo, BorderLayout.CENTER);

        return tarjeta;
    }

    /**
     * Crea un botón personalizado.
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
        boton.setFont(new Font("Verdana", Font.PLAIN, 18));
        boton.setPreferredSize(new Dimension(250, 50));
        return boton;
    }

    /**
     * Clase interna que almacena la información de un jugador.
     */
    private static class JugadorInfo {
        private String nombre;
        private String rol;
        private String equipoReal;
        private String kda;
        private int puntos;

        public JugadorInfo(String nombre, String rol, String equipoReal, String kda, int puntos) {
            this.nombre = nombre;
            this.rol = rol;
            this.equipoReal = equipoReal;
            this.kda = kda;
            this.puntos = puntos;
        }

        public String getNombre() { return nombre; }
        public String getRol() { return rol; }
        public String getEquipoReal() { return equipoReal; }
        public String getKda() { return kda; }
        public int getPuntos() { return puntos; }
    }

    /**
     * Añade un jugador libre al equipo si no supera el límite de 6 jugadores.
     */
    private void anadirJugador() {
        if (panelJugadores.getComponentCount() >= 6) {
            JOptionPane.showMessageDialog(this, "No puedes tener más de 6 jugadores en tu equipo.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> jugadoresLibres = new ArrayList<>();

        String sql = """
            SELECT j.nombre
            FROM JUGADOR j
            WHERE j.id_jugador NOT IN (SELECT id_jugador FROM PLANTILLA)
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                jugadoresLibres.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar jugadores disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jugadoresLibres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay jugadores disponibles para añadir.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String seleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un jugador para añadir:",
                "Añadir Jugador",
                JOptionPane.PLAIN_MESSAGE,
                null,
                jugadoresLibres.toArray(),
                jugadoresLibres.get(0)
        );

        if (seleccionado != null) {
            insertarJugadorEnEquipo(seleccionado);
        }
    }

    /**
     * Inserta un jugador en la plantilla del equipo.
     * 
     * @param nombreJugador Nombre del jugador a insertar.
     */
    private void insertarJugadorEnEquipo(String nombreJugador) {
        String sql = """
            INSERT INTO PLANTILLA (id_fantasy, id_jugador)
            VALUES (?, (SELECT id_jugador FROM JUGADOR WHERE nombre = ?))
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFantasyTeam);
            stmt.setString(2, nombreJugador);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Jugador añadido con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarJugadores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo añadir el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al añadir jugador.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina un jugador del equipo.
     */
    private void eliminarJugador() {
        List<String> jugadoresEnEquipo = new ArrayList<>();

        String sql = """
            SELECT j.nombre
            FROM PLANTILLA p
            JOIN JUGADOR j ON p.id_jugador = j.id_jugador
            WHERE p.id_fantasy = ?
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFantasyTeam);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                jugadoresEnEquipo.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar jugadores de tu equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jugadoresEnEquipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tu equipo no tiene jugadores para eliminar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String seleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona un jugador para eliminar:",
                "Eliminar Jugador",
                JOptionPane.PLAIN_MESSAGE,
                null,
                jugadoresEnEquipo.toArray(),
                jugadoresEnEquipo.get(0)
        );

        if (seleccionado != null) {
            eliminarJugadorDeEquipo(seleccionado);
        }
    }

    /**
     * Elimina un jugador concreto de la plantilla del equipo.
     * 
     * @param nombreJugador Nombre del jugador a eliminar.
     */
    private void eliminarJugadorDeEquipo(String nombreJugador) {
        String sql = """
            DELETE FROM PLANTILLA 
            WHERE id_fantasy = ? 
              AND id_jugador = (SELECT id_jugador FROM JUGADOR WHERE nombre = ?)
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFantasyTeam);
            stmt.setString(2, nombreJugador);

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Jugador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarJugadores();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el jugador.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
