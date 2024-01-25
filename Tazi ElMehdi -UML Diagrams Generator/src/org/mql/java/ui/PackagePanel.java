package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.mql.java.models.Package;

public class PackagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Package packageModel;

    public PackagePanel(Package packageModel) {
        this.packageModel = packageModel;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JPanel packageNamePanel = new JPanel();
        packageNamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel packageNameLabel = new JLabel(packageModel.getNom());
        packageNamePanel.add(packageNameLabel);

        JPanel classListPanel = new JPanel();
        classListPanel.setLayout(new GridLayout(0, 1)); // Utilisation d'un GridLayout
        classListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        List<org.mql.java.models.Class> classNames = packageModel.getClasses();
        for (org.mql.java.models.Class className : classNames) {
            Panel classPanel = new Panel();
            classPanel.add(new Label(className.getClazz().getCanonicalName()));
            classListPanel.add(classPanel);
        }

        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(packageNamePanel);
        add(classListPanel);
    }
}