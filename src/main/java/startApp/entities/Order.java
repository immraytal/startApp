package startApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "order_price")
    private double orderPrice;

    @Column(name = "status")
    private String status;

}

