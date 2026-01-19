package reactor;
import processing.core.PApplet;
import reactor.Constants.NeutronConstants;
import reactor.Constants.AtomConstants.AtomType;

import java.util.ArrayList;

public class Neutron {
    private float x, y, size;
    private float angle, speed;
    private boolean moderated;


    public Neutron(float x, float y, float moveAngle) {
        this.x = x;
        this.y = y;
        this.angle = moveAngle;
        this.size = NeutronConstants.SIZE;
        this.speed = NeutronConstants.MOVESPEED;
        this.moderated = false;
    }

    private void neutronUraniumCollisions(ArrayList<Atom> atoms, ArrayList<Neutron> neutrons) {
        if (this.moderated) {
        for (int i = 0; i < atoms.size(); i++) {
            Atom atom = atoms.get(i);
            if (atom.getAtomType() != AtomType.NONFISSILE && x <= atom.getBoundingBoxR() && x >= atom.getBoundingBoxL() && y <= atom.getBoundingBoxB() && y >= atom.getBoundingBoxT()) {
                float dX = Math.abs(atom.getX() - this.x);
                float dY = Math.abs(atom.getY() - this.y);
                double distance = Math.sqrt(dX*dX + dY*dY);
                if (distance <= atom.getSize()/2 + this.size/2) {
                    ParticleHandler.handleCollision(atom, this, neutrons);
                }
            }

        }
        }
    }

    private void waterInteraction(ArrayList<Water> waters, ArrayList<Neutron> neutrons) {
        for (int i = 0; i < waters.size(); i++) {
            Water water = waters.get(i);

            if (this.x >= water.getMinX()
                    && this.x <= water.getMaxX()
                    && this.y >= water.getMinY()
                    && this.y <= water.getMaxY()) {
                ParticleHandler.waterHeatingTick(water);

                if (water.getWaterHeatPercent() < 95 && (int)(Math.random()*400) == 0) {
                    ParticleHandler.handleCollision(this, neutrons);
                }
            }



        }
    }

    private void controlRodCollisions(ArrayList<ControlRod> controlRods, ArrayList<Neutron> neutrons) {
        if(moderated) {
            for (int i = 0; i < controlRods.size(); i++) {
                ControlRod controlRod = controlRods.get(i);

                if (this.x <= controlRod.getBoundRight()
                        && this.x >= controlRod.getBoundLeft()
                        && this.y >= controlRod.getBoundTop()
                        && this.y <= controlRod.getBoundBottom()) {

                    ParticleHandler.handleCollision(this, neutrons);
                }
            }
        }
    }

    private void neutronModeratorCollisions(ArrayList<NeutronModerator> neutronModerators) {
        if (!moderated) {
            for (int i = 0; i < neutronModerators.size(); i++) {
                NeutronModerator neutronModerator = neutronModerators.get(i);

                if (this.x <= neutronModerator.getBoundRight()
                        && this.x >= neutronModerator.getBoundLeft()
                        && this.y >= neutronModerator.getBoundTop()
                        && this.y <= neutronModerator.getBoundBottom()) {

                    ParticleHandler.handleCollisionModerate(this);
                }
            }
        }
    }

    public void setModerationState(boolean state) { this.moderated = state; }

    public float getAngle() { return angle; }

    public void setAngle(float angle) { this.angle = angle; }


    private void screenExit(ArrayList<Neutron> neutrons) {
        if ((x<0 || y<0 || x>1600 || y>850) && Constants.DEMOVERSION == 1) ParticleHandler.exitScreenHandler(this, neutrons);
    }

    public void periodic(PApplet window, ArrayList<Neutron> neutrons, ArrayList<Atom> atoms, ArrayList<ControlRod> controlRods, ArrayList<NeutronModerator> neutronModerators, ArrayList<Water> water) {

        x += (float) (speed*Math.cos(angle));
        y += (float) (speed*Math.sin(angle));

        neutronUraniumCollisions(atoms, neutrons);

        controlRodCollisions(controlRods, neutrons);

        neutronModeratorCollisions(neutronModerators);

        waterInteraction(water, neutrons);

        screenExit(neutrons);

        draw(window);
    }

    private void draw(PApplet window) {
        if (moderated) {
            window.fill(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
            window.stroke(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
            window.ellipse(x, y, size, size);
        } else {
            window.fill(255,255,255);
            window.stroke(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
            window.strokeWeight(2);
            window.ellipse(x, y, size, size);
            window.strokeWeight(0);
        }

    }
}
