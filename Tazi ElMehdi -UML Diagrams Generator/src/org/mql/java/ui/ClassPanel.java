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

    public ClassPanel(Class myClass) {
        this.myClass = myClass;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, calculateContentHeight()));

        initComponents();
    }

    private void initComponents() {
        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String titre = myClass.getNom();
        JLabel titleLabel = new JLabel(titre.split("\\.")[titre.split("\\.").length - 1]);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleContainer.add(titleLabel);

        // nom de la class
        add(titleContainer, BorderLayout.NORTH);

        // fields
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

        // Méthodes
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

    private int calculateContentHeight() {
        int totalHeight = 0;

        
        for (Field field : myClass.getFields()) {
            totalHeight += 10; 
        }

        totalHeight += 5; 

        for (Method method : myClass.getMethods()) {
            totalHeight += 10;
        }

        return totalHeight;
    }


}
