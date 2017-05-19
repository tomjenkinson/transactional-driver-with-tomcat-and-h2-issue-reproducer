### Description

This quickstart shows how to get started with Narayana and Transactional Driver in a simple JDBC example.

### Start Tomcat

You must add a $TOMCAT_HOME/bin/setenv.sh with the following content:

`export JAVA_OPTS="-Dcom.arjuna.ats.jta.recovery.XAResourceRecovery1=com.arjuna.ats.internal.jdbc.recovery.BasicXARecovery\;abs://$QUICKSTART_HOME/src/main/resources/h2recoveryproperties.xml\ \;1"`

### Build and deploy the reproducer

`mvn clean package; rm -rf apache-tomcat-7.0.78/webapps/reproducer/ ; cp target/reproducer.war apache-tomcat-7.0.78/webapps/`

### Get strings from the database

`curl http://localhost:8080/reproducer`

### Save string to the database

`curl --data "test" http://localhost:8080/reproducer`
