drop database if exists skincare;

create database if not exists skincare;

use skincaretest;

create table finish (
	finishid int auto_increment primary key,
    finishtype varchar(20) not null
);


create table coverage (
	coverageid int auto_increment primary key,
    coveragetype varchar(20)
);

create table concern (
	concernid int auto_increment primary key,
    concerntype varchar(50) not null
);

create table category (
	categoryid int auto_increment primary key,
    categorytype varchar(100) not null
);

create table base (
	baseid int auto_increment primary key,
    basetype varchar(20) not null
);

create table attribute (
	attributeid int auto_increment primary key,
    attributename varchar(100) not null
);

create table ingredient (
	ingredientid int auto_increment primary key,
    ingredientname varchar(500) not null
);

create table brand (
	brandid int auto_increment primary key,
    brandname varchar(100) not null,
    crueltyfree boolean default 0
);

create table skintype (
	skintypeid int auto_increment primary key,
    skintypename varchar(20)
);

create table product (
	itemnumber varchar(50) primary key,
	productname varchar(100) not null,    
    details varchar(5000),
    price decimal (8,2) not null,
    brandid int not null,
    categoryid int not null,
    baseid int not null,
    finishid int,
    url varchar(500),
    imageurl varchar(500),
    constraint fk_product_brand
    foreign key (brandid)
    references brand(brandid),
    constraint fk_product_category
    foreign key (categoryid)
    references category(categoryid),
    constraint fk_product_base
    foreign key (baseid)
    references base(baseid),
    constraint fk_product_finish
    foreign key (finishid)
    references finish(finishid)
);

create table preference (
	preferenceid int auto_increment primary key,
    crueltyfree boolean default 0,
    coverageid int,
    finishid int,
    itemnumber varchar(50),
    skintypeid int,
    constraint fk_preference_skintype
    foreign key (skintypeid)
    references skintype(skintypeid),
    constraint fk_preference_coverage
    foreign key (coverageid)
    references coverage(coverageid),
    constraint fk_preference_finish
    foreign key (finishid)
    references finish(finishid),
    constraint fk_preference_product
    foreign key (itemnumber)
    references product(itemnumber)
);

create table productcoverage (
productcoverageid int auto_increment primary key,
itemnumber varchar(50) not null,
coverageid int not null,
constraint fk_productcoverage_product
foreign key (itemnumber)
references product(itemnumber),
constraint fk_productcoverage_coverage
foreign key (coverageid)
references coverage(coverageid)
);

create table productconcern (
	productconcernid int auto_increment primary key,
	concernid int not null,
    itemnumber varchar(50) not null,
    constraint fk_productconcern_concern
    foreign key (concernid)
    references concern(concernid),
    constraint fk_productconcern_product
    foreign key (itemnumber)
    references product(itemnumber)    
);


create table productskintype (
	productskintypeid int auto_increment primary key,
    itemnumber varchar(50) not null,
    skintypeid int not null,
    constraint fk_productskintype_product
    foreign key (itemnumber)
    references product(itemnumber),
    constraint fk_productskintype_skintype
    foreign key (skintypeid)
    references skintype(skintypeid)
);

create table preferenceconcern (
	preferenceconcernid int auto_increment primary key,
	preferenceid int not null,
	concernid int not null,
    constraint fk_preferenceconcern_preference
    foreign key (preferenceid)
    references preference(preferenceid),
    constraint fk_guestconcern_concern
    foreign key (concernid)
    references concern(concernid)
);

create table guest (
	guestid varchar(50) primary key,
    guestname varchar(100) not null,
    password varchar(200) not null,
    preferenceid int,
    administrator boolean default 0,
    constraint fk_guest_preference
    foreign key (preferenceid)
    references preference(preferenceid)
);

create table guestproduct (
	guestid varchar(50) not null,
    itemnumber varchar(50) not null,
    constraint fk_guestproduct_guest
    foreign key (guestid)
    references guest(guestid),
    constraint fk_guestproduct_product
    foreign key (itemnumber)
    references product(itemnumber)
);

create table ingredientattribute (
	ingredientattributeid int auto_increment primary key,
	ingredientid int not null,
    attributeid int not null,
    constraint fk_ingredientattribute_attribute
    foreign key (attributeid)
    references attribute(attributeid),
    constraint fk_ingredientattribute_ingredient
    foreign key (ingredientid)
    references ingredient(ingredientid)
);

create table ingredientcompatibility (
	ingredientcompatibilityid int auto_increment primary key,
    ingredientid int not null,
    ingredientid2 int not null,
    rating int not null,
    constraint fk_ingredientcompatibility_ingredient
    foreign key (ingredientid)
    references ingredient(ingredientid),
    constraint fk_ingredientcompatibility_ingredient2
    foreign key (ingredientid2)
    references ingredient(ingredientid)
);

create table ingredientbase (
	ingredientbaseid int auto_increment primary key,
    ingredientid int not null,
    baseid int not null,
    constraint fk_ingredientbase_ingredient
    foreign key (ingredientid)
    references ingredient(ingredientid),
    constraint fk_ingredientbase_base
    foreign key (baseid)
    references base(baseid)
);

create table productingredient (
	productingredientid int auto_increment primary key,
    ingredientid int not null,
    itemnumber varchar(50) not null,
    constraint fk_productingredient_product
    foreign key (itemnumber)
    references product(itemnumber),
    constraint fk_productingredient_ingredient
    foreign key (ingredientid)
    references ingredient(ingredientid)
);

create table preferenceattribute (
	preferenceattributeid int auto_increment primary key,
	preferenceid int not null,
    attributeid int not null,
    constraint fk_preferenceattribute_preference
    foreign key (preferenceid)
    references preference(preferenceid),
    constraint fk_preferenceattribute_attribute
    foreign key (attributeid)
    references attribute(attributeid)
);

create table guestskincareset (
guestskincaresetid int auto_increment primary key,
guestid varchar(50),
itemnumber1 varchar(50),
itemnumber2 varchar(50),
itemnumber3 varchar(50),
constraint fk_guestskincareset_guestid
foreign key (guestid)
references guest(guestid),
constraint fk_guestskincareset_itemnumber1
foreign key (itemnumber1)
references product(itemnumber),
constraint fk_guestskincareset_itemnumber2
foreign key (itemnumber2)
references product(itemnumber),
constraint fk_guestskincareset_itemnumber3
foreign key (itemnumber3)
references product(itemnumber)
);

create table guestmakeupset (
guestmakeupsetid int auto_increment primary key,
guestid varchar(50),
itemnumber1 varchar(50),
itemnumber2 varchar(50),
constraint fk_guestmakeupset_guestid
foreign key (guestid)
references guest(guestid),
constraint fk_guestmakeupset_itemnumber1
foreign key (itemnumber1)
references product(itemnumber),
constraint fk_guestmakeupset_itemnumber2
foreign key (itemnumber2)
references product(itemnumber)
);
