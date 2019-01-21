# ShopifyCodingChallenge

This repo is a submission for the shopify Backend Developer Intern (also includes roles in Security & Software Engineering in Data) - Summer 2019.

I have made an API of an online shop where the end-user can call the controller to make his/her requests.

This project follows a Gateway architecture. I have implemented a couple of design patterns, such as the singleton pattern to keep the code clean. Since this is only the barebone of the project, there is no frontend available. Currently there is no place to host the data found in the markeyplace.sql file, the app was built in a mindset of having 3 layers. 

Frontend --- RestAPI ---> ShopifyCodingChallenge --- SQL ---> MySQL

## Requirements
Make sure all dependencies have been installed before moving on:
* [Maven](https://maven.apache.org/download.cgi) 
* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)

## Installation

1. Clone the project: `$ git clone --recursive https://github.com/Zhen-Yee/SOEN343-Team15.git`

## Setup

1. `cd` to the project's root folder
2. `cd server` to the project's server folder
3. Run `mvn clean install`  to execute 2 different lifecycle phases, to which plugin execution is done through pom.xml file

### Start server service
Inside `server/` run `mvn spring-boot:run`

Server application is now available at `http://localhost:8090`
