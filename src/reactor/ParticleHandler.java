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

    }

}
