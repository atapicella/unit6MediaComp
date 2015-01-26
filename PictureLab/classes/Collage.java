

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
        Picture canvas2 = new Picture(600, 800);
        canvas2.copy(canvas,0,600,0,800,0,0);
        canvas2.zeroBlue();
        //canvas.mirrorDiagonal();
        //canvas.mirrorDiagonalRightToLeft();
        canvas2.negate();
        
        
        
        
        
        canvas2.explore();
    }

}
