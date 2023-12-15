import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LiteBriteBoardPanel extends JPanel {

    private LitePegButton[][] pegs;

    public LiteBriteBoardPanel(ActionListener listener, int width, int height) {
        this.setLayout(new GridLayout(width, height));
        this.pegs = new LitePegButton[width][height];
        for(int row = 0; row < pegs.length; row++) {
            for(int column = 0; column < pegs.length; column++) {
                pegs[row][column] = new LitePegButton();
                add(pegs[row][column]);
            }
        }
    }

    public void reset() {
        for(int row = 0; row < pegs.length; row++) {
            for(int column = 0; column < pegs.length; column++) {
                pegs[row][column].resetColor();
            }
        }
    }

}

