import Product.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowForm {

    // attribute
    private JPanel main_form;
    private JPanel management_button;
    private JButton btnAdd;
    private JButton btnDecrease;
    private JButton btnEdit;
    private JButton btnIncrease;
    private JScrollPane basicInformation;
    private JTable basicData;
    private JButton btnFullData;
    private JButton btnRefresh;

    private JFrame frame;

    private Management management;

    // การทำงานใน Main
    public MainWindowForm() {
        management = new Management();
        frame = new JFrame("Stock Management System");

        setupTable();
        updateTable();

        if (main_form != null) {
            frame.setContentPane(main_form);
        } else {
            System.err.println("Error: main_form is null. Please check your GUI Designer binding.");
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null); // ให้อยู่กึ่งกลางหน้าจอ
        frame.setVisible(true);

        // ปุ่ม Add
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddWindowForm addFrame = new AddWindowForm(MainWindowForm.this, management);
                addFrame.setVisible(true);
            }
        });

        // ปุ่ม increase
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null,"Enter Product ID: ");
                if (id == null) return;
                int qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter increased quantity: "));
                management.increaseProductQuantity(id, qty);
            }
        });

        // ปุ่ม decrease
        btnDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null,"Enter Product ID: ");
                if (id == null) return;
                int qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter decreased quantity: "));
                management.decreaseProductQuantity(id, qty);
            }
        });

        // ปุ่ม edit
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. รับค่า ID จาก JOptionPane
                String idStr = JOptionPane.showInputDialog(frame, "Enter Product ID to Edit:");

                // ดักจับกรณีผู้ใช้กด Cancel หรือปล่อยว่าง
                if (idStr == null || idStr.trim().isEmpty()) {
                    return;
                }

                String productId = idStr.trim();

                // 2. ตรวจสอบว่ามี ID นี้ในระบบหรือไม่
                if (management.checkProductId(productId)) {
                    // ถ้ามี -> เปิดหน้าต่าง EditWindowForm แล้วส่ง ID ไปให้ด้วย
                    EditWindowForm editForm = new EditWindowForm(MainWindowForm.this, management, productId);
                    editForm.setVisible(true);
                } else {
                    // ถ้าไม่มี -> แจ้งเตือน Error
                    JOptionPane.showMessageDialog(frame,
                            "ไม่พบสินค้ารหัส: " + productId,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ปุ่ม full data
        btnFullData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Input Product ID: ");
                // use toString()
            }
        });

        // ปุ่ม refresh
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
    }

    // ตั้งค่าตาราง
    private void setupTable() {
        String[] columnNames = {"ID", "Name", "Price", "Quantity", "Max", "Min", "Status"};
        // ใช้ DefaultTableModel เพื่อให้จัดการข้อมูล (เพิ่ม/ลบ แถว) ได้ง่ายขึ้นในอนาคต
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        basicData.setModel(model);
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindowForm();
            }
        });
    }

    // ตั้งค่าข้อมูลในตาราง
    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) basicData.getModel();
        model.setRowCount(0);

        for (Product p : management.getProducts()) {
            Object[] rowData = {
                    p.getProductId(),
                    p.getProductName(),
                    p.getProductPrice(),
                    p.getProductQuantity(),
                    p.getProductMax(),
                    p.getProductMin(),
                    p.getProductStatus() ? "Available" : "Low Stock"
            };
            model.addRow(rowData);
        }
    }
}