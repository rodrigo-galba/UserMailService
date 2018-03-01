-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id bigint NOT NULL,
  admin boolean NOT NULL,
  created_date timestamp without time zone NOT NULL,
  email character varying(255) NOT NULL,
  login character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  updated_date timestamp without time zone NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
  CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO userapp;
