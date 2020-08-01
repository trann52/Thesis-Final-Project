package Stream;

import java.io.Serializable;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.1
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Luggage implements Serializable {

    private String barcodeNumber;

    /**
     * The construstcor to initialise barcodeNumber
     * @param barcodeNumber the barcode associate with each piece of luggage
     */
    public Luggage(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

}
