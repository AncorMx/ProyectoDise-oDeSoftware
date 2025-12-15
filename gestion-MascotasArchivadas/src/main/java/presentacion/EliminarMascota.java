/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

/**
 *
 * @author angel
 */
public class EliminarMascota extends javax.swing.JPanel {

        private String mascotaId;
        private gestion.mascotasarchivadas.control.ControlPresentacion controlPresentacion;

        /**
         * Creates new form EliminarMascota
         */
        public EliminarMascota() {
                initComponents();
                this.controlPresentacion = new gestion.mascotasarchivadas.control.ControlPresentacion();
                CamposNoEditables();
        }

        public void setMascotaId(String id) {
                this.mascotaId = id;
                cargarDatosMascota();
                // Aquí puedes agregar lógica para mostrar datos de la mascota si quieres
        }

        private void CamposNoEditables() {
                tfNombre.setEditable(false);
                tfEspecie.setEditable(false);
                tfRaza.setEditable(false);
                tfColor.setEditable(false);
                tfEdad.setEditable(false);
                tfPeso.setEditable(false);
                tfCondicion.setEditable(false);
        }

        private void cargarDatosMascota() {
                if (mascotaId == null || mascotaId.isEmpty()) {
                        return;
                }

                try {
                        gestion.mascotasarchivadas.dtos.MascotaArchivoDTO mascota = controlPresentacion
                                        .obtenerDetalles(mascotaId);

                        if (mascota != null) {
                                tfNombre.setText(mascota.getNombre());
                                tfEspecie.setText(mascota.getEspecie());
                                tfRaza.setText(mascota.getRaza());
                                tfColor.setText(mascota.getColor());
                                tfEdad.setText(String.valueOf(mascota.getEdad()));
                                tfPeso.setText(String.valueOf(mascota.getPeso()));
                                tfCondicion.setText(mascota.getEstadoSalud());

                                String estadoMascota = mascota.getEstado();

                                String historialBajas = obtenerHistorialBajas(estadoMascota);

                                textArea1.setText(historialBajas);

                                if (mascota.getUrlImagen() != null && !mascota.getUrlImagen().isEmpty()) {
                                        try {
                                                java.io.File imageFile = new java.io.File(mascota.getUrlImagen());
                                                if (imageFile.exists()) {
                                                        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
                                                                        imageFile.getAbsolutePath());
                                                        java.awt.Image img = icon.getImage().getScaledInstance(200, 200,
                                                                        java.awt.Image.SCALE_SMOOTH);
                                                        lbl_Img.setIcon(new javax.swing.ImageIcon(img));
                                                        lbl_Img.setText("");
                                                }
                                        } catch (Exception e) {
                                                System.err.println("Error al cargar imagen: " + e.getMessage());
                                        }
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * Extrae el historial de bajas del campo estado Formato esperado: "BAJA:
         * motivo | Prev: estado_anterior"
         */
        private String obtenerHistorialBajas(String estado) {
                if (estado == null || estado.isEmpty()) {
                        return "";
                }

                StringBuilder historial = new StringBuilder();
                String estadoActual = estado;
                int contadorBaja = 1;

                // Parsear el historial en formato "BAJA: motivo | Prev: estado_previo"
                while (estadoActual != null && estadoActual.startsWith("BAJA:")) {
                        try {
                                // Extraer motivo de baja
                                int inicioMotivo = estadoActual.indexOf("BAJA:") + 5;
                                int finMotivo = estadoActual.indexOf(" | Prev:");

                                if (finMotivo > inicioMotivo) {
                                        String motivo = estadoActual.substring(inicioMotivo, finMotivo).trim();
                                        historial.append("BAJA #").append(contadorBaja).append(":\n");
                                        historial.append(motivo).append("\n\n");
                                        contadorBaja++;

                                        // Obtener estado previo
                                        int inicioPrev = estadoActual.indexOf(" | Prev:") + 8;
                                        if (inicioPrev > 8 && inicioPrev < estadoActual.length()) {
                                                estadoActual = estadoActual.substring(inicioPrev).trim();
                                        } else {
                                                break;
                                        }
                                } else {
                                        break;
                                }
                        } catch (Exception e) {
                                System.err.println("Error al parsear historial: " + e.getMessage());
                                break;
                        }
                }

                return historial.toString();

        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbl_Img = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfRaza = new javax.swing.JTextField();
        tfCondicion = new javax.swing.JTextField();
        tfPeso = new javax.swing.JTextField();
        tfEdad = new javax.swing.JTextField();
        tfColor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfEspecie = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        jLabel8 = new javax.swing.JLabel();
        btn_Eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(219, 213, 195));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        lbl_Img.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Seguro que desea ELIMINAR a esta mascota?");

        tfRaza.setBackground(new java.awt.Color(204, 204, 204));

        tfCondicion.setBackground(new java.awt.Color(204, 204, 204));

        tfPeso.setBackground(new java.awt.Color(204, 204, 204));

        tfEdad.setBackground(new java.awt.Color(204, 204, 204));

        tfColor.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Condición");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Peso");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Edad");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Color");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Raza");

        tfEspecie.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Especie");

        tfNombre.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("MOTIVOS DE BAJA:");

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(25, 25, 25)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel13)
                                                        .addComponent(tfColor)
                                                        .addComponent(jLabel9)
                                                        .addComponent(tfCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12)
                                            .addComponent(tfRaza)
                                            .addComponent(jLabel14)
                                            .addComponent(tfEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(tfPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addGap(145, 145, 145)))
                        .addComponent(lbl_Img, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(328, 328, 328))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(8, 8, 8)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbl_Img, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 189, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

        private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_cancelarActionPerformed
                // Regresar a Detalles
                FramePrincipal frame = (FramePrincipal) javax.swing.SwingUtilities.getWindowAncestor(this);
                if (frame != null) {
                        frame.mostrarPanel("DETALLES");
                }
        }// GEN-LAST:event_btn_cancelarActionPerformed

        private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_EliminarActionPerformed
                // Confirmar si realmente desea eliminar
                int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                                this,
                                "¿Está seguro que desea ELIMINAR PERMANENTEMENTE esta mascota?\n\n" +
                                                "Esta acción NO SE PUEDE DESHACER.\n" +
                                                "Todos los datos de la mascota serán borrados de la base de datos.",
                                "Confirmar Eliminación Permanente",
                                javax.swing.JOptionPane.YES_NO_OPTION,
                                javax.swing.JOptionPane.WARNING_MESSAGE);

                if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                        try {
                                // Eliminar permanentemente de la base de datos
                                gestion.mascotasarchivadas.dtos.ResultadoOperacion resultado = controlPresentacion
                                                .eliminarPermanente(this.mascotaId);

                                if (resultado.isExitoso()) {
                                        javax.swing.JOptionPane.showMessageDialog(
                                                        this,
                                                        resultado.getMensaje(),
                                                        "Eliminación Exitosa",
                                                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

                                        // Recargar catálogo y regresar
                                        FramePrincipal frame = (FramePrincipal) javax.swing.SwingUtilities
                                                        .getWindowAncestor(this);
                                        if (frame != null) {
                                                frame.recargarCatalogo(); // Recargar datos antes de mostrar
                                                frame.mostrarPanel("CATALOGO");
                                        }
                                } else {
                                        javax.swing.JOptionPane.showMessageDialog(
                                                        this,
                                                        resultado.getMensaje(),
                                                        "Error al Eliminar",
                                                        javax.swing.JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                                javax.swing.JOptionPane.showMessageDialog(
                                                this,
                                                "Error inesperado al eliminar la mascota: " + e.getMessage(),
                                                "Error",
                                                javax.swing.JOptionPane.ERROR_MESSAGE);
                        }
                }
        }// GEN-LAST:event_btn_EliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_Img;
    private java.awt.TextArea textArea1;
    private javax.swing.JTextField tfColor;
    private javax.swing.JTextField tfCondicion;
    private javax.swing.JTextField tfEdad;
    private javax.swing.JTextField tfEspecie;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfPeso;
    private javax.swing.JTextField tfRaza;
    // End of variables declaration//GEN-END:variables
}
