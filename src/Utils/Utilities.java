package Utils;

import javafx.scene.paint.Color;

public class Utilities
{
    public static String ConvertToHex(int red,int green , int blue)
    {
        return String.format( "#%02X%02X%02X",
                (int)( red * 255),
                (int)(green * 255),
                (int)( blue * 255));
    }

    public static double DegreeToRadians(double degrees)
    {
        return degrees * (Math.PI / 180);
    }
}

