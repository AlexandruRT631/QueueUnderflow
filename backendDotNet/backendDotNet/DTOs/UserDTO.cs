using backendDotNet.Models;

namespace backendDotNet.DTOs;

public class UserDTO
{
    public long Id { get; set; }
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public string Picture { get; set; }
    public bool Moderator { get; set; }
    public bool Banned { get; set; }
    public List<QuestionDTO> Questions { get; set; }
    public List<AnswerDTO> Answers { get; set; }
    public double UserScore { get; set; }

    public UserDTO(User user)
    {
        Id = user.UserId;
        FirstName = user.FirstName;
        LastName = user.LastName;
        Picture = user.Picture;
        Moderator = user.Moderator;
        Banned = user.Banned;
        Questions = user.Questions != null ? user.Questions.Select(q =>
        {
            QuestionDTO questionDTO = new QuestionDTO(q);
            questionDTO.Answers = new List<AnswerDTO>();
            return questionDTO;
        }).ToList() : new List<QuestionDTO>();
        Answers = user.Answers != null ? user.Answers.Select(a => new AnswerDTO(a)).ToList() : new List<AnswerDTO>();
        UserScore = 0;
    }
}