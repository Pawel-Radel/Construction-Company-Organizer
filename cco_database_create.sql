create table Budget (ID bigint not null auto_increment, amount integer not null, primary key (ID)) engine=MyISAM
create table ConstructionOrder (ID bigint not null auto_increment, addres varchar(255), scheduledEndDate date, startDate date, title varchar(255), primary key (ID)) engine=MyISAM
create table Cost (ID bigint not null auto_increment, amount integer not null, forWhat varchar(255), scheduledtime date, primary key (ID)) engine=MyISAM
create table Income (ID bigint not null auto_increment, amount integer not null, forWhat varchar(255), scheduledTimeToGet date, constructionOrder_ID bigint, primary key (ID)) engine=MyISAM
create table IndicativeCost (ID bigint not null auto_increment, ForWhat varchar(255), amount integer not null, constructionOrder_ID bigint, primary key (ID)) engine=MyISAM
alter table Income add constraint FKi5fc4gr8446iaqqapyfyi2uaj foreign key (constructionOrder_ID) references ConstructionOrder (ID)
alter table IndicativeCost add constraint FK5jldvxo5vmwxqet79y0hqvdn3 foreign key (constructionOrder_ID) references ConstructionOrder (ID)
