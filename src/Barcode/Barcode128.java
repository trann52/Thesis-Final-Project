package Barcode;

import org.krysalis.barcode4j.impl.code128.Code128Bean;


/**
 * This class is based off:
 * https://github.com/primefaces/primefaces/blob/master/src/main/java/org/primefaces/application/resource/barcode/Code128Generator.java
 * There are 4 authors: Thomas Andraschko, Cagatay Civici, mertsincan and Melloware
 * This code uploaded to Github on 16/06/2020.
 *
 * I have used this code as it will create barcodes in the Code128 format.
 */
public class Barcode128 extends BarcodeGenerator {

    public Barcode128() {
        super(new Code128Bean());
    }

}