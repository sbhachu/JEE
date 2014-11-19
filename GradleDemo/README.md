<h1>Demo REST Server</h1>

This project demonstrates how to create a very simple REST server and how to secure it using basic authentication (provided by Spring Security).

To start up:
<ol>
<li>Create an empty database called 'Demo' in pgAdmin3 (PostgreSQL DB Management)</li>
<li>Import the project into IntelliJ (navigate to the build.gradle file in the import wizard)</li>
<li>Create a Tomcat server instance (tested using Tomcat 8.0.15 only), ensure that you specify the deployment target (GradleDemo.war)</li>
<li>Start the server</li>
</ol>
I have used my local instance of PostgreSQL, but feel free to modify the server.properties file to specify the DB platform of your choice, you will need to modify the JDBC settings and the hibernate dialect.<br/>

Please look at the build.gradle file for a full list of dependencies.


