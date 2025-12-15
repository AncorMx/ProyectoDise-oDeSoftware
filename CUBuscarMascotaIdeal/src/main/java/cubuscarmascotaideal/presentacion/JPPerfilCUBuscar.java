/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cubuscarmascotaideal.presentacion;

import cubuscarmascotaideal.control.ControlPresentacion;
import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.negocio.dtos.MascotaResultadoDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel de perfil del usuario con búsqueda de mascotas ideales
 * Integrado con el sistema de sesión y CardLayout del menú principal
 * 
 * @author Josel
 */
public class JPPerfilCUBuscar extends javax.swing.JPanel {

    private ControlPresentacion controlPresentacion;
    private JPEncuesta encuestaPanel;
    private AdopcionListener adopcionListener;
    private Runnable cerrarSesionListener;
    private String idUsuario; // ID del usuario para guardar resultados

    /**
     * Interface para manejar navegación a flujo de adopción
     */
    public interface AdopcionListener {
        void onAdoptarMascota(String idMascota, String nombreMascota, double porcentajeCompatibilidad);
    }

    /**
     * Creates new form JPPerfilCUBuscar
     */
    public JPPerfilCUBuscar() {
        initComponents();
        configurarEncuesta();
        configurarBotones();
    }

    /**
     * Establece el listener para el flujo de adopción
     */
    public void setAdopcionListener(AdopcionListener listener) {
        this.adopcionListener = listener;
    }

    /**
     * Establece el listener para cerrar sesión
     */
    public void setCerrarSesionListener(Runnable listener) {
        this.cerrarSesionListener = listener;
    }

    /**
     * Establece el control de presentación y carga los datos del usuario
     */
    public void setControlPresentacion(ControlPresentacion controlPresentacion) {
        this.controlPresentacion = controlPresentacion;
        // cargarDatosUsuario(); // Descomentar cuando se implemente login
    }

    /**
     * Configura el panel de encuesta y sus listeners
     */
    private void configurarEncuesta() {
        encuestaPanel = new JPEncuesta();
        // noHayMascotasPanel = new JPNohayMascotas(); // Removed

        // Listener para cuando se completa la encuesta
        encuestaPanel.setEncuestaListener(criterios -> {
            buscarMascotasIdeales(criterios);
        });
    }

    /**
     * Configura los listeners de los botones
     */
    private void configurarBotones() {
        // Botón para iniciar búsqueda de mascota ideal
        // Botón para cerrar sesión
    }

    /**
     * Carga los datos del usuario actual en el perfil
     */
    public void cargarDatosUsuario() {
        // Use setDatosUsuario for loading user data from external source
        lblNombre.setText("Usuario");
        lblCorreo.setText("email@example.com");
        lblCurp.setText("CURP");
    }

    /**
     * Establece los datos del usuario directamente en los labels del perfil
     * 
     * @param nombre Nombre del usuario
     * @param correo Correo electrónico del usuario
     * @param curp   CURP del usuario
     */
    public void setDatosUsuario(String nombre, String correo, String curp) {
        if (nombre != null)
            lblNombre.setText(nombre);
        if (correo != null)
            lblCorreo.setText(correo);
        if (curp != null)
            lblCurp.setText(curp);
    }

    /**
     * Establece el ID del usuario para guardar resultados
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        // Cargar resultados guardados si existen
        if (idUsuario != null && controlPresentacion != null) {
            cargarResultadosGuardados();
        }
    }

    /**
     * Carga los resultados guardados del usuario actual
     */
    private void cargarResultadosGuardados() {
        if (idUsuario == null || controlPresentacion == null) {
            return;
        }
        try {
            List<MascotaResultadoDTO> guardados = controlPresentacion.obtenerResultadosGuardados(idUsuario);
            if (!guardados.isEmpty()) {
                mostrarMascotasEnScroll(guardados);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar resultados guardados: " + e.getMessage());
        }
    }

    /**
     * Carga las mascotas ideales guardadas del usuario
     */
    private void cargarMascotasIdealesGuardadas(String idUsuario) {
        // This method is no longer relevant in this context
        // It was likely used for a previous iteration of the feature
    }

    /**
     * Busca mascotas ideales basadas en los criterios de la encuesta
     */
    private void buscarMascotasIdeales(EncuestaDTO encuesta) {
        if (controlPresentacion == null) {
            JOptionPane.showMessageDialog(this,
                    "Error: Sistema no inicializado",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Buscar mascotas compatibles
            List<MascotaResultadoDTO> mascotasCompatibles = controlPresentacion.buscarMascotasCompatibles(encuesta);

            if (mascotasCompatibles.isEmpty()) {
                // No se encontraron mascotas compatibles
                mostrarPanelNoHayMascotas();
            } else {
                // Guardar resultados automáticamente si hay usuario
                if (idUsuario != null && !idUsuario.isEmpty()) {
                    controlPresentacion.guardarResultados(idUsuario, mascotasCompatibles);
                }

                // Volver al perfil para mostrar los resultados
                volverAPerfil();
                // Mostrar las mascotas encontradas
                mostrarMascotasEnScroll(mascotasCompatibles);
                JOptionPane.showMessageDialog(this,
                        "Se encontraron " + mascotasCompatibles.size() + " mascotas compatibles",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar mascotas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Muestra las mascotas ideales en el ScrollPane
     */
    private void mostrarMascotasEnScroll(List<MascotaResultadoDTO> mascotasCompatibles) {
        // TODO: Implementar visualización de mascotas
        // Por ahora solo mostramos un mensaje
        System.out.println("Mostrando " + mascotasCompatibles.size() + " mascotas:");
        for (MascotaResultadoDTO mascota : mascotasCompatibles) {
            System.out.println("- " + mascota.getNombre() +
                    " (" + String.format("%.0f", mascota.getPorcentajeCompatibilidad()) + "% compatible)");
        }

        // Aquí deberías crear PetCardIdealPanel para cada mascota
        // y agregarlos a un panel con scroll

        // Crear panel contenedor con FlowLayout
        JPanel contenedorMascotas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        contenedorMascotas.setBackground(new java.awt.Color(255, 255, 204));

        // Agregar tarjetas de mascotas
        for (MascotaResultadoDTO mascotaIdeal : mascotasCompatibles) {
            PetCardIdealPanel tarjeta = new PetCardIdealPanel();
            tarjeta.setMascotaResultado(mascotaIdeal);

            // Listener del botón adoptar
            tarjeta.agregarListenerAdoptar(e -> {
                abrirFlujoAdopcion(mascotaIdeal);
            });

            contenedorMascotas.add(tarjeta);
        }

        // Establecer el panel en el ScrollPane
        SPNMostrarMascotasIdeales.setViewportView(contenedorMascotas);
        SPNMostrarMascotasIdeales.revalidate();
        SPNMostrarMascotasIdeales.repaint();
    }

    /**
     * Abre el flujo de adopción con la mascota seleccionada
     */
    private void abrirFlujoAdopcion(MascotaResultadoDTO mascotaResultado) {
        if (adopcionListener != null) {
            // Usar el callback para que el módulo Presentacion maneje la navegación
            adopcionListener.onAdoptarMascota(
                    mascotaResultado.getId(),
                    mascotaResultado.getNombre(),
                    mascotaResultado.getPorcentajeCompatibilidad());
        } else {
            JOptionPane.showMessageDialog(this,
                    "Funcionalidad de adopción para: " + mascotaResultado.getNombre() +
                            "\n(Compatibilidad: "
                            + String.format("%.0f", mascotaResultado.getPorcentajeCompatibilidad()) + "%)" +
                            "\n\nAdopcionListener no configurado.",
                    "Iniciar Adopción", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Muestra el panel de encuesta
     */
    private void mostrarEncuesta() {
        // Ocultar el panel principal y mostrar la encuesta
        jPanel3.setVisible(false);
        removeAll();
        setLayout(new BorderLayout());
        add(encuestaPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Muestra mensaje cuando no hay mascotas compatibles
     */
    private void mostrarPanelNoHayMascotas() {
        JOptionPane.showMessageDialog(this,
                "No se encontraron mascotas compatibles.\nIntenta ajustar tus criterios de búsqueda.",
                "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Vuelve a mostrar el panel de perfil principal
     */
    private void volverAPerfil() {
        removeAll();
        setLayout(new BorderLayout());
        add(jPanel3, BorderLayout.CENTER);
        jPanel3.setVisible(true);
        revalidate();
        repaint();
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnBuscarMascotaIdeal = new javax.swing.JButton();
        SPNMostrarMascotasIdeales = new javax.swing.JScrollPane();

        jPanel3.setBackground(new java.awt.Color(219, 213, 195));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setText("PERFIL");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 310, 80));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("CURP:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 100, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 100, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Correo:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 100, 40));

        lblCurp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCurp.setText("nombre");
        jPanel3.add(lblCurp, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 610, -1));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("nombre");
        jPanel3.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 600, -1));

        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCorreo.setText("nombre");
        jPanel3.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 590, -1));

        btnCerrarSesion.setBackground(new java.awt.Color(204, 0, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jPanel3.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 390, 60));

        btnBuscarMascotaIdeal.setBackground(new java.awt.Color(0, 153, 51));
        btnBuscarMascotaIdeal.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        btnBuscarMascotaIdeal.setText("Buscar mascota ideal");
        btnBuscarMascotaIdeal
                .setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarMascotaIdeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMascotaIdealActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarMascotaIdeal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 300, -1));

        SPNMostrarMascotasIdeales.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.add(SPNMostrarMascotasIdeales, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 740, 210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1296,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 688,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarMascotaIdealActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarMascotaIdealActionPerformed
        // Validar que haya control de presentación activo
        if (controlPresentacion == null) {
            JOptionPane.showMessageDialog(this,
                    "Sistema no inicializado",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mostrar la encuesta
        mostrarEncuesta();
    }// GEN-LAST:event_btnBuscarMascotaIdealActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarSesionActionPerformed
        if (cerrarSesionListener != null) {
            cerrarSesionListener.run();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se puede cerrar sesión en este momento",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_btnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPNMostrarMascotasIdeales;
    private javax.swing.JButton btnBuscarMascotaIdeal;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
