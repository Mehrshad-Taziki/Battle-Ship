package Logics.Controllers;

import Enums.Page;
import Graphics.Views.WelcomePageView;
import Listener.PageListener;
import Logics.LogicalAgent;

public class WelcomePageController {
    private final WelcomePageView view;
    private final LogicalAgent logicalAgent;

    public WelcomePageController(WelcomePageView view, LogicalAgent logicalAgent) {
        this.view = view;
        this.logicalAgent = logicalAgent;
        loadPageListener();
    }

    private void loadPageListener(){
        view.loadPageListener(page -> {
            if (page == Page.Login) logicalAgent.getAgent().switchToLoginForm();
            if (page == Page.Register) logicalAgent.getAgent().switchToRegisterForm();
        });
    }

}
