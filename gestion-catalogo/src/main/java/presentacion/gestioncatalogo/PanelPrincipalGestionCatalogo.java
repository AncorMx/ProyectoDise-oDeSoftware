/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.gestioncatalogo;

import gestion.catalogo.control.ControlPresentacion;
import gestion.catalogo.dtos.CatalogoDTO;
import gestion.catalogo.dtos.ExpedienteDTO;
import negocioo.ExpedienteBO;
import ObjetoNegocio.MascotaBO;
import DTOS.MascotaDTO;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author angel
 */
public class PanelPrincipalGestionCatalogo extends javax.swing.JFrame {

    private ControlPresentacion control;
    private CardLayout cardLayout;
    private GestionCatalogoPanel gestionCatalogoPanel;
    private DetallesMascota detallesMascotaPanel;
    private AgregarMascotaPanel agregarMascotaPanel;
    private ExpedienteMedico expedienteMedicoPanel;
    private DarBajaMascota darBajaMascotaPanel;
    private EditarMascotaPanel editarMascotaPanel;
    private ConfirmacionAgregarMascota confirmacionPanel;
    private static final String CARD_CATALOGO = "CATALOGO";
    private static final String CARD_AGREGAR = "AGREGAR";
    private static final String CARD_DETALLES = "DETALLES";
    private static final String CARD_EXPEDIENTE = "EXPEDIENTE";
    private static final String CARD_BAJA = "BAJA";
    private static final String CARD_EDITAR = "EDITAR";
    private static final String CARD_CONFIRMACION = "CONFIRMACION";

    /**
     * Creates new form PanelPrincipalAdmin
     */
    public PanelPrincipalGestionCatalogo() {
        initComponents();
        this.control = new ControlPresentacion();

        // Configurar CardLayout
        this.cardLayout = new CardLayout();
        panelContenidoDinamico.setLayout(cardLayout);

        // Crear e insertar GestionCatalogoPanel
        this.gestionCatalogoPanel = new GestionCatalogoPanel();
        this.gestionCatalogoPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(gestionCatalogoPanel, CARD_CATALOGO);

        // Configurar botón agregar mascota
        this.gestionCatalogoPanel.configurarBotonAgregar(() -> mostrarAgregarMascota());

        // Crear e insertar DetallesMascotaPanel
        this.detallesMascotaPanel = new DetallesMascota();
        this.detallesMascotaPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(detallesMascotaPanel, CARD_DETALLES);

        // Crear e insertar AgregarMascotaPanel
        this.agregarMascotaPanel = new AgregarMascotaPanel();
        this.agregarMascotaPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(agregarMascotaPanel, CARD_AGREGAR);

        // Crear e insertar DarBajaMascotaPanel
        this.darBajaMascotaPanel = new DarBajaMascota();
        this.darBajaMascotaPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(darBajaMascotaPanel, CARD_BAJA);

        // Crear e insertar ExpedienteMedicoPanel
        this.expedienteMedicoPanel = new ExpedienteMedico();
        this.expedienteMedicoPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(expedienteMedicoPanel, CARD_EXPEDIENTE);

        // Crear e insertar EditarMascotaPanel
        this.editarMascotaPanel = new EditarMascotaPanel();
        this.editarMascotaPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(editarMascotaPanel, CARD_EDITAR);

        // Crear e insertar ConfirmacionAgregarMascota
        this.confirmacionPanel = new ConfirmacionAgregarMascota();
        this.confirmacionPanel.setPanelPrincipal(this);
        panelContenidoDinamico.add(confirmacionPanel, CARD_CONFIRMACION);

        // Mostrar panel de catálogo
        mostrarCatalogo();
    }

    /**
     * Navega a la vista de catálogo principal
     */
    public void mostrarCatalogo() {
        gestionCatalogoPanel.recargarCatalogo();
        cardLayout.show(panelContenidoDinamico, CARD_CATALOGO);
    }

    /**
     * Navega a la vista de agregar mascota
     */
    public void mostrarAgregarMascota() {
        // Restaurar datos guardados si existen
        agregarMascotaPanel.cargarDatosFormulario();

        // Mostrar panel de agregar
        cardLayout.show(panelContenidoDinamico, CARD_AGREGAR);

        System.out.println("Mostrando panel de agregar mascota");
    }

    /**
     * Regresa al panel de agregar mascota desde otro panel
     */
    public void regresarAAgregarMascota() {
        cardLayout.show(panelContenidoDinamico, CARD_AGREGAR);
        System.out.println("Regresando a panel agregar mascota");
    }

    /**
     * Guarda el expediente temporal en el panel de agregar mascota
     */
    public void guardarExpedienteTemporal(gestion.catalogo.dtos.ExpedienteMedicoTemporal expediente) {
        if (agregarMascotaPanel != null) {
            agregarMascotaPanel.guardarExpedienteTemporal(expediente);
            System.out.println("Expediente temporal guardado");
        }
    }

    /**
     * Navega a la vista de editar mascota
     */
    public void mostrarEditarMascota(CatalogoDTO mascota) {
        // TODO: Implementar cuando EditarMascotaDialog esté como panel
        editarMascota(mascota);
    }

    /**
     * Muestra el panel de detalles con la información de la mascota
     */
    public void mostrarDetallesMascota(String idMascota) {
        try {
            // Obtener datos completos de la mascota
            CatalogoDTO mascota = control.obtenerDetalleMascota(idMascota);

            if (mascota == null) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la mascota con ID: " + idMascota,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Setear datos en el panel de detalles
            detallesMascotaPanel.setDatosMascota(mascota);

            // Mostrar panel de detalles
            cardLayout.show(panelContenidoDinamico, CARD_DETALLES);

            System.out.println("Mostrando detalles de: " + mascota.getNombre());

        } catch (Exception e) {
            System.err.println("Error al mostrar detalles: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los detalles de la mascota",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Navega al panel de expediente médico Carga datos temporales si existen
     * (MODO CREACION)
     */
    public void mostrarExpedienteMedico() {
        // Cargar datos temporales si existen
        gestion.catalogo.dtos.ExpedienteMedicoTemporal datosTemporales = agregarMascotaPanel.getExpedienteTemporal();
        if (datosTemporales != null) {
            expedienteMedicoPanel.cargarDatosTemporales(datosTemporales);
        } else {
            expedienteMedicoPanel.limpiarFormulario();
        }

        System.out.println("Navegando a expediente médico");
        cardLayout.show(panelContenidoDinamico, CARD_EXPEDIENTE);
    }

    /**
     * Muestra el panel de expediente médico en modo VISUALIZACIÓN (solo
     * lectura) Para ver el expediente de una mascota existente
     *
     * @param idMascota Identificador de la mascota
     */
    public void mostrarExpedienteMedicoExistente(String idMascota) {
        try {
            // Obtener expediente de BD
            ExpedienteBO expedienteBO = new ExpedienteBO();
            ExpedienteDTO expediente = expedienteBO.obtenerExpediente(idMascota);

            // Limpiar panel antes de cargar
            expedienteMedicoPanel.limpiarFormulario();

            // Cargar expediente si existe
            if (expediente != null) {
                expedienteMedicoPanel.cargarExpedienteExistente(expediente);
                System.out.println("Expediente cargado para visualización: " + idMascota);
            } else {
                expedienteMedicoPanel.setModoSoloLectura(true);
                System.out.println("La mascota no tiene expediente médico previo");
            }

            // Mostrar panel
            cardLayout.show(panelContenidoDinamico, CARD_EXPEDIENTE);

        } catch (Exception e) {
            System.err.println("Error al cargar expediente: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el expediente médico",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el panel de expediente médico en modo EDICIÓN Los campos son
     * editables para modificar el expediente
     *
     * @param idMascota Identificador de la mascota
     */
    /**
     * Muestra el panel de expediente médico en modo EDICIÓN Los campos son
     * editables para modificar el expediente
     *
     * @param idMascota Identificador de la mascota
     */
    public void mostrarExpedienteParaEdicion(String idMascota) {
        mostrarExpedienteParaEdicion(idMascota, false);
    }

    public void mostrarExpedienteParaEdicion(String idMascota, boolean retornarAEdicion) {
        try {
            // Obtener expediente de BD
            ExpedienteBO expedienteBO = new ExpedienteBO();
            ExpedienteDTO expediente = expedienteBO.obtenerExpediente(idMascota);
            // Cargar expediente para edición (campos editables)
            expedienteMedicoPanel.cargarExpedienteParaEdicion(idMascota, expediente);

            // Configurar flag de retorno
            expedienteMedicoPanel.setModoRetornoEdicion(retornarAEdicion);

            // Mostrar panel
            cardLayout.show(panelContenidoDinamico, CARD_EXPEDIENTE);
            System.out.println("Expediente abierto para edición: " + idMascota + " (Retorno a edición: "
                    + retornarAEdicion + ")");
        } catch (Exception e) {
            System.err.println("Error al cargar expediente para edición: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el expediente médico",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Regresa al panel de edición de mascota manteniendo los datos actuales
     */
    public void volverAEditarMascota() {
        cardLayout.show(panelContenidoDinamico, CARD_EDITAR);
        System.out.println("Regresando a panel editar mascota");
    }

    /**
     * Muestra el panel de expediente médico en modo VISUALIZACIÓN (solo
     * lectura) Carga los datos existentes de la base de datos
     *
     * @param idMascota Identificador de la mascota
     */
    public void mostrarEditarExpediente(String idMascota) {
        try {
            // 1. Obtener expediente de BD
            negocioo.ExpedienteBO expedienteBO = new negocioo.ExpedienteBO();
            gestion.catalogo.dtos.ExpedienteDTO expediente = expedienteBO.obtenerExpediente(idMascota);

            // 2. Limpiar panel antes de cargar
            expedienteMedicoPanel.limpiarFormulario();

            // 3. Cargar expediente si existe, o mostrar panel vacío en modo solo lectura
            //
            if (expediente != null) {
                expedienteMedicoPanel.cargarExpedienteExistente(expediente);
                System.out.println("Expediente cargado para visualización: " + idMascota);
            } else {
                // No hay expediente, mostrar panel vacío en modo solo lectura
                expedienteMedicoPanel.setModoSoloLectura(true);
                System.out.println("La mascota no tiene expediente médico previo");
            }

            // 4. Mostrar panel
            cardLayout.show(panelContenidoDinamico, CARD_EXPEDIENTE);

        } catch (Exception e) {
            System.err.println("Error al cargar expediente para visualización: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el expediente médico", "Error",
                    JOptionPane.ERROR_MESSAGE);
            mostrarDetallesMascota(idMascota); // Volver a detalles si falla
        }
    }

    /**
     * Carga el catálogo de mascotas y lo muestra en el panel dinámico
     */
    /**
     * Muestra los detalles de una mascota
     */
    private void verDetalles(CatalogoDTO mascota) {
        // Cargar datos en el panel de detalles
        detallesMascotaPanel.setDatosMascota(mascota);

        // Navegar al panel de detalles
        cardLayout.show(panelContenidoDinamico, CARD_DETALLES);
        System.out.println("Navegando a detalles de: " + mascota.getNombre());
    }

    /**
     * Abre diálogo para editar mascota
     */
    public void editarMascota(CatalogoDTO mascota) {
        if (mascota == null) {
            return;
        }

        // Cargar datos en el panel de edición
        editarMascotaPanel.setDatosMascota(mascota);

        // Mostrar panel
        cardLayout.show(panelContenidoDinamico, CARD_EDITAR);
        System.out.println("Navegando a edición de: " + mascota.getNombre());
    }

    /**
     * Da de baja una mascota
     */
    public void eliminarMascota(CatalogoDTO mascota) {
        darBajaMascotaPanel.setDatosMascota(mascota);
        cardLayout.show(panelContenidoDinamico, CARD_BAJA);
        System.out.println("Mostrando panel de baja para: " + mascota.getNombre());
    }

    /**
     * Confirma la baja de una mascota
     */
    public void mostrarConfirmacion(CatalogoDTO mascota) {
        if (mascota == null)
            return;

        confirmacionPanel.setDatosMascota(mascota);
        cardLayout.show(panelContenidoDinamico, CARD_CONFIRMACION);
        System.out.println("Navegando a confirmación de: " + mascota.getNombre());
    }

    /**
     * Confirma la baja de una mascota
     *
     * @param mascotaDTO Datos de la mascota a dar de baja
     * @param motivo     Razón de la baja
     */
    public void confirmarBajaMascota(CatalogoDTO mascotaDTO, String motivo) {
        try {
            if (mascotaDTO == null || mascotaDTO.getId() == null) {
                JOptionPane.showMessageDialog(this, "Error: Datos de mascota inválidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Usar MascotaBO para lógica de negocio
            MascotaBO mascotaBO = new MascotaBO();

            // Buscar la mascota original para asegurar integridad
            MascotaDTO mascotaOriginal = mascotaBO.buscarMascotaPorId(mascotaDTO.getId());

            if (mascotaOriginal != null) {
                // Actualizar estado
                mascotaOriginal.setDisponible(false);
                String estadoActual = mascotaOriginal.getEstado() != null ? mascotaOriginal.getEstado() : "";
                mascotaOriginal.setEstado("BAJA: " + motivo + " | Prev: " + estadoActual);

                // Persistir cambios
                mascotaBO.actualizarMascota(mascotaOriginal);

                JOptionPane.showMessageDialog(this,
                        "La mascota ha sido dada de baja correctamente.",
                        "Baja Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                mostrarCatalogo();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontró la mascota en la base de datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println("Error al dar de baja: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error inesperado al dar de baja: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenidoDinamico = new javax.swing.JPanel();
        panelCabecera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_inicio = new javax.swing.JButton();
        lblUserImage = new javax.swing.JLabel();
        lblUserImage1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_InicioAdmin = new javax.swing.JButton();
        btn_cerrarSesion1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenidoDinamico.setBackground(new java.awt.Color(219, 213, 195));

        javax.swing.GroupLayout panelContenidoDinamicoLayout = new javax.swing.GroupLayout(panelContenidoDinamico);
        panelContenidoDinamico.setLayout(panelContenidoDinamicoLayout);
        panelContenidoDinamicoLayout.setHorizontalGroup(
                panelContenidoDinamicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));
        panelContenidoDinamicoLayout.setVerticalGroup(
                panelContenidoDinamicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 685, Short.MAX_VALUE));

        panelCabecera.setBackground(new java.awt.Color(255, 255, 255));
        panelCabecera.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 110, 33));
        jLabel1.setText("LA VIDA ES BELLA");

        btn_inicio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_inicio.setForeground(new java.awt.Color(0, 0, 0));
        btn_inicio.setText("INICIO");
        btn_inicio.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btn_inicio.setBorderPainted(false);
        btn_inicio.setContentAreaFilled(false);
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });

        lblUserImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserImageMouseClicked(evt);
            }
        });

        lblUserImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserImage1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Gestionar Catalogo");

        btn_InicioAdmin.setBackground(new java.awt.Color(153, 0, 0));
        btn_InicioAdmin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_InicioAdmin.setText("Inicio Admin");
        btn_InicioAdmin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_InicioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InicioAdminActionPerformed(evt);
            }
        });

        btn_cerrarSesion1.setBackground(new java.awt.Color(153, 0, 0));
        btn_cerrarSesion1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_cerrarSesion1.setText("Cerrar Sesion");
        btn_cerrarSesion1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_cerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
        panelCabecera.setLayout(panelCabeceraLayout);
        panelCabeceraLayout.setHorizontalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(jLabel1)
                                                .addGap(251, 251, 251)
                                                .addComponent(btn_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 182,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(197, 197, 197)
                                                .addComponent(btn_InicioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblUserImage)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_cerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                                .addGap(506, 506, 506)
                                                .addComponent(jLabel2)))
                                .addContainerGap(72, Short.MAX_VALUE))
                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGap(597, 597, 597)
                                        .addComponent(lblUserImage1)
                                        .addContainerGap(598, Short.MAX_VALUE))));
        panelCabeceraLayout.setVerticalGroup(
                panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCabeceraLayout
                                .createSequentialGroup()
                                .addContainerGap(19, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(8, 8, 8)
                                .addGroup(panelCabeceraLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelCabeceraLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btn_InicioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btn_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelCabeceraLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblUserImage, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1))
                                        .addComponent(btn_cerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(lblUserImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(33, Short.MAX_VALUE))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelContenidoDinamico, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelContenidoDinamico, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_InicioAdminActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_InicioAdminActionPerformed
        try {
            // Usamos Reflection para cargar JPAdmin dinámicamente
            Class<?> jpAdminClass = Class.forName("gui.JPAdmin");
            javax.swing.JPanel jpAdmin = (javax.swing.JPanel) jpAdminClass.getDeclaredConstructor().newInstance();

            // Obtener el Frame actual
            javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities
                    .getWindowAncestor(btn_InicioAdmin);
            if (frame != null) {
                frame.setContentPane(jpAdmin);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setTitle("Panel de Administración");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error al volver al menú: " + ex.getMessage());
        }
    }// GEN-LAST:event_btn_InicioAdminActionPerformed

    private void btn_cerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_cerrarSesion1ActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea cerrar sesión?",
                "Cerrar Sesión",
                javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            try {
                // Usamos Reflection para crear nuevo FrmInicioSesion
                Class<?> loginClass = Class.forName("gui.FrmInicioSesion");
                javax.swing.JFrame loginFrame = (javax.swing.JFrame) loginClass.getDeclaredConstructor().newInstance();
                loginFrame.setVisible(true);

                // Cerrar ventana actual
                javax.swing.JFrame currentFrame = (javax.swing.JFrame) javax.swing.SwingUtilities
                        .getWindowAncestor(btn_cerrarSesion1);
                if (currentFrame != null)
                    currentFrame.dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }// GEN-LAST:event_btn_cerrarSesion1ActionPerformed

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_inicioActionPerformed
        // Recargar catálogo
        mostrarCatalogo();
        System.out.println("Catálogo recargado");
    }// GEN-LAST:event_btn_inicioActionPerformed

    private void lblUserImageMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblUserImageMouseClicked
        // Volver al menú de administrador
    }// GEN-LAST:event_lblUserImageMouseClicked

    private void lblUserImage1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblUserImage1MouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_lblUserImage1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_InicioAdmin;
    private javax.swing.JButton btn_cerrarSesion1;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblUserImage;
    private javax.swing.JLabel lblUserImage1;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelContenidoDinamico;
    // End of variables declaration//GEN-END:variables
}
