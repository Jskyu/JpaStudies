package jpastudies.jskshop.domain.items;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Book extends Item{

    private String author;
    private String isbn;

    public static Book createBook(String name, int price, int stockQuantity, String author, String isbn){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return book;
    }
    public static Book createBookId(Long id, String name, int price, int stockQuantity, String author, String isbn){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return book;
    }
    public void modifiedBook(String name, int price, int stockQuantity, String author, String isbn){
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }

    protected void setAuthor(String author){
        this.author = author;
    }

    protected void setIsbn(String isbn){
        this.isbn = isbn;
    }


}
