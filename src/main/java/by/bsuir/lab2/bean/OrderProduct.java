package by.bsuir.lab2.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class OrderProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = 2057842933940639445L;
    private int productId;

    private int quantity;

    private int price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return productId == that.productId && quantity == that.quantity && price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price);
    }
}
