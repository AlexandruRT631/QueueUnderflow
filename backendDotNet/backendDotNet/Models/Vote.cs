using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backendDotNet.Models;

public class Vote
{
    [Column("user_id")]
    public long UserId { get; set; }

    [Column("positive_vote")]
    public bool PositiveVote { get; set; }
}