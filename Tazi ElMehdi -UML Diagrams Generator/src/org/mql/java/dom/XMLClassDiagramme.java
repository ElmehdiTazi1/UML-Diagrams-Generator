package org.mql.java.dom;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.ProjetJava;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLClassDiagramme {
	 public static void generateClassDiagram(ProjetJava project, String outputPath) {
	        try {
	            // Création d'une nouvelle instance DocumentBuilderFactory
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	            // Création d'un nouveau document XML
	            Document doc = docBuilder.newDocument();

	            // Élément racine du diagramme de classe
	            Element classDiagramElement = doc.createElement("classDiagram");
	            doc.appendChild(classDiagramElement);

	            for (Package aPackage : project.getPackages()) {
	                for (Class aClass : aPackage.getClasses()) {
	                    // Élément pour chaque classe
	                    Element classElement = doc.createElement("class");
	                    classDiagramElement.appendChild(classElement);

	                    // Attribut name de la classe
	                    classElement.setAttribute("name", aClass.getNom());

	                    // Relations avec d'autres classes
	                    List<Class> relatedClasses = aClass.getRelatedClasses();
	                    for (Class relatedClass : relatedClasses) {
	                        Element relationElement = doc.createElement("relation");
	                        relationElement.setAttribute("type", "Association");
	                        relationElement.setAttribute("target", relatedClass.getNom());
	                        classElement.appendChild(relationElement);
	                    }

	                    // Superclasse (relation de généralisation)
	                    if (aClass.getSupClass() != null) {
	                        Element generalizationElement = doc.createElement("generalization");
	                        generalizationElement.setAttribute("target", aClass.getSupClass().getNom());
	                        classElement.appendChild(generalizationElement);
	                    }
	                }
	            }

	            // Écriture du contenu du document dans un fichier XML
	            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath))) {
	                TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                Transformer transformer = transformerFactory.newTransformer();
	                transformer.transform(new DOMSource(doc), new StreamResult(fileOutputStream));
	                System.out.println("Class diagram generated successfully at: " + outputPath);
	            } catch (Exception e) {
	                System.err.println("Error transforming document to file: " + e.getMessage());
	            }

	        } catch (ParserConfigurationException e) {
	            System.err.println("Error creating DocumentBuilder: " + e.getMessage());
	        }
	    }

}
