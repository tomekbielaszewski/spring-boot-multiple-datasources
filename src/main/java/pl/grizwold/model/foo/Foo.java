package pl.grizwold.model.foo;

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
@Table(name = "foos")
public class Foo {
    public Foo() {
    }

    @Id
    @GeneratedValue
    private Long id;
    private String fooValue;
    private Integer fooCount;
}
