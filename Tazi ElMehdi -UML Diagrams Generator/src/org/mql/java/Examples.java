package org.mql.java;

import org.mql.java.models.ProjetJava;
import org.mql.java.ui.ClassPanel;
import org.mql.java.ui.MainJframe;
import org.mql.java.ui.PackageDiagramPanel;
import org.mql.java.ui.ClassDiagramPanel;
import org.mql.java.uml.RelationshipDetector;
import org.mql.java.xml.ClassDiagramXMLGenerator;
import org.mql.java.xml.SaxParser;
import org.mql.java.xml.XMLGenerator;
import org.mql.java.xml.XMLParser;
import org.mql.java.xml.XmiGenerator;

import java.awt.Dimension;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.models.Class;
import org.mql.java.models.Package;

public class Examples {

	public Examples() {
		exp06();
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
					System.out.println("		SubClass=> " + c.getSupClass());
			}

		}
	}

	void exp02() {
		ProjetJava project = new ProjetJava("C:/Users/Mehdi/MQL/P05-MultiThreading");
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClassDiagramPanel projectPanel = new ClassDiagramPanel(project);
		frame.getContentPane().add(new JScrollPane(projectPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	void exp03() {
        XMLParser.parse("resources/xml/UML.XML");
	}
	void exp04() {
		ProjetJava project = new ProjetJava("C:/Users/Mehdi/MQL/P05-MultiThreading");
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PackageDiagramPanel packageDiagramPanel = new PackageDiagramPanel(project);
	    frame.add(packageDiagramPanel);
		frame.getContentPane().add(new JScrollPane(packageDiagramPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	void exp05() {
		SaxParser parser = new SaxParser();
		ProjetJava project = parser.parse("resources/xml/project.xml");
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClassDiagramPanel projectPanel = new ClassDiagramPanel(project);
		frame.getContentPane().add(new JScrollPane(projectPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	void exp06() {
		new MainJframe();
	}

	public static void main(String[] args) {
		new Examples();

	}

}
