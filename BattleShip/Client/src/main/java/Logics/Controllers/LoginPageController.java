package Logics.Controllers;

import Graphics.Views.LoginPageView;
import Logics.LogicalAgent;
import Requests.LoginRequest;

public class LoginPageController {
    private final LoginPageView view;
    private final LogicalAgent logicalAgent;

    public LoginPageController(LoginPageView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadAuthListener();
    }

    private void loadAuthListener(){
        view.setAuthListener((username, password) -> logicalAgent.addRequest(new LoginRequest(username, password)));
    }
}
