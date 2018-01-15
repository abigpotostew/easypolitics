#!/usr/bin/env bash
function prop {
    grep "${1}" my.properties|cut -d'=' -f2
}


THIS_PWD=$(pwd)
LEGIS=$(prop 'LEGISLATOR_GIT')
DB=$(prop 'DBNAME')
cd $LEGIS

git checkout gh-pages
git pull

cd $THIS_PWD
./gradlew :db:run -PappArgs="['-l', '-f', '$LEGIS/legislators-current.json', '-f', '$LEGIS/legislators-historical.json', '-s', '$LEGIS/legislators-social-media.json', '-b', '$DB']"
#echo "['-l', '-f', '$LEGIS/legislators-current.json', '-f', '$LEGIS/legislators-historical.json', '-s', '$LEGIS/legislators-social-media.json', '-b', '$DB']"
