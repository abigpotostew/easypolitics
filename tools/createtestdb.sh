### # !/usr/bin/env bash
function prop {
    grep "${1}" my.properties|cut -d'=' -f2
}


THIS_PWD=$(pwd)
DB_PATH=$(prop 'DB_PATH')
TEST_BILL_DB_DATA=$(prop 'TEST_BILL_DBDATA')
TEST_LEGISLATOR_DB_DATA=$(prop 'TEST_LEGISLATOR_DBDATA')
TEST_DBNAME=$(prop 'TEST_DB_NAME')

if [[ -z "$DB_PATH" ]]; then
	echo "Property 'DB_PATH' must be defined in my.propertied"
	exit 1
fi

if [[ -z "$TEST_BILL_DB_DATA" ]]; then
	echo "Property 'TEST_BILL_DB_DATA' must be defined in my.propertied"
	exit 1
fi

if [[ -z "$TEST_LEGISLATOR_DB_DATA" ]]; then
	echo "Property 'TEST_LEGISLATOR_DB_DATA' must be defined in my.propertied"
	exit 1
fi

if [[ -z "$TEST_DBNAME" ]]; then
	echo "Property 'TEST_DBNAME' must be defined in my.propertied"
	exit 1
fi

./gradlew :db:run -PappArgs="['-b', '-d','$TEST_BILL_DB_DATA', '--reset', '-b', '$TEST_DBNAME']"
./gradlew :db:run -PappArgs="['-l', '-f', '$TEST_LEGISLATOR_DB_DATA/legislators-current.json', '-s', '$TEST_LEGISLATOR_DB_DATA/legislators-social-media.json', '-b', '$TEST_DBNAME']"
