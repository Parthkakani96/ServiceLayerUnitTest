package com.weetechsolution.servicelayerunittest.repository;


import com.weetechsolution.servicelayerunittest.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByInStore(boolean inStore);

    List<Tutorial> findByAuthor(String author);

    List<Tutorial> findByDescriptionContaining(String description);

    List<Tutorial> findByInPrint(boolean inPrint);

}
