import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TrafficLight extends JPanel {
	private Color lightColor[];//灯颜色的数组
	private Color color;//灯当前颜色
	private int lightX, lightY, lightR;//灯的位置和半径
	private int nowColor, timePeriod;//灯当前色彩在数组中的位置

	TrafficLight(int x, int y, int r, boolean b) {
		lightColor = new Color[] { Color.green, Color.yellow, Color.red, Color.red };
		lightX = x;
		lightY = y;
		lightR = r;
		
		//用布尔值设置当前颜色
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

	//得到当前灯的状态
	public String getLightStatement() {
		if (color == Color.green) {
			return "run";
		} else if (color == Color.yellow) {
			return "prepare";
		} else {
			return "stop";
		}
	}

	//循环切换下一个颜色
	public void changeColor() {
		nowColor++;
		if (nowColor == 4) {
			nowColor = 0;
		}
		color = lightColor[nowColor];
		repaint();
	}

	//改变状态，黄灯变绿灯，绿灯变黄灯，红灯变绿灯
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
