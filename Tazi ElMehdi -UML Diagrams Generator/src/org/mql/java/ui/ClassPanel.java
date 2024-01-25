package org.mql.java.ui;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.mql.java.models.Class;

public class ClassPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Class myClass;

	public Class getMyClass() {
		return myClass;
	}

	public void setMyClass(Class myClass) {
		this.myClass = myClass;
	}

	public ClassPanel(Class myClass) {
		this.myClass = myClass;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());

		initComponents();
	}

	@Override
	public String toString() {
		return "ClassPanel [myClass=" + myClass.getNom() + "]";
	}

	private void initComponents() {
		JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
		String titre =myClass.getIsInterface()?"<<interface>>":"" ;
		titre += myClass.getClazz().getSimpleName();
		JLabel titleLabel = new JLabel(titre);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		titleContainer.add(titleLabel);

		add(titleContainer, BorderLayout.NORTH);

		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

		for (Field field : myClass.getFields()) {
			String s = "+";
			if (field.accessFlags().toString().equals("[PRIVATE]")) {
				s = "-";
			}
			JLabel fieldLabel = new JLabel(s + " " + field.getName() + " : " + field.getType().getSimpleName());
			fieldsPanel.add(fieldLabel);
		}

		add(new JScrollPane(fieldsPanel), BorderLayout.CENTER);

		JPanel methodsPanel = new JPanel();
		methodsPanel.setLayout(new BoxLayout(methodsPanel, BoxLayout.Y_AXIS));

		for (Method method : myClass.getMethods()) {
			String s = "+";
			if (method.accessFlags().toString().equals("[PRIVATE]")) {
				s = "-";
			}
			s += method.getName() + "(";

			Parameter[] parameters = method.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				s += parameter.getType().getSimpleName() + " " + parameter.getName();
				if (i < parameters.length - 1) {
					s += ", ";
				}
			}
			s += "):" + method.getReturnType().getSimpleName();
			JLabel methodLabel = new JLabel(s);
			methodsPanel.add(methodLabel);
		}

		add(new JScrollPane(methodsPanel), BorderLayout.SOUTH);
	}
}
