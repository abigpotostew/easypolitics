db = db.getSiblingDB('admin');

db.createUser(
  {
    user: "admin",
    pwd: "ADMIN_PWD",
    roles: [ "root" ]
  }
)

db = db.getSiblingDB("DB_NAME");
db.createUser(
  {
    user: "easypolitics",
    pwd: "READONLY_PWD",
    roles: [ { role: "read", db: "DB_NAME" } ]
  }
)

db.createUser(
  {
    user: "roomservice",
    pwd: "READWRITE_PWD",
    roles: [ { role: "readWrite", db: "DB_NAME" } ]
  }
)