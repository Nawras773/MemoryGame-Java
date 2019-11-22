
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private List<Card> cards;
	private Card selectedCard;
	private Card card1;
	private Card card2;
	private Timer t;

	public MainWindow(){

		int pairs = 8;
		List<Card> cardsList = new ArrayList<Card>();
		List<Integer> cardVals = new ArrayList<Integer>();

		for (int i = 0; i < pairs; i++){
			cardVals.add(i);
			cardVals.add(i);
		}
		Collections.shuffle(cardVals);

		for (int val : cardVals){
			Card card = new Card();
			card.setId(val);
			card.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					selectedCard = card;
					doTurn();
				}
			});
			cardsList.add(card);
		}
		this.cards = cardsList;
		//set up the timer
		t = new Timer(250, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				checkCards();
			}
		});

		t.setRepeats(false);

		//set up the board itself
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(4, 5));
		for (Card card : cards){
			pane.add(card);
		}
		setTitle("Memory Game");
                
	}

	private void doTurn(){
		if (card1 == null && card2 == null){
			card1 = selectedCard;
			card1.setText(String.valueOf(card1.getId()));
		}

		if (card1 != null && card1 != selectedCard && card2 == null){
			card2 = selectedCard;
			card2.setText(String.valueOf(card2.getId()));
			t.start();

		}
	}

	public void checkCards(){
		if (card1.getId() == card2.getId()){//match condition
			card1.setEnabled(false); //disables the button
			card2.setEnabled(false);
			card1.setMatched(true); //flags the button as having been matched
			card2.setMatched(true);
			if (this.isGameWon()){
				JOptionPane.showMessageDialog(this, "Gewoonen");
				System.exit(0);
			}
		}

		else{
			card1.setText(""); //'hides' text
			card2.setText("");
		}
		card1 = null; //reset c1 and c2
		card2 = null;
	}

	public boolean isGameWon(){
		for(Card card: this.cards){
			if (card.getMatched() == false){
				return false;
			}
		}
		return true;
	}

}