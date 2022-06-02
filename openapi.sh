#!/bin/bash/

# Generate blown-out java code based on openapi spec file.
# Also generates openapi.yaml file with references injected.  See resource directory.

openapi-generator generate \
     -g spring \
     --library spring-boot \
     -i openapi.yaml \
     -p groupId=com.company.car.inventory \
     -p artifactId=car-inventory \
     -p artifactVersion=1.0.0-SNAPSHOT \
     \
     -p basePackage=com.company.car.inventory \
     -p configPackage=com.company.car.inventory.config \
     -p apiPackage=com.company.car.inventory.api \
     -p modelPackage=com.company.car.inventory.model \
     \
     -p sourceFolder=src/main/gen \
     \
     -p dateLibrary=java8 \
     -p java8=true