import java.awt.Dimension;
import javax.swing.JFrame;


class Game {
	public static void main(String[] args){
		MainWindow b = new MainWindow();
		b.setPreferredSize(new Dimension(500, 500)); 	//need to use this instead of setSize
		b.setLocation(500,250);
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.pack();
		b.setVisible(true);
	}
}