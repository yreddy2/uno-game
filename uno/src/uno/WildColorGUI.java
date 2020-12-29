/*This class allows a player to select the color if a wild is played.*/
package uno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class WildColorGUI implements ActionListener {
	
	/*The four color buttons as well as the are initialized*/
	private JButton blueButton = new JButton("Blue");
	
	private JButton greenButton = new JButton("Green");
	
	private JButton redButton = new JButton("Red");
	
	private JButton yellowButton = new JButton("Yellow");
	
	/*The color chosen is stored here*/
	private char color;
	
	/*Keeps track of if the player drew a wild*/
	boolean draw;
	
	GameGUI gameGUI;
	
	Card card;
	
	public JFrame wildColorFrame = new JFrame();
	
	
	/*The interface is populated with the four buttons*/
	public WildColorGUI(GameGUI game, boolean onDraw, Card selectedCard) {
		JPanel wildPanel = new JPanel();
		gameGUI = game;
		draw = onDraw;
		card = selectedCard;
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		redButton.addActionListener(this);
		yellowButton.addActionListener(this);
		wildPanel.add(blueButton);
		wildPanel.add(greenButton);
		wildPanel.add(redButton);
		wildPanel.add(yellowButton);
		wildColorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wildColorFrame.setSize(300, 200);
        wildColorFrame.setVisible(true);
        wildColorFrame.add(wildPanel);
	} 
	
	/*Depending on the button selected the color is changed in the game*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == blueButton) {
			color = Card.BLUE;
		} else if(e.getSource() == greenButton) {
			color = Card.GREEN;
		} else if(e.getSource() == redButton) {
			color = Card.RED;
		} else if(e.getSource() == yellowButton) {
			color = Card.YELLOW;
		}
		wildColorFrame.dispose();
		if(draw) {
			gameGUI.getGameServer().drawOnTurn(gameGUI.getGameServer().getNextPlayer(), color);
			new WaitGUI(gameGUI);
		} else {
			if(gameGUI.getGameServer().playCard(card, gameGUI.getGameServer().getNextPlayer(), color)) {
				new WaitGUI(gameGUI);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Card! Penalty of One Card!");
				gameGUI.showPlayer();
			}
		}
	}
}
