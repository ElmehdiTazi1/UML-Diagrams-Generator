package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.dom.ClassDiagramXMLGenerator;
import org.mql.java.dom.XMLGenerator;
import org.mql.java.reflection.PackageExtractor;
import org.mql.java.uml.RelationshipDetector;



public class ProjetJava {
    private String name;
    private List<Package> packages= new Vector<>();
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	private List<String> test;
	public ProjetJava(String projectName) {
		this.name=projectName;
		test=PackageExtractor.extractPackages(name);
		for (String p : test) {
			packages.add(new Package(p));
		}
		RelationshipDetector.detectRelationships(this);
		ClassDiagramXMLGenerator.generateXML(this,"resources/xml/uml.xml");
		XMLGenerator.generateXML(this, "resources/xml/project.xml");
		}
}