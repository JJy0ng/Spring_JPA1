package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id") // PK값 설정
    private Long id;

    private String name;

    @Embedded //내장값을 포함했다는 뜻
    private Address address;

    @OneToMany(mappedBy = "member") //일대다, 매핑을 하는 것이 아니라 거울일뿐
    private List<Order> orders = new ArrayList<>();
}
