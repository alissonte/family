package aralves.com.family.domain.dtos;

import aralves.com.family.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FamilyDTO {

    private String name;

    private List<Member> members;

    private double points;

    private String level;

    public void addPoints(int newPoint) {
        this.points += newPoint;
    }
}
