package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.AnswerDTO;
import com.rtx.queueunderflow.entity.Answer;
import com.rtx.queueunderflow.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("/insertAnswer")
    @ResponseBody
    public Answer insertAnswer(@RequestBody AnswerDTO answerDTO) {
        return answerService.saveAnswer(answerDTO);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<AnswerDTO> retrieveQuestions() {
        return answerService.retrieveAnswers();
    }

    @GetMapping("/getById/{answer_id}")
    @ResponseBody
    public AnswerDTO retrieveById(@PathVariable Long answer_id) {
        return answerService.retrieveAnswerByID(answer_id);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public Answer updateAnswer(@RequestBody Answer answer) {
        return answerService.updateAnswer(answer);
    }

    @DeleteMapping("/deleteById/{answer_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long answer_id) {
        return answerService.deleteById(answer_id);
    }
}
