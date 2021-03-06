list-spring-mvc Application 4.0
===============================

### Changes From Version 3.1.1

Book entity added along with all the needed changes (frontend and backend).

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

### IDE Setup

<ol>
<li><strong>Clone Repository:</strong> git clone https://github.com/blxbrgld/list-spring-mvc.git</li>
<li>Import In Your Favorite IDE (i.e. File -> Import -> Maven -> Existing Maven project)</li>
<li><strong>Create Database: </strong>src/main/resources/database/mysql/ contains MySQL Dumps of a sample database and database schema without data. Alternatively you can run the Liquibase scripts that exist in /src/main/resources/database/liquibase. From version 3.1 and onwards all database changes will be made with Liquibase, so these scripts will be the way to go.</li>
<li>Change the database and filesystem path properties in <strong>src/main/resources/configuration.properties</strong> (and in <strong>src/integration/resources/integration.properties</strong> if you want to execute the integration tests defined with the prod Maven profile).</li>
<li>Build the <strong>Lucene index</strong> (<strong>/admin/administrator/lucene</strong>) in order to be able to search for items and view the listing pages (you need Administrator privileges to do that).</li>
</ol>
