package Stream;

import java.io.Serializable;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.2
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Luggage implements Serializable {

    private String boardPassNumber;
    private String barcodeNumber;
    private String type;
    private double weight;
    private String colour;
    private int dimension[];
    private String fragile;
    private String excess;

    public Luggage(String boardPassNumber, String barcodeNumber, String type, double weight, String colour, int[] dimension, String fragile, String excess) {
        this.boardPassNumber = boardPassNumber;
        this.barcodeNumber = barcodeNumber;
        this.type = type;
        this.weight = weight;
        this.colour = colour;
        this.dimension = dimension;
        this.fragile = fragile;
        this.excess = excess;
    }

    // Getter methods
    public String getBoardPassNumber() {
        return boardPassNumber;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String getColour() {
        return colour;
    }

    public int[] getDimension() {
        return dimension;
    }

    public String getFragile() {
        return fragile;
    }

    public String getExcess() {
        return excess;
    }

}
