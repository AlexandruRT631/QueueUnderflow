using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backendDotNet.Models;

[Table("users")]
public class User
{
    [Key]
    [Column("user_id")]
    public long UserId { get; set; }

    [Column("first_name")]
    public string FirstName { get; set; }

    [Column("last_name")]
    public string LastName { get; set; }

    [Column("e_mail")]
    public string EMail { get; set; }

    [Column("pass")]
    public string Password { get; set; }

    [Column("phone")]
    public string? Phone { get; set; }

    [Column("picture")]
    public string Picture { get; set; } = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";

    [Column("moderator")]
    public bool Moderator { get; set; }

    [Column("banned")]
    public bool Banned { get; set; }

    public virtual ICollection<Question>? Questions { get; set; }
    public virtual ICollection<Answer>? Answers { get; set; }
    public virtual ICollection<AnswerVotes>? AnswerVotes { get; set; }
}