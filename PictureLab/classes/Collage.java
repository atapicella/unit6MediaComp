

/**
 * Write a description of class Collage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collage
{
    public static void main(String AArgs[])
    {
        Picture canvas = new Picture("600x800.jpg");
        Picture planet = new Picture("planet.jpg");
        canvas.scaleByHalf(planet, 50);
        canvas.mirrorDiagonal();
        canvas.mirrorHorizontalBotToTop();
        //planet.zeroBlue();
        canvas.keepOnlyBlueTopHalf();
        
        
        
        
        
        
        
        
        
        canvas.explore();
    }

}
