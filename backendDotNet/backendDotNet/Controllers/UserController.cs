using backendDotNet.DTOs;
using backendDotNet.Services;
using Microsoft.AspNetCore.Mvc;

namespace backendDotNet.Controllers;

[ApiController]
[Route("[controller]")]
public class UserController
{
    private readonly UserService _userService;

    public UserController(UserService userService)
    {
        _userService = userService;
    }
    
    [HttpGet(Name = "GetUsers")]
    public List<UserDTO> Get()
    {
        return _userService.GetAll();
    }
    
    [HttpGet("{userId}", Name = "GetUserById")]
    public UserDTO? GetById(long userId)
    {
        return _userService.GetById(userId);
    }
}