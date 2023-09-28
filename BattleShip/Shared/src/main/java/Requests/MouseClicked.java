package Requests;

import Responses.Response;

public class MouseClicked extends  Request{
    private final int i;
    private final int j;

    public MouseClicked(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public Response takeAct(RequestHandler requestHandler) {
        return requestHandler.cellClicked(i, j);
    }
}
