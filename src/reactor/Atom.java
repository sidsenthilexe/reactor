package reactor;
import processing.core.PApplet;
import reactor.Constants.AtomConstants;
import reactor.Constants.AtomConstants.*;

import java.util.ArrayList;

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
    public void setAtomType(AtomType atomType) { this.atomType = atomType; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSize() { return size; }

    public void periodic(PApplet window, ArrayList<Neutron> neutrons) {

        if(this.atomType == AtomType.NONFISSILE) {
            if ((int)(Math.random()*20000) == 0) ParticleHandler.createNeutron(this.x, this.y, (float) (Math.random() * Math.PI * 2), neutrons);
        }

        draw(window);
    }

    private void draw(PApplet window) {
        window.fill(atomType.R(), atomType.G(), atomType.B());
        window.stroke(atomType.R(), atomType.G(), atomType.B());
        window.ellipse(x, y, size, size);
    }

}
