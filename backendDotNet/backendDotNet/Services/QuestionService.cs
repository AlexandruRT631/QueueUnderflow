using backendDotNet.DTOs;
using backendDotNet.Repositories;

namespace backendDotNet.Services;

public class QuestionService
{
    private readonly Repository _repository;

    public QuestionService(Repository repository)
    {
        _repository = repository;
    }

    public List<QuestionDTO> GetAll(int page)
    {
        const int pageSize = 10;
        var start = (page - 1) * pageSize;
        try 
        {
            var questions = _repository.Questions.AsQueryable()
                .OrderByDescending(q => q.Date)
                .ToList();

            var end = Math.Min(start + pageSize, questions.Count);
            return questions.GetRange(start, end - start)
                .Select(q => new QuestionDTO(q))
                .ToList();;
        }
        catch(Exception e)
        {
            return new List<QuestionDTO>();
        }
    }

    public QuestionDTO? GetById(long id)
    {
        var question = _repository.Questions.FirstOrDefault(q => q.QuestionId == id);
        return question != null ? new QuestionDTO(question) : null;
    }

    public string DeleteById(long id)
    {
        return "Question deleted";
    }
}