package reactor;
import processing.core.PApplet;
import reactor.Constants.NeutronConstants;
import reactor.Constants.AtomConstants.AtomType;

import java.util.ArrayList;

public class Neutron {
    private float x, y, size;
    private float angle, speed;

    public Neutron(float x, float y, float moveAngle) {
        this.x = x;
        this.y = y;
        this.angle = moveAngle;
        this.size = NeutronConstants.SIZE;
        this.speed = NeutronConstants.MOVESPEED;
    }

    public void collisions(ArrayList<Atom> atoms) {
        for (int i = 0; i < atoms.size(); i++) {
            Atom atom = atoms.get(i);

            if (atom.getAtomType() == AtomType.URANIUM) {
                float dX = Math.abs(atom.getX() - this.x);
                float dY = Math.abs(atom.getY() - this.y);
                double distance = Math.sqrt(dX*dX + dY*dY);
                if (distance <= atom.getSize()/2 + this.size/2) {
                    //
                }


            }
        }
    }

    public void periodic(PApplet window) {

        x += (float) (speed*Math.cos(angle));
        y += (float) (speed*Math.sin(angle));

        draw(window);
    }

    public void draw(PApplet window) {
        window.fill(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
        window.stroke(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
        window.ellipse(x, y, size, size);

    }
}
