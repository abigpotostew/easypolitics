#!/bin/sh

MONGO_BIN=$1
HOST_PORT=$2
JS_FILE="tools/firstimesetupmongo.js"
DB_NAME=$3
ADMIN_PWD=$4
READONLY_PWD=$5
READWRITE_PWD=$6

if [[ -z "$MONGO_BIN" ]]; then
	echo "First argument pointing to mongo binary is empty."
	exit 1
fi

if [[ -z "$HOST_PORT" ]]; then
	echo "Second argument, mongo connection <host>:<port>, is empty."
	exit 1
fi

if [[ -z "$DB_NAME" ]]; then
	echo "Third argument, mongo database name, is empty."
	exit 1
fi

if [[ -z "$ADMIN_PWD" ]]; then
	echo "Fourth argument, desired mongo admin user password, is empty."
	exit 1
fi

if [[ -z "$READONLY_PWD" ]]; then
	echo "Fifth argument, desired mongo read only user password, is empty."
	exit 1
fi

if [[ -z "$READWRITE_PWD" ]]; then
	echo "Third argument, desired mongo read+write user password, is empty."
	exit 1
fi

JS_REPLACE=$(sed -e "s/DB_NAME/$DB_NAME/g" -e "s/ADMIN_PWD/$ADMIN_PWD/g" -e "s/READONLY_PWD/$READONLY_PWD/g" -e "s/READWRITE_PWD/$READWRITE_PWD/g" $JS_FILE)

"$MONGO_BIN" "$HOST_PORT" --eval "$JS_REPLACE"
