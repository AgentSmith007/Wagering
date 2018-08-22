--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

-- Started on 2018-07-05 11:49:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 2149
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 189 (class 1259 OID 16525)
-- Name: type_of_wagering; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_of_wagering (
    name character varying(40),
    id integer NOT NULL,
    rules character varying(300)
);


ALTER TABLE public.type_of_wagering OWNER TO postgres;

--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 189
-- Name: TABLE type_of_wagering; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.type_of_wagering IS 'Type of wagering. Conclude rules, prizes, time constraints and offer.';


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN type_of_wagering.name; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.type_of_wagering.name IS 'The name of this type of wagering.';


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN type_of_wagering.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.type_of_wagering.id IS 'Unique number and primary key.';


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN type_of_wagering.rules; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.type_of_wagering.rules IS 'Set of rules for this type of wagering. For example, " half of users must win", "user win if answer  have been within certain limits".';


--
-- TOC entry 186 (class 1259 OID 16512)
-- Name: typical_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.typical_user (
    id integer NOT NULL,
    login character varying(40),
    email character varying(40),
    user_name character varying(100),
    password character varying(40)
);


ALTER TABLE public.typical_user OWNER TO postgres;

--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 186
-- Name: TABLE typical_user; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.typical_user IS 'Users can make the wagerings. It means user choose the type of wagering, open and close bidding and says true variant or answer. Also user can take part in the bidding and try its luck.';


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN typical_user.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.typical_user.id IS 'The unical number of user in the system.';


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN typical_user.login; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.typical_user.login IS 'The login of user.';


--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN typical_user.email; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.typical_user.email IS 'Adress of electronic mail of the user.';


--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN typical_user.user_name; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.typical_user.user_name IS 'ФИО пользователя из AD, пока не используем';


--
-- TOC entry 188 (class 1259 OID 16522)
-- Name: users_bet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_bet (
    id integer NOT NULL,
    user_id integer,
    wagering_id integer,
    users_answer character varying(100)
);


ALTER TABLE public.users_bet OWNER TO postgres;

--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE users_bet; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.users_bet IS 'The table of user''s bets. The table consist of unique number as a primary key, ID of user who take a bet, ID of wagering where bet was placed and answer in analog or discrete types.';


--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN users_bet.user_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.users_bet.user_id IS 'The ID of the user who have made this bet.';


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN users_bet.wagering_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.users_bet.wagering_id IS 'The ID of Wagering on which the bet was placed.';


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN users_bet.users_answer; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.users_bet.users_answer IS 'It is a bet from user. At this moment it is just a string.';


--
-- TOC entry 187 (class 1259 OID 16516)
-- Name: wagering; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wagering (
    id integer NOT NULL,
    name character varying(100),
    description character varying(500),
    type_id integer,
    status character varying(25),
    author_id integer,
    prize_description character varying(200),
    wagering_data_type character varying(25),
    wagering_result character varying(100)
);


ALTER TABLE public.wagering OWNER TO postgres;

--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 187
-- Name: TABLE wagering; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.wagering IS 'Users take a bet in real number or choose one of the proposed options  and wait the result. In future the author says the answer (true variant).';


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.name; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.name IS 'The name of Wagering. (for example "Russia in FIFA").';


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.description; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.description IS 'Special information about this wagering. Rules of this wagering keeps in current type, not here.';


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.type_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.type_id IS 'Type of wagering. Each type describe one type of wagering and consist of rules, prizes, time constraints and other information.';


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.status IS 'The status of wagering at the present. A wagering can be  in the following states: OPEN - новое пари, CLOSED - закрытое, FINISHED - спор сыгран, CANCELLED - отменена или что-то пошло не так, DELETED - удален.';


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.author_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.author_id IS 'The author if this wagering.';


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.prize_description; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.prize_description IS ' Comprehensive information about prize in this wagering.';


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.wagering_data_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.wagering_data_type IS 'Type of answers and bets of this wagering. For example, we can use following types: Date, Real, Integer, Enum (Discrete).';


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wagering.wagering_result; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.wagering.wagering_result IS 'Answer (Result) of this wagering. Answer will be given by author of this wagering.';


--
-- TOC entry 2022 (class 2606 OID 16531)
-- Name: type_of_wagering TypeOfWageringPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_wagering
    ADD CONSTRAINT "TypeOfWageringPK" PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16533)
-- Name: typical_user TypicalUserPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.typical_user
    ADD CONSTRAINT "TypicalUserPK" PRIMARY KEY (id);


--
-- TOC entry 2020 (class 2606 OID 16529)
-- Name: users_bet UsersBetPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_bet
    ADD CONSTRAINT "UsersBetPK" PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 16535)
-- Name: wagering WageringPK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagering
    ADD CONSTRAINT "WageringPK" PRIMARY KEY (id);


--
-- TOC entry 2017 (class 1259 OID 16551)
-- Name: fki_AuthorOfWagering; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_AuthorOfWagering" ON public.wagering USING btree (author_id);


--
-- TOC entry 2018 (class 1259 OID 16557)
-- Name: fki_TypeOfWageringFK; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_TypeOfWageringFK" ON public.wagering USING btree (type_id);


--
-- TOC entry 2024 (class 2606 OID 16546)
-- Name: wagering AuthorOfWageringFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagering
    ADD CONSTRAINT "AuthorOfWageringFK" FOREIGN KEY (author_id) REFERENCES public.typical_user(id);


--
-- TOC entry 2023 (class 2606 OID 16552)
-- Name: wagering TypeOfWageringFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagering
    ADD CONSTRAINT "TypeOfWageringFK" FOREIGN KEY (type_id) REFERENCES public.type_of_wagering(id);


--
-- TOC entry 2026 (class 2606 OID 16541)
-- Name: users_bet WageringOfThisBet; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_bet
    ADD CONSTRAINT "WageringOfThisBet" FOREIGN KEY (wagering_id) REFERENCES public.wagering(id);


--
-- TOC entry 2025 (class 2606 OID 16536)
-- Name: users_bet WhoMadeThisBet; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_bet
    ADD CONSTRAINT "WhoMadeThisBet" FOREIGN KEY (user_id) REFERENCES public.typical_user(id);


-- Completed on 2018-07-05 11:49:12

--
-- PostgreSQL database dump complete
--


