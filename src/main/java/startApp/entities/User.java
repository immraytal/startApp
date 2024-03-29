package startApp.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Transient
    private String matchingPassword;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
    orderList.add(order);
    order.setUser(this);
    }

    public void removeOrder(Order order){
        orderList.remove(order);
        order.setUser(null);
    }


}