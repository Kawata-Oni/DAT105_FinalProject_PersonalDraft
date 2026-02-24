package Product;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.io.*;

public class Management {

    private ArrayList<Product> products;

    public Management() {
        loadData();
    }

    // กลุ่มจัดการสินค้า (Add / Edit) ==============================================================

    public boolean addProduct(Product newProduct) {
        if (checkProductId(newProduct.getProductId())) {
            // แจ้งเตือน Error แบบ Popup (ไอคอนกากบาทสีแดง)
            JOptionPane.showMessageDialog(null,
                    "Product ID: " + newProduct.getProductId() + " has been added",
                    "Duplicate Product ID", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        products.add(newProduct);
        // แจ้งเตือนสำเร็จ แบบ Popup (ไอคอนตัว i สีฟ้า)
        JOptionPane.showMessageDialog(null,
                "add " + newProduct.getProductName() + " succceed!",
                "succeed", JOptionPane.INFORMATION_MESSAGE);

        warnLowStock(newProduct);

        saveData();

        return true;
    }

    public void editProduct(String productId, String newName, double newPrice, int newMin, int newMax) {
        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "ไม่พบสินค้ารหัส " + productId + " สำหรับการแก้ไข",
                    "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return;
        }

        p.setProductName(newName);
        p.setProductPrice(newPrice);
        p.setProductMin(newMin);
        p.setProductMax(newMax);

        JOptionPane.showMessageDialog(null,
                "แก้ไขข้อมูลสินค้ารหัส " + productId + " เรียบร้อยแล้ว",
                "สำเร็จ", JOptionPane.INFORMATION_MESSAGE);

        saveData();
    }

    // กลุ่มจัดการสต็อก (Inventory Control) ==============================================================

    public void increaseProductQuantity(String productId, int addedQuantity) {
        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "ไม่พบสินค้ารหัส " + productId,
                    "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newQty = p.getProductQuantity() + addedQuantity;

        if (newQty > p.getProductMax()) {
            // แจ้งเตือน Warning แบบ Popup (ไอคอนตกใจสีเหลือง)
            JOptionPane.showMessageDialog(null,
                    "ไม่สามารถเติมได้ จำนวนจะเกินความจุสูงสุด (" + p.getProductMax() + " ชิ้น)",
                    "แจ้งเตือนสต็อก", JOptionPane.WARNING_MESSAGE);
            return;
        }

        p.setProductQuantity(newQty);
        JOptionPane.showMessageDialog(null,
                "เติมสต็อกสำเร็จ: " + p.getProductName() + " มีจำนวน " + newQty + " ชิ้น",
                "สำเร็จ", JOptionPane.INFORMATION_MESSAGE);

        saveData();
    }

    public void decreaseProductQuantity(String productId, int removedQuantity) {
        Product p = findProduct(productId);

        if (p == null) {
            JOptionPane.showMessageDialog(null,
                    "ไม่พบสินค้ารหัส " + productId,
                    "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (p.getProductQuantity() >= removedQuantity) {
            int newQty = p.getProductQuantity() - removedQuantity;
            p.setProductQuantity(newQty);
            JOptionPane.showMessageDialog(null,
                    "ขายสินค้าสำเร็จ: ตัดสต็อกไป " + removedQuantity + " ชิ้น",
                    "สำเร็จ", JOptionPane.INFORMATION_MESSAGE);

            warnLowStock(p);

            saveData();

        } else {
            JOptionPane.showMessageDialog(null,
                    "จำนวนสินค้าไม่เพียงพอ (มีแค่ " + p.getProductQuantity() + " ชิ้น)",
                    "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void warnLowStock(Product p) {
        // ถ้า qty ปัจจุบันน้อยกว่า min
        if (p.getProductQuantity() <= p.getProductMin()) {
            JOptionPane.showMessageDialog(null,
                    "แจ้งเตือนสินค้าเหลือน้อย!\n" +
                            "รหัสสินค้า: " + p.getProductId() + "\n" +
                            "ชื่อสินค้า: " + p.getProductName() + "\n" +
                            "คงเหลือ: " + p.getProductQuantity() + " ชิ้น",
                    "แจ้งเตือนสต็อก", JOptionPane.WARNING_MESSAGE);
        }
    }

    // กลุ่มเครื่องมือตรวจสอบ (find / check) ==============================================================

    public Product findProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public boolean checkProductId(String productId) {
        return findProduct(productId) != null;
    }

    // กลุ่มทำงานกับไฟล์และตาราง ==============================================================

    public ArrayList<Product> getProducts() {
        return products;
    }

    private void saveData() {
        try (FileOutputStream fos = new FileOutputStream("products.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (FileInputStream fis = new FileInputStream("products.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.products = (ArrayList<Product>) ois.readObject();
        } catch (Exception e) {
            this.products = new ArrayList<Product>();
        }
    }
}
