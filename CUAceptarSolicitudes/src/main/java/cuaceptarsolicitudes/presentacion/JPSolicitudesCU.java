package cuaceptarsolicitudes.presentacion;

import cuaceptarsolicitudes.control.ControlPresentacion;
import cuaceptarsolicitudes.negocio.dtos.SolicitudDTO;
import cuaceptarsolicitudes.negocio.dtos.ResultadoOperacion;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de gestión de solicitudes de adopción para administradores
 *
 * @author Josel
 */
public class JPSolicitudesCU extends javax.swing.JPanel {

        private ControlPresentacion controlPresentacion;
        private DefaultTableModel modeloTabla;
        private Runnable inicioListener;

        public JPSolicitudesCU() {
                initComponents();
                configurarTabla();
                configurarEventos();
                // Inicializar el control por defecto
                this.controlPresentacion = new ControlPresentacion();
                cargarSolicitudes();
        }

        /**
         * Establece el listener para navegar al inicio
         */
        public void setInicioListener(Runnable listener) {
                this.inicioListener = listener;
        }

        public void setControlPresentacion(ControlPresentacion control) {
                this.controlPresentacion = control;
                cargarSolicitudes();
        }

        private void configurarTabla() {
                String[] columnas = { "ID", "Usuario", "Mascota", "Fecha", "Estado" };
                modeloTabla = new DefaultTableModel(columnas, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
                tablaSolicitudes.setModel(modeloTabla);
        }

        private void configurarEventos() {
                tablaSolicitudes.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                        int filaSeleccionada = tablaSolicitudes.getSelectedRow();
                                        if (filaSeleccionada >= 0) {
                                                mostrarDetallesSolicitud();
                                        }
                                }
                        }
                });
        }

        private void cargarSolicitudes() {
                if (controlPresentacion == null) {
                        return;
                }

                try {
                        List<SolicitudDTO> solicitudes = controlPresentacion.obtenerSolicitudes();
                        modeloTabla.setRowCount(0);

                        for (SolicitudDTO solicitud : solicitudes) {
                                String nombreUsuario = (solicitud.getNombreUsuario() != null)
                                                ? solicitud.getNombreUsuario()
                                                : "Desconocido";

                                String nombreMascota = (solicitud.getNombreMascota() != null)
                                                ? solicitud.getNombreMascota()
                                                : "Desconocida";

                                String fecha = (solicitud.getFechaCita() != null)
                                                ? solicitud.getFechaCita().toLocalDate().toString()
                                                : "Sin cita";

                                String estado = (solicitud.getEstado() != null)
                                                ? solicitud.getEstado()
                                                : "PENDIENTE";

                                modeloTabla.addRow(new Object[] {
                                                solicitud.getId(),
                                                nombreUsuario,
                                                nombreMascota,
                                                fecha,
                                                estado
                                });
                        }

                        System.out.println("Cargadas " + solicitudes.size() + " solicitudes.");
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                                        "Error al cargar solicitudes: " + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                }
        }

        private SolicitudDTO obtenerSolicitudSeleccionada() {
                int filaSeleccionada = tablaSolicitudes.getSelectedRow();
                if (filaSeleccionada < 0) {
                        return null;
                }

                // Obtener el ID de la fila seleccionada
                String idSolicitud = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

                // Buscar en la lista de solicitudes
                List<SolicitudDTO> solicitudes = controlPresentacion.obtenerSolicitudes();
                for (SolicitudDTO solicitud : solicitudes) {
                        if (solicitud.getId().equals(idSolicitud)) {
                                return solicitud;
                        }
                }

                return null;
        }

        private void mostrarDetallesSolicitud() {
                SolicitudDTO solicitud = obtenerSolicitudSeleccionada();
                if (solicitud != null) {
                        java.awt.Frame parentFrame = (java.awt.Frame) javax.swing.SwingUtilities
                                        .getWindowAncestor(this);
                        JDinfoSolicitud dialog = new JDinfoSolicitud(parentFrame, true, solicitud);
                        dialog.setLocationRelativeTo(this);
                        dialog.setVisible(true);
                }
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                btncancelarcita = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                tablaSolicitudes = new javax.swing.JTable();
                jLabel2 = new javax.swing.JLabel();
                btnActualizar = new javax.swing.JButton();
                btnModificar = new javax.swing.JButton();
                btnAceptarCita = new javax.swing.JButton();
                btnInicio = new javax.swing.JButton();

                jPanel1.setBackground(new java.awt.Color(219, 213, 195));

                jPanel2.setBackground(new java.awt.Color(219, 213, 195));
                jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                btncancelarcita.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btncancelarcita.setText("Cancelar Cita");
                btncancelarcita.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btncancelarcitaActionPerformed(evt);
                        }
                });

                tablaSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                {},
                                                {},
                                                {},
                                                {}
                                },
                                new String[] {

                                }));
                jScrollPane1.setViewportView(tablaSolicitudes);

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                jLabel2.setText("SOLICITUDES");

                btnActualizar.setText("Actualizar");
                btnActualizar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnActualizarActionPerformed(evt);
                        }
                });

                btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnModificar.setText("Modificar cita");
                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnModificarActionPerformed(evt);
                        }
                });

                btnAceptarCita.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnAceptarCita.setText("Aceptar cita");
                btnAceptarCita.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAceptarCitaActionPerformed(evt);
                        }
                });

                btnInicio.setText("Inicio");
                btnInicio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnInicioActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jPanel2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(321, 321, 321)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(btncancelarcita,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                155,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(85, 85, 85)
                                                                                                                                .addComponent(btnAceptarCita,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                155,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(74, 74, 74)
                                                                                                                                .addComponent(btnModificar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                155,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                650,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(54, 54, 54)
                                                                                                                                .addComponent(btnActualizar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                99,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(704, 704,
                                                                                                                                                704)
                                                                                                                                .addComponent(btnInicio,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                99,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(521, 521, 521)
                                                                                                .addComponent(jLabel2)))
                                                                .addContainerGap(352, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(430, 430, 430)
                                                                                                .addComponent(jPanel2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(14, 14, 14)
                                                                                                .addComponent(jLabel2)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                430,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(31, 31, 31)
                                                                                                                                .addComponent(btnActualizar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                36,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(31, 31, 31)
                                                                                                                                .addComponent(btnInicio,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                36,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(btncancelarcita,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnModificar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnAceptarCita,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                55,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(12, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
        }// </editor-fold>//GEN-END:initComponents

        private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInicioActionPerformed
                // Usar el callback si está configurado, sino cerrar la ventana
                if (inicioListener != null) {
                        inicioListener.run();
                } else {
                        java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);
                        if (ventana != null) {
                                ventana.dispose();
                        }
                }
        }// GEN-LAST:event_btnInicioActionPerformed

        private void btnRechazarCitaActionPerformed(java.awt.event.ActionEvent evt) {
                SolicitudDTO solicitud = obtenerSolicitudSeleccionada();
                if (solicitud == null) {
                        JOptionPane.showMessageDialog(this,
                                        "Por favor selecciona una solicitud",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(this,
                                "¿Está seguro de rechazar la solicitud de " + solicitud.getNombreUsuario() + "?",
                                "Confirmar Rechazo",
                                JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                        ResultadoOperacion resultado = controlPresentacion.rechazarSolicitud(solicitud.getId(),
                                        "admin@gmail.com");
                        if (resultado.isExitoso()) {
                                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Éxito",
                                                JOptionPane.INFORMATION_MESSAGE);
                                cargarSolicitudes();
                        } else {
                                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
                cargarSolicitudes();
                JOptionPane.showMessageDialog(this, "Tabla actualizada", "Información",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
                SolicitudDTO solicitud = obtenerSolicitudSeleccionada();
                if (solicitud == null) {
                        JOptionPane.showMessageDialog(this,
                                        "Por favor selecciona una solicitud",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                }

                // Mostrar diálogo JDMsgModificacion para ingresar razón
                java.awt.Frame parentFrame = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
                String razonModificacion = controlPresentacion.mostrarDialogoModificacion(parentFrame);

                if (razonModificacion != null && !razonModificacion.trim().isEmpty()) {
                        ResultadoOperacion resultado = controlPresentacion.modificarSolicitud(
                                        solicitud.getId(), "admin@gmail.com", razonModificacion);
                        if (resultado.isExitoso()) {
                                controlPresentacion.mostrarCorreoConfirmacion(solicitud.getCorreoUsuario());
                        } else {
                                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                } else if (razonModificacion != null) {
                        JOptionPane.showMessageDialog(this, "Debes ingresar una razón", "Aviso",
                                        JOptionPane.WARNING_MESSAGE);
                }
        }

        private void btnAceptarCitaActionPerformed(java.awt.event.ActionEvent evt) {
                SolicitudDTO solicitud = obtenerSolicitudSeleccionada();
                if (solicitud == null) {
                        JOptionPane.showMessageDialog(this,
                                        "Por favor selecciona una solicitud",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(this,
                                "¿Está seguro de aceptar la solicitud de " + solicitud.getNombreUsuario() + "?",
                                "Confirmar Aceptación",
                                JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                        ResultadoOperacion resultado = controlPresentacion.aceptarSolicitud(
                                        solicitud.getId(), "admin@gmail.com");
                        if (resultado.isExitoso()) {
                                controlPresentacion.mostrarCorreoConfirmacion(solicitud.getCorreoUsuario());
                        } else {
                                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        private void btncancelarcitaActionPerformed(java.awt.event.ActionEvent evt) {
                SolicitudDTO solicitud = obtenerSolicitudSeleccionada();
                if (solicitud == null) {
                        JOptionPane.showMessageDialog(this,
                                        "Por favor selecciona una solicitud",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(this,
                                "¿Está seguro de cancelar/rechazar la solicitud de " + solicitud.getNombreUsuario()
                                                + "?",
                                "Confirmar Cancelación",
                                JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                        ResultadoOperacion resultado = controlPresentacion.rechazarSolicitud(
                                        solicitud.getId(), "admin@gmail.com");
                        if (resultado.isExitoso()) {
                                controlPresentacion.mostrarCorreoConfirmacion(solicitud.getCorreoUsuario());
                        } else {
                                JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAceptarCita;
        private javax.swing.JButton btnActualizar;
        private javax.swing.JButton btnInicio;
        private javax.swing.JButton btnModificar;
        private javax.swing.JButton btncancelarcita;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable tablaSolicitudes;
        // End of variables declaration//GEN-END:variables
}
