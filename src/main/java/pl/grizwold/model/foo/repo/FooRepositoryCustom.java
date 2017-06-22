package pl.grizwold.model.foo.repo;

import java.math.BigDecimal;

public interface FooRepositoryCustom {
    BigDecimal aggregateFooCountByFooValue(String fooValue);
}
