example how to run with gradle and command line arguments:
Update any changed data, but test most only, database will not be modified
gw run -PappArgs="['-d','~/congress/data', '--update', '-t']"

Drop the entire existing database and reload
gw run -PappArgs="['-d','~/congress/data', '--reset']"


gw debugRun -PappArgs="['-b','-d', '/Users/stew/Documents/Code/github/congress/data', '--update', '-b', 'congress2', '-C115']"