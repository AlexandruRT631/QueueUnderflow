package com.rtx.queueunderflow.repository;

import com.rtx.queueunderflow.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByTagsContaining(String tag);
    List<Question> findByTitleContainingIgnoreCase(String title);
}
