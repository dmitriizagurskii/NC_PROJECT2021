CREATE TABLE IF NOT EXISTS netcracker.policy (
    id INT2,
    role_name TEXT NOT NULL,
    resource TEXT NOT NULL,
    algorithm TEXT NOT NULL,
    PRIMARY KEY (id)
);