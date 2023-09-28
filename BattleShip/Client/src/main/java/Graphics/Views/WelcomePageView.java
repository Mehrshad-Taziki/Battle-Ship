package Graphics.Views;

import Enums.Page;
import Listener.PageListener;
import javafx.fxml.FXML;

public class WelcomePageView {
    private PageListener listener;

    @FXML
    public void login() {
        listener.listen(Page.Login);
    }

    @FXML
    public void register() {
        listener.listen(Page.Register);
    }

    public void loadPageListener(PageListener pageListener) {
        this.listener = pageListener;
    }
}
