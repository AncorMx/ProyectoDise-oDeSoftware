/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion.gestioncatalogo;

import gestion.catalogo.dtos.ExpedienteDTO;
import negocioo.ExpedienteBO;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class ExpedienteMedico extends javax.swing.JPanel {

    private PanelPrincipalGestionCatalogo panelPrincipal;
    private boolean modoEdicion = false; // false = creación, true = edición
    private String idMascotaActual = null; // ID de mascota para modo edición
    private boolean modoRetornoEdicion = false; // Si true, regresar al panel de editar mascota al salir

    /**
     * Creates new form ExpedienteMedico
     */
    public ExpedienteMedico() {
        initComponents();
    }

    public void setPanelPrincipal(PanelPrincipalGestionCatalogo panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setModoRetornoEdicion(boolean modoRetornoEdicion) {
        this.modoRetornoEdicion = modoRetornoEdicion;
    }

    public void limpiarFormulario() {
        tfNombre.setText("");
        tfCorreo.setText("");
        tfCorreo1.setText("");
        jComboBox2.setSelectedIndex(0);
        cbAdoptadoPreviamente.setSelected(false);
        cbAdoptadoPreviamente1.setSelected(false);
        cbAdoptadoPreviamente2.setSelected(false);
        cbAdoptadoPreviamente3.setSelected(false);
        cbAdoptadoPreviamente4.setSelected(false);
        cbAdoptadoPreviamente5.setSelected(false);
        modoEdicion = false;
        idMascotaActual = null;
    }

    public void setModoSoloLectura(boolean soloLectura) {
        tfNombre.setEnabled(!soloLectura);
        tfCorreo.setEnabled(!soloLectura);
        tfCorreo1.setEnabled(!soloLectura);
        jComboBox2.setEnabled(!soloLectura);
        cbAdoptadoPreviamente.setEnabled(!soloLectura);
        cbAdoptadoPreviamente1.setEnabled(!soloLectura);
        cbAdoptadoPreviamente2.setEnabled(!soloLectura);
        cbAdoptadoPreviamente3.setEnabled(!soloLectura);
        cbAdoptadoPreviamente4.setEnabled(!soloLectura);
        cbAdoptadoPreviamente5.setEnabled(!soloLectura);
    }

    /**
     * Carga datos temporales del expediente (modo CREACIÓN desde
     * AgregarMascota)
     */
    public void cargarDatosTemporales(gestion.catalogo.dtos.ExpedienteMedicoTemporal datos) {
        modoEdicion = false;
        idMascotaActual = null;
        if (datos == null) {
            return;
        }

        // Cargar campos de texto
        tfNombre.setText(datos.getCondicion() != null ? datos.getCondicion() : "");
        tfCorreo.setText(datos.getAlergias() != null ? datos.getAlergias() : "");
        tfCorreo1.setText(datos.getCondicionesEspeciales() != null ? datos.getCondicionesEspeciales() : "");

        // Cargar nivel de energía
        if (datos.getNivelEnergia() != null) {
            jComboBox2.setSelectedItem(datos.getNivelEnergia());
        }

        // Cargar checkboxes de vacunas
        cbAdoptadoPreviamente.setSelected(datos.isVacunaRabia());
        cbAdoptadoPreviamente1.setSelected(datos.isVacunaDesparasitacionExterna());
        cbAdoptadoPreviamente2.setSelected(datos.isVacunaBordetella());
        cbAdoptadoPreviamente3.setSelected(datos.isVacunaMultiple());
        cbAdoptadoPreviamente4.setSelected(datos.isVacunaDesparasitacionInterna());
        cbAdoptadoPreviamente5.setSelected(datos.isEsterilizado());

        // Campos editables en modo creación
        setModoSoloLectura(false);
    }

    /**
     * Carga expediente existente en modo EDICIÓN (desde DetallesMascota)
     */
    public void cargarExpedienteParaEdicion(String idMascota, gestion.catalogo.dtos.ExpedienteDTO expediente) {
        modoEdicion = true;
        idMascotaActual = idMascota;

        if (expediente != null) {
            // Cargar campos de texto
            tfNombre.setText(expediente.getCondicion() != null ? expediente.getCondicion() : "");
            tfCorreo.setText(expediente.getAlergias() != null ? expediente.getAlergias() : "");
            tfCorreo1.setText(expediente.getCondicionesEspeciales() != null
                    ? expediente.getCondicionesEspeciales()
                    : "");

            // Cargar nivel de energía
            if (expediente.getNivelEnergia() != null) {
                jComboBox2.setSelectedItem(expediente.getNivelEnergia());
            }

            // Cargar checkboxes de vacunas
            cbAdoptadoPreviamente.setSelected(expediente.isVacunaRabia());
            cbAdoptadoPreviamente1.setSelected(expediente.isVacunaDesparasitacionExterna());
            cbAdoptadoPreviamente2.setSelected(expediente.isVacunaBordetella());
            cbAdoptadoPreviamente3.setSelected(expediente.isVacunaMultiple());
            cbAdoptadoPreviamente4.setSelected(expediente.isVacunaDesparasitacionInterna());
            cbAdoptadoPreviamente5.setSelected(expediente.isEsterilizado());
        } else {
            limpiarFormulario();
            modoEdicion = true;
            idMascotaActual = idMascota;
        }

        // Campos EDITABLES en modo edición
        setModoSoloLectura(false);

        System.out.println("Expediente cargado para edición. ID Mascota: " + idMascota);
    }

    /**
     * Carga expediente existente en modo SOLO LECTURA (visualización)
     */
    public void cargarExpedienteExistente(gestion.catalogo.dtos.ExpedienteDTO expediente) {
        modoEdicion = false;
        if (expediente == null) {
            return;
        }

        // Cargar campos de texto
        tfNombre.setText(expediente.getCondicion() != null ? expediente.getCondicion() : "");
        tfCorreo.setText(expediente.getAlergias() != null ? expediente.getAlergias() : "");
        tfCorreo1.setText(expediente.getCondicionesEspeciales() != null ? expediente.getCondicionesEspeciales()
                : "");

        // Cargar nivel de energía
        if (expediente.getNivelEnergia() != null) {
            jComboBox2.setSelectedItem(expediente.getNivelEnergia());
        }

        // Cargar checkboxes de vacunas
        cbAdoptadoPreviamente.setSelected(expediente.isVacunaRabia());
        cbAdoptadoPreviamente1.setSelected(expediente.isVacunaDesparasitacionExterna());
        cbAdoptadoPreviamente2.setSelected(expediente.isVacunaBordetella());
        cbAdoptadoPreviamente3.setSelected(expediente.isVacunaMultiple());
        cbAdoptadoPreviamente4.setSelected(expediente.isVacunaDesparasitacionInterna());
        cbAdoptadoPreviamente5.setSelected(expediente.isEsterilizado());

        // Poner en modo solo lectura
        setModoSoloLectura(true);
    }

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public String getIdMascotaActual() {
        return idMascotaActual;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        cbAdoptadoPreviamente = new javax.swing.JCheckBox();
        tfCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        cbAdoptadoPreviamente1 = new javax.swing.JCheckBox();
        cbAdoptadoPreviamente2 = new javax.swing.JCheckBox();
        cbAdoptadoPreviamente4 = new javax.swing.JCheckBox();
        cbAdoptadoPreviamente3 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cbAdoptadoPreviamente5 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        tfCorreo1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(219, 213, 195));
        setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cbAdoptadoPreviamente.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente.setText("Rabia");
        cbAdoptadoPreviamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamenteActionPerformed(evt);
            }
        });

        tfCorreo.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Alergias Detectadas");

        tfNombre.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Condición");

        btnSiguiente.setBackground(new java.awt.Color(0, 153, 51));
        btnSiguiente.setText("Guardar");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        cbAdoptadoPreviamente1.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente1.setText("Desparasitación Externa");
        cbAdoptadoPreviamente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamente1ActionPerformed(evt);
            }
        });

        cbAdoptadoPreviamente2.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente2.setText("Bordetella");
        cbAdoptadoPreviamente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamente2ActionPerformed(evt);
            }
        });

        cbAdoptadoPreviamente4.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente4.setText("Desparasitación Interna");
        cbAdoptadoPreviamente4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamente4ActionPerformed(evt);
            }
        });

        cbAdoptadoPreviamente3.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente3.setText("Múltiple/Quíntuple");
        cbAdoptadoPreviamente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamente3ActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Vacunas");

        cbAdoptadoPreviamente5.setForeground(new java.awt.Color(0, 0, 0));
        cbAdoptadoPreviamente5.setText("Esterilizado");
        cbAdoptadoPreviamente5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdoptadoPreviamente5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Condiciones Especiales");

        tfCorreo1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nivel de Energía / Comportamiento");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAdoptadoPreviamente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAdoptadoPreviamente3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAdoptadoPreviamente2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAdoptadoPreviamente5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAdoptadoPreviamente4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAdoptadoPreviamente1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(372, 372, 372))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfCorreo1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(34, 34, 34))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAdoptadoPreviamente)
                    .addComponent(cbAdoptadoPreviamente4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAdoptadoPreviamente1)
                    .addComponent(cbAdoptadoPreviamente3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAdoptadoPreviamente2)
                    .addComponent(cbAdoptadoPreviamente5))
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCorreo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 99;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(45, 192, 65, 182);
        add(jPanel2, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Expediente Medico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 434, 0, 0);
        add(jLabel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Desea regresar? Los cambios no guardados se perderán.",
                "Confirmar",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            if (panelPrincipal != null) {
                if (modoRetornoEdicion && idMascotaActual != null) {
                    // Volver a la edición de la mascota
                    panelPrincipal.volverAEditarMascota();
                } else if (modoEdicion && idMascotaActual != null) {
                    // Si estamos en modo edición (desde detalles), volver a detalles
                    panelPrincipal.mostrarDetallesMascota(idMascotaActual);
                } else {
                    // Si es modo creación, volver al panel de agregar/adoptar
                    // Solo regresar, NO limpiar el formulario
                    panelPrincipal.regresarAAgregarMascota();
                }
            }
        }
    }// GEN-LAST:event_btnCancelarActionPerformed

    private void cbAdoptadoPreviamenteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamenteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamenteActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSiguienteActionPerformed
        if (modoEdicion && idMascotaActual != null) {
            try {
                // MODO EDICIÓN: Actualizar en base de datos
                ExpedienteBO expedienteBO = new ExpedienteBO();
                ExpedienteDTO expediente = expedienteBO.obtenerExpediente(idMascotaActual);

                if (expediente == null) {
                    // Si no existe, creamos uno nuevo asociado a la mascota
                    expediente = new ExpedienteDTO();
                    expediente.setIdMascota(idMascotaActual);
                    // Generar código si fuera necesario, o dejar que el BO lo maneje al crear
                    // Por ahora asumimos actualización de existente o manejo en BO
                }

                // Actualizar datos del objeto DTO con los campos del formulario
                expediente.setCondicion(tfNombre.getText());
                expediente.setAlergias(tfCorreo.getText());
                expediente.setCondicionesEspeciales(tfCorreo1.getText());
                expediente.setNivelEnergia((String) jComboBox2.getSelectedItem());

                expediente.setVacunaRabia(cbAdoptadoPreviamente.isSelected());
                expediente.setVacunaDesparasitacionExterna(cbAdoptadoPreviamente1.isSelected());
                expediente.setVacunaBordetella(cbAdoptadoPreviamente2.isSelected());
                expediente.setVacunaMultiple(cbAdoptadoPreviamente3.isSelected());
                expediente.setVacunaDesparasitacionInterna(cbAdoptadoPreviamente4.isSelected());
                expediente.setEsterilizado(cbAdoptadoPreviamente5.isSelected());

                boolean exito;
                if (expediente.getCodigo() == null) {
                    // Es nuevo en BD (caso raro si venimos de editar, pero posible si no tenía exp
                    // previo)
                    // Crear expediente no soporta DTO directo en la interfaz actual,
                    // usaremos expedienteBO.actualizarExpediente asumiendo que el ID mascota es la
                    // clave
                    // Si no existe, actualizarExpediente podría fallar.
                    // Mejor estrategia: Si no tenía expediente, usar crearExpedienteConDatos
                    // adaptando el DTO a Temporal
                    // Pero para simplificar y dado que 'cargarExpedienteParaEdicion' ya validó
                    // existencia o creó vacío...
                    // Asumiremos que si estamos aquí es para actualizar.
                    exito = expedienteBO.actualizarExpediente(expediente);
                } else {
                    exito = expedienteBO.actualizarExpediente(expediente);
                }

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Expediente actualizado correctamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    if (panelPrincipal != null) {
                        if (panelPrincipal != null) {
                            if (modoRetornoEdicion) {
                                panelPrincipal.volverAEditarMascota();
                            } else {
                                panelPrincipal.mostrarDetallesMascota(idMascotaActual);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el expediente.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al guardar cambios: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // MODO CREACIÓN (Temporal)
            // Crear objeto temporal con los datos del expediente
            gestion.catalogo.dtos.ExpedienteMedicoTemporal datosExpediente = new gestion.catalogo.dtos.ExpedienteMedicoTemporal();
            datosExpediente.setCondicion(tfNombre.getText());
            datosExpediente.setAlergias(tfCorreo.getText());
            datosExpediente.setCondicionesEspeciales(tfCorreo1.getText());
            datosExpediente.setNivelEnergia((String) jComboBox2.getSelectedItem());

            // Guardar vacunas seleccionadas (usando setters individuales)
            datosExpediente.setVacunaRabia(cbAdoptadoPreviamente.isSelected());
            datosExpediente.setVacunaDesparasitacionExterna(cbAdoptadoPreviamente1.isSelected());
            datosExpediente.setVacunaBordetella(cbAdoptadoPreviamente2.isSelected());
            datosExpediente.setVacunaMultiple(cbAdoptadoPreviamente3.isSelected());
            datosExpediente.setVacunaDesparasitacionInterna(cbAdoptadoPreviamente4.isSelected());
            datosExpediente.setEsterilizado(cbAdoptadoPreviamente5.isSelected());

            // Pasar datos al panel principal
            if (panelPrincipal != null) {
                panelPrincipal.guardarExpedienteTemporal(datosExpediente);
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Expediente médico guardado en memoria.",
                        "Guardado",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                panelPrincipal.regresarAAgregarMascota();
            }
        }
    }// GEN-LAST:event_btnSiguienteActionPerformed

    private void cbAdoptadoPreviamente1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamente1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamente1ActionPerformed

    private void cbAdoptadoPreviamente2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamente2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamente2ActionPerformed

    private void cbAdoptadoPreviamente4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamente4ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamente4ActionPerformed

    private void cbAdoptadoPreviamente3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamente3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamente3ActionPerformed

    private void cbAdoptadoPreviamente5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamente5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cbAdoptadoPreviamente5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JCheckBox cbAdoptadoPreviamente;
    private javax.swing.JCheckBox cbAdoptadoPreviamente1;
    private javax.swing.JCheckBox cbAdoptadoPreviamente2;
    private javax.swing.JCheckBox cbAdoptadoPreviamente3;
    private javax.swing.JCheckBox cbAdoptadoPreviamente4;
    private javax.swing.JCheckBox cbAdoptadoPreviamente5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfCorreo;
    private javax.swing.JTextField tfCorreo1;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
