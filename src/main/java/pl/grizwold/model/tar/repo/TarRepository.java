package pl.grizwold.model.tar.repo;

import org.springframework.data.repository.CrudRepository;
import pl.grizwold.model.tar.Tar;

public interface TarRepository extends CrudRepository<Tar, Long> {
}
