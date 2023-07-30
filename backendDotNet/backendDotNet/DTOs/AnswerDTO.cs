using backendDotNet.Models;

namespace backendDotNet.DTOs;

public class AnswerDTO
{
    public long Id { get; set; }
    public long UserId { get; set; }
    public string UserFirstName { get; set; }
    public string UserLastName { get; set; }
    public string UserPicture { get; set; }
    public long QuestionId;
    public string QuestionTitle { get; set; }
    public string Content { get; set; }
    public string Date { get; set; }
    public string? Picture { get; set; }
    public List<Vote> Votes { get; set; }
    public double userScore { get; set; }

    public AnswerDTO(Answer answer)
    {
        Id = answer.AnswerId;
        UserId = answer.UserId;
        UserFirstName = answer.User.FirstName;
        UserLastName = answer.User.LastName;
        UserPicture = answer.User.Picture;
        QuestionId = answer.QuestionId;
        QuestionTitle = answer.Question.Title;
        Content = answer.Content;
        Date = answer.Date.ToString("dd/MM/yyyy HH:mm:ss");
        Picture = answer.Picture;
        Votes = answer.Votes != null ? answer.Votes.ToList() : new List<Vote>();
        userScore = 0;
    }
}