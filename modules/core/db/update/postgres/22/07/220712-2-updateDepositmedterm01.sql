alter table DEPOSIT_DEPOSITMEDTERM rename column sumstock to sumstock__u74327 ;
alter table DEPOSIT_DEPOSITMEDTERM rename column stock to stock__u81525 ;
alter table DEPOSIT_DEPOSITMEDTERM add column STOCK integer ;
alter table DEPOSIT_DEPOSITMEDTERM add column SUMSTOCK integer ;
