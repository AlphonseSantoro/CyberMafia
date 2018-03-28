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
  name CHAR(20),
  ghz  FLOAT,
  CONSTRAINT id_cpu_pk
  PRIMARY KEY (id)
);

CREATE TABLE pc_gpu (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  name CHAR(20),
  ghz  FLOAT,
  CONSTRAINT id_gpu_pk
  PRIMARY KEY (id)
);

CREATE TABLE pc_hdd (
  id   INT AUTO_INCREMENT UNIQUE NOT NULL,
  name CHAR(20),
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
  pc_cpu_id INT,
  pc_gpu_id INT,
  pc_hdd_id INT,
  firewall  INT,
  backdoor  INT,
  CONSTRAINT username_pk
  PRIMARY KEY (username),
  CONSTRAINT username_fk
  FOREIGN KEY (username) REFERENCES user (username)
  /*
  constraint pc_cpu_id_fk
  foreign key (pc_cpu_id) references pc_cpu(id),
  constraint pc_gpu_id_fk
  foreign key (pc_gpu_id) references pc_gpu(id),
  constraint pc_hdd_id_fk
  foreign key (pc_hdd_id) references pc_hdd(id),
  constraint fw_id_fk
  foreign key (firewall) references firewall(id),
  constraint bd_id_fk
  foreign key (backdoor) references backdoor(id) */
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
