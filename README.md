# coindesk-api 

# To run project
-- Clean and build file .jar
- ./mvnw clean package

-- Create image and run container 
- docker build -t coindesk-api .
- docker run -d -p 8080:8080 --name coindesk-api coindesk-api

# API DOCS
http://localhost:8080/swagger-ui/index.html

TIme estimate on this project: ~8 hours