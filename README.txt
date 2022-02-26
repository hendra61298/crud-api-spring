
List Requirement ( Java 11, DB Postgress, Springboot 2.6.4)

How To Run Apps 
1. Run Project Spring boot 
2. Hit endpoint login using email : henhen@gmail.com, password : 1234733 as role admin
after that, it will generate access token for hitting another endpoint. 
3. with admin account, we can create account, add movie to list, update detail movie
4. Using endpoint {{base_url}}/api/movie/home that can get movie list data for home page(1).
5. Using endpoint {{base_url}}/api/movie/detail that can get movie detail data for detail page(2).



notes:
1. I also attach postman_collection in project 


Postman :
Nostra Backend Test.postman_collection.json

Swagger Link : 
{{baseUrl}}/swagger.html