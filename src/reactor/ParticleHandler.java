package reactor;
import reactor.Constants.AtomConstants.AtomType;

import java.util.ArrayList;

public class ParticleHandler {

    public static void handleCollision(Atom atom, Neutron neutron, ArrayList<Neutron> neutrons) {
        atom.setAtomType(AtomType.NONFISSILE);
        neutrons.remove(neutron);
        float randAngle1 = (float) (Math.random() * Math.PI*2 / 3);
        float randAngle2 = (float) ( (Math.random() * Math.PI*2 / 3) + (Math.PI * 2 / 3) );
        float randAngle3 = (float) ( (Math.random() * Math.PI*2 / 3) + (Math.PI * 4 / 3) );

        Neutron newNeutron1 = new Neutron(atom.getX(), atom.getY(), randAngle1);
        Neutron newNeutron2 = new Neutron(atom.getX(), atom.getY(), randAngle2);
        Neutron newNeutron3 = new Neutron(atom.getX(), atom.getY(), randAngle3);
        neutrons.add(newNeutron1);
        neutrons.add(newNeutron2);
        neutrons.add(newNeutron3);
    }

    public static void exitScreenHandler(Neutron neutron, ArrayList<Neutron> neutrons) {
        neutrons.remove(neutron);
    }

    public static void createNeutron(float x, float y, float moveAngle, ArrayList<Neutron> neutrons) {
        Neutron newNeutron = new Neutron(x, y, moveAngle);
        neutrons.add(newNeutron);

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

}
