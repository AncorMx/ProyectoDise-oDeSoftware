/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.flujoAdoptar;

import gui.flujoAdoptar.FrmInfoPersonal;
import DTOS.MascotaDTO;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Ventana que muestra la información detallada de una mascota seleccionada.
 * Permite visualizar datos como nombre, edad, temperamento, y estatus médico,
 * así como iniciar el proceso de adopción.
 * 
 * @author angel
 */
public class FrmInformacionMascota extends javax.swing.JFrame {

        /** DTO con la información de la mascota a mostrar. */
        private MascotaDTO mascota;
        /** Controlador de presentación para la navegación y lógica. */
        private control.ControlPresentacion controlPresentacion;
        /** Referencia a la ventana de información personal (si aplica). */
        private FrmInfoPersonal frmInfoPersonal;

        /**
         * Crea una nueva instancia de FrmInformacionMascota.
         */
        public FrmInformacionMascota() {
                initComponents();
        }

        /**
         * Establece el controlador de presentación.
         * 
         * @param controlPresentacion El controlador a asociar.
         */
        public void setControlPresentacion(control.ControlPresentacion controlPresentacion) {
                this.controlPresentacion = controlPresentacion;
        }

        /**
         * Constructor con mascota
         */
        public FrmInformacionMascota(MascotaDTO mascota) {
                initComponents();
                this.mascota = mascota;
                cargarDatosMascota();
        }

        /**
         * Crea una nueva instancia de FrmInformacionMascota con una mascota específica
         * y el controlador.
         * 
         * @param mascota             DTO con los datos de la mascota a mostrar.
         * @param controlPresentacion Controlador de presentación.
         */
        public FrmInformacionMascota(MascotaDTO mascota, control.ControlPresentacion controlPresentacion) {
                initComponents();
                this.mascota = mascota;
                this.controlPresentacion = controlPresentacion;
                cargarDatosMascota();
        }

        /**
         * Carga los datos de la mascota en los componentes
         */
        private void cargarDatosMascota() {
                if (mascota != null) {
                        lblMascota1.setText(mascota.getNombre());
                        lblDetalles1.setText(
                                        "Especie: " + mascota.getEspecie() + " | Edad: " + mascota.getEdad() + " años");
                        lblTemperamento1.setText(mascota.getPersonalidad() != null ? mascota.getPersonalidad()
                                        : "No especificado");
                        jLabel10.setText(mascota.getEstadoSalud() != null ? mascota.getEstadoSalud()
                                        : "No especificado");
                        // lblRequisitos1 puede poblarse con datos adicionales si existen

                        // Cargar imagen si existe
                        cargarImagen(mascota.getUrlImagen());
                }
        }

        /**
         * Constructor con mascota y referencia al control de presentación
         */

        /**
         * Carga la imagen de la mascota
         */
        private void cargarImagen(String urlImagen) {
                try {
                        if (urlImagen != null && !urlImagen.isEmpty()) {
                                ImageIcon icon = null;

                                // Intentar cargar desde resources primero
                                if (urlImagen.startsWith("/")) {
                                        java.net.URL imgURL = getClass().getResource(urlImagen);
                                        if (imgURL != null) {
                                                icon = new ImageIcon(imgURL);
                                        }
                                } else {
                                        File imgFile = new File(urlImagen);
                                        if (imgFile.exists()) {
                                                icon = new ImageIcon(urlImagen);
                                        }
                                }

                                // Si se cargó la imagen, ajustar tamaño y mostrar
                                if (icon != null && icon.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
                                        Image img = icon.getImage().getScaledInstance(330, 330, Image.SCALE_SMOOTH);
                                        jLabel12.setIcon(new ImageIcon(img));
                                        jLabel12.setText("");
                                } else {
                                        jLabel12.setText("Imagen no disponible");
                                }
                        } else {
                                jLabel12.setText("Sin imagen");
                        }
                } catch (Exception e) {
                        jLabel12.setText("Error al cargar imagen");
                        e.printStackTrace();
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                lblMascota1 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                lblDetalles1 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                lblTemperamento1 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                lblRequisitos1 = new javax.swing.JLabel();
                jButton2 = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                jLabel12 = new javax.swing.JLabel();
                btnRegresar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(255, 244, 225));

                jPanel3.setBackground(new java.awt.Color(255, 244, 225));

                lblMascota1.setBackground(new java.awt.Color(0, 0, 0));
                lblMascota1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                lblMascota1.setText("NOMBRE MASCOTA");

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
                jLabel7.setText("Detalles");

                lblDetalles1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblDetalles1.setText("DETALLES DE LA MASCOTA");

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
                jLabel8.setText("Temperamento");

                lblTemperamento1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblTemperamento1.setText("TEMPERAMENTO DE LA MASCOTA");

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
                jLabel9.setText("Estatus Médico");

                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel10.setText("ESTATUS MEDICO DE LA MASCOTA");

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
                jLabel11.setText("Requisitos del Hogar ");

                lblRequisitos1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblRequisitos1.setText("REQUISITOS DEL HOGAR PARA LA MASCOTA");

                jButton2.setBackground(new java.awt.Color(33, 110, 33));
                jButton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                jButton2.setText("ADOPTAR");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap(524, Short.MAX_VALUE)
                                                                .addComponent(jButton2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                221,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(56, 56, 56))
                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGap(72, 72, 72)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(lblMascota1)
                                                                                                .addComponent(jLabel7)
                                                                                                .addComponent(jLabel8)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(6, 6, 6)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jLabel9)
                                                                                                                                .addComponent(jLabel10,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                507,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(lblDetalles1)
                                                                                                                                .addComponent(lblTemperamento1)
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addGap(6, 6, 6)
                                                                                                                                                .addComponent(lblRequisitos1))
                                                                                                                                .addComponent(jLabel11))))
                                                                                .addContainerGap(216,
                                                                                                Short.MAX_VALUE))));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(229, Short.MAX_VALUE)
                                                                .addComponent(jButton2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                111,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(178, 178, 178))
                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(lblMascota1)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel7)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblDetalles1)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel8)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblTemperamento1)
                                                                                .addGap(34, 34, 34)
                                                                                .addComponent(jLabel9)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel10,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                123,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(32, 32, 32)
                                                                                .addComponent(jLabel11)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(lblRequisitos1)
                                                                                .addContainerGap(
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));

                jPanel4.setBackground(new java.awt.Color(255, 244, 225));

                jLabel12.setText("IMAGEN MASCOTA");
                jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addComponent(jLabel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                330,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(43, Short.MAX_VALUE)
                                                                .addComponent(jLabel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                330,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)));

                btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                btnRegresar.setText("Regresar");
                btnRegresar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                btnRegresar.setBorderPainted(false);
                btnRegresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRegresarActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(25, 25, 25)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(121, 121, 121)
                                                                                                .addComponent(btnRegresar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                221,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(96, 96, 96)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(41, 41, 41)
                                                                                                .addComponent(jPanel3,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(70, 70, 70)
                                                                                                .addComponent(jPanel4,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(btnRegresar)))
                                                                .addContainerGap(108, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        /**
         * Maneja el evento de clic en el botón de Adoptar.
         * Notifica al controlador para continuar con el proceso de solicitud de
         * adopción.
         * 
         * @param evt El evento de acción.
         */
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
                // Notificar al control para continuar con el proceso de adopción
                if (controlPresentacion != null) {
                        controlPresentacion.continuarConSolicitud();
                } else {
                        System.err.println("Error: No hay referencia al ControlPresentacion");
                }
        }// GEN-LAST:event_jButton2ActionPerformed

        /**
         * Maneja el evento de regresar al menú de especies.
         * Cierra la ventana actual y notifica al controlador.
         * 
         * @param evt El evento de acción.
         */
        private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegresarActionPerformed
                if (controlPresentacion != null) {
                        controlPresentacion.regresarAlMenuEspecies();
                        this.dispose(); // Cerrar ventana actual
                }
        }// GEN-LAST:event_btnRegresarActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        /** Botón para regresar al menú anterior */
        private javax.swing.JButton btnRegresar;
        /** Botón para iniciar la adopción */
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        /** Etiqueta para detalles de la mascota */
        private javax.swing.JLabel lblDetalles1;
        /** Etiqueta para nombre de la mascota */
        private javax.swing.JLabel lblMascota1;
        /** Etiqueta para requisitos de la mascota */
        private javax.swing.JLabel lblRequisitos1;
        /** Etiqueta para temperamento de la mascota */
        private javax.swing.JLabel lblTemperamento1;
        // End of variables declaration//GEN-END:variables
}
