import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.Any;

public class Road extends JPanel {
	private JButton controlButtonH, controlButtonV;
	private JLabel xyLabel;
	private int roadsNum;
	
	private RoadControl rc;
	
	private JTextField roadsNumText;
	private JButton textButton;

	Road() {
		setLayout(null);
		setBounds(0, 0, RoadControl.panelWidth, RoadControl.panelHeight);

		roadsNumText = new JTextField("3");
		roadsNumText.setBounds(400, 10, 100, 20);
		add(roadsNumText);
		
		textButton = new JButton("play!");
		textButton.addActionListener(new ButtonListener());
		textButton.setBounds(500, 10, 100, 20);
		add(textButton);
		
		rc = new RoadControl(0, 40);
		add(rc);

		controlButtonH = new JButton("水平车道");
		controlButtonH.setBounds(20, 10, 100, 20);
		controlButtonH.addActionListener(new ButtonListener());

		controlButtonV = new JButton("垂直车道");
		controlButtonV.setBounds(140, 10, 100, 20);
		controlButtonV.addActionListener(new ButtonListener());

		add(controlButtonH);
		add(controlButtonV);

		xyLabel = new JLabel("位置");
		xyLabel.setBounds(300, 10, 500, 20);
		add(xyLabel);

		addMouseListener(new MouseChange());
		addMouseMotionListener(new MouseMotion());

	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == controlButtonH) {
				rc.getLight(1).changeColor();
				for (int i = 0; i < roadsNum; i++) {
					rc.getHorizontalRoad(i).changeStatement();
				}
			} else if (e.getSource() == controlButtonV) {
				rc.getLight(2).changeStatement();
				for (int i = 0; i < roadsNum; i++) {
					rc.getVerticalRoad(i).changeStatement();
				}

			} else {
				try {
					roadsNum = Integer.parseInt(roadsNumText.getText());
					Result(roadsNum);//检测数字是否在3-5之间
				} catch (NumberFormatException | NumberRangeException ee) {
					rc.getRoadInfo().setErrorText("Error!" + ee.getMessage());
				} //当输入数字没有问题时
				if (roadsNum > 2 && roadsNum <= 5) {
					rc.getRoadInfo().setRoadsNumText(roadsNum + " roads");
					rc.getRoadInfo().setErrorText("none");
					rc.setRoadControl(roadsNum);
					textButton.setEnabled(false);
				}
				
			}
		}

	}

	private class MouseChange implements MouseListener {
		public void mousePressed(MouseEvent e) {
			xyLabel.setText("x:" + e.getX() + ",y:" + e.getY());
		}

		public void mouseReleased(MouseEvent e) {
			xyLabel.setText("x:" + e.getX() + ",y:" + e.getY());
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			// xyLabel.setText("x:" + e.getX() + ",y:" + e.getY());
		}
	}

	private class MouseMotion extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			xyLabel.setText("x:" + e.getX() + ",y:" + e.getY());
		}

		public void mouseMoved(MouseEvent e) {
			xyLabel.setText("x:" + e.getX() + ",y:" + e.getY());
		}

	}
	
	class NumberRangeException extends Exception {

        NumberRangeException(String msg) {
            super(msg);
        }
    }

    public void Result(int inputNums) throws NumberRangeException {
        if ((inputNums > 5) || (inputNums < 3)) {
            NumberRangeException ee
                    = new NumberRangeException("number must between 3-5");
            throw ee;
        }
    }

}
