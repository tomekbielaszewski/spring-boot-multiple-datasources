package pl.grizwold.model.foo.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.grizwold.model.foo.repo.FooRepositoryCustom;

import java.math.BigDecimal;

public class FooRepositoryImpl implements FooRepositoryCustom {
    private final String AGGREGATION_SQL = "SELECT Sum(fooCount) as summary FROM foos WHERE fooValue = :fooValue";

    @Autowired
    @Qualifier("fooJdbcTemplate")
    private JdbcTemplate jdbc;

    @Override
    public BigDecimal aggregateFooCountByFooValue(String fooValue) {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fooValue", fooValue);

        return namedJdbcTemplate.queryForObject(AGGREGATION_SQL, params, BigDecimal.class);
    }
}
