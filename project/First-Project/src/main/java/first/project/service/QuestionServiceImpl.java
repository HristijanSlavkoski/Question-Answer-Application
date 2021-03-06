package first.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import first.project.dto.QuestionDTO;
import first.project.dto.UserQuestionDTO;
import first.project.exceptions.InvalidCreatorException;
import first.project.model.Question;
import first.project.model.User;
import first.project.repository.QuestionRepository;
import first.project.repository.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean checkTitle(String email)
    {
        if ("".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuestion(String question)
    {
        if ("".equals(question))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email)
    {
        if ("".equals(email) || email == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> validateAndPost(QuestionDTO questionDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkTitle(questionDTO.getTitle()) == false)
        {
            res.add("Title is empty. Please enter title");
        }
        if (checkQuestion(questionDTO.getQuestion()) == false)
        {
            res.add("Question is empty. Please enter question");
        }
        if (checkEmail(questionDTO.getEmail()) == false)
        {
            res.add("You are not logged in. Please log in to ask a question");
        }
        if (res.isEmpty())
        {
            Question question = new Question();
            question.setCreator(questionDTO.getEmail());
            question.setModifier(questionDTO.getEmail());
            question.setTitle(questionDTO.getTitle());
            question.setQuestion(questionDTO.getQuestion());
            questionRepository.save(question);
        }
        return res;
    }

    @Override
    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteQuestion(UserQuestionDTO userQuestionDTO) throws InvalidCreatorException
    {
        Question questionToDelete = questionRepository.findById(userQuestionDTO.getQuestionId());
        User user = userRepository.findById(userQuestionDTO.getUserId());
        if (questionToDelete.getCreator().equals(user.getEmail()))
        {
            String queryDeleteQuestion = "DELETE FROM question WHERE question.id=?1";
            Query nativeQueryDeleteQuestion = entityManager.createNativeQuery(queryDeleteQuestion);
            nativeQueryDeleteQuestion.setParameter(1, questionToDelete.getId());
            nativeQueryDeleteQuestion.executeUpdate();
        }
        else
        {
            throw new InvalidCreatorException(
                    "Unable to delete this question. User `" + user.getEmail() + "` is not the creator of the question!");
        }
    }
}
