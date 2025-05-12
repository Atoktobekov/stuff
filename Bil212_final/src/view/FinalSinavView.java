package view;

import controller.GenerateReportController;
import controller.GetPlaneInfoController;
import dto.PlaneDTO;

import javax.swing.*;
import java.awt.*;

public class FinalSinavView extends JFrame {
    private JTextField fileNameField;
    private JButton getInfoButton;
    private JButton reportButton;
    private JRadioButton byNationality;
    private JRadioButton byGender;
    private JTextArea theoryTextArea;

    private PlaneDTO planeInfo;

    private final GetPlaneInfoController getController;
    private final GenerateReportController reportController;

    public FinalSinavView(GetPlaneInfoController getController, GenerateReportController reportController) {
        this.getController = getController;
        this.reportController = reportController;

        setTitle("BIL-212 Final Project");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 Top Panel (File input + button)
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Filename:"));
        fileNameField = new JTextField(20);
        topPanel.add(fileNameField);

        getInfoButton = new JButton("Get plane info");
        topPanel.add(getInfoButton);

        add(topPanel, BorderLayout.NORTH);

        // 🔹 Center Panel (Radio buttons + report button)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        byNationality = new JRadioButton("By Nationality", true);
        byGender = new JRadioButton("By Gender");

        ButtonGroup group = new ButtonGroup();
        group.add(byNationality);
        group.add(byGender);

        centerPanel.add(byNationality);
        centerPanel.add(byGender);

        reportButton = new JButton("Report");
        centerPanel.add(reportButton);

        add(centerPanel, BorderLayout.CENTER);

        // 🔹 Bottom Panel (Theory answer area)
        theoryTextArea = new JTextArea(10, 50);
        theoryTextArea.setText("Теориялык суроо: \nOpen/Close принципке мисал бул программада -\nСервис жана контроллер класстары жаңы функция кошкондо өзгөртүлбөстөн кеңейтиле алат.");
        JScrollPane scrollPane = new JScrollPane(theoryTextArea);

        add(scrollPane, BorderLayout.SOUTH);

        // 🔹 Add Listeners
        getInfoButton.addActionListener(e -> {
            String fileName = fileNameField.getText();
            planeInfo = getController.getPlaneInfoFromFile(fileName);
            if (planeInfo != null) {
                JOptionPane.showMessageDialog(this, "Plane data loaded successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        reportButton.addActionListener(e -> {
            if (planeInfo == null) {
                JOptionPane.showMessageDialog(this, "Load plane info first.");
                return;
            }
            String groupBy = byNationality.isSelected() ? "nationality" : "gender";
            reportController.generateReport(planeInfo, groupBy);
            JOptionPane.showMessageDialog(this, "Report generated to output.txt");
        });

        setVisible(true);
    }

    public PlaneDTO getPlaneInfo() {
        return planeInfo;
    }
}
