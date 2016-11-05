import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RoadTest extends JPanel {
	Timer timer;
	ArrayList<Car> cars;
	public int CAR_STEP = 10;

	RoadTest() {
		//setPreferredSize(new Dimension(20, 1000));
		setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		cars = new ArrayList<Car>();
		
		//单个车的运行
		Car car0 = new Car();
		cars.add(car0);
		add(car0);
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("X:" + car1.getCarX() + "Y"+ car1.getCarY());
				//addCar();
				//System.out.println(cars.size());
				addCar();
				for(int i = 0; i<cars.size(); i++){
					//cars.get(i).setCar(carXs.get(i), carYs.get(i));
					//System.out.println("X:" + carXs.get(i) + "Y:"+ carYs.get(i));
					runCar(i);
					System.out.println("i:" + i + "X:" + cars.get(i).getCarX() + "Y"+ cars.get(i).getCarY());
				}
			}
		});
		timer.start();
	}
	
	public void addCar(){
		if((cars.get(cars.size() -1 ).getCarY() == CAR_STEP) && cars.size() <= 5){
			Car car = new Car();
			cars.add(car);
			add(car);
		}
	}
	
	public void removeCar(){
		
	}
	
	public void runCar(int i){
		int x = cars.get(i).getCarX();
		int y = cars.get(i).getCarY();
		y += CAR_STEP;
		cars.get(i).setCar(x, y);		
	}
}
