/*This class contains the general GUI as well as the gameloop to register the actions of the user*/
package uno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameGUI extends WindowAdapter implements ActionListener, ItemListener {
	
	
	private Card selectedCard;
	
	/*This is where the gameServer is stored*/
	private GameServer gameServer;

	/*Initializing all the elements contained in the user interface*/
	private JPanel gamePanel;
	
	private JFrame gameFrame = new JFrame();
	
	private JLabel selectedCardlabel = new JLabel("selectedCardlabel");
	
	private JLabel playerLabel = new JLabel("playerLabel");
	
	private JLabel DiscardLabel = new JLabel("DiscardLabel");
	
	private JButton drawButton = new JButton("Draw Card");
	
	private JButton playButton = new JButton("Play Card");
	
	private JComboBox<Card> cardDropDown = new JComboBox<Card>();

	private JLabel wildLabel = new JLabel("wildLabel");

	/**
	 * The constructor connects the buttons to their actions as well as sets up the frame of the graphics.
	 * It also begins the game and starts the loop.
	 * @param numberOfPlayers
	 */
	public GameGUI(int numberOfPlayers) {
		
		gameServer = new GameServer(numberOfPlayers);
		
		drawButton.addActionListener(this);
		playButton.addActionListener(this);
		
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(300,200);
        gameFrame.setVisible(true);
        
        showPlayer();
	}

	/**
	 * This method gets the current players information from the server.
	 */
	public void showPlayer() {
		if(gameServer.getWinner() < 0) {
			playerLabel.setText("Player: " + (gameServer.getNextPlayer().getPlayerPlace() + 1));
			DiscardLabel.setText("Discard Pile: " + gameServer.getTopOfDiscardPile().toString());
			cardDropDown = new JComboBox<Card>();
			
			ArrayList<Card> cardList = gameServer.getNextPlayer().getPlayerHand().getCardList();
			for(int cardListIdx = 0; cardListIdx < cardList.size(); cardListIdx++) {
				cardDropDown.addItem(cardList.get(cardListIdx));
			}
			
			selectedCard = (Card) cardDropDown.getSelectedItem();
			selectedCardlabel.setText("Selected Card: " + selectedCard.toString());
			
			replacePanel();
		} else {
			new WinnerGUI(gameServer.getWinner());
		}
	}
	
	/**
	 * This function updates the graphics with the current players information.
	 */
	private void replacePanel() {
		
		cardDropDown.addItemListener(this);
		
		gamePanel = new JPanel();
		gamePanel.add(playerLabel);
		gamePanel.add(DiscardLabel);
		gamePanel.add(selectedCardlabel);
		gamePanel.add(cardDropDown);
		gamePanel.add(drawButton);
		gamePanel.add(playButton);
		
		System.out.println(gameServer.getTopOfDiscardPile().getCardColor());
		if(gameServer.getTopOfDiscardPile().getCardColor() == Card.WILD) {
			wildLabel.setText("Wild Color " + gameServer.getTopOfDiscardPile().colorToString(gameServer.getWildColor()));
			gamePanel.add(wildLabel);
		}
		
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
		gameFrame.getContentPane().removeAll();
		gameFrame.add(gamePanel);
	}
	
	/**
	 * This method allows cards to be selected from the dropdown menu.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		selectedCard = (Card) cardDropDown.getSelectedItem();
		selectedCardlabel.setText("Selected Card: " + selectedCard.toString());
	}
	
	/**
	 * This method is connected to the buttons.
	 * A player can either play a selected card or draw a card.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == drawButton) {
			if(gameServer.getTopOfDrawPile().getCardColor() == Card.WILD) {
				new WildColorGUI(this, true, null);
			} else {
				gameServer.drawOnTurn(gameServer.getNextPlayer(), Card.BLUE);
				new WaitGUI(this);
			}
		} else if(e.getSource() == playButton) {
			if(selectedCard.getCardColor() == Card.WILD) {
				new WildColorGUI(this, false, selectedCard);
			} else {
				if(gameServer.playCard(selectedCard, gameServer.getNextPlayer(), Card.BLUE)) {
					new WaitGUI(this);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Card! Penalty of One Card!");
					showPlayer();
				}
			}
		}
	}
	
	/**
	 * Getter for the gameServer
	 * @return gameServer
	 */
	public GameServer getGameServer() {
		
		return gameServer;
	}
}
