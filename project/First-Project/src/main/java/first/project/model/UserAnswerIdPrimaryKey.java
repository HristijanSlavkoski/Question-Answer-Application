package first.project.model;

import java.io.Serializable;
import java.util.Objects;

public class UserAnswerIdPrimaryKey implements Serializable
{
    private User user;
    private Answer answer;

    public UserAnswerIdPrimaryKey()
    {

    }

    public UserAnswerIdPrimaryKey(User userId, Answer answerId)
    {
        this.user = userId;
        this.answer = answerId;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user, answer);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserAnswerIdPrimaryKey userAnswerIdPrimaryKey = (UserAnswerIdPrimaryKey) o;
        return user.equals(userAnswerIdPrimaryKey.user) &&
                answer.equals(userAnswerIdPrimaryKey.answer);
    }
}
