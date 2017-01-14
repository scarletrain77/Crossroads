import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * ˮƽ��·����������
 * 
 * @author WangChen
 *
 */
public class RoadHorizontal extends JPanel implements Runnable {
	private Timer timer;
	private ArrayList<Car> cars;// ������
	private static int CAR_STEP = Car.CarHeight * 1 / 5;// ����һ���ǳ���1/5
	public static int ROAD_WIDTH = (int) (RoadControl.panelWidth * 9 / 10);// ·�Ŀ��Ϊ������·���Ƶ�9/10
	public static int ROAD_HEIGHT = (int) (Car.CarWidth * 10 / 9);// ·�ĸ߶�Ϊ����ȵ�10/9���ȳ���΢��һ��

	public static Color ROAD_COLOR = Color.gray;
	
	private boolean isRunning;// �Ƿ����̵ƿ����˶�
	private int roadsNum;// ��·������
	public static int horizontalLine;// ʮ��·��λ�ã����������߾������·

	
	RoadHorizontal(int roadX, int roadY, int roadsNum, boolean isRunning) {
		// ��ʼ��
		this.roadsNum = roadsNum;
		this.isRunning = isRunning;
		int ratio = 48 - roadsNum * 13 / 3;
		horizontalLine = (int) (ROAD_WIDTH * ratio / 48);
		cars = new ArrayList<Car>();
		// ����һ���ļ��㣬����·����Ϊ3��4��5��ʱ�������ʽ��Ϊ����

		// ����
		setLayout(null);
		setBounds(roadX, roadY, ROAD_WIDTH, ROAD_HEIGHT);
		setBackground(ROAD_COLOR);
	}

	public void run() {
		// �����㷨���ƣ���������һ����ʻ�룬����2Ϊ����ˮƽ������ӵ�����JPanel
		Car car0 = new Car(2);
		cars.add(car0);
		add(cars.get(cars.size() - 1));

		// ÿ��һ��ʱ����Ƴ�����ӡ��˶����Ƴ�
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

	// ���Թ���·ʱ�����һ������Y����Ϊһ�������ȵı��������ҿ���Ϊ������һ��������ж��Ƿ����ɳ������
	// && cars.size() <= 10
	private void addCarRunning() {
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarX() % Car.CarHeight == CAR_STEP) && r.nextBoolean() == true) {
			addCar();
		}
	}

	// �ȴ����̵Ƶ�ʱ�򣬵����һ������X�������һ�����ĳ��Ⱥ�һ���ľ����ʱ��һ������������С���룩�����漴�ж��Ƿ����ɳ������
	// && cars.size() <= 10
	private void addCarWaiting() {
		Random r = new Random();
		if ((cars.get(cars.size() - 1).getCarX() > (Car.CarHeight + CAR_STEP)) && r.nextBoolean() == true) {
			addCar();
		}
	}

	// ���Թ���·ʱ����X���겻��·�Ŀ��ʱ�������߶���Ҳ���ǵ�������ˮƽ��·�����Ҷ�ʱ��ֹͣ�˶���
	private void runCarRunning() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getX() != ROAD_WIDTH) {
				runCar(i);
			}
		}
	}

	// Ϊ�Ƶ�ʱ�������ʱ�Ѿ�Խ������·�ߣ�����Լ����˶�����·�����Ҷˣ����û��Խ����·�ߵ�ʱ�򣬾��жϺ�ǰһ�����ľ���
	// ������Ҫ�жϴ�ʱΪ�ڼ�����������ǵ�һ������û��ǰһ��������ô��ֱ���ߵ���·�߾ͺ�
	private void runCarWaiting() {
		for (int i = 0; i < cars.size(); i++) {
			// System.out.println("i:" + i + "X:" + cars.get(i).getX() + "Y:" +
			// cars.get(i).getY());
			if (cars.get(i).getX() > horizontalLine) {
				runCar(i);
			}
			if (i != 0) {
				// ����ǰһ�����ĳ�����ڳ���һ���ľ��루����������ڳ�����һ����ӣ�������ǰ��һ��
				// �����������һ����û������ֱ������ʮ��·���ߣ�����һ��
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

	//�������·���Ҷ�ʱ�������Ͳ����˶��ˣ����Դ�ʱ������Ϊ������X����Ϊ��·��ȵĳ����ǿ��Ƴ���
	private void removeCarTimer() {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getX() == ROAD_WIDTH) {
				removeCar(i);
			}
		}
	}

	//���ˮƽ���������顢Panel��
	public void addCar() {
		Car car = new Car(2);
		cars.add(car);
		add(cars.get(cars.size() - 1));
	}

	//���ݱ���Ƴ�����
	public void removeCar(int i) {
		remove(i);
		cars.remove(i);
	}

	//���ݱ���ƶ�����
	public void runCar(int i) {
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		x += CAR_STEP;
		cars.get(i).setCar(x, y);
	}

	// ���أ������趨һ���߶���
	public void runCar(int i, int step) {
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		x += step;
		cars.get(i).setCar(x, y);
	}

	//�л�����״̬���ǿ����߻��ǲ�������
	public void changeStatement() {
		isRunning = !isRunning;
	}

	//���ó���״̬
	public void setStatement(boolean s) {
		isRunning = s;
	}

	//ȡ�ó���״̬
	public String getStatement() {
		if(isRunning == true){
			return "run";
		}else{
			return "stop";
		}
	}
	
	
}
