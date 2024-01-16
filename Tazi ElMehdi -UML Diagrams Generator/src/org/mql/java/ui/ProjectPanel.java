package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import org.mql.java.models.ProjetJava;
import org.mql.java.models.Package;
import org.mql.java.models.Class;

public class ProjectPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProjetJava project;

	public ProjectPanel(ProjetJava project) {
		this.project = project;
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		initComponents();
	}

	private void initComponents() {
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int panelWidth = 200; 
		int margin = 30; 
		int numColumns = screenWidth / (panelWidth + 2 * margin);
		numColumns = Math.max(1, numColumns); 

		setLayout(new GridLayout(0, numColumns, margin * 2, margin * 2));

		for (Package aPackage : project.getPackages()) {
			for (Class aClass : aPackage.getClasses()) {
				ClassPanel classPanel = new ClassPanel(aClass);
				classPanel.setPreferredSize(new Dimension(panelWidth, classPanel.getPreferredSize().height));
				add(classPanel);
			}
		}
	}
}