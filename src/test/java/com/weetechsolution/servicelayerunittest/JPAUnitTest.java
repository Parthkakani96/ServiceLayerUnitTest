package com.weetechsolution.servicelayerunittest;

import com.weetechsolution.servicelayerunittest.model.Tutorial;
import com.weetechsolution.servicelayerunittest.repository.TutorialRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPAUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    TutorialRepository tutorialRepository;

    @Test
    @Order(1)
    public void should_find_no_tutorials_if_repository_is_empty() {

        Iterable<Tutorial>  tutorials = tutorialRepository.findAll();
        assertThat(tutorials).isEmpty();
    }

    @Test
    @Order(2)
    public void should_store_a_tutorial() {

        Tutorial tutorial = tutorialRepository.save(new Tutorial("Tut title", "Tut desc", true, false, "Author", false));

        assertThat(tutorial).hasFieldOrPropertyWithValue("title", "Tut title");
        assertThat(tutorial).hasFieldOrPropertyWithValue("description", "Tut desc");
        assertThat(tutorial).hasFieldOrPropertyWithValue("published",true);
        assertThat(tutorial).hasFieldOrPropertyWithValue("inStore", false);
        assertThat(tutorial).hasFieldOrPropertyWithValue("author", "Author");
        assertThat(tutorial).hasFieldOrPropertyWithValue("inPrint", false);
    }

    @Test
    @Order(3)
    public void should_find_all_tutorials() {
        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true, false, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", true, false, "Auth#3", true);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findAll();

        assertThat(tutorials).hasSize(3).contains(tut1, tut2, tut3);
    }

    @Test
    @Order(4)
    public void should_find_tutorial_by_id() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true, false, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial foundTutorial = tutorialRepository.findById(tut2.getId()).get();

        assertThat(foundTutorial).isEqualTo(tut2);
    }

    @Test
    @Order(5)
    public void should_find_published_tutorials() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#2", true, false, "Auth#1", false);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3","Desc#3", true, false, "Auth#3", true);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        assertThat(tutorials).hasSize(2).contains(tut1, tut3);
    }

    @Test
    @Order(12)
    public void should_find_by_inPrint() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#2", true, false, "Auth#1", false);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", true, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", true, false, "Auth#3", true);
        testEntityManager.persist(tut3);

        Tutorial tut4 = new Tutorial("Tut#4", "Desc#4", true, false, "Auth#4", true);
        testEntityManager.persist(tut4);

        Iterable<Tutorial> tutorials = tutorialRepository.findByInPrint(true);

        assertThat(tutorials).hasSize(2).contains(tut4, tut3);
    }

    @Test
    @Order(10)
    public void should_find_tutorials_by_author() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true, false, "Auth#1", false);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", true, false, "Auth#1", true);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", false, true, "Auth#3", false);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByAuthor("Auth#1");

        assertThat(tutorials).hasSize(2).contains(tut1, tut2);
    }

    @Test
    @Order(11)
    public void should_find_by_description_containing_string() {

        Tutorial tut1 = new Tutorial("Tut#1", "Learn Java", true, false, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Learn Spring", false, false, "Auth#2", true);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Learn Spring Boot", true, false, "Auth#3", false);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByDescriptionContaining("Spring");

        assertThat(tutorials).hasSize(2).contains(tut2, tut3);
    }

    @Test
    @Order(9)
    public void should_find_tutorials_by_inStore() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true, true, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", true, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", false, true, "Auth#3", false);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByInStore(true);

        assertThat(tutorials).hasSize(2).contains(tut1,tut3);
    }

    @Test
    @Order(6)
    public void should_find_tutorials_by_title_containing_string() {

        Tutorial tut1 = new Tutorial("Spring Boot Tut#1", "Desc#1", true, false, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Java Tut#2", "Desc#2", false, false, "Auth#2", false);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Spring Jpa Data Tut#3", "Desc#3", true, false, "Auth#3", false);
        testEntityManager.persist(tut3);

        Iterable<Tutorial> tutorials = tutorialRepository.findByTitleContaining("ring");

        assertThat(tutorials).hasSize(2).contains(tut1, tut3);
    }

    @Test
    @Order(7)
    public void should_delete_tutorial_by_id() {

        Tutorial tut1 = new Tutorial("Tut#1", "Desc#1", true, false, "Auth#1", true);
        testEntityManager.persist(tut1);

        Tutorial tut2 = new Tutorial("Tut#2", "Desc#2", false, false, "Auth#2", true);
        testEntityManager.persist(tut2);

        Tutorial tut3 = new Tutorial("Tut#3", "Desc#3", true, false, "Auth#3", true);
        testEntityManager.persist(tut3);

        tutorialRepository.deleteById(tut2.getId());

        Iterable<Tutorial> tutorials = tutorialRepository.findAll();

        assertThat(tutorials).hasSize(2).contains(tut1, tut3);
    }

    @Test
    @Order(8)
    public void should_delete_all_tutorials() {
        testEntityManager.persist(new Tutorial("Tut#1", "Desc#1", true, false, "Auth#1", false));
        testEntityManager.persist(new Tutorial("Tut#2", "Desc#2", false, false, "Auth#2", false));

        tutorialRepository.deleteAll();

        assertThat(tutorialRepository.findAll()).isEmpty();
    }
}
