package reactor;

import processing.core.PApplet;
import reactor.Constants.NeutronModeratorConstants;

public class NeutronModerator {
    private float x, y;
    private float width, height;

    public NeutronModerator(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = NeutronModeratorConstants.WIDTH;
        this.height = NeutronModeratorConstants.HEIGHT;
    }

    public void periodic(PApplet window) {
        draw(window);
    }

    public float getBoundRight() { return x + width + 4; }
    public float getBoundLeft() { return x - 4; }
    public float getBoundTop() { return y - 4; }
    public float getBoundBottom() { return y + height + 4; }

    private void draw(PApplet window) {
        window.fill(255, 255, 255);
        window.strokeWeight(3);
        window.stroke(NeutronModeratorConstants.R, NeutronModeratorConstants.G, NeutronModeratorConstants.B);
        window.rect(x, y, width, height);
        window.strokeWeight(0);
    }

}
