jbpm6-spring
============

Jbpm6 integration with Spring
Here I have created a sample project for JBPM6 integration with spring.
This project is enabled H2 in memory database and PostgreSQL database. Based on DB you can comment/uncomment lines at jbpm-application-config.xml, persistence.xml and ProcessEngineTest.java.

If you want to run with postgresql DB, do the following steps.
- Install postgres DB
- create a database (name:jbpmdb)
- copy ddl script to postgresdb, script is available in this directory src\main\resources\META-INF\spring\ddl-script\jbpm-schema-postgresql.sql

For H2 Embedded DB
-Run the testcase ProcessEngineTest.java, you will get success result.
