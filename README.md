# eTicketor

# Welcome to eTicketor API

This API enables hosts to create events and event goers to purchase ticket for the events online

Tools required to run the application are:

#### IntelliJ IDEA

#### smtp4dev (fake smtp server) https://github.com/rnwood/smtp4dev/releases/download/3.2.0-ci20221023104/Rnwood.Smtp4dev-win-x64-3.2.0-ci20221023104.zip

#### MySQL Database

#### Dependency with route mapping, rate limiting and circuit breaking. https://github.com/DennisMatambanadzo/CloudGateway

****************


The entities involved are:

User,
Ticket,
Event.

# User

### Registration

This system houses users with three different roles: User, Admin and Host.

For each, registration requires a firstname, lastname, email, and password.

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/a0577231-3bfa-4c49-9f28-1c350f001b48)

The registration provides the following response:

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/86737ff8-97ad-4ada-87d4-cefd856f5b7e)

The next step is to check the stmp server for an email with a provided token and copy the token to verify the email.

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/7852de4b-6f4d-4f87-ad96-7c932c8a9aa8)

The email verification process takes place by copying the token and pasting it into the *verify email* endpoint

![img_1.png](img_1.png)

At that point the user email is verified.

### Login

After verification the user can log in.

The system supports three points of entry for the users depending on the role the registered for.

![img_2.png](img_2.png)

To login the user enters their email and password.

If the email exists in the system and the password matches the API will respond with a JWT token 
that will be used to authenticate and authorize the user for each endpoint they attempt to access.

![img_3.png](img_3.png)

If both conditions above are not met the API responds with a Bad Request (400) message

![img_4.png](img_4.png)


********

# Events

**Only users with the HOST role can create Events**

![img_5.png](img_5.png)

An event object has to have the following fields:

**Name**

**Event Description**

**Location**

**Start Time**

**End Time**

**Age Restriction**

**Number of Tickets available for purchase**

**Ticket Price**


A successful submission of an event will yield the following response:

![img_6.png](img_6.png)
****

## Ticket

**Only a user with the USER role can purchase a ticket**

![img_7.png](img_7.png)
 The parameters required to purchase a ticket are to provide:

**Event Name**

**Section.**

The API will provide the above response after a successful purchase of a ticket.

![img_8.png](img_8.png)


    
