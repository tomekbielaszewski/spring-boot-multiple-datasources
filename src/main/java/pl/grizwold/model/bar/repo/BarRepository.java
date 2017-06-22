package pl.grizwold.model.bar.repo;

import org.springframework.data.repository.CrudRepository;
import pl.grizwold.model.bar.Bar;

public interface BarRepository extends CrudRepository<Bar, Long> {
}
