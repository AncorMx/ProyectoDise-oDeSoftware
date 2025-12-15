/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.flujoAdoptar;

import cubuscarmascotaideal.presentacion.JPPerfilCUBuscar;
import gui.JPContacto;
import gui.JPSolicitudes;

/**
 * Ventana principal de la aplicación para el flujo de adopción.
 * Utiliza un CardLayout para gestionar la navegación entre las diferentes
 * pantallas
 * (catálogo, solicitudes, perfil, formularios, etc.).
 * 
 * @author angel
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    /** Layout manager para cambiar entre paneles */
    private java.awt.CardLayout cardLayout;
    /** Panel del catálogo de especies */
    private MenuMostrarEspecies menuMostrarEspecies;
    /** Formulario de información personal */
    private FrmInfoPersonal frmInfoPersonal;
    /** Formulario de información de vivienda */
    private FrmInfoVivienda frmInfoVivienda;
    /** Formulario de razones y antecedentes */
    private FrmRazonesAntecedentes frmRazonesAntecedentes;
    /** Panel de resumen de la solicitud */
    private JPInfoResumen frmInfoResumen;
    /** Panel de perfil de usuario con búsqueda de mascota ideal */
    private JPPerfilCUBuscar jpPerfilCUBuscar;

    /** Panel para listar solicitudes del usuario */
    private JPSolicitudes jpSolicitudes;
    /** Pantalla de confirmación de correo (placeholder) */
    private gui.FrmCorreoConfirmacion frmCorreoConfirmacion;

    /** Controlador de presentación */
    private control.ControlPresentacion controlPresentacion;

    /**
     * Crea una nueva instancia de FrmMenuPrincipal.
     * Inicializa los componentes gráficos y el layout de tarjetas.
     */
    public FrmMenuPrincipal() {
        initComponents();
        initCardLayout();
    }

    /**
     * Inicializa el CardLayout y añade todos los paneles de la aplicación.
     */
    private void initCardLayout() {
        cardLayout = new java.awt.CardLayout();
        panelContenidoDinamico.setLayout(cardLayout);

        // Instanciar paneles
        menuMostrarEspecies = new MenuMostrarEspecies();
        frmInfoPersonal = new FrmInfoPersonal();
        frmInfoVivienda = new FrmInfoVivienda();
        frmRazonesAntecedentes = new FrmRazonesAntecedentes();
        frmInfoResumen = new JPInfoResumen();
        jpSolicitudes = new JPSolicitudes();
        jpPerfilCUBuscar = new JPPerfilCUBuscar();
        // Configurar listener para el flujo de adopción
        jpPerfilCUBuscar.setAdopcionListener((idMascota, nombreMascota, porcentaje) -> {
            // Establecer la mascota seleccionada en el control
            if (controlPresentacion != null) {
                controlPresentacion.setIdMascotaSeleccionada(idMascota);
            }
            // Navegar al formulario de información personal
            mostrarInfoPersonal();
        });
        // Configurar listener para cerrar sesión
        jpPerfilCUBuscar.setCerrarSesionListener(() -> {
            if (controlPresentacion != null) {
                controlPresentacion.cerrarSesion();
            }
        });
        frmCorreoConfirmacion = new gui.FrmCorreoConfirmacion();

        // Agregar al CardLayout con identificadores clave
        panelContenidoDinamico.add(menuMostrarEspecies, "inicio");
        panelContenidoDinamico.add(jpSolicitudes, "solicitudes");
        panelContenidoDinamico.add(new JPContacto(), "contacto");
        panelContenidoDinamico.add(frmInfoPersonal, "infoPersonal"); // Agregar al layout
        panelContenidoDinamico.add(frmInfoVivienda, "infoVivienda");
        panelContenidoDinamico.add(frmRazonesAntecedentes, "razonesAntecedentes");
        panelContenidoDinamico.add(frmInfoResumen, "infoResumen");
        panelContenidoDinamico.add(jpPerfilCUBuscar, "perfil");
        panelContenidoDinamico.add(frmCorreoConfirmacion, "confirmacion");

        cardLayout.show(panelContenidoDinamico, "inicio");
    }

    /**
     * Establece el controlador de presentación y lo propaga a los paneles hijos.
     * 
     * @param control El controlador de presentación.
     */
    public void setControl(control.ControlPresentacion control) {
        this.controlPresentacion = control; // Guardar referencia

        if (menuMostrarEspecies != null) {
            menuMostrarEspecies.setControlPresentacion(control);
        }
        if (frmInfoPersonal != null) {
            frmInfoPersonal.setControlPresentacion(control);
        }
        if (frmInfoVivienda != null) {
            frmInfoVivienda.setControlPresentacion(control);
        }
        if (frmRazonesAntecedentes != null) {
            frmRazonesAntecedentes.setControlPresentacion(control);
        }
        if (frmInfoResumen != null) {
            frmInfoResumen.setControlPresentacion(control);
        }
        if (jpSolicitudes != null) {
            jpSolicitudes.setControlPresentacion(control);
        }
        if (jpPerfilCUBuscar != null) {
            // JPPerfilCUBuscar uses cubuscarmascotaideal.control.ControlPresentacion
            cubuscarmascotaideal.control.ControlPresentacion controlCUBuscar = new cubuscarmascotaideal.control.ControlPresentacion();
            jpPerfilCUBuscar.setControlPresentacion(controlCUBuscar);

            // Cargar datos del usuario actual en los labels del perfil
            if (control != null && control.getUsuarioActual() != null) {
                DTOS.UsuarioDTO usuario = control.getUsuarioActual();
                String nombre = usuario.getInfoPersonal() != null ? usuario.getInfoPersonal().getNombre() : "Usuario";
                String correo = usuario.getInfoPersonal() != null ? usuario.getInfoPersonal().getCorreo() : "";
                String curp = usuario.getInfoPersonal() != null ? usuario.getInfoPersonal().getCurp() : "";
                jpPerfilCUBuscar.setDatosUsuario(nombre, correo, curp);
            }
        }

        // Verificar si hay solicitudes que requieren modificación del usuario
        verificarModificacionesPendientes();
    }

    /**
     * Verifica si el usuario tiene solicitudes que requieren modificación y muestra
     * popup.
     */
    private void verificarModificacionesPendientes() {
        if (controlPresentacion == null)
            return;

        try {
            // Obtener las solicitudes del usuario actual
            java.util.List<DTOS.SolicitudAdopcionDTO> solicitudes = controlPresentacion
                    .obtenerSolicitudesUsuarioActual();

            for (DTOS.SolicitudAdopcionDTO solicitud : solicitudes) {
                if ("Requiere Modificación".equalsIgnoreCase(solicitud.getEstado())) {
                    // Mostrar popup al usuario
                    String mensaje = solicitud.getMensajeCorreccion();
                    if (mensaje == null || mensaje.isEmpty()) {
                        mensaje = "El administrador ha solicitado que modifiques tu solicitud.";
                    }

                    int opcion = javax.swing.JOptionPane.showConfirmDialog(this,
                            "El administrador ha solicitado modificaciones en tu solicitud:\n\n" +
                                    "\"" + mensaje + "\"\n\n" +
                                    "¿Deseas modificar tu solicitud ahora?",
                            "Modificación Requerida",
                            javax.swing.JOptionPane.YES_NO_OPTION,
                            javax.swing.JOptionPane.QUESTION_MESSAGE);

                    if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                        // Cargar datos de la solicitud y navegar al formulario
                        controlPresentacion.setIdMascotaSeleccionada(solicitud.getIdMascota());
                        mostrarInfoPersonal();
                    } else {
                        // Cancelar la solicitud
                        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,
                                "Si no modificas la solicitud, será cancelada.\n¿Estás seguro de cancelar?",
                                "Confirmar Cancelación",
                                javax.swing.JOptionPane.YES_NO_OPTION,
                                javax.swing.JOptionPane.WARNING_MESSAGE);

                        if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
                            controlPresentacion.cancelarSolicitudAdopcion(solicitud.getId());
                            javax.swing.JOptionPane.showMessageDialog(this,
                                    "Tu solicitud ha sido cancelada.",
                                    "Solicitud Cancelada",
                                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    break; // Solo procesar la primera solicitud pendiente
                }
            }
        } catch (Exception e) {
            System.err.println("Error al verificar modificaciones pendientes: " + e.getMessage());
        }
    }

    /**
     * Obtiene el panel del menú de especies.
     * 
     * @return El panel MenuMostrarEspecies.
     */
    public MenuMostrarEspecies getMenuMostrarEspecies() {
        return menuMostrarEspecies;
    }

    /** Muestra el panel de inicio (catálogo de especies). */
    public void mostrarInicio() {
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "inicio");
        }
    }

    /** Muestra el formulario de información personal. */
    public void mostrarInfoPersonal() {
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "infoPersonal");
        }
    }

    /** Muestra el formulario de información de vivienda. */
    public void mostrarInfoVivienda() {
        if (cardLayout != null)
            cardLayout.show(panelContenidoDinamico, "infoVivienda");
    }

    /** Muestra el formulario de razones y antecedentes. */
    public void mostrarRazonesAntecedentes() {
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "razonesAntecedentes");
        }
    }

    /** Muestra el panel de resumen de la solicitud. */
    public void mostrarInfoResumen() {
        if (cardLayout != null) {
            if (frmInfoResumen != null) {
                frmInfoResumen.cargarDatos();
            }
            cardLayout.show(panelContenidoDinamico, "infoResumen");
        }
    }

    /** Muestra la pantalla de confirmación. */
    public void mostrarConfirmacion() {
        if (cardLayout != null) {
            if (frmCorreoConfirmacion != null) {
                frmCorreoConfirmacion.setControlPresentacion(controlPresentacion);
                frmCorreoConfirmacion.cargarDatos();
            }
            cardLayout.show(panelContenidoDinamico, "confirmacion");
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCabecera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_inicio = new javax.swing.JButton();
        btn_solicitudes = new javax.swing.JButton();
        btn_contacto = new javax.swing.JButton();
        lblUserImage = new javax.swing.JLabel();
        panelContenidoDinamico = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCabecera.setBackground(new java.awt.Color(255, 255, 255));
        panelCabecera.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 110, 33));
        jLabel1.setText("LA VIDA ES BELLA");

        btn_inicio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_inicio.setText("Inicio");
        btn_inicio.setBorder(null);
        btn_inicio.setBorderPainted(false);
        btn_inicio.setContentAreaFilled(false);
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });

        btn_solicitudes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_solicitudes.setText("Mis Solicitudes");
        btn_solicitudes.setBorder(null);
        btn_solicitudes.setBorderPainted(false);
        btn_solicitudes.setContentAreaFilled(false);
        btn_solicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_solicitudesActionPerformed(evt);
            }
        });

        btn_contacto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_contacto.setText("Contacto");
        btn_contacto.setBorder(null);
        btn_contacto.setBorderPainted(false);
        btn_contacto.setContentAreaFilled(false);
        btn_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_contactoActionPerformed(evt);
            }
        });

        lblUserImage
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sources/icon-7797704_1280.png"))); // NOI18N
        lblUserImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
        panelCabecera.setLayout(panelCabeceraLayout);
        panelCabeceraLayout.setHorizontalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel1)
                                .addGap(122, 122, 122)
                                .addComponent(btn_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btn_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 194,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btn_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 194,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(lblUserImage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        panelCabeceraLayout.setVerticalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabeceraLayout
                                .createSequentialGroup()
                                .addContainerGap(40, Short.MAX_VALUE)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblUserImage, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelCabeceraLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1)
                                                .addComponent(btn_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn_solicitudes, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)));

        panelContenidoDinamico.setBackground(new java.awt.Color(235, 229, 220));
        panelContenidoDinamico.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelContenidoDinamicoLayout = new javax.swing.GroupLayout(panelContenidoDinamico);
        panelContenidoDinamico.setLayout(panelContenidoDinamicoLayout);
        panelContenidoDinamicoLayout.setHorizontalGroup(
                panelContenidoDinamicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1255, Short.MAX_VALUE));
        panelContenidoDinamicoLayout.setVerticalGroup(
                panelContenidoDinamicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 607, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelContenidoDinamico, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(panelContenidoDinamico, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Muestra el perfil del usuario al hacer clic en su imagen de perfil.
     * 
     * @param evt El evento del mouse.
     */
    private void lblUserImageMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblUserImageMouseClicked
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "perfil");
        }
    }// GEN-LAST:event_lblUserImageMouseClicked

    /**
     * Navega a la sección de administración al pulsar el botón Inicio.
     * 
     * @param evt El evento de acción.
     */
    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_inicioActionPerformed
        if (cardLayout != null) {
            // Mostrar panel de inicio (catálogo de mascotas)
            cardLayout.show(panelContenidoDinamico, "inicio");
        }
    }// GEN-LAST:event_btn_inicioActionPerformed

    /**
     * Navega a la sección de solicitudes del usuario.
     * 
     * @param evt El evento de acción.
     */
    private void btn_solicitudesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_solicitudesActionPerformed
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "solicitudes");
        }
    }// GEN-LAST:event_btn_solicitudesActionPerformed

    /**
     * Navega a la sección de contacto.
     * 
     * @param evt El evento de acción.
     */
    private void btn_contactoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_contactoActionPerformed
        if (cardLayout != null) {
            cardLayout.show(panelContenidoDinamico, "contacto");
        }
    }// GEN-LAST:event_btn_contactoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /** Botón de navegación a Contacto */
    private javax.swing.JButton btn_contacto;
    /** Botón de navegación a Inicio */
    private javax.swing.JButton btn_inicio;
    /** Botón de navegación a Mis Solicitudes */
    private javax.swing.JButton btn_solicitudes;
    private javax.swing.JLabel jLabel1;
    /** Etiqueta con la imagen de perfil del usuario */
    private javax.swing.JLabel lblUserImage;
    /** Panel de cabecera con logo y navegación */
    private javax.swing.JPanel panelCabecera;
    /** Panel contenedor para el contenido cambiante (CardLayout) */
    private javax.swing.JPanel panelContenidoDinamico;
    // End of variables declaration//GEN-END:variables
}
