import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TrafficLight extends JPanel {
	private Color lightColor[];//����ɫ������
	private Color color;//�Ƶ�ǰ��ɫ
	private int lightX, lightY, lightR;//�Ƶ�λ�úͰ뾶
	private int nowColor, timePeriod;//�Ƶ�ǰɫ���������е�λ��

	TrafficLight(int x, int y, int r, boolean b) {
		lightColor = new Color[] { Color.green, Color.yellow, Color.red, Color.red };
		lightX = x;
		lightY = y;
		lightR = r;
		
		//�ò���ֵ���õ�ǰ��ɫ
		int c;
		if(b == true){
			c = 0;
		}else{
			c = 2;
		}
		nowColor = c;

		setLayout(null);
		setLocation(lightX, lightY);
		setBounds(lightX, lightY, lightR, lightR);

		color = lightColor[nowColor];
		timePeriod = 1000;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillOval(0, 0, lightR, lightR);
	}

	//�õ���ǰ�Ƶ�״̬
	public String getLightStatement() {
		if (color == Color.green) {
			return "run";
		} else if (color == Color.yellow) {
			return "prepare";
		} else {
			return "stop";
		}
	}

	//ѭ���л���һ����ɫ
	public void changeColor() {
		nowColor++;
		if (nowColor == 4) {
			nowColor = 0;
		}
		color = lightColor[nowColor];
		repaint();
	}

	//�ı�״̬���ƵƱ��̵ƣ��̵Ʊ�Ƶƣ���Ʊ��̵�
	public void changeStatement() {
		if (nowColor == 0) {
			nowColor = 1;
		} else if (nowColor == 1) {
			nowColor = 0;
		} else if (nowColor == 2 || nowColor == 3) {
			nowColor = 0;
		}
		color = lightColor[nowColor];
		repaint();
	}

}
