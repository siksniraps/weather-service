#Java developer home assignment:
Your task is to develop a simple RESTful web service that would satisfy a set of functional requirements, as well as a list of non-functional requirements. Please note, those non-functional requirements are given in order of importance; items appearing earlier in the list are more crucial for assignment.

##Functional requirements

Implement a RESTful web service that would handle GET requests to path “weather” by returning the weather data determined by IP of the request originator. 

Upon receiving a request, the service should perform a geolocation search using a non-commercial, 3rd party IP to location provider.

Having performed the reverse geo search service should use another non-commercial, 3rd party service to determine current weather conditions using the coordinates of the IP.

##Non-functional requirements
As mentioned previously, the following list is given in order of priority, you may implement only part of the items (more is better, however).

1. Test coverage should be not less than 80%
2. Implemented web service should be able to handle 3rd party service unavailability
3. Responses from services should be cached in an in-memory cache (please prefer a 3rd party solution)
4. Geolocation data should be stored in a database as a cache
5. DB schema should allow a historical analysis of both queries from a specific IP and of weather conditions for specific coordinates
6. Implement DB schema versioning (please prefer a 3rd party solution).

##Application
Both the source code and an executable jar file should be provided. The executable should have an embedded web-container.