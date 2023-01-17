package aralves.com.family.domain.services;

import aralves.com.family.domain.model.Family;

import aralves.com.family.domain.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private CalculatePoints calculatePoints;

    public Family addNewFamily(final Family family) {
        return familyRepository.save(family);
    }

    public List<Family> getAllFamily() {
        return familyRepository.findAll();
    }

    public List<Family> calculatePoints() {
        return calculatePoints.calculateFamilies();
    }
}
