# Assurity Consulting
A brief description of what this project does.

## Installation
Go to github https://github.com/Nikita161992/Assurity and run the following command in your system
git clone https://github.com/Nikita161992/Assurity.git

## Tech Stack
Java
Rest Assured
BDD Cucumber
Gradle Build Tool

## Run Locally
Clone the project using the command provided in the Installation section. From the local machine run command, go to the location where the code is available and run command: gradle clean build

## Features
An API has been automated
https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false

On a successful response the following acceptance criteria is validated.

Name = "Carbon credits" CanRelist = true The Promotions element with Name = "Gallery" has a Description that contains the text "Good position in category"

Feature file : assurity.feature has been created which is located at : assurity\src\test\resources\features\assurity.feature

Cucumber Runner: A runner file is also available which is located at : assurity\src\test\java\runner\CucumberRunner.java
