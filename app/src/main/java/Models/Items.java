package Models;


 enum Category {
    Tv,
     Moblies,
     Ac,
     washingMachine;
}
public class Items {

    private  int id;
    private  String name;
    private int qty;
    private String category;
    private String description;
    private int price;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected = true;

    public Items(int id, String name, String description, int price, int qty, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category= category;
        this.qty=qty;

    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
