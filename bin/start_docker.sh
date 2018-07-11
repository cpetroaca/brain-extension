#!/bin/bash

docker run -p 8081:8081 appservice
docker run -p 8082:8082 nlpservice

docker run --name fuseki-data -v /fuseki busybox
docker run --name fuseki -p 3030:3030 --volumes-from fuseki-data stain/jena-fuseki