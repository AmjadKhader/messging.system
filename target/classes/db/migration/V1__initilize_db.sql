CREATE TABLE IF NOT EXISTS users (
    id              SERIAL NOT NULL,
    nickname        varchar(25) NOT NULL,
    visible_name    varchar(25) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS messages (
    id              SERIAL PRIMARY KEY NOT NULL,
    sender          INT NOT NULL,
    receiver        INT NOT NULL,
    text            TEXT NOT NULL,

    FOREIGN key (sender) REFERENCES users(id),
    FOREIGN key (receiver) REFERENCES users(id)
 );