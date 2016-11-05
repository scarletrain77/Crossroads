import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Crossroad {

	public static void main(String[] args) {
		// TODO code application logic here
		JFrame frame = new JFrame("FinalWork14081117");
		frame.setSize(640, 1136);
		//frame.setLayout(null);
		//frame.setBounds(0, 0, 1920, 1080);
		frame.setLayout(new BorderLayout());
		frame.add(new RoadTest(), BorderLayout.CENTER);
		frame.add(new TimeControl(), BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.pack();

	}

}
