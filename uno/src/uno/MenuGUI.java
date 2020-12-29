/*This class contains the initial menu for the game. Here players can choose the number of people playing.*/
package uno;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class MenuGUI implements ActionListener {
	
	
	/*JSpinner is the element which allows the user to select the number of players.*/
    JSpinner numberSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1));
    
    JFrame menuFrame = new JFrame();

    /**
     * This is the main constructor for the class
     */
    public MenuGUI() {
    	
    	JLabel numberPlayersLabel = new JLabel("Number of Players:");
        JButton startButton = new JButton("Start Game");
        JPanel panel = new JPanel();
        /*Attaching the button to the actions*/
        startButton.addActionListener(this);
        /*Adding elements to the panel*/
        panel.add(numberPlayersLabel);
        panel.add(numberSpinner);
        panel.add(startButton);

        menuFrame.add(panel);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(300, 200);
        menuFrame.setVisible(true);
    }

    /**
     * This is to register the button to start the game.
     */
    public void actionPerformed(ActionEvent e) {
    	
    	int playersValue = (Integer) numberSpinner.getValue();
        menuFrame.dispose();
        new GameGUI(playersValue);
    }

    /**
     * The main method to start the program from the menu.
     * @param args
     */
    public static void main(String[] args) {
    	
        new MenuGUI();
    }
}
