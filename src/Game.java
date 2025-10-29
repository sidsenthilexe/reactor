import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    ArrayList<Atom> atoms;

    public void settings() {
        size(1280, 800);   // set the window size

    }

    public void setup() {

        atoms = new ArrayList<>();

        for (int x = 1; x <= 35; x++) {
            for (int y = 1; y <= 17; y++) {
                Atom newAtom = new Atom(35*x, 35*y, Constants.ATOM_TYPE.URANIUM);
                atoms.add( newAtom );
            }
        }
    }

    public void draw() {
        background(255);

        for (Atom atom : atoms) {
            atom.periodic(this);
        }

    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
