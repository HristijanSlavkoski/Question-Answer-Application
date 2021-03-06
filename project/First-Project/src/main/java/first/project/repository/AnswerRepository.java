package first.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.ArrayList;

import first.project.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>
{
    @Query(value = "SELECT * FROM Answer a WHERE a.questionid=?1", nativeQuery = true)
    public ArrayList<Answer> getAllAnswerForQuestion(Integer id);

    @Query("SELECT a FROM Answer a WHERE a.answerId=?1")
    public Answer getAnswerById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Answer a SET a.upvotes = :newUpVotes WHERE a.answerId = :answerId")
    public void updateUpVotes(@Param("answerId") Integer answerId, @Param("newUpVotes") Integer upVotes);
}
