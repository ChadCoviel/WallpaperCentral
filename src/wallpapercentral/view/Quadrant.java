package wallpapercentral.view;

public class Quadrant {
    public static int UPPER_RIGHT = 1;
    public static int LOWER_RIGHT = 2;
    public static int LOWER_LEFT = 3;
    public static int UPPER_LEFT = 4;
    public static int ORIGIN = 0;
    private int value;
    private String quadrant;

    public Quadrant(double x, double y) {
        if(x > 0 && y > 0) {
            value = 1;
            quadrant = "upper left";
        }
        else if(x > 0 && y < 0) {
            value = 2;
            quadrant = "lower right";
        }
        else if(x < 0 && y < 0) {
            value = 3;
            quadrant = "lower left";
        }
        else if (x<0 && y >0) {
            value = 4;
            quadrant = "upper left";
        }
        else {
            value = 0;
            quadrant = "none";
        }
    }

    public String toString() {
        return quadrant;
    }
}
