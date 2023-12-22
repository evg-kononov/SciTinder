-- -------------------------------------------------------------
-- TablePlus 5.2.2(478)
--
-- https://tableplus.com/
--
-- Database: scopus
-- Generation Time: 2023-01-26 16:49:52.7680
-- -------------------------------------------------------------


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

DROP TABLE IF EXISTS "public"."asjc" CASCADE;
DROP TABLE IF EXISTS "public"."author" CASCADE;
DROP TABLE IF EXISTS "public"."category" CASCADE;
DROP TABLE IF EXISTS "public"."country" CASCADE;
DROP TABLE IF EXISTS "public"."date" CASCADE;
DROP TABLE IF EXISTS "public"."keyword" CASCADE;
DROP TABLE IF EXISTS "public"."organization" CASCADE;
DROP TABLE IF EXISTS "public"."publication" CASCADE;
DROP TABLE IF EXISTS "public"."publication_asjc" CASCADE;
DROP TABLE IF EXISTS "public"."publication_author" CASCADE;
DROP TABLE IF EXISTS "public"."publication_keyword" CASCADE;
DROP TABLE IF EXISTS "public"."publication_source" CASCADE;
DROP TABLE IF EXISTS "public"."publisher" CASCADE;
DROP TABLE IF EXISTS "public"."sector" CASCADE;
DROP TABLE IF EXISTS "public"."source_category" CASCADE;
DROP TABLE IF EXISTS "public"."source_metrics" CASCADE;
DROP TABLE IF EXISTS "public"."topic" CASCADE;
DROP TABLE IF EXISTS "public"."topic_cluster" CASCADE;


DROP SEQUENCE IF EXISTS author_id_seq CASCADE;
DROP SEQUENCE IF EXISTS category_id_seq CASCADE;
DROP SEQUENCE IF EXISTS country_id_seq CASCADE;
DROP SEQUENCE IF EXISTS keyword_id_seq CASCADE;
DROP SEQUENCE IF EXISTS organization_id_seq CASCADE;
DROP SEQUENCE IF EXISTS publication_id_seq CASCADE;
DROP SEQUENCE IF EXISTS publication_source_id_seq CASCADE;
DROP SEQUENCE IF EXISTS publisher_id_seq CASCADE;
DROP SEQUENCE IF EXISTS sector_id_seq CASCADE;