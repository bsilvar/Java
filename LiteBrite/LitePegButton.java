import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LitePegButton extends JButton {

    private final Color COLORS[] = {Color.BLACK, Color.BLUE, Color.GREEN,
                      Color.YELLOW, Color.ORANGE, Color.RED, Color.PINK};
    private int colorIndex;

    public LitePegButton() {
        this.colorIndex = 0;
        this.setPreferredSize(new Dimension(25,25));
        this.setBackground(COLORS[colorIndex]);
        this.addActionListener(new ButtonListener());
    }

    public Color getColor() {
        return COLORS[colorIndex];
    }

    public void resetColor() {
        colorIndex = 0;
        setBackground(COLORS[colorIndex]);
    }

    public void changeColor() {
        colorIndex++;
        setBackground(COLORS[colorIndex]);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            colorIndex = (colorIndex+1) % COLORS.length;
            setBackground(COLORS[colorIndex]);
        }

    }

}

