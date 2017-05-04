import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Panel {
	JFrame frame;
	JLabel label;
	int x = 0;
	int y = 0;

	public static void main(String[] args) {
		Panel panel = new Panel();
		panel.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton labelButton = new JButton("label button");
		labelButton.addActionListener(new LabelListener());
		JButton colorButton = new JButton("color change");
		colorButton.addActionListener(new ColorListener());

		label = new JLabel("I am a label");
		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.EAST, labelButton);
		frame.getContentPane().add(BorderLayout.WEST, label);
		frame.setSize(500, 500);
		frame.setVisible(true);
		for (int i = 0; i < 500; i++) {
			x++;
			y++;

			drawPanel.repaint();

			try {
				Thread.sleep(50);
			} catch (Exception ex) {}
		}
	}

	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Ouch");
		}
	}

	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	}


	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0,0,this.getWidth(), this.getHeight());

			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch( Exception e) {}
		return event;
	}
}
