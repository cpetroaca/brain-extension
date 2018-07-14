#!/bin/bash

docker build -t appservice appservice/web/

#need to git clone sophie in nlpservice
docker build -t nlpservice nlpservice/

docker run --name fuseki-data -v /fuseki busybox