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
      }
    }
  }
  
  
  /** Method to mirror the picture around a vertical line in the center of the picture from left to right */
  public void mirrorVertical()
  {
    //add your code here
    Pixel[][] actual = this.getPixels2D();
    int height = getHeight();
    int width = getWidth();
    int half = (width/2);
    for(int r = 0; r < height;r++ )
    {
      for( int c = 0; c < half; c++ )
      {
        
        int red = actual[r][c].getRed();
        int blue = actual[r][c].getBlue();
        int green = actual[r][c].getGreen();

       
        actual[r][width-c-1].setRed(red);
        actual[r][width-c-1].setBlue(blue);
        actual[r][width-c-1].setGreen(green);
        
      }
    }
    
  }
  
  /** Method to mirror around a diagonal line from bottom left to top right */
  public void mirrorDiagonal() {
    Pixel[][] pixels = getPixels2D();
    int height = getHeight();
    int width = getWidth();
    //int counter = width;
    int limit = Math.min(height,width);
    for(int r = limit-1; r > -1; r-- )
    {
      for( int c = 0; c < limit; c++ )
      {
        int red = pixels[r][c].getRed();
        int blue = pixels[r][c].getBlue();
        int green = pixels[r][c].getGreen();

        pixels[c][r].setRed(red);
        pixels[c][r].setBlue(blue);
        pixels[c][r].setGreen(green);
      }
      //counter--;
    }
}


    
  /** Method to mirror just part of a picture of a temple to fix the broken part of the temple */
  public void mirrorTemple()
  {
    //add your code here
    Pixel[][] actual = this.getPixels2D();
    //int height = getHeight();
    int width = getWidth();
    int half = (width/2);
    for(int r = 0; r < 100;r++ )
    {
      for( int c = 0; c < half; c++ )
      {
        
        int red = actual[r][c].getRed();
        int blue = actual[r][c].getBlue();
        int green = actual[r][c].getGreen();

      
        actual[r][width-c-1].setRed(red);
        actual[r][width-c-1].setBlue(blue);
        actual[r][width-c-1].setGreen(green);
        
      }
    }
  }
  
  /** Method to mirror just part of a picture of a snowman, so that it will have four arms instead of two */
  public void mirrorArms()
  {
    //add your code here
    Pixel[][] actual = this.getPixels2D();
    //int height = getHeight();
    //int width = getWidth();
    //int half = (height/2);
    // Pixel[][] target = 
    for(int r = 157; r < 200;r++ )
    {
      for( int c = 99; c < 299; c++ )
      {
        
        int red = actual[r][c].getRed();
        int blue = actual[r][c].getBlue();
        int green = actual[r][c].getGreen();

        if(red < 200 && red > 36 && blue < 100 && green < 100)
        {
          actual[180+(200-r)][c].setRed(red);
          actual[180+(200-r)][c].setBlue(blue);
          actual[180+(200-r)][c].setGreen(green);
        }
      }
    }
  }
  
  /** Method to copy the gull in the picture to another location of the picture */
  public void copyGull()
  {
    //add your code here
    Pixel[][] actual = this.getPixels2D();
    //int height = getHeight();
    int width = getWidth();
    //int half = (width/2);
    for(int r = 228; r < 328;r++ )
    {
      for( int c = 234; c < 351; c++ )
      {
        
        int red = actual[r][c].getRed();
        int blue = actual[r][c].getBlue();
        int green = actual[r][c].getGreen();

       
        actual[r][width-c+60].setRed(red);
        actual[r][width-c+60].setBlue(blue);
        actual[r][width-c+60].setGreen(green);
        
      }
    }
  }
     
  
  /** Method to replace the blue background with the pixels in the newBack picture
    * @param newBack the picture to copy from
    */
  public void chromakey(Picture newBack)
  {
    //add your code here
    //DigitalPicture x = new Picture(1,1);
    Pixel[][] original = this.getPixels2D();
    Pixel[][] bg = newBack.getPixels2D();
    //Pixel blue = new Pixel(x,1,1);
    // Color colBlue = new Color(11,39,76);

    for( int r = 0; r < original.length; r++ )
    {
      for( int c = 0; c < original[0].length; c++ )
      {

        int red = original[r][c].getRed();
        int blue = original[r][c].getBlue();
        int green = original[r][c].getGreen();

        // origColor = original.getColor();
        if( red < 35 && blue < 90 && blue > 4 && green > 5 && green < 55 )
        {
          Color replace = bg[r][c].getColor();
          original[r][c].setColor(replace);
        }

      }
    }

  }
  
  /** Method to decode a message hidden in the red value of the current picture */
  public void decode()
  {
    //add your code here
    Pixel[][] pixels = getPixels2D();
    Color black = new Color(0,0,0);
    Color white = new Color(255,255,255);
    for( int r = 0; r < getHeight(); r++ )
    {
      for( int c = 0; c < getWidth(); c++ )
      {
        int red = pixels[r][c].getRed();
        if(red%2==0)
        {
          pixels[r][c].setColor(black);
        }
        else
        {
          pixels[r][c].setColor(white);
        }
      }
    }
  }
  
  /** Hide a black and white message in the current picture by changing the green to even and then setting it to odd if the message pixel is black 
    * @param messagePict the picture with a message
    */
  public void encodeGreen(Picture messagePict)
  {
    //add your code here
    Pixel[][] msg = messagePict.getPixels2D();
    Pixel[][] pic = this.getPixels2D();
    for( Pixel[] rowArray : msg )
    {
      for( Pixel pixelObj : rowArray )
      {
        int red = pixelObj.getRed();
        int green = pixelObj.getGreen();
        int blue = pixelObj.getBlue();
        int x = pixelObj.getX();
        int y = pixelObj.getY();
        int picGreen = pic[y][x].getGreen();

        if( red == 0 && green == 0 && blue == 0 ) //if black
        {
          if( picGreen%2 != 1)
          {
            pic[y][x].setGreen(picGreen+1);
          }
        }
        else
        {
          if( picGreen%2 != 0 )
          {
            pic[y][x].setGreen(picGreen+1);
          }
        }
      }
    }
  }

  /** Your own customized method*/
  public void customized()
  {
    //add your code here
    Color white = new Color(255,255,255);
    Color black = new Color(0,0,0);

    Pixel topPixel = null;
    Pixel lowerPixel = null;
      
    double topIntensity;
    double lowerIntensity;

    double threshold = 10;
    for(int y = 0; y < this.getHeight()-1; y++){
      for(int x = 0; x < this.getWidth(); x++){

        topPixel = this.getPixel(x,y);
        lowerPixel = this.getPixel(x,y+1);

        topIntensity =  (topPixel.getRed() + topPixel.getGreen() + topPixel.getBlue()) / 3;
        lowerIntensity =  (lowerPixel.getRed() + lowerPixel.getGreen() + lowerPixel.getBlue()) / 3;

        if(Math.abs(topIntensity - lowerIntensity) < threshold)
          topPixel.setColor(white);
        else
          topPixel.setColor(black);
      }
    }
  }
}
