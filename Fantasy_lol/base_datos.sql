CREATE DATABASE FANTASYLOL;

USE FANTASYLOL;

-- Tabla de usuarios
CREATE TABLE USUARIO (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);

-- Tabla de equipos fantasy
CREATE TABLE FANTASY_TEAM (
    id_fantasy INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

-- Tabla de equipos profesionales
CREATE TABLE EQUIPO (
    id_equipo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla de jugadores reales
CREATE TABLE JUGADOR (
    id_jugador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    rol ENUM('TOP', 'JUNGLA', 'MID', 'ADC', 'SUPPORT') NOT NULL,
    id_equipo INT,
    FOREIGN KEY (id_equipo) REFERENCES EQUIPO(id_equipo)
);

-- Tabla de campeones
CREATE TABLE CAMPEON (
    id_campeon INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla de partidas
CREATE TABLE PARTIDA (
    id_partida INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    id_equipo_azul INT NOT NULL,
    id_equipo_rojo INT NOT NULL,
    id_equipo_ganador INT,
    FOREIGN KEY (id_equipo_azul) REFERENCES EQUIPO(id_equipo),
    FOREIGN KEY (id_equipo_rojo) REFERENCES EQUIPO(id_equipo),
    FOREIGN KEY (id_equipo_ganador) REFERENCES EQUIPO(id_equipo)
);


-- Tabla intermedia: estadísticas por jugador en cada partida
CREATE TABLE KDA (
    id_jugador INT,
    id_partida INT,
    id_campeon INT,
    kills INT DEFAULT 0,
    deaths INT DEFAULT 0,
    assists INT DEFAULT 0,
    PRIMARY KEY (id_jugador, id_partida),
    FOREIGN KEY (id_jugador) REFERENCES JUGADOR(id_jugador),
    FOREIGN KEY (id_partida) REFERENCES PARTIDA(id_partida),
    FOREIGN KEY (id_campeon) REFERENCES CAMPEON(id_campeon)
);

-- Tabla plantilla de jugadores en un equipo fantasy
CREATE TABLE PLANTILLA (
    id_fantasy INT,
    id_jugador INT,
    PRIMARY KEY (id_fantasy, id_jugador),
    FOREIGN KEY (id_fantasy) REFERENCES FANTASY_TEAM(id_fantasy),
    FOREIGN KEY (id_jugador) REFERENCES JUGADOR(id_jugador)
);

-- INSERCION DE DATOS

-- Insercion de equipos
INSERT INTO EQUIPO (nombre) VALUES
('G2 Esports'),
('Fnatic'),
('Team Vitality'),
('Team Heretics'),
('Movistar KOI'),
('GIANTX'),
('SK Gaming'),
('Karmine Corp'),
('Rogue'),
('Team BDS');


INSERT INTO JUGADOR (nombre, rol, id_equipo) VALUES
-- G2 Esports
('BrokenBlade', 'TOP', 1),
('SkewMond', 'JUNGLA', 1),
('Caps', 'MID', 1),
('Hans Sama', 'ADC', 1),
('Labrov', 'SUPPORT', 1),

-- Fnatic
('Oscarinin', 'TOP', 2),
('Razork', 'JUNGLA', 2),
('Humanoid', 'MID', 2),
('Upset', 'ADC', 2),
('Mikyx', 'SUPPORT', 2),

-- Team Vitality
('Naak Nako', 'TOP', 3),
('Lyncas', 'JUNGLA', 3),
('Czajek', 'MID', 3),
('Carzzy', 'ADC', 3),
('Hylissang', 'SUPPORT', 3),

-- Team Heretics
('Carlsen', 'TOP', 4),
('Sheo', 'JUNGLA', 4),
('Kamiloo', 'MID', 4),
('Flakked', 'ADC', 4),
('Stend', 'SUPPORT', 4),

-- Movistar KOI
('Myrwn', 'TOP', 5),
('Elyoya', 'JUNGLA', 5),
('Jojopyun', 'MID', 5),
('Supa', 'ADC', 5),
('Alvaro', 'SUPPORT', 5),

-- GIANTX
('Lot', 'TOP', 6),
('Closer', 'JUNGLA', 6),
('Jackies', 'MID', 6),
('Noah', 'ADC', 6),
('Jun', 'SUPPORT', 6),

-- SK Gaming
('JNX', 'TOP', 7),
('Isma', 'JUNGLA', 7),
('Reeker', 'MID', 7),
('Rahel', 'ADC', 7),
('Loopy', 'SUPPORT', 7),

-- Karmine Corp
('Canna', 'TOP', 8),
('Yike', 'JUNGLA', 8),
('Vladi', 'MID', 8),
('Caliste', 'ADC', 8),
('Targamas', 'SUPPORT', 8),

-- Rogue
('Adam', 'TOP', 9),
('Malrang', 'JUNGLA', 9),
('Larssen', 'MID', 9),
('Patrik', 'ADC', 9),
('Execute', 'SUPPORT', 9),

-- Team BDS
('Irrelevant', 'TOP', 10),
('113', 'JUNGLA', 10),
('Nuc', 'MID', 10),
('Ice', 'ADC', 10),
('Parus', 'SUPPORT', 10);

-- Insercion campeones
INSERT INTO CAMPEON (nombre) VALUES
('Aatrox'),
('Ahri'),
('Akali'),
('Alistar'),
('Ambessa'),
('Amumu'),
('Anivia'),
('Annie'),
('Aphelios'),
('Ashe'),
('Aurelion Sol'),
('Aurora'),
('Azir'),
('Bard'),
('Bel\'Veth'),
('Blitzcrank'),
('Brand'),
('Braum'),
('Caitlyn'),
('Camille'),
('Cassiopeia'),
('Cho\'Gath'),
('Corki'),
('Darius'),
('Diana'),
('Dr. Mundo'),
('Draven'),
('Ekko'),
('Elise'),
('Evelynn'),
('Ezreal'),
('Fiddlesticks'),
('Fiora'),
('Fizz'),
('Galio'),
('Gangplank'),
('Garen'),
('Gnar'),
('Gragas'),
('Graves'),
('Gwen'),
('Hecarim'),
('Heimerdinger'),
('Illaoi'),
('Irelia'),
('Ivern'),
('Janna'),
('Jarvan IV'),
('Jax'),
('Jayce'),
('Jhin'),
('Jinx'),
('Kai\'Sa'),
('Kalista'),
('Karma'),
('Karthus'),
('Kassadin'),
('Katarina'),
('Kayle'),
('Kayn'),
('Kennen'),
('Kha\'Zix'),
('Kindred'),
('Kled'),
('Kog\'Maw'),
('LeBlanc'),
('Lee Sin'),
('Leona'),
('Lillia'),
('Lissandra'),
('Lucian'),
('Lulu'),
('Lux'),
('Malphite'),
('Malzahar'),
('Maokai'),
('Master Yi'),
('Mel'),
('Miss Fortune'),
('Mordekaiser'),
('Morgana'),
('Nami'),
('Nasus'),
('Nautilus'),
('Neeko'),
('Nidalee'),
('Nilah'),
('Nocturne'),
('Nunu & Willump'),
('Olaf'),
('Orianna'),
('Ornn'),
('Pantheon'),
('Poppy'),
('Pyke'),
('Qiyana'),
('Quinn'),
('Rakan'),
('Rammus'),
('Rek\'Sai'),
('Rell'),
('Renata Glasc'),
('Renekton'),
('Rengar'),
('Riven'),
('Rumble'),
('Ryze'),
('Samira'),
('Sejuani'),
('Senna'),
('Seraphine'),
('Sett'),
('Shaco'),
('Shen'),
('Shyvana'),
('Singed'),
('Sion'),
('Sivir'),
('Skarner'),
('Smolder'),
('Sona'),
('Soraka'),
('Swain'),
('Sylas'),
('Syndra'),
('Tahm Kench'),
('Taliyah'),
('Talon'),
('Taric'),
('Teemo'),
('Thresh'),
('Tristana'),
('Trundle'),
('Tryndamere'),
('Twisted Fate'),
('Twitch'),
('Udyr'),
('Urgot'),
('Varus'),
('Vayne'),
('Veigar'),
('Vel\'Koz'),
('Vex'),
('Vi'),
('Viego'),
('Viktor'),
('Vladimir'),
('Volibear'),
('Warwick'),
('Wukong'),
('Xayah'),
('Xerath'),
('Xin Zhao'),
('Yasuo'),
('Yone'),
('Yorick'),
('Yuumi'),
('Zac'),
('Zed'),
('Zeri'),
('Ziggs'),
('Zilean'),
('Zoe'),
('Zyra');

-- Partidas jugadas, datos ficticios
INSERT INTO PARTIDA (fecha, id_equipo_azul, id_equipo_rojo, id_equipo_ganador) VALUES
('2025-03-29', 1, 2, 1),    -- G2 Esports vs Fnatic
('2025-03-30', 3, 4, 3),    -- Team Vitality vs Team Heretics
('2025-04-05', 5, 6, 5),    -- Movistar KOI vs GIANTX
('2025-04-06', 7, 9, 7),    -- SK Gaming vs Rogue
('2025-04-12', 10, 8, 10),  -- Team BDS vs Karmine Corp
('2025-04-13', 2, 3, 2),    -- Fnatic vs Team Vitality
('2025-04-19', 6, 7, 6),    -- GIANTX vs SK Gaming
('2025-04-20', 9, 10, 9),   -- Rogue vs Team BDS
('2025-04-26', 1, 6, 1),    -- G2 Esports vs GIANTX
('2025-04-27', 5, 2, 5),    -- Movistar KOI vs Fnatic
('2025-06-01', 1, 3, NULL), -- G2 Esports vs Team Vitality (futura)
('2025-06-02', 5, 2, NULL), -- Movistar KOI vs Fnatic (futura)
('2025-06-03', 7, 6, NULL), -- SK Gaming vs GIANTX (futura)
('2025-06-04', 9, 8, NULL), -- Rogue vs Karmine Corp (futura)
('2025-06-05', 10, 4, NULL);-- Team BDS vs Team Heretics (futura)



-- PARTIDA 1: G2 Esports vs Fnatic

-- G2 vs FNC
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(1, 1, 1, 3, 1, 6),   -- BrokenBlade - Aatrox (TOP)
(2, 1, 66, 2, 2, 8),  -- SkewMond - Lee Sin (JUNGLA)
(3, 1, 89, 4, 3, 5),  -- Caps - Orianna (MID)
(4, 1, 93, 6, 0, 4),  -- Hans Sama - Aphelios (ADC)
(5, 1, 96, 0, 1, 12), -- Labrov - Rakan (SUPPORT)
(6, 1, 2, 1, 4, 2),   -- Oscarinin - Camille (TOP)
(7, 1, 67, 1, 5, 6),  -- Razork - Maokai (JUNGLA)
(8, 1, 90, 3, 2, 4),  -- Humanoid - Azir (MID)
(9, 1, 94, 4, 1, 3),  -- Upset - Jhin (ADC)
(10, 1, 97, 0, 3, 10);-- Mikyx - Lulu (SUPPORT)

-- PARTIDA 2: Team Vitality vs Team Heretics
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(11, 2, 3, 2, 3, 5),   -- Naak Nako - Darius (TOP)
(12, 2, 68, 1, 4, 6),  -- Lyncas - Elise (JUNGLA)
(13, 2, 91, 3, 2, 4),  -- Czajek - Syndra (MID)
(14, 2, 95, 5, 1, 3),  -- Carzzy - Kai'Sa (ADC)
(15, 2, 98, 0, 2, 9),  -- Hylissang - Thresh (SUPPORT)
(16, 2, 4, 1, 5, 2),   -- Carlsen - Gnar (TOP)
(17, 2, 69, 2, 3, 5),  -- Sheo - Nocturne (JUNGLA)
(18, 2, 92, 4, 2, 3),  -- Kamiloo - LeBlanc (MID)
(19, 2, 96, 3, 2, 4),  -- Flakked - Jhin (ADC)
(20, 2, 99, 0, 3, 8);  -- Stend - Nautilus (SUPPORT)

-- PARTIDA 3: Movistar KOI vs GIANTX
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(21, 3, 5, 3, 2, 6),   -- Myrwn - Malphite (TOP)
(22, 3, 70, 2, 3, 7),  -- Elyoya - Rek'Sai (JUNGLA)
(23, 3, 93, 5, 1, 4),  -- Jojopyun - Orianna (MID)
(24, 3, 97, 6, 0, 5),  -- Supa - Aphelios (ADC)
(25, 3, 100, 0, 2, 10),-- Alvaro - Rakan (SUPPORT)
(26, 3, 6, 1, 4, 3),   -- Lot - Shen (TOP)
(27, 3, 71, 2, 5, 4),  -- Closer - Kha'Zix (JUNGLA)
(28, 3, 94, 3, 3, 5),  -- Jackies - Azir (MID)
(29, 3, 98, 4, 2, 2),  -- Noah - Kai'Sa (ADC)
(30, 3, 101, 0, 3, 7); -- Jun - Lulu (SUPPORT)

-- PARTIDA 4: SK Gaming vs Rogue
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(31, 4, 7, 2, 3, 5),   -- JNX - Sion (TOP)
(32, 4, 72, 1, 4, 6),  -- Isma - Jarvan IV (JUNGLA)
(33, 4, 95, 4, 2, 3),  -- Reeker - Syndra (MID)
(34, 4, 99, 5, 1, 4),  -- Rahel - Jhin (ADC)
(35, 4, 102, 0, 2, 9), -- Loopy - Thresh (SUPPORT)
(36, 4, 8, 1, 5, 2),   -- Adam - Ornn (TOP)
(37, 4, 73, 2, 3, 5),  -- Malrang - Sejuani (JUNGLA)
(38, 4, 96, 3, 2, 4),  -- Larssen - Orianna (MID)
(39, 4, 100, 4, 2, 3), -- Patrik - Aphelios (ADC)
(40, 4, 103, 0, 3, 8); -- Execute - Leona (SUPPORT)

-- PARTIDA 5: Team BDS vs Karmine Corp
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(41, 5, 9, 2, 3, 5),   -- Irrelevant - Garen (TOP)
(42, 5, 74, 1, 4, 6),  -- 113 - Vi (JUNGLA)
(43, 5, 97, 3, 2, 4),  -- Nuc - Orianna (MID)
(44, 5, 101, 5, 1, 3), -- Ice - Kai'Sa (ADC)
(45, 5, 104, 0, 2, 9), -- Parus - Rakan (SUPPORT)
(46, 5, 10, 1, 5, 2),  -- Canna - Malphite (TOP)
(47, 5, 75, 2, 3, 5),  -- Yike - Lee Sin (JUNGLA)
(48, 5, 98, 4, 2, 3),  -- Vladi - Azir (MID)
(49, 5, 102, 6, 0, 4), -- Caliste - Aphelios (ADC)
(50, 5, 105, 0, 3, 8); -- Targamas - Thresh (SUPPORT)

-- PARTIDA 6: Fnatic vs Team Vitality
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(6, 6, 11, 3, 2, 6),   -- Oscarinin - Renekton (TOP)
(7, 6, 76, 2, 3, 7),   -- Razork - Gragas (JUNGLA)
(8, 6, 99, 5, 1, 4),   -- Humanoid - Syndra (MID)
(9, 6, 103, 6, 0, 5),  -- Upset - Jhin (ADC)
(10, 6, 106, 0, 2, 10),-- Mikyx - Leona (SUPPORT)
(11, 6, 12, 1, 4, 3),  -- Naak Nako - Gnar (TOP)
(12, 6, 77, 2, 5, 4),  -- Lyncas - Sejuani (JUNGLA)
(13, 6, 100, 3, 3, 5), -- Czajek - Orianna (MID)
(14, 6, 104, 4, 2, 2), -- Carzzy - Aphelios (ADC)
(15, 6, 107, 0, 3, 7); -- Hylissang - Nautilus (SUPPORT)

-- PARTIDA 7: GIANTX vs SK Gaming
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(26, 7, 13, 2, 3, 5),  -- Lot - Darius (TOP)
(27, 7, 78, 1, 4, 6),  -- Closer - Elise (JUNGLA)
(28, 7, 101, 3, 2, 4), -- Jackies - Orianna (MID)
(29, 7, 105, 5, 1, 3), -- Noah - Kai'Sa (ADC)
(30, 7, 108, 0, 2, 9), -- Jun - Thresh (SUPPORT)
(31, 7, 14, 1, 5, 2),  -- JNX - Garen (TOP)
(32, 7, 79, 2, 3, 5),  -- Isma - Vi (JUNGLA)
(33, 7, 102, 4, 2, 3), -- Reeker - Syndra (MID)
(34, 7, 106, 6, 0, 4), -- Rahel - Jhin (ADC)
(35, 7, 109, 0, 3, 8); -- Loopy - Leona (SUPPORT)

-- PARTIDA 8: Rogue vs Team BDS
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(36, 8, 15, 2, 3, 5),  -- Adam - Sion (TOP)
(37, 8, 80, 1, 4, 6),  -- Malrang - Jarvan IV (JUNGLA)
(38, 8, 103, 3, 2, 4), -- Larssen - Syndra (MID)
(39, 8, 107, 5, 1, 3), -- Patrik - Jhin
(40, 8, 110, 0, 2, 10), -- Execute - Rakan (SUPPORT)
(41, 8, 16, 2, 2, 4),   -- Irrelevant - Camille (TOP)
(42, 8, 81, 2, 3, 6),   -- 113 - Rengar (JUNGLA)
(43, 8, 104, 4, 2, 3),  -- Nuc - Orianna (MID)
(44, 8, 108, 5, 1, 2),  -- Ice - Aphelios (ADC)
(45, 8, 111, 0, 3, 9);  -- Parus - Leona (SUPPORT)

-- PARTIDA 9: G2 Esports vs GIANTX
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(1, 9, 17, 4, 1, 5),    -- BrokenBlade - Renekton (TOP)
(2, 9, 82, 3, 2, 6),    -- SkewMond - Viego (JUNGLA)
(3, 9, 105, 6, 0, 4),   -- Caps - Azir (MID)
(4, 9, 109, 5, 1, 3),   -- Hans Sama - Jhin (ADC)
(5, 9, 112, 0, 2, 11),  -- Labrov - Lulu (SUPPORT)
(26, 9, 18, 2, 3, 4),   -- Lot - Gnar (TOP)
(27, 9, 83, 1, 4, 5),   -- Closer - Lee Sin (JUNGLA)
(28, 9, 106, 3, 2, 4),  -- Jackies - Syndra (MID)
(29, 9, 110, 4, 2, 2),  -- Noah - Aphelios (ADC)
(30, 9, 113, 0, 3, 7);  -- Jun - Thresh (SUPPORT)

-- PARTIDA 10: KOI vs Fnatic
INSERT INTO KDA (id_jugador, id_partida, id_campeon, kills, deaths, assists) VALUES
(21, 10, 19, 2, 2, 5),  -- Myrwn - Sion (TOP)
(22, 10, 84, 2, 2, 7),  -- Elyoya - Jarvan IV (JUNGLA)
(23, 10, 107, 5, 1, 3), -- Jojopyun - Orianna (MID)
(24, 10, 111, 6, 0, 2), -- Supa - Jinx (ADC)
(25, 10, 114, 0, 2, 12),-- Alvaro - Leona (SUPPORT)
(6, 10, 20, 1, 3, 4),   -- Oscarinin - Malphite (TOP)
(7, 10, 85, 2, 3, 6),   -- Razork - Maokai (JUNGLA)
(8, 10, 108, 3, 2, 5),  -- Humanoid - Azir (MID)
(9, 10, 115, 4, 1, 3),  -- Upset - Kai'Sa (ADC)
(10, 10, 116, 0, 3, 9); -- Mikyx - Nautilus (SUPPORT)
 







