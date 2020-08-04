package Barcode;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.XMLConstants;



/**
 * @author Nicky Tran
 * @version 25/07/2020: 1.1
 */

// TODO: 26/07/2020 may delete this later, may not be needed 
public abstract class BarcodeHandle implements Handle {

    private static final Logger LOGGER = Logger.getLogger(BarcodeHandle.class.getName());

    private Map<String, BarcodeGenerator> generators;

    public BarcodeHandle(){
        generators = new HashMap<>();
        generators.put("code128", new Barcode128());
    }

//    @Override
//    public void handle (FacesContext context) throws IOException{
//        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
//
//
//    }



}
