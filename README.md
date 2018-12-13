## Week 14 Homework: Event Tracker Project
![Alt text](Users/joshuaciccone/Desktop/eventtrackerimg.png "Front Page")
### Overview
 The purpose of this project was to practice using a RESTful api along with spring boot and JPA repositories connecting to a database. All controller methods where verified and tested using "postman" route tester. The project is running off of a Amazon web server at http://18.223.244.221:8080/VideogameTrackerREST.

#### Operation/Description of how the program works:
This project is for tracking video games. It keeps track of whether the game is owned or not, and the total amount of money it would require to purchase all of the unowned games. It currently has basic CRUD operations for adding, updating and deleting games. there is a shortcut for toggling a game between owned and not owned by clicking on the "YES" or "NO" found on the list.

#### Technologies/Techniques Used

| Technologies/Techniques |
| ----------------------- |
| Java               |
| MySQL              |
| RESTful api        |
| Spring boot      |
| JPA repositories       |
| Postman route tester      |
| Postman route tester      |
| Gradle      |
| ec2 Amazon web server     |
| Angular Front-End      |



#### Lessons Learned
- learning to use angular on my own for the first time.

#### Problems/Issues
- I had issues with persisting entities to the database due to having multiple tables with mappings. I had to make sure a cascade remove and merge happened between the connections and that parent/child table relationships were kept in mind when doing CRUD operations.
 


#### Stretch Goals
- I wanted to have a sort functionality for the front page list by title, owned and release date