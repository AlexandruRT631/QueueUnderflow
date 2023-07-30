using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("[controller]")]
public class AnswerController
{
    private readonly AnswerService _answerService;
    
    public AnswerController(AnswerService answerService)
    {
        _answerService = answerService;
    }
    
    [HttpGet(Name = "GetAnswers")]
    public List<AnswerDTO> Get()
    {
        return _answerService.GetAll();
    }
    
    [HttpGet("{answerId}", Name = "GetAnswerById")]
    public AnswerDTO? GetById(long answerId)
    {
        return _answerService.GetById(answerId);
    }
}