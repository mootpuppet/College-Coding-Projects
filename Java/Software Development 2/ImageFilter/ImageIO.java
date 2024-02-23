/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class ImageIO
 * Name: mottc
 * Created 2/7/2023
 */
package mottc;

import edu.msoe.cs1021.ImageUtil;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


import java.io.*;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * ImageIO purpose: Reading and writing images
 *
 * @author mottc
 * @version created on 2/7/2023 at 9:40 AM
 */
public class ImageIO {


    /**
     * Reads in the specified image file and returns a javafx.scene.image.Image object containing the image
     * @param path - path of file to be read
     * @throws IllegalArgumentException
     */
    public static Image read(Path path) throws IllegalArgumentException {

       Image image;

       String fileName = path.getFileName().toString();
       String[] splitFileName = fileName.split("\\.");
       String extn = splitFileName[1];

       if(extn.equalsIgnoreCase("msoe")){
           image= readMSOE(path);
       }
       else if(extn.equalsIgnoreCase("bmsoe")){
           image = readBMSOE(path);
       }

       else {

           try {

               image = ImageUtil.readImage(path);
           } catch (IOException e) {
               throw new IllegalArgumentException("Invalid File Type");
           }
       }

        return image;
    }

    /**
     * Reads an image file in .msoe format.
     * @param path - path of file to be read
     * @throws IllegalArgumentException
     */
    private static Image readMSOE(Path path) throws IllegalArgumentException{

        WritableImage msoeImage;
        PixelWriter pixelWriter;

        try (Scanner in = new Scanner(path)){
            in.nextLine();
            int width= Integer.parseInt(in.next());
            int height= Integer.parseInt(in.next());
            msoeImage = new WritableImage(width,height);
            pixelWriter = msoeImage.getPixelWriter();
            String[][] colorStrings= new String[height][width];

            for (int i = 0; i < height; i++){
                for(int j=0; j<width;j++){
                    colorStrings[i][j]=in.next();
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    try{
                        pixelWriter.setColor(i, j, stringToColor(colorStrings[j][i]));
                    }catch (InputMismatchException e){
                        throw new IllegalArgumentException("Error reading pixel at ("+j+","+i+")");
                    }
                }
            }
        }  catch (NumberFormatException e){

            throw new IllegalArgumentException("Invalid input on line 2 of .msoe file");

        }catch (IOException e) {
            throw new IllegalArgumentException("Invalid Path");
        }



        return msoeImage;
    }

    /**
     * reads an image file in the .bsmoe format
     * @param path - path to image file
     * @return - Image
     * @throws IllegalArgumentException
     */
    private static Image readBMSOE(Path path)throws IllegalArgumentException{

        WritableImage bmsoeImage;
        PixelWriter pixelWriter;

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path.toFile())))) {
            for(int i=0; i<5 ; i++){
                in.readByte();
            }
            int width = in.readInt();
            int height = in.readInt();
            bmsoeImage = new WritableImage(width, height);
            pixelWriter = bmsoeImage.getPixelWriter();
            int[][] colorInts= new int[height][width];

            for (int i = 0; i < height; i++){
                for(int j=0; j<width;j++){
                    colorInts[i][j]=in.readInt();
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    try{
                        pixelWriter.setColor(i, j, intToColor(colorInts[j][i]));
                    }catch (InputMismatchException e){
                        throw new IllegalArgumentException("Error reading pixel at ("+j+","+i+")");
                    }
                }
            }

        }catch (IOException e){
            throw new IllegalArgumentException("improper file format");

        }

        return bmsoeImage;
    }//end readBMSOE

    /**
     * Writes the specified image to the specified path.
     * @param path - path where the file will be written
     * @param image - image to be written to a file
     * @throws IllegalArgumentException
     */
    public static void write(Path path, Image image) throws IllegalArgumentException{

        String fileName = path.getFileName().toString();
        String[] splitFileName = fileName.split("\\.");
        String extn = splitFileName[1];

        if(extn.equalsIgnoreCase("msoe")){
            writeMSOE(path,image);
        }
        else if(extn.equalsIgnoreCase("bmsoe")){
            writeBMSOE(path,image);
        }
        else {

            try {
                ImageUtil.writeImage(path, image);
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid File Type");
            }
        }

    }//end write

    /**
     * writes an image to the specified path in the .bmsoe file format
     * @param path - saved file path
     * @param image - image to be saved
     * @throws IllegalArgumentException
     */
    private static void writeBMSOE(Path path, Image image) throws IllegalArgumentException {
        PixelReader pixelReader = image.getPixelReader();

        try (DataOutputStream out =new DataOutputStream(new FileOutputStream(path.toFile()))){

            out.writeBytes("BMSOE");
            out.writeInt((int)image.getWidth());
            out.writeInt((int)image.getHeight());

            for(int i=0; i< image.getHeight();i++){
                for (int j=0;j< image.getWidth();j++){
                    out.writeInt(colorToInt(pixelReader.getColor(j,i)));
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid path");
        }
    }//end writeBMSOE

    /**
     * Writes an image file in .msoe format.
     * @param path - path where file will be written to
     * @param image - image to be saved as a .msoe image file
     * @throws IllegalArgumentException
     */
    private static void writeMSOE(Path path, Image image) throws IllegalArgumentException{

        PixelReader pixelReader = image.getPixelReader();

        try (PrintWriter printWriter= new PrintWriter(path.toFile())){

            printWriter.println("MSOE");
            printWriter.println(""+(int)image.getWidth()+"\s"+(int)image.getHeight());

            for(int i=0; i< image.getHeight();i++){
                if (i!=0) {
                    printWriter.println("");
                }
                for (int j=0;j< image.getWidth();j++){
                    printWriter.print(colorToHex(pixelReader.getColor(j,i)));
                    if(j!=image.getWidth()-1){
                        printWriter.print("  ");
                    }

                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid path");
        }


    }//end writeMSOE

    /**
     * takes a hexadecimal string representation of a color and converts it to
     * a Color object
     * @param colorHex - color code as a String in hex format
     * @return - Color Object
     * @throws InputMismatchException - thrown if String is in the incorrect format
     */
    private static Color stringToColor(String colorHex) throws InputMismatchException {

        Character firstChar =colorHex.charAt(0);

        if(!firstChar.equals('#')){
            throw new InputMismatchException("Color Hex Code Incorrect format - missing '#'");
        }//end if

        else {
            try {
                return Color.valueOf(colorHex);
            } catch (NumberFormatException e) {

                throw new InputMismatchException("Color Hex Code Incorrect format");
            }//end try catch
        }//end else

    }//end stringToColor


    /**
     * converts a Color Object into a hexadecimal String
     * @param color - Color
     * @return - hexadecimal
     */
    public static String colorToHex(Color color){

        return ("#"+ intTo8BitHex((int)(color.getRed()*255))+
                intTo8BitHex((int)(color.getGreen()*255))+
                intTo8BitHex((int)(color.getBlue()*255))+
                intTo8BitHex((int)(color.getOpacity()*255)));

    }//end colorToHex

    /**
     * converts an int into an 8-bit hex string
     * @param num - integer
     * @return - hexadecimal String
     */
    private static String intTo8BitHex(int num){
        String hex;

        if(num<16){
            hex="0"+Integer.toHexString(num).toUpperCase();
        }
        else {
            hex=Integer.toHexString(num).toUpperCase();
        }

        return hex;

    }//end intTo8BitHex

    /**
     * parses an int into a Color object
     * @param color - int representation of a color
     * @return - Color object
     */
    private static Color intToColor(int color) {
        double red = ((color >> 16) & 0x000000FF)/255.0;
        double green = ((color >> 8) & 0x000000FF)/255.0;
        double blue = (color & 0x000000FF)/255.0;
        double alpha = ((color >> 24) & 0x000000FF)/255.0;
        return new Color(red, green, blue, alpha);
    } //end intToColor

    /**
     * parses a Color object into an int
     * @param color - Color object
     * @return - int representation of a color
     */
    private static int colorToInt(Color color) {
        int red = ((int)(color.getRed()*255)) & 0x000000FF;
        int green = ((int)(color.getGreen()*255)) & 0x000000FF;
        int blue = ((int)(color.getBlue()*255)) & 0x000000FF;
        int alpha = ((int)(color.getOpacity()*255)) & 0x000000FF;
        return (alpha << 24) + (red << 16) + (green << 8) + blue;
    }//end colorToInt


}//end ImageIO
