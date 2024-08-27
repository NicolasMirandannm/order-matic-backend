// !! important !!
// make sure to this script be same of the infrastructure/mongo-entrypoint/init.js

db = db.getSiblingDB('admin');

db.auth("admin", "admin");

db = db.getSiblingDB('security');

db.createUser(
    {
        user: "admin-security",
        pwd: "admin",
        roles: [ { role: "dbOwner", db: "security" } ]
    }
);

db.createCollection("customer_user");