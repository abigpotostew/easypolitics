#!/usr/bin/env bash

FILE_OUT=my.properties

echo "CONGRESS_GIT='<get unitedstates/congress>'" > ${FILE_OUT}
echo "SHELL=/bin/zsh" >> ${FILE_OUT}
echo "LEGISLATOR_GIT='<us/congress-legislators>" >> ${FILE_OUT}
echo "DBNAME='<db name>' >> ${FILE_OUT}

echo "Default my.properties created. Place this in the project root directory. Execute scripts from tools dir from root directory."
