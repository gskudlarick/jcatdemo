#!/bin/sh

if [[ $# -eq 0 ]] ; then
    echo 'Convienence script to run java cat'
    echo 'usage:  ./run.sh [file ...]'
    echo 'example:  ./run.sh  ~/.profile'
    exit 0
fi
# runs on mac osx  10.12.6
java -jar target/ks-cat-1.0-SNAPSHOT.jar "$@"

