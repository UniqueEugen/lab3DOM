public class Entity {
    private int id;
    private String type;
    private int price;
    private String kind;
    private String description;
    private String subspecies;

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }

    public String getSubspecies() {
        return subspecies;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }

    @Override
    public String toString() {
        return "Животное [id=" + getId() + ", вид: " + getKind() + ", подвид: " + getSubspecies() +
                ", семейство: " + getType() + ", цена: " + getPrice() + "]\nОписание: "+getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
