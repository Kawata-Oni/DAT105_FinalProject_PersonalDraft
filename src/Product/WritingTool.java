package Product;
import java.io.Serializable;

public abstract class WritingTool extends Product implements Serializable {

    // attribute
    private String color;

    // constructor
    public WritingTool (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String color) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin);
        this.color = color;
    }

    // method getter
    public String getColor() {
        return color;
    }

    @Override
    public abstract String toString();
}
