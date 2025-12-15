/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.flujoAdoptar;

import DTOS.MascotaDTO;
import gui.PetCardPanel;
import negocio.subsistemas.mascotas.FachadaMascotas;
import negocio.subsistemas.mascotas.IMascotas;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
/**
 * Panel para mostrar el catálogo de especies de mascotas disponibles para
 * adopción.
 * Permite filtrar por especie y ver detalles de cada mascota.
 * 
 * @author angel
 */
public class MenuMostrarEspecies extends javax.swing.JPanel {

        private IMascotas fachadaMascotas;
        private control.ControlPresentacion controlPresentacion;

        /**
         * Crea un nuevo formulario MenuMostrarEspecies.
         * Inicializa la fachada de mascotas y carga el catálogo.
         */
        public MenuMostrarEspecies() {
                initComponents();
                this.fachadaMascotas = new FachadaMascotas();
                cargarEspecies();
                cargarMascotas();

                JScrollPaneCatalogo.getVerticalScrollBar().setUnitIncrement(16);
                JScrollPaneCatalogo.getVerticalScrollBar().setBlockIncrement(100);
        }

        /**
         * Carga las especies disponibles en el filtro.
         */
        private void cargarEspecies() {
                try {
                        cbFiltro.removeAllItems();
                        cbFiltro.addItem("Todas"); // Opción por defecto

                        // Obtener todas las mascotas para extraer especies únicas
                        List<MascotaDTO> mascotas = fachadaMascotas.buscarMascotasDisponibles();

                        // Extraer especies únicas
                        java.util.Set<String> especiesUnicas = new java.util.HashSet<>();
                        for (MascotaDTO mascota : mascotas) {
                                if (mascota.getEspecie() != null && !mascota.getEspecie().isEmpty()) {
                                        especiesUnicas.add(mascota.getEspecie());
                                }
                        }

                        // Agregar especies al ComboBox ordenadas alfabéticamente
                        java.util.List<String> especiesOrdenadas = new java.util.ArrayList<>(especiesUnicas);
                        java.util.Collections.sort(especiesOrdenadas);
                        for (String especie : especiesOrdenadas) {
                                cbFiltro.addItem(especie);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * Configura el panel del catálogo con layout adecuado
         */
        // private void configurarPanelCatalogo() {
        // JPaneCatalogo.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        // }

        /**
         * Establece el controlador de presentación.
         * 
         * @param controlPresentacion El controlador.
         */
        public void setControlPresentacion(control.ControlPresentacion controlPresentacion) {
                this.controlPresentacion = controlPresentacion;
        }

        /**
         * Carga las mascotas disponibles desde la base de datos
         */
        public void cargarMascotas() {
                try {
                        // Limpiar el panel antes de cargar
                        JPaneCatalogo.removeAll();

                        // Obtener la especie seleccionada del ComboBox
                        String especieSeleccionada = (String) cbFiltro.getSelectedItem();
                        String filtroEspecie = ("Todas".equals(especieSeleccionada)) ? null : especieSeleccionada;

                        // Obtener mascotas disponibles
                        List<MascotaDTO> mascotas = fachadaMascotas.buscarMascotasDisponibles();

                        // Si no hay mascotas, mostrar mensaje
                        if (mascotas == null || mascotas.isEmpty()) {
                                return;
                        }

                        // Crear una tarjeta por cada mascota
                        for (MascotaDTO mascota : mascotas) {
                                // Aplicar filtro de especie si está seleccionado
                                if (filtroEspecie != null && !filtroEspecie.equals(mascota.getEspecie())) {
                                        continue; // Saltar esta mascota si no coincide con el filtro
                                }

                                PetCardPanel card = new PetCardPanel();
                                card.setNombreMascota(mascota.getNombre());
                                card.setImagenMascota(mascota.getUrlImagen());
                                card.setMascotaId(mascota.getId());

                                card.agregarListenerDetalle(e -> {
                                        // Notificar al control sobre la selección de mascota
                                        if (controlPresentacion != null) {
                                                controlPresentacion.procesarSeleccionMascota(mascota.getId());
                                        } else {
                                                System.err.println("Error: No hay referencia al ControlPresentacion");
                                        }
                                });

                                JPaneCatalogo.add(card);
                        }

                        // Refrescar el panel
                        JPaneCatalogo.revalidate();
                        JPaneCatalogo.repaint();

                } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,
                                        "Error al cargar las mascotas: " + e.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        /**
         * Maneja el evento de cambio de selección en el filtro de especies.
         * 
         * @param evt Evento de acción.
         */
        private void cbFiltroActionPerformed(java.awt.event.ActionEvent evt) {
                cargarMascotas(); // Recargar catálogo con el nuevo filtro
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
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                JScrollPaneCatalogo = new javax.swing.JScrollPane();
                JPaneCatalogo = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                perro6 = new javax.swing.JLabel();
                btn_infoMascota2 = new javax.swing.JButton();
                jLabel9 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                perro5 = new javax.swing.JLabel();
                btn_infoMascota1 = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                perro3 = new javax.swing.JLabel();
                btn_infoMascota = new javax.swing.JButton();
                jLabel3 = new javax.swing.JLabel();
                cbFiltro = new javax.swing.JComboBox<>();
                jLabel2 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(219, 213, 195));

                jPanel1.setBackground(new java.awt.Color(219, 213, 195));

                JScrollPaneCatalogo.setHorizontalScrollBarPolicy(
                                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                JPaneCatalogo.setBackground(new java.awt.Color(235, 229, 220));
                JPaneCatalogo.setLayout(new java.awt.GridLayout(0, 3, 15, 15));

                btn_infoMascota2.setText("VER DETALLES");
                btn_infoMascota2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_infoMascota2ActionPerformed(evt);
                        }
                });

                jLabel9.setText("Cacao");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(43, 43, 43)
                                                                .addComponent(perro6)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel3Layout.createSequentialGroup()
                                                                                                                .addComponent(btn_infoMascota2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                147,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(49, 49, 49))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel3Layout.createSequentialGroup()
                                                                                                                .addComponent(jLabel9)
                                                                                                                .addGap(107, 107,
                                                                                                                                107)))));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(perro6)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel9)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                305, Short.MAX_VALUE)
                                                                .addComponent(btn_infoMascota2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                38,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)));

                JPaneCatalogo.add(jPanel3);

                btn_infoMascota1.setText("VER DETALLES");
                btn_infoMascota1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_infoMascota1ActionPerformed(evt);
                        }
                });

                jLabel4.setText("Pinto");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(46, 46, 46)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(btn_infoMascota1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                147,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(perro5)))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(102, 102, 102)
                                                                                                .addComponent(jLabel4)))
                                                                .addContainerGap(189, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(perro5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                307, Short.MAX_VALUE)
                                                                .addComponent(btn_infoMascota1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                38,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24, 24, 24)));

                JPaneCatalogo.add(jPanel2);

                btn_infoMascota.setText("VER DETALLES");
                btn_infoMascota.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_infoMascotaActionPerformed(evt);
                        }
                });

                jLabel3.setText("Rufus");

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(46, 46, 46)
                                                                                                .addGroup(jPanel4Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(btn_infoMascota,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                147,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(perro3)))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(105, 105, 105)
                                                                                                .addComponent(jLabel3)))
                                                                .addContainerGap(189, Short.MAX_VALUE)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addComponent(perro3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                307, Short.MAX_VALUE)
                                                                .addComponent(btn_infoMascota,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                38,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24, 24, 24)));

                JPaneCatalogo.add(jPanel4);

                JScrollPaneCatalogo.setViewportView(JPaneCatalogo);

                cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                cbFiltro.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbFiltroActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(cbFiltro,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                126,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(JScrollPaneCatalogo,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                1183,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addComponent(cbFiltro,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                35, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(JScrollPaneCatalogo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                428,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
                jLabel2.setText("FILTRAR");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(29, 29, 29)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel2)
                                                                .addGap(40, 40, 40)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void btn_infoMascotaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_infoMascotaActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btn_infoMascotaActionPerformed

        private void btn_infoMascota1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_infoMascota1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btn_infoMascota1ActionPerformed

        private void btn_infoMascota2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_infoMascota2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btn_infoMascota2ActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel JPaneCatalogo;
        private javax.swing.JScrollPane JScrollPaneCatalogo;
        private javax.swing.JButton btn_infoMascota;
        private javax.swing.JButton btn_infoMascota1;
        private javax.swing.JButton btn_infoMascota2;
        private javax.swing.JComboBox<String> cbFiltro;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JLabel perro3;
        private javax.swing.JLabel perro5;
        private javax.swing.JLabel perro6;
        // End of variables declaration//GEN-END:variables
}
