import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class TimeControl extends JPanel{
	Timer timer;
	int hour = 0;
	int minute = 0;
	int second = 0;
	String time;
	JLabel timeLabel;
	JButton play;
	int TIME_WIDTH = 200, TIME_HEIGHT = 100;

	public TimeControl(int timeX, int timeY) {
		setLayout(null);
		setBounds(timeX, timeY, TIME_WIDTH, TIME_HEIGHT);
		setLocation(timeX, timeY);
		
		timeLabel = new JLabel("00:00:00");
		Font f = new Font("ºÚÌå", 1, 15);
		timeLabel.setFont(f);
		add(timeLabel);
		timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time = "";
                second++;
                if (second == 60) {
                    minute++;
                    second = 0;
                }
                if (minute == 60) {
                    hour++;
                    minute = 0;
                }
                if (hour == 24) {
                    hour = 0;
                }
                if (hour < 10) {
                    time = time + "0" + hour + ":";
                } else {
                    time = time + hour + ":";
                }
                if (minute < 10) {
                    time = time + "0" + minute + ":";
                } else {
                    time = time + minute + ":";
                }
                if (second < 10) {
                    time = time + "0" + second;
                } else {
                    time = time + second;
                }
                timeLabel.setText(time);
            }
        });
        timer.start();
    }
}

