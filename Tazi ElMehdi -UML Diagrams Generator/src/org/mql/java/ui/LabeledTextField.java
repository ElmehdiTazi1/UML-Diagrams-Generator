package org.mql.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextField extends JPanel {
	private static final long serialVersionUID = 1L;
    private JLabel l1;
    private JTextField t1;
	public LabeledTextField(String label, int size) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		if(!label.contains(":")) label +=" : ";
		l1 =new JLabel(label);
		t1 = new JTextField(size);
		add(l1);
		add(t1);
		
	}
	public JLabel getL1() {
		return l1;
	}
	public void setL1(JLabel l1) {
		this.l1 = l1;
	}
	public JTextField getT1() {
		return t1;
	}
	public void setT1(JTextField t1) {
		this.t1 = t1;
	}
	public String getText() {
		return this.t1.getText();
	}
	public LabeledTextField(String label, int size,int labelsize) {
		this(label,size);
		l1= (JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelsize,l1.getPreferredSize().height));
	}


}