package tda367.paybike.handlers;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class RequestHandler {
    private static RequestHandler instance = null;

    private RequestHandler() {
        // Singleton
    }

    // Create singleton if it does not exist, otherwise return it.
    public static RequestHandler getInstance() {
        if (instance == null) {
            instance = new RequestHandler();
        }

        return instance;
    }
}
