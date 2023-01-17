package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public abstract class FinancePoints {

    @Value("${family.salary.min:900}")
    protected double incomeValueMin;
    @Value("${income.value.max:1500}")
    protected double incomeValueMax;
    @Value("${dependent.value.limit:2}")
    protected double dependentLimit;
    protected FinancePoints next;

    @PostConstruct
    public void init() {
        System.out.println("================== " + incomeValueMin + "================== ");
        System.out.println("================== " + incomeValueMax + "================== ");
        System.out.println("================== " + dependentLimit + "================== ");
    }

    public FinancePoints linkNext(final FinancePoints nextInChain) {
        this.next = nextInChain;
        return nextInChain;
    }

    public void validateFinance(final Family family){
        if(nonNull(next)) {
            next.validateFinance(family);
        }
    }

    public void setIncomeValueMax(double incomeValueMax) {
        this.incomeValueMax = incomeValueMax;
    }


    public void setIncomeValueMin(double incomeValueMin) {
        this.incomeValueMin = incomeValueMin;
    }

    public void setDependentLimit(double dependentLimit) {
        this.dependentLimit = dependentLimit;
    }

    public double getIncomeValueMin() {
        return incomeValueMin;
    }

    public double getIncomeValueMax() {
        return incomeValueMax;
    }

    public double getDependentLimit() {
        return dependentLimit;
    }
}
