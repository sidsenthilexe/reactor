import processing.core.PApplet;

public class Atom {
    private float x, y;
    private float size;
    private Constants.ATOM_TYPE atomType;

    public Atom(float x, float y, Constants.ATOM_TYPE atomType) {
        this.x = x;
        this.y = y;
        this.atomType = atomType;
        this.size = Constants.ATOM_SIZE;
    }

    public void periodic(PApplet window) {
        window.fill(36, 140, 252);
        window.stroke(36, 140, 252);
        window.ellipse(x, y, size, size);
    }


}
