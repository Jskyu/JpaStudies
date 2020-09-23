package jpql;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.setAge(10);
            memberA.setType(MemberType.ADMIN);
            memberA.changeTeam(teamA);

            Member memberB = new Member();
            memberB.setUsername("memberB");
            memberB.setAge(20);
            memberB.changeTeam(teamA);
            memberB.setType(MemberType.ADMIN);

            Member memberC = new Member();
            memberC.setUsername("memberC");
            memberC.setAge(20);
            memberC.changeTeam(teamB);
            memberC.setType(MemberType.ADMIN);

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            int i = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();


            tx.commit();
        } catch(Exception e){
            System.out.println("ERROR");
            e.printStackTrace();
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();


    }
}
