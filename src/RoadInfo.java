import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoadInfo extends JPanel {
	private JLabel infoV;
	private JLabel infoH;
	private JLabel error;
	private JLabel roadsNum;
	private JLabel lightH;
	private JLabel lightV;

	private JLabel infoVText;
	private JLabel infoHText;
	private JLabel errorText;
	private JLabel roadsNumText;
	private JLabel lightHText;
	private JLabel lightVText;

	private ArrayList<JLabel> infos;

	RoadInfo(int x, int y) {
		setLayout(null);
		setBounds(x, y, 400, 200);

		infos = new ArrayList<JLabel>();

		infoV = new JLabel("vertical car state:");
		infoVText = new JLabel("waiting");
		infoH = new JLabel("horizontal car state:");
		infoHText = new JLabel("waiting");
		error = new JLabel("Error:");
		errorText = new JLabel("none");
		roadsNum = new JLabel("Roads number:");
		roadsNumText = new JLabel("0");
		lightH = new JLabel("Horizontal light statement:");
		lightHText = new JLabel("waiting");
		lightV = new JLabel("Vertical light statement:");
		lightVText = new JLabel("waiting");

		infos.add(infoV);
		infos.add(infoVText);
		infos.add(infoH);
		infos.add(infoHText);
		infos.add(roadsNum);
		infos.add(roadsNumText);
		infos.add(lightV);
		infos.add(lightVText);
		infos.add(lightH);
		infos.add(lightHText);
		infos.add(error);
		infos.add(errorText);
		
		for (int i = 0, boundy = 0; i < infos.size()/2; i++, boundy += 20) {
			infos.get(i).setBounds(0, boundy, 300, 15);
			add(infos.get(i));
		}
		for (int i = infos.size()/2, boundy = 0; i < infos.size(); i++, boundy += 20) {
			infos.get(i).setBounds(200, boundy, 300, 15);
			add(infos.get(i));
		}
	}

	public String getLightHText() {
		return lightHText.getText();
	}

	public void setLightHText(String l) {
		lightHText.setText(l);
	}

	public String getLightVText() {
		return lightVText.getText();
	}

	public void setLightVText(String l) {
		lightVText.setText(l);;
	}

	public String getInfoVText() {
		return infoVText.getText();
	}

	public void setInfoVText(String s) {
		infoVText.setText(s);
	}

	public String getInfoHText() {
		return infoHText.getText();
	}

	public void setInfoHText(String s) {
		infoHText.setText(s);
	}

	public String getErrorText() {
		return errorText.getText();
	}

	public void setErrorText(String s) {
		errorText.setText(s);
	}

	public String getRoadsNumText() {
		return roadsNumText.getText();
	}

	public void setRoadsNumText(String s) {
		roadsNumText.setText(s);
	}

}
