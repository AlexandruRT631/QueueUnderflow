using backendDotNet.DTOs;
using backendDotNet.Repositories;

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
        return _repository.Users.ToList().Select(user => new UserDTO(user)).ToList();
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