package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;
import org.springframework.stereotype.Service;

import static aralves.com.family.insfrastructure.utils.Utils.FIVE_POINTS;

@Service
public class TotalFamilyIncomeService extends FinancePoints {

    @Override
    public void validateFinance(final Family family) {
        if (family.hasMembers() && family.getFinance() <= getIncomeValueMin()) {
            family.addPoints(FIVE_POINTS);
        }
        super.validateFinance(family);
    }
}
