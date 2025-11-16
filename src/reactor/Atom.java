package reactor;
import processing.core.PApplet;
import reactor.Constants.AtomConstants;
import reactor.Constants.AtomConstants.*;
import java.util.ArrayList;

public class Atom {
    private float x, y, size;
    private float boundingBoxL, boundingBoxR, boundingBoxT, boundingBoxB;
    private AtomType atomType;
    private boolean queueForXenon;

    public Atom(float x, float y, AtomType atomType) {
        this.x = x;
        this.y = y;
        this.atomType = atomType;
        this.size = AtomConstants.SIZE;
        queueForXenon = false;
        boundingBoxL = (x - (size/2)) - (Constants.NeutronConstants.SIZE/2);
        boundingBoxR = (x + (size/2)) + (Constants.NeutronConstants.SIZE/2);
        boundingBoxT = (y - (size/2)) - (Constants.NeutronConstants.SIZE/2);
        boundingBoxB = (y + (size/2)) - (Constants.NeutronConstants.SIZE/2);
    }

    public AtomType getAtomType() { return atomType; }
    public void setAtomType(AtomType atomType) { this.atomType = atomType; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSize() { return size; }

    public float getBoundingBoxL() { return boundingBoxL; }
    public float getBoundingBoxR() { return boundingBoxR; }
    public float getBoundingBoxT() { return boundingBoxT; }
    public float getBoundingBoxB() { return boundingBoxB; }

    public void setQueueForXenon(boolean state) { queueForXenon = state; }
    public boolean getQueueForXenon() { return queueForXenon; }


    public void periodic(PApplet window, ArrayList<Neutron> neutrons) {

        if(this.atomType == AtomType.NONFISSILE && Constants.DEMOVERSION == 1) {
            if ((int)(Math.random()*50000) == 0) ParticleHandler.createNeutron(this.x, this.y, (float) (Math.random() * Math.PI * 2), neutrons);
        }

        if(this.atomType == AtomType.NONFISSILE && this.queueForXenon) {
            if ((int)(Math.random()*120) == 0) {
                this.queueForXenon = false;
                setAtomType(AtomType.XENON);
            }
        }

        draw(window);
    }

    private void draw(PApplet window) {
        window.fill(atomType.R(), atomType.G(), atomType.B());
        window.stroke(atomType.R(), atomType.G(), atomType.B());
        window.ellipse(x, y, size, size);
    }

}
