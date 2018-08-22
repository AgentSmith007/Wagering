CREATE FUNCTION public.type_of_wagering_autoincrement_funcion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.id:=nextval('type_of_wagering_id_seq');
   Return NEW;
 END;
 $$;


CREATE FUNCTION public.typical_user_autoincrement_funcion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.id:=nextval('typical_user_id_seq');
   Return NEW;
 END;
 $$;

--

CREATE FUNCTION public.users_bet_autoincrement_funcion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.id:=nextval('users_bet_id_seq');
   Return NEW;
 END;
 $$;
--

CREATE FUNCTION public.wagering_autoincrement_funcion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 BEGIN
   New.id:=nextval('wagering_id_seq');
   Return NEW;
 END;
 $$;


CREATE SEQUENCE public.type_of_wagering_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--

ALTER TABLE typical_user
    ALTER COLUMN login
        SET NOT NULL;



CREATE SEQUENCE public.typical_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.users_bet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.wagering_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER TABLE ONLY public.typical_user
    ADD CONSTRAINT unique_for_login UNIQUE (login);



CREATE TRIGGER type_of_wagering_autoincrement_trigger BEFORE INSERT ON public.type_of_wagering FOR EACH ROW EXECUTE PROCEDURE public.type_of_wagering_autoincrement_funcion();


--
-- TOC entry 2051 (class 2620 OID 25010)
-- Name: typical_user typical_user_autoincrement_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER typical_user_autoincrement_trigger BEFORE INSERT ON public.typical_user FOR EACH ROW EXECUTE PROCEDURE public.typical_user_autoincrement_funcion();


--
-- TOC entry 2052 (class 2620 OID 25014)
-- Name: users_bet users_bet_autoincrement_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER users_bet_autoincrement_trigger BEFORE INSERT ON public.users_bet FOR EACH ROW EXECUTE PROCEDURE public.users_bet_autoincrement_funcion();


--
-- TOC entry 2053 (class 2620 OID 25016)
-- Name: wagering wagering_autoincrement_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER wagering_autoincrement_trigger BEFORE INSERT ON public.wagering FOR EACH ROW EXECUTE PROCEDURE public.wagering_autoincrement_funcion();



--

