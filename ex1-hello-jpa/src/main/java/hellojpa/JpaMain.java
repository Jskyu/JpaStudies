package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){


        /**
         * em.persist() : 비영속성 컨텍스트을 영속성 컨텍스트로 만들어줌.
         * em.flush() : 1차 캐쉬에 있는 영속성 컨텍스트들을 DB에 저장해줌.(쿼리 발생)
         *              영속성 컨텍스트를 비우는것이 아니라 영속성 컨텍스트의 변경내용을 DB에 동기화 하는것임.
         *
         * 준영속 상태 : 영속 상태의 Entity가 영속성 컨텍스트에서 분리(edtached)된 상태
         *              영속성 컨텍스트에서 제공하는 기능을 사용하지 못함.
         *
         * 영속 상태 : JPA가 관리하고 있는, 1차 캐쉬에 들어가 있는 컨텍스트
         *
         * 비영속 상태 : JPA와는 아무런 상관이 없는 상태 */


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street1", "zipcode1"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("스테이크");
            member.getFavoriteFoods().add("파스타");

            member.getAddressHistory().add(new Address("old1", "old street1", "old zipcode1"));
            member.getAddressHistory().add(new Address("old2", "old street2", "old zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===================================");
            Member findMember = em.find(Member.class, member.getId());

            tx.commit();
        } catch(Exception e){
            System.out.println("ERROR");
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();

    }
}
