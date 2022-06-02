# build image via maven cli, bypasses Dockerfile
# Warning, Kubernetes has issues accessing images created by this command.

# Point mvnw to absolute path of mvnw in this directory if it can't be found with relative path
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=car-inventory-service-image