/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cubuscarmascotaideal.presentacion;

import cubuscarmascotaideal.negocio.dtos.EncuestaDTO;
import cubuscarmascotaideal.control.ControlPresentacion;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Josel
 */
public class JPEncuesta extends javax.swing.JPanel {

        // ButtonGroups para manejar exclusividad de RadioButtons
        private ButtonGroup grupoAnimalesPeludos;
        private ButtonGroup grupoAlergias;
        private ButtonGroup grupoTamanoCasa;
        private ButtonGroup grupoCasaODepa;
        private ButtonGroup grupoSueldo;
        private ButtonGroup grupoTiempoFuera;

        // Listener para cuando se completa la encuesta
        private EncuestaListener listener;

        // Control de presentación
        private ControlPresentacion controlPresentacion;

        /**
         * Creates new form JPEncuesta
         */
        public JPEncuesta() {
                initComponents();
                inicializarButtonGroups();
                this.controlPresentacion = new ControlPresentacion();
        }

        /**
         * Establece el control de presentación
         */
        public void setControlPresentacion(ControlPresentacion controlPresentacion) {
                this.controlPresentacion = controlPresentacion;
        }

        /**
         * Inicializa los ButtonGroups para los RadioButtons
         */
        private void inicializarButtonGroups() {
                // Grupo: ¿Te gustan los animales peludos?
                grupoAnimalesPeludos = new ButtonGroup();
                grupoAnimalesPeludos.add(rbtnSIAnimalesPeludos);
                grupoAnimalesPeludos.add(rbtnNoAnimalesPeludos);

                // Grupo: ¿Tienes alergias?
                grupoAlergias = new ButtonGroup();
                grupoAlergias.add(rbtnSiAlergias);
                grupoAlergias.add(rbtnNoAlergias);

                // Grupo: ¿Tu casa/depa es grande?
                grupoTamanoCasa = new ButtonGroup();
                grupoTamanoCasa.add(rbtnSICasaGrande);
                grupoTamanoCasa.add(rbtnMasomenosCasaGrande);
                grupoTamanoCasa.add(rbtnNoCasaGrande);

                // Grupo: ¿Casa o Departamento?
                grupoCasaODepa = new ButtonGroup();
                grupoCasaODepa.add(rbtnCasa);
                grupoCasaODepa.add(rbtnDepa);

                // Grupo: ¿Dispuest@ a gastar bastante?
                grupoSueldo = new ButtonGroup();
                grupoSueldo.add(rbtnGastoBastante);
                grupoSueldo.add(rbtnNogastobastante);

                // Grupo: ¿Tiempo fuera de casa?
                grupoTiempoFuera = new ButtonGroup();
                grupoTiempoFuera.add(rbtnSITiempoFuera);
                grupoTiempoFuera.add(rbtnNotiempoFuera);
        }

        /**
         * Establece el listener para eventos de la encuesta
         */
        public void setEncuestaListener(EncuestaListener listener) {
                this.listener = listener;
        }

        /**
         * Valida que todas las preguntas hayan sido respondidas
         */
        private boolean validarRespuestas() {
                if (grupoAnimalesPeludos.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "responde la pregunta de ¿Te gustan los animales peludos?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                if (grupoAlergias.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "reaponde la pregunta de ¿Tienes alergias?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                if (grupoCasaODepa.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "reaponde la pregunta de¿Vives en casa o departamento?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                if (grupoTamanoCasa.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "reaponde la pregunta de ¿Tu casa/depa es grande?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                if (grupoSueldo.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "reaponde la pregunta de ¿Estás dispuest@ a gastar bastante?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                if (grupoTiempoFuera.getSelection() == null) {
                        JOptionPane.showMessageDialog(this,
                                        "reaponde la pregunta de ¿Pasarás mucho tiempo fuera de tu hogar?",
                                        "Pregunta sin responder", JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                return true;
        }

        /**
         * Obtiene los criterios de búsqueda basados en las respuestas
         */
        public EncuestaDTO obtenerCriterios() {
                if (!validarRespuestas()) {
                        return null;
                }

                EncuestaDTO encuesta = new EncuestaDTO();

                // Pregunta 1: ¿Te gustan los animales peludos?
                encuesta.setPeludo(rbtnSIAnimalesPeludos.isSelected());

                // Pregunta 2: ¿Tienes alergias?
                encuesta.setTieneAlergias(rbtnSiAlergias.isSelected());

                // Pregunta 3: ¿Vives en casa o departamento?
                encuesta.setViveEnDepartamento(rbtnDepa.isSelected());

                // Pregunta 4: Tamaño de casa -> tamaño de mascota
                if (rbtnSICasaGrande.isSelected()) {
                        encuesta.setTamano("grande");
                } else if (rbtnMasomenosCasaGrande.isSelected()) {
                        encuesta.setTamano("mediano");
                } else {
                        encuesta.setTamano("pequeño");
                }

                // Pregunta 5: ¿Dispuest@ a gastar bastante?
                if (rbtnGastoBastante.isSelected()) {
                        encuesta.setCostoMantenimiento("alto");
                } else {
                        encuesta.setCostoMantenimiento("bajo");
                }

                // Pregunta 6: Nivel de actividad (basado en tiempo fuera)
                if (rbtnSITiempoFuera.isSelected()) {
                        encuesta.setNivelActividad("bajo"); // Poco tiempo en casa = mascota tranquila
                } else {
                        encuesta.setNivelActividad("alto"); // Mucho tiempo en casa = mascota activa
                }

                return encuesta;
        }

        /**
         * Interfaz para escuchar eventos de la encuesta
         */
        public interface EncuestaListener {
                void onBusquedaCompletada(EncuestaDTO encuesta);
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

                jPanel3 = new javax.swing.JPanel();
                btnBuscarMascotas = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                lblAnimalesPeludos = new javax.swing.JLabel();
                rbtnSIAnimalesPeludos = new javax.swing.JRadioButton();
                rbtnNoAnimalesPeludos = new javax.swing.JRadioButton();
                lblAlergias = new javax.swing.JLabel();
                rbtnNoAlergias = new javax.swing.JRadioButton();
                rbtnSiAlergias = new javax.swing.JRadioButton();
                lblCasaODepa = new javax.swing.JLabel();
                rbtnCasa = new javax.swing.JRadioButton();
                rbtnDepa = new javax.swing.JRadioButton();
                lblTamanoCasa = new javax.swing.JLabel();
                rbtnSICasaGrande = new javax.swing.JRadioButton();
                rbtnMasomenosCasaGrande = new javax.swing.JRadioButton();
                rbtnNoCasaGrande = new javax.swing.JRadioButton();
                lblSueldo = new javax.swing.JLabel();
                rbtnNogastobastante = new javax.swing.JRadioButton();
                rbtnGastoBastante = new javax.swing.JRadioButton();
                lblTiempoFuera = new javax.swing.JLabel();
                rbtnSITiempoFuera = new javax.swing.JRadioButton();
                rbtnNotiempoFuera = new javax.swing.JRadioButton();
                jLabel1 = new javax.swing.JLabel();

                jPanel3.setBackground(new java.awt.Color(219, 213, 195));
                jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                btnBuscarMascotas.setText("Buscar");
                btnBuscarMascotas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarMascotasActionPerformed(evt);
                        }
                });
                jPanel3.add(btnBuscarMascotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, -1, -1));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                lblAnimalesPeludos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblAnimalesPeludos.setText("¿Te gustan los animales peludos?");

                rbtnSIAnimalesPeludos.setText("SI");
                rbtnSIAnimalesPeludos.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                rbtnSIAnimalesPeludosActionPerformed(evt);
                        }
                });

                rbtnNoAnimalesPeludos.setText("No");
                rbtnNoAnimalesPeludos.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                rbtnNoAnimalesPeludosActionPerformed(evt);
                        }
                });

                lblAlergias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblAlergias.setText("¿Tienes alergias?");

                rbtnNoAlergias.setText("No");
                rbtnNoAlergias.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                rbtnNoAlergiasActionPerformed(evt);
                        }
                });

                rbtnSiAlergias.setText("SI");
                rbtnSiAlergias.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                rbtnSiAlergiasActionPerformed(evt);
                        }
                });

                lblCasaODepa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblCasaODepa.setText("¿Vives en una casa o en un departamento?");

                rbtnCasa.setText("Casa");

                rbtnDepa.setText("Departamento");

                lblTamanoCasa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblTamanoCasa.setText("¿Tu casa/depa es grande?");

                rbtnSICasaGrande.setText("SI");

                rbtnMasomenosCasaGrande.setText("Maso menos");

                rbtnNoCasaGrande.setText("No");

                lblSueldo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblSueldo.setText("¿Estas dispuest@ a gastar bastante?");

                rbtnNogastobastante.setText("No");

                rbtnGastoBastante.setText("SI");

                lblTiempoFuera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                lblTiempoFuera.setText("¿Pasaras mucho tiempo fuera de tu hogar?");

                rbtnSITiempoFuera.setText("Si");
                rbtnSITiempoFuera.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                rbtnSITiempoFueraActionPerformed(evt);
                        }
                });

                rbtnNotiempoFuera.setText("No");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(lblAnimalesPeludos,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                250,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(10, 10, 10)
                                                                                                .addComponent(rbtnNoAnimalesPeludos,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(200, 200, 200)
                                                                                                .addComponent(rbtnSIAnimalesPeludos,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(lblAlergias,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                250,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(20, 20, 20)
                                                                                                .addComponent(rbtnNoAlergias,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(200, 200, 200)
                                                                                                .addComponent(rbtnSiAlergias,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(240, 240,
                                                                                                                                                240)
                                                                                                                                .addComponent(rbtnCasa,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                90,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(lblTamanoCasa,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                250,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(70, 70, 70)
                                                                                                .addComponent(rbtnNoCasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(320, 320, 320)
                                                                                                .addComponent(rbtnDepa,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                140,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(lblCasaODepa,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                270,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(200, 200, 200)
                                                                                                .addComponent(rbtnSICasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(280, 280, 280)
                                                                                                .addComponent(rbtnMasomenosCasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                130,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(330, 330, 330)
                                                                                                .addComponent(rbtnNotiempoFuera,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(200, 200, 200)
                                                                                                .addComponent(rbtnGastoBastante,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(lblSueldo,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                230,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblTiempoFuera,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                270,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(280, 280, 280)
                                                                                                .addComponent(rbtnNogastobastante,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(260, 260, 260)
                                                                                                .addComponent(rbtnSITiempoFuera,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                80,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(lblAnimalesPeludos,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(rbtnNoAnimalesPeludos,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(rbtnSIAnimalesPeludos,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(lblAlergias,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(rbtnNoAlergias,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(rbtnSiAlergias,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(rbtnCasa,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(lblTamanoCasa,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(rbtnNoCasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                60,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(rbtnDepa,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblCasaODepa,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(rbtnSICasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                60,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(rbtnMasomenosCasaGrande,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                60,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(rbtnNotiempoFuera,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                60,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(rbtnGastoBastante,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblSueldo,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                50,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(lblTiempoFuera,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(rbtnNogastobastante,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(rbtnSITiempoFuera,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                60,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(14, Short.MAX_VALUE)));

                jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 500, 360));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
                jLabel1.setText("Encuesta");
                jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 190, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                1291,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                601,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
        }// </editor-fold>//GEN-END:initComponents

        private void btnBuscarMascotasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBuscarMascotasActionPerformed
                EncuestaDTO encuesta = obtenerCriterios();
                if (encuesta != null && listener != null) {
                        listener.onBusquedaCompletada(encuesta);
                }
        }// GEN-LAST:event_btnBuscarMascotasActionPerformed

        private void rbtnNoAlergiasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rbtnNoAlergiasActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_rbtnNoAlergiasActionPerformed

        private void rbtnNoAnimalesPeludosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rbtnNoAnimalesPeludosActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_rbtnNoAnimalesPeludosActionPerformed

        private void rbtnSIAnimalesPeludosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rbtnSIAnimalesPeludosActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_rbtnSIAnimalesPeludosActionPerformed

        private void rbtnSiAlergiasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rbtnSiAlergiasActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_rbtnSiAlergiasActionPerformed

        private void rbtnSITiempoFueraActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_rbtnSITiempoFueraActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_rbtnSITiempoFueraActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnBuscarMascotas;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JLabel lblAlergias;
        private javax.swing.JLabel lblAnimalesPeludos;
        private javax.swing.JLabel lblCasaODepa;
        private javax.swing.JLabel lblSueldo;
        private javax.swing.JLabel lblTamanoCasa;
        private javax.swing.JLabel lblTiempoFuera;
        private javax.swing.JRadioButton rbtnCasa;
        private javax.swing.JRadioButton rbtnDepa;
        private javax.swing.JRadioButton rbtnGastoBastante;
        private javax.swing.JRadioButton rbtnMasomenosCasaGrande;
        private javax.swing.JRadioButton rbtnNoAlergias;
        private javax.swing.JRadioButton rbtnNoAnimalesPeludos;
        private javax.swing.JRadioButton rbtnNoCasaGrande;
        private javax.swing.JRadioButton rbtnNogastobastante;
        private javax.swing.JRadioButton rbtnNotiempoFuera;
        private javax.swing.JRadioButton rbtnSIAnimalesPeludos;
        private javax.swing.JRadioButton rbtnSICasaGrande;
        private javax.swing.JRadioButton rbtnSITiempoFuera;
        private javax.swing.JRadioButton rbtnSiAlergias;
        // End of variables declaration//GEN-END:variables
}
