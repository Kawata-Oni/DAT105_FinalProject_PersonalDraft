package Product;
import java.io.Serializable;

public class ReportPaper extends PaperProduct implements Serializable {

    // attribute
    private int numberOfSheets;

    // constructor
    public ReportPaper(String productId, String productName, double productPrice, int productQuantity, int productMax, int productMin, String size, int gsm, int numberOfSheets) {
        super(productId, productName, productPrice, productQuantity, productMax, productMin, size, gsm);
        this.numberOfSheets = numberOfSheets;
    }

    // method getter
    public int getNumberOfSheets() {
        return numberOfSheets;
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
                "\nNumber of Sheets : ";
    }
}
