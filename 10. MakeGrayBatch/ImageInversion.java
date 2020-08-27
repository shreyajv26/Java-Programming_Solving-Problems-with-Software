/**
 * Convert any number of images to a gray scale version by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class ImageInversion {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //get inPixel's red + inPixel's blue + inPixel's green
            int redPixel = inPixel.getRed();
            int bluePixel = inPixel.getBlue();
            int greenPixel = inPixel.getGreen();
            //compute the new values of pixels
            int outRed = 255 - redPixel;
            int outBlue = 255 - bluePixel;
            int outGreen = 255 - greenPixel;
            //set pixel's red to its invert
            pixel.setRed(outRed);
            //set pixel's green to its invert
            pixel.setGreen(outGreen);
            //set pixel's blue to its invert
            pixel.setBlue(outBlue);
        }
        //outImage is your answer
        return outImage;
    }

    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
