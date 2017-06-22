package pl.grizwold.model.bar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "bars")
public class Bar {
    public Bar() {
    }

    @Id
    @GeneratedValue
    private Long id;
    private String barValue;
}
