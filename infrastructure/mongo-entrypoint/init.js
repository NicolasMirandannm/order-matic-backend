db.createUser(
    {
        user: "admin-security",
        pwd: "admin",
        roles: [ { role: "readWrite", db: "security" } ]
    }
)