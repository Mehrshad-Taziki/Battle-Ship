package Logics.Controllers;

import Graphics.Views.RegisterPageView;
import Listener.AuthListener;
import Logics.LogicalAgent;
import Requests.RegisterRequest;

public class RegisterPageController {
    private final RegisterPageView view;
    private final LogicalAgent logicalAgent;

    public RegisterPageController(RegisterPageView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadAuthListener();
    }

    private void loadAuthListener(){
        view.setAuthListener((username, password) -> logicalAgent.addRequest(new RegisterRequest(username, password)));
    }
}
