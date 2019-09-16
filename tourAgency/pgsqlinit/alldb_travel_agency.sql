--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 11.5

-- Started on 2019-09-16 18:09:02

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
-- TOC entry 2897 (class 0 OID 0)
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
-- TOC entry 2898 (class 0 OID 0)
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
-- TOC entry 2899 (class 0 OID 0)
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
-- TOC entry 2900 (class 0 OID 0)
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
-- TOC entry 2901 (class 0 OID 0)
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
-- TOC entry 2902 (class 0 OID 0)
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
-- TOC entry 2903 (class 0 OID 0)
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
-- TOC entry 2904 (class 0 OID 0)
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
-- TOC entry 2905 (class 0 OID 0)
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
-- TOC entry 2906 (class 0 OID 0)
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
-- TOC entry 2875 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admins (surname, email, phone_number, login, password, id, name, role, status) FROM stdin;
Грекова	grekovaAnn@mail.com	+30011115777	grekovaAnn	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	2	Анна	ADMIN	ACTIVE
\.


--
-- TOC entry 2877 (class 0 OID 16401)
-- Dependencies: 198
-- Data for Name: agents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agents (id, name, surname, email, phone_number, login, password, role, status) FROM stdin;
1	pavel	pokonechny	pokonechny@mail.ru	+37545678901	pavlik995	ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=	AGENT	ACTIVE
4	Maria	Konovalova	konovalova@grem.com	+375889135568	konovalovaM	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	AGENT	ACTIVE
3	Игорь	Крутой	igorKrutoy@mylo.com	+3003456788	igorKrutoy	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	AGENT	ACTIVE
0	not defined	not defined	not defined	not defined	not defined	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	AGENT	ACTIVE
\.


--
-- TOC entry 2879 (class 0 OID 16408)
-- Dependencies: 200
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (name, surname, email, phone_number, login, password, id, role, status) FROM stdin;
Толик	Фамич	fomich@mail.ru	+375456789010	fomich	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	15	CLIENT	ACTIVE
Andrey	Korneshonok	andreyKo@gmail.com	+30054815777	andreyKo	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	16	CLIENT	ACTIVE
Pavel	pokonechny	pokonechny@mail.com	+375336586922	ppavlushka	dpI68jpx4zojCdJvTlSUueq0wPDEEy0Ax22KvHGwzy0=	1	CLIENT	ACTIVE
name	myname	pokon12@gmail.com	+30054235777	test	x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=	17	CLIENT	ACTIVE
\.


--
-- TOC entry 2881 (class 0 OID 16415)
-- Dependencies: 202
-- Data for Name: credit_cards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.credit_cards (id_card, number, owner, validity) FROM stdin;
\.


--
-- TOC entry 2883 (class 0 OID 16420)
-- Dependencies: 204
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, payment_state, id_tour, id_ticket, id_client, id_agent) FROM stdin;
13	false	13	57	16	0
14	false	20	61	16	0
16	false	11	0	1	3
5	true	10	0	15	3
17	true	0	61	15	0
\.


--
-- TOC entry 2889 (class 0 OID 16440)
-- Dependencies: 211
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (id, flight_number, ticket_number, departure_city, arrival_city, departure_datetime, arrival_datetime) FROM stdin;
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
-- TOC entry 2888 (class 0 OID 16432)
-- Dependencies: 209
-- Data for Name: tours; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tours (departure_date, arrival_date, departure_city, arrival_city, arrival_country, hotel, nutrition, adults_number, children_number, price, id, status, tour_name) FROM stdin;
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
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 197
-- Name: admins_id_admin_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admins_id_admin_seq', 2, true);


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 199
-- Name: agents_id_agent_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.agents_id_agent_seq', 4, true);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 201
-- Name: clients_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_client_seq', 17, true);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 203
-- Name: credit_cards_id_card_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_cards_id_card_seq', 1, false);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 205
-- Name: orders_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_client_seq', 1, false);


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 206
-- Name: orders_id_order_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_order_seq', 17, true);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 207
-- Name: orders_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_ticket_seq', 1, false);


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 208
-- Name: orders_id_tour_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_tour_seq', 1, false);


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 212
-- Name: tickets_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_ticket_seq', 65, true);


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 213
-- Name: tours_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tours_id_seq', 21, true);


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


-- Completed on 2019-09-16 18:09:02

--
-- PostgreSQL database dump complete
--

