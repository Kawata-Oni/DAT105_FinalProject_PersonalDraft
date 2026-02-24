package Product;
import java.io.Serializable;

public class Pen extends WritingTool implements Serializable {

    // attribute
    private double tipSize;
    private String penType;

    // constructor
    public Pen (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String color, double tipSize, String penType) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, color);
        this.tipSize = tipSize;
        this.penType = penType;
    }

    // method getter
    public double getTipSize() {
        return tipSize;
    }
    public String getPenType() {
        return penType;
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
                "\nColor            : " +
                "\nTip Size         : " +
                "\nPen Type         : ";
    }
}
