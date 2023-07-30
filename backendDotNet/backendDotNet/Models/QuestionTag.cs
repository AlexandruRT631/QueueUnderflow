using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backendDotNet.Models;

[Table("list_of_tags")]
public class QuestionTag
{
    [Column("question_id")]
    public long QuestionId { get; set; }

    [Column("tag")]
    public string Tag { get; set; }

    public Question Question { get; set; }
}