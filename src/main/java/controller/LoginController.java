package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class LoginController extends Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordField;

    @FXML
    public void loginButtonAction() throws NoSuchAlgorithmException, IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.isEmpty()) {
            MessageAlert.showErrorMessage(null, "Introduceti un nume de utilizator!");
        }
        else if (password.isEmpty()) {
            MessageAlert.showErrorMessage(null, "Introduceti o parola!");
        }
        else if (service.isExistingUser(username, password)){
            Main.changeSceneToMainView();
        }
        else {
            MessageAlert.showErrorMessage(null, "Username sau parola sunt gresite!");
        }
    }
}
