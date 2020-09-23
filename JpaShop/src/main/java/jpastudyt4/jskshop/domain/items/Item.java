package jpastudyt4.jskshop.domain.items;

import jpastudyt4.jskshop.Exception.NotEnoughStockException;
import jpastudyt4.jskshop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /** 비즈니스 로직 **/
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    protected void setId(Long id){
        this.id = id;
    }

    protected void setName(String name){
        this.name = name;
    }

    protected void setPrice(int price){
        this.price = price;
    }

    protected void setStockQuantity(int stockQuantity){
        this.stockQuantity = stockQuantity;
    }
}
