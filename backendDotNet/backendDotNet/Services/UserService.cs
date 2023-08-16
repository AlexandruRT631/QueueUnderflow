using backendDotNet.DTOs;
using backendDotNet.Repositories;
using Microsoft.EntityFrameworkCore;

namespace backendDotNet.Services;

public class UserService: IService<UserDTO>
{
    private readonly Repository _repository;
    
    public UserService(Repository repository)
    {
        _repository = repository;
    }

    public List<UserDTO> GetAll()
    {
        return _repository.Users
            .Include(u => u.Questions)
            .Include(u => u.Answers)
            .ToList().Select(user => new UserDTO(user)).ToList();
    }

    public UserDTO? GetById(long id)
    {
        var user = _repository.Users.FirstOrDefault(u => u.UserId == id);
        return user != null ? new UserDTO(user) : null;
    }
    
    public string DeleteById(long id)
    {
        return "User deleted";
    }
}