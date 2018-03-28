package kz.sushi.dao.entity;

public class ProductType extends Basic {
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
