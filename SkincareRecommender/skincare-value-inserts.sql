use skincare;
select * from ingredient;
-- SELECT * FROM Product p 
-- INNER JOIN ProductSkinType pst ON p.itemnumber = pst.itemnumber
-- INNER JOIN Brand b ON p.brandid = b.brandid
-- INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber 
-- WHERE (p.categoryid = 10 AND pc.coverageid = 4 AND p.finishid = 1 AND p.baseid = 2 AND pst.skintypeid = 1) AND p.itemnumber NOT IN( 
-- 	SELECT pi.itemnumber
--     FROM ProductIngredient pi
-- 	INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid 
-- 	INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid 
--     WHERE ia.attributeid = 7);
    
-- WHERE (p.categoryid = 10 AND p.finishid = 1 AND p.baseid = 2 AND pst.skintypeid = 1);
-- SELECT * FROM Product p INNER JOIN ProductIngredient pi ON pi.itemnumber = p.itemnumber INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid INNER JOIN ProductConcern pc ON p.itemnumber = pc.itemnumber INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber WHERE (ia.attributeid NOT IN (7) AND pc.concernid IN (1) AND pst.skintypeid = 2);

insert into skintype (skintypename) values ("Dry"),("Oily"),("Combo");
insert into skintype (skintypename) values ("Normal"),("Sensitive");
insert into finish (finishtype) values ("Matte"),("Natural"),("Illuminating");
insert into coverage(coveragetype) values ("Light"),("Medium"),("Full");
insert into concern (concerntype) values ("Acne"),("Anti-Aging"),("Pores"),("Dryness"),("Fine lines & wrinkles"),("Dullness");
insert into coverage(coveragetype) values ("n/a");
insert into concern (concerntype) values ("n/a");
insert into brand (brandname, crueltyfree) values ("Babo Botanicals", true),
("Derma E", true),
("Elemis", true),
("Frank Body", true),
("H2O Plus", true),
("John Masters Organic", true),
("NO7", true),
("Pacifica", true),
("Patchology", true),
("Perricone MD", true),
("Shea Moisture", true),
("Skin Laundry", true),
("skyn ICELAND", true),
("Tarte", true),
("Tree Hut", true),
("Trilogy", true),
("Tula", true),
("Anastasia Beverly Hills", true),
("bareMinerals", true),
("Becca", true),
("butter LONDON", true),
("Buxom", true),
("Cover FX", true),
("Dose of Colors", true),
("Essence", true),
("Eyeko", true),
("Fiona Stiles", true),
("Flower", true),
("Grande Cosmetics", true),
("IT Cosmetics", true),
("Japonesque", true),
("LA Girl", true),
("NYX", true),
("Physician's Formula", true),
("Sleek", true),
("Smashbox", true),
("Stila", true),
("Too Faced", true),
("Urban Decay", true);

insert into attribute (attributename) values
 ("DEA/MEA/TEA"),("Mineral Oil"),("Parabens"),("Phthalates"),("Synthetic Fragrances"),("Retinol"),("Gluten"),("Dairy"),("Nuts");
 
 insert into base (basetype) values ("Water"),("Silicone"),("Oil");
 
 insert into category (categorytype) values 
 ("Cleanser"),
 ("Moisturizer"),
 ("Exfoliator"),
 ("Serum"),
 ("Face Oil"),
 ("Toner"),
 ("Face Mask"),
 ("Eye Cream");
 
 -- insert gluten ingredients
 insert into ingredient (ingredientname) values 
 ("Hordeum"),("Secale"),("Triticum"),("Colloidal Oatmeal"),
 ("Sodium Stearoyl Oat Protein"),("Barley Extract"),("Cyclodextrin"),("Dextrin palmitate"),
 ("Hydrolyzed malt extract"),("Wheat"),("Yeast extract"),("Gliadin");
 
 
 -- insert retinol ingredients
 insert into ingredient (ingredientname) values 
 ("Retinol"),("Retinyl acetate"),("Retinyl palmitate"),
 ("Retinaldehyde"),("Propionic acid"),("Retinoid");
 
 -- insert dea ingredients
 insert into ingredient (ingredientname) values
 ("diethoanolamine"),("monoethanolamide"),("triethanolamine"),
 ("DEA"),("MEA"),("TEA");
 
 -- insert mineral oil ingredients
 insert into ingredient (ingredientname) values
 ("Mineral oil"),("Deobase"),("Paraffin"),("Petrolatum"),("Prolatum oil"),("Petroleum");
 
 -- insert paraben ingredients
 insert into ingredient (ingredientname) values 
 ("methylparaben"),
 ("ethylparaben"),
 ("butylparaben"),
 ("propylparaben");
 
 insert into ingredient (ingredientname) values ("phthalate");
 
 insert into ingredient (ingredientname) values 
 ("Fragrance"),("Parfum");
 
 insert into ingredient (ingredientname) values
 ("Prunus amara"),("Prunus dulcis"),
 ("Bertholletia excelsa"),("Anacardium occidentale"),
 ("Corylus rostrata"),("Corylus ramericana"),("Corylus avellana"),
 ("Macadamia ternifolia"),("Arachis hypogaea"),("Pistacia vera"),
 ("Juglans regia"),("Juglans nigra"),("arachis oil"),("arachis oil"),
 ("arachidyl behenate");
 
 
 -- insert dairy ingredients
 insert into ingredient (ingredientname) values
 ("Lac"),("Lactoferrin"),("lactoperoxidase");
 
 insert into ingredientattribute (ingredientid, attributeid) values
 (1,7),(2,7),(3,7),(4,7),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(11,7),(12,7),
 (13,6),(14,6),(15,6),(16,6),(17,6),(18,6),(19,1),(20,1),(21,1),
 (22,1),(23,1),(24,1),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),
 (31,3),(32,3),(33,3),(34,3),(35,4),(36,5),(37,5),(38,9),
 (39,9),(40,9),(41,9),(42,9),(43,9),(44,9),(45,9),(46,9),(47,9),
 (48,9),(49,9),(50,9),(51,9),(52,9),(53,8),(54,8),(55,8);
 

insert into ingredient (ingredientname) values ("Water"),("Aqua"),("Argan Oil"),
("Jojoba Oil"),("Avocado Oil"),("Castor Oil"),("Coconut Oil"),("Rosehip Oil"),
("Evening Primrose Oil"),("Sweet Almond Oil"),("Hemp Seed Oil"),("Marula Oil"),("Olive Oil"),("Camellia Japonica Seed Oil"),("Salvia Hispanica Seed Oil"),("Calophyllum Inophyllum Seed Oil"),
("Pelargonium Graveolens Oil"),("Hippophae Rhamnoides Oil"),("Vitis Vinifera (Grape) Seed Oil"),
("Sesamum Indicum (Sesame) Seed Oil"),("Avena Sativa (Oat) Kernel Oil"),("Prunus Amygdalus Dulcis Oil")
,(" Carthamus Tinctorius (Safflower) Seed Oil"),("Passiflora Edulis Seed Oil"),("Argania Spinosa Oil"),("Pomegranate Seed Oil"),
("Sunflower Seed Oil"),("Sweet Orange Oil"),("Coconut alkanes"),("Simmondsia chinesis"),("Cocos nucifera"),("Moringa oleifera"),
("Cannabis Sativa Seed Oil"),("Rosa Canina Seed Oil"),("Zea Mays Oil"),("Salvia Hispanica Oil"),("Calophyllum inophyllum seed oil"),("Silicone"),
("Dimethicone"),("Cyclopenasiloxane"),("Cyclohexasiloxane"),("Methicone"),("Cyclomethicone"),("Dimethacone copolyl");
 
insert into ingredientbase (ingredientid,baseid) values (50,2),(25,2),(56,1),(57,1),(58,2),(59,2),(60,2),(61,2),(62,2),(63,2),
(64,2),(65,2),(66,2),(67,2),(68,2),(69,2),(70,2),(71,2),(72,2),(73,2),(74,2),(75,2),(75,2),(76,2),(77,2),(78,2),(79,2),(80,2),
(81,2),(82,2),(83,2),(84,2),(85,2),(86,2),(87,2),(88,2),(89,2),(90,2),(91,2),(92,2),(93,3),(93,3),(94,3),(95,3),(96,3),(97,3),
(98,3),(99,3);
insert into category (categorytype) values ("Foundation"),("Primer");
insert into ingredient (ingredientname) values ("Water/Aqua/Eau");

select * from ingredient;
insert into ingredientbase (ingredientid, baseid) values
(149,2),(150,2),(151,2),(132,2),(129,2),(119,2),(107,2),(108,2),(167,2),(169,2)
,(175,2),(194,2),(218,2),(236,2),(163,3),(246,1);
insert into finish (finishtype) values ("n/a");

insert into ingredientcompatibility(ingredientid,ingredientid2,rating) values
(310,221,1),(310,220,1),(310,222,1),(310,223,1),(310,224,1),(310,225,1),(310,226,1),(310,227,1),
(310,228,1),(310,229,1),(310,230,1),(310,995,1),(310,820,1),(310,823,1),(310,932,1),(310,983,1),(310,314,1),
(310,363,1),(310,834,1),(310,326,1),(310,400,1),(310,160,-1),(310,360,-1),(310,541,-1),(310,945,-1),(310,755,-1),(310,627,-1),
(390,221,1),(390,220,1),(390,222,1),(390,223,1),(390,224,1),(390,225,1),(390,226,1),(390,227,1),
(390,228,1),(390,229,1),(390,230,1),(390,995,1),(390,820,1),(390,823,1),(390,932,1),(390,983,1),(390,314,1),
(390,363,1),(390,834,1),(390,326,1),(390,400,1),(390,160,-1),(390,360,-1),(390,541,-1),(390,945,-1),(390,755,-1),(390,627,-1),
(895,221,1),(895,220,1),(895,222,1),(895,223,1),(895,224,1),(895,225,1),(895,226,1),(895,227,1),
(895,228,1),(895,229,1),(895,230,1),(895,995,1),(895,820,1),(895,823,1),(895,932,1),(895,983,1),(895,314,1),
(895,363,1),(895,834,1),(895,326,1),(895,400,1),(895,160,-1),(895,360,-1),(895,541,-1),(895,945,-1),(895,755,-1),(895,627,-1),
(485,221,1),(485,220,1),(485,222,1),(485,223,1),(485,224,1),(485,225,1),(485,226,1),(485,227,1),
(485,228,1),(485,229,1),(485,230,1),(485,995,1),(485,820,1),(485,823,1),(485,932,1),(485,983,1),(485,314,1),
(485,363,1),(485,834,1),(485,326,1),(485,400,1),(485,160,-1),(485,360,-1),(485,541,-1),(485,945,-1),(485,755,-1),(485,627,-1),
(755,157,1),(755,546,1),(755,161,1),(755,361,1),(755,312,1),(755,679,1),
(755,765,1),(755,495,1),(755,945,-1),(755,160,-1),(755,627,-1),(755,13,-1),(755,14,-1),(755,15,-1),(755,16,-1),(755,17,-1),(755,18,-1),
(13,495,1),(13,161,1),(13,361,1),(13,312,1),(13,266,1),(13,250,1),(13,103,1),(13,464,1),(13,310,-1),(13,390,-1),(13,895,-1),(13,485,-1),
(14,495,1),(14,161,1),(14,361,1),(14,312,1),(14,266,1),(14,250,1),(14,103,1),(14,464,1),(14,310,-1),(14,390,-1),(14,895,-1),(14,485,-1),
(15,495,1),(15,161,1),(15,361,1),(15,312,1),(15,266,1),(15,250,1),(15,103,1),(15,464,1),(15,310,-1),(15,390,-1),(15,895,-1),(15,485,-1),
(16,495,1),(16,161,1),(16,361,1),(16,312,1),(16,266,1),(16,250,1),(16,103,1),(16,464,1),(16,310,-1),(16,390,-1),(16,895,-1),(16,485,-1),
(17,495,1),(17,161,1),(17,361,1),(17,312,1),(17,266,1),(17,250,1),(17,103,1),(17,464,1),(17,310,-1),(17,390,-1),(17,895,-1),(17,485,-1),
(18,495,1),(18,161,1),(18,361,1),(18,312,1),(18,266,1),(18,250,1),(18,103,1),(18,464,1),(18,310,-1),(18,390,-1),(18,895,-1),(18,485,-1),
(627,400,1),(627,326,1),(627,475,1),(627,110,1),(627,585,1),(627,157,1),(627,647,1),(627,666,1),(627,365,1),(627,290,1),(627,783,1),
(627,744,1),(627,484,1),(627,161,1),(627,361,1),(627,312,1),(627,495,1),(627,765,1),(627,161,1),(627,546,1),(627,13,-1),(627,14,-1),
(627,15,-1),(627,16,-1),(627,17,-1),(627,18,-1),
(160,400,1),(160,326,1),(160,475,1),(160,110,1),(160,585,1),(160,157,1),(160,647,1),(160,666,1),(160,365,1),(160,290,1),(160,783,1),
(160,744,1),(160,484,1),(160,161,1),(160,361,1),(160,312,1),(160,495,1),(160,765,1),(160,161,1),(160,546,1),(160,13,-1),(160,14,-1),
(160,15,-1),(160,16,-1),(160,17,-1),(160,18,-1),
(945,400,1),(945,326,1),(945,475,1),(945,110,1),(945,585,1),(945,157,1),(945,647,1),(945,666,1),(945,365,1),(945,290,1),(945,783,1),
(945,744,1),(945,484,1),(945,161,1),(945,361,1),(945,312,1),(945,495,1),(945,765,1),(945,161,1),(945,546,1),(945,13,-1),(945,14,-1),
(945,15,-1),(945,16,-1),(945,17,-1),(945,18,-1);




SELECT 
    *
FROM
    Product p
        INNER JOIN
    productskintype pst ON p.itemnumber = pst.itemnumber
WHERE
    (pst.skintypeid = 1
        AND p.itemnumber NOT IN (SELECT 
            pi.itemnumber
        FROM
            ProductIngredient pi
                INNER JOIN
            Ingredient i ON pi.ingredientid = i.ingredientid
                INNER JOIN
            IngredientAttribute ia ON i.ingredientid = ia.ingredientid
        WHERE
            ia.attributeid = 7)
        AND p.itemnumber IN (SELECT 
            pc.itemnumber
        FROM
            ProductConcern pc
        WHERE
            pc.concernid = 3));

SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND p.baseid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true) AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid = " + result + ");
select * from ingredient where ingredientname = "Retinol";
select i.ingredientname, i2.ingredientname from product p 
INNER JOIN productingredient pi ON p.itemnumber = pi.itemnumber 
INNER JOIN ingredientcompatibility ic ON pi.ingredientid = ic.ingredientid
INNER JOIN ingredient i ON ic.ingredientid = i.ingredientid
INNER JOIN ingredient i2 on ic.ingredientid2 = i2.ingredientid
inner join productingredient pi2 on i2.ingredientid = pi2.ingredientid
inner join product p2 on pi2.itemnumber = p2.itemnumber 
WHERE p.itemnumber = 2508736 and p2.itemnumber = 2547231;

select p.itemnumber, i.ingredientname from product p 
inner join productingredient pi on p.itemnumber = pi.itemnumber 
inner join ingredient i on pi.ingredientid = i.ingredientid
where p.itemnumber = 2508736;
SELECT * FROM ingredient i 
                INNER JOIN productingredient pi ON i.ingredientid = pi.ingredientid
                WHERE pi.itemnumber = 2508736;
                
                
                select * from guestproduct;
             select * from category;   
                select * from brand;
                SELECT b.brandname, p.categoryid FROM Brand b 
            INNER JOIN Product p ON b.brandid = p.brandid
            -- INNER JOIN ProductCategory pc ON p.itemnumber = pc.itemnumber
            GROUP BY b.brandname, p.categoryid
            HAVING p.categoryid = 9;
            
            SELECT * FROM Product p 
                INNER JOIN Brand b on p.brandid = b.brandid 
                INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber
                INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber
                WHERE (p.categoryid = 10 AND pc.coverageid = 1
                AND p.finishid = 2
                AND p.baseid = ? AND pst.skintypeid = ?);
                
                select * from preference p inner join guest g on g.preferenceid = p.preferenceid;
                select * from guestconcern;
                
                select * from guestskincareset;
-- insert into product(itemnumber,productname,price,brandid,categoryid,url,baseid) values ("2306256","Studio Skin 15 Hour Wear Hydrating Foundation","36.00",36,9,"https://www.ulta.com/studio-skin-15-hour-wear-hydrating-foundation?productId=xlsImpprod3590081",2);

-- update product set imageurl = "https://images.ulta.com/is/image/Ulta/2306256?$tn$" where itemnumber = "2306256";
-- select * from brand;
-- insert into product (itemnumber,productname,price,brandid,categoryid,url,imageurl,baseid) values ("2233964","The Original Photo Finish Smooth & Blur Primer","36.00",36,10,"https://www.ulta.com/original-photo-finish-smooth-blur-primer?productId=xlsImpprod3590041","https://images.ulta.com/is/image/Ulta/2233964?$tn$",2);
-- insert into productskintype (itemnumber,skintypeid) values ("2233964",1),("2233964",2),("2233964",3),("2233964",4),("2233964",5);

--  update product set finishid = 2 where itemnumber = "2233964";

-- select * from guest;
-- insert into guest (guestid,guestname,password,administrator) values ("linaloo","lina","password",true);
-- UPDATE guest SET password = '$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS' WHERE guestid = "linaloo";
-- select * from guest;
-- set sql_safe_updates = 1;
-- update product set categoryid = 9 where itemnumber = 2264063;

use skincaretest;

select * from guest;