package Barcode;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author Nicky Tran
 * @version 22/07/2020: 2.1
 * This class is based off:
 * http://naeemgik.blogspot.com/2013/10/generating-barcode-in-java-using.html
 * There is 1 author: naeemgik
 * This code uploaded on 07/10/2013
 *
 * I have used this code as it will create barcodes in the Code128 format.
 */
public class Barcode128  {


    private String barcodePath = "C:/Users/User1/Documents/coding/nxt930/src/Barcode/";

    public void generateBarcode128(int fileName){

        try{
                // initialising the bean
                Code128Bean code128 = new Code128Bean();
                final int dpi = 160;

                // the generation og the barcode is configured
                code128.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

                code128.doQuietZone(false);

                // the output file is opened
                File outputFile = new File(barcodePath + fileName + ".JPG");

                FileOutputStream out = new FileOutputStream(outputFile);

                BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

                // the barcode is generated
                code128.generateBarcode(canvas, String.valueOf(fileName));

                //the end of the generation is signalled
                canvas.finish();

                System.out.println("Barcode generated");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Barcode128 barcode = new Barcode128();
        barcode.generateBarcode128((int) Math.round(Math.random()*10000000000L));

    }




}