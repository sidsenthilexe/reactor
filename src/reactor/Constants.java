package reactor;

public class Constants {

    public static final class AtomConstants {

        public static final class Create {
            public static int BUFFER = 30;
            public static int DISTANCE = 38;
            public static int NUMROWS = 35;
            public static int NUMCOLS = 17;
        }

        public static float SIZE = 20;

        public enum AtomType {
            URANIUM(36,140,252),
            NONFISSILE(186,188,190);

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
        public static float MOVESPEED = 2;
        public static float SIZE = 8;
        public static int R = 69;
        public static int G = 69;
        public static int B = 73;
    }

}
