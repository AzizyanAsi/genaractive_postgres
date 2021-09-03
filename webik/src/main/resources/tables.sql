CREATE
DATABASE generative;
CREATE SEQUENCE item_id_sequence INCREMENT 1 MINVALUE 0;
CREATE TABLE public.group
(
    id          varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name        varchar,
    groupParent varchar REFERENCES "group" (id)
);
--  DROP TABLE public.group;
-- DROP TABLE public.items;
-- DROP TABLE public.generative_items;
-- DROP TABLE public.stock_items;
CREATE TABLE public.items
(
    id            varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name          varchar,
    price         double precision,
    configuration varchar,
    imageUrl      varchar,
    parentGroup   varchar REFERENCES "group" (id)

);
CREATE TABLE public.stock_items
(
    id            varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name          varchar,
    imageUrl      varchar,
    price         double precision,
    configuration varchar

);
CREATE TABLE public.configuration
(
    resolution varchar
);
CREATE TABLE public.generative_items
(
    id            varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name          varchar,
    complexity    double precision,
    price         double precision,
    configuration varchar

);

INSERT INTO public.group
VALUES (1, 'group1', 1),
       (2, 'group2', 2),
       (3, 'group3', 3);
INSERT INTO public.items
VALUES (1, 'row1', 200, 'HD', 'url', 2),
       (2, 'row2', 200, 'FHD', 'url', 3),
       (3, 'row3', 200, '4k', 'url', 1);
INSERT INTO public.stock_items
VALUES (1, 'srow1', 'url', 300, 'HD'),
       (2, 'srow2', 'url', 240, 'FHD'),
       (3, 'srow3', 'url', 340, 'FHD');

INSERT INTO public.generative_items
VALUES (1, 'grow1', 2, 100, 'HD'),
       (2, 'grow2', 3, 400, 'FHD'),
       (3, 'grow3', 4, 330, 'FHD');

SELECT "items".id, "items".name, "group".name
FROM "group"
         LEFT JOIN "items" ON "items".parentGroup = "group".id;
