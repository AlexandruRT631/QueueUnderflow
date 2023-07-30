using backendDotNet.Models;

namespace backendDotNet.DTOs;

public class QuestionDTO
{
    public long Id { get; set; }
    public long UserId { get; set; }
    public string UserFirstName { get; set; }
    public string UserLastName { get; set; }
    public string UserPicture { get; set; }
    public string Title { get; set; }
    public string Content { get; set; }
    public string Date { get; set; }
    public string? Picture { get; set; }
    public List<Vote> Votes { get; set; }
    public List<AnswerDTO> Answers { get; set; }
    public List<string> Tags { get; set; }
    public double UserScore { get; set; }

    public QuestionDTO(Question question)
    {
        Id = question.QuestionId;
        UserId = question.UserId;
        UserFirstName = question.User.FirstName;
        UserLastName = question.User.LastName;
        UserPicture = question.User.Picture;
        Title = question.Title;
        Content = question.Content;
        Date = question.Date.ToString("dd/MM/yyyy HH:mm:ss");
        Picture = question.Picture;
        Votes = question.Votes != null ? question.Votes.ToList() : new List<Vote>();
        Answers = question.Answers != null ? question.Answers.Select(a => new AnswerDTO(a))
            .OrderByDescending(a => GetVoteScore(a.Votes))
            .ToList() : new List<AnswerDTO>();
        Tags = question.Tags != null ? question.Tags.Select(t => t.Tag).ToList() : new List<string>();
        UserScore = 0;
    }
    
    private static int GetVoteScore(IEnumerable<Vote> votes)
    {
        return votes.Sum(vote => vote.PositiveVote ? 1 : -1);
    }
}