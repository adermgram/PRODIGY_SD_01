import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Background extends JPanel {
    private Image backgroundImage;

    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}