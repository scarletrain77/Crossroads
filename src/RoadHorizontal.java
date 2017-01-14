import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 水平道路，车横向走
 * 
 * @author WangChen
 *
 */
public class RoadHorizontal extends JPanel implements Runnable {
	private Timer timer;
	private ArrayList<Car> cars;// 车数组
	private static int CAR_STEP = Car.CarHeight * 1 / 5;// 车的一步是车长1/5
	public static int ROAD_WIDTH = (int) (RoadControl.panelWidth * 9 / 10);// 路的宽度为整个道路控制的9/10
	public static int ROAD_HEIGHT = (int) (Car.CarWidth * 10 / 9);// 路的高度为车宽度的10/9，比车稍微大一点

	public static Color ROAD_COLOR = Color.gray;
	
	private boolean isRunning;// 是否是绿灯可以运动
	private int roadsNum;// 道路的条数
	public static int horizontalLine;// 十字路口位置，超过这条线就算过马路

	
	RoadHorizontal(int roadX, int roadY, int roadsNum, boolean isRunning) {
		// 初始化
		this.roadsNum = roadsNum;
		this.isRunning = isRunning;
		int ratio = 48 - roadsNum * 13 / 3;
		horizontalLine = (int) (ROAD_WIDTH * ratio / 48);
		cars = new ArrayList<Car>();
		// 经过一定的计算，当道路条数为3、4、5的时候这个公式最为合适

		// 布局
		setLayout(null);
		setBounds(roadX, roadY, ROAD_WIDTH, ROAD_HEIGHT);
		setBackground(ROAD_COLOR);
	}

	public void run() {
		// 由于算法限制，必须先有一辆车驶入，数字2为绘制水平车，添加到整个JPanel
		Car car0 = new Car(2);
		cars.add(car0);
		add(cars.get(cars.size() - 1));

		// 每隔一定时间控制车的添加、运动和移除
		timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cars.size() > 0) {
					if (isRunning == true) {
						addCarRunning();
						runCarRunning();
					} else if (isRunning == false) {
						addCarWaiting();
						runCarWaiting();
					}
					removeCarTimer();
				}
			}
		});
		timer.start();
	}

	// 可以过马路时当最后一辆车的Y坐标为一辆车长度的倍数，并且空余为汽车的一步，随机判断是否生成车，添加
	// && cars.size() <= 10
	private void addCarRunning() {
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarX() % Car.CarHeight == CAR_STEP) && r.nextBoolean() == true) {
			addCar();
		}
	}

	// 等待红绿灯的时候，当最后一辆车的X坐标大于一辆车的长度和一步的距离的时候（一步是两辆车最小距离），且随即判断是否生成车，添加
	// && cars.size() <= 10
	private void addCarWaiting() {
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarX() > (Car.CarHeight + CAR_STEP)) && r.nextBoolean() == true) {
			addCar();
		}
	}

	// 可以过马路时，当X坐标不是路的宽度时，可以走动（也就是当它到达水平道路的最右端时，停止运动）
	private void runCarRunning() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getX() != ROAD_WIDTH) {
				runCar(i);
			}
		}
	}

	// 为黄灯时，如果此时已经越过了马路线，则可以继续运动到道路的最右端，如果没有越过马路线的时候，就判断和前一个车的距离
	// 所以需要判断此时为第几辆车，如果是第一辆车就没有前一辆车，那么就直接走到马路线就好
	private void runCarWaiting() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getX() > horizontalLine) {
				runCar(i);
			}
			if (i != 0) {
				// 当和前一辆车的车距大于车走一步的距离（车的坐标大于车长和一步相加），就往前走一步
				// 如果这辆车走一步还没超过垂直车道的十字路口线，就走一步
				if (Math.abs(cars.get(i - 1).getX() - cars.get(i).getX()) > (CAR_STEP + Car.CarHeight)) {
					if ((cars.get(i).getX() + CAR_STEP) < horizontalLine) {
						runCar(i);
					} 
				}
			} else {
				if ((cars.get(i).getX() + CAR_STEP) < horizontalLine) {
					runCar(i);
				}
			}
		}
	}

	//当到达道路最右端时，汽车就不再运动了，所以此时可以认为，所有X坐标为道路宽度的车都是可移除的
	private void removeCarTimer() {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getX() == ROAD_WIDTH) {
				removeCar(i);
			}
		}
	}

	//添加水平车辆到数组、Panel中
	public void addCar() {
		Car car = new Car(2);
		cars.add(car);
		add(cars.get(cars.size() - 1));
	}

	//根据编号移除车辆
	public void removeCar(int i) {
		remove(i);
		cars.remove(i);
	}

	//根据编号移动车辆
	public void runCar(int i) {
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		x += CAR_STEP;
		cars.get(i).setCar(x, y);
	}

	// 重载，可以设定一步走多少
	public void runCar(int i, int step) {
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		x += step;
		cars.get(i).setCar(x, y);
	}

	//切换车道状态，是可以走还是不可以走
	public void changeStatement() {
		isRunning = !isRunning;
	}

	//设置车道状态
	public void setStatement(boolean s) {
		isRunning = s;
	}

	//取得车道状态
	public String getStatement() {
		if(isRunning == true){
			return "run";
		}else{
			return "stop";
		}
	}
	
	
}
