using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("answers")]
public class AnswerController
{
    private readonly AnswerService _answerService;
    
    public AnswerController(AnswerService answerService)
    {
        _answerService = answerService;
    }
    
    [HttpGet("getAll")]
    public List<AnswerDTO> Get()
    {
        return _answerService.GetAll();
    }
    
    [HttpGet("getById/{answerId:long}")]
    public AnswerDTO? GetById(long answerId)
    {
        return _answerService.GetById(answerId);
    }
}