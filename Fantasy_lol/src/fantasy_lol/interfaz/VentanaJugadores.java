package fantasy_lol.interfaz;

import fantasy_lol.conexion.MySQLConnection;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ventana que muestra una lista de jugadores con opción de búsqueda.
 * 
 * Muestra tarjetas con información de cada jugador y permite filtrar por nombre.
 * 
 * @author Vicente y Gonzalo
 */
public class VentanaJugadores extends JFrame {

    private JTextField txtBuscar;
    private JPanel panelJugadores;
    private List<JugadorInfo> jugadoresTotales;

    /**
     * Constructor que inicializa la ventana de jugadores.
     */
    public VentanaJugadores() {
        setTitle("Jugadores");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(15, 15, 30));

        // Panel Norte: Título + Búsqueda
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelNorte.setBackground(new Color(15, 15, 30));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel lblTitulo = new JLabel("Lista Jugadores");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Barra de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(new Color(15, 15, 30));
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda, BoxLayout.X_AXIS));
        panelBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Icono lupa
        ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/recursos/img/jugadores/lupablanca.png"));
        Image imgLupa = iconoLupa.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel lblLupa = new JLabel(new ImageIcon(imgLupa));
        lblLupa.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Campo de texto de búsqueda
        txtBuscar = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };

        txtBuscar.setFont(new Font("Verdana", Font.PLAIN, 16));
        txtBuscar.setPreferredSize(new Dimension(300, 40));
        txtBuscar.setMaximumSize(new Dimension(300, 40));
        txtBuscar.setBackground(new Color(30, 30, 60));
        txtBuscar.setForeground(Color.GRAY);
        txtBuscar.setCaretColor(Color.WHITE);
        txtBuscar.setOpaque(false);
        txtBuscar.setBorder(new RoundedBorder(20));
        txtBuscar.setText("Buscar jugador...");

        // Placeholder efecto
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().equals("Buscar jugador...")) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(Color.WHITE);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Buscar jugador...");
                    txtBuscar.setForeground(Color.GRAY);
                }
            }
        });

        // Buscar al escribir
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarJugadores(txtBuscar.getText());
            }
        });

        panelBusqueda.add(lblLupa);
        panelBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBusqueda.add(txtBuscar);

        panelNorte.add(lblTitulo);
        panelNorte.add(panelBusqueda);

        // Botón Cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.setPreferredSize(new Dimension(150, 40));
        btnCerrar.setMaximumSize(new Dimension(150, 40));
        btnCerrar.setBackground(new Color(50, 50, 70));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(new RoundedBorder(20));
        btnCerrar.setFont(new Font("Verdana", Font.BOLD, 16));

        // Hover efecto
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(70, 70, 100));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(50, 50, 70));
            }
        });

        btnCerrar.addActionListener(e -> dispose());

        panelNorte.add(Box.createRigidArea(new Dimension(0, 15)));
        panelNorte.add(btnCerrar);

        add(panelNorte, BorderLayout.NORTH);

        // Panel de jugadores
        panelJugadores = new JPanel();
        panelJugadores.setBackground(new Color(15, 15, 30));
        panelJugadores.setLayout(new GridLayout(0, 4, 30, 30));

        JScrollPane scrollPane = new JScrollPane(panelJugadores);
        scrollPane.getViewport().setBackground(new Color(15, 15, 30));
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        // Scroll suave
        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                int rotation = e.getWheelRotation();
                int amount = rotation * 30;
                int newValue = bar.getValue() + amount;
                newValue = Math.max(newValue, 0);
                newValue = Math.min(newValue, bar.getMaximum());
                bar.setValue(newValue);
            }
        });

        // Scroll personalizado
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100);
                this.trackColor = new Color(30, 30, 60);
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

        add(scrollPane, BorderLayout.CENTER);

        cargarJugadoresDesdeBD();

        setVisible(true);
    }

    /**
     * Carga todos los jugadores desde la base de datos.
     */
    private void cargarJugadoresDesdeBD() {
        jugadoresTotales = new ArrayList<>();

        String sql = """
            SELECT 
                j.nombre AS nombre_jugador,
                j.rol,
                e.nombre AS nombre_equipo,
                AVG(k.kills) AS avg_kills,
                AVG(k.deaths) AS avg_deaths,
                AVG(k.assists) AS avg_assists,
                SUM(k.kills + k.assists) AS puntos
            FROM JUGADOR j
            JOIN EQUIPO e ON j.id_equipo = e.id_equipo
            LEFT JOIN KDA k ON j.id_jugador = k.id_jugador
            GROUP BY j.id_jugador
        """;

        try (Connection conn = new MySQLConnection().mySQLConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre_jugador");
                String rol = rs.getString("rol");
                String equipo = rs.getString("nombre_equipo");
                double avgKills = rs.getDouble("avg_kills");
                double avgDeaths = rs.getDouble("avg_deaths");
                double avgAssists = rs.getDouble("avg_assists");
                int puntos = rs.getInt("puntos");

                String kda = String.format("%.1f/%.1f/%.1f", avgKills, avgDeaths, avgAssists);

                jugadoresTotales.add(new JugadorInfo(equipo, rol, nombre, kda, puntos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarJugadores(jugadoresTotales);
    }

    /**
     * Muestra una lista de jugadores en el panel.
     * 
     * @param jugadores Lista de jugadores a mostrar.
     */
    private void mostrarJugadores(List<JugadorInfo> jugadores) {
        panelJugadores.removeAll();

        for (JugadorInfo jugador : jugadores) {
            panelJugadores.add(crearTarjetaJugador(
                jugador.getEquipo(),
                jugador.getRol(),
                jugador.getNombre(),
                jugador.getKda(),
                jugador.getPuntos()
            ));
        }

        panelJugadores.revalidate();
        panelJugadores.repaint();
    }

    /**
     * Filtra jugadores por texto de búsqueda.
     * 
     * @param texto Texto a buscar.
     */
    public void filtrarJugadores(String texto) {
        if (texto.equals("Buscar jugador...")) {
            mostrarJugadores(jugadoresTotales);
            return;
        }

        List<JugadorInfo> filtrados = new ArrayList<>();
        for (JugadorInfo jugador : jugadoresTotales) {
            if (jugador.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(jugador);
            }
        }
        mostrarJugadores(filtrados);
    }

    /**
     * Crea una tarjeta visual con la información de un jugador.
     * 
     * @param equipo Equipo del jugador.
     * @param rol Rol del jugador.
     * @param nombre Nombre del jugador.
     * @param kda Estadísticas KDA.
     * @param puntos Puntos totales.
     * @return Panel con la tarjeta del jugador.
     */
    private JPanel crearTarjetaJugador(String equipo, String rol, String nombre, String kda, int puntos) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new Color(15, 15, 30));
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        tarjeta.setPreferredSize(new Dimension(200, 320));

        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tarjeta.setBackground(new Color(30, 30, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tarjeta.setBackground(new Color(15, 15, 30));
            }
        });

        tarjeta.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblFoto = new JLabel();
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            String rutaImagen = "/recursos/img/jugadores/" + nombre + ".png";
            java.net.URL urlImagen = getClass().getResource(rutaImagen);
            if (urlImagen == null) {
                urlImagen = getClass().getResource("/recursos/img/jugadores/placeholder.png");
            }
            ImageIcon icono = new ImageIcon(urlImagen);
            Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblFoto.setText("Sin imagen");
            lblFoto.setForeground(Color.LIGHT_GRAY);
        }
        tarjeta.add(lblFoto);

        tarjeta.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblNombre = new JLabel(nombre, SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(lblNombre);

        tarjeta.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblEquipo = new JLabel(equipo, SwingConstants.CENTER);
        lblEquipo.setForeground(new Color(200, 50, 50));
        lblEquipo.setFont(new Font("Verdana", Font.PLAIN, 14));
        lblEquipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(lblEquipo);

        tarjeta.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblRol = new JLabel(rol, SwingConstants.CENTER);
        lblRol.setForeground(Color.LIGHT_GRAY);
        lblRol.setFont(new Font("Verdana", Font.PLAIN, 13));
        lblRol.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(lblRol);

        tarjeta.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblKDA = new JLabel("KDA: " + kda, SwingConstants.CENTER);
        lblKDA.setForeground(Color.LIGHT_GRAY);
        lblKDA.setFont(new Font("Verdana", Font.PLAIN, 13));
        lblKDA.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(lblKDA);

        tarjeta.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblPuntos = new JLabel("Puntos: " + puntos, SwingConstants.CENTER);
        lblPuntos.setForeground(new Color(100, 200, 100));
        lblPuntos.setFont(new Font("Verdana", Font.BOLD, 14));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        tarjeta.add(lblPuntos);

        tarjeta.add(Box.createVerticalGlue());

        return tarjeta;
    }

    /**
     * Clase interna que almacena la información de un jugador.
     */
    private static class JugadorInfo {
        private String equipo;
        private String rol;
        private String nombre;
        private String kda;
        private int puntos;

        public JugadorInfo(String equipo, String rol, String nombre, String kda, int puntos) {
            this.equipo = equipo;
            this.rol = rol;
            this.nombre = nombre;
            this.kda = kda;
            this.puntos = puntos;
        }

        public String getEquipo() { return equipo; }
        public String getRol() { return rol; }
        public String getNombre() { return nombre; }
        public String getKda() { return kda; }
        public int getPuntos() { return puntos; }
    }

    /**
     * Borde redondeado personalizado para componentes.
     */
    public static class RoundedBorder extends AbstractBorder {
        private int radius;
        public RoundedBorder(int radius) { this.radius = radius; }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
            g2d.dispose();
        }
    }
}
