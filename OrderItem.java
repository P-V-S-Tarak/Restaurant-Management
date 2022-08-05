
public class OrderItem extends MenuItem {
    private int itemQuantity;
    private String cookState;

    public OrderItem(MenuItem i, int itemQuantity, String category) {
        super(i.name, i.description, i.price * itemQuantity, -1, category);
        this.itemQuantity = itemQuantity;
        cookState = "Taken";

    }

    public void setCookState(String cookState) {
        this.cookState = cookState;

    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCookState() {
        return this.cookState;
    }

    @Override
    public String toString() {
        String s = name + " " + itemQuantity + " " + price;
        return s;
    }

    public boolean isServed() {
        if (this.cookState.equals("Served"))
            return true;
        else
            return false;
    }

}
