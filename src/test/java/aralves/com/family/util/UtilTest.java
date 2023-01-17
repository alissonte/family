package aralves.com.family.util;

import aralves.com.family.domain.model.Member;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    private UtilTest(){}

    public static List<Member> getMembers(boolean withDependent) {
        List<Member> members = new ArrayList<>();
        Member member;
        for (int i = 0; i < 10; i++) {
            member = new Member();
            member.setName(Faker.instance().name().name());
            if (i == 0 || i == 1) {
                member.setDependent(false);
            } else {
                member.setDependent(withDependent);
            }
            members.add(member);
        }

        return members;
    }


    public static List<Member> updateAgeMembersFamily(List<Member> members) {
        List<Member> novaLista = new ArrayList<>();
        for(int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if(i>=2) {
                if (i % 2 == 0)
                    member.setAge(16);
                else
                    member.setAge(29);
            }else{
                member.setAge(55);
            }

            novaLista.add(member);
        }

        return novaLista;
    }

}
