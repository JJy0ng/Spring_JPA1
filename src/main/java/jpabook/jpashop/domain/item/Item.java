package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 관계의 전략을 지정해야함(부모클래스)
@DiscriminatorColumn(name = "dtype")
@Setter @Getter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private  int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> items = new ArrayList<>();

}
