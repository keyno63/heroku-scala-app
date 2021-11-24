-- CREATE TABLE
CREATE TABLE IF NOT EXISTS issues (
    id SERIAL NOT NULL PRIMARY KEY,
    summary VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL
);

-- INSERT DATA
INSERT INTO issues (summary, description) VALUES ('概要1', '説明1');
INSERT INTO issues (summary, description) VALUES ('概要2', '説明2');
INSERT INTO issues (summary, description) VALUES ('概要3', '説明3');
