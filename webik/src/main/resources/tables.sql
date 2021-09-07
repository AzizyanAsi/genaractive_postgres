CREATE SEQUENCE item_id_sequence INCREMENT 1 MINVALUE 0;
CREATE SEQUENCE config_id_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 3;

CREATE TABLE public.group
(
    id          varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name        varchar,
    groupParent varchar REFERENCES "group" (id)
);
CREATE TABLE public.configuration
(
    id         varchar PRIMARY KEY DEFAULT nextval('config_id_sequence'),
    resolution varchar
);

CREATE TABLE public.item
(
    id              varchar PRIMARY KEY DEFAULT nextval('item_id_sequence'),
    name            varchar,
    price           double precision,
    configurationId varchar REFERENCES "configuration" (id),
    parentGroup     varchar REFERENCES "group" (id)

);

CREATE TABLE public.stock_item
(
    id       varchar PRIMARY KEY REFERENCES "item" (id),
    imageUrl varchar
);

CREATE TABLE public.generative_item
(
    id         varchar PRIMARY KEY REFERENCES "item" (id),
    complexity double precision
);
