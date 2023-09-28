package Sender;

import Requests.Request;
import Responses.Response;

public interface RequestSender {
    Response sendRequest(Request request);
}
