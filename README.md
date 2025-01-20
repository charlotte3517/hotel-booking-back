# Hotel Booking Backend

This is the backend of the Hotel Booking website,  
built using Spring Boot, MyBatis, and Gradle, and hosted on AWS ECS with Fargate.

The backend is responsible for managing hotel data, bookings, and payments, integrating with Amadeus and Google Places APIs for hotel information and reviews.  
It also incorporates Redis for caching API data and automated cleanup features to ensure optimal performance and reliability.

## Features

- **Amadeus API Integration**: Retrieves hotel data, available offers, and allows hotel bookings using the Amadeus API.
- **Google Places API Integration**: Fetches Google reviews and address for hotels, providing users with detailed feedback and ratings.
- **Caching with Redis**: Caches frequently accessed API data (e.g., hotel search results and reviews) to improve performance and reduce API calls.  
  Cached data in Redis is automatically cleaned up on a monthly schedule to ensure data freshness and optimize storage.
- **Logging**: Logs all external API requests and responses in the `External_Request_Log` table for monitoring and debugging.
- **Tests**: Includes unit tests for Amadeus API integration to ensure functionality and reliability.

## Tech Stack

- **Backend Framework**: Spring Boot
- **ORM**: MyBatis
- **Build Tool**: Gradle
- **Database**: Oracle 19c hosted on AWS EC2 using Docker
- **Cache**: Redis hosted on AWS EC2 for caching frequently accessed data and improving response times
- **Hosting**: Backend hosted on AWS ECS (Fargate), Docker images stored in AWS ECR
- **API Integration**:
  - **Google Places API**: Integrated for fetching hotel reviews and location details
  - **Amadeus API**: Integrated to:
    - Search hotels by city
    - Get hotel offers
    - Book hotels
    - *To handle incomplete or missing data from the Amadeus API testing environment,  
      the backend seamlessly switches to mock data, ensuring uninterrupted functionality.*

- **Domain**: Domain registered with AWS Route 53

## Prerequisites

- Java 21
- Gradle 8.5
- Docker


