#!/bin/bash

docker run -p 8081:8081 appservice
docker run -p 8082:8082 nlpservice
docker run -p 3030:3030 -e ADMIN_PASSWORD=admin --volumes-from fuseki-data stain/jena-fuseki