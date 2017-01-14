import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RoadVertical extends JPanel implements Runnable {
	private Timer timer;
	private ArrayList<Car> cars;

	private static int CAR_STEP = Car.CarHeight * 1 / 5;
	public static int ROAD_WIDTH = (int)(Car.CarWidth*10/9);
	public static int ROAD_HEIGHT = (int)(RoadControl.panelHeight*4/5);
	public static Color ROAD_COLOR = Color.gray;

	private int roadsNum;
	private boolean isRunning;
	public static int verticalLine;

	RoadVertical(int roadX, int roadY, int roadsNum , boolean isRunning) {
		this.isRunning = isRunning;
		this.roadsNum = roadsNum;
		int ratio = 8-roadsNum;
		verticalLine = (int) (ROAD_HEIGHT * ratio/ 8);

		setLayout(null);
		setBounds(roadX, roadY, ROAD_WIDTH, ROAD_HEIGHT);
		setBackground(ROAD_COLOR);

		cars = new ArrayList<Car>();
	}

	public void run() {
		// 单个车的运行
		Car car0 = new Car(1);
		cars.add(car0);
		add(cars.get(cars.size() - 1));

		//System.out.println(verticalLine);
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

	//当最后一辆车的Y坐标为一辆车长度的倍数，并且空余为汽车的一步，随机生成车，添加
	private void addCarRunning() {
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarY() % Car.CarHeight == CAR_STEP) && r.nextBoolean() == true) {
			addCar();
		}
	}
	
	private void addCarWaiting(){
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarY() > (Car.CarHeight + CAR_STEP)) && r.nextBoolean() == true) {
			addCar();
		}
	}

	private void runCarRunning() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getY() != ROAD_HEIGHT) {
				runCar(i);
			}
		}
	}

	private void runCarWaiting() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getY() > verticalLine) {
				runCar(i);
			}
			if (i != 0) {
				//当和前一辆车的车距大于车走一步的距离（车的坐标大于车长和一步相加），就往前走一步
				//如果这辆车走一步还没超过垂直车道的十字路口线，就走一步
				if ((cars.get(i - 1).getY() - cars.get(i).getY()) > (CAR_STEP+Car.CarHeight)) {
					if((cars.get(i).getY() + CAR_STEP) < verticalLine){
						runCar(i);
					}/*else {
						if((cars.get(i).getY() + verticalLine) > CAR_STEP){
							runCar(i, verticalLine - cars.get(i).getY());
						}
					}*/
				}
			}else{
				if ((cars.get(i).getY() + CAR_STEP) < verticalLine){
					runCar(i);
				}
			}
		}
	}

	private void removeCarTimer() {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getY() == ROAD_HEIGHT) {
				removeCar(i);
			}
		}
	}

	public void addCar() {
		Car car = new Car(1);
		cars.add(car);
		add(cars.get(cars.size() - 1));

	}

	public void removeCar(int i) {
		remove(i);
		cars.remove(i);
	}

	public void runCar(int i) {
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		y += CAR_STEP;
		cars.get(i).setCar(x, y);
		//cars.get(i).setLocation(x, y);
	}
	
	//重载
	public void runCar(int i, int step){
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		y += step;
		cars.get(i).setCar(x, y);
		//cars.get(i).setLocation(x, y);
	}

	public void changeStatement() {
		isRunning = !isRunning;
	}
	
	public void setStatement(boolean s){
		isRunning = s;
	}


	public String getStatement() {
		if(isRunning == true){
			return "run";
		}else{
			return "stop";
		}
	}
}
