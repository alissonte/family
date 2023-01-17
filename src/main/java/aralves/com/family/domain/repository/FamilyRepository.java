package aralves.com.family.domain.repository;

import aralves.com.family.domain.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    @Query("FROM Family f LEFT JOIN FETCH f.members")
    List<Family> getAllFamilies();

}
