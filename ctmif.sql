create table mp_auth.ctmif (
  id serial not null
  , name character varying(50) not null
  , email character varying(50) not null
  , password character varying(50) not null
  , gender integer
  , marriage character varying(50) not null
  , privacy integer
  , magazine integer
  , point integer
  , primary key (id)
);
