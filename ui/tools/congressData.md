#Setup
sudo pip install virtualenv
sudo pip install virtualenvwrapper --upgrade --ignore-installed six
mkvirtualenv congress
workon congress

#Part 1: initial download
# (run in github folder)
./run fdsys --collections=BILLSTATUS

##Part 2: Update and run every 6 hours
./run bills

## Important to stop using the congress virt env
deactivate
