package com.example.coco_spring.Service.Quiz;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizzRepository quizzRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addQuestionToQuiz(QuizQuestion q, Long quizId) {
        questionRepository.save(q);
        Quiz quiz = quizzRepository.findById(quizId).get();
        Set<QuizQuestion> quest = new HashSet<>();
        quest.add(q);
        quiz.setQuestions(quest);
        quizzRepository.flush();


    }

    @Override
    public void addListQuestionsToQuiz(Set<QuizQuestion> questions, Long quizId) {
        questionRepository.saveAll(questions);
        Quiz quiz = quizzRepository.findById(quizId).get();
        quiz.getQuestions().addAll(questions);
        quizzRepository.save(quiz);


    }

    @Override
    public void removeQuestion(Long questionId,Long quizId) {
        Quiz q = quizzRepository.findById(quizId).get();
        QuizQuestion qq = questionRepository.findById(questionId).get();

        q.getQuestions().remove(qq);
        quizzRepository.flush();
        questionRepository.deleteById(questionId);

    }

    @Override
    public void editQuestion(QuizQuestion question,Long questionId) {
        QuizQuestion quest = questionRepository.findById(questionId).get();
        quest.setQuestion(question.getQuestion());
        quest.setScore(question.getScore());
        questionRepository.flush();
    }

    @Override
    public void addAnswersToQuestion(Set<Answer> answers, Long questionId) {
        answerRepository.saveAll(answers);
        QuizQuestion qq = questionRepository.findById(questionId).get();
        qq.getAnswers().addAll(answers);
        questionRepository.save(qq);
    }

    @Override
    public void setCorrectAnswer( Long answerId) {

        Answer answer = answerRepository.findById(answerId).get();
        answer.setCorrect(true);
        answerRepository.flush();
    }

    @Override
    public Answer answerQuizQuestion(Long idUser,Long idAnswer) {
        User user = userRepository.findById(idUser).get();
        Answer answer = answerRepository.findById(idAnswer).get();
        user.getAnswers().add(answer);
        userRepository.flush();
        return answer;


    }

    @Override
    public int calculScore(Long idUser,Long idQuiz) {
        String usercred = idUser.toString();
        String quizcred = idQuiz.toString();
        int score=0;
        Quiz quiz = quizzRepository.findById(idQuiz).get();

        List<String> userCorrectAnswers = quizzRepository.getUserScore(idUser);
        System.err.println(userCorrectAnswers);
        for (String userAns : userCorrectAnswers) {

            String[] ans = userAns.split(",");
            if(ans[0].equals(usercred) && ans[4].equals(quizcred)){
                score = score + Integer.parseInt(ans[3]);
            }

        }
        return score;
    }

    @Override
    public Quiz getQuiz(Long idQuiz) {
        return quizzRepository.findById(idQuiz).get();

    }



    @Override
    public Set<QuizQuestion> getQuestionsByQuizzId(long quizId) {
        Quiz q =  quizzRepository.findById(quizId).get();
        return q.getQuestions();
    }

    @Override
    public Set<Answer> getAnswersByQuestionId(long questionId) {
        QuizQuestion qq = questionRepository.findById(questionId).get();
        return qq.getAnswers();
    }




}