Setup Annotation Processors
===========================

Eclipse
-------

* Go to "project properties" (i.e. right click on project in package explorer and select last option)
  - Go to "java compiler"
    - Go to "annotation processing"
      - Check "enable project specifying settings"
      - Set "generated sources directory" to "target/generated-sources/annotations"
      - Add "Processor option" "defaultOverwrite" with value "true"
      - Go to "annotation path"
        - Check "enable project specifying settings"
        - Do following procedure:
           1. Click "add variable...", select M2\_REPO
           2. Click "edit..." and add one of the followings:
             + M2\_REPO/org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.0.Final/hibernate-jpa-2.0-api-1.0.0.Final.jar
             + M2\_REPO/org/hibernate/hibernate-jpamodelgen/1.1.1.Final/hibernate-jpamodelgen-1.1.1.Final.jar
             + M2\_REPO/org/hibernate/hibernate-validator-annotation-processor/4.2.0.Final/hibernate-validator-annotation-processor-4.2.0.Final.jar
             + M2\_REPO/org/hibernate/hibernate-validator/4.2.0.Final/hibernate-validator-4.2.0.Final.jar
             + M2\_REPO/javax/validation/validation-api/1.0.0.GA/validation-api-1.0.0.GA.jar
             + M2\_REPO/com/mysema/querydsl/querydsl-jpa/2.2.4/querydsl-jpa-2.2.4-apt-hibernate-one-jar.jar
