package first.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import first.project.dto.AnswerDTO;
import first.project.model.Answer;
import first.project.repository.AnswerRepository;
import first.project.repository.QuestionRepository;

@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public boolean checkEmail(String email)
    {
        if ("".equals(email))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAnswer(String answer)
    {
        if ("".equals(answer))
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> validateAndPost(AnswerDTO answerDTO)
    {
        ArrayList<String> res = new ArrayList<>();
        if (checkEmail(answerDTO.getEmail()) == false)
        {
            res.add("You are not logged in. Please log in to answer the question");
        }
        if (checkAnswer(answerDTO.getAnswer()) == false)
        {
            res.add("Answer is empty. Please enter answer");
        }
        if (res.isEmpty())
        {
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setCreator(answerDTO.getEmail());
            answer.setDownvotes(0);
            answer.setUpvotes(0);
            answer.setQuestion(questionRepository.findById(answerDTO.getId()));
            answerRepository.save(answer);
        }
        return res;
    }
}
