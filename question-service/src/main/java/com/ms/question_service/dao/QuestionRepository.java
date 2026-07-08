package com.ms.question_service.dao;

import com.ms.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("limit") int limit);

    @Query(value = "SELECT id FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Integer> findRandomQuestionsIdByCategory(@Param("category") String category, @Param("limit") int limit);

    Optional<Question> findByRightAnswer(String rightAnswer);

}
