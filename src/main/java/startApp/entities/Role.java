package startApp.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;
}