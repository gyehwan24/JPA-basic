package hellojpa.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "delivery")
    private Order orders;
}
