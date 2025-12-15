package cuaceptarsolicitudes.presentacion;

import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 * Dialog para mostrar detalles de una solicitud de adopción
 * 
 * @author Josel
 */
public class JDinfoSolicitud extends JDialog {

    private SolicitudDTO solicitud;

    // Labels para mostrar datos
    private JLabel lblNomUsuario;
    private JLabel lblHoraSolicitudllenar;
    private JLabel lblHoraCitaLLenar;
    private JLabel lblNombreMascotallenar;
    private JLabel lblEstadoLlenar;

    public JDinfoSolicitud(Frame parent, boolean modal, SolicitudDTO solicitud) {
        super(parent, "Detalles de Solicitud", modal);
        this.solicitud = solicitud;
        initComponents();
        cargarDatos();
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(219, 213, 195));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Detalles de Solicitud", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1: Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblCliente = new JLabel("Nombre del usuario:");
        lblCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(lblCliente, gbc);

        gbc.gridx = 1;
        lblNomUsuario = new JLabel("...");
        lblNomUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblNomUsuario, gbc);

        // Row 2: Hora Solicitud
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblHoraSolicitud = new JLabel("Hora de solicitud:");
        lblHoraSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(lblHoraSolicitud, gbc);

        gbc.gridx = 1;
        lblHoraSolicitudllenar = new JLabel("...");
        lblHoraSolicitudllenar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblHoraSolicitudllenar, gbc);

        // Row 3: Hora Cita
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblHoraCita = new JLabel("Hora de la cita:");
        lblHoraCita.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(lblHoraCita, gbc);

        gbc.gridx = 1;
        lblHoraCitaLLenar = new JLabel("...");
        lblHoraCitaLLenar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblHoraCitaLLenar, gbc);

        // Row 4: Mascota
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblMascota = new JLabel("Nombre mascota:");
        lblMascota.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(lblMascota, gbc);

        gbc.gridx = 1;
        lblNombreMascotallenar = new JLabel("...");
        lblNombreMascotallenar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblNombreMascotallenar, gbc);

        // Row 5: Estado
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(lblEstado, gbc);

        gbc.gridx = 1;
        lblEstadoLlenar = new JLabel("...");
        lblEstadoLlenar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblEstadoLlenar, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(219, 213, 195));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCerrar.addActionListener(e -> dispose());
        buttonPanel.add(btnCerrar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add to dialog
        getContentPane().add(mainPanel);
    }

    private void cargarDatos() {
        if (solicitud != null) {
            lblNomUsuario.setText(solicitud.getNombreUsuario() != null ? solicitud.getNombreUsuario() : "N/A");
            lblNombreMascotallenar.setText(solicitud.getNombreMascota() != null ? solicitud.getNombreMascota() : "N/A");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            if (solicitud.getFechaSolicitud() != null) {
                lblHoraSolicitudllenar.setText(solicitud.getFechaSolicitud().format(formatter));
            } else {
                lblHoraSolicitudllenar.setText("N/A");
            }

            if (solicitud.getFechaCita() != null) {
                lblHoraCitaLLenar.setText(solicitud.getFechaCita().format(formatter));
            } else {
                lblHoraCitaLLenar.setText("Sin cita asignada");
            }

            lblEstadoLlenar.setText(solicitud.getEstado() != null ? solicitud.getEstado() : "PENDIENTE");
        }
    }
}
