  CREATE TABLE accomodation
  (
      id integer NOT NULL,
      type character varying(32) NOT NULL,
      bed_type character varying(32) NOT NULL,
      max_guests integer,
      description character varying(512),
      PRIMARY KEY (id)
  );

 ALTER TABLE accommodation ALTER COLUMN id RESTART WITH 1;

 CREATE TABLE Room_fare
 (
     id integer NOT NULL,
     value double precision NOT NULL,
     season character varying(32) NOT NULL,
     PRIMARY KEY (id)
 );

 ALTER TABLE IF EXISTS public."Room_fare"
     OWNER to postgres;




 CREATE TABLE IF NOT EXISTS accommodation_fare_relation
 (
     id integer NOT NULL,
     id_accommodation integer NOT NULL,
     id_room_fare integer NOT NULL,
     CONSTRAINT "accommodation_fare_relation_pkey" PRIMARY KEY (id_accommodation),
     CONSTRAINT "accommodation_fare_relation" FOREIGN KEY (id_accommodation)
         REFERENCES public."accommodation" (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION,
     CONSTRAINT "Room_fare" FOREIGN KEY (id_room_fare)
         REFERENCES public."Room_fare" (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
 )

 TABLESPACE pg_default;