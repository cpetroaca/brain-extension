#!/bin/bash
set -e

STANBOL_STABLE_LAUNCHER="launchers/stable/target"
STANBOL_RUNTIME_HOME=stanbol
STANFORD_CONFIG=config
STANFORD_NLP_LAUNCHER="server/target"

MAX_RETRIES=100

if [ -z "$STANBOL_HOME" ]
then
	printf "[ERROR] The STANBOL_HOME environment variable is not set."
	exit 1
fi

if [ ! -d "$STANBOL_HOME/$STANBOL_STABLE_LAUNCHER" ]
then
	printf "[ERROR] Stanbol has not been compiled."
	exit 1
fi

if [ -z "$STANFORD_NLP_HOME" ]
then
	printf "[ERROR] The STANFORD_NLP_HOME environment variable is not set."
	exit 1
fi

if [ ! -d "$STANFORD_NLP_HOME/$STANFORD_NLP_LAUNCHER" ]
then
	printf "[ERROR] Stanford nlp for Stanbol has not been compiled."
	exit 1
fi

if [ "$1" == "clean" ]
then
	if [ -d $STANBOL_RUNTIME_HOME ]
	then
		printf "Removing stanbol cache\n"
		rm -rf $STANBOL_RUNTIME_HOME
	fi
	
	if [ -d $STANFORD_CONFIG ]
	then
		printf "Removing stanford nlp config\n"
		rm -rf $STANFORD_CONFIG
	fi
fi

printf "Starting Stanford NLP for Stanbol"
java -Xmx3g -jar $STANFORD_NLP_HOME/$STANFORD_NLP_LAUNCHER/stanbol-stanfordnlp-server-1.1.0-SNAPSHOT-jar-with-dependencies.jar -p 8081 > stanford-nlp.out 2>&1 &
printf $! > stanford-nlp.pid

#wait for stanford nlp to be up and running
STANFORD_NLP_RETRY_CNT=0
while true; do
	let STANFORD_NLP_RETRY_CNT=STANFORD_NLP_RETRY_CNT+1

	if grep -q " ... server started" stanford-nlp.out
	then
		break;
	fi
	
	if [ $STANFORD_NLP_RETRY_CNT -gt $MAX_RETRIES ]
	then
		printf "[ERROR] Stanford nlp failed to start in the given amount of time"
		
		if [ -f stanford-nlp.pid ]; then
			STANFORD_NLP_PID=`cat stanford-nlp.pid`
			kill -1 $STANFORD_NLP_PID
			echo "Stanford NLP for Stanbol stopped"
			rm -rf stanford-nlp.pid
		fi
		
		exit 1
	fi
	
	printf "."
	sleep 1
done	

printf "\nStanford NLP for Stanbol started\n"

printf "Starting stanbol"
java -Xmx1g -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1044 $STANBOL_HOME/$STANBOL_STABLE_LAUNCHER/org.apache.stanbol.launchers.stable-1.0.1-SNAPSHOT.jar > stanbol.out 2>&1 &
printf $! > stanbol.pid

#wait for stanbol to be up and running
STANBOL_RETRY_CNT=0
while true; do
	let STANBOL_RETRY_CNT=STANBOL_RETRY_CNT+1
	
	if grep -q "Startup completed" stanbol.out
	then
		break;
	fi
	
	if [ $STANBOL_RETRY_CNT -gt $MAX_RETRIES ]
	then
		printf "[ERROR] Stanbol failed to start in the given amount of time"
		
		if [ -f stanford-nlp.pid ]; then
			STANFORD_NLP_PID=`cat stanford-nlp.pid`
			kill -1 $STANFORD_NLP_PID
			echo "Stanford NLP for Stanbol stopped"
			rm -rf stanford-nlp.pid
		fi

		if [ -f stanbol.pid ]; then
			STANBOL_PID=`cat stanbol.pid`
			kill -1 $STANBOL_PID
			echo "Stanbol stopped"
			rm -rf stanbol.pid
		fi

		rm -rf stanford-nlp.out
		exit 1
	fi
	
	printf "."
	sleep 1
done

printf "\nStanbol started\n"

printf "Brain Extension Framework READY"