package reactor;

public class Constants {


    // DO NOT CHANGE FROM 1 UNLESS DEMOING
    // 1: Demo with all current features
    // 2: Single neutron interaction with Uranium
    // 3: Single neutron interaction with a grid
    // 4: Water demo
    // 5: Xenon demo
    public static int DEMOVERSION = 1;

    public static final class WaterConstants {
        public static float size = 34;

        public static final class Color0 {
            public static int R = 220;
            public static int G = 236;
            public static int B = 253;
        }

        public static final class Color10 {
            public static int R = 228;
            public static int G = 220;
            public static int B = 230;
        }

        public static final class Color20 {
            public static int R = 229;
            public static int G = 204;
            public static int B = 210;
        }

        public static final class Color30 {
            public static int R = 230;
            public static int G = 205;
            public static int B = 211;
        }

        public static final class Color40 {
            public static int R = 235;
            public static int G = 188;
            public static int B = 190;
        }

        public static final class Color50 {
            public static int R = 242;
            public static int G = 172;
            public static int B = 167;
        }

        public static final class Color60 {
            public static int R = 247;
            public static int G = 160;
            public static int B = 151;
        }

        public static final class Color70 {
            public static int R = 252;
            public static int G = 140;
            public static int B = 124;
        }

        public static final class Color80 {
            public static int R = 252;
            public static int G = 116;
            public static int B = 89;
        }

        public static final class Color90 {
            public static int R = 253;
            public static int G = 125;
            public static int B = 105;
        }

        public static final class Color100 {
            public static int R = 255;
            public static int G = 255;
            public static int B = 255;
        }
    }

    public static final class ControlRodConstants {
        public static float WIDTH = 8;
        public static float HEIGHT = 785;
        public static int R = 69;
        public static int G = 69;
        public static int B = 73;

        public static int STARTGAP = 101;
        public static int DISTANCE = 152;
        public static int STARTPOS = 28;


    }

    public static final class NeutronModeratorConstants {
        public static float WIDTH = 8;
        public static float HEIGHT = 785;

        public static int R = 69;
        public static int G = 69;
        public static int B = 73;

        public static int STARTY = 34;
        public static int STARTGAP = 25;
        public static int DISTANCE = 152;
    }

    public static final class AtomConstants {

        public static final class Create {
            public static int BUFFER = 10;
            public static int DISTANCE = 38;
            public static int NUMROWS = 40;
            public static int NUMCOLS = 21;
        }

        public static float SIZE = 22;

        public enum AtomType {
            URANIUM(36,140,252),
            NONFISSILE(186,188,190),
            XENON(69, 69, 73);


            private final int R;
            private final int G;
            private final int B;

            AtomType(int R, int G, int B) {
                this.R = R;
                this.G = G;
                this.B = B;
            }

            public int R() { return R; }
            public int G() { return G; }
            public int B() { return B; }

        }

    }

    public static final class NeutronConstants{
        public static float MOVESPEED = 2f;
        public static float UNMODMOVESPEED = 3f;
        public static float SIZE = 8;
        public static int R = 69;
        public static int G = 69;
        public static int B = 73;
    }

}
