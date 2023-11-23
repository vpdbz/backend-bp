#!/bin/sh

# stop and remove current container, if it exists
docker rm bp-client-service --force || true

# run detached
docker run -d \
--name bp-client-service \
--add-host=host.docker.internal:host-gateway \
-p 8090:8090 \
-e SERVER_PORT=8090 \
-e DATASOURCE_CONNECTION_TIMEOUT=20000 \
-e DATASOURCE_MAXIMUM_POOL_SIZE=5 \
-e JPA_SHOW_SQL=true \
-e JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true \
-e JPA_PROPERTIES_HIBERNATE_GENERATE_STATISTICS=true \
-e LOGGING_LEVEL_ORG_HIBERNATE_SQL=DEBUG \
-e LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL=TRACE \
-e DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/bpclient \
-e DATASOURCE_DRIVER_CLASSNAME=org.postgresql.Driver \
-e DATASOURCE_USERNAME=bpclient \
-e DATASOURCE_PASSWORD=admin \
bp-client-service:1.0.0

read -p "Press any key to resume ..."