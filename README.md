A simple SpringBoot application that takes bearer token from remote API, 
then uses that token to get a list of Store objects and persists them in the H2 database. 

List of endpoints:

 * GET: localhost:8080/user/login - retrieves or refreshes bearer token from remote API.

 * POST: localhost:8080/store/retrieve - retrieves a list of Store objects from remote API 
using bearer token from previous endpoint and persists them to the database.

 * GET: localhost:8080/store - returns the list of persisted Store objects.

First two endpoints are mainly for testing purposes, as application will automatically do those steps when launched. 
They could also be used to manually update bearer token or the list of persisted Store objects.

This application needs a set of properties to be filled-out in the application.properties file:

 * api.login.path
 * api.stores.path
 * api.key
 * api.client.id
 * api.client.secret
 * api.grant.type

Due to security concerns these properties are empty by deafult.

I have also included a dockerfile, so this app could be run as a docker container.

Command to build and run it using windows cmd: docker build -t app . && docker run -p 8080:8080 --name app app