package org.mql.java.ui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.mql.java.Util;
import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.ProjetJava;

public class ClassDiagramPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProjetJava project;
	private List<ClassPanel> cp;
	private Map<String, Point> classPosition;
	private int screenWidth;
	private int panelWidth;
	private int panelHeight;
	private int margin = 60;

	public ClassDiagramPanel(ProjetJava project) {
		this.project = project;
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		cp = new Vector<>();
		classPosition = new HashMap<>();
		initComponents();
	}

	private void initComponents() {
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		panelWidth = 150;
		panelHeight = 300;
		int numColumns = screenWidth / (panelWidth + 2 * margin);
		numColumns = Math.max(1, numColumns);
		setLayout(new GridLayout(0, numColumns, margin * 2, margin * 2));
		for (Package aPackage : project.getPackages()) {
			for (Class aClass : aPackage.getClasses()) {
				ClassPanel classPanel = new ClassPanel(aClass);
				cp.add(classPanel);
				classPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
				addComponentListener(new ComponentAdapter() {
					int i, j = 0;

					@Override
					public void componentResized(ComponentEvent e) {
						i = 0;
						for (Component component : getComponents()) {
							if (component instanceof ClassPanel) {
								Point panelLocation = component.getLocation();
								if (j >= cp.size())
									MapAdd(cp.get(i).getMyClass().getNom(), panelLocation);
								i++;
								j++;
								if (j == cp.size() * 2)
									repaint();
							}
						}
					}
				});
				add(classPanel);
			}
		}
	}

	private void MapAdd(String c, Point p) {
		classPosition.putIfAbsent(c, p);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawRelations(g);
	}

	private void drawRelations(Graphics g) {
		Point endPoint;
		for (Package aPackage : project.getPackages()) {
			for (Class aClass : aPackage.getClasses()) {
				List<Class> relatedClasses = aClass.getRelatedClasses();
				List<Class> interfaces = aClass.getInterfaces();
				Point startPoint = classPosition.get(aClass.getNom());
				if (startPoint != null && relatedClasses != null) {
					Class supClass = aClass.getSupClass();
					if (supClass != null) {
						endPoint = classPosition.get(aClass.getSupClass().getNom());
						if (endPoint != null)
							drawArrow(g,"extends", startPoint, endPoint, true);
					}
					endPoint = null;
					for (Class relatedClass : relatedClasses) {
						endPoint = classPosition.get(relatedClass.getNom());
						if (endPoint != null) {
							drawArrow(g,"", startPoint, endPoint, false);
						}
					}
					for (Class f : interfaces) {
						endPoint = classPosition.get(f.getNom());
						if (endPoint != null) {
							drawArrow(g, "implements",startPoint, endPoint, true);
						}
					}
				}
			}
		}
	}

	private void drawArrow(Graphics g,String s, Point start, Point end, Boolean drap) {
	    Graphics2D g2d = (Graphics2D) g;
	    int startX, startY, endX, endY;
	    if (Util.isStartBeforeEnd(start, end)) {
	        startX = start.x + panelWidth + margin;
	        startY = start.y;
	        endX = end.x;
	        endY = end.y + panelHeight / 2;
	    } else {
	        startX = start.x;
	        startY = start.y;
	        endX = end.x + panelWidth + margin;
	        endY = end.y + panelHeight / 2;
	    }
	    g2d.setColor(Color.BLACK);
	    g2d.setStroke(new BasicStroke(2));
	    AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	    g2d.setComposite(alphaComposite);
	    g2d.drawLine(startX, startY, endX, endY);
	    int arrowLength = 10;
	    double angle = Math.atan2(endY - startY, endX - startX);
	    double arrowAngle1 = angle - Math.toRadians(45);
	    double arrowAngle2 = angle + Math.toRadians(45);
	    int arrowX1 = endX - (int) (arrowLength * Math.cos(arrowAngle1));
	    int arrowY1 = endY - (int) (arrowLength * Math.sin(arrowAngle1));
	    int arrowX2 = endX - (int) (arrowLength * Math.cos(arrowAngle2));
	    int arrowY2 = endY - (int) (arrowLength * Math.sin(arrowAngle2));
	    g2d.drawLine(endX, endY, arrowX1, arrowY1);
	    g2d.drawLine(endX, endY, arrowX2, arrowY2);
	    if (drap) 
	        g2d.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
	    g2d.drawString(s, (startX+endX)/2, (startY+endY)/2);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

}
