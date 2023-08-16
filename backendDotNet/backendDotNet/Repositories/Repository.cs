﻿using backendDotNet.Models;
using Microsoft.EntityFrameworkCore;

namespace backendDotNet.Repositories;

public class Repository: DbContext
{
    public DbSet<AnswerVotes> AnswerVotes { get; set; }
    public DbSet<Answer> Answers { get; set; }
    public DbSet<Question> Questions { get; set; }
    public DbSet<User> Users { get; set; }
    public DbSet<QuestionTag> QuestionTags { get; set; }

    public Repository(DbContextOptions<Repository> options): base(options)
    {
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
        
        modelBuilder.Entity<User>()
            .Property(u => u.Picture)
            .HasDefaultValue("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        
        modelBuilder.Entity<User>()
            .HasMany(u => u.Questions)
            .WithOne(q => q.User)
            .OnDelete(DeleteBehavior.Cascade);
        
        modelBuilder.Entity<User>()
            .HasMany(u => u.Answers)
            .WithOne(a => a.User)
            .OnDelete(DeleteBehavior.Cascade);
        
        modelBuilder.Entity<User>()
            .HasMany(u => u.AnswerVotes)
            .WithOne(a => a.User)
            .OnDelete(DeleteBehavior.Cascade);
        
        modelBuilder.Entity<Question>()
            .HasMany(q => q.Votes)
            .WithOne()
            .HasForeignKey("QuestionId");

        modelBuilder.Entity<Question>()
            .HasOne(q => q.User)
            .WithMany(u => u.Questions) // Assuming you have a navigation property on User entity to represent the Questions collection
            .HasForeignKey(q => q.UserId);
        
        modelBuilder.Entity<Answer>()
            .HasOne(a => a.User)
            .WithMany(u => u.Answers)
            .HasForeignKey(a => a.UserId);

        modelBuilder.Entity<Answer>()
            .HasOne(a => a.Question)
            .WithMany(q => q.Answers)
            .HasForeignKey(a => a.QuestionId);

        modelBuilder.Entity<Answer>()
            .HasMany(a => a.Votes)
            .WithOne()
            .HasForeignKey("AnswerId");

        modelBuilder.Entity<AnswerVotes>()
            .HasOne(av => av.User)
            .WithMany(u => u.AnswerVotes)
            .HasForeignKey(av => av.UserId);
        
        modelBuilder.Entity<Vote>()
            .HasKey(v => new { v.UserId, v.PositiveVote });
        
        modelBuilder.Entity<QuestionTag>()
            .HasKey(qt => new { qt.QuestionId, qt.Tag });

        modelBuilder.Entity<QuestionTag>()
            .HasOne(qt => qt.Question)
            .WithMany(q => q.Tags)
            .HasForeignKey(qt => qt.QuestionId);
    }
}