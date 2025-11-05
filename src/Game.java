import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Particle;
import processing.core.PApplet;
import reactor.*;
import reactor.Constants.AtomConstants.Create;
import reactor.Constants.AtomConstants.AtomType;
import reactor.Constants.ControlRodConstants;


import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Atom> atoms;
    ArrayList<Neutron> neutrons;
    ArrayList<ControlRod> controlRods;

    public void settings() {
        size(1600, 850);   // set the window size

    }

    public void setup() {
        frameRate(30);

        atoms = new ArrayList<>();
        neutrons = new ArrayList<>();
        controlRods = new ArrayList<>();

        for (int x = 1; x <= Create.NUMROWS; x++) {
            for (int y = 1; y <= Create.NUMCOLS; y++) {
                AtomType newAtomType;

                if (x==1 && y==1) newAtomType = AtomType.URANIUM;
                else newAtomType = AtomType.NONFISSILE;

                Atom newAtom = new Atom(Create.DISTANCE * x + Create.BUFFER,
                                        Create.DISTANCE * y + Create.BUFFER,
                                        newAtomType);
                atoms.add(newAtom);

            }
        }

        for (int i = 0; i < 124; i++) {
            int randomAtomIndex = (int) (Math.random() * 819);
            atoms.get(randomAtomIndex).setAtomType(AtomType.URANIUM);
        }

        Neutron testNeutron = new Neutron(1, 1, (float) (Math.PI)/4 );
        neutrons.add(testNeutron);

        for (int x = 0; x < 10; x++) {
            ControlRod newControlRod = new ControlRod(ControlRodConstants.DISTANCE * x + ControlRodConstants.STARTGAP, 28);
            controlRods.add(newControlRod);
        }
    }

    public void draw() {
        background(255);    // paint screen white

        ParticleHandler.atomReplaceHandler(atoms);

        for (int i = 0; i < atoms.size(); i++) {
            atoms.get(i).periodic(this, neutrons);
        }

        for (int i = 0; i < neutrons.size(); i++) {
            neutrons.get(i).periodic(this, neutrons, atoms, controlRods);
        }

        for (int i = 0; i < controlRods.size(); i++) {
            controlRods.get(i).periodic(this, neutrons.size()+1);
        }

    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
