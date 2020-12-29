/*This class displays the winner of the game*/
package uno;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinnerGUI {
	
	/*Displays the winner of the game*/
	public WinnerGUI(int winner) {
		
		JLabel winnerLabel = new JLabel("Player " + (winner + 1) + " Wins!");
		JPanel winnerPanel = new JPanel();
		JFrame winnerFrame = new JFrame();
		winnerPanel.add(winnerLabel);
		winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winnerFrame.setSize(300, 200);
        winnerFrame.setVisible(true);
        winnerFrame.add(winnerPanel);
	}
}
