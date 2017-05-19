### Problem description

After invoking services POST method multiple times (~8) service (any method) becomes unresponsive. Seems that database connections are not released by the transaction manager.

### Start Tomcat

`rm -rf apache-tomcat-7.0.78; unzip apache-tomcat-7.0.78.zip; echo "export JAVA_OPTS=\"-Dcom.arjuna.ats.jta.recovery.XAResourceRecovery1=com.arjuna.ats.internal.jdbc.recovery.BasicXARecovery\;abs://C:\home\still\Documents\jbosstm\transactional-driver-with-tomcat-and-h2-issue-reproducer\src\main\resources\h2recoveryproperties.xml\ \;1\"" > $TOMCAT_HOME/bin/setenv.sh ; chmod +x $TOMCAT_HOME/bin/catalina.sh`
`JPDA_SUSPEND=y ./apache-tomcat-7.0.78/bin/catalina.sh jpda run`


### Build and deploy the reproducer

`mvn clean package; rm -rf apache-tomcat-7.0.78/webapps/reproducer/ ; cp target/reproducer.war apache-tomcat-7.0.78/webapps/`

### Get strings from the database

`curl http://localhost:8080/reproducer`

### Save string to the database

`curl --data "test" http://localhost:8080/reproducer`
