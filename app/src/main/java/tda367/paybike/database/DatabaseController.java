package tda367.paybike.database;

import java.util.Map;

public class DatabaseController {

    public void addToDatabase(String collection, String id, Map documentMap) {

    }

    public void addToDatabase(String collection, Map documentMap) {

    }

    public String readFromDatabase(String collection, String documentID, String field) {

        return "";
    }

    public void deleteFromDatabase(String collection, String documentID) {

    }

    // placeholder
    public static DatabaseController getInstance() {
        return new DatabaseController();
    }
}
