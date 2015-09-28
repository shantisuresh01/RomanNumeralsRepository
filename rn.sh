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
# Get code into OpenShift cloud
# git remote add openshift ssh://560951960c1e667f6600005e@romannumeral-cybersecurity.rhcloud.com/~/git/romannumeral.git/
# git checkout master
# git checkout -n openshift
# git push openshift HEAD:master -f 
# To deploy app to OpenShift,  check out the local openshift branch and merge master branch with it, then force push to OpenShift, however -f may not be required for the next pushes:

# git checkout openshift
# git merge --no-ff master
# git push openshift HEAD:master -f

# How to replace master entirely with dev
# git checkout dev
# git merge -s ours master
# git checkout master
# git merge dev
# git push upstream master
# git checkout dev

