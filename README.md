### Problem description

After invoking services POST method multiple times (~8) service (any method) becomes unresponsive. Seems that database connections are not released by the transaction manager.

### Build the reproducer

`mvn clean package`

### Deploy reproducer to your Tomcat instance

`cp target/reproducer.war $TOMCAT_HOME/webapps/`

### Get strings from the database

`curl http://localhost:8080/reproducer`

### Save string to the database

`curl --data "test" http://localhost:8080/reproducer`
