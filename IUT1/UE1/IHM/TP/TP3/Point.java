package tp;

public class Point {
	private int X, Y;
	
	public Point() {
		setX(0);
		setY(0);
	}
	
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public int getX() { return X; }
	public int getY() { return Y; }
	
	public void setX(int x) { X = x; }
	public void setY(int y) { Y = y; }
}
