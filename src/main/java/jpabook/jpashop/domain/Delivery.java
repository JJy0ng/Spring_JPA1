package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Setter @Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL -> 숫자 중간에 다른 값이 오면 숫자가 밀림(문제 발생), STRING을 써야 중간에 들어와서 순서에 밀리는게 없음
    private DeliveryStatus status; //READY, CAMP
}
