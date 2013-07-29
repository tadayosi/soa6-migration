package org.switchyard.quickstarts.webservice.consumer2.ws;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private static final long serialVersionUID = -4620754343715487457L;
    private Long id;
    private ArrayList<LineItem> lineItems;
    private String shipTo;
    
    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }
    
    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    public float getTotalPrice() {
        float totalPrice = 0;
        if (lineItems != null) {
            for (LineItem item : lineItems) {
                if (item.getPrice() != null) totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getShipTo() {
        return shipTo;
    }
    
    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("Order ID= " + this.id + "\nTotal Price=" + getTotalPrice() + "\nShip to=" + this.shipTo
                + "\nLine Items:\n");
        if (lineItems != null) {
            for (LineItem lineItem : lineItems) {
                builder.append("\t ID: " + lineItem.getId() + "\n");
                builder.append("\t Name: " + lineItem.getName() + "\n");
                builder.append("\t Price: " + lineItem.getPrice() + "\n");
            }
        } else {
            builder.append("\t There are no Line Items!\n");
        }
        
        return builder.toString();
    }
    
}
