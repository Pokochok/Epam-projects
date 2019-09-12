--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 11.5

-- Started on 2019-09-12 10:24:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admins (
    surname character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    phone_number character varying(15) NOT NULL,
    login character varying(60) NOT NULL,
    password character varying(70) NOT NULL,
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    role character varying(10) DEFAULT 'ADMIN'::character varying NOT NULL,
    status character varying(40) DEFAULT 'ACTIVE'::character varying NOT NULL
);


ALTER TABLE public.admins OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16399)
-- Name: admins_id_admin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admins_id_admin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admins_id_admin_seq OWNER TO postgres;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 197
-- Name: admins_id_admin_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admins_id_admin_seq OWNED BY public.admins.id;


--
-- TOC entry 198 (class 1259 OID 16401)
-- Name: agents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agents (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    phone_number character varying(15) NOT NULL,
    login character varying(60) NOT NULL,
    password character varying(70) NOT NULL,
    role character varying(10) DEFAULT 'AGENT'::character varying NOT NULL,
    status character varying(40) DEFAULT 'ACTIVE'::character varying NOT NULL
);


ALTER TABLE public.agents OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16406)
-- Name: agents_id_agent_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.agents_id_agent_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.agents_id_agent_seq OWNER TO postgres;

--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 199
-- Name: agents_id_agent_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.agents_id_agent_seq OWNED BY public.agents.id;


--
-- TOC entry 200 (class 1259 OID 16408)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    phone_number character varying(15) NOT NULL,
    login character varying(60) NOT NULL,
    password character varying(70) NOT NULL,
    id integer NOT NULL,
    role character varying(10) DEFAULT 'CLIENT'::character varying NOT NULL,
    status character varying(40) DEFAULT 'ACTIVE'::character varying NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16413)
-- Name: clients_id_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_client_seq OWNER TO postgres;

--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 201
-- Name: clients_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_client_seq OWNED BY public.clients.id;


--
-- TOC entry 202 (class 1259 OID 16415)
-- Name: credit_cards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.credit_cards (
    id_card integer NOT NULL,
    number character varying(16) NOT NULL,
    owner character varying(60) NOT NULL,
    validity bigint NOT NULL
);


ALTER TABLE public.credit_cards OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16418)
-- Name: credit_cards_id_card_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.credit_cards_id_card_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_cards_id_card_seq OWNER TO postgres;

--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 203
-- Name: credit_cards_id_card_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.credit_cards_id_card_seq OWNED BY public.credit_cards.id_card;


--
-- TOC entry 204 (class 1259 OID 16420)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    payment_state character varying(40) NOT NULL,
    id_tour integer NOT NULL,
    id_ticket integer NOT NULL,
    id_client integer NOT NULL,
    id_agent integer DEFAULT 0
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16424)
-- Name: orders_id_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_client_seq OWNER TO postgres;

--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 205
-- Name: orders_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_client_seq OWNED BY public.orders.id_client;


--
-- TOC entry 206 (class 1259 OID 16426)
-- Name: orders_id_order_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_order_seq OWNER TO postgres;

--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 206
-- Name: orders_id_order_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_order_seq OWNED BY public.orders.id;


--
-- TOC entry 207 (class 1259 OID 16428)
-- Name: orders_id_ticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_ticket_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_ticket_seq OWNER TO postgres;

--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 207
-- Name: orders_id_ticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_ticket_seq OWNED BY public.orders.id_ticket;


--
-- TOC entry 208 (class 1259 OID 16430)
-- Name: orders_id_tour_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_tour_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_tour_seq OWNER TO postgres;

--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 208
-- Name: orders_id_tour_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_tour_seq OWNED BY public.orders.id_tour;


--
-- TOC entry 209 (class 1259 OID 16432)
-- Name: tours; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tours (
    departure_date bigint NOT NULL,
    arrival_date bigint NOT NULL,
    departure_city character varying(40) NOT NULL,
    arrival_city character varying(40) NOT NULL,
    arrival_country character varying(40) NOT NULL,
    hotel character varying(40) NOT NULL,
    nutrition character varying(40) NOT NULL,
    adults_number integer NOT NULL,
    children_number integer NOT NULL,
    price money NOT NULL,
    id integer NOT NULL,
    status character varying(40) DEFAULT 'AVAILABLE'::character varying NOT NULL,
    tour_name character varying(40) NOT NULL
);


ALTER TABLE public.tours OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16436)
-- Name: su; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.su AS
 SELECT sum(tours.price) AS pric
   FROM (public.orders
     JOIN public.tours ON ((orders.id_tour = tours.id)))
  GROUP BY orders.id_client;


ALTER TABLE public.su OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16440)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    flight_number integer NOT NULL,
    ticket_number integer NOT NULL,
    departure_city character varying(50) NOT NULL,
    arrival_city character varying(50) NOT NULL,
    departure_datetime bigint NOT NULL,
    arrival_datetime bigint NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16443)
-- Name: tickets_id_ticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_ticket_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_ticket_seq OWNER TO postgres;

--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 212
-- Name: tickets_id_ticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_ticket_seq OWNED BY public.tickets.id;


--
-- TOC entry 213 (class 1259 OID 16445)
-- Name: tours_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tours_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tours_id_seq OWNER TO postgres;

--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 213
-- Name: tours_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tours_id_seq OWNED BY public.tours.id;


--
-- TOC entry 2718 (class 2604 OID 16447)
-- Name: admins id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins ALTER COLUMN id SET DEFAULT nextval('public.admins_id_admin_seq'::regclass);


--
-- TOC entry 2721 (class 2604 OID 16448)
-- Name: agents id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agents ALTER COLUMN id SET DEFAULT nextval('public.agents_id_agent_seq'::regclass);


--
-- TOC entry 2724 (class 2604 OID 16449)
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_client_seq'::regclass);


--
-- TOC entry 2725 (class 2604 OID 16450)
-- Name: credit_cards id_card; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_cards ALTER COLUMN id_card SET DEFAULT nextval('public.credit_cards_id_card_seq'::regclass);


--
-- TOC entry 2727 (class 2604 OID 16451)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_order_seq'::regclass);


--
-- TOC entry 2728 (class 2604 OID 16452)
-- Name: orders id_tour; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id_tour SET DEFAULT nextval('public.orders_id_tour_seq'::regclass);


--
-- TOC entry 2729 (class 2604 OID 16453)
-- Name: orders id_ticket; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id_ticket SET DEFAULT nextval('public.orders_id_ticket_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 16454)
-- Name: orders id_client; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id_client SET DEFAULT nextval('public.orders_id_client_seq'::regclass);


--
-- TOC entry 2733 (class 2604 OID 16455)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_ticket_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 16456)
-- Name: tours id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tours ALTER COLUMN id SET DEFAULT nextval('public.tours_id_seq'::regclass);


--
-- TOC entry 2735 (class 2606 OID 16458)
-- Name: admins admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 16460)
-- Name: agents agents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agents
    ADD CONSTRAINT agents_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 16462)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 2741 (class 2606 OID 16464)
-- Name: credit_cards credit_cards_id_card_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_cards
    ADD CONSTRAINT credit_cards_id_card_number_key UNIQUE (id_card, number);


--
-- TOC entry 2743 (class 2606 OID 16466)
-- Name: credit_cards credit_cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.credit_cards
    ADD CONSTRAINT credit_cards_pkey PRIMARY KEY (id_card);


--
-- TOC entry 2745 (class 2606 OID 16468)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 16470)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 16472)
-- Name: tours tours_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);


--
-- TOC entry 2750 (class 2606 OID 16473)
-- Name: orders orders_id_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_id_client_fkey FOREIGN KEY (id_client) REFERENCES public.clients(id);


--
-- TOC entry 2751 (class 2606 OID 16478)
-- Name: orders orders_id_ticket_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_id_ticket_fkey FOREIGN KEY (id_ticket) REFERENCES public.tickets(id);


--
-- TOC entry 2752 (class 2606 OID 16483)
-- Name: orders orders_id_tour_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_id_tour_fkey FOREIGN KEY (id_tour) REFERENCES public.tours(id);


-- Completed on 2019-09-12 10:24:17

--
-- PostgreSQL database dump complete
--

