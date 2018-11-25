/*
    Moving Tree recursion
*/
public class MovingTree {
    public static int swag = 15;
    public static int swag2 = 37;

    public static void tree(int n, double x, double y, double a, double branchRadius, int neuBandangle) {
        double bendAngle = Math.toRadians(neuBandangle);
        double branchAngle = Math.toRadians(37);
        double branchRatio = 0.65;

        double cx = x + Math.cos(a) * branchRadius;
        double cy = y + Math.sin(a) * branchRadius;
        StdDraw.setPenRadius(0.001 * Math.pow(n, 1.2));
        StdDraw.line(x, y, cx, cy);

        if (n == 0) return;

        tree(n - 1, cx, cy, a + bendAngle - branchAngle, branchRadius * branchRatio, neuBandangle);
        tree(n - 1, cx, cy, a + bendAngle + branchAngle, branchRadius * branchRatio, neuBandangle);
        tree(n - 1, cx, cy, a + bendAngle, branchRadius * (1 - branchRatio), neuBandangle);
    }

    public static void main(String[] args) {
        int n = 9;

        int neuBandangle = 15;

        boolean checkSide = true;
        StdDraw.enableDoubleBuffering();
        int neuBendAngle = 15;


        while (true) {
            StdDraw.clear();
            tree(n, 0.5, 0, Math.PI / 2, 0.3, neuBandangle);

            if (checkSide) {
                if (neuBandangle > 0) {
                    neuBandangle--;

                } else {
                    if (neuBandangle <= -15)
                        checkSide = !checkSide;
                    else {
                        neuBandangle--;

                    }
                }
            } else {
                if (neuBandangle < 0) {
                    neuBandangle++;

                } else {
                    if (neuBandangle >= 15)
                        checkSide = !checkSide;
                    else {
                        neuBandangle++;

                    }
                }
            }


            StdDraw.show();

        }

    }
}

