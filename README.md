list-spring-mvc Application 3.0
===============================

### Changes From Version 2.0

Bootstap 3 added to the game. Nearly everything redesigned and now the application is fully mobile friendly. Furthermore, all administrator pages are now under the /admin path so that we can separate the logic of the front-end and the back-end. This will help, for example, to create more easily a Varnish template for the application, which i'm planning to do very soon.

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
</ul>

### What Does It Look Like

Hopefully very soon I'll deploy a sample instance at OpenShift.

### Import In Eclipse/STS

1) Clone Repository
```
git clone https://github.com/blxbrgld/list-spring-mvc.git
```
2) Import
```
File -> Import -> Maven -> Existing Maven project
```
3) Create Database
```
src/main/resources/database/mysql/ contains MySQL Dumps of a sample database and database schema without data
```
4) Create src/main/resources/database/database.properties File
```
dataSource.driverClassName, dataSource.url, dataSource.username, dataSource.password are the minimum requirements
```
5) Create src/main/resources/miscellaneous.properties File
```
filepath.lucene, filepath.images, author.email are the minimum requirements
```
