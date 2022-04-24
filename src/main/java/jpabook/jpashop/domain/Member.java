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
    @Column(name = "member_id") //엔티티 식별자 이름 매핑
    private Long id;

    private String name;

    @Embedded //내장타입을 포함하였다는 의미
    private Address address;

    @OneToMany(mappedBy = "member") //회원 : 주문 = 1 : 다 (order테이블의 member 필드에 의해 매핑됨)
    private List<Order> orders = new ArrayList<>();
}
