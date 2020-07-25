package Barcode;


import javax.faces.context.FacesContext;
import java.io.IOException;

//Creating a interface which uses the FacesContent library
public interface Handle {
    void handle(FacesContext facesContext) throws IOException;
}
