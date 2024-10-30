-- CREATE
CREATE TABLE `writer` (
   `email`   varchar(50)   NOT NULL,
   `name`   varchar(20)   NOT NULL,
   `password`   varchar(100)   NOT NULL
);

CREATE TABLE `schedule` (
   `scheduleId`   bigint   NOT NULL,
   `email`   varchar(50)   NOT NULL,
   `content`   varchar(100)   NULL   DEFAULT ' ',
   `name`   varchar(20)   NOT NULL,
   `password`   varchar(100)   NOT NULL,
   `dtcreate`   date   NOT NULL,
   `dtmodify`   date   NOT NULL
);

ALTER TABLE `writer` ADD CONSTRAINT `PK_WRITER` PRIMARY KEY (
   `email`
);

ALTER TABLE `schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
   `scheduleId`
);

-- SELECT
SEL
