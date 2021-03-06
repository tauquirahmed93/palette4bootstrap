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
import netscape.javascript.JSObject;
import org.openide.util.NbBundle;

public class FontAwesomeCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final FontAwesome fontAwesome;

    /**
     * Creates new form FontAwesomeCustomizer
     */
    public FontAwesomeCustomizer(FontAwesome fontAwesome) {
        this.fontAwesome = fontAwesome;
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
                + " " + NbBundle.getMessage(getClass(), "MEDIA.FONTAWESOME.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of fontAwesome object so that old values
                   does not appear on re-opening the dialog */
                fontAwesome.setFaIconName("address-book");
                fontAwesome.resetClasses();
                fontAwesome.setAriaHidden(true);
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
                webEngine.load(getClass().getResource("resources/fontAwesomeSelector.html").toExternalForm());
                fxPanel.setScene(new Scene(browser));
                /* The following code is used to run functions after the webpage
                    has completed loading
                 */
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            JSObject win = (JSObject) webEngine.executeScript("window");
                            win.setMember("interface", new JSInterface());
                        }
                    }
                });
            }
        });
    }

    /**
     * JavaScript interface object Used to make upcalls from javascript inside
     * the webpage loaded in the javaFX webview to FontAwesomeCustomizer.java
     */
    @SuppressWarnings("PublicInnerClass")
    public class JSInterface {

        public void setSelection(String selection) {
            fontAwesome.setFaIconName(selection);
            codeGenerated.setText(fontAwesome.generateBody());
        }

        public void closeDialog(boolean dialogAccepted) {
            FontAwesomeCustomizer.this.dialogOK = dialogAccepted;
            jd.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dialogOKBtn = new javax.swing.JButton();
        dialogCancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        relativeSize = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        animation = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        rotation = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fixedWidth = new javax.swing.JCheckBox();
        flipVertical = new javax.swing.JCheckBox();
        flipHorizontal = new javax.swing.JCheckBox();
        jPreview = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codeGenerated = new javax.swing.JTextField();
        isAriaHidden = new javax.swing.JCheckBox();

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "Customizer.OK")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "Customizer.Cancel")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        relativeSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "fa-lg", "fa-2x", "fa-3x", "fa-4x", "fa-5x" }));
        relativeSize.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.relativeSize.toolTipText")); // NOI18N
        relativeSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.relativeSize.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(relativeSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(relativeSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel2.add(jPanel4);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.animation.toolTipText")); // NOI18N

        animation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "fa-spin", "fa-pulse" }));
        animation.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.animation.toolTipText")); // NOI18N
        animation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animation, 0, 170, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(animation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel2.add(jPanel5);

        rotation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "fa-rotate-90", "fa-rotate-180", "fa-rotate-270" }));
        rotation.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.rotation.toolTipText")); // NOI18N
        rotation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.rotation.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(rotation, 0, 196, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)))
                .addGap(0, 0, 0))
        );

        jPanel2.add(jPanel6);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        org.openide.awt.Mnemonics.setLocalizedText(fixedWidth, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.fixedWidth.text")); // NOI18N
        fixedWidth.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.fixedWidth.toolTipText")); // NOI18N
        fixedWidth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fixedWidthItemStateChanged(evt);
            }
        });
        jPanel3.add(fixedWidth);

        org.openide.awt.Mnemonics.setLocalizedText(flipVertical, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.flipVertical.text")); // NOI18N
        flipVertical.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.flipVertical.toolTipText")); // NOI18N
        flipVertical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                flipVerticalItemStateChanged(evt);
            }
        });
        jPanel3.add(flipVertical);

        org.openide.awt.Mnemonics.setLocalizedText(flipHorizontal, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.flipHorizontal.text")); // NOI18N
        flipHorizontal.setToolTipText(org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.flipHorizontal.toolTipText")); // NOI18N
        flipHorizontal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                flipHorizontalItemStateChanged(evt);
            }
        });
        jPanel3.add(flipHorizontal);

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "Customizer.GeneratedCode")); // NOI18N

        codeGenerated.setEditable(false);
        codeGenerated.setText("<span class=\"fa fa-address-book\" aria-hidden=\"true\"></span>"); // NOI18N
        codeGenerated.setFocusable(false);

        isAriaHidden.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(isAriaHidden, org.openide.util.NbBundle.getMessage(FontAwesomeCustomizer.class, "FontAwesomeCustomizer.isAriaHidden.text")); // NOI18N
        isAriaHidden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isAriaHiddenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(isAriaHidden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeGenerated)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isAriaHidden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codeGenerated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void isAriaHiddenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isAriaHiddenActionPerformed
        fontAwesome.setAriaHidden(isAriaHidden.isSelected());
        codeGenerated.setText(fontAwesome.generateBody());
    }//GEN-LAST:event_isAriaHiddenActionPerformed

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (!(evt.getItem().equals("Normal") || evt.getItem().equals("None"))) {
                fontAwesome.addClass(evt.getItem().toString());
            }
        } else if (evt.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            fontAwesome.removeClass(evt.getItem().toString());  // Remove the deselected item from the list
        }
        codeGenerated.setText(fontAwesome.generateBody());
    }//GEN-LAST:event_comboBoxItemStateChanged

    private void fixedWidthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fixedWidthItemStateChanged
        if (fixedWidth.isSelected()) {
            fontAwesome.addClass("fa-fw");
        } else {
            fontAwesome.removeClass("fa-fw");
        }
        codeGenerated.setText(fontAwesome.generateBody());
    }//GEN-LAST:event_fixedWidthItemStateChanged

    private void flipVerticalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_flipVerticalItemStateChanged
        if (flipVertical.isSelected()) {
            fontAwesome.addClass("fa-flip-vertical");
        } else {
            fontAwesome.removeClass("fa-flip-vertical");
        }
        codeGenerated.setText(fontAwesome.generateBody());
    }//GEN-LAST:event_flipVerticalItemStateChanged

    private void flipHorizontalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_flipHorizontalItemStateChanged
        if (flipHorizontal.isSelected()) {
            fontAwesome.addClass("fa-flip-horizontal");
        } else {
            fontAwesome.removeClass("fa-flip-horizontal");
        }
        codeGenerated.setText(fontAwesome.generateBody());
    }//GEN-LAST:event_flipHorizontalItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> animation;
    private javax.swing.JTextField codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JCheckBox fixedWidth;
    private javax.swing.JCheckBox flipHorizontal;
    private javax.swing.JCheckBox flipVertical;
    private javax.swing.JCheckBox isAriaHidden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPreview;
    private javax.swing.JComboBox<String> relativeSize;
    private javax.swing.JComboBox<String> rotation;
    // End of variables declaration//GEN-END:variables
}
