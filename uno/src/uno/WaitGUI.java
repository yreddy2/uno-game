package uno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WaitGUI implements ActionListener {
	
	
	GameGUI gameGUI;
	
	/*Initializing elements of the interface*/
    private JButton finishButton = new JButton("Continue");
    JFrame waitFrame = new JFrame();
    
    /*Constructor to create the waiting screen and inform who is playing next*/
    public WaitGUI(GameGUI game) {
    	gameGUI = game;
    	JPanel waitPanel = new JPanel();
    	JLabel waitLabel = new JLabel("Turn Finished! " + "Player: " + (gameGUI.getGameServer().getNextPlayer().getPlayerPlace() + 1) + " Up Next!");
    	finishButton.addActionListener(this);
    	waitPanel.add(waitLabel);
    	waitPanel.add(finishButton);
    	waitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	waitFrame.setSize(300, 200);
    	waitFrame.setVisible(true);
    	waitFrame.add(waitPanel);
    }
    
    /*Allows button to go back to the game interface*/
	@Override
	public void actionPerformed(ActionEvent e) {
		gameGUI.showPlayer();
		waitFrame.dispose();
	}
    
}
