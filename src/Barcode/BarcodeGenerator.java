package Barcode;

import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.CanvasProvider;

import java.io.IOException;

/**
 * This class has been created using the code found here:
 * https://github.com/primefaces/primefaces/blob/master/src/main/java/org/primefaces/application/resource/barcode/BarcodeGenerator.java
 * There are 4 authors: Thomas Andraschko, Cagatay Civici, mertsincan, Melloware
 * This code uploaded to github on 16/06/2020.
 *
 * I have used this code because it will create a barcode generator.
 */
public abstract class BarcodeGenerator {

    private AbstractBarcodeBean barcodeBean;

    public BarcodeGenerator(){
        super();
    }

    public BarcodeGenerator(AbstractBarcodeBean bean){
        this();
        setBarcodeBean(bean);
    }

    public void generate(CanvasProvider canvasProvider, String value) throws IOException {
        getBarcodeBean().generateBarcode(canvasProvider, value);
    }


    public AbstractBarcodeBean getBarcodeBean(){
        return barcodeBean;
    }

    public void setBarcodeBean(AbstractBarcodeBean barcodeBean){
        this.barcodeBean = barcodeBean;
    }






}
