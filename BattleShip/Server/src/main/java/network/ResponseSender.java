package network;

import Requests.Request;
import Responses.Response;

public interface ResponseSender {
    Request getRequest();

    void sendResponse(Response response);

    void close();
}
