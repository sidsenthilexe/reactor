import processing.core.PApplet;
import reactor.*;
import reactor.Constants.AtomConstants.Create;
import reactor.Constants.AtomConstants.AtomType;

import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Atom> atoms;
    ArrayList<Neutron> neutrons;

    public void settings() {
        size(1600, 800);   // set the window size

    }

    public void setup() {
        frameRate(30);

        atoms = new ArrayList<>();
        neutrons = new ArrayList<>();

        for (int x = 1; x <= Create.NUMROWS; x++) {
            for (int y = 1; y <= Create.NUMCOLS; y++) {

                Atom newAtom = new Atom(Create.DISTANCE * x + Create.BUFFER,
                                        Create.DISTANCE * y + Create.BUFFER,
                                        AtomType.URANIUM);
                atoms.add(newAtom);

            }
        }

        Neutron testNeutron = new Neutron(0, 0, (float) (Math.PI)/4 );
        neutrons.add(testNeutron);
    }

    public void draw() {
        background(255);    // paint screen white

        for (int i = 0; i < atoms.size(); i++) {
            atoms.get(i).periodic(this);
        }

        for (int i = 0; i < neutrons.size(); i++) {
            neutrons.get(i).periodic(this, neutrons, atoms);
        }

    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
