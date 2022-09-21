alter table DEPOSIT_DEPOSITMEDTERM rename column sumstock to sumstock__u82370 ;
alter table DEPOSIT_DEPOSITMEDTERM rename column stock to stock__u87320 ;
alter table DEPOSIT_DEPOSITMEDTERM rename column percentage to percentage__u49248 ;
alter table DEPOSIT_DEPOSITMEDTERM add column PERCENTAGE integer ;
alter table DEPOSIT_DEPOSITMEDTERM add column STOCK decimal(19, 2) ;
alter table DEPOSIT_DEPOSITMEDTERM add column SUMSTOCK decimal(19, 2) ;
