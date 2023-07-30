using backendDotNet.DTOs;
using backendDotNet.Repositories;

namespace backendDotNet.Services;

public class AnswerService: IService<AnswerDTO>
{
    private readonly Repository _repository;

    public AnswerService(Repository repository)
    {
        _repository = repository;
    }

    public List<AnswerDTO> GetAll()
    {
        return _repository.Answers.ToList().Select(answer => new AnswerDTO(answer)).ToList();
    }

    public AnswerDTO? GetById(long id)
    {
        var answer = _repository.Answers.FirstOrDefault(a => a.AnswerId == id);
        return answer != null ? new AnswerDTO(answer) : null;
    }

    public string DeleteById(long id)
    {
        return "Answer deleted";
    }
}