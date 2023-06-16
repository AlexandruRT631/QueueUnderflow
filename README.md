# QueueUnderflow
This is a repository containing my assignment for the Software Design laboratory from my university curriculum. The aim of this assignment is to develop a StackOverflow like platform, where users can post questions which can be answered by other users. The project is a web application, so it's composed out of a database, a backend and a frontend. <b>A more detailed explanation of this project and its features can be found in QueueUnderflow.docx</b>.

## Contents
<ul>
<li>The <b>Database</b> is written in MySQL, and this repository contains a file <b>database.sql</b> which is a SQL Script containing the database used for testing.</li>
<li>The <b>Backend</b> is written in Java using the Spring Framework, and it follows the layered architecture pattern.</li>
<li>The <b>Frontend</b> is written using React JavaScript. To build the web application, the Material UI component library was used.</li>
</ul>

## Setting up the project
Firstly, in order to load the database, you'll need <b>MySQL Workbench</b> and <b>MySQL Server</b> (I used version 8.0.33) installed. You can either establish a new MySQL Connection or use an existing one (you'll need to use the connection's url, the username and the password inside the backend) to add the database to it. After opening the connection and starting the server, you can load the script from ```File -> Open SQL Script -> /path-to-project/database.sql```. Now, execute the whole script and the database is ready.</br></br>

The backend was developed using <b>IntelliJ IDEA Ultimate 2023.1.2</b>. Open the backend folder using IntelliJ, and then go to ```src -> main -> resources -> application.properties```. There, modify ```spring.datasource.url```, ```spring.datasource.username``` and ```spring.datasource.password``` with your database connection information. The ```spring.mail``` fields can be left alone (an error will be generated when trying to ban a user, because when a user is banned, it receives an email) or replaced with the data of an email server. Go to ```src -> main -> java -> com.rtx.queueunderflow -> QueueUnderFlowApplication``` and run the application.</br></br>

The frontend was developed using <b>WebStorm 2023.1.2</b>, although running the fronend can be done from a terminal. Just go inside the fronend folder, open a terminal, run ```npm install```, wait for it to finish, then ```npm start```, and a browser window should open with the project's homepage. </br></br>

Now you're done! The project was initialized. A more detailed explanation of the project, how it works, what features does it have and how was developed can be found inside the ```QueueUnderflow.docx``` file. The project is finished and I have no plan to continue it at the moment.