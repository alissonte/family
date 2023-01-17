package aralves.com.family.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Family implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "family")
    private List<Member> members = new ArrayList<>();

    private double finance;
    @Transient
    private int points;

    public void addPoints(int newPoint) {
        this.points += newPoint;
    }

    public boolean hasMembers() {
        return !CollectionUtils.isEmpty(members);
    }
}
