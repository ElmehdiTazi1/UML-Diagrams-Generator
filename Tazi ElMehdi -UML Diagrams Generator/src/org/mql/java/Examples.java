package org.mql.java;

import org.mql.java.models.ProjetJava;
import org.mql.java.ui.ClassPanel;
import org.mql.java.ui.ClassDiagramPanel;
import org.mql.java.uml.RelationshipDetector;
import java.awt.Dimension;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.mql.java.dom.ClassDiagramXMLGenerator;
import org.mql.java.dom.XMLGenerator;
import org.mql.java.models.Class;
import org.mql.java.models.Package;

public class Examples {

	public Examples() {
		exp04();
	}

	void exp01() {
		String projectName = "C:/Users/Mehdi/MQL/p05-MultiThreading";
		var project = new ProjetJava(projectName);
		for (Package p : project.getPackages()) {
			System.out.println("Package => " + p.getNom());
			for (Class c : p.getClasses()) {
				System.out.println("	Class => " + c.getNom());
				for (Field f : c.getFields()) {
					System.out.println("		Field=> " + f.getName());
				}
				if (c.getSupClass() != null)
					System.out.println("		SubClass=> " + c.getSupClass().getNom());
			}

		}
	}

	void exp02() {
		String projectName = "C:/Users/Mehdi/MQL/Tazi ElMehdi - StringMapper";
		var project = new ProjetJava(projectName);
		String outputPath = "resources/xml/Tazi ElMehdi - StringMapper.xml";
		XMLGenerator.generateXML(project, outputPath);
	}

	void exp03() {
		Class myClass = new Class("C:/Users/Mehdi/MQL/Tazi ElMehdi - StringMapper/bin/org/mql/java/model.Person");
		JFrame frame = new JFrame("Class Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ClassPanel(myClass));
		frame.pack();
		frame.setVisible(true);

	}

	void exp04() {
		ProjetJava project = new ProjetJava("C:/Users/Mehdi/MQL/P05-MultiThreading");
		RelationshipDetector.detectRelationships(project);
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClassDiagramPanel projectPanel = new ClassDiagramPanel(project);
		frame.getContentPane().add(new JScrollPane(projectPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	void exp05() {
		ProjetJava project = new ProjetJava("C:/Users/Mehdi/MQL/Tazi ElMehdi - StringMapper");
		RelationshipDetector.detectRelationships(project);
		String outputPath = "resources/xml/Tazi ElMehdi - StringMapper.xml";
		XMLGenerator.generateXML(project, outputPath);
		outputPath = "resources/xml/class diagrame.xml";

	}

	void exp06() {
		ProjetJava project = new ProjetJava("C:/Users/Mehdi/MQL/Tazi ElMehdi - StringMapper");
		RelationshipDetector.detectRelationships(project);
		ClassDiagramXMLGenerator.generateXML(project, "resources/xml/Uml.xml");
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClassDiagramPanel projectPanel = new ClassDiagramPanel(project);
		frame.getContentPane().add(new JScrollPane(projectPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Examples();

	}

}
