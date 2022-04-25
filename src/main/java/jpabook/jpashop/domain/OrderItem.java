package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //해당 클래스에 대한 새로운 생성자를 별도 생성하는 것을 방지
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //주문상품의 수량이 증가하는 만큼 상품 재고가 감소한다
        return orderItem;
    }

    //비즈니스 로직
    public void cancel() { //주문 취소 로직
        getItem().addStock(count); //취소가 된 주문의 수량만큼 복구
    }

    //조회 로직
    public int getTotalPrice() { //주문 가격 * 주문 수량
        return getOrderPrice() * getCount();
    }
}
