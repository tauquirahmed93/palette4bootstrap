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

public class GlyphiconCustomizer extends javax.swing.JPanel {

    private boolean dialogOK = false;
    private JDialog jd;
    private WebEngine webEngine;
    private final JFXPanel fxPanel;
    private final Glyphicon glyphicon;

    /**
     * Creates new form GlyphiconsCustomizer
     */
    public GlyphiconCustomizer(Glyphicon glyphicon) {
        this.glyphicon = glyphicon;
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
                + " " + NbBundle.getMessage(getClass(), "MEDIA.GLYPHICONS.NAME"));
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jd.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        jd.add(this);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
        jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                /* Reset every parameter of glyphicon object so that old values
                   does not appear on re-opening the dialog */
                glyphicon.setGlyphName("asterisk");
                glyphicon.setAriaHidden(true);
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
                webEngine.load(getClass().getResource("resources/glyphiconsSelector.html").toExternalForm());
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
     * the webpage loaded in the javaFX webview to GlyphiconCustomizer.java
     */
    @SuppressWarnings("PublicInnerClass")
    public class JSInterface {
        public void setSelection(String selection) {
            glyphicon.setGlyphName(selection);
            codeGenerated.setText(glyphicon.generateBody());
        }
        public void closeDialog(boolean dialogAccepted){
            GlyphiconCustomizer.this.dialogOK = dialogAccepted;
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
        jPreview = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codeGenerated = new javax.swing.JTextField();
        isAriaHidden = new javax.swing.JCheckBox();

        jPanel1.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(dialogOKBtn, org.openide.util.NbBundle.getMessage(GlyphiconCustomizer.class, "Customizer.OK")); // NOI18N
        dialogOKBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogOKBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogOKBtn, java.awt.BorderLayout.WEST);

        org.openide.awt.Mnemonics.setLocalizedText(dialogCancelBtn, org.openide.util.NbBundle.getMessage(GlyphiconCustomizer.class, "Customizer.Cancel")); // NOI18N
        dialogCancelBtn.setMaximumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setMinimumSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.setPreferredSize(new java.awt.Dimension(69, 23));
        dialogCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dialogCancelBtn, java.awt.BorderLayout.EAST);

        jPreview.setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("windowBorder")));
        jPreview.setFocusable(false);
        jPreview.setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(GlyphiconCustomizer.class, "Customizer.GeneratedCode")); // NOI18N

        codeGenerated.setEditable(false);
        codeGenerated.setText("<span class=\"glyphicon glyphicon-asterisk\" aria-hidden=\"true\"></span>"); // NOI18N
        codeGenerated.setFocusable(false);

        isAriaHidden.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(isAriaHidden, org.openide.util.NbBundle.getMessage(GlyphiconCustomizer.class, "GlyphiconCustomizer.isAriaHidden.text")); // NOI18N
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 621, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(isAriaHidden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(codeGenerated))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
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
        glyphicon.setAriaHidden(isAriaHidden.isSelected());
        codeGenerated.setText(glyphicon.generateBody());
    }//GEN-LAST:event_isAriaHiddenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codeGenerated;
    private javax.swing.JButton dialogCancelBtn;
    private javax.swing.JButton dialogOKBtn;
    private javax.swing.JCheckBox isAriaHidden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPreview;
    // End of variables declaration//GEN-END:variables
}
