import Product.Management;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindowForm extends JFrame {

    private JPanel mainPanel;
    private JRadioButton choicePencil;
    private JRadioButton choicePen;
    private JRadioButton choiceNotebook;
    private JRadioButton choiceReportPaper;
    private JRadioButton choiceGeneralStationery;
    private JButton btnConfirm;
    private JButton btnCancel;

    private MainWindowForm mainWindow;
    private Management management;

    public AddWindowForm(MainWindowForm mainWindow, Management management) {
        this.mainWindow = mainWindow;
        this.management = management;

        setTitle("Add Window");
        setContentPane(mainPanel);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ButtonGroup group = new ButtonGroup();
        group.add(choicePencil);
        group.add(choicePen);
        group.add(choiceNotebook);
        group.add(choiceReportPaper);
        group.add(choiceGeneralStationery);

        // ปุ่ม Cancel
        btnCancel.addActionListener(e -> dispose());

        // ปุ่ม Confirm
        btnConfirm.addActionListener(e -> {
            if (!choicePencil.isSelected() && !choicePen.isSelected() &&
                    !choiceNotebook.isSelected() && !choiceReportPaper.isSelected() &&
                    !choiceGeneralStationery.isSelected()) {

                JOptionPane.showMessageDialog(this, "Please select an item!");
                return;
            }

            if (choicePencil.isSelected()) {
                InputBasicData inputForm = new InputBasicData("Pencil", mainWindow, management);
                inputForm.setVisible(true);
            } else if (choicePen.isSelected()) {
                InputBasicData inputForm = new InputBasicData("Pen", mainWindow, management);
                inputForm.setVisible(true);
            } else if (choiceNotebook.isSelected()) {
                InputBasicData inputForm = new InputBasicData("Notebook", mainWindow, management);
                inputForm.setVisible(true);
            } else if (choiceReportPaper.isSelected()) {
                InputBasicData inputForm = new InputBasicData("Report Paper", mainWindow, management);
                inputForm.setVisible(true);
            } else if (choiceGeneralStationery.isSelected()) {
                InputBasicData inputForm = new InputBasicData("General Stationery", mainWindow, management);
                inputForm.setVisible(true);
            dispose();
        }
    })
;}}