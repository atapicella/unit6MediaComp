 import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

  
    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }
    
     public void keepOnlyBlueTopHalf()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length/2; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                pixels[row][col].setBlue(0);
            }
        }
    }
    
    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(255-pixelObj.getBlue());
                pixelObj.setRed(255-pixelObj.getRed());
                pixelObj.setGreen(255-pixelObj.getGreen());
            }
        }
    }
    
    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int avg = pixelObj.getBlue()+pixelObj.getRed()+pixelObj.getGreen()/3;
                pixelObj.setBlue(avg);
                pixelObj.setRed(avg);
                pixelObj.setGreen(avg);
            }
        }
    }
    
    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int red = pixelObj.getRed();
                int blue = pixelObj.getBlue();
                if (red < 20 && blue > 145)
                {
                    pixelObj.setBlue(blue + 10);
                    pixelObj.setGreen(pixelObj.getGreen()+ 10);
                    pixelObj.setRed(0);
                }
                    
                //pixelObj.setBlue(avg);
                //pixelObj.setRed(avg);
                //pixelObj.setGreen(avg);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }
    
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; (row < height / 2) && (col<width); col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }
    
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = height-1; row >=0; row--)
        {
            for (int col = 0; (row > height / 2) && (col<width); col++)
            {
                bottomPixel = pixels[row][col];
                topPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }
    
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row <height; row++)
        {
            for (int col = 0; col<row&&col<width; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[height - 1 - row][width-1-col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }
    
    public void mirrorArms()
    {
        int mirrorPoint = 198;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 170; row < mirrorPoint; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 102; col < 295; col++)
            {

                topPixel = pixels[row][col];      
                bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];                ;
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }
    
    public void mirrorGull()
    {
        int mirrorPoint = 347;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 234; row <= 313; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 237; col < mirrorPoint; col++)
            {
                
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];                
                if (leftPixel.getBlue() < 100 || leftPixel.getBlue() > 160)
                {
                    rightPixel.setColor(leftPixel.getColor());
                }
            }
        }
    }
    
//     public void copy(Picture sourcePicture, int startSourceRow, int endSourceRow,
//                 int startSourceCol, int endSourceCol, int startDestRow, int startDestCol)
//          {
//              Pixel fromPixel = null;
//              Pixel toPixel = null;
//              Pixel sourcePixels[][] = sourcePicture.getPixels2D();
//              Pixel toPixels[][] = this.getPixels2D();
//              for (int fromRow = startSourceRow, toRow = startDestRow; 
//                     fromRow<=endSourceRow && toRow<toPixels.length; fromRow++, toRow++)
//              {
//                  for (int fromCol = startSourceCol, toCol = startDestCol;
//                         fromCol<=endSourceCol && toCol<toPixels[0].length; fromCol++, toCol++)
//                         {
//                             fromPixel = sourcePixels[fromRow][fromCol];
//                             toPixel = toPixels[toRow][toCol];
//                             toPixel.setColor(fromPixel.getColor());                            
//                         }
//                     }
//             }
    //Scale entered as percentage        
    public void scaleByHalf(Picture sourcePicture, int scale)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel sourcePixels[][] = sourcePicture.getPixels2D();
        Pixel toPixels[][] = this.getPixels2D();
        int add = Math.round(100/scale);
        for (int fromRow = 0, toRow = 0; 
                fromRow<sourcePixels.length -1 && toRow<toPixels.length-1; fromRow+=add, toRow++)
        {
            for (int fromCol = 0, toCol = 0;
                   fromCol<sourcePixels[0].length-1 && toCol<toPixels[0].length-1; fromCol+=add, toCol++)
                   {
                       fromPixel = sourcePixels[fromRow][fromCol];
                       toPixel = toPixels[toRow][toCol];
                       toPixel.setColor(fromPixel.getColor());    
                   }
               }
    }
            


    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println(count);
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture canvas = new Picture("600x800.jpg");
        Picture planet = new Picture("planet.jpg");
        canvas.scaleByHalf(planet , 50);
        this.mirrorDiagonal();
        this.mirrorHorizontalBotToTop();
        this.keepOnlyBlueTopHalf();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("Desert.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
        beach.mirrorVertical();
        beach.explore();
        beach = new Picture("Desert.jpg");
        beach.mirrorVertical();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
