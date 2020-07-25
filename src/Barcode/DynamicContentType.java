package Barcode;

/**
 * This class has been created using the code found here:
 * https://github.com/primefaces/primefaces/blob/master/src/main/java/org/primefaces/application/resource/DynamicContentType.java
 * There are 2 authors: Thomas Andraschko and mertsincan
 * This code uploaded to Github on 16/06/2020.
 * <p>
 * This class will return toString
 */
public enum DynamicContentType {

    Barcode("barcode");

    private String toString;

    DynamicContentType(String toString){
        this.toString = toString;
    }

    DynamicContentType(){
    }

        @Override
    public String toString(){
        return ((this.toString != null) ? this.toString : super.toString());
    }

}
