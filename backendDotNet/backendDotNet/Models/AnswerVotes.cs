using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backendDotNet.Models;

[Table("answer_votes")]
public class AnswerVotes
{
    [Key]
    [Column("answer_id")]
    public long AnswerId { get; set; }

    [Column("user_id")]
    public long UserId { get; set; }
    public User User { get; set; }

    [Column("positive_vote")]
    public bool PositiveVote { get; set; }
}