using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backendDotNet.Models;

[Table("answers")]
public class Answer
{
    [Key]
    [Column("answer_id")]
    public long AnswerId { get; set; }

    [Column("question_id")]
    public long QuestionId { get; set; }
    public Question Question { get; set; }

    [Column("user_id")]
    public long UserId { get; set; }
    public User User { get; set; }

    [Column("content")]
    public string Content { get; set; }

    [Column("creation_date")]
    public DateTime Date { get; set; } = DateTime.Now;

    [Column("picture")]
    public string? Picture { get; set; }

    public virtual ICollection<Vote>? Votes { get; set; }
}