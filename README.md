# getWeather REST API

This is the REST backend service for the getWeather web-client. The application was made using the Spring MVC framework
It connects to the openweathermap.org/api and retrieves weather info to display it.
Also gives support for an autocomplete input with the different available locations

* /weather?q=&lat={lat}&lon={lon}&unit={unit}&langCode={langCode}
       
      Parameters:  
              (lat,lon)     required      Geographical coords

              unit          optional      Units of measurement ['metric','imperial'] , by default its 'metric'
                      
              langCode      optional      Language code for the output , by default its 'en' (English)
              

* /weather?q=&cityName={cityName}&unit={unit}&langCode={langCode}
     
       Parameters:  
              cityName      required       Name of the city and the country code. Example: 'London,UK'
                                           If there is no country code it will be resolved automatically

              unit          optional       Units of measurement ['metric','imperial'] , by default its 'metric'
                      
              langCode      optional       Language code for the output , by default its 'en' (English)
       
      
              
  
* /locations/like?q=&cityName={cityName}
      
      Parameters:
              cityName    required         Name of the city and the country code. Example: 'London,UK'
                                           If there is no country code it will be resolved automatically
                                           
Responses:

* /weather returns the weather info in json format.

       For example a response to /weather?q=&cityName='London,UK' is:
       
              {
              "date":"2021-09-28T22:45:47",
              "city":{
                      "name":"London",
                      "country":"UK"
                      },
              "temperature": {
                    "value":"55.99",
                    "unit":"fahrenheit"
                    },
              "humidity":{
                    "value":"77",
                    "unit":"%"
                    },
              "climate":{
                    "value":"clear sky",
                    "id":null},
              "pressure":{
                    "value":"1020",
                    "unit":"hPa"},
              "cloudiness":"0",
              "wind":{
                      "value":"8.19",
                      "unit":"mph",
                      "name":"Gentle Breeze"
                      }
               }
             
* /locations/like returns an Array of strings in XML format.
  
        For example a response to /locations/like?q=&cityName=Lon is:
        
                 <Strings>
                  <item>Long,TH</item>
                  <item>Loni,IN</item>
                  <item>Lono,PH</item>
                  <item>Lons,FR</item>
                  <item>Lonār,IN</item>
                  <item>Londa,IN</item>
                  <item>Londa,IT</item>
                  <item>Londe,ID</item>
                 </Strings>
        
