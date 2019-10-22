# Weather service
Uses the request originators ip address to lookup location information from external geolocation service. 
Retrieved coordinates are used to get weather information from an external service.

## External services
1.  Geolocation - https://ipgeolocation.io
2.  Weather -  https://openweathermap.org

## Build
Install lombock plugin and enable annotation processing in IDE. Run gradle task bootJar.

## Running
Use WeatherApplication to launch or using packaged jar from command line
    
    java -jar <jar file>