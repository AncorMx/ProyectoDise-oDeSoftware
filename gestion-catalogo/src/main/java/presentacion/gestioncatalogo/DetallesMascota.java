/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion.gestioncatalogo;

import gestion.catalogo.dtos.CatalogoDTO;

/**
 *
 * @author angel
 */
public class DetallesMascota extends javax.swing.JPanel {

    private PanelPrincipalGestionCatalogo panelPrincipal;
    private CatalogoDTO mascotaActual;

    /**
     * Creates new form DetallesMascota
     */
    public DetallesMascota() {
        initComponents();
        bloquearCampos();
    }

    private void bloquearCampos() {
        javax.swing.JTextField[] campos = { tfNombre1, tfEspecie1, tfRaza1, tfEdad1, tfColor1, tfPeso1,
                tfCondicion1 };
        for (javax.swing.JTextField campo : campos) {
            if (campo != null) {
                campo.setEditable(false);
                campo.setFocusable(false);
            }
        }
    }

    public void setPanelPrincipal(PanelPrincipalGestionCatalogo panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setDatosMascota(CatalogoDTO mascota) {
        this.mascotaActual = mascota;
        if (mascota == null) {
            return;
        }

        // Cargar datos en los campos del panel principal (jPanel3)
        tfNombre1.setText(mascota.getNombre() != null ? mascota.getNombre() : "");
        tfEspecie1.setText(mascota.getEspecie() != null ? mascota.getEspecie() : "");
        tfRaza1.setText(mascota.getRaza() != null ? mascota.getRaza() : "");
        tfEdad1.setText(String.valueOf(mascota.getEdad()));
        tfColor1.setText(mascota.getColor() != null ? mascota.getColor() : "");
        tfPeso1.setText(String.valueOf(mascota.getPeso()));
        tfCondicion1.setText(mascota.getEstadoSalud() != null ? mascota.getEstadoSalud() : "");

        // Cargar imagen
        cargarImagen(mascota.getUrlImagen());

        // Verificar estado del expediente
        checkEstadoExpediente(mascota.getId());
    }

    private void checkEstadoExpediente(String idMascota) {
        try {
            negocioo.ExpedienteBO expedienteBO = new negocioo.ExpedienteBO();
            gestion.catalogo.dtos.ExpedienteDTO expediente = expedienteBO.obtenerExpediente(idMascota);

            String estado = "Incompleto";

            if (expediente != null) {
                boolean camposTextoCompletos = isValido(expediente.getCondicion()) &&
                        isValido(expediente.getNivelEnergia()) &&
                        isValido(expediente.getAlergias()) &&
                        isValido(expediente.getCondicionesEspeciales());

                boolean checklistCompleto = expediente.isVacunaRabia() ||
                        expediente.isVacunaDesparasitacionExterna() ||
                        expediente.isVacunaBordetella() ||
                        expediente.isVacunaDesparasitacionInterna() ||
                        expediente.isVacunaMultiple() ||
                        expediente.isEsterilizado();

                if (camposTextoCompletos && checklistCompleto) {
                    estado = "Completo";
                }
            }

            jLabel1.setText(estado);
            jLabel2.setText(estado);

        } catch (Exception e) {
            System.err.println("Error verificando expediente: " + e.getMessage());
        }
    }

    private boolean isValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    private void cargarImagen(String urlImagen) {
        if (urlImagen == null || urlImagen.isEmpty()) {
            lbl_Imagen1.setIcon(null);
            lbl_Imagen1.setText("Sin imagen");
            return;
        }

        try {
            // Normalizar la ruta: quitar "/" inicial si existe
            String rutaRecurso = urlImagen.startsWith("/") ? urlImagen.substring(1) : urlImagen;

            // Intentar cargar desde recursos
            java.net.URL imageUrl = getClass().getClassLoader().getResource(rutaRecurso);
            if (imageUrl != null) {
                javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imageUrl);
                java.awt.Image img = icon.getImage().getScaledInstance(
                        lbl_Imagen1.getWidth() > 0 ? lbl_Imagen1.getWidth() : 200,
                        lbl_Imagen1.getHeight() > 0 ? lbl_Imagen1.getHeight() : 200,
                        java.awt.Image.SCALE_SMOOTH);
                lbl_Imagen1.setIcon(new javax.swing.ImageIcon(img));
                lbl_Imagen1.setText("");
            } else {
                // Intentar cargar como archivo absoluto
                java.io.File file = new java.io.File(urlImagen);
                if (file.exists()) {
                    javax.swing.ImageIcon icon = new javax.swing.ImageIcon(urlImagen);
                    java.awt.Image img = icon.getImage().getScaledInstance(
                            lbl_Imagen1.getWidth() > 0 ? lbl_Imagen1.getWidth() : 200,
                            lbl_Imagen1.getHeight() > 0 ? lbl_Imagen1.getHeight() : 200,
                            java.awt.Image.SCALE_SMOOTH);
                    lbl_Imagen1.setIcon(new javax.swing.ImageIcon(img));
                    lbl_Imagen1.setText("");
                } else {
                    lbl_Imagen1.setIcon(null);
                    lbl_Imagen1.setText("Imagen no encontrada");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + e.getMessage());
            lbl_Imagen1.setIcon(null);
            lbl_Imagen1.setText("Error al cargar");
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        tfPeso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfEdad = new javax.swing.JTextField();
        tfNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        tfEspecie = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfColor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tfRaza = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lbl_Imagen = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfCondicion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnCancelar1 = new javax.swing.JButton();
        tfPeso1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfEdad1 = new javax.swing.JTextField();
        tfNombre1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnSiguiente1 = new javax.swing.JButton();
        tfEspecie1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tfColor1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfRaza1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lbl_Imagen1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tfCondicion1 = new javax.swing.JTextField();

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Detalles Mascota");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelar.setText("Regresar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tfPeso.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Peso");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Edad");

        tfEdad.setBackground(new java.awt.Color(204, 204, 204));

        tfNombre.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre");

        btnSiguiente.setBackground(new java.awt.Color(0, 153, 51));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSiguiente.setText("Ver Expediente Medico");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        tfEspecie.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Especie");

        tfColor.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Color");

        tfRaza.setBackground(new java.awt.Color(204, 204, 204));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Raza");

        lbl_Imagen.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Expediente Medico: ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Incompleto");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Condición");

        tfCondicion.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(jLabel8)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel1)
                                                                        .addGap(64, 64, 64))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(tfNombre,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        375,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                        jPanel2Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(
                                                                                                                        tfEdad,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                        139,
                                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(25,
                                                                                                                        25,
                                                                                                                        25)
                                                                                                                .addGroup(
                                                                                                                        jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                        false)
                                                                                                                                .addComponent(
                                                                                                                                        jLabel13)
                                                                                                                                .addComponent(
                                                                                                                                        tfColor,
                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                        211,
                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                .addComponent(
                                                                                                                                        jLabel9)
                                                                                                                                .addComponent(
                                                                                                                                        tfCondicion))))
                                                                                        .addComponent(jLabel4))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                        .addComponent(jLabel12)
                                                                                        .addComponent(tfRaza,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                158, Short.MAX_VALUE)
                                                                                        .addComponent(jLabel14)
                                                                                        .addComponent(tfEspecie)))
                                                                        .addComponent(tfPeso,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                139,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel7))
                                                                .addGap(22, 22, 22)))
                                                .addComponent(lbl_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 315,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 273,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel12)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(tfNombre,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(tfEspecie,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(9, 9, 9)
                                                                                                .addComponent(jLabel6)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(tfEdad,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        jLabel14))))
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel13)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(tfColor,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(tfRaza,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel7)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfPeso,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel9)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfCondicion,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel1)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(lbl_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 244,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(58, Short.MAX_VALUE)));

        setBackground(new java.awt.Color(219, 213, 195));
        setLayout(new java.awt.GridBagLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Detalles Mascota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(60, 430, 0, 0);
        add(jLabel5, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        btnCancelar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancelar1.setText("Regresar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        tfPeso1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Peso");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Edad");

        tfEdad1.setBackground(new java.awt.Color(204, 204, 204));

        tfNombre1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nombre");

        btnSiguiente1.setBackground(new java.awt.Color(0, 153, 51));
        btnSiguiente1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSiguiente1.setText("Ver Expediente Medico");
        btnSiguiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguiente1ActionPerformed(evt);
            }
        });

        tfEspecie1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Especie");

        tfColor1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Color");

        tfRaza1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Raza");

        lbl_Imagen1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Expediente Medico: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Incompleto");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Condición");

        tfCondicion1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(tfNombre1,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        375,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(
                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                        jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(tfEdad1,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        139,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(25, 25, 25)
                                                                                                .addGroup(jPanel3Layout
                                                                                                        .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                        .addComponent(
                                                                                                                jLabel17)
                                                                                                        .addComponent(
                                                                                                                tfColor1,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                211,
                                                                                                                Short.MAX_VALUE)
                                                                                                        .addComponent(
                                                                                                                jLabel20)
                                                                                                        .addComponent(
                                                                                                                tfCondicion1))))
                                                                        .addComponent(jLabel15))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(jLabel16)
                                                                        .addComponent(tfRaza1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                158, Short.MAX_VALUE)
                                                                        .addComponent(jLabel18)
                                                                        .addComponent(tfEspecie1)))
                                                        .addComponent(tfPeso1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel10))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lbl_Imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 315,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel11)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSiguiente1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(61, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel16)
                                                        .addComponent(jLabel15))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addGroup(jPanel3Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(tfNombre1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(tfEspecie1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(9, 9, 9)
                                                                                                .addComponent(jLabel11)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(tfEdad1,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        jLabel18))))
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(jLabel17)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel3Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(tfColor1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(tfRaza1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel10)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfPeso1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel20)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfCondicion1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel19)
                                                        .addComponent(jLabel2)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(lbl_Imagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 244,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSiguiente1, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(65, Short.MAX_VALUE)));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 59;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 60, 83, 80);
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnSiguienteActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelar1ActionPerformed
        // Regresar al catálogo
        if (panelPrincipal != null) {
            panelPrincipal.mostrarCatalogo();
        }
    }// GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnSiguiente1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSiguiente1ActionPerformed
        // Ver expediente médico
        if (panelPrincipal != null && mascotaActual != null) {
            panelPrincipal.mostrarExpedienteParaEdicion(mascotaActual.getId());
        }
    }// GEN-LAST:event_btnSiguiente1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSiguiente1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_Imagen;
    private javax.swing.JLabel lbl_Imagen1;
    private javax.swing.JTextField tfColor;
    private javax.swing.JTextField tfColor1;
    private javax.swing.JTextField tfCondicion;
    private javax.swing.JTextField tfCondicion1;
    private javax.swing.JTextField tfEdad;
    private javax.swing.JTextField tfEdad1;
    private javax.swing.JTextField tfEspecie;
    private javax.swing.JTextField tfEspecie1;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfNombre1;
    private javax.swing.JTextField tfPeso;
    private javax.swing.JTextField tfPeso1;
    private javax.swing.JTextField tfRaza;
    private javax.swing.JTextField tfRaza1;
    // End of variables declaration//GEN-END:variables
}
