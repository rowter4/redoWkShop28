package sg.edu.nus.iss.workshop27Redo.models;
import jakarta.json.JsonObject;

public class LineItem {
    
    private Integer itemId;
    private String description;
    private double unitPrice;
    private Integer quantity;

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public String getDescription() { return description;}
    public void setDescription(String description) { this.description = description; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public static LineItem create(JsonObject o) {

        final LineItem li = new LineItem();
        li.setDescription(o.getString("description"));
        li.setQuantity(o.getInt("quantity"));
        li.setUnitPrice((double)o.getJsonNumber("unitPrice").doubleValue());

        return li;

    }
}
