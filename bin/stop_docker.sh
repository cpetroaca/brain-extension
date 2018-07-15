#!/bin/bash
docker stop $(docker ps -q --filter ancestor=appservice)
docker stop $(docker ps -q --filter ancestor=nlpservice)
docker stop $(docker ps -q --filter ancestor=stain/jena-fuseki)