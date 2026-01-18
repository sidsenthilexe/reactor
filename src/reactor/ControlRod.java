package reactor;

import processing.core.PApplet;
import reactor.Constants.ControlRodConstants;

public class ControlRod {
    private float x, pos;
    private float width, height;
    private float deployPercent;

    public ControlRod(float x, float pos) {
        this.x = x;
        this.pos = pos;
        this.width = ControlRodConstants.WIDTH;
        this.height = ControlRodConstants.HEIGHT;
        deployPercent = 100;
    }

    public void periodic(PApplet window) {
        this.pos = 28 - ( (100-deployPercent) * 8) ;

        draw(window);
    }

    public float getDeployPercent() { return deployPercent; }
    public void setDeployPercent(float deployPercent) { this.deployPercent = deployPercent; }

    public float getBoundRight() { return x + width + 4; }
    public float getBoundLeft() { return x - 4; }
    public float getBoundTop() { return pos - 4; }
    public float getBoundBottom() { return pos + height + 4; }

    private void draw(PApplet window) {
        window.fill(ControlRodConstants.R, ControlRodConstants.G, ControlRodConstants.B);
        window.stroke(ControlRodConstants.R, ControlRodConstants.B, ControlRodConstants.G);
        window.rect(x, pos, width, height);
    }



}
