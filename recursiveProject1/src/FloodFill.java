import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
  Flood Fill Algorithmus
*/
public class FloodFill {

    // converts rgb image into an grayscale array

    private static int[][] convertImg2Arr(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int) (tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }
        }
        return imgArray;
    }

    private static void floodFill(int[][] imgArr, int sx, int sy, int gVal, int gThresh) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        double percentageDiff = Math.abs(((double)gVal - (double)imgArr[sy][sx] )) / (((double)gVal+(double)imgArr[sy][sx])/2) * 100;

        if (sx >= imgArr.length - 1 || sy >= imgArr[0].length - 1)
        return;
       if (percentageDiff >gThresh)
          return;
        StdDraw.point(sx, imgArr.length-sy);
        imgArr[sy][sx] = 1500;
        floodFill(imgArr, sx + 1, sy, gVal, gThresh);
        floodFill(imgArr, sx, sy + 1, gVal, gThresh);
        floodFill(imgArr, sx - 1, sy, gVal, gThresh);
        floodFill(imgArr, sx, sy - 1, gVal, gThresh);

    }


    public static void main(String[] args) {
        BufferedImage img = null;
        // try to open image file
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "earth.png"));
        } catch (IOException e) {
        }

        // set StdDraw window size based on the image size
        int width = img.getWidth();
        int height = img.getHeight();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        // convert the rgb image into a grayscale array
        int[][] myImage = convertImg2Arr(img);

        // draw grayscale image on the StdDraw window
        StdDraw.enableDoubleBuffering();
        for (int y = 0; y < myImage.length; y++) {
            for (int x = 0; x < myImage[y].length; x++) {
                StdDraw.setPenColor(myImage[y][x], myImage[y][x], myImage[y][x]);
                StdDraw.point(x, myImage.length - y);
            }
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please click on an area within the image!");
        StdDraw.setPenColor(StdDraw.RED);

        // start endless program for flood filling
        while (true) {
            if (StdDraw.isMousePressed()) {
                // mouse click detected - draw clicked point on image
                StdDraw.point(StdDraw.mouseX(), StdDraw.mouseY());
                int mouseX = (int) StdDraw.mouseX();
                int mouseY = myImage.length - (int) StdDraw.mouseY();

                // read grayscale value of clicked point and read user info for the grayscale threshold
                System.out.println("Grayvalue of the clicked position y: " + mouseY + " x: " + mouseX + " is: " + myImage[mouseY][mouseX]);
                System.out.println("Please type value between 1-50% for the grayscale threshold: ");
                int grayThreshold = myScanner.nextInt();
                grayThreshold = grayThreshold > 0 && grayThreshold <= 50 ? grayThreshold : 10;

                // start flood fill algorihtm with the current paramters

                floodFill(myImage, mouseX, mouseY, myImage[mouseY][mouseX], grayThreshold);
            }
        }
    }
}

