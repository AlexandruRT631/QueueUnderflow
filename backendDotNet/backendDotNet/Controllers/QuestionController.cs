using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("[controller]")]
public class QuestionController
{
    private readonly QuestionService _questionService;
    
    public QuestionController(QuestionService questionService)
    {
        _questionService = questionService;
    }
    
    [HttpGet("{page}", Name = "GetQuestions")]
    public List<QuestionDTO> Get(int page)
    {
        return _questionService.GetAll(page);
    }
    
    [HttpGet("{questionId}", Name = "GetQuestionById")]
    public QuestionDTO? GetById(long questionId)
    {
        return _questionService.GetById(questionId);
    }
}