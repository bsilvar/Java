import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LiteBritePanel extends JPanel
{
	private LiteBriteBoardPanel board;
	private JButton resetButton;

	/**
	 * Creates a new LiteBrite GUI with specified width and height.
	 * @param width The number of pegs in the horizontal axis.
	 * @param height The number of pegs in the vertical axis.
	 */
	public LiteBritePanel(int width, int height) {
		// Create new LiteBriteBoard with specified dimensions
		board = new LiteBriteBoardPanel(new LitePegListener(), width, height);

		// Create reset button and add ActionListener to it.
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetButtonListener());

		// Add sub-components to this main panel.
		this.add(board);
		this.add(resetButton);
	}

	// The ActionListener for the button to reset the game.
	private class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			board.reset();
		}
	}

	// Changes the color of the peg when the button is clicked.
	private class LitePegListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			LitePegButton peg = (LitePegButton) e.getSource();
			peg.changeColor();
		}
	}

}
