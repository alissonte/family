package aralves.com.family.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String name;

    private int age;

    private boolean dependent;

    private FamilyDTO family;
}
