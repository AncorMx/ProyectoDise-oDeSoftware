/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

import gestion.mascotasarchivadas.control.ControlPresentacion;
import gestion.mascotasarchivadas.dtos.MascotaArchivoDTO;
import java.util.List;

/**
 *
 * @author angel
 */
public class CatalogoMascotasArchivadas extends javax.swing.JPanel {

    /**
     * Controlador de presentación.
     */
    private ControlPresentacion controlPresentacion;

    /**
     * Creates new form CatalogoMascotasArchivadas
     */
    public CatalogoMascotasArchivadas() {
        initComponents();

        // Inicializar control
        this.controlPresentacion = new gestion.mascotasarchivadas.control.ControlPresentacion();

        // Configurar ComboBox de especies
        configurarComboBoxEspecies();

        // Cargar catálogo al iniciar
        cargarCatalogo();

        // Configurar scroll
        JScrollPaneCatalogo.getVerticalScrollBar().setUnitIncrement(16);
        JScrollPaneCatalogo.getVerticalScrollBar().setBlockIncrement(100);
    }

    /**
     * Carga el catálogo de mascotas archivadas desde la base de datos.
     * Muestra las cartas de mascotas en el panel.
     */
    public void cargarCatalogo() {
        try {
            // Limpiar panel
            JPaneCatalogo.removeAll();

            // Obtener mascotas ARCHIVADAS (disponible=false o estado="baja")
            List<MascotaArchivoDTO> mascotasArchivadas = controlPresentacion.obtenerMascotasArchivadas();

            if (mascotasArchivadas == null || mascotasArchivadas.isEmpty()) {
                System.out.println("No hay mascotas archivadas");
                JPaneCatalogo.revalidate();
                JPaneCatalogo.repaint();
                return;
            }

            System.out.println("Cargando " + mascotasArchivadas.size() + " mascotas archivadas...");

            // Crear PetCard por cada mascota archivada
            for (MascotaArchivoDTO mascota : mascotasArchivadas) {
                System.out.println("Creando card para: " + mascota.getNombre()
                        + " (ID: " + mascota.getId() + ")");

                PetCardPanel card = new PetCardPanel();

                // Setear datos de la mascota
                card.setMascotaId(mascota.getId());
                card.setNombreMascota(mascota.getNombre());
                card.setImagenMascota(mascota.getUrlImagen());

                // Listener para ver detalles
                card.agregarListenerDetalle(e -> {
                    // Obtener la ventana ancestro sin hacer cast específico
                    java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
                    if (window instanceof FramePrincipal) {
                        FramePrincipal frame = (FramePrincipal) window;
                        // Obtener panel de detalles y pasarle el ID
                        DetallesMascota detalles = frame.obtenerPanelDetalles();
                        if (detalles != null) {
                            detalles.setMascotaId(mascota.getId());
                        }
                        frame.mostrarPanel("DETALLES");
                    } else {
                        System.err.println("Error: Ventana ancestro no es FramePrincipal. Tipo: " +
                                (window != null ? window.getClass().getName() : "null"));
                        javax.swing.JOptionPane.showMessageDialog(this,
                                "Error de navegación. Por favor, abra el módulo desde el menú de administración.",
                                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });

                JPaneCatalogo.add(card);
            }

            // Refrescar UI
            JPaneCatalogo.revalidate();
            JPaneCatalogo.repaint();

            System.out.println("Catálogo de mascotas archivadas cargado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al cargar catálogo: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarComboBoxEspecies() {
        cbFiltro.removeAllItems();
        cbFiltro.addItem("TODAS");
        cbFiltro.addItem("Perro");
        cbFiltro.addItem("Gato");
        cbFiltro.addItem("Ave");
        cbFiltro.addItem("Reptil");
        cbFiltro.addItem("Otro");
    }

    /**
     * Aplica el filtro de especie seleccionado y recarga el catálogo
     */
    private void aplicarFiltro() {
        try {
            // Limpiar panel
            JPaneCatalogo.removeAll();

            // Obtener especie seleccionada
            String especieSeleccionada = cbFiltro.getSelectedItem() != null
                    ? cbFiltro.getSelectedItem().toString()
                    : "Todas las especies";

            // Obtener todas las mascotas archivadas
            List<MascotaArchivoDTO> todasLasMascotas = controlPresentacion.obtenerMascotasArchivadas();

            if (todasLasMascotas == null || todasLasMascotas.isEmpty()) {
                System.out.println("No hay mascotas archivadas");
                JPaneCatalogo.revalidate();
                JPaneCatalogo.repaint();
                return;
            }

            System.out.println("Filtrando por especie: " + especieSeleccionada);

            // Crear cards solo para las mascotas que coincidan con el filtro
            for (MascotaArchivoDTO mascota : todasLasMascotas) {
                // Aplicar filtro: si es "Todas" mostrar todo, sino filtrar por especie
                if (!especieSeleccionada.equals("Todas las especies")
                        && !mascota.getEspecie().equalsIgnoreCase(especieSeleccionada)) {
                    continue; // Saltar esta mascota si no coincide con el filtro
                }

                PetCardPanel card = new PetCardPanel();
                card.setMascotaId(mascota.getId());
                card.setNombreMascota(mascota.getNombre());
                card.setImagenMascota(mascota.getUrlImagen());

                card.agregarListenerDetalle(e -> {
                    // Obtener la ventana ancestro sin hacer cast específico
                    java.awt.Window window = javax.swing.SwingUtilities.getWindowAncestor(this);
                    if (window instanceof FramePrincipal) {
                        FramePrincipal frame = (FramePrincipal) window;
                        // Obtener panel de detalles y pasarle el ID
                        DetallesMascota detalles = frame.obtenerPanelDetalles();
                        if (detalles != null) {
                            detalles.setMascotaId(mascota.getId());
                        }
                        frame.mostrarPanel("DETALLES");
                    } else {
                        System.err.println("Error: Ventana ancestro no es FramePrincipal en filtro. Tipo: " +
                                (window != null ? window.getClass().getName() : "null"));
                        javax.swing.JOptionPane.showMessageDialog(this,
                                "Error de navegación. Por favor, abra el módulo desde el menú de administración.",
                                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });

                JPaneCatalogo.add(card);
            }

            // Refrescar UI
            JPaneCatalogo.revalidate();
            JPaneCatalogo.repaint();

            System.out.println("Catálogo filtrado actualizado");

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al filtrar catálogo: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPaneCatalogo = new javax.swing.JScrollPane();
        JPaneCatalogo = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        perro6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        perro5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        perro3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(219, 213, 195));

        JScrollPaneCatalogo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPaneCatalogo.setBackground(new java.awt.Color(235, 229, 220));
        JPaneCatalogo.setLayout(new java.awt.GridLayout(0, 3, 15, 15));

        jLabel9.setText("Cacao");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(perro6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(207, 207, 207))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(perro6)
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addContainerGap(362, Short.MAX_VALUE))
        );

        JPaneCatalogo.add(jPanel3);

        jLabel4.setText("Pinto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(perro5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel4)))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(perro5)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(361, Short.MAX_VALUE))
        );

        JPaneCatalogo.add(jPanel2);

        jLabel3.setText("Rufus");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(perro3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel3)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(perro3)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(361, Short.MAX_VALUE))
        );

        JPaneCatalogo.add(jPanel4);

        JScrollPaneCatalogo.setViewportView(JPaneCatalogo);

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltroActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("FILTRAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JScrollPaneCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 1350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(89, 89, 89))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(JScrollPaneCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbFiltroActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbFiltroActionPerformed
        aplicarFiltro(); // Filtrar en tiempo real al cambiar la selección
    }// GEN-LAST:event_cbFiltroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPaneCatalogo;
    private javax.swing.JScrollPane JScrollPaneCatalogo;
    private javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel perro3;
    private javax.swing.JLabel perro5;
    private javax.swing.JLabel perro6;
    // End of variables declaration//GEN-END:variables
}
