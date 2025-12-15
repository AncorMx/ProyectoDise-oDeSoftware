
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package presentacion.gestioncatalogo;

import gestion.catalogo.control.ControlPresentacion;
import gestion.catalogo.dtos.CatalogoDTO;
import gestion.catalogo.dtos.ExpedienteMedicoTemporal;
import gestion.catalogo.dtos.ResultadoOperacion;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Panel para agregar nuevas mascotas al catálogo
 *
 * @author angel
 */
public class AgregarMascotaPanel extends javax.swing.JPanel {

        private PanelPrincipalGestionCatalogo panelPrincipal;
        private ControlPresentacion control;
        private File archivoImagenSeleccionado = null;

        // Datos temporales del expediente médico
        private ExpedienteMedicoTemporal expedienteTemporalEnMemoria = null;

        /**
         * Clase interna para guardar datos temporales del formulario
         */
        private class DatosFormularioTemporal {

                String nombre, raza, color, edad, peso, descripcion, personalidad, especie, imagenPath;
        }

        private DatosFormularioTemporal datosFormularioTemp = null;

        /**
         * Creates new form AgregarMascotaDialog
         */
        public AgregarMascotaPanel() {
                initComponents();
                this.control = new ControlPresentacion();
                inicializarComboEspecies();
                limpiarFormulario();
        }

        /**
         * Setea la referencia al panel principal para navegación
         */
        public void setPanelPrincipal(PanelPrincipalGestionCatalogo panelPrincipal) {
                this.panelPrincipal = panelPrincipal;
        }

        /**
         * Inicializa el combo box de especies con valores predefinidos
         */
        private void inicializarComboEspecies() {
                cb_especie.removeAllItems();
                cb_especie.addItem("Perro");
                cb_especie.addItem("Gato");
                cb_especie.addItem("Ave");
                cb_especie.addItem("Reptil");
                cb_especie.addItem("Otro");
        }

        /**
         * Limpia todos los campos del formulario
         */
        public void limpiarFormulario() {
                tfNombre.setText("");
                tfEdad.setText("");
                tfRaza.setText("");
                tfColor.setText("");
                tfPeso.setText("");
                tfDescripcion.setText("");
                tfPersonalidad.setText("");
                cb_especie.setSelectedIndex(0);
                lbl_Imagen.setIcon(null);
                lbl_Imagen.setText("Sin imagen");
                jLabel3.setText("Ningún archivo seleccionado.");
                archivoImagenSeleccionado = null;

                // Limpiar expediente temporal
                expedienteTemporalEnMemoria = null;
                datosFormularioTemp = null;
                System.out.println("Formulario y expediente temporal limpiados");
        }

        /**
         * Guarda datos temporales del expediente médico
         */
        public void guardarExpedienteTemporal(ExpedienteMedicoTemporal datos) {
                this.expedienteTemporalEnMemoria = datos;
                System.out.println("Expediente médico guardado temporalmente");
        }

        /**
         * Obtiene los datos temporales del expediente médico
         */
        public ExpedienteMedicoTemporal getExpedienteTemporal() {
                return this.expedienteTemporalEnMemoria;
        }

        /**
         * Guarda los datos actuales del formulario en memoria
         */
        public void guardarDatosFormulario() {
                DatosFormularioTemporal datos = new DatosFormularioTemporal();
                datos.nombre = tfNombre.getText();
                datos.edad = tfEdad.getText();
                datos.raza = tfRaza.getText();
                datos.color = tfColor.getText();
                datos.peso = tfPeso.getText();
                datos.descripcion = tfDescripcion.getText();
                datos.personalidad = tfPersonalidad.getText();
                datos.especie = (String) cb_especie.getSelectedItem();
                if (archivoImagenSeleccionado != null) {
                        datos.imagenPath = archivoImagenSeleccionado.getAbsolutePath();
                }
                this.datosFormularioTemp = datos;
                System.out.println("Datos del formulario guardados temporalmente");
        }

        /**
         * Carga los datos guardados del formulario
         */
        public void cargarDatosFormulario() {
                if (datosFormularioTemp != null) {
                        tfNombre.setText(datosFormularioTemp.nombre != null ? datosFormularioTemp.nombre : "");
                        tfEdad.setText(datosFormularioTemp.edad != null ? datosFormularioTemp.edad : "");
                        tfRaza.setText(datosFormularioTemp.raza != null ? datosFormularioTemp.raza : "");
                        tfColor.setText(datosFormularioTemp.color != null ? datosFormularioTemp.color : "");
                        tfPeso.setText(datosFormularioTemp.peso != null ? datosFormularioTemp.peso : "");
                        tfDescripcion.setText(
                                        datosFormularioTemp.descripcion != null ? datosFormularioTemp.descripcion : "");
                        tfPersonalidad.setText(
                                        datosFormularioTemp.personalidad != null ? datosFormularioTemp.personalidad
                                                        : "");
                        if (datosFormularioTemp.especie != null) {
                                cb_especie.setSelectedItem(datosFormularioTemp.especie);
                        }
                        if (datosFormularioTemp.imagenPath != null) {
                                archivoImagenSeleccionado = new File(datosFormularioTemp.imagenPath);
                                jLabel3.setText(archivoImagenSeleccionado.getName());
                                try {
                                        ImageIcon icon = new ImageIcon(datosFormularioTemp.imagenPath);
                                        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                        lbl_Imagen.setIcon(new ImageIcon(img));
                                        lbl_Imagen.setText("");
                                } catch (Exception e) {
                                        System.err.println("Error al cargar preview de imagen: " + e.getMessage());
                                }
                        }
                        System.out.println("Datos del formulario restaurados");
                }
        }

        /**
         * Valida que los campos obligatorios estén llenos
         */
        private boolean validarCampos() {
                if (tfNombre.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                        "El nombre es obligatorio",
                                        "Validación",
                                        JOptionPane.WARNING_MESSAGE);
                        tfNombre.requestFocus();
                        return false;
                }

                if (tfEdad.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                        "La edad es obligatoria",
                                        "Validación",
                                        JOptionPane.WARNING_MESSAGE);
                        tfEdad.requestFocus();
                        return false;
                }

                // Validar que la edad sea un número
                try {
                        int edad = Integer.parseInt(tfEdad.getText().trim());
                        if (edad < 0 || edad > 30) {
                                JOptionPane.showMessageDialog(this,
                                                "La edad debe estar entre 0 y 30 años",
                                                "Validación",
                                                JOptionPane.WARNING_MESSAGE);
                                tfEdad.requestFocus();
                                return false;
                        }
                } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this,
                                        "La edad debe ser un número válido",
                                        "Validación",
                                        JOptionPane.WARNING_MESSAGE);
                        tfEdad.requestFocus();
                        return false;
                }

                if (tfPersonalidad.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                        "La personalidad es obligatoria",
                                        "Validación",
                                        JOptionPane.WARNING_MESSAGE);
                        tfPersonalidad.requestFocus();
                        return false;
                }

                return true;
        }

        /**
         * Recolecta los datos del formulario y crea un CatalogoDTO
         */
        private CatalogoDTO recolectarDatos() {
                CatalogoDTO mascota = new CatalogoDTO();

                mascota.setNombre(tfNombre.getText().trim());
                mascota.setEspecie(cb_especie.getSelectedItem().toString());
                mascota.setEdad(Integer.parseInt(tfEdad.getText().trim()));
                mascota.setEstadoSalud(tfDescripcion.getText().trim().isEmpty() ? "Saludable"
                                : tfDescripcion.getText().trim());
                mascota.setPersonalidad(tfPersonalidad.getText().trim());
                mascota.setEstado("disponible");
                mascota.setDisponible(true);

                // Nuevos campos
                mascota.setColor(tfColor.getText().trim());
                mascota.setRaza(tfRaza.getText().trim());
                try {
                        String pesoText = tfPeso.getText().trim();
                        if (!pesoText.isEmpty()) {
                                mascota.setPeso(Double.parseDouble(pesoText));
                        } else {
                                mascota.setPeso(0.0);
                        }
                } catch (NumberFormatException e) {
                        mascota.setPeso(0.0);
                        System.err.println("Error al parsear peso, usando 0.0: " + e.getMessage());
                }

                // Si hay imagen seleccionada, guardar la ruta
                // RUTA ABSOLUTA para compatibilidad entre módulos
                if (archivoImagenSeleccionado != null) {
                        try {
                                // Obtener raíz del proyecto
                                File projectRoot = new File(System.getProperty("user.dir"));
                                if (projectRoot.getName().equals("gestion-catalogo")) {
                                        projectRoot = projectRoot.getParentFile();
                                }

                                // Construir ruta a Infraestructura/src/main/resources/imagenes/mascotas
                                File destino = new File(projectRoot,
                                                "Infraestructura/src/main/resources/imagenes/mascotas");
                                File imagenDestino = new File(destino, archivoImagenSeleccionado.getName());

                                String rutaAbsoluta = imagenDestino.getAbsolutePath();
                                mascota.setUrlImagen(rutaAbsoluta);
                                System.out.println("Imagen con ruta absoluta: " + rutaAbsoluta);
                        } catch (Exception e) {
                                System.err.println("Error construyendo ruta imagen: " + e.getMessage());
                        }
                }

                // Adjuntar expediente médico si existe
                if (expedienteTemporalEnMemoria != null) {
                        mascota.setDatosExpediente(expedienteTemporalEnMemoria);
                        System.out.println("Adjuntando expediente médico temporal al DTO");
                }

                // Adjuntar expediente médico si existe
                if (expedienteTemporalEnMemoria != null) {
                        mascota.setDatosExpediente(expedienteTemporalEnMemoria);
                        System.out.println("Adjuntando expediente médico temporal al DTO");
                }

                return mascota;
        }

        /**
         * Guarda la mascota en la base de datos
         */
        private void guardarMascota() {
                if (!validarCampos()) {
                        return;
                }

                try {
                        CatalogoDTO mascota = recolectarDatos();

                        System.out.println("Guardando mascota: " + mascota.getNombre());
                        System.out.println(" - Especie: " + mascota.getEspecie());
                        System.out.println(" - Edad: " + mascota.getEdad());
                        System.out.println(" - Estado Salud: " + mascota.getEstadoSalud());
                        System.out.println(" - Personalidad: " + mascota.getPersonalidad());
                        System.out.println(" - Estado: " + mascota.getEstado());

                        ResultadoOperacion resultado = control.agregarMascota(mascota);

                        if (resultado.isExitoso()) {
                                System.out.println("Mascota guardada exitosamente");

                                if (archivoImagenSeleccionado != null) {
                                        copiarImagenARecursos();
                                }

                                JOptionPane.showMessageDialog(this,
                                                resultado.getMensaje(),
                                                "Éxito",
                                                JOptionPane.INFORMATION_MESSAGE);

                                limpiarFormulario();

                                if (panelPrincipal != null) {
                                        panelPrincipal.mostrarConfirmacion(mascota);
                                }
                        } else {
                                System.err.println("Error al guardar: " + resultado.getMensaje());
                                JOptionPane.showMessageDialog(this,
                                                "Error al guardar:\n" + resultado.getMensaje(),
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }

                } catch (Exception e) {
                        System.err.println("Excepción al guardar mascota: " + e.getMessage());
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this,
                                        "Error inesperado:\n" + e.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        /**
         * Copia la imagen seleccionada al directorio de recursos
         */
        private void copiarImagenARecursos() {
                try {
                        // Obtener raíz del proyecto
                        File projectRoot = new File(System.getProperty("user.dir"));
                        if (projectRoot.getName().equals("gestion-catalogo")) {
                                projectRoot = projectRoot.getParentFile();
                        }

                        // Construir ruta a Infraestructura/src/main/resources/imagenes/mascotas
                        File directorioImagenes = new File(projectRoot,
                                        "Infraestructura/src/main/resources/imagenes/mascotas");

                        if (!directorioImagenes.exists()) {
                                directorioImagenes.mkdirs();
                                System.out.println("Directorio creado: " + directorioImagenes.getAbsolutePath());
                        }

                        File destino = new File(directorioImagenes, archivoImagenSeleccionado.getName());
                        java.nio.file.Files.copy(
                                        archivoImagenSeleccionado.toPath(),
                                        destino.toPath(),
                                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                        System.out.println("Imagen copiada a: " + destino.getAbsolutePath());

                } catch (Exception e) {
                        System.err.println("Error al copiar imagen: " + e.getMessage());
                }
        }

        /**
         * Abre un selector de archivos para elegir una imagen
         */
        private void seleccionarImagen() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar imagen de la mascota");

                FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                                "Imágenes (JPG, PNG, JPEG)", "jpg", "jpeg", "png");
                fileChooser.setFileFilter(filtro);
                fileChooser.setAcceptAllFileFilterUsed(false);

                int resultado = fileChooser.showOpenDialog(this);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                        archivoImagenSeleccionado = fileChooser.getSelectedFile();
                        jLabel3.setText(archivoImagenSeleccionado.getName());

                        try {
                                ImageIcon icon = new ImageIcon(archivoImagenSeleccionado.getAbsolutePath());
                                Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                                lbl_Imagen.setIcon(new ImageIcon(img));
                                lbl_Imagen.setText("");
                                System.out.println("Imagen seleccionada: " + archivoImagenSeleccionado.getName());
                        } catch (Exception e) {
                                System.err.println("Error al cargar preview: " + e.getMessage());
                        }
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
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {
                java.awt.GridBagConstraints gridBagConstraints;

                jLabel2 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                btnCancelar = new javax.swing.JButton();
                jLabel11 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                tfDescripcion = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                tfEdad = new javax.swing.JTextField();
                tfNombre = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                btnSiguiente = new javax.swing.JButton();
                btnAgregarImg = new javax.swing.JButton();
                cb_especie = new javax.swing.JComboBox<>();
                tfRaza = new javax.swing.JTextField();
                jLabel12 = new javax.swing.JLabel();
                tfColor = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                tfPeso = new javax.swing.JTextField();
                jLabel14 = new javax.swing.JLabel();
                lbl_Imagen = new javax.swing.JLabel();
                btnAgregarExp = new javax.swing.JButton();
                jLabel3 = new javax.swing.JLabel();
                tfPersonalidad = new javax.swing.JTextField();
                jLabel9 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(219, 213, 195));
                setLayout(new java.awt.GridBagLayout());

                jLabel2.setBackground(new java.awt.Color(0, 0, 0));
                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
                jLabel2.setText("Agregar Mascotas");
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(6, 463, 0, 0);
                add(jLabel2, gridBagConstraints);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setForeground(new java.awt.Color(0, 0, 0));

                btnCancelar.setText("Cancelar");
                btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarActionPerformed(evt);
                        }
                });

                jLabel11.setForeground(new java.awt.Color(102, 102, 102));
                jLabel11.setText(
                                "Esta es una funcionalidad opcional, que de ser preferente debe ser llenada completamente.");

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
                jLabel8.setText("Agregar Expdiente Medico");

                tfDescripcion.setBackground(new java.awt.Color(204, 204, 204));

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
                jLabel7.setText("Estado Salud");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(0, 0, 0));
                jLabel6.setText("Edad (Años)");

                tfEdad.setBackground(new java.awt.Color(204, 204, 204));

                tfNombre.setBackground(new java.awt.Color(204, 204, 204));

                jLabel4.setBackground(new java.awt.Color(0, 0, 0));
                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
                jLabel4.setText("Nombre");

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
                jLabel5.setText("Especie");

                btnSiguiente.setBackground(new java.awt.Color(0, 153, 51));
                btnSiguiente.setText("Agregar");
                btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSiguienteActionPerformed(evt);
                        }
                });

                btnAgregarImg.setText("Agregar Imagen");
                btnAgregarImg.setActionCommand("Agregrar Imagen");
                btnAgregarImg.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarImgActionPerformed(evt);
                        }
                });

                cb_especie.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                tfRaza.setBackground(new java.awt.Color(204, 204, 204));

                jLabel12.setBackground(new java.awt.Color(0, 0, 0));
                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(0, 0, 0));
                jLabel12.setText("Raza");

                tfColor.setBackground(new java.awt.Color(204, 204, 204));

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(0, 0, 0));
                jLabel13.setText("Color");

                tfPeso.setBackground(new java.awt.Color(204, 204, 204));

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(0, 0, 0));
                jLabel14.setText("Peso (Kg)");

                lbl_Imagen.setForeground(new java.awt.Color(0, 0, 0));

                btnAgregarExp.setText("Agregar Expediente Medico");
                btnAgregarExp.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarExpActionPerformed(evt);
                        }
                });

                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
                jLabel3.setText("Ningun archivo seleccionado.");
                jLabel3.setToolTipText("");

                tfPersonalidad.setBackground(new java.awt.Color(204, 204, 204));

                jLabel9.setBackground(new java.awt.Color(0, 0, 0));
                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(0, 0, 0));
                jLabel9.setText("Personalidad");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(31, 31, 31)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(tfDescripcion,
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(btnCancelar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                93,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(btnSiguiente,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                130,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                                                .addComponent(tfEdad,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                139,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(25, 25, 25)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jLabel13)
                                                                                                                                                                .addComponent(tfColor,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                211,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(tfPeso,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                158,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel14))
                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jLabel9)
                                                                                                                                                                .addComponent(tfPersonalidad,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                158,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                                                .addGap(34, 34, 34))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel8)
                                                                                                                .addComponent(jLabel6)
                                                                                                                .addComponent(jLabel11)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(lbl_Imagen,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                120,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(cb_especie,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                139,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel5)))
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(tfNombre,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                320,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(jLabel4))
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(tfRaza,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                310,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(jLabel12)))
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addComponent(btnAgregarImg,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                303,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                .addComponent(jLabel3))))
                                                                                                                .addComponent(jLabel7))
                                                                                                .addContainerGap(45,
                                                                                                                Short.MAX_VALUE))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(btnAgregarExp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                303,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(288, 288, 288)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(42, 42, 42)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btnAgregarImg,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel3)))
                                                                                .addComponent(lbl_Imagen,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                120,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel12)
                                                                                                                .addComponent(jLabel4)
                                                                                                                .addComponent(jLabel5))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(tfRaza,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(tfNombre,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(cb_especie,
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
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                                .addComponent(jLabel14)
                                                                                                                                                .addComponent(jLabel9)))))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
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
                                                                                                                .addComponent(tfPeso,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(tfPersonalidad,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel7)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tfDescripcion,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(22, 22, 22)
                                                                .addComponent(jLabel8)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel11)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnAgregarExp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                38,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(btnSiguiente,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                52,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnCancelar,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                56,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(24, 24, 24)));

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridwidth = 2;
                gridBagConstraints.ipadx = 39;
                gridBagConstraints.ipady = 4;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(18, 189, 62, 131);
                add(jPanel2, gridBagConstraints);
        }// </editor-fold>//GEN-END:initComponents

        private void btnGuardarBorradorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarBorradorActionPerformed
                // TODO: Implementar guardado como borrador
                JOptionPane.showMessageDialog(this,
                                "Funcionalidad de borrador en desarrollo",
                                "Info",
                                JOptionPane.INFORMATION_MESSAGE);
        }// GEN-LAST:event_btnGuardarBorradorActionPerformed

        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
                int confirmacion = JOptionPane.showConfirmDialog(this,
                                "¿Desea cancelar? Los datos ingresados se perderán.",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                        limpiarFormulario();
                        if (panelPrincipal != null) {
                                panelPrincipal.mostrarCatalogo();
                        }
                }
        }// GEN-LAST:event_btnCancelarActionPerformed

        private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSiguienteActionPerformed
                guardarMascota();
        }// GEN-LAST:event_btnSiguienteActionPerformed

        private void btnAgregarImgActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarImgActionPerformed
                seleccionarImagen();
        }// GEN-LAST:event_btnAgregarImgActionPerformed

        private void btnAgregarExpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarExpActionPerformed
                // Guardar datos del formulario antes de navegar
                guardarDatosFormulario();

                // Navegar al panel de expediente médico
                if (panelPrincipal != null) {
                        panelPrincipal.mostrarExpedienteMedico();
                }
        }// GEN-LAST:event_btnAgregarExpActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAgregarExp;
        private javax.swing.JButton btnAgregarImg;
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnSiguiente;
        private javax.swing.JComboBox<String> cb_especie;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel lbl_Imagen;
        private javax.swing.JTextField tfColor;
        private javax.swing.JTextField tfDescripcion;
        private javax.swing.JTextField tfEdad;
        private javax.swing.JTextField tfNombre;
        private javax.swing.JTextField tfPersonalidad;
        private javax.swing.JTextField tfPeso;
        private javax.swing.JTextField tfRaza;
        // End of variables declaration//GEN-END:variables
}
