#!/bin/bash
docker run -d -p 3030:3030 -e ADMIN_PASSWORD=admin --volumes-from fuseki-data stain/jena-fuseki
docker run -d -p 8082:8082 nlpservice
docker run -d -p 8081:8081 appservice