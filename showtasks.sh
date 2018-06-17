#!/usr/bin/env bash

if ./runcrud.sh;
then open -a "Google Chrome" http://localhost:8088/crud/v1/task/getTasks
else
echo "runcrud niewykonany"
fi