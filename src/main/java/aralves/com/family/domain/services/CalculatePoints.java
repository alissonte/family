package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;
import aralves.com.family.domain.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculatePoints {
    @Autowired
    private FamilyRepository familyRepository;

    public List<Family> calculateFamilies() {
        final List<Family> allFamily = familyRepository.getAllFamilies();
        return allFamily
                .stream()
                .map(this::calculate)
                .sorted(Comparator.comparing(Family::getPoints, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    private Family calculate(final Family family) {
        final FinancePoints calculate1 = new TotalFamilyIncomeService();
        final FinancePoints calculate2 = new TotalFamilyIncomeMiddleService();
        final FinancePoints calculate3 = new TotalFamilyMoreDependentsService();
        final FinancePoints calculate4 = new TotalFamilyLessDependentsService();
        calculate1
                .linkNext(calculate2)
                .linkNext(calculate3)
                .linkNext(calculate4)
                .linkNext(null);

        calculate1.validateFinance(family);

        return family;
    }
}
