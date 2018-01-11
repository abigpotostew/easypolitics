This is a personal project and work in progress. It's not really intended for anyone to use but feel free to read the source.

frontend, backend, and rest servlet for us legislative bills


Other dependencies:
- scss `gem install sass`
- mongodb `brew install mongodb`


Runtime setup:
1. Creating the database
    - Create a mongo db at a specific directory.
        - For example using mongo shell run `mongod --dbpath <db directory>`
2. Start the mongo database
    - `./gradlew startdb`
3. Get the congress data by downloading the congress git repos.
    - legislators, using branch `gh-pages` from repo `https://github.com/unitedstates/congress-legislators`
    - congress `https://github.com/unitedstates/congress`
        - follow instructions so that you can run `./run fdsys --collections=BILLSTATUS` and `./run bills` which will download bill data to the data directory.
4. Create the my.properties
    - copy `sample-my.properties` as my.properties
    - set the properties to your mongo db and your git repo, follow instructions in the file.
5. Populate your local mongo database collections
    - bills: Either run `tools/updatebills.sh` after setting up the correct properties in my.properties, or read through this simple script to find the gradle command with proper parameters to update your mongo database bills from the congress git data.
    - legislators: Similarly to create the legislators collection, run `tools/updatelegislators.sh` after setting up the correct properties in my.properties, or find the command within the script.
6. Start the rest api server:
    - `./gradlew rest:bootRun`
7. Start the web server:
    - `./gradlew web:runWeb`
8. Open up your browser to `localhost:<web serving port, default 4567>`