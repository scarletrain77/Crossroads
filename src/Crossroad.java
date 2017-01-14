import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Crossroad {
	public static void main(String[] args) {
		// TODO code application logic here
		JFrame frame = new JFrame("FinalWork14081117");

		frame.setLayout(null);
		frame.setBounds(0, 0, RoadControl.panelWidth, RoadControl.panelHeight + 50);
		frame.add(new Road());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	
}
