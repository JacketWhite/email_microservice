# email_microservice


As a developer, I want to be able to send an email in the background without dealing with the email sending logic. 


"Given there is a client.
When it  would like to use my microservice.
Then it has to have an API key for its usage 
And the microservice cannot be used without or with the wrong API key.

Given there is an endpoint..
When it receives a to and from email address a subject and a message
Then an email will be sent in the background.
And it gives back errors when it receives wrong email formats or missing data. 

Given there is a database.
Then it stores the clients' API key, header & footer links, text RGB color.
When the email will be sent, then it will be formatted with this properties."