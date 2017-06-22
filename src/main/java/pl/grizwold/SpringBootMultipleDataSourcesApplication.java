package pl.grizwold;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.grizwold.model.bar.Bar;
import pl.grizwold.model.bar.repo.BarRepository;
import pl.grizwold.model.foo.Foo;
import pl.grizwold.model.foo.repo.FooRepository;
import pl.grizwold.model.tar.Tar;
import pl.grizwold.model.tar.repo.TarRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class SpringBootMultipleDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultipleDataSourcesApplication.class, args);
    }

    @Autowired
    private FooRepository fooRepository;
    @Autowired
    private BarRepository barRepository;
    @Autowired
    private TarRepository tarRepository;

    @PostConstruct
    public void init() {
        fooRepository.save(createFoo());
        fooRepository.save(createFoo());
        fooRepository.save(createFoo());
        BigDecimal fooAggregation = fooRepository.aggregateFooCountByFooValue("foo");
        if (fooAggregation.longValue() != 30) log.error("Sum of fooCounts should be 30!");
        else log.info(fooAggregation.toString());
        fooRepository.deleteAll();

        barRepository.save(createBar());
        barRepository.findAll().forEach(b -> log.info(b.toString()));
        barRepository.deleteAll();

        tarRepository.save(createTar());
        tarRepository.findAll().forEach(t -> log.info(t.toString()));
        tarRepository.deleteAll();
    }

    private Foo createFoo() {
        return Foo.builder().fooValue("foo").fooCount(10).build();
    }

    private Bar createBar() {
        return Bar.builder().barValue("bar").build();
    }

    private Tar createTar() {
        return Tar.builder().tarValue("tar").build();
    }
}
