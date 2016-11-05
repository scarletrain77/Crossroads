import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Car extends JPanel {
	private Color carColor;
	private int carX, carY;
	public static int CAR_HEIGHT = 30, CAR_WIDTH = 20;

	public Car() {
		//setPreferredSize(new Dimension(CAR_WIDTH, 1000));
		setLayout(null);
		carX = 0;
		carY = -30;
		this.setLocation(carX, carY);
		this.setBounds(carX, carY, CAR_WIDTH, CAR_HEIGHT);
		carColor = new Color(
				(new Double(Math.random() * 128).intValue() + 128),
				(new Double(Math.random() * 128).intValue() + 128),
				(new Double(Math.random() * 128).intValue() + 128)
		);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(carColor);
		g.fillRect(0, 0, CAR_WIDTH, CAR_HEIGHT);
	}
	
	public void setCar(int x, int y){
		carX = x;
		carY = y;
		this.setLocation(carX, carY);
		this.setBounds(carX, carY, CAR_WIDTH, CAR_HEIGHT);
		//System.out.println("carX" + carX + "carY" + carY);
	}
	
	public int getCarX(){
		return carX;
	}
	
	public int getCarY(){
		return carY;
	}
}
