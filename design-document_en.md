# 1. Introduction 

  

**Project Name**: COMP.SE.110 "Quick Vacation on a Budget" 

  

**Project Team**: Otto Haarahiltunen, Vilppu Tupeli, Eero Hakanen, Niilo Jaakkola 

  

**Summary**: A last-minute travel scanner based on price, dates, and desired weather conditions. 

  

## 1.1 Purpose and Scope 

  

The purpose of the software is to suggest the cheapest direct flights to different destinations from the user's selected airport and time frame. The found flights are sorted by price and local weather. The user can then choose their preferred option and proceed to a website where they can book the flight. 

  

## 1.2 Goals 

  

Short-term goals include enabling the program to offer reasonable options. 

  

# 2. Requirements Specification 

  

## 2.1 Functional Requirements 

  

- **Selecting search criteria**: The user must be able to select the travel budget, departure date, return date, and airport. 

- **Data visualization**: The user should be able to view map-based visualizations of destinations, weather, and prices. 

  

## 2.2 Non-Functional Requirements 

  

- **Performance**: The application must operate at a sufficient speed. 

  

# 3. Architecture Design 

  

## 3.1 General Architecture Overview 

  

The application will be implemented as a web application following the MVC principle. The Front-End is responsible for data visualization and user interface functionality, while the business logic is handled in the Back-End. A layered architecture is employed in the Back-End to clarify structure and facilitate task distribution and testing. The class diagram below illustrates the structure of the Back-End and the relationships between different layers. 

  

[Class Diagram](https://drive.google.com/file/d/1OiQVkGx7B5HnSlQVauICjwz9y3TbM1ZT/preview) 

  

## 3.2 Technologies and Tools 

  

- **Frontend**: HTML/CSS/JS 

- **Backend**: Java 

  

# 4. User Interface Design 

  

## 4.1 Use Cases 

  

Detailed use cases explaining user actions within the application, such as: 

  

- Viewing map data 

- Loading and processing data 

  

## 4.2 User Interface Prototype 

  

[The user interface prototype.](https://www.figma.com/proto/3Z0nJM7AmZo1X4sag637eH/Prototype?node-id=32-54&node-type=canvas&t=vkOqLgQTHZ5XPx7T-0&scaling=min-zoom&content-scaling=fixed&starting-point-node-id=52%3A248) 

  

# 5. Integration with External Services 

  

## 5.1 API Description 

   

**External APIs used**: 

  

- **Weather API** - OpenWeatherMap 

- **Flight Offers Search API** - Find Cheap Flights, 400+ Airlines | Amadeus for Developers 

- [Airport api](https://www.flightsfrom.com/) 

  

## 5.2 Data Integration and Visualization 

  

The application aims to integrate data from the aforementioned API calls and display it according to the prior user interface plan. 

  

# 6. Testing 

  

## 6.1 Testing Strategy 

  

The testing strategy for the Front-End involves manual acceptance testing at the final stage, complemented with unit tests as needed. The focus of testing is on the Back-End, where unit tests and integration tests will be implemented for each layer to ensure the functionality of interfaces between the layers. 

  

# 7. Project Management and Timeline 

  

## 7.1 Project Management Tools 

  

The progress of the project and task distribution will be tracked using Trello. Internal group communication will be carried out via Telegram and Discord. Git and GitHub will be used for version control. Figma will be used for UI design and prototyping. 

  

# 8. Use of AI in Project Design and Implementation 

  

AI was utilized in brainstorming and finding potential APIs. It was also used to create the foundation of the documentation. 
