/*
Created: 06.12.2015
Modified: 08.12.2015
Project: VIS projekt
Model: PostgreSQL 9.4 pokus
Database: PostgreSQL 9.4
*/


-- Drop relationships section -------------------------------------------------

ALTER TABLE "objednavka_jidlo" DROP CONSTRAINT "vari"
;
ALTER TABLE "reklamace" DROP CONSTRAINT "zamestnenec_reklamace"
;
ALTER TABLE "zamestnanec_piti" DROP CONSTRAINT "piti_zamestnanec"
;
ALTER TABLE "zamestnanec_piti" DROP CONSTRAINT "zamestnanec_piti"
;
ALTER TABLE "zamestnanec_jidlo" DROP CONSTRAINT "jidlo_zamestnanec"
;
ALTER TABLE "zamestnanec_jidlo" DROP CONSTRAINT "zamestnanec_jidlo"
;
ALTER TABLE "objednavka" DROP CONSTRAINT "zamestnanec_objednavka"
;
ALTER TABLE "ucet" DROP CONSTRAINT "zamestnanec_ucet"
;
ALTER TABLE "objednavka" DROP CONSTRAINT "ucet_objednavka"
;
ALTER TABLE "reklamace" DROP CONSTRAINT "reklamace_objednavka"
;
ALTER TABLE "jidlo_surovina" DROP CONSTRAINT "surovina_jidlo"
;
ALTER TABLE "jidlo_surovina" DROP CONSTRAINT "jidlo_surovina"
;
ALTER TABLE "objednavka_jidlo" DROP CONSTRAINT "jidlo_objednavka"
;
ALTER TABLE "objednavka_jidlo" DROP CONSTRAINT "objednavka_jidlo"
;
ALTER TABLE "objednavka_piti" DROP CONSTRAINT "piti_objednavka"
;
ALTER TABLE "objednavka_piti" DROP CONSTRAINT "objednavka_piti"
;




-- Drop keys for tables section -------------------------------------------------

ALTER TABLE "zamestnanec_piti" DROP CONSTRAINT "Key16"
;
ALTER TABLE "zamestnanec_jidlo" DROP CONSTRAINT "Key15"
;
ALTER TABLE "jidlo_surovina" DROP CONSTRAINT "Key14"
;
ALTER TABLE "objednavka_jidlo" DROP CONSTRAINT "Key13"
;
ALTER TABLE "objednavka_piti" DROP CONSTRAINT "Key12"
;
ALTER TABLE "surovina" DROP CONSTRAINT "Key6"
;
ALTER TABLE "objednavka" DROP CONSTRAINT "Key5"
;
ALTER TABLE "reklamace" DROP CONSTRAINT "Key4"
;
ALTER TABLE "ucet" DROP CONSTRAINT "Key3"
;
ALTER TABLE "jidlo" DROP CONSTRAINT "idj"
;
ALTER TABLE "piti" DROP CONSTRAINT "idp"
;
ALTER TABLE "zamestnanec" DROP CONSTRAINT "Key1"
;
ALTER TABLE "zamestnanec" DROP CONSTRAINT "login"
;


-- Drop indexes section -------------------------------------------------

DROP INDEX "IX_Relationship1"
;
DROP INDEX "IX_Relationship43"
;
DROP INDEX "IX_Relationship45"
;
DROP INDEX "IX_Relationship42"
;
DROP INDEX "IX_Relationship50"
;
DROP INDEX "IX_Relationship44"
;

-- Drop tables section ---------------------------------------------------

DROP TABLE "zamestnanec_piti"
;
DROP TABLE "zamestnanec_jidlo"
;
DROP TABLE "jidlo_surovina"
;
DROP TABLE "objednavka_jidlo"
;
DROP TABLE "objednavka_piti"
;
DROP TABLE "surovina"
;
DROP TABLE "objednavka"
;
DROP TABLE "reklamace"
;
DROP TABLE "ucet"
;
DROP TABLE "jidlo"
;
DROP TABLE "piti"
;
DROP TABLE "zamestnanec"
;


-- Create tables section -------------------------------------------------

-- Table zamestnanec

CREATE TABLE "zamestnanec"(
 "idz" BigSerial NOT NULL,
 "jmeno" Varchar NOT NULL,
 "prijmeni" Varchar NOT NULL,
 "login" Varchar NOT NULL,
 "heslo" Varchar NOT NULL,
 "plat" Bigint NOT NULL,
 "rc" Varchar NOT NULL,
 "email" Varchar NOT NULL,
 "telefon" Varchar NOT NULL,
 "poznamka" Varchar,
 "role" Integer NOT NULL
)
;

-- Add keys for table zamestnanec

ALTER TABLE "zamestnanec" ADD CONSTRAINT "Key1" PRIMARY KEY ("idz")
;

ALTER TABLE "zamestnanec" ADD CONSTRAINT "login" UNIQUE ("login")
;

-- Table piti

CREATE TABLE "piti"(
 "idp" BigSerial NOT NULL,
 "nazev" Varchar NOT NULL,
 "objem" Integer NOT NULL,
 "jednotka" Integer NOT NULL,
 "cena" Bigint NOT NULL,
 "mnozstvi" Integer NOT NULL,
 "poznamka" Varchar
)
;

-- Add keys for table piti

ALTER TABLE "piti" ADD CONSTRAINT "idp" PRIMARY KEY ("idp")
;

-- Table jidlo

CREATE TABLE "jidlo"(
 "idj" BigSerial NOT NULL,
 "nazev" Varchar NOT NULL,
 "hmotnost" Integer NOT NULL,
 "jednotka" Integer NOT NULL,
 "cena" Bigint NOT NULL,
 "poznamka" Varchar
)
;

-- Add keys for table jidlo

ALTER TABLE "jidlo" ADD CONSTRAINT "idj" PRIMARY KEY ("idj")
;

-- Table ucet

CREATE TABLE "ucet"(
 "idu" BigSerial NOT NULL,
 "vytvoren" Timestamp NOT NULL,
 "zpusob_platby" Integer,
 "cena" Bigint,
 "dph" Integer,
 "sleva" Bigint,
 "poznamka" Bigint,
 "idz" Bigint
)
;

-- Create indexes for table ucet

CREATE INDEX "IX_Relationship44" ON "ucet" ("idz")
;

-- Add keys for table ucet

ALTER TABLE "ucet" ADD CONSTRAINT "Key3" PRIMARY KEY ("idu")
;

-- Table reklamace

CREATE TABLE "reklamace"(
 "idr" BigSerial NOT NULL,
 "popis" Varchar NOT NULL,
 "poznamka" Varchar,
 "ido" Bigint,
 "idz" Bigint
)
;

-- Create indexes for table reklamace

CREATE INDEX "IX_Relationship42" ON "reklamace" ("ido")
;

CREATE INDEX "IX_Relationship50" ON "reklamace" ("idz")
;

-- Add keys for table reklamace

ALTER TABLE "reklamace" ADD CONSTRAINT "Key4" PRIMARY KEY ("idr")
;

-- Table objednavka

CREATE TABLE "objednavka"(
 "ido" BigSerial NOT NULL,
 "stul" Integer NOT NULL,
 "objednano" Timestamp NOT NULL,
 "vyhotoveno" Date,
 "poznamka" Varchar,
 "idu" Bigint,
 "idz" Bigint
)
;

-- Create indexes for table objednavka

CREATE INDEX "IX_Relationship43" ON "objednavka" ("idu")
;

CREATE INDEX "IX_Relationship45" ON "objednavka" ("idz")
;

-- Add keys for table objednavka

ALTER TABLE "objednavka" ADD CONSTRAINT "Key5" PRIMARY KEY ("ido")
;

-- Table surovina

CREATE TABLE "surovina"(
 "ids" BigSerial NOT NULL,
 "nazev" Varchar NOT NULL,
 "mnozstvi" Integer NOT NULL,
 "poznamka" Varchar
)
;

-- Add keys for table surovina

ALTER TABLE "surovina" ADD CONSTRAINT "Key6" PRIMARY KEY ("ids")
;

-- Table objednavka_piti

CREATE TABLE "objednavka_piti"(
 "ido" Bigint NOT NULL,
 "idp" Bigint NOT NULL
)
;

-- Add keys for table objednavka_piti

ALTER TABLE "objednavka_piti" ADD CONSTRAINT "Key12" PRIMARY KEY ("ido","idp")
;

-- Table objednavka_jidlo

CREATE TABLE "objednavka_jidlo"(
 "ido" Bigint NOT NULL,
 "idj" Bigint NOT NULL,
 "zacatek" Date,
 "konec" Date,
 "pocet" Integer NOT NULL,
 "idz" Bigint
)
;

-- Create indexes for table objednavka_jidlo

CREATE INDEX "IX_Relationship1" ON "objednavka_jidlo" ("idz")
;

-- Add keys for table objednavka_jidlo

ALTER TABLE "objednavka_jidlo" ADD CONSTRAINT "Key13" PRIMARY KEY ("ido","idj")
;

-- Table jidlo_surovina

CREATE TABLE "jidlo_surovina"(
 "idj" Bigint NOT NULL,
 "ids" Bigint NOT NULL
)
;

-- Add keys for table jidlo_surovina

ALTER TABLE "jidlo_surovina" ADD CONSTRAINT "Key14" PRIMARY KEY ("idj","ids")
;

-- Table zamestnanec_jidlo

CREATE TABLE "zamestnanec_jidlo"(
 "idz" Bigint NOT NULL,
 "idj" Bigint NOT NULL
)
;

-- Add keys for table zamestnanec_jidlo

ALTER TABLE "zamestnanec_jidlo" ADD CONSTRAINT "Key15" PRIMARY KEY ("idz","idj")
;

-- Table zamestnanec_piti

CREATE TABLE "zamestnanec_piti"(
 "idz" Bigint NOT NULL,
 "idp" Bigint NOT NULL
)
;

-- Add keys for table zamestnanec_piti

ALTER TABLE "zamestnanec_piti" ADD CONSTRAINT "Key16" PRIMARY KEY ("idz","idp")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "objednavka_piti" ADD CONSTRAINT "objednavka_piti" FOREIGN KEY ("ido") REFERENCES "objednavka" ("ido") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka_piti" ADD CONSTRAINT "piti_objednavka" FOREIGN KEY ("idp") REFERENCES "piti" ("idp") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka_jidlo" ADD CONSTRAINT "objednavka_jidlo" FOREIGN KEY ("ido") REFERENCES "objednavka" ("ido") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka_jidlo" ADD CONSTRAINT "jidlo_objednavka" FOREIGN KEY ("idj") REFERENCES "jidlo" ("idj") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "jidlo_surovina" ADD CONSTRAINT "jidlo_surovina" FOREIGN KEY ("idj") REFERENCES "jidlo" ("idj") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "jidlo_surovina" ADD CONSTRAINT "surovina_jidlo" FOREIGN KEY ("ids") REFERENCES "surovina" ("ids") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "reklamace" ADD CONSTRAINT "reklamace_objednavka" FOREIGN KEY ("ido") REFERENCES "objednavka" ("ido") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka" ADD CONSTRAINT "ucet_objednavka" FOREIGN KEY ("idu") REFERENCES "ucet" ("idu") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "ucet" ADD CONSTRAINT "zamestnanec_ucet" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka" ADD CONSTRAINT "zamestnanec_objednavka" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "zamestnanec_jidlo" ADD CONSTRAINT "zamestnanec_jidlo" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "zamestnanec_jidlo" ADD CONSTRAINT "jidlo_zamestnanec" FOREIGN KEY ("idj") REFERENCES "jidlo" ("idj") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "zamestnanec_piti" ADD CONSTRAINT "zamestnanec_piti" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "zamestnanec_piti" ADD CONSTRAINT "piti_zamestnanec" FOREIGN KEY ("idp") REFERENCES "piti" ("idp") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "reklamace" ADD CONSTRAINT "zamestnenec_reklamace" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "objednavka_jidlo" ADD CONSTRAINT "vari" FOREIGN KEY ("idz") REFERENCES "zamestnanec" ("idz") ON DELETE NO ACTION ON UPDATE NO ACTION
;





