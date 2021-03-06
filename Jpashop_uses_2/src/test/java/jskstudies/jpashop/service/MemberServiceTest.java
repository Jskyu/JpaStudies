package jskstudies.jpashop.service;

import jskstudies.jpashop.model.Member;
import jskstudies.jpashop.repository.MemberRepository;
import jskstudies.jpashop.repository.MemberRepositoryOld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(saveId).orElse(null));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("memberA");
        Member member2 = new Member();
        member2.setName("memberA");

        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다!!!

        /*
        try{
            memberService.join(member2); //예외가 발생해야 한다!!!
        } catch(IllegalStateException e){
            return;
        }
        */

        //Then
        fail("예외가 발생해야 한다.");


    }
}