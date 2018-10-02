package tda367.paybike.model;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class User {
    private String name;
    private String id;
    private String passWord;
    private Position position;

    public User(String id, String name, String passWord, Position position){
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.position = position;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassWord(String passWord){this.passWord = passWord; }
    public String getPassWord(){return passWord; }

    public void setPosition(Position position) {this.position = position;}
    public Position getPosition() {return position;}
}
