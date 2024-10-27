# COMP.SE.110

COMP.SE.110 project repository

## Backend

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 17 installed. You can download it from [AdoptOpenJDK](https://adoptopenjdk.net/).
- **Maven**: Ensure you have Maven installed. You can download it from [Maven](https://maven.apache.org/download.cgi).

### Running the Project

1. **Navigate to the correct folder**:

   ```sh
   cd backend/app
   ```

2. **Build the Project**:
   Use Maven to build the project:

   ```sh
   mvn clean install
   ```

3. **Run the Application**:
   Use Maven to run the Spring Boot application:

   ```sh
   mvn spring-boot:run
   ```

4. **Access the Application**:
   Once the application is running, you can access it at [`http://localhost:8080`](localhost:8080)

## Frontend

### Prerequisites

- **Node.js**: Ensure you have Node.js installed. You can download it from [Node.js](https://nodejs.org/).
- **npm**: Ensure you have npm installed. It usually comes with Node.js.

### Install Dependencies

1. **Navigate to the frontend folder**:

   ```sh
   cd frontend
   ```

2. **Install the dependencies**:

   ```sh
   npm install
   ```

### Running the Project

1. **Start the development server**:

   ```sh
   npm run dev
   ```

2. **Build the project for production**:

   ```sh
   npm run build
   ```

3. **Preview the production build**:

   ```sh
   npm run preview
   ```

### Linting and Formatting

1. **Check code formatting**:

   ```sh
   npm run lint
   ```

2. **Format the code**:

   ```sh
   npm run format
   ```

### Type Checking

1. **Run type checking**:

   ```sh
   npm run check
   ```

2. **Run type checking in watch mode**:

   ```sh
   npm run check:watch
   ```
