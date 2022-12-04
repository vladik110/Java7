package javafxapplication;

import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    static {
        // Установка формата вывода для java.util.logging.SimpleFormatter
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");
    }
    // Объявление логировцика типа org.apache.log4j.Logger
    static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(FXMLDocumentController.class);
    
    @FXML
    private Button button_Math;
    @FXML
    private Label label_a;
    @FXML
    private Label label_b;
    @FXML
    private Label label_x;
    @FXML
    private Label label_otvet;
    @FXML
    private TextField textField_A;
    @FXML
    private TextField textField_B;
    @FXML
    private TextField textField_X;
    @FXML
    private Label label_d;
    @FXML
    private TextField textField_D;
    @FXML
    private Button button_Clear;
    @FXML
    private Button button_Exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void buttonMathAction(ActionEvent event) {
        
        log4j.info("Start log4j");
        
        double a, b, x, y;
        try {
            a = Double.parseDouble(textField_A.getText());
            b = Double.parseDouble(textField_B.getText());
            x = Double.parseDouble(textField_X.getText());
            
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Ошибка введенных данных!", "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
            textField_A.requestFocus();
            label_otvet.setText("В введенных значениях допущены ошибки");
            textField_A.setText("");
            textField_B.setText("");
            textField_X.setText("");
            log4j.warn("Ошибка в введенных данных!");
            return;
        }
        if (x >= 4) {
            y = ((x + (4 * a)) / (a * a) * (b * b));
        } else {
            y = (x * x * x) - (a * b);
        }
        if (!(Double.isNaN(y)) && (!Double.isInfinite(y))) {
            label_otvet.setText("Ответ: " + String.format("%.2f", y));
        } else {
            label_otvet.setText("Нет ответа");
            log4j.error("Error!");
            log4j.fatal("Fatal error!");
        }
        
    }

    @FXML
    private void buttonClearAction(ActionEvent event) {
        log4j.info("Start clean!");
        label_otvet.setText("Ответ: ");
        textField_A.setText("");
        textField_B.setText("");
        textField_X.setText("");
        log4j.info("End clean!");
    }

    @FXML
    private void buttonExitAction(ActionEvent event) {
        log4j.info("End log4j");
        org.apache.log4j.LogManager.shutdown();
        System.exit(0);
    }

}
