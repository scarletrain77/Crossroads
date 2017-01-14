import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RoadControl extends JPanel {

	private static int PANEL_HEIGHT = 1080, PANEL_WIDTH = 1920;
	private static float PANEL_RATIO = (float) 0.5;
	public static int panelHeight = (int) (PANEL_HEIGHT * PANEL_RATIO);
	public static int panelWidth = (int) (PANEL_WIDTH * PANEL_RATIO);
	private int roadsNum;

	private ArrayList<RoadHorizontal> rh;
	private ArrayList<RoadVertical> rv;
	private Thread t1, t2;
	private Timer timer;
	private Timer textTimer;
	private TrafficLight tl1, tl2;
	private RoadInfo info;

	/*RoadControl(int x, int y, int roadsNum) {
		this.roadsNum = roadsNum;

		setLayout(null);
		setBounds(x, y, panelWidth, panelHeight);

		rh = new ArrayList<RoadHorizontal>();
		rv = new ArrayList<RoadVertical>();
		tl1 = new TrafficLight(900, 360, 25, 0);
		tl2 = new TrafficLight(730, 460, 25, 2);
		info = new RoadInfo(0, 20);

		add(tl1);
		add(tl2);
		add(info);

		int rht = 9;
		int rvt = 38;
		for (int i = 0; i < roadsNum; i++) {
			rh.add(new RoadHorizontal(panelWidth * 1 / 48, panelHeight * rht / 12, roadsNum));
			rv.add(new RoadVertical(panelWidth * rvt / 48, panelHeight * 1 / 27, roadsNum));
			add(rh.get(i));
			add(rv.get(i));
			Thread t1 = new Thread(rh.get(i));
			Thread t2 = new Thread(rv.get(i));
			t1.start();
			t2.start();
			rht--;
			rvt -= 2;
		}

		timer = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tl1.changeColor();
				tl2.changeColor();
				if (tl1.getLightStatement().equals("run")) {
					for (int i = 0; i < roadsNum; i++) {
						rh.get(i).setStatement(true);
					}
				} else if (tl1.getLightStatement().equals("prepare")) {
					for (int i = 0; i < roadsNum; i++) {
						rh.get(i).setStatement(false);
					}
				}
				if (tl2.getLightStatement().equals("run")) {
					for (int i = 0; i < roadsNum; i++) {
						rv.get(i).setStatement(true);
					}
				} else if (tl2.getLightStatement().equals("prepare")) {
					for (int i = 0; i < roadsNum; i++) {
						rv.get(i).setStatement(false);
					}
				}
			}
		});
		timer.start();
	}*/

	public RoadControl(int x, int y) {
		setLayout(null);
		setBounds(x, y, panelWidth, panelHeight);

		rh = new ArrayList<RoadHorizontal>();
		rv = new ArrayList<RoadVertical>();

		tl1 = new TrafficLight(900, 365, 25, true);
		tl2 = new TrafficLight(711, 460, 25, false);

		info = new RoadInfo(20, 0);

		add(tl1);
		add(tl2);

		add(info);

		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(RoadHorizontal.ROAD_COLOR);
		//水平道路
		if (roadsNum == 0) {
			g.fillRect(20, 300, RoadHorizontal.ROAD_WIDTH, RoadHorizontal.ROAD_HEIGHT * 6);
		} else {
			//高是最后一条车道的y坐标减去第一条车道再加上水平车道的高，再在上下各留20空隙
			int h = Math.abs(rh.get(rh.size() - 1).getLocation().y - rh.get(0).getLocation().y)
					+ RoadHorizontal.ROAD_HEIGHT + 40;
			//宽是最后一条车道的y坐标减去之前曾经空过的20
			g.fillRect(20, rh.get(rh.size() - 1).getLocation().y - 20, RoadHorizontal.ROAD_WIDTH, h);
		}
		//垂直道路
		g.setColor(RoadVertical.ROAD_COLOR);
		if (roadsNum == 0) {
			g.fillRect(640, 0, RoadVertical.ROAD_WIDTH * 6, RoadVertical.ROAD_HEIGHT + 20);
		} else {
			int w = Math.abs(rv.get(rv.size() - 1).getLocation().x - rv.get(0).getLocation().x)
					+ RoadVertical.ROAD_WIDTH + 40;
			g.fillRect(rv.get(rv.size() - 1).getLocation().x - 20, 20, w, RoadVertical.ROAD_HEIGHT);
		}
		//分道线
		g.setColor(Color.white);
		if (roadsNum == 0) {
			int y1 = 340, y2 = 400;
			for (int x0 = 40; x0 <= 600; x0 += 30) {
				g.drawLine(x0, y1, x0 + 20, y1);
				g.drawLine(x0, y2, x0 + 20, y2);
			}
			for (int x0 = 787; x0 <= 850; x0 += 30) {
				g.drawLine(x0, y1, x0 + 20, y1);
				g.drawLine(x0, y2, x0 + 20, y2);
			}
			int x1 = 670, x2 = 740;
			for (int y0 = 20; y0 <= 300; y0 += 30) {
				g.drawLine(x1, y0, x1, y0 + 20);
				g.drawLine(x2, y0, x2, y0 + 20);
			}
			for (int y0 = 430; y0 <= 450; y0 += 30) {
				g.drawLine(x1, y0, x1, y0 + 20);
				g.drawLine(x2, y0, x2, y0 + 20);
			}
		} else {
			//水平道路和垂直道路的绘制
			int y1 = (int) (rh.get(rh.size() - 1).getLocation().getY() + Car.CarHeight);
			int x1 = (int) (rv.get(rv.size() - 1).getLocation().getX() + Car.CarWidth+10);
			for (int i = 1; i < roadsNum; i++) {
				for (int x0 = 40; x0 <= RoadHorizontal.horizontalLine; x0 += 30) {
					g.drawLine(x0, y1, x0 + 20, y1);
				}
				for (int x0 = 787; x0 <= 850; x0 += 30) {
					g.drawLine(x0, y1, x0 + 20, y1);
				}
				for (int y0 = 20; y0 <= RoadVertical.verticalLine; y0 += 30) {
					g.drawLine(x1, y0, x1, y0 + 20);
				}
				for (int y0 = 430; y0 <= 450; y0 += 30) {
					g.drawLine(x1, y0, x1, y0 + 20);
				}
				y1+=Car.CarHeight + 20;
				x1+=Car.CarWidth + 20;
			}
		}
	}

	public void setRoadControl(int roadsNum) {
		this.roadsNum = roadsNum;
		//计算过后这两个参数最为适合
		int rht = 9;
		int rvt = 38;
		for (int i = 0; i < roadsNum; i++) {
			rh.add(new RoadHorizontal(panelWidth * 1 / 48, panelHeight * rht / 12, roadsNum, true));
			rv.add(new RoadVertical(panelWidth * rvt / 48, panelHeight * 1 / 27, roadsNum, false));
			add(rh.get(i));
			add(rv.get(i));
			Thread t1 = new Thread(rh.get(i));
			Thread t2 = new Thread(rv.get(i));
			t1.start();
			t2.start();
			rht--;
			rvt -= 2;
		}

		timer = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tl1.changeColor();
				tl2.changeColor();
				if (tl1.getLightStatement().equals("run")) {
					for (int i = 0; i < roadsNum; i++) {
						rh.get(i).setStatement(true);
					}
				} else if (tl1.getLightStatement().equals("prepare")) {
					for (int i = 0; i < roadsNum; i++) {
						rh.get(i).setStatement(false);
					}
				}
				if (tl2.getLightStatement().equals("run")) {
					for (int i = 0; i < roadsNum; i++) {
						rv.get(i).setStatement(true);
					}
				} else if (tl2.getLightStatement().equals("prepare")) {
					for (int i = 0; i < roadsNum; i++) {
						rv.get(i).setStatement(false);
					}
				}
			}
		});

		textTimer = new Timer(250, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setInfoHText(rh.get(rh.size() - 1).getStatement());
				info.setInfoVText(rv.get(rv.size() - 1).getStatement());
				info.setLightHText(tl1.getLightStatement());
				info.setLightVText(tl2.getLightStatement());
			}
		});

		timer.start();
		textTimer.start();

		repaint();
	}

	public TrafficLight getLight(int i) {
		if (i == 1) {
			return tl1;
		} else if (i == 2) {
			return tl2;
		}
		return tl1;
	}

	public RoadHorizontal getHorizontalRoad(int i) {
		return rh.get(i);
	}

	public RoadVertical getVerticalRoad(int i) {
		return rv.get(i);
	}

	public RoadInfo getRoadInfo() {
		return info;
	}
}
