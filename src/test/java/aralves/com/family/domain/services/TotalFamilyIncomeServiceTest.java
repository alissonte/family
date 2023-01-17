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

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = TotalFamilyIncomeService.class)
public class TotalFamilyIncomeServiceTest {
    @InjectMocks
    private TotalFamilyIncomeService totalFamilyIncomeService;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("income.value.min", "900");
    }

    @Test
    public void givenFamiliaSemNenhumMembro_whenProcess_thenRendaEPontuacacoSaoZero() {
        final Family family = new Family();
        totalFamilyIncomeService.validateFinance(family);
        assertEquals(0, family.getFinance());
        assertEquals(0, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosRendaZero_whenProcess_thenPontuacacoEhCinco() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(0);
        family.setMembers(List.of(parent));

        totalFamilyIncomeService.validateFinance(family);

        assertEquals(0, family.getFinance());
        assertEquals(5, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosAndRendaLess900_whenProcess_thenPontuacacoEhCinco() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(899);
        family.setMembers(List.of(parent));
        totalFamilyIncomeService.setIncomeValueMin(900);

        totalFamilyIncomeService.validateFinance(family);

        assertEquals(899, family.getFinance());
        assertEquals(5, family.getPoints());
    }

    @Test
    public void givenFamiliaComMembrosAndRendaMore900_whenProcess_thenPontuacacoEhCinco() {
        final Family family = new Family();
        final Member parent = new Member();

        family.setFinance(1000);
        family.setMembers(List.of(parent));
        totalFamilyIncomeService.setIncomeValueMin(900);

        totalFamilyIncomeService.validateFinance(family);

        assertEquals(1000, family.getFinance());
        assertEquals(0, family.getPoints());
    }
}