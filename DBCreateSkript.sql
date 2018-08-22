DROP DATABASE IF EXISTS cybermafia;
CREATE DATABASE cybermafia;
USE cybermafia;

CREATE TABLE user (
  username  VARCHAR(20),
  password  VARCHAR(256),
  salt      VARCHAR(256),
  email     VARCHAR(256),
  lastlogin DATETIME,
  CONSTRAINT username_pk
  PRIMARY KEY (username)
);

CREATE TABLE pc_cpu (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  name CHAR(30),
  ghz  FLOAT,
  CONSTRAINT id_cpu_pk
  PRIMARY KEY (id)
);

CREATE TABLE pc_gpu (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  name CHAR(30),
  ghz  FLOAT,
  CONSTRAINT id_gpu_pk
  PRIMARY KEY (id)
);

CREATE TABLE pc_hdd (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  name CHAR(30),
  size BIGINT
);

CREATE TABLE firewall (
  id       INT AUTO_INCREMENT NOT NULL,
  name     CHAR(20),
  security INT,
  CONSTRAINT fw_id_pk
  PRIMARY KEY (id)
);

CREATE TABLE backdoor (
  id    INT AUTO_INCREMENT NOT NULL,
  name  CHAR(20),
  power INT,
  CONSTRAINT bd_id_pk
  PRIMARY KEY (id)
);

CREATE TABLE player (
  username  VARCHAR(20),
  playerip  CHAR(19),
  uptime	DATETIME,
  pc_cpu_id INT,
  pc_gpu_id INT,
  pc_hdd_id INT,
  firewall  INT,
  backdoor  INT,
  CONSTRAINT username_pk
  PRIMARY KEY (username),
  CONSTRAINT username_fk
  FOREIGN KEY (username) REFERENCES user (username),
  constraint pc_cpu_id_fk
  foreign key (pc_cpu_id) references pc_cpu(id),
  constraint pc_gpu_id_fk
  foreign key (pc_gpu_id) references pc_gpu(id),
  constraint pc_hdd_id_fk
  foreign key (pc_hdd_id) references pc_hdd(id),
  constraint fw_id_fk
  foreign key (firewall) references firewall(id),
  constraint bd_id_fk
  foreign key (backdoor) references backdoor(id)
);

CREATE TABLE npc (
  id   INT AUTO_INCREMENT NOT NULL,
  name CHAR(20),
  ip   CHAR(19),
  -- type
  CONSTRAINT name_pk
  PRIMARY KEY (id)
);

CREATE TABLE ip_list (
  ip       CHAR(19),
  username VARCHAR(20),
  npcid    INT,
  CONSTRAINT ip_pk PRIMARY KEY (ip),
  CONSTRAINT iplist_username_fk FOREIGN KEY (username)
  REFERENCES player (username),
  CONSTRAINT npcid_fk FOREIGN KEY (npcid)
  REFERENCES npc (id)
);

CREATE TRIGGER checkip
  AFTER INSERT
  ON player
  FOR EACH ROW INSERT INTO ip_list
VALUES (new.playerip, new.username, NULL);

INSERT INTO pc_cpu (name, ghz)
VALUES  ('Pentium Single-Core I', 1.1),
  ('Pentium Single-Core II', 1.2),
  ('Pentium Single-Core III', 1.3),
  ('Pentium Single-Core IV', 1.4),
  ('Pentium Single-Core V', 1.5),
  ('Pentium Single-Core VI', 1.6),
  ('Pentium Single-Core VII', 1.7),
  ('Pentium Single-Core VIII', 1.8),
  ('Pentium Single-Core IX', 1.9),
  ('Pentium Single-Core X', 2.0),
  ('Pentium Dual-Core I', 2.1),
  ('Pentium Dual-Core II', 2.2),
  ('Pentium Dual-Core III', 2.3),
  ('Pentium Dual-Core IV', 2.4),
  ('Pentium Dual-Core V', 2.5),
  ('Pentium Dual-Core VI', 2.6),
  ('Pentium Dual-Core VII', 2.7),
  ('Pentium Dual-Core VIII', 2.8),
  ('Pentium Dual-Core IX', 2.9),
  ('Pentium Dual-Core X', 3.0),
  ('Pentium Quad-Core I', 3.1),
  ('Pentium Quad-Core II', 3.2),
  ('Pentium Quad-Core III', 3.3),
  ('Pentium Quad-Core IV', 3.4),
  ('Pentium Quad-Core V', 3.5),
  ('Pentium Quad-Core VI', 3.6),
  ('Pentium Quad-Core VII', 3.7),
  ('Pentium Quad-Core VIII', 3.8),
  ('Pentium Quad-Core IX', 3.9),
  ('Pentium Quad-Core X', 4.0),
  ('Pentium Octa-Core I', 4.1),
  ('Pentium Octa-Core II', 4.2),
  ('Pentium Octa-Core III', 4.3),
  ('Pentium Octa-Core IV', 4.4),
  ('Pentium Octa-Core V', 4.5),
  ('Pentium Octa-Core VI', 4.6),
  ('Pentium Octa-Core VII', 4.7),
  ('Pentium Octa-Core VIII', 4.8),
  ('Pentium Octa-Core IX', 4.9),
  ('Pentium Octa-Core X', 5.0);


INSERT INTO pc_gpu (name, ghz)
VALUES  ('Bit-Sized Graphics I', 1.0),
  ('Bit-Sized Graphics II', 1.2),
  ('Bit-Sized Graphics III', 1.3),
  ('Bit-Sized Graphics IV', 1.4),
  ('Bit-Sized Graphics V', 1.5),
  ('Bit-Sized Graphics VI', 1.6),
  ('Bit-Sized Graphics VII', 1.7),
  ('Bit-Sized Graphics VIII', 1.8),
  ('Bit-Sized Graphics IX', 1.9),
  ('Bit-Sized Graphics X', 2.0),
  ('Byte-Sized Graphics I', 2.1),
  ('Byte-Sized Graphics II', 2.2),
  ('Byte-Sized Graphics III', 2.3),
  ('Byte-Sized Graphics IV', 2.4),
  ('Byte-Sized Graphics V', 2.5),
  ('Byte-Sized Graphics VI', 2.6),
  ('Byte-Sized Graphics VII', 2.7),
  ('Byte-Sized Graphics VIII', 2.8),
  ('Byte-Sized Graphics IX', 2.9),
  ('Byte-Sized Graphics X', 3.0),
  ('Mega-Sized Graphics I', 3.1),
  ('Mega-Sized Graphics II', 3.2),
  ('Mega-Sized Graphics III', 3.3),
  ('Mega-Sized Graphics IV', 3.4),
  ('Mega-Sized Graphics V', 3.5),
  ('Mega-Sized Graphics VI', 3.6),
  ('Mega-Sized Graphics VII', 3.7),
  ('Mega-Sized Graphics VIII', 3.8),
  ('Mega-Sized Graphics IX', 3.9),
  ('Mega-Sized Graphics X', 4.0),
  ('Giga-Sized Graphics I', 4.1),
  ('Giga-Sized Graphics II', 4.2),
  ('Giga-Sized Graphics III', 4.3),
  ('Giga-Sized Graphics IV', 4.4),
  ('Giga-Sized Graphics V', 4.5),
  ('Giga-Sized Graphics VI', 4.6),
  ('Giga-Sized Graphics VII', 4.7),
  ('Giga-Sized Graphics VIII', 4.8),
  ('Giga-Sized Graphics IX', 4.9),
  ('Giga-Sized Graphics X', 5.0);

INSERT INTO pc_hdd (name, size)
VALUES  ('BitStorinator I', 10),
  ('BitStorinator II', 20),
  ('BitStorinator III', 30),
  ('BitStorinator IV', 40),
  ('BitStorinator V', 50),
  ('BitStorinator VI', 60),
  ('BitStorinator VII', 70),
  ('BitStorinator VIII', 80),
  ('BitStorinator IX', 90),
  ('BitStorinator X', 100),
  ('ByteStorinator I', 10),
  ('ByteStorinator II', 20),
  ('ByteStorinator III', 30),
  ('ByteStorinator IV', 40),
  ('ByteStorinator V', 50),
  ('ByteStorinator VI', 60),
  ('ByteStorinator VII', 70),
  ('ByteStorinator VIII', 80),
  ('ByteStorinator IX', 90),
  ('ByteStorinator X', 100),
  ('MegaStorinator I', 10),
  ('MegaStorinator II', 20),
  ('MegaStorinator III', 30),
  ('MegaStorinator IV', 40),
  ('MegaStorinator V', 50),
  ('MegaStorinator VI', 60),
  ('MegaStorinator VII', 70),
  ('MegaStorinator VIII', 80),
  ('MegaStorinator IX', 90),
  ('MegaStorinator X', 100),
  ('GigaStorinator I', 10),
  ('GigaStorinator II', 20),
  ('GigaStorinator III', 30),
  ('GigaStorinator IV', 40),
  ('GigaStorinator V', 50),
  ('GigaStorinator VI', 60),
  ('GigaStorinator VII', 70),
  ('GigaStorinator VIII', 80),
  ('GigaStorinator IX', 90),
  ('GigaStorinator X', 100);

INSERT INTO firewall (name, security)
VALUES	('BitDefender', 100),
  ('ByteDefender', 200),
  ('MegaDefender', 300),
  ('GigaDefender', 400),
  ('TerraDefender', 500),
  ('PetaDefender', 600),
  ('ExaDefender', 700),
  ('ZettaDefender', 800),
  ('ZottaDefender', 900),
  ('BrontoDefender', 1000);

INSERT INTO backdoor (name, power)
VALUES	('BitAttacker', 100),
  ('ByteAttacker', 200),
  ('MegaAttacker', 300),
  ('GigaAttacker', 400),
  ('TerraAttacker', 500),
  ('PetaAttacker', 600),
  ('ExaAttacker', 700),
  ('ZettaAttacker', 800),
  ('ZottaAttacker', 900),
  ('BrontoAttacker', 1000);