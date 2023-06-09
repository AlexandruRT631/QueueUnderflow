package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.QuestionDTO;
import com.rtx.queueunderflow.entity.Question;
import com.rtx.queueunderflow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/insertQuestion")
    @ResponseBody
    public Question insertQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.saveQuestion(questionDTO);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<QuestionDTO> retrieveQuestions(@RequestParam int page) {
        return questionService.retrieveQuestions(page);
    }

    @GetMapping("/tag/{tag}")
    @ResponseBody
    public List<QuestionDTO> retrieveQuestionsByTag(@PathVariable String tag, @RequestParam int page) {
        return questionService.retrieveQuestionsByTag(tag, page);
    }

    @GetMapping("/search/{title}")
    @ResponseBody
    public List<QuestionDTO> retrieveQuestionsByTitle(@PathVariable String title, @RequestParam int page) {
        return questionService.retrieveQuestionsByTitle(title, page);
    }

    @GetMapping("/getById/{question_id}")
    @ResponseBody
    public QuestionDTO retrieveQuestionById(@PathVariable Long question_id) {
        return questionService.retrieveQuestionById(question_id);
    }

    @PutMapping("/updateQuestion")
    @ResponseBody
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/deleteById/{question_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long question_id){
        return questionService.deleteById(question_id);
    }
}
