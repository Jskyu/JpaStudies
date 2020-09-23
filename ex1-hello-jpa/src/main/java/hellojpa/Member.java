package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Annotation 정리
 *
 * @Colunm :  컬럼 매핑
 * @Temporal : 날짜 타입 매핑
 * @Enumerated: enum타입 매핑
 * @Lob :   BLob, CLob 매핑
 * @Trasient: 특정 필드를 컬럼에 매핑하지 않을때
 * 자세한 내용은 JPA기본/Entity Mapping/18Page
 **/

/**
 * 기본 Key Mapping
 *
 * @GeneratedValue - 자세한 내용은 JPA기본/Entitiy Mapping/26Page~38Page
 * - strategy = GenerationType.IDENTITY
 * - strategy = GenerationType.AUTO
 * - strategy = GenerationType.SEQUENCE
 * - strategy = GenerationType.TABLE
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    /*@Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;
    */

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public Address getHomeAddress(){
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress){
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods(){
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods){
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory(){
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory){
        this.addressHistory = addressHistory;
    }
}