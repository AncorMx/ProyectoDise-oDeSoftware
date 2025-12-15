/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package presentacion.gestioncatalogo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import gestion.catalogo.dtos.CatalogoDTO;

/**
 *
 * @author angel
 */
public class EditarMascotaPanel extends javax.swing.JPanel {

        private PanelPrincipalGestionCatalogo panelPrincipal;
        private File imagenSeleccionada;
        private CatalogoDTO mascotaActual;
        private boolean imagenCambiada = false;

        /**
         * Creates new form AgregarMascotaDialog
         */
        public EditarMascotaPanel() {
                initComponents();
        }

        public void setPanelPrincipal(PanelPrincipalGestionCatalogo panelPrincipal) {
                this.panelPrincipal = panelPrincipal;
        }

        /**
         * Carga los datos de la mascota en el formulario
         */
        public void setDatosMascota(CatalogoDTO mascota) {
                this.mascotaActual = mascota;
                this.imagenCambiada = false;

                // Limpiar modelo si tiene items por defecto (Item 1...) o está vacío
                javax.swing.DefaultComboBoxModel<String> model = (javax.swing.DefaultComboBoxModel<String>) cb_especie
                                .getModel();
                if (model.getSize() > 0 && "Item 1".equals(model.getElementAt(0))) {
                        model.removeAllElements();
                        model.addElement("Perro");
                        model.addElement("Gato");
                        model.addElement("Ave");
                        model.addElement("Reptil");
                        model.addElement("Otro");
                }

                // Selección segura de especie
                String especie = mascota.getEspecie();
                if (especie != null && !especie.trim().isEmpty()) {
                        boolean encontrado = false;
                        for (int i = 0; i < model.getSize(); i++) {
                                if (model.getElementAt(i).equalsIgnoreCase(especie)) {
                                        cb_especie.setSelectedIndex(i);
                                        encontrado = true;
                                        break;
                                }
                        }
                        if (!encontrado) {
                                model.addElement(especie);
                                cb_especie.setSelectedItem(especie);
                        }
                }

                tfNombre.setText(mascota.getNombre());
                tfRaza.setText(mascota.getRaza());
                tfColor.setText(mascota.getColor());
                tfEdad.setText(String.valueOf(mascota.getEdad()));
                tfPeso.setText(String.valueOf(mascota.getPeso()));
                tfDescripcion.setText(mascota.getEstadoSalud());
                tfPeso1.setText(mascota.getPersonalidad());

                // Cargar imagen si existe
                if (mascota.getUrlImagen() != null && !mascota.getUrlImagen().isEmpty()) {
                        cargarImagenExistente(mascota.getUrlImagen());
                }
        }

        private void cargarImagenExistente(String urlImagen) {
                try {
                        // Normalizar la ruta: quitar "/" inicial si existe
                        String rutaRecurso = urlImagen.startsWith("/") ? urlImagen.substring(1) : urlImagen;

                        // Intentar cargar desde recursos primero
                        java.net.URL imageUrl = getClass().getClassLoader().getResource(rutaRecurso);
                        if (imageUrl != null) {
                                try {
                                        // Convertir URL a File si es posible
                                        File archivoImagen = new File(imageUrl.toURI());
                                        imagenSeleccionada = archivoImagen;
                                        cargarImagenEnLabel(archivoImagen);
                                        return;
                                } catch (Exception ex) {
                                        // Si no se puede convertir a File, cargar directamente desde URL
                                        BufferedImage img = javax.imageio.ImageIO.read(imageUrl);
                                        if (img != null) {
                                                Image scaledImg = img.getScaledInstance(
                                                                lbl_Img.getWidth() > 0 ? lbl_Img.getWidth() : 200,
                                                                lbl_Img.getHeight() > 0 ? lbl_Img.getHeight() : 200,
                                                                Image.SCALE_SMOOTH);
                                                lbl_Img.setIcon(new ImageIcon(scaledImg));
                                                lbl_Img.setText("");
                                                return;
                                        }
                                }
                        }

                        // Si no se encontró en recursos, intentar como archivo absoluto
                        File archivoImagen = new File(urlImagen);
                        if (archivoImagen.exists()) {
                                imagenSeleccionada = archivoImagen;
                                cargarImagenEnLabel(archivoImagen);
                        } else {
                                System.err.println("Imagen no encontrada - Recurso: " + rutaRecurso + " | Archivo: "
                                                + urlImagen);
                                lbl_Img.setIcon(null);
                                lbl_Img.setText("No Encontrada");
                        }
                } catch (Exception e) {
                        System.err.println("Error al cargar imagen existente: " + e.getMessage());
                        lbl_Img.setIcon(null);
                        lbl_Img.setText("Error");
                }
        }

        private void cargarImagenEnLabel(File archivo) {
                if (archivo == null || !archivo.exists()) {
                        System.err.println(
                                        "Archivo de imagen no encontrado: "
                                                        + (archivo != null ? archivo.getAbsolutePath() : "null"));
                        return;
                }

                try {
                        java.awt.image.BufferedImage bimg = javax.imageio.ImageIO.read(archivo);
                        if (bimg != null) {
                                Image image = bimg.getScaledInstance(
                                                lbl_Img.getWidth(), lbl_Img.getHeight(),
                                                Image.SCALE_SMOOTH);
                                lbl_Img.setIcon(new ImageIcon(image));
                                lbl_Img.setText("");
                        } else {
                                System.err.println("No se pudo leer la imagen: " + archivo.getAbsolutePath());
                                lbl_Img.setText("Error Img");
                        }
                } catch (Exception e) {
                        System.err.println("Excepcion al cargar imagen: " + e.getMessage());
                        lbl_Img.setText("Error");
                }
        }

        private void seleccionarImagen() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar imagen de la mascota");
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Archivos de imagen", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);

                int resultado = fileChooser.showOpenDialog(this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                        imagenSeleccionada = fileChooser.getSelectedFile();
                        imagenCambiada = true;
                        cargarImagenEnLabel(imagenSeleccionada);
                }
        }

        private void guardarCambios() {
                if (validarCampos()) {
                        try {
                                // Actualiza datos de la mascota
                                mascotaActual.setNombre(tfNombre.getText().trim());
                                mascotaActual.setEspecie(cb_especie.getSelectedItem().toString());
                                mascotaActual.setEdad(Integer.parseInt(tfEdad.getText().trim()));
                                mascotaActual.setEstadoSalud(tfDescripcion.getText().trim());
                                mascotaActual.setPersonalidad(tfPeso1.getText().trim());

                                // Procesa nueva imagen si fue seleccionada y marcada como cambiada
                                if (imagenCambiada && imagenSeleccionada != null) {
                                        String destinoDir = "src/main/resources/images/mascotas/";
                                        File directorio = new File(destinoDir);
                                        if (!directorio.exists()) {
                                                directorio.mkdirs();
                                        }

                                        String extension = getFileExtension(imagenSeleccionada);
                                        String nuevoNombre = UUID.randomUUID().toString() + "." + extension;
                                        Path destino = Paths.get(destinoDir + nuevoNombre);

                                        Files.copy(imagenSeleccionada.toPath(), destino);
                                        mascotaActual.setUrlImagen(nuevoNombre);
                                }

                                // Llamada al control de presentación directamente para actualizar
                                gestion.catalogo.control.ControlPresentacion control = new gestion.catalogo.control.ControlPresentacion();
                                gestion.catalogo.dtos.ResultadoOperacion resultado = control
                                                .editarMascota(mascotaActual);

                                if (resultado.isExitoso()) {
                                        JOptionPane.showMessageDialog(this, "Mascota actualizada exitosamente.");
                                        if (panelPrincipal != null) {
                                                panelPrincipal.mostrarCatalogo();
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(this,
                                                        "Error al actualizar la mascota: " + resultado.getMensaje(),
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                }

                        } catch (Exception e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        private boolean validarCampos() {
                if (tfNombre.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "El nombre es requerido.");
                        return false;
                }
                if (tfEdad.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "La edad es requerida.");
                        return false;
                }
                try {
                        Integer.parseInt(tfEdad.getText().trim());
                } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.");
                        return false;
                }
                return true;
        }

        private String getFileExtension(File file) {
                String name = file.getName();
                int lastDot = name.lastIndexOf('.');
                return (lastDot > 0) ? name.substring(lastDot + 1) : "";
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
                java.awt.GridBagConstraints gridBagConstraints;

                jLabel2 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                btnCancelar = new javax.swing.JButton();
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
                lbl_Img = new javax.swing.JLabel();
                btnAgregarExp = new javax.swing.JButton();
                jLabel1 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                tfPeso1 = new javax.swing.JTextField();

                setBackground(new java.awt.Color(219, 213, 195));
                setLayout(new java.awt.GridBagLayout());

                jLabel2.setBackground(new java.awt.Color(0, 0, 0));
                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
                jLabel2.setText("Editor Mascotas");
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(6, 405, 0, 0);
                add(jLabel2, gridBagConstraints);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setForeground(new java.awt.Color(0, 0, 0));

                btnCancelar.setText("Cancelar");
                btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarActionPerformed(evt);
                        }
                });

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
                jLabel8.setText("Editar Expdiente Medico");

                tfDescripcion.setBackground(new java.awt.Color(204, 204, 204));

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
                jLabel7.setText("Estado salud");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(0, 0, 0));
                jLabel6.setText("Edad");

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
                btnSiguiente.setText("Editar");
                btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSiguienteActionPerformed(evt);
                        }
                });

                btnAgregarImg.setText("Agregar Imagen");
                btnAgregarImg.setActionCommand("Editar Imagen");
                btnAgregarImg.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarImgActionPerformed(evt);
                        }
                });

                cb_especie.setModel(new javax.swing.DefaultComboBoxModel<>(
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
                jLabel14.setText("Peso");

                lbl_Img.setForeground(new java.awt.Color(0, 0, 0));

                btnAgregarExp.setText("Editar Expediente Medico");
                btnAgregarExp.setActionCommand("EditarExpedienteMedico");
                btnAgregarExp.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarExpActionPerformed(evt);
                        }
                });

                jLabel1.setForeground(new java.awt.Color(0, 0, 0));
                jLabel1.setText("Ningun archivo seleccionado.");
                jLabel1.setToolTipText("");

                jLabel9.setBackground(new java.awt.Color(0, 0, 0));
                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(0, 0, 0));
                jLabel9.setText("Personalidad");

                tfPeso1.setBackground(new java.awt.Color(204, 204, 204));

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
                                                                                                                                                                .addComponent(tfPeso1,
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
                                                                                                                .addComponent(jLabel7)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(lbl_Img,
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
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addComponent(btnAgregarImg,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                303,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(tfNombre,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                320,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel4)))
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGap(1, 1, 1)
                                                                                                                                                                .addComponent(jLabel1))
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(tfRaza,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                310,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addComponent(jLabel12))))))
                                                                                                .addContainerGap(45,
                                                                                                                Short.MAX_VALUE))))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(248, 248, 248)
                                                                .addComponent(btnAgregarExp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                303,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(lbl_Img,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                120,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(btnAgregarImg,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                38,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel1))
                                                                                                                .addGap(39, 39, 39)))
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
                                                                                                                .addComponent(tfPeso1,
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
                                                                .addGap(30, 30, 30)
                                                                .addComponent(jLabel8)
                                                                .addGap(19, 19, 19)
                                                                .addComponent(btnAgregarExp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                38,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(btnSiguiente,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                52,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnCancelar,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(24, 24, 24)));

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridwidth = 2;
                gridBagConstraints.ipadx = 39;
                gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
                gridBagConstraints.insets = new java.awt.Insets(18, 109, 25, 120);
                add(jPanel2, gridBagConstraints);
        }// </editor-fold>//GEN-END:initComponents

        private void btnGuardarBorradorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarBorradorActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnGuardarBorradorActionPerformed

        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
                if (panelPrincipal != null) {
                        panelPrincipal.mostrarCatalogo();
                }
        }// GEN-LAST:event_btnCancelarActionPerformed

        private void cbAdoptadoPreviamenteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbAdoptadoPreviamenteActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_cbAdoptadoPreviamenteActionPerformed

        private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSiguienteActionPerformed
                guardarCambios();
        }// GEN-LAST:event_btnSiguienteActionPerformed

        private void btnAgregarImgActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarImgActionPerformed
                seleccionarImagen();
        }// GEN-LAST:event_btnAgregarImgActionPerformed

        private void btnAgregarExpActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarExpActionPerformed
                if (mascotaActual != null && panelPrincipal != null) {
                        panelPrincipal.mostrarExpedienteParaEdicion(mascotaActual.getId(), true);
                } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Datos de mascota no disponibles", "Error",
                                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
        }// GEN-LAST:event_btnAgregarExpActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAgregarExp;
        private javax.swing.JButton btnAgregarImg;
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnSiguiente;
        private javax.swing.JComboBox<String> cb_especie;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel lbl_Img;
        private javax.swing.JTextField tfColor;
        private javax.swing.JTextField tfDescripcion;
        private javax.swing.JTextField tfEdad;
        private javax.swing.JTextField tfNombre;
        private javax.swing.JTextField tfPeso;
        private javax.swing.JTextField tfPeso1;
        private javax.swing.JTextField tfRaza;
        // End of variables declaration//GEN-END:variables
}
