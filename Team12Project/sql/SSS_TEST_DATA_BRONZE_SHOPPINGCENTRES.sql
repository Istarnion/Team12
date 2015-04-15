use supershoppingsurfer_bronze;

-- Creating some businesses
INSERT INTO business (business_name, business_name_dmp, adress, zipcode, email, telephone)
VALUES(
'Obsidian Sanctum',
'obsdnsnctm',
'Dragonblight',
7227,
'os@battle.net',
8083
);

INSERT INTO shoppingcentre(
business_id,
parking_spaces,
text_description
) 
VALUES(1, 25, 'The Obsidian Sanctum, a part of the Chamber of Aspects, is a secret chamber where members of the Black Dragonflight would convene whenever they wished to meet.');


-- ---------------------------------------------------------------------------------------------------
INSERT INTO business (business_name, business_name_dmp, adress, zipcode, email, telephone)
VALUES(
'The Ruby Sanctum',
'thrbsnctm',
'Dragonblight',
7227,
'trs@battle.net',
99226645
);

INSERT INTO shoppingcentre(
business_id,
parking_spaces,
text_description
) 
VALUES(2, 50, 'The Ruby Sanctum, a part of the Chamber of Aspects, is a secret meeting place for members of the Red Dragonflight.');



INSERT INTO business (business_name, business_name_dmp, adress, zipcode, email, telephone)
VALUES(
'Ulduar',
'ldr',
'The Storm Peaks',
7227,
'u@battle.net',
93065598
);

INSERT INTO shoppingcentre(
business_id,
parking_spaces,
text_description
) 
VALUES(3, 75, 'Ulduar is a raid dungeon in the Titan complex of Ulduar, located within the The Storm Peaks. It serves as the prison of the old god Yogg-Saron as well as the current residence of most of the titanic watchers who have fallen under his influence.');


INSERT INTO business (business_name, business_name_dmp, adress, zipcode, email, telephone)
VALUES(
'Icecrown Citadel',
'ccrwnctdl',
'Icecrown',
7227,
'trs@battle.net',
8083
);

INSERT INTO shoppingcentre(
business_id,
parking_spaces,
text_description
) 
VALUES(4, 100, 'The Icecrown Citadel is the final raid instance in Wrath of the Lich King, and its final boss is none other than Arthas Menethil, the Lich King himself!');



