# eTicketor

Welcome to eTicketor API

This API enables hosts to create events and event goers to purchase ticket for the events online

Tools required to run the application are:

IntelliJ IDEA

smtp4dev (fake smtp server) https://github.com/rnwood/smtp4dev/releases/download/3.2.0-ci20221023104/Rnwood.Smtp4dev-win-x64-3.2.0-ci20221023104.zip

SQL Database

****************


The entities involved are:

User,
Ticket,
Event.

USER:

This system houses users with three different roles: User, Admin and Host.

For each, registration requires a firstname, lastname, email, and password.

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/a0577231-3bfa-4c49-9f28-1c350f001b48)

The registration provides the following response:

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/86737ff8-97ad-4ada-87d4-cefd856f5b7e)

The next step is to check the stmp server for an email with a token.

![image](https://github.com/DennisMatambanadzo/eTicketor/assets/49873792/7852de4b-6f4d-4f87-ad96-7c932c8a9aa8)

