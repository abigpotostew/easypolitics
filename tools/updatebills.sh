#!/usr/bin/env bash
function prop {
    grep "${1}" my.properties|cut -d'=' -f2
}

THIS_PWD=$(pwd)
CONGRESS=$(prop 'CONGRESS_GIT')
cd $CONGRESS

workon congress
./run fdsys --collections=BILLSTATUS
./run bills

echo "==============================================================="
echo "Bills updated"
echo "==============================================================="

deactivate
cd $THIS_PWD

./gradlew :db:run -PappArgs="['-b', '-d','$CONGRESS/data', '--update', '-b', 'congress2']"

#exit 0