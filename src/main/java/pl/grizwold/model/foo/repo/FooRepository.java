package pl.grizwold.model.foo.repo;

import org.springframework.data.repository.CrudRepository;
import pl.grizwold.model.foo.Foo;

public interface FooRepository extends CrudRepository<Foo, Long>, FooRepositoryCustom {
}
