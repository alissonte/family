package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;
import aralves.com.family.domain.model.Member;
import aralves.com.family.util.UtilTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static aralves.com.family.util.UtilTest.updateAgeMembersFamily;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = TotalFamilyLessDependentsService.class)
public class TotalFamilyIncomeLessDependentsTest {
    @InjectMocks
    private TotalFamilyLessDependentsService totalFamilyLessDependentsService;

    @Test
    public void givenFamiliaSemNenhumMembro_whenProcess_thenRendaEPontuacacoEhZero() {
        final Family family = new Family();

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosSemDependentes_whenProcess_thenRendaEPontuacacoEhZero() {
        final Family family = new Family();
        family.setMembers(UtilTest.getMembers(false));

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosDependentesEMaioresDezoito_whenProcess_thenRendaEPontuacacoEhZero() {
        final Family family = new Family();
        List<Member> members = UtilTest.getMembers(true)
                .stream().peek(m-> m.setAge(25))
                .collect(Collectors.toList());
        family.setMembers(members);

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosDependentesEQuatroMaioresDezoito_whenProcess_thenRendaEPontuacacoEhZero() {
        final Family family = new Family();
        List<Member> members = UtilTest.getMembers(true);
        family.setMembers(updateAgeMembersFamily(members));
        totalFamilyLessDependentsService.setDependentLimit(2);

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosDependentesEUmMenorDezoito_whenProcess_thenRendaEPontuacacoSaoDois() {
        final Family family = new Family();
        List<Member> members = UtilTest.getMembers(true)
                .stream().peek(m-> m.setAge(25))
                .collect(Collectors.toList());
        members.get(members.size()-1).setAge(10);

        family.setMembers(members);
        totalFamilyLessDependentsService.setDependentLimit(2);

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(2, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosDependentesDoisMenoresDezoito_whenProcess_thenRendaEPontuacacoSaoDois() {
        final Family family = new Family();
        List<Member> members = UtilTest.getMembers(true)
                .stream().peek(m-> m.setAge(25))
                .collect(Collectors.toList());
        members.get(members.size()-1).setAge(10);
        members.get(members.size()-2).setAge(10);

        family.setMembers(members);
        totalFamilyLessDependentsService.setDependentLimit(2);

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(2, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosDependentesTresMenoresDezoito_whenProcess_thenRendaEPontuacacoEhZero() {
        final Family family = new Family();
        List<Member> members = UtilTest.getMembers(true)
                .stream().peek(m-> m.setAge(25))
                .collect(Collectors.toList());
        members.get(members.size()-1).setAge(10);
        members.get(members.size()-2).setAge(10);
        members.get(members.size()-3).setAge(10);

        family.setMembers(members);
        totalFamilyLessDependentsService.setDependentLimit(2);

        totalFamilyLessDependentsService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }
}