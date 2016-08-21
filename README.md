list-spring-mvc Application 3.1
===============================

### Changes From Version 2.0

Bootstap 3 added to the game. Nearly everything redesigned and now the application is full mobile friendly. Furthermore, all administrator pages are now under the /admin path so that we can separate the logic of the front-end and the back-end. This will help, among other things, to create more easily a Varnish template for the application, which i'm planning to do very soon. 

Version 3.1 introduced some new Maven plug-ins (Liquibase, SonarQube et.al.) and some changes needed to keep SonarQube happy.

### Technologies / Tools

<ul>
<li>Spring Framework</li>
<li>Spring MVC</li>
<li>Spring Security</li>
<li>Hibernate ORM</li>
<li>Hibernate Validator</li>
<li>Hibernate Search</li>
<li>Apache Tiles</li>
<li>Bootstrap</li>
<li>jQuery / jQuery UI</li>
<li>JUnit / Mockito / Hamcrest</li>
<li>SonarQube</li>
</ul>

### What Does It Look Like

Hopefully very soon I'll deploy a sample instance at OpenShift.

### IDE Setup

<ol>
<li><strong>Clone Repository:</strong> git clone https://github.com/blxbrgld/list-spring-mvc.git</li>
<li>Import In Your Favorite IDE (i.e. File -> Import -> Maven -> Existing Maven project)</li>
<li><strong>Create Database: </strong>src/main/resources/database/mysql/ contains MySQL Dumps of a sample database and database schema without data. Alternatively you can run the Liquibase scripts that exist in /src/main/resources/database/liquibase. From version 3.1 and onwards all database changes will be made with Liquibase, so these scripts will be the way to go.</li>
<li>Change the database and filesystem path properties in <strong>src/main/resources/configuration.properties</strong> (and in <strong>src/integration/resources/integration.properties</strong> if you want to execute the integration tests defined with the prod Maven profile).</li>
</ol>
