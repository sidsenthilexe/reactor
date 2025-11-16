import processing.core.PApplet;
import processing.core.PFont;
import processing.opengl.*;
import com.jogamp.opengl.GL;
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

    String gpuVendor = "N/A";
    String gpuName = "N/A";
    String rendererName;

    int uraniumCount;
    int xenonCount;

    int demoVersion = reactor.Constants.DEMOVERSION;

    public void settings() {
        size(1600, 850, P2D);   // set the window size

    }

    public void setup() {

        surface.setTitle("reactor (github.com/sidsenthilexe/reactor)");
        frameRate(30);

        if (g instanceof PGraphicsOpenGL) {
            PGraphicsOpenGL pg = (PGraphicsOpenGL) g;
            pg.beginPGL();
            PJOGL pgl = (PJOGL) pg.pgl;
            gpuVendor = pgl.gl.glGetString(GL.GL_VENDOR);
            gpuName = pgl.gl.glGetString(GL.GL_RENDERER);
            pg.endPGL();
        }

        rendererName = g.getClass().getName();

        System.out.println("Rendering with\n" + rendererName + "\non\n" + gpuVendor + " " + gpuName);

        if (demoVersion == 1) {

            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            for (int x = 1; x <= Create.NUMROWS; x++) {
                for (int y = 1; y <= Create.NUMCOLS; y++) {
                    AtomType newAtomType;

                    if (x == 3 && y == 18) newAtomType = AtomType.URANIUM;
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

        } else if (demoVersion == 2) {

            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            Atom newAtom = new Atom(800, 425, AtomType.URANIUM);
            atoms.add(newAtom);



            Neutron testNeutron = new Neutron(250,  425, (float) 0);
            neutrons.add(testNeutron);

        } else if (demoVersion == 3) {
            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            for (int x = 1; x <= Create.NUMROWS; x++) {
                for (int y = 1; y <= Create.NUMCOLS; y++) {

                    Atom newAtom = new Atom(Create.DISTANCE * x + Create.BUFFER,
                            Create.DISTANCE * y + Create.BUFFER,
                            AtomType.URANIUM);
                    atoms.add(newAtom);

                }
            }

            Neutron testNeutron = new Neutron(1,  1, (float) Math.PI / 4);
            neutrons.add(testNeutron);
        } else if (demoVersion == 4) {
            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            for (int x = 1; x <= Create.NUMROWS; x++) {
                for (int y = 1; y <= Create.NUMCOLS; y++) {
                    Water newWater = new Water((Create.DISTANCE * x + Create.BUFFER) - 17,
                            (Create.DISTANCE * y + Create.BUFFER) - 17);
                    water.add(newWater);

                }
            }

            for (int x = 1; x < 50; x++) {
                for (int y = 1; y < 25; y++) {
                    Neutron newNeutron = new Neutron(x * (15) - 600, y * (30) + 50, (float) ((Math.random() * Math.PI / 6) - Math.PI / 12));
                    neutrons.add(newNeutron);
                }
            }


        } else if (demoVersion == 5) {
            atoms = new ArrayList<>();
            neutrons = new ArrayList<>();
            controlRods = new ArrayList<>();
            water = new ArrayList<>();

            Atom newAtom = new Atom(800, 425, AtomType.URANIUM);
            atoms.add(newAtom);



            Neutron neutronOne = new Neutron(450,  425, (float) 0);
            Neutron neutronTwo = new Neutron(50, 425, 0F);
            neutrons.add(neutronOne);
            neutrons.add(neutronTwo);
        }
    }

    public void draw() {
        background(255);    // paint screen white

        if (demoVersion == 1) ParticleHandler.atomReplaceHandler(atoms);


        for (Water water: water) {
            water.periodic(this);
        }

        uraniumCount = 0;
        xenonCount = 0;

        for (Atom atom : atoms) {

            atom.periodic(this, neutrons);
            if (atom.getAtomType() == AtomType.URANIUM) uraniumCount ++;
            else if (atom.getAtomType() == AtomType.XENON) xenonCount ++;

        }

        for (int i = 0; i < neutrons.size(); i++) {
            neutrons.get(i).periodic(this, neutrons, atoms, controlRods, water);
        }

        ParticleHandler.autoDeployControlRods(controlRods, neutrons.size());

        for (ControlRod controlRod : controlRods) {
            controlRod.periodic(this, neutrons.size());
        }

        fill(0,0,0);
        stroke(0,0,0);
        textSize(20);
        PFont mono;
        mono = createFont("consola.ttf", 20);
        textFont(mono);



        text("reactor     N: " + (neutrons.size())
                        + "     CR: " + ParticleHandler.getCRDeployDoublePercent(),
                30, 844);

        text("     U: " + uraniumCount + " / 840"
                + "     XE: " + xenonCount + " / 840"
                + "     " + frameCount + " @ " + (int)frameRate,
                350, 844);

        text("     " + gpuName,
                855, 844);



    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
