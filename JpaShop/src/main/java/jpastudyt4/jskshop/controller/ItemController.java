package jpastudyt4.jskshop.controller;

import jpastudyt4.jskshop.domain.items.Book;
import jpastudyt4.jskshop.domain.items.Item;
import jpastudyt4.jskshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createMember(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){

        Book book = Book.createBook
                (form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book book = (Book) itemService.findOne(itemId);
        BookForm form = BookForm.createBookForm
                (book.getId(), book.getName(), book.getPrice(), book.getStockQuantity(), book.getAuthor(), book.getIsbn());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, BookForm form){

        itemService.updateItem(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());

        return "redirect:/items";
    }
}
