package Product;
import java.io.Serializable;

public class Notebook extends PaperProduct implements Serializable {

    // attribute
    private int numberOfPages;

    // constructor
    public Notebook (String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String size, int gsm, int numberOfPages) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm);
        this.numberOfPages =numberOfPages;
    }

    // method getter
    public int getNumberOfPages() {
        return numberOfPages;
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
                "\nSize             : " +
                "\nGSM              : " +
                "\nNumber of Pages  : ";
    }
}
