Setup Annotation Processors
===========================

Eclipse
-------

* Go to "project properties" (i.e. right click on project in package explorer and select last option)
  - Go to "java compiler"
    - Go to "annotation processing"
      - Check "enable project specifing settings"
      - Set "generated sources directory" to "target/generated-sources/annotations"
      - Add "Processor option" "defaultOverwrite" with value "true"
      - Go to "annotation path"
        - Check "enable project specifing settings"
        - Do following procedure:
           1. Click "add variable...", select M2\_REPO
           2. Click "edit..." and add one of the followings:
             + M2\_REPO/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.0.Final/hibernate-jpa-2.0-api-1.0.0.Final.jar
             + M2\_REPO/org/hibernate/hibernate-jpamodelgen/${hibernate-jpamodelgen.version}/hibernate-jpamodelgen-${hibernate-jpamodelgen.version}.jar
             + M2\_REPO/org/hibernate/hibernate-validator-annotation-processor/${hibernate-validator.version}/hibernate-validator-annotation-processor-${hibernate-validator.version}.jar
             + M2\_REPO/org/hibernate/hibernate-validator/${hibernate-validator.version}/hibernate-validator-${hibernate-validator.version}.jar
             + M2\_REPO/javax/validation/validation-api/1.0.0.GA/validation-api-1.0.0.GA.jar
             + M2\_REPO/com/mysema/querydsl/querydsl-jpa/${querydsl.version}/querydsl-jpa-${querydsl.version}-apt-hibernate-one-jar.jar
