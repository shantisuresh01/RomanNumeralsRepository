#!/bin/sh

# Create a Maven project:
# Run Tests:
mvn test
# Compile source and create a WAR file to later install in Tomcat perhaps.
#mvn clean compile war:war

# Commenting the following because project has been properly Mavenized.
# javac -cp /usr/share/java/lib/junit-4.12.jar Roman.java RomanTest.java
# java -ea -cp .:/usr/share/java/lib/*  org.junit.runner.JUnitCore  RomanTest

# git add .
# git commit -m 
# git push origin dev
# Merge with master:
# git fetch origin master
# git merge master
# git push origin dev:master

