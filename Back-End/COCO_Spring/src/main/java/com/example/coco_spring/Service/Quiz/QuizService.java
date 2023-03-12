package com.example.coco_spring.Service.Quiz;

import com.example.coco_spring.Entity.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface QuizService {
    public void addQuestionToQuiz(QuizQuestion q, Long quizId);
    public void addListQuestionsToQuiz(Set<QuizQuestion> questions, Long quizId);
    public void removeQuestion(Long questionId,Long quizId);
    public void editQuestion(QuizQuestion question,Long questionId);
    public void addAnswersToQuestion(Set<Answer> answers,Long questionId);
    public void setCorrectAnswer(Long answerId);
    public Answer answerQuizQuestion(Long idUser,Long idAnswer);
    public int calculScore(Long idUser,Long idQuiz);

    public Quiz getQuiz(Long idQuiz);

    public Set<QuizQuestion> getQuestionsByQuizzId(long quizId);
    public Set<Answer> getAnswersByQuestionId(long questionId);

}