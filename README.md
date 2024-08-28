# Hotel Booking Backend

This is the backend of the Hotel Booking website, 
built using Spring Boot, MyBatis, and Gradle, hosted on AWS ECS with Fargate. 

The backend handles order creation, payment processing, hotel review from google place api, and external API logging.

## Features

- **Order Management**: Automatically creates order details and payment records.
- **Hotel Review Storage**: Stores hotel review data in the database for 7 days.  
  The backend checks if the stored data is older than 7 days;  
  if it is, the data is deleted, and new data is retrieved from the Google Places API.

- **Automated Data Cleanup**: Automatically deletes hotel reviews older than 7 days every Monday at 2 AM,  
  ensuring that the database contains only the most recent data.
- **Logging**: All external requests and responses are logged in the External_Request_Log table.
- **Tests**: Includes unit tests for hotel, order, and payment functionalities.

## Tech Stack

- **Backend Framework**: Spring Boot
- **ORM**: MyBatis
- **Build Tool**: Gradle
- **Database**: Oracle 19c hosted on AWS EC2 using Docker
- **Hosting**: Backend hosted on AWS ECS (Fargate), Docker images stored in AWS ECR
- **API Integration**: Spring Boot backend integrated with Google Places API
- **Domain**: Domain registered with AWS Route 53

## Prerequisites

- Java 21
- Gradle 8.5
- Docker


