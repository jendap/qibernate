Qibernate Demo
==============

Do not use this! It's a pile of crap. It just shows multiple technologies.
But it's not a good demo project as it show how to do one thing multiple ways
in one project. You'd better not be writing DAO five different ways
within single project ;-)

Fell free to ask how to use it or what can you learn here!

Note: Prefer Spring Boot ecosystem for these kind of things. It is fantastic
from about 2015.


Run
---

```bash
# test, package, and verify
mvn verify

# run web services war file (prefer spring boot jar deployment if only you can)
mvn -am -pl qibernate-cxf verify -P run-war -DskipTests

# run main class of one of the subprojects
mvn -am -pl qibernate-spring verify -P run -DskipTests
```


History
-------

This project was started as show how hibernate works. It was done for people from small company which name started on Q.
It evolved into showcase of much more technologies:

* hibernate / jpa
  - hql
  - jpa query api
  - hibernate criteria api
  - custom serialization
  - custom validation
* related stuff
  - tomcat jdbc connection pool
  - ehcache cache
  - querydsl
* spring
  - dependency injection
  - transactions
  - spring data
* web service (using apache cxf)
  - soap
  - rest
* maven
  - ton of things demonstrated in maven ;-)
  - modules
  - testing
  - packaging
* logging
  - slf4j
* tests
  - junit
  - spring-test
* lombok

And perhaps even more - there are so many that it's hard to get a complete list ;-)
