<h1>OAuth2 Secured REST Server</h1>

This project demonstrates how to create a very simple REST server and how to secure it using OAuth2 authentication (provided by Spring Security).

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

Only two endpoints are defined in the UserController:
<ol>
<li><b>GET: http://localhost:8080/api/v1/users</b> <i>returns user list in JSON, secured to default user role.</i></li>
<li><b>GET: http://localhost:8080/api/v1/user/{id}</b> <i>returns user object in JSON, secured to default user role.</i></li>
</ol>
However, it is very easy to extend the UserController and to add further controllers as needed.

In order to access the secured REST end points, the user must provide a valid access token.

<b>Basic Authorization Header (Pseudo-Code):</b><br/>
```
Base64.encode(client-id + ":" + secret);
```

<b>Single Sign-In:</b><br/>
```
curl -v -X POST \
   -H "Content-Type: application/json" \
   -H "Authorization: Basic Y2xpZW50OnNlY3JldA==" \
   'http://localhost:8080/oauth/token?grant_type=password&username=manager@gradledemo.com&password=5f4dcc3b5aa765d61d8327deb882cf99'
```

<b>Response:</b><br/>
```
{
    "access_token": "53bfa41f-b85c-4a42-8e7a-1d83acefd9e7",
    "token_type": "bearer",
    "refresh_token": "b44c1157-cbfc-4856-92d5-3d6251560f19",
    "expires_in": 9,
    "scope": "read trust write"
}
```
<br/>The above refresh token should be stored by the client application, so the access token can be refreshed as needed.
<br/><br/>The response shows that the access token is valid for 10 seconds.  This valid access token can be used to access a secured REST end-point.

<b>Access REST End-Point:</b><br/>
```
curl -v -X GET \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer 53bfa41f-b85c-4a42-8e7a-1d83acefd9e7" \
  'http://localhost:8080/api/v1/users'
```

<b>Response:</b><br/>
```
[
    {
        "id": 1,
        "name": "manager@gradledemo.com"
        "firstName": "manager",
        "lastName": "manager",
        "email": "manager@gradledemo.com",
        "role": "ROLE_ADMIN",
        "enabled": true,
        "lastLogin": 1417787570936,
        "dateCreated": 1417787570936,
        "dateModified": 1417787570936,
    }
]
```

<b>Error Response:</b><br/>
```
{
    "error": "invalid_token",
    "error_description": "Access token expired: 53bfa41f-b85c-4a42-8e7a-1d83acefd9e7"
}
```
<p>If the access token validity expires, a new token can be requested using the refresh-token mechanism.</p>

<b>Refresh Token:</b><br/>
```
curl -v -X POST \
   -H "Content-Type: application/json" \
   -H "Authorization: Basic Y2xpZW50OnNlY3JldA==" \
   'http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=b44c1157-cbfc-4856-92d5-3d6251560f19'
```

<p>This requires a more detailed write-up and I'll get around to doing that eventually, sufficed to say this lays the ground work for creating an OAuth 2 secured REST server.</p>

<b>DISCLAIMER: This is just an example, and not intended for deployment to a production server.</b>
