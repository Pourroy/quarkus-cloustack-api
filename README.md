# quarkus-cloudstack-api

This project uses Quarkus, the Supersonic Subatomic Java Framework.

This API receives requests and in REST end-points to manage Virtual Machines and another resources from your Apache CloudStack Cluster.
The integration with RabbitMQ gives the possibility to enqueue the results to another microservices and applications.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.