package Product;
import java.io.Serializable;

public class GeneralStationery extends Product implements Serializable {

    // attribute
    private String stationeryType;

    // constructor
    public GeneralStationery (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String stationeryType) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin);
        this.stationeryType = stationeryType;
    }

    // method getter
    public String getStationeryType() {
        return stationeryType;
    }

    @Override
    public String toString() {
        return "Basic Data ----------------------------------" +
                "\n" +
                "\nProduct ID       : " +
                "\nProduct Name     : " +
                "\nPrice            : " +
                "\nCurrent Quantity : " +
                "\nMaximum Quantity : " +
                "\nMinimum Quantity : " +
                "\nSpecific Data ----------------------------" +
                "\n" +
                "\nStationery Type  : ";
    }
}
