package tp;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.Box.Filler;

public class Souris extends JPanel {
	public static void main(String[] args) {
		Souris srs = new Souris();
		srs.setPreferredSize(new Dimension(640,480));
		srs.setVisible(true);
		
		JFrame win = new JFrame();
		win.add(srs);
		win.pack();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setVisible(true);
	}
	
	private Vector<Point> points = new Vector<Point>();
	private Vector<Color> colors = new Vector<Color>();
	public Point mousePos = new Point();
	private MouseEvents mouseEvents = new MouseEvents();
	private Random rand = new Random();
	
	public Souris() {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		addMouseListener(mouseEvents);
		addMouseMotionListener(mouseEvents);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
		g2.clearRect(0, 0, getWidth(), getHeight());
		
		for(int p = 1; p < points.size(); p++) {
			Line2D line = new Line2D.Double(points.elementAt(p-1).getX(), points.elementAt(p-1).getY(), points.elementAt(p).getX(), points.elementAt(p).getY());
			g2.setColor(colors.elementAt(p-1));
			g2.draw(line);
		}
		
		if(points.size() > 0) {
			Line2D mouseLine = new Line2D.Double(points.lastElement().getX(), points.lastElement().getY(), mousePos.getX(), mousePos.getY());
			g2.setColor(colors.lastElement());
			g2.draw(mouseLine);
		}
	}
	
	class MouseEvents extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			points.add(new Point(e.getX(), e.getY()));
			colors.add(new Color(rand.nextInt(0xffffff)));
			repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mousePos = new Point(e.getX(), e.getY());
			if(colors.size() < points.size()) {
				colors.add(new Color(rand.nextInt(0xffffff)));
			}
			repaint();
		}
	}
}
