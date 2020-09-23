package jpastudyt4.jskshop.domain.items;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Movie extends Item{

    private String director;
    private String actor;
}
