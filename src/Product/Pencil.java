package Product;
import java.io.Serializable;

public class Pencil extends WritingTool implements Serializable {

    // attribute
    private String grade;

    // constructor
    public Pencil (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String color, String grade) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, color);
        this.grade = grade;
    }

    // method getter
    public String getGrade() {
        return grade;
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
                "\nGrade            : ";
    }
}
