package Graphics.Views;

import Listener.AuthListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginPageView {
    private AuthListener listener;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    public void resetFields() {
        passwordTextField.setText("");
        usernameTextField.setText("");
    }

    @FXML
    void login() {
        if (passwordTextField.getText().equals("") || usernameTextField.getText().equals("")) return;
        listener.listen(usernameTextField.getText(), passwordTextField.getText());
        resetFields();
    }

    public void setAuthListener(AuthListener listener) {
        this.listener = listener;
    }

}
