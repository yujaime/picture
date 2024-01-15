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
        pixelObj.setBlue(0);
      }
    }
  }

  public void zeroRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
      }
    }
  }

  public void zeroGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
      }
    }
  }

  public void setBlue(int x)
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(x);
      }
    }
  }

  public void setRed(int x)
  {
    Pixel[][] pixels = this.getPixels2D();
    for( Pixel[] rowArray : pixels )
    {
      for( Pixel pixelObj : rowArray )
      {
        pixelObj.setRed(x);
      }
    }
  }

  public void setGreen(int x)
  {
    Pixel[][] pixels = this.getPixels2D();
    for( Pixel[] rowArray : pixels )
    {
      for( Pixel pixelObj : rowArray )
      {
        pixelObj.setGreen(x);
      }
    }
  }

  // public int getAverage()
  // {
  //   getRed();
  //   getGreen();
  //   getBlue();
  // }
  
  /** Method to set the red and blue to 0 */
  public void greenOnly()
  {
    //add your code here
    zeroRed();
    zeroBlue();
  }
  
  /** Method to set the red,green,blue to (255 - its original value)  */
  public void negate()
  {
    //add your code here
    Pixel[][] pixels = this.getPixels2D();
    for( Pixel[] rowArray : pixels )
    {
      for( Pixel pixelObj : rowArray )
      {
        pixelObj.setRed(225-pixelObj.getRed());
        pixelObj.setBlue(225-pixelObj.getBlue());
        pixelObj.setGreen(225-pixelObj.getGreen());
      }
    }
    
  }
  
  /** Method to set all red,green,blue to the average of the three values  */
  public void grayscale()
  {
    //add your code here
    Pixel[][] pixels = this.getPixels2D();
    for( Pixel[] rowArray : pixels )
    {
      for( Pixel pixelObj : rowArray )
      {
        int a = pixelObj.getRed();
        int b = pixelObj.getBlue();
        int c = pixelObj.getGreen();
        int x = (a+b+c)/3;
        pixelObj.setGreen(x);
        pixelObj.setBlue(x);
        pixelObj.setRed(x);
      }
    }
  }
  
  /** Method to make the shape of the fish stand out  */
  public void enhanceFish()
  {
    //add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int a = pixelObj.getRed();
        int b = pixelObj.getGreen();
        int c = pixelObj.getBlue();
        if( (a >10)&&(a<30) && (b>150)&&(b<175) && (c>160)&&(c<195) )
        {
          pixelObj.setRed(225);
          pixelObj.setBlue(225);
          pixelObj.setGreen(225);
        }
      }
    }
  }
  
  /** Method to highlight the edges of object in a picture by checking large changes in color */
  public void edgeDetection()
  {
    //add your code here
    Picture read = new  Picture(getFileName());
    Pixel[][] pixels = read.getPixels2D();
    Pixel[][] actual = getPixels2D();
    int width = read.getWidth();
    int height = read.getHeight();
    for (int r = 0; r < height; r++ )
    {
      for (int c = 0; c < width; c++ )
      {

        int x = pixels[r][c].getX();
        int y = pixels[r][c].getY();
        //System.out.println( "(" + x + "," + y + ")[" + r + "," + c + "]" );

        boolean leftEdge = false;
        boolean rightEdge = false;
        boolean topEdge = false;
        boolean bottomEdge = false;


        if( x == 0)
        {
          leftEdge = true;
          //rightEdge = false;
        }
        if( x == width-1 )
        {
          rightEdge = true;
          //leftEdge = false;
        }
        if( y == 0 )
        {
          bottomEdge = true;
          topEdge = false;
        }
        if( y == height-1 )
        {
          topEdge = true;
          bottomEdge = false;
        }
        Color topLeft = pixels[r][c].getColor();
        Color topRight = pixels[r][c].getColor();
        Color topMid = pixels[r][c].getColor();
        Color left = pixels[r][c].getColor();
        Color right = pixels[r][c].getColor();
        Color botMid = pixels[r][c].getColor();
        Color botLeft = pixels[r][c].getColor();
        Color botRight = pixels[r][c].getColor();

        if( !leftEdge )
        {
          //topLeft = read.getPixel(x-1, y+1).getColor();
          left = read.getPixel(x-1,y).getColor();
          //botLeft = read.getPixel(x-1,y-1).getColor();
          if( !topEdge )
          {
            topLeft = read.getPixel(x-1,y+1).getColor();
          }
          if( !bottomEdge )
          {
            botLeft = read.getPixel(x-1,y-1).getColor();
          }
        }
        if( !rightEdge )
        {
          //topRight = read.getPixel(x+1, y+1).getColor();
          right = read.getPixel(x+1, y).getColor();
          //botRight = read.getPixel(x+1,y-1).getColor();
          if( !topEdge )
          {
            topRight = read.getPixel(x+1,y+1).getColor();
          }
          if( !bottomEdge )
          {
            botRight = read.getPixel(x+1,y-1).getColor();
          }
        }
        if( !topEdge )
        {
          topMid = read.getPixel(x,y+1).getColor();
        }
        if( !bottomEdge )
        {
          botMid = read.getPixel(x,y-1).getColor();
        }

        //yay i got all the colors

        Color[] surrounding = {topLeft, topMid, topRight,left,right,botLeft,botMid,botRight};
        int drastic = 0;
        for( Color compare : surrounding )
        {
          double value = pixels[r][c].colorDistance(compare);
          if( value > 11 )//for creepy use 5.
          {
            drastic++;
          }
        }
        if( drastic > 4 )
        {
          actual[r][c].setRed(0);
          actual[r][c].setBlue(0);
          actual[r][c].setGreen(0);
        }
        else
        {
          actual[r][c].setRed(225);
          actual[r][c].setBlue(225);
          actual[r][c].setGreen(225);
        }

        // topEdge = false;
        // bottomEdge = false;
        // leftEdge = false;
        // rightEdge = false;
      }
    }
  }
  
  
  /** Method to mirror the picture around a vertical line in the center of the picture from left to right */
  public void mirrorVertical() {
    Pixel[][] pixels = this.getPixels2D();
    int height = pixels.length;
    int width = pixels[0].length;

    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width / 2; col++) {
            Pixel leftPixel = pixels[row][col];
            Pixel rightPixel = pixels[row][width - col - 1];

            // Swap the pixels without overwriting the original values
            rightPixel.setColor(leftPixel.getColor());
        }
    }
}
  
  /** Method to mirror around a diagonal line from bottom left to top right */
  public void mirrorDiagonal()
  {
    //add your code here
    Pixel[][] pixels = this.getPixels2D();
    for( Pixel[] rowArray : pixels )
    {
      for( Pixel pixelObj : rowArray )
      {
        
      }
    }
    
  }
    
  /** Method to mirror just part of a picture of a temple to fix the broken part of the temple */
  public void mirrorTemple()
  {
    //add your code here
  }
  
  /** Method to mirror just part of a picture of a snowman, so that it will have four arms instead of two */
  public void mirrorArms()
  {
    //add your code here
  }
  
  /** Method to copy the gull in the picture to another location of the picture */
  public void copyGull()
  {
    //add your code here
  }
     
  
  /** Method to replace the blue background with the pixels in the newBack picture
    * @param newBack the picture to copy from
    */
  public void chromakey(Picture newBack)
  {
    //add your code here
  }
  
  /** Method to decode a message hidden in the red value of the current picture */
  public void decode()
  {
    //add your code here
  }
  
  /** Hide a black and white message in the current picture by changing the green to even and then setting it to odd if the message pixel is black 
    * @param messagePict the picture with a message
    */
  public void encodeGreen(Picture messagePict)
  {
    //add your code here
  }

  /** Your own customized method*/
  public void customized()
  {
    //add your code here
  }
} 
