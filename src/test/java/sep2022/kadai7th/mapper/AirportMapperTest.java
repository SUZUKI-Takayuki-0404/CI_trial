package sep2022.kadai7th.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AirportMapperTest {

    @Autowired
    AirportMapper airportMapper;

    @Test
    @Sql(
            scripts = {"classpath:/sql-annotation/delete-airports.sql", "classpath:/sql-annotation/insert-airports.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void findAll() {
        assertThat(airportMapper.findAll())
                .hasSize(4)
                .extracting("AirportCode", "AirportName", "Country")
                .contains(
                        tuple("NRT", "NARITA", "JAPAN"),
                        tuple("HND", "HANEDA", "JAPAN"),
                        tuple("KIX", "KANSAI", "JAPAN"),
                        tuple("NGO", "CHUBU", "JAPAN")
                );
    }

    @Test
    @Sql(
            scripts = {"classpath:/sql-annotation/delete-airports.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void FindAll2() {
        assertThat(airportMapper.findAll()).isEmpty();
    }

    /*
    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
    */
}
