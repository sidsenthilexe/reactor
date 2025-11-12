package reactor;

import processing.core.PApplet;
import reactor.Constants.WaterConstants;
import reactor.Constants.WaterConstants.*;

public class Water {

    private float waterHeatPercent;
    private float size, x, y;

    public Water(float x, float y) {
        this.size = WaterConstants.size;
        this.x = x;
        this.y = y;
        this.waterHeatPercent = 0;
    }

    public float getMinX() { return x; }
    public float getMinY() { return y; }

    public float getMaxX() { return x + size; }
    public float getMaxY() { return y + size; }

    public float getWaterHeatPercent() { return waterHeatPercent; }

    public void setWaterHeatPercent(float newPercent) { waterHeatPercent = newPercent; }

    public void periodic (PApplet window) {

        waterHeatPercent -= 0.3F;

        waterHeatPercent = Math.max(0, waterHeatPercent);

             if (waterHeatPercent > 95) draw(window, Color100.R, Color100.G, Color100.B);
        else if (waterHeatPercent > 90) draw(window, Color90.R, Color90.G, Color90.B);
        else if (waterHeatPercent > 80) draw(window, Color80.R, Color80.G, Color80.B);
        else if (waterHeatPercent > 70) draw(window, Color70.R, Color70.G, Color70.B);
        else if (waterHeatPercent > 60) draw(window, Color60.R, Color60.G, Color60.B);
        else if (waterHeatPercent > 50) draw(window, Color50.R, Color50.G, Color50.B);
        else if (waterHeatPercent > 40) draw(window, Color40.R, Color40.G, Color40.B);
        else if (waterHeatPercent > 30) draw(window, Color30.R, Color30.G, Color30.B);
        else if (waterHeatPercent > 20) draw(window, Color20.R, Color20.G, Color20.B);
        else if (waterHeatPercent > 10) draw(window, Color10.R, Color10.G, Color10.B);
        else                            draw(window, Color0.R, Color0.G, Color0.B);
    }

    private void draw (PApplet window, int R, int G, int B) {
        window.fill(R, G, B);
        window.stroke(R, G, B);
        window.rect(x, y, size, size);
    }

}
