package jskstudies.jpashop.service;

import jskstudies.jpashop.controller.BookForm;
import jskstudies.jpashop.model.item.Book;
import jskstudies.jpashop.model.item.Item;
import jskstudies.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findById(itemId).orElse(null);
    }

    @Transactional
    public void updateItem(BookForm form) {
        Book book = (Book) itemRepository.findById(form.getId()).orElse(null);

        assert book != null;
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
    }
}