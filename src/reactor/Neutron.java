package reactor;
import processing.core.PApplet;
import reactor.Constants.NeutronConstants;

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

    private void neutronUraniumCollisions(ArrayList<Atom> atoms, ArrayList<Neutron> neutrons) {
        for (int i = 0; i < atoms.size(); i++) {
            Atom atom = atoms.get(i);
            if (x <= atom.getBoundingBoxR() && x >= atom.getBoundingBoxL() && y <= atom.getBoundingBoxB() && y >= atom.getBoundingBoxT()) {
                float dX = Math.abs(atom.getX() - this.x);
                float dY = Math.abs(atom.getY() - this.y);
                double distance = Math.sqrt(dX*dX + dY*dY);
                if (distance <= atom.getSize()/2 + this.size/2) {
                    ParticleHandler.handleCollision(atom, this, neutrons);
                }
            }

        }
    }

    private void waterInteraction(ArrayList<Water> waters, ArrayList<Neutron> neutrons) {
        for (int i = 0; i < waters.size(); i++) {
            Water water = waters.get(i);

            if (this.x > water.getMinX()
                    && this.x < water.getMaxX()
                    && this.y > water.getMinY()
                    && this.y < water.getMaxY()) {
                ParticleHandler.waterHeatingTick(water);

                if (water.getWaterHeatPercent() < 95 && (int)(Math.random()*400) == 0) {
                    ParticleHandler.handleCollision(this, neutrons);
                }
            }



        }
    }

    private void controlRodCollisions(ArrayList<ControlRod> controlRods, ArrayList<Neutron> neutrons) {
        for (int i = 0; i < controlRods.size(); i++) {
            ControlRod controlRod = controlRods.get(i);

            // if the neutron is to the left of the rod,
            // part of it is hitting the rod, and is within the vertical bounds of th rod
            if (x <= controlRod.getXCenter()
                    && x + 4 >= controlRod.getXMin()
                    && y+4 >= controlRod.getYTop()
                    && y-4 <= controlRod.getYBottom()) {
                ParticleHandler.handleCollision(this, neutrons);
            }

            // if the neutron is to the right of the rod, part of it is hitting, and is within vertical bounds.
            else if (x >= controlRod.getXCenter()
                    && x - 4 <= controlRod.getXMax()
                    && y+4 >= controlRod.getYTop()
                    && y-4 <= controlRod.getYBottom()) {
                ParticleHandler.handleCollision(this, neutrons);
            }


        }
    }

    private void screenExit(ArrayList<Neutron> neutrons) {
        if ((x<0 || y<0 || x>1600 || y>850) && Constants.DEMOVERSION == 1) ParticleHandler.exitScreenHandler(this, neutrons);
    }

    public void periodic(PApplet window, ArrayList<Neutron> neutrons, ArrayList<Atom> atoms, ArrayList<ControlRod> controlRods, ArrayList<Water> water) {

        x += (float) (speed*Math.cos(angle));
        y += (float) (speed*Math.sin(angle));

        neutronUraniumCollisions(atoms, neutrons);

        controlRodCollisions(controlRods, neutrons);

        waterInteraction(water, neutrons);

        screenExit(neutrons);

        draw(window);
    }

    public void draw(PApplet window) {
        window.fill(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
        window.stroke(NeutronConstants.R, NeutronConstants.G, NeutronConstants.B);
        window.ellipse(x, y, size, size);

    }
}
