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
    ArrayList<Water> water;
    int demoVersion = 1;

    public void settings() {
        size(1600, 850);   // set the window size

    }

    public void setup() {
        frameRate(30);

        if (demoVersion == 1) {

            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            for (int x = 1; x <= Create.NUMROWS; x++) {
                for (int y = 1; y <= Create.NUMCOLS; y++) {
                    AtomType newAtomType;

                    if (x == 1 && y == 1) newAtomType = AtomType.URANIUM;
                    else newAtomType = AtomType.NONFISSILE;

                    Atom newAtom = new Atom(Create.DISTANCE * x + Create.BUFFER,
                            Create.DISTANCE * y + Create.BUFFER,
                            newAtomType);
                    atoms.add(newAtom);

                    Water newWater = new Water((Create.DISTANCE * x + Create.BUFFER) - 17,
                            (Create.DISTANCE * y + Create.BUFFER) - 17);
                    water.add(newWater);

                }
            }

            for (int i = 0; i < 124; i++) {
                int randomAtomIndex = (int) (Math.random() * 819);
                atoms.get(randomAtomIndex).setAtomType(AtomType.URANIUM);
            }

            Neutron testNeutron = new Neutron(1,  700, (float) 0);
            neutrons.add(testNeutron);


            for (int x = 0; x < 10; x++) {
                ControlRod newControlRod = new ControlRod(ControlRodConstants.DISTANCE * x + ControlRodConstants.STARTGAP, 28);
                controlRods.add(newControlRod);
            }
        }
    }

    public void draw() {
        background(255);    // paint screen white

        ParticleHandler.atomReplaceHandler(atoms);


        for (Water water: water) {
            water.periodic(this);
        }

        for (Atom atom : atoms) {
            atom.periodic(this, neutrons);
        }

        for (int i = 0; i < neutrons.size(); i++) {
            neutrons.get(i).periodic(this, neutrons, atoms, controlRods, water);
        }

        System.out.println(neutrons.size());
        ParticleHandler.autoDeployControlRods(controlRods, neutrons.size());

        for (ControlRod controlRod : controlRods) {
            controlRod.periodic(this, neutrons.size() + 1);
        }



    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
