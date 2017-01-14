import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Car extends JPanel {
	private Color carColor; // ���������ɫ
	private int carX, carY; // ����XY����
	private int carNumber;//
	private int mode; // ������������ģʽ
	private int carCurrentNum = 0;//
	private static int CAR_HEIGHT = 3, CAR_WIDTH = 2; // ���ĳ������
	private static int CAR_RATIO = 10; // ����һ���������Է���ı䳤��
	public static int CarHeight = CAR_HEIGHT * CAR_RATIO; // �������ĸ�
	public static int CarWidth = CAR_WIDTH * CAR_RATIO; // �������Ŀ�

	public Car(int mode) {
		this.mode = mode;
		if (this.mode == 1) {// vertical mode
			// ��ʼ��������
			carX = 0;
			carY = -(CarHeight);

			// ����
			setLayout(null);
			setBounds(carX, carY, CarWidth, CarHeight);

		} else {
			// ��ʼ��������
			carX = -CarHeight;
			carY = 0;

			// ����
			setLayout(null);
			setBounds(carX, carY, CarHeight, CarWidth);
		}
		// ���ɫ��
		carColor = new Color(
				(new Double(Math.random() * 128).intValue() + 128),
				(new Double(Math.random() * 128).intValue() + 128), 
				(new Double(Math.random() * 128).intValue() + 128));

		// ����
		repaint();

		carNumber = carCurrentNum;
		carCurrentNum++;
	}

	// ����
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(carColor);
		if (this.mode == 1) {
			//����
			g.fillRect(0, 0, CarWidth, CarHeight);
		} else {
			//����
			g.fillRect(0, 0, CarHeight, CarWidth);
		}
	}

	// �趨����λ��
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
