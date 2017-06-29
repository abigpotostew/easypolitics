#!/usr/bin/env bash
function prop {
    grep "${1}" my.properties|cut -d'=' -f2
}

set -e

THIS_PWD=$(pwd)
CONGRESS=$(prop 'CONGRESS_GIT')
LEGIS=$(prop 'LEGISLATOR_GIT')
cd $CONGRESS

source /usr/local/bin/virtualenvwrapper.sh
workon congress
./run fdsys --collections=BILLSTATUS
./run bills

echo "==============================================================="
echo "Bills updated"
echo "==============================================================="

deactivate
cd $THIS_PWD

./gradlew :db:run -PappArgs="['-b', '-d','$CONGRESS/data', '--update', '-b', 'congress2']"
gw :db:run -PappArgs="['-l', '-f', '$LEGIS/alternate_formats/legislators-current.json', '-s', '$LEGIS/alternate_formats/legislators-social-media.json', '-b', 'congress2']"
#exit 0
