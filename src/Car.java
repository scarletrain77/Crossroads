import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Car extends JPanel {
	private Color carColor; // 车的随机颜色
	private int carX, carY; // 车的XY坐标
	private int carNumber;//
	private int mode; // 横向、纵向两种模式
	private int carCurrentNum = 0;//
	private static int CAR_HEIGHT = 3, CAR_WIDTH = 2; // 车的长宽比例
	private static int CAR_RATIO = 10; // 乘以一个比例可以方便改变长宽
	public static int CarHeight = CAR_HEIGHT * CAR_RATIO; // 车真正的高
	public static int CarWidth = CAR_WIDTH * CAR_RATIO; // 车真正的宽

	public Car(int mode) {
		this.mode = mode;
		if (this.mode == 1) {// vertical mode
			// 初始化车坐标
			carX = 0;
			carY = -(CarHeight);

			// 布局
			setLayout(null);
			setBounds(carX, carY, CarWidth, CarHeight);

		} else {
			// 初始化车坐标
			carX = -CarHeight;
			carY = 0;

			// 布局
			setLayout(null);
			setBounds(carX, carY, CarHeight, CarWidth);
		}
		// 随机色彩
		carColor = new Color(
				(new Double(Math.random() * 128).intValue() + 128),
				(new Double(Math.random() * 128).intValue() + 128), 
				(new Double(Math.random() * 128).intValue() + 128));

		// 画车
		repaint();

		carNumber = carCurrentNum;
		carCurrentNum++;
	}

	// 画车
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(carColor);
		if (this.mode == 1) {
			//横向
			g.fillRect(0, 0, CarWidth, CarHeight);
		} else {
			//纵向
			g.fillRect(0, 0, CarHeight, CarWidth);
		}
	}

	// 设定车的位置
	public void setCar(int x, int y) {
		carX = x;
		carY = y;
		setLocation(carX, carY);
	}

	public int getCarX() {
		return carX;
	}

	public int getCarY() {
		return carY;
	}
}
