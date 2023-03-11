package com.example.coco_spring.Controller.Quiz;



import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Quiz.*;

import com.example.coco_spring.Service.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

@RestController

@RequestMapping("/api/quiz")
public class QuizRestController {


    @Autowired
    QuizServiceImpl quizService;

    @Autowired
    StoreService storeService;

    @PostMapping(path = "createQuiz/{storeId}/{userId}")
    public void createQuiz(@RequestBody Quiz q, @PathVariable("storeId")Long storeId, @PathVariable("userId")  Long userId ) {

        storeService.createQuizz(q, storeId,userId);
    }


    @PostMapping(path = "addQuestion/{quizId}")
    public void addQuestion(@RequestBody QuizQuestion quest,@PathVariable("quizId")Long quizId) {

        quizService.addQuestionToQuiz(quest, quizId);

    }
    @PostMapping(path = "addQuestions/{quizId}")
    public void addQuestions(@RequestBody Set<QuizQuestion> quest, @PathVariable("quizId")Long quizId) {

        quizService.addListQuestionsToQuiz(quest, quizId);

    }
    @DeleteMapping(path="deleteQuestion/{questionId}/{quizId}")
    public void deleteQuestion(@PathVariable("questionId")Long questionId,@PathVariable("quizId")Long quizId) {
        quizService.removeQuestion(questionId,quizId);

    }
    @PutMapping(path="editQuestion/{questionId}")
    public void editQuestion(@RequestBody QuizQuestion q,@PathVariable("questionId")Long questionId) {
        quizService.editQuestion(q,questionId);
    }
    @PostMapping(path = "addAnswers/{questionId}")
    public void addAnswers(@RequestBody Set<Answer> answer,@PathVariable("questionId")Long questionId) {

        quizService.addAnswersToQuestion(answer, questionId);

    }
    @PutMapping(path="setCorrectAnswer/{answerId}")
    public void setCorrectAnswer(@PathVariable("answerId")Long answerId) {
        quizService.setCorrectAnswer(answerId) ;
    }
    @PostMapping(path="answerQuestion/{idAnswer}/{userId}")
    public Answer userAnswerQuestion(@PathVariable("userId")  Long userId,@PathVariable("idAnswer")Long idAnswer) {
        return quizService.answerQuizQuestion(userId, idAnswer);
    }
    @GetMapping(path="calculUserScore/{userId}/{quizId}")
    public int userScoreCalcul(@PathVariable("userId")Long userId,@PathVariable("quizId")Long quizId) {
        return quizService.calculScore(userId, quizId);

    }


    @GetMapping(path="getQuiz/{idQuiz}")
    public Quiz getQuiz(@PathVariable("idQuiz")Long idQuiz) {
        return quizService.getQuiz(idQuiz);
    }

    @GetMapping(path="getQuizQuestions/{idquiz}")
    public Set<QuizQuestion> getQuizQuestion(@PathVariable("idquiz")Long idQuiz) {
        return quizService.getQuestionsByQuizzId(idQuiz);
    }
    @GetMapping(path="getAnswers/{idQuestion}")
    public Set<Answer> getQuizAnswers(@PathVariable("idQuestion")Long idquestion) {
        return quizService.getAnswersByQuestionId(idquestion);
    }
}