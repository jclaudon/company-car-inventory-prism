# K8-spring-boot-microservice
Creating a spring boot application that will run on AWS using ECR and EKS.

The service itself is a simple CRUD service that returns information about a Company's car inventory.

# Docker Build
Warning: If you use this approach, the image will not be accessable to kubernetes.

To build artifact and image via maven (in same directory as pom): ``sh ./docker/docker-maven-build.sh``

Run locally on docker: ``docker compose up``

# K8s Build
To build image: ``./docker/docker-cli-build.sh``

To deploy to K8s locally: ``./kubernetes/kubernetes-apply.cmd``


# Swagger Page
UI URL (with default port): ``http://localhost:8080/swagger-ui.html``