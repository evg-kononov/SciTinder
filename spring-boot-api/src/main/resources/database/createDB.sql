-- -------------------------------------------------------------
-- TablePlus 5.2.2(478)
--
-- https://tableplus.com/
--
-- Database: scopus
-- Generation Time: 2023-01-26 16:49:52.7680
-- -------------------------------------------------------------


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS author_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS category_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS country_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS keyword_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS organization_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS publication_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS publication_source_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS publisher_id_seq;

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS sector_id_seq;


-- Table Definition
CREATE TABLE "public"."asjc" (
    "code" int8 NOT NULL,
    "field" varchar(255) NOT NULL,
    "subject_area" varchar(255) NOT NULL,
    PRIMARY KEY ("code")
);

-- Table Definition
CREATE TABLE "public"."author" (
    "id" int8 NOT NULL DEFAULT nextval('author_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "scopus_id" int8,
    "h_index" int8,
    "organization_id" int8,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."category" (
    "id" int8 NOT NULL DEFAULT nextval('category_id_seq'::regclass),
    "name" varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."country" (
    "id" int8 NOT NULL DEFAULT nextval('country_id_seq'::regclass),
    "name" varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."date" (
    "year" int8 NOT NULL,
    PRIMARY KEY ("year")
);

-- Table Definition
CREATE TABLE "public"."keyword" (
    "id" int8 NOT NULL DEFAULT nextval('keyword_id_seq'::regclass),
    "word" varchar NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."organization" (
    "id" int8 NOT NULL DEFAULT nextval('organization_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "scopus_id" int8 UNIQUE,
    "country_id" int8,
    "sector_id" int8,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."publication" (
    "id" int8 NOT NULL DEFAULT nextval('publication_id_seq'::regclass),
    "name" varchar NOT NULL,
    "type" varchar(255),
    "abstract" varchar,
    "english" bool,
    "pages_num" int4,
    "doi" varchar(255),
    "eid" varchar(255) UNIQUE,
    "pubmed_id" int8,
    "views_num" int8,
    "citations_num" int8,
    "open_access" varchar(255),
    "correspondence_address" varchar,
    "date_year" int8,
    "topic_cluster_num" int8,
    "topic_num" int8,
    "publication_source_id" int8,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."publication_asjc" (
    "asjc_code" int8 NOT NULL,
    "publication_id" int8 NOT NULL,
    PRIMARY KEY ("asjc_code", "publication_id")
);

-- Table Definition
CREATE TABLE "public"."publication_author" (
    "publication_id" int8 NOT NULL,
    "author_id" int8 NOT NULL,
    PRIMARY KEY ("publication_id", "author_id")
);

-- Table Definition
CREATE TABLE "public"."publication_keyword" (
    "publication_id" int8 NOT NULL,
    "keyword_id" int8 NOT NULL,
    PRIMARY KEY ("publication_id", "keyword_id")
);

-- Table Definition
CREATE TABLE "public"."publication_source" (
    "id" int8 NOT NULL DEFAULT nextval('publication_source_id_seq'::regclass),
    "name" varchar NOT NULL,
    "type" varchar(255),
    "issn" varchar(255),
    "scopus_id" int8 UNIQUE,
    "publisher_id" int8,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."publisher" (
    "id" int8 NOT NULL DEFAULT nextval('publisher_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."sector" (
    "id" int8 NOT NULL DEFAULT nextval('sector_id_seq'::regclass),
    "name" varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY ("id")
);

-- Table Definition
CREATE TABLE "public"."source_category" (
    "publication_source_id" int8 NOT NULL,
    "category_id" int8 NOT NULL,
    "date_year" int8 NOT NULL,
    "quartile" int2,
    PRIMARY KEY ("publication_source_id", "category_id", "date_year")
);

-- Table Definition
CREATE TABLE "public"."source_metrics" (
    "publication_source_id" int8 NOT NULL,
    "date_year" int8 NOT NULL,
    "snip" float8,
    "sjr" float8,
    "citescore" float8,
    "sjr_best_quartile" int2,
    "h_index" int8,
    PRIMARY KEY ("publication_source_id", "date_year")
);

-- Table Definition
CREATE TABLE "public"."topic" (
    "num" int8 NOT NULL,
    "name" varchar(255) NOT NULL,
    "prominence_percentile" float8 NOT NULL,
    PRIMARY KEY ("num")
);

-- Table Definition
CREATE TABLE "public"."topic_cluster" (
    "num" int8 NOT NULL,
    "name" varchar(255) NOT NULL,
    "prominence_percentile" float8 NOT NULL,
    PRIMARY KEY ("num")
);


ALTER TABLE "public"."author" ADD FOREIGN KEY ("organization_id") REFERENCES "public"."organization"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."organization" ADD FOREIGN KEY ("country_id") REFERENCES "public"."country"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."organization" ADD FOREIGN KEY ("sector_id") REFERENCES "public"."sector"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."publication" ADD FOREIGN KEY ("topic_num") REFERENCES "public"."topic"("num") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."publication" ADD FOREIGN KEY ("topic_cluster_num") REFERENCES "public"."topic_cluster"("num") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."publication" ADD FOREIGN KEY ("date_year") REFERENCES "public"."date"("year") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."publication" ADD FOREIGN KEY ("publication_source_id") REFERENCES "public"."publication_source"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."publication_asjc" ADD FOREIGN KEY ("asjc_code") REFERENCES "public"."asjc"("code") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_asjc" ADD FOREIGN KEY ("publication_id") REFERENCES "public"."publication"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_author" ADD FOREIGN KEY ("publication_id") REFERENCES "public"."publication"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_author" ADD FOREIGN KEY ("author_id") REFERENCES "public"."author"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_keyword" ADD FOREIGN KEY ("publication_id") REFERENCES "public"."publication"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_keyword" ADD FOREIGN KEY ("keyword_id") REFERENCES "public"."keyword"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."publication_source" ADD FOREIGN KEY ("publisher_id") REFERENCES "public"."publisher"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."source_category" ADD FOREIGN KEY ("date_year") REFERENCES "public"."date"("year") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."source_category" ADD FOREIGN KEY ("category_id") REFERENCES "public"."category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."source_category" ADD FOREIGN KEY ("publication_source_id") REFERENCES "public"."publication_source"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."source_metrics" ADD FOREIGN KEY ("date_year") REFERENCES "public"."date"("year") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."source_metrics" ADD FOREIGN KEY ("publication_source_id") REFERENCES "public"."publication_source"("id") ON DELETE CASCADE ON UPDATE CASCADE;
