

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
        Picture planet = new Picture("planet.jpg");
        planet.mirrorDiagonal();
        planet.mirrorHorizontalBotToTop();
        //planet.zeroBlue();
        planet.keepOnlyBlueTopHalf();
        
        
        
        
        
        
        
        
        
        planet.explore();
    }

}
