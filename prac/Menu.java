import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("new");
		JMenuItem openItem = new JMenuItem("open");
		menu.add(newItem);
		menu.add(openItem);
		menubar.add(menu);
		frame.setJMenuBar(menubar);

		Font bigFont = new Font("sanserif", Font.BOLD, 24);

		JTextArea display = new JTextArea(10,20);
		display.setFont(bigFont);
		display.setLineWrap(true);

		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JTextArea display2 = new JTextArea(10,20);
		display2.setFont(bigFont);
		display2.setLineWrap(true);

		JLabel label1 = new JLabel("label1");
		JLabel label2 = new JLabel("label2");
		JScrollPane qScroller2 = new JScrollPane(display2);
		qScroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel mainPanel = new JPanel();
		JButton nextButton = new JButton("Show Question");
		mainPanel.add(label1);
		mainPanel.add(qScroller);
		mainPanel.add(label2);
		mainPanel.add(qScroller2);
		mainPanel.add(nextButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640, 500);
		frame.setVisible(true);

	}

}
