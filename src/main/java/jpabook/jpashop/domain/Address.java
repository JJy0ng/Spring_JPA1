package jpabook.jpashop.domain;

import lombok.Getter;


import javax.persistence.Embeddable;

@Embeddable //내장값
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //함부로 생성을 막음
    protected Address() {
    }
    //Setter를 제거해서 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만듬
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
