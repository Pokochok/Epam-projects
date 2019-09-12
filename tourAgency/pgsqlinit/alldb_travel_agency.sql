--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.3
-- Dumped by pg_dump version 9.3.3
-- Started on 2019-09-10 10:44:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 188 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 188
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 41186)
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE admins (
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
-- TOC entry 181 (class 1259 OID 41184)
-- Name: admins_id_admin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE admins_id_admin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admins_id_admin_seq OWNER TO postgres;

--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 181
-- Name: admins_id_admin_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE admins_id_admin_seq OWNED BY admins.id;


--
-- TOC entry 174 (class 1259 OID 24706)
-- Name: agents; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE agents (
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
-- TOC entry 173 (class 1259 OID 24704)
-- Name: agents_id_agent_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE agents_id_agent_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.agents_id_agent_seq OWNER TO postgres;

--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 173
-- Name: agents_id_agent_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE agents_id_agent_seq OWNED BY agents.id;


--
-- TOC entry 170 (class 1259 OID 24688)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE clients (
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
-- TOC entry 180 (class 1259 OID 24780)
-- Name: clients_id_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clients_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_client_seq OWNER TO postgres;

--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 180
-- Name: clients_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clients_id_client_seq OWNED BY clients.id;


--
-- TOC entry 172 (class 1259 OID 24696)
-- Name: credit_cards; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE credit_cards (
    id_card integer NOT NULL,
    number character varying(16) NOT NULL,
    owner character varying(60) NOT NULL,
    validity bigint NOT NULL
);


ALTER TABLE public.credit_cards OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 24694)
-- Name: credit_cards_id_card_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE credit_cards_id_card_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.credit_cards_id_card_seq OWNER TO postgres;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 171
-- Name: credit_cards_id_card_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE credit_cards_id_card_seq OWNED BY credit_cards.id_card;


--
-- TOC entry 177 (class 1259 OID 24725)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE orders (
    id integer NOT NULL,
    payment_state character varying(40) NOT NULL,
    id_tour integer NOT NULL,
    id_ticket integer NOT NULL,
    id_client integer NOT NULL,
    id_agent integer DEFAULT 0
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 41259)
-- Name: orders_id_client_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orders_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_client_seq OWNER TO postgres;

--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 186
-- Name: orders_id_client_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orders_id_client_seq OWNED BY orders.id_client;


--
-- TOC entry 176 (class 1259 OID 24723)
-- Name: orders_id_order_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orders_id_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_order_seq OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 176
-- Name: orders_id_order_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orders_id_order_seq OWNED BY orders.id;


--
-- TOC entry 185 (class 1259 OID 41241)
-- Name: orders_id_ticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orders_id_ticket_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_ticket_seq OWNER TO postgres;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 185
-- Name: orders_id_ticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orders_id_ticket_seq OWNED BY orders.id_ticket;


--
-- TOC entry 184 (class 1259 OID 41234)
-- Name: orders_id_tour_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orders_id_tour_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_tour_seq OWNER TO postgres;

--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 184
-- Name: orders_id_tour_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orders_id_tour_seq OWNED BY orders.id_tour;


--
-- TOC entry 175 (class 1259 OID 24716)
-- Name: tours; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE tours (
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
-- TOC entry 187 (class 1259 OID 41285)
-- Name: su; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW su AS
 SELECT sum(tours.price) AS pric
   FROM (orders
   JOIN tours ON ((orders.id_tour = tours.id)))
  GROUP BY orders.id_client;


ALTER TABLE public.su OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 24733)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE tickets (
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
-- TOC entry 178 (class 1259 OID 24731)
-- Name: tickets_id_ticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tickets_id_ticket_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_ticket_seq OWNER TO postgres;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 178
-- Name: tickets_id_ticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tickets_id_ticket_seq OWNED BY tickets.id;


--
-- TOC entry 183 (class 1259 OID 41193)
-- Name: tours_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tours_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tours_id_seq OWNER TO postgres;

--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 183
-- Name: tours_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tours_id_seq OWNED BY tours.id;


--
-- TOC entry 1884 (class 2604 OID 41189)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admins ALTER COLUMN id SET DEFAULT nextval('admins_id_admin_seq'::regclass);


--
-- TOC entry 1873 (class 2604 OID 24709)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agents ALTER COLUMN id SET DEFAULT nextval('agents_id_agent_seq'::regclass);


--
-- TOC entry 1869 (class 2604 OID 24782)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_client_seq'::regclass);


--
-- TOC entry 1872 (class 2604 OID 24699)
-- Name: id_card; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY credit_cards ALTER COLUMN id_card SET DEFAULT nextval('credit_cards_id_card_seq'::regclass);


--
-- TOC entry 1878 (class 2604 OID 24728)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN id SET DEFAULT nextval('orders_id_order_seq'::regclass);


--
-- TOC entry 1879 (class 2604 OID 41236)
-- Name: id_tour; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN id_tour SET DEFAULT nextval('orders_id_tour_seq'::regclass);


--
-- TOC entry 1880 (class 2604 OID 41243)
-- Name: id_ticket; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN id_ticket SET DEFAULT nextval('orders_id_ticket_seq'::regclass);


--
-- TOC entry 1881 (class 2604 OID 41261)
-- Name: id_client; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders ALTER COLUMN id_client SET DEFAULT nextval('orders_id_client_seq'::regclass);


--
-- TOC entry 1883 (class 2604 OID 24736)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tickets ALTER COLUMN id SET DEFAULT nextval('tickets_id_ticket_seq'::regclass);


--
-- TOC entry 1876 (class 2604 OID 41195)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tours ALTER COLUMN id SET DEFAULT nextval('tours_id_seq'::regclass);


--
-- TOC entry 2026 (class 0 OID 41186)
-- Dependencies: 182
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY admins (surname, email, phone_number, login, password, id, name, role, status) FROM stdin;
Грекова	grekovaAnn@mail.com	+30011115777	grekovaAnn	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	2	Анна	ADMIN	ACTIVE
\.


--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 181
-- Name: admins_id_admin_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('admins_id_admin_seq', 2, true);


--
-- TOC entry 2018 (class 0 OID 24706)
-- Dependencies: 174
-- Data for Name: agents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY agents (id, name, surname, email, phone_number, login, password, role, status) FROM stdin;
1	pavel	pokonechny	pokonechny@mail.ru	+37545678901	pavlik995	ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=	AGENT	ACTIVE
0	not defined	not defined	not defined	not defined	not defined	i/rpWfCJgnphEQLNyanpSDsvOLqL3NVXkEaAgd98qrU=	AGENT	ACTIVE
4	Maria	Konovalova	konovalova@grem.com	+375889135568	konovalovaM	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	AGENT	ACTIVE
3	Игорь	Крутой	igorKrutoy@mylo.com	+3003456788	igorKrutoy	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	AGENT	ACTIVE
\.


--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 173
-- Name: agents_id_agent_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('agents_id_agent_seq', 4, true);


--
-- TOC entry 2014 (class 0 OID 24688)
-- Dependencies: 170
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY clients (name, surname, email, phone_number, login, password, id, role, status) FROM stdin;
Толик	Фамич	fomich@mail.ru	+375456789010	fomich	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	15	CLIENT	ACTIVE
Andrey	Korneshonok	andreyKo@gmail.com	+30054815777	andreyKo	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	16	CLIENT	ACTIVE
Pavel	pokonechny	pokonechny@mail.com	+375336586922	ppavlushka	dpI68jpx4zojCdJvTlSUueq0wPDEEy0Ax22KvHGwzy0=	1	CLIENT	ACTIVE
name	myname	pokon12@gmail.com	+30054235777	test	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	17	CLIENT	ACTIVE
\.


--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 180
-- Name: clients_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('clients_id_client_seq', 17, true);


--
-- TOC entry 2016 (class 0 OID 24696)
-- Dependencies: 172
-- Data for Name: credit_cards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY credit_cards (id_card, number, owner, validity) FROM stdin;
\.


--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 171
-- Name: credit_cards_id_card_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('credit_cards_id_card_seq', 1, false);


--
-- TOC entry 2021 (class 0 OID 24725)
-- Dependencies: 177
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY orders (id, payment_state, id_tour, id_ticket, id_client, id_agent) FROM stdin;
5	false	10	0	15	3
13	false	13	57	16	0
14	false	20	61	16	0
16	false	11	0	1	3
\.


--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 186
-- Name: orders_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orders_id_client_seq', 1, false);


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 176
-- Name: orders_id_order_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orders_id_order_seq', 16, true);


--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 185
-- Name: orders_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orders_id_ticket_seq', 1, false);


--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 184
-- Name: orders_id_tour_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orders_id_tour_seq', 1, false);


--
-- TOC entry 2023 (class 0 OID 24733)
-- Dependencies: 179
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tickets (id, flight_number, ticket_number, departure_city, arrival_city, departure_datetime, arrival_datetime) FROM stdin;
2	1	1	Moscow	Minsk	61527513600000	61527945600000
17	2	2	Minsk	Kiev	61527513600000	61527945600000
0	0	0	not defined	not defined	0	0
18	627	5543	Minsk	Moscow	1564956000000	1565128800000
19	633	1112	Ternopol	Warsaw	1566252000000	1566856800000
20	633	1112	Ternopol	Warsaw	1566252000000	1566856800000
21	633	1112	Ternopol	Warsaw	1566252000000	1566856800000
22	632	1112	Warsaw	Berlin	1564956000000	1565042400000
23	1402	12223	Amsterdam	Paris	1566338400000	1566943200000
25	1568	11235	Warsaw	Moscow	1566165600000	1566770400000
47	1402	5543	Moscow	Warsaw	1566511200000	1567116000000
48	1402	5543	Moscow	Warsaw	1566424800000	1567029600000
49	8879	11897	Kiev	Riga	1566684000000	1567288800000
50	3254	1234500	Minsk	Singapore	1566684000000	1567288800000
51	34562	233441	Minsk	Chernovtsy	1566424800000	1567029600000
52	34562	1234500	Minsk	Chernovtsy	1566511200000	1567116000000
53	34562	23344123	Minsk	Chernovtsy	1566338400000	1566943200000
54	56236	546568	Minsk	Singapore	1566338400000	1566943200000
55	135121	9875184	Minsk	Singapore	1566597600000	1567202400000
56	111	111	Minsk	Sochi	1566424800000	1567029600000
57	111	112	Minsk	Sochi	1566252000000	1566856800000
58	111	113	Minsk	Sochi	1566597600000	1567202400000
59	111	114	Minsk	Sochi	1566684000000	1567288800000
60	112	111	Minsk	Tokio	1566165600000	1566770400000
61	112	112	Minsk	Tokio	1566252000000	1566856800000
62	112	114	Minsk	Tokio	1566424800000	1567029600000
63	113	111	Minsk	Saint Petersburg	1566165600000	1566770400000
64	113	113	Minsk	Tokio	1566511200000	1567116000000
65	777	777	Minsk	Test	1565992800000	1567202400000
\.


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 178
-- Name: tickets_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tickets_id_ticket_seq', 65, true);


--
-- TOC entry 2019 (class 0 OID 24716)
-- Dependencies: 175
-- Data for Name: tours; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tours (departure_date, arrival_date, departure_city, arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, id, status, tour_name) FROM stdin;
1566511200000	1567116000000	Moscow	Sochi	Russia	Moscow city	FB	2	2	$650.00	13	AVAILABLE	Walk in Sochi
0	0	not defined	not defined	not defined	not defined	not defined	0	0	$0.00	0	not defined	not defined
1566511200000	1567116000000	Minsk	Moscow	Russia	Moscow city	RO	2	1	$586.00	11	AVAILABLE	Tour in Moscow
1566424800000	1567029600000	Minsk	Tokio	Japan	Tokio	AI	2	0	$840.00	20	AVAILABLE	Beautiful Japan
1565128800000	1566597600000	Minsk	Moscow	Russia	Европа	HB+	4	2	$700.00	14	AVAILABLE	Beautiful MSK
1566684000000	1567288800000	Minsk	Singapore	Singapore	AndazSingapore	AI	3	0	$840.00	17	NOT_AVAILABLE	PicturesqueSingapore
1565820000000	1565215200000	Minsk	Test	Test	Test	AI	2	0	$300.00	21	NOT_AVAILABLE	Test tour
100000	100100	Minsk	Moscow	Russia	Москва	RO	2	1	$500.00	15	AVAILABLE	beautiful MSK
1566597600000	1567202400000	Minsk	Chernovtsy	Ukrainee	Москва	RO	2	1	$300.00	10	AVAILABLE	tour in Ukraine
100000	100100	Minsk	Moscow	Russia	Москва	RO	2	1	$500.00	12	AVAILABLE	Walk in MSK
\.


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 183
-- Name: tours_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tours_id_seq', 21, true);


--
-- TOC entry 1902 (class 2606 OID 41191)
-- Name: admins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- TOC entry 1894 (class 2606 OID 24711)
-- Name: agents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY agents
    ADD CONSTRAINT agents_pkey PRIMARY KEY (id);


--
-- TOC entry 1888 (class 2606 OID 24787)
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 1890 (class 2606 OID 24703)
-- Name: credit_cards_id_card_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY credit_cards
    ADD CONSTRAINT credit_cards_id_card_number_key UNIQUE (id_card, number);


--
-- TOC entry 1892 (class 2606 OID 24701)
-- Name: credit_cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY credit_cards
    ADD CONSTRAINT credit_cards_pkey PRIMARY KEY (id_card);


--
-- TOC entry 1898 (class 2606 OID 24730)
-- Name: orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 1900 (class 2606 OID 24738)
-- Name: tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 1896 (class 2606 OID 41204)
-- Name: tours_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);


--
-- TOC entry 1905 (class 2606 OID 41266)
-- Name: orders_id_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_id_client_fkey FOREIGN KEY (id_client) REFERENCES clients(id);


--
-- TOC entry 1904 (class 2606 OID 41253)
-- Name: orders_id_ticket_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_id_ticket_fkey FOREIGN KEY (id_ticket) REFERENCES tickets(id);


--
-- TOC entry 1903 (class 2606 OID 41248)
-- Name: orders_id_tour_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_id_tour_fkey FOREIGN KEY (id_tour) REFERENCES tours(id);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-09-10 10:44:51

--
-- PostgreSQL database dump complete
--