/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class Glyphicon implements ActiveEditorDrop {

    private String glyphName = "asterisk";
    private boolean ariaHidden = true;

    public Glyphicon() {
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<span class=\"glyphicon glyphicon-").append(glyphName).append("\"");
        if (ariaHidden) {
            code.append(" aria-hidden=\"true\"");
        }
        code.append("></span>");
        return code.toString();
    }

    public void setGlyphName(String glyphName) {
        this.glyphName = glyphName;
    }

    public void setAriaHidden(boolean ariaHidden) {
        this.ariaHidden = ariaHidden;
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        GlyphiconCustomizer c = new GlyphiconCustomizer(this);
        boolean accept = c.showDialog();
        if (accept) {
            String body = generateBody();
            try {
                bsPaletteUtilities.insert(body, targetComponent);
            } catch (BadLocationException ble) {
                accept = false;
            }
        }
        return accept;
    }

}
