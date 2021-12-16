# couchWizards
Magic the gathering card database implementation of project0. Jonah and Billy's work.

#Couch Wizards 
A service that allows users to quickly read from and write to a database of Magic the Gathering cards using various PUT-like requests.

# Features
Card objects with (Name, money value, super type, sub type, power, toughness, a description, color/mana cost, printSet, and a unique ID assigned to them by the service)
- Add a card to the database
- Fetch a card from the database
- Fetch all cards from the database
- Update cards from the database
- Delete cards from the database

# Technologies Used
- AWS RDS
- Java 8
- Postman (for testing with different requests)
- Mockito
- Junit
- Jackson
- Javax Servlets
- PostresSQL

# Setup
In order to set this up you need to run the database script included in the files here in a postgresSQL server. Then, download the cardORM from https://github.com/211101-java-react-enterprise/couchWizards_ORM and package it into a jar. After that, setup a db.properties file with the following syntax
url=YOURCARDSCHEMA'SURL
username=YOURDBUSERNAME
password=YOURDBPASSWORD

# Contributors
Jonah Landry 
William "Billy" Nelson 
