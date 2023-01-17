package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;

import static aralves.com.family.insfrastructure.utils.Utils.THREE_POINTS;

public class TotalFamilyIncomeMiddleService extends FinancePoints {

    @Override
    public void validateFinance(final Family family) {
        if(hasIncomeBetween(family)) {
            family.addPoints(THREE_POINTS);
        }
        super.validateFinance(family);
    }

    private boolean hasIncomeBetween(Family family) {
        return family.hasMembers()  && family.getFinance() > getIncomeValueMin() && family.getFinance() <= getIncomeValueMax();
    }
}
