package Graphics.Views;

import Listener.AuthListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class RegisterPageView {
    private AuthListener listener;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;


    public void resetFields(){
        passwordTextField.setText("");
        confirmPasswordTextField.setText("");
        usernameTextField.setText("");
    }
    @FXML
    void register() {
        if (passwordTextField.getText().equals("") || usernameTextField.getText().equals("")) return;
        if (passwordTextField.getText().equals(confirmPasswordTextField.getText()))
            listener.listen(usernameTextField.getText(), passwordTextField.getText());
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText(null);
            alert.setContentText("Passwords Doesn't Match");
            alert.showAndWait();
        }
        resetFields();
    }

    public void setAuthListener(AuthListener listener) {
        this.listener = listener;
    }

}
