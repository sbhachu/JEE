<h1>Demo REST Server</h1>

This project demonstrates how to create a very simple REST server and how to secure it using basic authentication (provided by Spring Security).

To start up:
<ol>
<li>Create an empty database called 'demo' in pgAdmin3 (PostgreSQL Database Management)</li>
<li>Import the project into IntelliJ or your preferred IDE (navigate to the build.gradle file in the import wizard)</li>
<li>Create a Tomcat server instance (tested using Tomcat 8.0.15 only), ensure that you specify the deployment target (GradleDemo.war)</li>
<li>Modify the server.properties file (PostgreSQL server username/password and manager account credentials).</li>
<li>Start the server</li>
</ol>
I have used my local instance of PostgreSQL, but feel free to modify the server.properties file to specify the DB platform of your choice, you will need to modify the JDBC settings and the hibernate dialect.  Additionally you will need to add the JDBC driver dependency to the build.gradle file.<br/>

On startup the database schema is automatically created based on the domain model, and the manager account record is persisted, this is done by the ServerContextListener class.

Please look at the build.gradle file for a full list of dependencies.

Only three endpoints are defined in the UserController:
<ol>
<li><b>GET: http://localhost:8080/api/v1/users</b> <i>returns user list in JSON, secured to default user role.</i></li>
<li><b>GET: http://localhost:8080/api/v1/user/{id}</b> <i>returns user object in JSON, secured to default user role.</i></li>
<li><b>POST: http://localhost:8080/api/v1/user/create</b> <i>creates a new user record and returns object in JSON, NOT secured to allow for anonymoous sign-up</i></li>
</ol>
However, it is very easy to extend the UserController and to add further controllers as needed.

<b>DISCLAIMER: This is just an example, and not intended for deployment to a production server.</b>
