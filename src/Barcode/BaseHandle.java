package Barcode;


import javax.faces.context.ExternalContext;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


//I have used this code because it will get the time and date that the barcode was created on
// TODO: 26/07/2020 may delete this later, may not be needed 
public abstract class BaseHandle implements Handle {

    public void dateTimeGet(ExternalContext externalContext){
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now());
    }

    public static void main(String[] args) {
//        System.out.println();
        System.out.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now()));
    }

}
