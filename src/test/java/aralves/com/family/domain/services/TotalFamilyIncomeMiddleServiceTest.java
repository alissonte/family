package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;
import aralves.com.family.domain.model.Member;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static aralves.com.family.util.UtilTest.getMembers;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = TotalFamilyIncomeMiddleService.class)
public class TotalFamilyIncomeMiddleServiceTest {
    @InjectMocks
    private TotalFamilyIncomeMiddleService totalFamilyIncomeMiddleService;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("income.value.min", "900");
    }

    @Test
    public void givenFamiliaSemNenhumMembro_whenProcess_thenRendaEPontuacacoSaoZero() {
        final Family family = new Family();

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosRendaZero_whenProcess_thenPontuacacoEhZero() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(0);
        family.setMembers(List.of(parent));

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosRenda1000_whenProcess_thenPontuacacoEhThree() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(1000);
        family.setMembers(List.of(parent));
        totalFamilyIncomeMiddleService.setIncomeValueMax(1500);
        totalFamilyIncomeMiddleService.setIncomeValueMin(900);

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(1000, family.getFinance());
        assertEquals(3, family.getPoints());
    }

    @Test
    public void givenFamiliaComDezMembrosRenda1000_whenProcess_thenPontuacacoEhThree() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(1000);
        family.setMembers(getMembers(true));
        totalFamilyIncomeMiddleService.setIncomeValueMax(1500);
        totalFamilyIncomeMiddleService.setIncomeValueMin(900);

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(1000, family.getFinance());
        assertEquals(3, family.getPoints());
    }

    @Test
    public void givenFamiliaSemMembrosRenda1000_whenProcess_thenPontuacacoEhThree() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(1000);
        totalFamilyIncomeMiddleService.setIncomeValueMax(1500);
        totalFamilyIncomeMiddleService.setIncomeValueMin(900);

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(1000, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaSemMembrosRenda2000_whenProcess_thenPontuacacoEhThree() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(2000);
        family.setMembers(getMembers(true));
        totalFamilyIncomeMiddleService.setIncomeValueMax(1500);
        totalFamilyIncomeMiddleService.setIncomeValueMin(900);

        totalFamilyIncomeMiddleService.validateFinance(family);

        assertEquals(2000, family.getFinance());
        assertEquals(0, family.getPoints());
    }
}