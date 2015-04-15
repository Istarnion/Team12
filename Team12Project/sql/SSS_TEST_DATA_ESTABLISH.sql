use supershoppingsurfer_silver;

-- Creating some establishments


-- OS
INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Sartherion',
'srthrn',
'sarth@os.net',
80833232,
'Sartharion is a member of the Black Dragonflight and is in charge of watching over the eggs bred in The Obsidian Sanctum. This chamber also serves as a secret meeting point for the dragons of the Black Dragonflight, located below the Wyrmrest temple in Dragonblight.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
6,
1, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Tenebron',
'tnbrn',
'tenebro@os.net',
12347653,
'Tenebron, a Twilight Drake, is the first lieutenant of Sartharion in The Obsidian Sanctum. She strongly despises Sartharion, yet she obeys his command.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
7,
2, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Shadron',
'shdrn',
'shadron@os.net',
88776655,
'Shadron, a Twilight Drake, is the second lieutenant of Sartharion in The Obsidian Sanctum. He strongly despises Sartharion, yet he obeys his command.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
8,
2, 
2
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Vesperon',
'vsprn',
'vespo@os.net',
43871699,
'Vesperon, a twilight drake, is the third lieutenant of Sartharion in The Obsidian Sanctum. He strongly despises Sartharion, yet he obeys his command.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
9,
2, 
3
);





-- ----------------------------------------------------------------------------------------------------
-- RB
INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Halion',
'hln',
'halion@rs.net',
3148467,
'Halion the Twilight Destroyer is a Twilight dragon, trusted commander of Deathwing himself. He was sent to the Ruby Sanctum along with his three lieutenants Baltharus the Warborn, General Zarithrian, and Saviana Ragefire to steal the Red Dragonflights eggs.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
10,
3, 
1
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Blatharus',
'blthrs',
'baltharus@rs.net',
87164900,
'Baltharus the Warborn is one of Halions lieutenants in The Ruby Sanctum.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
11,
3, 
1
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Zarithrian',
'zrthrn',
'zarithrian@rs.net',
37188193,
'General Zarithrian is one of Halions lieutenants in The Ruby Sanctum.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
12,
3, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Saviana Ragefire',
'svnrgfr',
'saviana@rs.net',
31425364,
'Saviana Ragefire is one of Halions lieutenants in The Ruby Sanctum.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
13,
3, 
1
);





-- -------------------------------------------------------------------------------------------------
-- ulduar

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Yogg-Saron',
'yggsrn',
'yogg@ulduar.com',
666,
'Long ago the titans imprisoned Yogg-Saron deep within Ulduar to save Azeroth from the Old Gods destructive power. But after years of plotting, Yogg-Saron has succeeded in corrupting its guards and now the monstrous creature is finally breaking free.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
14,
4, 
13
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'General Vezax',
'gnrlvzx',
'general@ulduar.com',
555,
'Strange creatures known as faceless ones lurk in the depths of Ulduar. One of their mightiest commanders, General Vezax, guards the twisted passages leading to the Prison of Yogg-Saron.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
15,
4, 
12
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Freya',
'fr',
'freya@ulduar.com',
555,
'The watcher Freya was formerly a protector of all living things, aided by three stoic elders. Though her conservatory remains lush and verdant, she has also succumbed to the Old Gods maddening presence.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
16,
5, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Hodir',
'hdr',
'hodir@ulduar.com',
333,
'The giant Hodir once presided over the Temple of Winter, lending his guidance to the frost giants in the Storm Peaks. Now the watcher dwells in an ice cave in the Halls of Winter, forced to serve the will of Yogg-Saron.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
17,
5, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Mimiron',
'mmrn',
'mimi@ulduar.com',
222,
'A brilliant inventor, Mimiron has been responsible for several of the most advanced mechanisms on Azeroth. But ever since he was subjected to Lokens disturbed influence, this watcher of Ulduar has only constructed war machines.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
18,
5, 
2
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Ignis',
'gns',
'ignis@ulduar.com',
111,
'Like the other creations in Ulduar, Ignis now serves the Old God Yogg-Saron. This imposing fire giant toils over the Colossal Forge, creating the iron armies that will conquer Azeroth in Yogg-Sarons name.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
19,
6, 
2
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Flame Leviathan',
'flmlvthn',
'leviathan@ulduar.com',
888,
'This massive armored tank guards the courtyard entrance in Ulduar. The watcher Mimiron constructed the Flame Leviathan as part of his V0-L7R-0N weapons platform.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
20,
6, 
2
);



-- ---------------------------------------------------------------------------------------------------
-- ICC

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Sindragosa',
'sndrgs',
'sindy@icc.biz',
83619463,
'Sindragosa was Malygos last consort from the time before his madness and the devastation of the Blue Dragonflight. When Neltharion betrayed the other Dragonflights and decimated the Blues, Sindragosa was separated from Malygos, and sent far into the frozen north, where her last thoughts were ones of anger, hatred, and revenge. When she was recently raised by the Lich King, she became one of his most fearsome servants.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
21,
7, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Valithria',
'vlthr',
'valithria@icc.biz',
888,
'She is a green dragon captured by the Scourge, likely used in experiments to reproduce the Emerald Nightmare effect.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
22,
7, 
2
);



INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Blood Prince Council',
'bldprnccncl',
'prince@icc.biz',
31452635,
'The princes Keleseth, Taldaram, and Valanar, easily dispatched by adventurers, now make their return in the Crimson Hall of Icecrown Citadel, refreshed and empowered. The Council needs to be defeated in order to gain access to the Royal Quarters.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
23,
8, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Lanathel',
'lnthl',
'lanathel@icc.biz',
8823138,
'Former countess, now Blood Queen of all Sanlayn, Lanathel was one of the blood elves who followed Kaelthas lead into Northrend, in pursuit of Arthas after he tainted the Sunwell. She was the one that brought Thalorien Dawnseekers famous blade - QuelDelar - from the commanders dying place at the Dead Scar into the frozen north. When she and some of her friends were slain by the Scourge, Lanathel was raised by the Lich King as a Sanlayn, or a Darkfallen, to lead the undead armies in Northrend and to lead the other Sanlayn.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
24,
8, 
2
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Festergut',
'fstrgt',
'festergut@icc.biz',
23232323,
'Festergut is one of the two abomination bosses in the Plagueworks section of Icecrown Citadel that players need to defeat in order to unlock Professor Putricides laboratory and fight him there.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
25,
9, 
1
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Rotface',
'rtfc',
'rotface@icc.biz',
23423423,
'Rotface is one of the two abomination bosses in the Plagueworks section of Icecrown Citadel that players need to defeat in order to unlock Professor Putricides laboratory and fight him there.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
26,
9, 
1
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Professor Putricide',
'prfptrcd',
'profput@icc.biz',
882228,
'Professor Putricide is the Scourge genius behind the development of all forms of blight, plague, ooze, scourge, and death delivery. He is the final boss in the Plagueworks section of Icecrown Citadel, the first Icecrown wing to be unlocked. In order to reach his laboratory, players must first defeat Festergut and Rotface.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
27,
9, 
2
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Arthas',
'rths',
'thaliach@icc.biz',
123,
'The Lich King, Arthas Menethil, is the final boss of the Icecrown Citadel 10 and 25-player raid dungeon.'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
28,
10, 
100
);


-- ------------------------------------------------------------------------------------
-- HYRUL

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Bomb shop',
'bmbshp',
'bombs@hyrule.info',
126663,
'Bombs and bombs and bombs and bombs and bombs and bombs and bombs and bombs and bombs and'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
29,
11, 
1
);

INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Magic Shop',
'mgshop',
'magics@hyrule.info',
126663,
'More magics'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
30,
11, 
1
);


INSERT INTO business (business_name, business_name_dmp, email, telephone, text_description)
VALUES(
'Links Leisure Loft',
'lll',
'lll@hyrule.info',
126622263,
'Link laxing'
);

INSERT INTO establishment(business_id, building_id, floor_number)
VALUES(
31,
11, 
3
);





