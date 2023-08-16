using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("users")]
public class UserController
{
    private readonly UserService _userService;

    public UserController(UserService userService)
    {
        _userService = userService;
    }
    
    [HttpGet("getAll")]
    public List<UserDTO> Get()
    {
        return _userService.GetAll();
    }
    
    [HttpGet("getById/{userId:long}")]
    public UserDTO? GetById(long userId)
    {
        return _userService.GetById(userId);
    }
}