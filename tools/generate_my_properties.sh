#!/usr/bin/env bash

FILE_OUT=my.properties

echo "DB_PATH=/Users/stew/Documents/db/congress1" > ${FILE_OUT}
echo "CONGRESS_GIT='<get unitedstates/congress>'" >> ${FILE_OUT}
echo "SHELL=/bin/zsh" >> ${FILE_OUT}
echo "LEGISLATOR_GIT='<us/congress-legislators>" >> ${FILE_OUT}

echo "DBNAME='<db name>'" >> ${FILE_OUT}

echo "# Optional test DB setup properties:"
echo "#TEST_DBNAME='<db name>'" >> ${FILE_OUT}
echo "#TEST_BILL_DB_DATA=#optional dir overrides congress_git" >> ${FILE_OUT}
echo "#TEST_LEGISLATOR_DB_DATA=#optional dir overrides legislator db data location" >> ${FILE_OUT}

echo "Default my.properties created. Place this in the project root directory. Execute scripts from tools dir from root directory."
