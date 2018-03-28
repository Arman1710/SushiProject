package kz.sushi.dao.entity;


import java.io.Serializable;

public class Basic implements Serializable {
    private int id;

    @Override
    public String toString() {
        return "Basic{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
