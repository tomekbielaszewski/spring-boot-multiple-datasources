package pl.grizwold.model.tar;

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
@Table(name = "tars")
public class Tar {
    public Tar() {
    }

    @Id
    @GeneratedValue
    private Long id;
    private String tarValue;
}
