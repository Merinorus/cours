package tp4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class TraceZone extends JPanel {
	public TraceZone() {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}
	
	public void draw() {
		repaint();
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(5));
		g2.clearRect(0, 0, getWidth(), getHeight());
	}
}
