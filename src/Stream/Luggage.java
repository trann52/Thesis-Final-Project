package Stream;

import java.io.Serializable;
import java.sql.Array;

/**
 * @author Nicky Tran
 * @version 01/08/2020: 1.3
 * This class implements the Serializable interface because any serialised object
 * can be read to a file. When the file is deserialised, the data can be recreatead.
 */
public class Luggage implements Serializable {

    private String boardPassNumber;
    private String barcodeNumber;
    private String type;
    private String weight;
    private String colour;
    private String dimension;
    private String fragile;
    private String excess;
    private String location;

    // -----------------------------------------------------------------------------------------------------------------
    // constructors

    public Luggage(String boardPassNumber, String barcodeNumber, String type, String weight, String colour, String dimension, String fragile, String excess, String location) {
        this.boardPassNumber = boardPassNumber;
        this.barcodeNumber = barcodeNumber;
        this.type = type;
        this.weight = weight;
        this.colour = colour;
        this.dimension = dimension;
        this.fragile = fragile;
        this.excess = excess;
        this.location = location;
    }

    public Luggage(String boardPassNumber, String barcodeNumber, String type, String weight, String colour, String dimension, String fragile, String excess) {
        this.boardPassNumber = boardPassNumber;
        this.barcodeNumber = barcodeNumber;
        this.type = type;
        this.weight = weight;
        this.colour = colour;
        this.dimension = dimension;
        this.fragile = fragile;
        this.excess = excess;
    }

    public Luggage(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public Luggage(String barcodeNumber, String location) {
        this.barcodeNumber = barcodeNumber;
        this.location = location;
    }

    public Luggage(String type, String weight, String colour, String dimension, String fragile, String excess) {
        this.type = type;
        this.weight = weight;
        this.colour = colour;
        this.dimension = dimension;
        this.fragile = fragile;
        this.excess = excess;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // getter methods


    public String getBoardPassNumber() { return boardPassNumber; }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public String getColour() {
        return colour;
    }

    public String getDimension() {
        return dimension;
    }

    public String getFragile() {
        return fragile;
    }

    public String getExcess() {
        return excess;
    }

    public String getLocation() { return location; }
}
