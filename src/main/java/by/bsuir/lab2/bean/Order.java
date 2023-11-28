package by.bsuir.lab2.bean;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 5269385212643196423L;

    private int id;

    private int userId;

    private Timestamp datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && Objects.equals(datetime, order.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, datetime);
    }
}
