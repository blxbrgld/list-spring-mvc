list-spring-mvc
===============

### About

I consider this to be Version 2.0.0 of MyList Application.

### Technologies / Tools

<ul>
<li>Spring Framework</li>
<li>Spring MVC</li>
<li>Spring Security</li>
<li>Hibernate ORM</li>
<li>Hibernate Validator</li>
<li>Hibernate Search</li>
<li>JSP / JSTL</li>
<li>Apache Tiles</li>
<li>jQuery / jQuery UI</li>
</ul>

### What Does It Look Like

Hopefully very soon I'll deploy a sample instance at OpenShift.

### Import In Eclipse/STS

1) Clone Repository
```
git clone https://github.com/blixabargeld/list-spring-mvc.git
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
