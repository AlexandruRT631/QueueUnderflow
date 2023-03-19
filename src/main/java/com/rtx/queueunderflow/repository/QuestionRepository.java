package com.rtx.queueunderflow.repository;

import com.rtx.queueunderflow.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByQuestionIsTrue();
    Optional<Question> findByPostIdAndQuestionIsTrue(Long answerId);
}
