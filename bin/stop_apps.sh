#!/bin/bash
set -e

if [ -f brain-ext.pid ]; then
    printf "Stopping brain extension server\n"
	BRAIN_EXT_PID=`cat stanbol.pid`
	kill -1 $BRAIN_EXT_PID
	printf "Brain extension server stopped\n"
	rm -rf brain-ext.pid
fi

if [ -f stanbol.pid ]; then
    printf "Stopping stanbol\n"
	STANBOL_PID=`cat stanbol.pid`
	kill -1 $STANBOL_PID
	printf "Stanbol stopped\n"
	rm -rf stanbol.pid
fi

if [ -f stanford-nlp.pid ]; then
    printf "Stopping Stanford NLP for Stanbol\n"
	STANFORD_NLP_PID=`cat stanford-nlp.pid`
	kill -1 $STANFORD_NLP_PID
	printf "Stanford NLP for Stanbol stopped\n"
	rm -rf stanford-nlp.pid
fi

if [ -f stanford-nlp.out ]; then
	rm -rf stanford-nlp.out
fi

if [ -f stanbol.out ]; then
	rm -rf stanbol.out
fi

if [ -f brain-ext.out ]; then
	rm -rf brain-ext.out
fi

printf "Brain Extension Framework STOPPED"