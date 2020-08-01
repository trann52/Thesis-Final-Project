package Barcode;


import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * @author Nicky Tran
 * @version 25/07/2020: 1.1
 * This is an interface which uses the FacesContent library
 */

public interface Handle {
    void handle(FacesContext facesContext) throws IOException;
}
