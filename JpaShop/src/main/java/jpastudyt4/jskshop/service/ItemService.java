package jpastudyt4.jskshop.service;

import jpastudyt4.jskshop.domain.items.Book;
import jpastudyt4.jskshop.domain.items.Item;
import jpastudyt4.jskshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity, String author, String isbn){
        Book findItem = (Book) itemRepository.findOne(id);
        findItem.modifiedBook(name, price, stockQuantity, author, isbn);
    }


    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> findByName(String name){
        return itemRepository.findByName(name);
    }

}