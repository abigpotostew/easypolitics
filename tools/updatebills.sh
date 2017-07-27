### # !/usr/bin/env bash
function prop {
    grep "${1}" my.properties|cut -d'=' -f2
}


THIS_PWD=$(pwd)
CONGRESS=$(prop 'CONGRESS_GIT')
LEGIS=$(prop 'LEGISLATOR_GIT')
DB=$(prop 'DBNAME')
cd $CONGRESS

source /usr/local/bin/virtualenvwrapper.sh
workon congress
set -e
./run fdsys --collections=BILLSTATUS
./run bills

echo "==============================================================="
echo "Bills updated"
echo "==============================================================="

onDeactivate(){
   cd $THIS_PWD
   
   ./gradlew :db:run -PappArgs="['-b', '-d','$CONGRESS/data', '--update', '-b', '$DB']"
}
trap onDeactivate EXIT
deactivate
#cd $THIS_PWD

