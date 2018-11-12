## Event Tracker Project
 The purpose of this project was to practice using a RESTful api along with spring boot and JPA repositories connecting to a database. There is no front-end at this time but all controller methods where verified and tested using "postman" route tester. A Json file titled "Videogame Tracker collection" in the root directory can simply be imported into the postman program to allow for testing of all the routes. The project is running off of a localhost server with port 8084 and will soon be pushed to an EC2 instance server. A proper front-end will be implemented in the next weekend project.

## Routes To test
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/1
* POST http://18.223.244.221:8080/VideogameTrackerREST/api/games
* PUT http://18.223.244.221:8080/VideogameTrackerREST/api/games/10
* DELETE http://18.223.244.221:8080/VideogameTrackerREST/api/games/10
* GET http://18.223.244.221:8080/VideogameTrackerREST/games/own
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/own/sort/date
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist/sort/date
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/own/sort/rating
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist/sort/rating
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/own/sort/title
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist/sort/title
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/own/sort/price
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist/sort/price
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/own/sort/category
* GET http://18.223.244.221:8080/VideogameTrackerREST/api/games/wishlist/sort/category

## Program Overview
This project is for tracking video games. It keeps track of both owned games and games on a "wishlist". It currently has basic CRUD operations for adding, updating and deleting games as well as sorting both the owned and wishlist games by title, rating, release date, category and price. Front-end to come soon.

## Technologies/Techniques
* RESTful api
* Spring boot
* JPA repositories
* Postman route tester
* MySql


## Lessons Learned
I had issues with persisting entities to the database due to having multiple tables with mappings. I had to make sure a cascade remove and merge happened between the connections and that parent/child table relationships were kept in mind when doing CRUD operations.
