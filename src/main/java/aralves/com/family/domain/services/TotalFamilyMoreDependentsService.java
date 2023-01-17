package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;

import java.util.ArrayList;
import java.util.Optional;

import static aralves.com.family.insfrastructure.utils.Utils.DEPENDENT_ADULT;

public class TotalFamilyMoreDependentsService extends FinancePoints {
    @Override
    public void validateFinance(final Family family) {
        final long numberDependents = Optional.ofNullable(family.getMembers())
                .orElse(new ArrayList<>())
                .stream()
                .filter(m -> m.isDependent() && m.getAge() < DEPENDENT_ADULT)
                .count();

        if(numberDependents > getDependentLimit()) {
            family.addPoints(3);
        }

        super.validateFinance(family);
    }
}
