#!/bin/sh

# stop and remove current container, if it exists
docker rm bp-account-service --force || true

# run detached
docker run -d \
--name bp-account-service \
--add-host=host.docker.internal:host-gateway \
-p 8100:8100 \
-e SERVER_PORT=8100 \
-e DATASOURCE_CONNECTION_TIMEOUT=20000 \
-e DATASOURCE_MAXIMUM_POOL_SIZE=5 \
-e JPA_SHOW_SQL=true \
-e JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true \
-e JPA_PROPERTIES_HIBERNATE_GENERATE_STATISTICS=true \
-e LOGGING_LEVEL_ORG_HIBERNATE_SQL=DEBUG \
-e LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL=TRACE \
-e DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/bpaccount \
-e DATASOURCE_DRIVER_CLASSNAME=org.postgresql.Driver \
-e DATASOURCE_USERNAME=bpaccount \
-e DATASOURCE_PASSWORD=admin \
-e APP_CLIENT_URL=http://bp-client-service:8100/api/v1/clientes \
bp-account-service:1.0.0

read -p "Press any key to resume ..."