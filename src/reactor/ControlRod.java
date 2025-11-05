package reactor;

import processing.core.PApplet;
import reactor.Constants.ControlRodConstants;

public class ControlRod {
    private float x, pos;
    private float width, height;

    public ControlRod(float x, float pos) {
        this.x = x;
        this.pos = pos;
        this.width = ControlRodConstants.WIDTH;
        this.height = ControlRodConstants.HEIGHT;
    }

    public void periodic(PApplet window, int numNeutrons) {
        draw(window);
    }

    public float getXMin() { return x; }
    public float getXMax() { return x + width; }
    public float getXCenter() { return x + (width/2); }
    public float getYBottom() { return pos; };
    public float getYTop() { return pos + height; }
    public float getYCenter() { return pos + (height/2); }

    private void draw(PApplet window) {
        window.fill(ControlRodConstants.R, ControlRodConstants.G, ControlRodConstants.B);
        window.stroke(ControlRodConstants.R, ControlRodConstants.B, ControlRodConstants.G);
        window.rect(x, pos, width, height);
    }



}
