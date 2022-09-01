package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;//주문 가격
    private int count;//주문 수량

    //orderservice에서 orderitem 생성법 createorderitem으로 통일 되어야 유지보수 쉬워짐 -> 생성자로 orderitem사용하지 못하게 하기 위해서
    protected OrderItem() {

    }

    //생성메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }
    //비즈니스 로직
    public void cancel() {
        getItem().addStock(count);//orderitem의 캔스 -> orderitem 클래스의 item갖고 와서 재고량을 늘려준다
    }

    //조회로직
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
