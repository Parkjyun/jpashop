package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//읽기만 가능
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional//transanctional의 기본값은 readonly=false이렇게 따로 annotaion적어줘야지 저장 가능
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional//for 변경감지    ,   cf.em.merge()는 이 메서드를 jpa가 만들어준 것이다
    public void updateItem(Long itemId, Book param) {
        Item findItem = itemRepository.findOne(itemId);//findItem은 Db에서 갓 나옴 고로 영속상태->transactional 커핏 -> jpa flush날림==영속성 컨텍스트에서 변경된 아이를 찾음, db에 쿼리를 날려 바꿔줌
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

    }
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
