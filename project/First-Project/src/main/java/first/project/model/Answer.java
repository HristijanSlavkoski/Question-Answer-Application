package first.project.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "answer")
@EntityListeners(AuditingEntityListener.class)
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerid;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String creator;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "questionid", nullable = false)
    private Question question;

    @Column(nullable = false)
    private Integer upvotes;

    @Column(nullable = false)
    private Integer downvotes;

    public Integer getAnswerid()
    {
        return answerid;
    }

    public void setAnswerid(Integer answerid)
    {
        this.answerid = answerid;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public Instant getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate)
    {
        this.createdDate = createdDate;
    }

    public Integer getUpvotes()
    {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes)
    {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes()
    {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes)
    {
        this.downvotes = downvotes;
    }
}
