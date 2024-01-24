package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.reflection.PackageExtractor;
import org.mql.java.uml.RelationshipDetector;
import org.mql.java.xml.ClassDiagramXMLGenerator;
import org.mql.java.xml.XMLGenerator;



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
	public void addPackage(Package p) {
		packages.add(p);
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
	public ProjetJava() {
		
	}
}