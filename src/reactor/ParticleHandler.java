package reactor;
import reactor.Constants.AtomConstants.AtomType;

import java.util.ArrayList;

public class ParticleHandler {

    private static float deployDoublePercent = 150;

    public static void handleCollision(Atom atom, Neutron neutron, ArrayList<Neutron> neutrons) {


        if (atom.getAtomType() == AtomType.URANIUM) {
            atom.setAtomType(AtomType.NONFISSILE);

            if ( (int)(Math.random() * 17) == 0) atom.setQueueForXenon(true);

            neutrons.remove(neutron);
            float randAngle1 = (float) (Math.random() * Math.PI * 2 / 3);
            float randAngle2 = (float) ((Math.random() * Math.PI * 2 / 3) + (Math.PI * 2 / 3));
            float randAngle3 = (float) ((Math.random() * Math.PI * 2 / 3) + (Math.PI * 4 / 3));

            Neutron newNeutron1 = new Neutron(atom.getX(), atom.getY(), randAngle1);
            Neutron newNeutron2 = new Neutron(atom.getX(), atom.getY(), randAngle2);
            Neutron newNeutron3 = new Neutron(atom.getX(), atom.getY(), randAngle3);
            neutrons.add(newNeutron1);
            neutrons.add(newNeutron2);
            neutrons.add(newNeutron3);
        }

        if (atom.getAtomType() == AtomType.XENON) {
            neutrons.remove(neutron);
            atom.setAtomType(AtomType.NONFISSILE);
        }
    }

    public static void handleCollision(Neutron neutron, ArrayList<Neutron> neutrons) {
        neutrons.remove(neutron);
    }



    public static void exitScreenHandler(Neutron neutron, ArrayList<Neutron> neutrons) {
        neutrons.remove(neutron);
    }

    public static void createNeutron(float x, float y, float moveAngle, ArrayList<Neutron> neutrons) {
        Neutron newNeutron = new Neutron(x, y, moveAngle);
        neutrons.add(newNeutron);

    }

    public static void waterHeatingTick(Water water) {
        water.setWaterHeatPercent((float) (water.getWaterHeatPercent()+1));
    }

    public static void atomReplaceHandler(ArrayList<Atom> atoms) {
        int targetUranium =125; //~ 10% concentration of uranium
        int currentUranium = 0;
        int uraniumDeficit = 0;

        for (Atom atom : atoms) {
            if (atom.getAtomType() == AtomType.URANIUM) currentUranium++;
        }

        if ( (targetUranium - currentUranium) > 0) uraniumDeficit = targetUranium-currentUranium;

        while (uraniumDeficit > 0) {
            int randomAtomIndex = (int) (Math.random() * 819);
            if (atoms.get(randomAtomIndex).getAtomType() == AtomType.NONFISSILE) {
                atoms.get(randomAtomIndex).setAtomType(AtomType.URANIUM);
                uraniumDeficit--;
            }
        }


    }

    public static void deployControlRodsTo(ArrayList<ControlRod> controlRods, float deployDoublePercent) {
        if (deployDoublePercent > 100 && 200 >= deployDoublePercent) {
            for (int i = 0; i < controlRods.size(); i++) {
                if (i % 2 != 0) controlRods.get(i).setDeployPercent(100);
                else controlRods.get(i).setDeployPercent( (deployDoublePercent - 100));
            }
        } else  if (deployDoublePercent <= 100 && 0 <= deployDoublePercent){
            for (int i = 0; i < controlRods.size(); i++) {
                if (i%2 == 0) controlRods.get(i).setDeployPercent(0);
                else controlRods.get(i).setDeployPercent(deployDoublePercent);
            }
        }
    }

    public static void autoDeployControlRods(ArrayList<ControlRod> controlRods, int neutrons) {
        if (neutrons < 30) deployDoublePercent-= 0.20F;
        else if (neutrons > 33) deployDoublePercent += 0.25F;

        deployDoublePercent = Math.max(Math.min(deployDoublePercent, 200), 0);

        deployControlRodsTo(controlRods, deployDoublePercent);
    }

}
