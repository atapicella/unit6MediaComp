

/**
 * Write a description of class Collage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collage
{
    public static void main(String Args[])
    {
        Picture canvas = new Picture(600, 800);
        Picture planet = new Picture("planet.jpg");
        canvas.scaleByHalf(planet);
        canvas.mirrorDiagonal();
        canvas.mirrorHorizontalBotToTop();
        canvas.mirrorVertical();
        canvas.zeroBlue();
        canvas.negate();       
        canvas.negateTriangle(200,299,299,398);
        canvas.mirrorVertical();
        canvas.mirrorHorizontal();

        canvas.explore();
    }

}
