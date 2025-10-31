package reactor;
import processing.core.PApplet;
import reactor.Constants.AtomConstants;
import reactor.Constants.AtomConstants.*;

public class Atom {
    private float x, y, size;
    private AtomType atomType;

    public Atom(float x, float y, AtomType atomType) {
        this.x = x;
        this.y = y;
        this.atomType = atomType;
        this.size = AtomConstants.SIZE;
    }

    public AtomType getAtomType() { return atomType; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSize() { return size; }

    public void periodic(PApplet window) {

        draw(window);
    }

    private void draw(PApplet window) {
        window.fill(atomType.R(), atomType.G(), atomType.B());
        window.stroke(atomType.R(), atomType.G(), atomType.B());
        window.ellipse(x, y, size, size);
    }

}
