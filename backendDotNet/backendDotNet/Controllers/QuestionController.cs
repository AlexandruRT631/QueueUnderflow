using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("questions")]
public class QuestionController
{
    private readonly QuestionService _questionService;
    
    public QuestionController(QuestionService questionService)
    {
        _questionService = questionService;
    }
    
    [HttpGet("getAll")]
    public List<QuestionDTO> Get([FromQuery(Name = "page")] int page)
    {
        return _questionService.GetAll(page);
    }
    
    [HttpGet("getById/{questionId:long}")]
    public QuestionDTO? GetById(long questionId)
    {
        return _questionService.GetById(questionId);
    }
}