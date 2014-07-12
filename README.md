jbpm6-spring
============
Jbpm6 integration with Spring
Here I have created a sample project for JBPM6 integration with spring.
This project is enabled H2 in memory database and PostgreSQL database. Based on DB choice, you can comment/uncomment lines at jbpm-application-config.xml, persistence.xml and ProcessEngineTest.java.

If you want to run with postgresql DB, do the following steps.
- Install postgres DB
- create a database (eg, name:jbpmdb)
- copy ddl script and run to postgresdb, script is available in this directory src\main\resources\META-INF\spring\ddl-script\jbpm-schema-postgresql.sql

For H2 Embedded DB: There is no configuration for H2, just run the test case.
-Run the testcase ProcessEngineTest.java, you will get success result.

Results:
========
username : suresh
==============================================================
Process started: 11
==============================================================
==============================================================
Task Id: 31 Task Name <<User Task 1>>
==============================================================
==============================================================
Task Id: 32 Task Name <<User Task 2>>
==============================================================