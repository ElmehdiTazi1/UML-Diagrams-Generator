package org.mql.java.xml;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.ProjetJava;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XmiGenerator {

    public static void generateXMI(ProjetJava project, String outputPath) {
    	try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Créer le document XMI
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);

            // Élément racine (Model)
            Element modelElement = doc.createElement("uml:Model");
            modelElement.setAttribute("xmi:version", "2.0");
            modelElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
            modelElement.setAttribute("xmlns:uml", "http://www.eclipse.org/uml2/1.0.0/UML");
            modelElement.setAttribute("xmi:id", "_UwyEFBV_Edqs_vsvaW3hqA");
            modelElement.setAttribute("name", project.getName());
            doc.appendChild(modelElement);

            // Ajouter les packages
            for (Package aPackage : project.getPackages()) {
                Element packageElement = createPackageElement(doc, aPackage);
                modelElement.appendChild(packageElement);
            }

            // Transformer le document en fichier XMI
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputPath));

            transformer.transform(source, result);

            System.out.println("Le fichier XMI a été généré avec succès à l'emplacement : " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element createPackageElement(Document doc, Package aPackage) {
        Element packageElement = doc.createElement("ownedMember");
        packageElement.setAttribute("xmi:type", "uml:Package");
        packageElement.setAttribute("xmi:id", "_somePackageId"); // Remplacez cela par une logique appropriée
        packageElement.setAttribute("name", aPackage.getNom());

        // Ajouter les classes dans le package
        for (Class aClass : aPackage.getClasses()) {
            Element classElement = createClassElement(doc, aClass);
            packageElement.appendChild(classElement);
        }

        return packageElement;
    }

    private static Element createClassElement(Document doc, Class aClass) {
        Element classElement = doc.createElement("ownedMember");
        classElement.setAttribute("xmi:type", "uml:Class");
        classElement.setAttribute("xmi:id", "_someClassId"); // Remplacez cela par une logique appropriée
        classElement.setAttribute("name", aClass.getNom());

        // Ajouter les attributs, méthodes, etc.
        // ...

        return classElement;
    }

     
}