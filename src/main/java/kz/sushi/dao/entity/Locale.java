package kz.sushi.dao.entity;

public class Locale extends Basic {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
