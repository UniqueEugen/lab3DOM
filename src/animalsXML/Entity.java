package animalsXML;

import myLibrary.console.Console;

import java.util.UUID;

public class Entity {
    private UUID id;
    private String type;
    private double price;
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

    public double getPrice() {
        return price;
    }

    public String getSubspecies() {
        return subspecies;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("`","\n");
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }

    @Override
    public String toString() {
        Console.log( "id=" + getId()+"\nЖивотное [вид: " + getKind() + ", подвид: " + getSubspecies() +
                ", семейство: " + getType() + ", цена: " + getPrice() + "]\nОписание: "+getDescription());
        return null;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

}
