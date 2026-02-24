import Product.*;
import javax.swing.*;

public class EditWindowForm extends JFrame {
    // ตัวแปรเหล่านี้ต้องตั้งชื่อให้ตรงกับ Field Name ในหน้าต่าง UI Designer
    private JPanel mainPanel;
    private JTextField editName;
    private JTextField editPrice;
    private JTextField editMax;
    private JTextField editMin;
    private JButton btnConfirm;
    private JButton btnCancel;

    private MainWindowForm mainWindow;
    private Management management;
    private String productId;

    // Constructor รับค่าหน้าต่างหลัก, คลังสินค้า และ รหัสสินค้าที่ต้องการแก้
    public EditWindowForm(MainWindowForm mainWindow, Management management, String productId) {
        this.mainWindow = mainWindow;
        this.management = management;
        this.productId = productId;

        setTitle("Edit Product - " + productId);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack(); // ให้หน้าต่างปรับขนาดพอดีกับข้อมูล
        setLocationRelativeTo(null); // ให้อยู่กลางจอ

        // ==========================================
        // 1. ดึงข้อมูลเดิมมาแสดงในช่อง (Pre-fill) จะได้รู้ว่าแก้ของเก่าคืออะไร
        // ==========================================
        Product p = management.findProduct(productId);
        if (p != null) {
            editName.setText(p.getProductName());
            editPrice.setText(String.valueOf(p.getProductPrice()));
            editMax.setText(String.valueOf(p.getProductMax()));
            editMin.setText(String.valueOf(p.getProductMin()));
        }

        // ==========================================
        // 2. การทำงานของปุ่ม Cancel
        // ==========================================
        btnCancel.addActionListener(e -> dispose()); // กดแล้วปิดหน้าต่างทิ้งเลย

        // ==========================================
        // 3. การทำงานของปุ่ม Confirm
        // ==========================================
        btnConfirm.addActionListener(e -> {
            String nameStr = editName.getText().trim();
            String priceStr = editPrice.getText().trim();
            String maxStr = editMax.getText().trim();
            String minStr = editMin.getText().trim();

            // เช็คว่าปล่อยช่องว่างไว้ไหม
            if (nameStr.isEmpty() || priceStr.isEmpty() || maxStr.isEmpty() || minStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // แปลงข้อความเป็นตัวเลข
                double newPrice = Double.parseDouble(priceStr);
                int newMax = Integer.parseInt(maxStr);
                int newMin = Integer.parseInt(minStr);

                // ดักจับเงื่อนไขความถูกต้อง (Validation)
                if (newPrice < 0 || newMax < 0 || newMin < 0) {
                    JOptionPane.showMessageDialog(this, "Numbers cannot be negative!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (newMax < newMin) {
                    JOptionPane.showMessageDialog(this, "Max cannot be less than Min!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // สั่งให้ Management แก้ไขข้อมูล (มันจะเซฟลง .dat ให้อัตโนมัติด้วย)
                management.editProduct(productId, nameStr, newPrice, newMin, newMax);

                // รีเฟรชตารางที่หน้าหลัก เพื่อโชว์ข้อมูลใหม่
                mainWindow.updateTable();

                // ปิดหน้าต่างนี้ทิ้ง
                dispose();

            } catch (NumberFormatException ex) {
                // ดักจับคนพิมพ์ตัวหนังสือในช่องตัวเลข
                JOptionPane.showMessageDialog(this, "Invalid number format!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}