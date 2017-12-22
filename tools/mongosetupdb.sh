#!/bin/sh

MONGO_BIN=$1
#echo 1
HOST_PORT=$2
#echo 2
JS_FILE="tools/firstimesetupmongo.js"
DB_NAME=$3
#echo 3
ADMIN_PWD=$4
READONLY_PWD=$5
READWRITE_PWD=$6

JS_REPLACE=$(sed -e "s/DB_NAME/$DB_NAME/g" -e "s/ADMIN_PWD/$ADMIN_PWD/g" -e "s/READONLY_PWD/$READONLY_PWD/g" -e "s/READWRITE_PWD/$READWRITE_PWD/g" $JS_FILE)
#echo pizza

"$MONGO_BIN" "$HOST_PORT" --eval "$JS_REPLACE"
