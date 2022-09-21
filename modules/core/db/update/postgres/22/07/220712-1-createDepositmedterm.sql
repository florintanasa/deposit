create table DEPOSIT_DEPOSITMEDTERM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ACCENUMB varchar(10) not null,
    DEPOSIT varchar(30) not null,
    YEARSTORE varchar(8),
    YEARMULTI varchar(4),
    MULTIPLY varchar(2),
    YEARGERM varchar(4),
    PERCENTAGE varchar(3),
    STOCK varchar(10),
    SUMSTOCK varchar(12),
    --
    primary key (ID)
);