package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.mql.java.models.Package;
import org.mql.java.models.ProjetJava;
import java.util.Vector;

public class PackageDiagramPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProjetJava project;
    private List<PackagePanel> packagePanels;
    private int screenWidth;
    private int panelWidth;
    private int panelHeight;
    private int margin = 60;

    public PackageDiagramPanel(ProjetJava project) {
        this.project = project;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        packagePanels = new Vector<>();
        initComponents();
    }

    private void initComponents() {
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        panelWidth = 300;
        panelHeight = 300;
        int numColumns = screenWidth / (panelWidth + 2 * margin);
        numColumns = Math.max(1, numColumns);
        setLayout(new GridLayout(0, numColumns, margin * 2, margin * 2));
        for (Package aPackage : project.getPackages()) {
            PackagePanel packagePanel = new PackagePanel(aPackage);
            packagePanels.add(packagePanel);
            packagePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            add(packagePanel);
        }
    }

}
 