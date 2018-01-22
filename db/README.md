example how to run with gradle and command line arguments:
Update any changed data, but test most only, database will not be modified
gw run -PappArgs="['-d','~/congress/data', '--update', '-t']"

Drop the entire existing database and reload
gw run -PappArgs="['-d','~/congress/data', '--reset']"


gw debugRun -PappArgs="['-b','-d', '/Users/stew/Documents/Code/github/congress/data', '--update', '-b', 'congress2', '-C115']"



---------
## Notes
### Bill Index

- 1 Text indices
    - + subjects[]
    - + subjects_top_term (higher weight than subjects)
    - + official_title
    - + titles[].title
        - todo party? need to merge this info into bill db
    
- 2 Regular string index
    - + bill_id
    - + bill_type
    - + status
    - + summary.as
    - + sponsor.name
    - + sponsor.state
    - + sponsor.bioguide_id
    - cosponsors[].bioguide_id
    - + cosponsors[].name
    
- 3 Date index
    - + introduced_at : -1 (current first)
    - + status_at : -1 (last major action status date)
    - + updated_at: -1 (?? last time bill data updated maybe??)
    
### Legislator Index
- 1 String index
    - + LegislatorData.id.buiguide
    - LegislatorData.name.official_full
    - LegislatorData.name.first
    - LegislatorData.name.last
