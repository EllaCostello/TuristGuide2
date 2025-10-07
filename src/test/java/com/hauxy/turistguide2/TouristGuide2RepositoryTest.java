package com.hauxy.turistguide2;
import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.repository.Tag;
import com.hauxy.turistguide2.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class TouristGuide2RepositoryTest {

    @Autowired
    private TouristRepository repo;


    //Create
    @Test
    void addTouristAttraction() {
        repo.addTouristAttraction(new TouristAttraction("testName", "testDescription", "København",
                new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT))));
        var at = repo.getTouristAttractions();
        for (TouristAttraction t : at) {
            if (t.getName().equals("testName")) {
                assertThat(t).isNotNull();
                assertThat(t.getDescription()).isEqualTo("testDescription");
            }
        }
    }
    // Read
    @Test
    void getTouristAttraction() {
        List<TouristAttraction> all = repo.getTouristAttractions();
        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0).getName()).isEqualTo("Tivoli");
        assertThat(all.get(2).getName()).isEqualTo("Operaen");
    }

    //Update
    @Test
    void updateTouristAttraction() {
        repo.addTouristAttraction(new TouristAttraction("testName", "testDescription", "Aalborg",
                new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT))));
        repo.updateTouristAttraction("testName", "testDescriptionNew", "Aalborg",
                new ArrayList<>(Arrays.asList(Tag.FORLYSTELSESPARK, Tag.KONCERT)));
        var at = repo.getTouristAttractions();
        for (TouristAttraction t : at) {
            if (t.getName().equals("testName")) {
                assertThat(t).isNotNull();
                assertThat(t.getDescription()).isEqualTo("testDescriptionNew");
                assertThat(t.getTags())
                        .containsExactlyInAnyOrder(Tag.FORLYSTELSESPARK, Tag.KONCERT);

            }

        }
    }

    // Delete
    @Test
    void removeTouristAttraction() {
        repo.addTouristAttraction(new TouristAttraction(
                "testName", "testDescription", "Aalborg",
                new ArrayList<>(Arrays.asList(Tag.BØRNEVENLIG, Tag.DYRT))
        ));

        var atBefore = repo.getTouristAttractions();
        boolean foundBefore = false;
        for (TouristAttraction t : atBefore) {
            if (t.getName().equals("testName")) {
                foundBefore = true;
                assertThat(t).isNotNull();
                assertThat(t.getDescription()).isEqualTo("testDescription");
            }
        }
        assertThat(foundBefore).isTrue(); // sanity check

        repo.removeTouristAttraction("testName");

        var atAfter = repo.getTouristAttractions();
        boolean foundAfter = false;
        for (TouristAttraction t : atAfter) {
            if (t.getName().equals("testName")) {
                foundAfter = true;
            }
        }
        assertThat(foundAfter).isFalse();


    }


}
