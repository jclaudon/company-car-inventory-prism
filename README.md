# K8-spring-boot-microservice
Creating a spring boot application that will run on AWS using ECR and EKS.

The service itself is a simple CRUD service that returns information about a Company's car inventory.

## Generate code
While the code should generate at build time, if you want to see your edits between builds run: ``sh ./openapi.sh`` to regenerate the java code and resources/openapi.yaml file with your new changes.

## Docker Build
Warning: If you use this approach, the image will not be accessable to kubernetes.

To build artifact and image via maven (in same directory as pom): ``sh ./docker/docker-maven-build.sh``

Run locally on docker: ``docker compose up``

## K8s Build
To build image: ``sh ./docker/docker-cli-build.sh``

To deploy to K8s locally: ``sh ./kubernetes/apply.sh``

To update image after creation and auto-deploy out to kubernetes: ``sh ./kubernetes/update-image.sh``
### Redploy to K8s
If you make code changes and you want to see them reflected in K8s, rebuild the image and redeploy to K8s.

## Swagger Page
UI URL (with default port): ``http://localhost:8080/swagger-ui.html``

