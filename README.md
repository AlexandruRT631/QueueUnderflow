# QueueUnderflow
This is a repository containing my assignment for the Software Design laboratory from my university curriculum. The aim of this assignment is to develop a StackOverflow like platform, where users can post questions which can be answered by other users. The project is a web application, so it's composed out of a database, a backend and a frontend.

## Database
The database is written in MySQL, and this repository contains a file ```database.sql``` which is a SQL Script containing the database used for testing.

## Backend
The backend is written in Java using the Spring Framework, and it follows the layered architecture pattern.

## Frontend
The frontend is written using React JavaScript. To build the web application, the Material UI component library was used.

## Setting up the project
TO DO when the project is finished

## Pages
### User Page
The page is accessed by "./users/{id}". <br />
Pressing any of the answers or questions redirects you to the page of that question. <br />
![alt text](https://raw.githubusercontent.com/AlexandruRT631/QueueUnderflow/main/frontend/UserPage.png?raw=true)
### Question Page
The page is accessed by "./questions/{id}.  <br />
In the question component, pressing any tag from the list will redirect you to a list with all the questions with the same tag. (TO DO) <br />
Pressing the upvote or downvote button on a post will modify it's counter. Each user has only one vote, but it can be changed (TO DO) <br />
Pressing a profile picture or a name will redirect you to the page of that user. <br />
![alt text](https://raw.githubusercontent.com/AlexandruRT631/QueueUnderflow/main/frontend/QuestionPage.png?raw=true)
