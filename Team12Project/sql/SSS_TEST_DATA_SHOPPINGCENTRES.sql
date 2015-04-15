use supershoppingsurfer_silver;

-- Creating some shoppping centres
INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Obsidian Sanctum',
'obsdnsnctm',
'os@battle.net',
8083,
'The Obsidian Sanctum, a part of the Chamber of Aspects, is a secret chamber where members of the Black Dragonflight would convene whenever they wished to meet.'
);

INSERT INTO shoppingcentre(business_id, address, zipcode, parking_spaces) 
VALUES(
1, 
'Dragonblight',
 7227,
 25);
-- ---------------------------------------------------------------------------------------------------
INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'The Ruby Sanctum',
'thrbsnctm',
'trs@battle.net',
99226645,
'The Ruby Sanctum, a part of the Chamber of Aspects, is a secret meeting place for members of the Red Dragonflight.'
);

INSERT INTO shoppingcentre(business_id, address, zipcode, parking_spaces) 
VALUES(
2,
'Dragonblight',
4260,
50
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Ulduar',
'ldr',
'u@battle.net',
93065598,
'Ulduar is a raid dungeon in the Titan complex of Ulduar, located within the The Storm Peaks. It serves as the prison of the old god Yogg-Saron as well as the current residence of most of the titanic watchers who have fallen under his influence.'
);

INSERT INTO shoppingcentre(business_id, address, zipcode, parking_spaces) 
VALUES(
3, 
'The Storm Peaks',
0001,
75
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Icecrown Citadel',
'ccrwnctdl',
'trs@battle.net',
8083,
'The Icecrown Citadel is the final raid instance in Wrath of the Lich King, and its final boss is none other than Arthas Menethil, the Lich King himself!'
);

INSERT INTO shoppingcentre(business_id, address, zipcode, parking_spaces) 
VALUES(
4,
'Icecrown',
7058, 
100
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Hyrule Castle',
'hrlcstl',
'hyrule@nintendo.com',
13371337,
'Hyrule Castle is the seat of Hyrules monarchical government and the home of the Royal Family of Hyrule, which usually includes Princess Zelda and the normally unseen King of Hyrule.'
);

INSERT INTO shoppingcentre(business_id, address, zipcode, parking_spaces) 
VALUES(
5,
'Hyrule',
7236,
10000
);


