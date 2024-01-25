package org.mql.java.ui;

import javax.swing.*;

import org.mql.java.models.ProjetJava;
import org.mql.java.xml.ClassDiagramXMLGenerator;
import org.mql.java.xml.XMLGenerator;

import java.awt.Color;
import java.awt.Dimension;


public class MainJframe extends JFrame {
	private static final long serialVersionUID = 1L;
	private LabeledTextField projectNameField;
    private LabeledTextField xmlFilePathField;
    private String projectName="";
    private String pathXml="";
    private JLabel erreur;
    private ProjetJava project;
    public MainJframe() {
        super("Projet Java Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        projectNameField = new LabeledTextField("Nom du Projet", 20, 150);
        xmlFilePathField = new LabeledTextField("Chemin du Fichier XML", 20, 150);
        JButton classDiagramButton = new JButton("Générer Diagramme de Classe");
        JButton packageDiagramButton = new JButton("Générer Diagramme de Package");
        classDiagramButton.addActionListener( e ->generateClassDiagram());
        packageDiagramButton.addActionListener( e->generatePackageDiagram());
        erreur = new JLabel();
        erreur.setForeground(Color.RED);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(projectNameField);
        mainPanel.add(xmlFilePathField);
        mainPanel.add(erreur);
        mainPanel.add(classDiagramButton);
        mainPanel.add(packageDiagramButton);
        add(mainPanel);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void generateClassDiagram() {
    	projectName = getProjectName();
        if(!"".equals(projectName)) {
        project = new ProjetJava(projectName);
        generateXml();
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ClassDiagramPanel projectPanel = new ClassDiagramPanel(project);
		frame.getContentPane().add(new JScrollPane(projectPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
        }else {
        	erreur.setText("Erreur : le nom du Project Vide");

        }
        
    }

    private void generatePackageDiagram() {
        projectName = getProjectName();
        if(!"".equals(projectName)) {
        project = new ProjetJava(projectName);
        generateXml();
		JFrame frame = new JFrame("Project Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PackageDiagramPanel packageDiagramPanel = new PackageDiagramPanel(project);
	    frame.add(packageDiagramPanel);
		frame.getContentPane().add(new JScrollPane(packageDiagramPanel));
		frame.setSize(new Dimension(800, 600));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
        }else {
        	erreur.setText("Erreur : le nom du Project Vide");
        }
    }
    private String getPath() {
    	return xmlFilePathField.getText();
    }
    private String getProjectName() {
    	return projectNameField.getText();
    }
    private void generateXml() {
    	pathXml= getPath();
    	if(!"".equals(pathXml)) {
    		ClassDiagramXMLGenerator.generateXML(project,pathXml+"/uml.xml");
    		XMLGenerator.generateXML(project, pathXml+"/project.xml");
    	}
    }
    public static void main(String[] args) {
		new MainJframe();
	}


}
