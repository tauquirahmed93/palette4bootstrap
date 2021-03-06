/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JDialog;
import org.openide.util.NbBundle;

public class TypeMoreCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final TypeMore typeMore;

    /**
     * Creates new form TypeMoreDialog
     */
    public TypeMoreCustomizer(TypeMore typeMore) {
        this.typeMore = typeMore;
        initComponents();
        fxPanel = new JFXPanel();   // JFXPanel is required to display JavaFX
        Platform.setImplicitExit(false);    // Prevents jfx thread from shutting down when dialog is closed
        createScene();              // content inside Swing containers
        jPreview.add(fxPanel, BorderLayout.CENTER);
        fxPanel.setVisible(true);
    }

    public boolean showDialog() {
        dialogOK = false;
        jd = new JDialog();
        jd.setSize(this.getPreferredSize());
        jd.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));
        jd.setTitle(NbBundle.getMessage(getClass(), "Customizer.InsertPrefix")
                + " " + NbBundle.getMessage(getClass(), "TYPOGRAPHY.MORE.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of typeMore object so that old values
                   does not appear on re-opening the dialog */
                typeMore.setText("Enter text here");
                typeMore.setElementType("small");
                typeMore.removeAllAttributes();
            }
        });
        return dialogOK;
    }

    private void createScene() {
        // webView is used to display live preview of the components. It is a
        // JavaFX component so it must be run on the JavaFX thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Set up the embedded browser:
                WebView browser = new WebView();
                webEngine = browser.getEngine();
                webEngine.load(getClass().getResource("resources/preview.html").toExternalForm());
                fxPanel.setScene(new Scene(browser));

                /* The following code updates the preview window with content
                   from typeMore.generateBody() after the webEngine loads the page
                 */
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            String processedString = typeMore.generateBody().trim().replaceAll("\n", "&nbsp;");
                            String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                            webEngine.executeScript(script);
                        }
                    }
                });
            }
        });
    }

    private void updatePreviews() {
        // Update the Generated Code textArea
        codeGenerated.setText(typeMore.generateBody());
        // Update the live preview area in javafx thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // replace \n with &nbsp; or else Unexpected EOF error will occur inside executeScript()
                String processedString = typeMore.generateBody().trim().replaceAll("\n", "&nbsp;");
                String script = "document.getElementById('previewContainer').innerHTML='" + processedString + "'";
                webEngine.executeScript(script);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPreview = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        codeGenerated = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        elementType = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        dialogOKBtn = new javax.swing.JButton();
        dialogCancelBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        textTransformation = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        textColor = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        bgColor = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        elementInfo = new javax.swing.JTextPane();

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        codeGenerated.setEditable(false);
        codeGenerated.setColumns(20);
        codeGenerated.setLineWrap(true);
        codeGenerated.setRows(5);
        codeGenerated.setText("<small>Enter text here</small>"); // NOI18N
        codeGenerated.setWrapStyleWord(true);
        codeGenerated.setFocusable(false);
        jScrollPane1.setViewportView(codeGenerated);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "Customizer.GeneratedCode")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "Customizer.Preview")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textBox.toolTipText")); // NOI18N

        textBox.setText("Enter text here"); // NOI18N
        textBox.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textBox.toolTipText")); // NOI18N
        textBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBoxFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.elementType.toolTipText")); // NOI18N

        elementType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "small", "strong", "em", "del", "ins", "b", "i", "s", "u", "mark", "span" }));
        elementType.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.elementType.toolTipText")); // NOI18N
        elementType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                elementTypeItemStateChanged(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "Customizer.OK")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "Customizer.Cancel")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.jLabel6.text")); // NOI18N
        jLabel6.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textTransformation.toolTipText")); // NOI18N

        textTransformation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "text-lowercase", "text-uppercase", "text-capitalize" }));
        textTransformation.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textTransformation.toolTipText")); // NOI18N
        textTransformation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.jLabel7.text")); // NOI18N
        jLabel7.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textColor.toolTipText")); // NOI18N

        textColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "text-muted", "text-primary", "text-success", "text-info", "text-warning", "text-danger" }));
        textColor.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.textColor.toolTipText")); // NOI18N
        textColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.bgColor.toolTipText")); // NOI18N

        bgColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "bg-primary", "bg-success", "bg-info", "bg-warning", "bg-danger" }));
        bgColor.setToolTipText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.bgColor.toolTipText")); // NOI18N
        bgColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        elementInfo.setEditable(false);
        elementInfo.setContentType("text/html"); // NOI18N
        elementInfo.setText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.elementInfo.small")); // NOI18N
        elementInfo.setAutoscrolls(false);
        elementInfo.setFocusable(false);
        jScrollPane2.setViewportView(elementInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(textBox)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textTransformation, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                            .addComponent(textColor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bgColor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(elementType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(elementType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(bgColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textTransformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogButtonActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        if (button.equals(dialogOKBtn)) {
            dialogOK = true;
            jd.dispose();

        } else if (button.equals(dialogCancelBtn)) {
            dialogOK = false;
            jd.dispose();
        }
    }//GEN-LAST:event_dialogButtonActionPerformed

    private void textBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBoxFocusLost
        typeMore.setText(textBox.getText());
        updatePreviews();
    }//GEN-LAST:event_textBoxFocusLost

    private void elementTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_elementTypeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {    // Ignore ItemEvent.DESELECTED
            String selectedElement = evt.getItem().toString();
            // First we set element type in typeMore object to the selection
            typeMore.setElementType(selectedElement);
            //  Then we update the elementInfo textbox according to the checkbox selection
            elementInfo.setText(org.openide.util.NbBundle.getMessage(TypeMoreCustomizer.class, "TypeMoreCustomizer.elementInfo."+selectedElement));
            // Then we update the previews
            updatePreviews();
        }
    }//GEN-LAST:event_elementTypeItemStateChanged

    private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {    // Ignore ItemEvent.DESELECTED
            if (!evt.getItem().equals("Default")) {             // Add the selected item to the list
                typeMore.addAttribute(evt.getItem().toString()); // if "Default" option was not selected
                updatePreviews();
            }
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            typeMore.removeAttribute(evt.getItem().toString());  // Remove the deselected item from the list
            updatePreviews();
        }
    }//GEN-LAST:event_jComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bgColor;
    private javax.swing.JTextArea codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JTextPane elementInfo;
    private javax.swing.JComboBox<String> elementType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPreview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textBox;
    private javax.swing.JComboBox<String> textColor;
    private javax.swing.JComboBox<String> textTransformation;
    // End of variables declaration//GEN-END:variables
}
