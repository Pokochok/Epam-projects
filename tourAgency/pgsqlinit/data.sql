--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 11.5

-- Started on 2019-09-12 10:24:37

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

--
-- TOC entry 2875 (class 0 OID 16394)
-- Dependencies: 196
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.admins VALUES ('Грекова', 'grekovaAnn@mail.com', '+30011115777', 'grekovaAnn', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 2, 'Анна', 'ADMIN', 'ACTIVE');


--
-- TOC entry 2877 (class 0 OID 16401)
-- Dependencies: 198
-- Data for Name: agents; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.agents VALUES (1, 'pavel', 'pokonechny', 'pokonechny@mail.ru', '+37545678901', 'pavlik995', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'AGENT', 'ACTIVE');
INSERT INTO public.agents VALUES (4, 'Maria', 'Konovalova', 'konovalova@grem.com', '+375889135568', 'konovalovaM', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 'AGENT', 'ACTIVE');
INSERT INTO public.agents VALUES (3, 'Игорь', 'Крутой', 'igorKrutoy@mylo.com', '+3003456788', 'igorKrutoy', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 'AGENT', 'ACTIVE');
INSERT INTO public.agents VALUES (0, 'not defined', 'not defined', 'not defined', 'not defined', 'not defined', 'i/rpWfCJgnphEQLNyanpSDsvOLqL3NVXkEaAgd98qrU=', 'AGENT', 'ACTIVE');


--
-- TOC entry 2879 (class 0 OID 16408)
-- Dependencies: 200
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clients VALUES ('Толик', 'Фамич', 'fomich@mail.ru', '+375456789010', 'fomich', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 15, 'CLIENT', 'ACTIVE');
INSERT INTO public.clients VALUES ('Andrey', 'Korneshonok', 'andreyKo@gmail.com', '+30054815777', 'andreyKo', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 16, 'CLIENT', 'ACTIVE');
INSERT INTO public.clients VALUES ('Pavel', 'pokonechny', 'pokonechny@mail.com', '+375336586922', 'ppavlushka', 'dpI68jpx4zojCdJvTlSUueq0wPDEEy0Ax22KvHGwzy0=', 1, 'CLIENT', 'ACTIVE');
INSERT INTO public.clients VALUES ('name', 'myname', 'pokon12@gmail.com', '+30054235777', 'test', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', 17, 'CLIENT', 'ACTIVE');


--
-- TOC entry 2881 (class 0 OID 16415)
-- Dependencies: 202
-- Data for Name: credit_cards; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2889 (class 0 OID 16440)
-- Dependencies: 211
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tickets VALUES (2, 1, 1, 'Moscow', 'Minsk', 61527513600000, 61527945600000);
INSERT INTO public.tickets VALUES (17, 2, 2, 'Minsk', 'Kiev', 61527513600000, 61527945600000);
INSERT INTO public.tickets VALUES (0, 0, 0, 'not defined', 'not defined', 0, 0);
INSERT INTO public.tickets VALUES (18, 627, 5543, 'Minsk', 'Moscow', 1564956000000, 1565128800000);
INSERT INTO public.tickets VALUES (19, 633, 1112, 'Ternopol', 'Warsaw', 1566252000000, 1566856800000);
INSERT INTO public.tickets VALUES (20, 633, 1112, 'Ternopol', 'Warsaw', 1566252000000, 1566856800000);
INSERT INTO public.tickets VALUES (21, 633, 1112, 'Ternopol', 'Warsaw', 1566252000000, 1566856800000);
INSERT INTO public.tickets VALUES (22, 632, 1112, 'Warsaw', 'Berlin', 1564956000000, 1565042400000);
INSERT INTO public.tickets VALUES (23, 1402, 12223, 'Amsterdam', 'Paris', 1566338400000, 1566943200000);
INSERT INTO public.tickets VALUES (25, 1568, 11235, 'Warsaw', 'Moscow', 1566165600000, 1566770400000);
INSERT INTO public.tickets VALUES (47, 1402, 5543, 'Moscow', 'Warsaw', 1566511200000, 1567116000000);
INSERT INTO public.tickets VALUES (48, 1402, 5543, 'Moscow', 'Warsaw', 1566424800000, 1567029600000);
INSERT INTO public.tickets VALUES (49, 8879, 11897, 'Kiev', 'Riga', 1566684000000, 1567288800000);
INSERT INTO public.tickets VALUES (50, 3254, 1234500, 'Minsk', 'Singapore', 1566684000000, 1567288800000);
INSERT INTO public.tickets VALUES (51, 34562, 233441, 'Minsk', 'Chernovtsy', 1566424800000, 1567029600000);
INSERT INTO public.tickets VALUES (52, 34562, 1234500, 'Minsk', 'Chernovtsy', 1566511200000, 1567116000000);
INSERT INTO public.tickets VALUES (53, 34562, 23344123, 'Minsk', 'Chernovtsy', 1566338400000, 1566943200000);
INSERT INTO public.tickets VALUES (54, 56236, 546568, 'Minsk', 'Singapore', 1566338400000, 1566943200000);
INSERT INTO public.tickets VALUES (55, 135121, 9875184, 'Minsk', 'Singapore', 1566597600000, 1567202400000);
INSERT INTO public.tickets VALUES (56, 111, 111, 'Minsk', 'Sochi', 1566424800000, 1567029600000);
INSERT INTO public.tickets VALUES (57, 111, 112, 'Minsk', 'Sochi', 1566252000000, 1566856800000);
INSERT INTO public.tickets VALUES (58, 111, 113, 'Minsk', 'Sochi', 1566597600000, 1567202400000);
INSERT INTO public.tickets VALUES (59, 111, 114, 'Minsk', 'Sochi', 1566684000000, 1567288800000);
INSERT INTO public.tickets VALUES (60, 112, 111, 'Minsk', 'Tokio', 1566165600000, 1566770400000);
INSERT INTO public.tickets VALUES (61, 112, 112, 'Minsk', 'Tokio', 1566252000000, 1566856800000);
INSERT INTO public.tickets VALUES (62, 112, 114, 'Minsk', 'Tokio', 1566424800000, 1567029600000);
INSERT INTO public.tickets VALUES (63, 113, 111, 'Minsk', 'Saint Petersburg', 1566165600000, 1566770400000);
INSERT INTO public.tickets VALUES (64, 113, 113, 'Minsk', 'Tokio', 1566511200000, 1567116000000);
INSERT INTO public.tickets VALUES (65, 777, 777, 'Minsk', 'Test', 1565992800000, 1567202400000);


--
-- TOC entry 2888 (class 0 OID 16432)
-- Dependencies: 209
-- Data for Name: tours; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tours VALUES (1566511200000, 1567116000000, 'Moscow', 'Sochi', 'Russia', 'Moscow city', 'FB', 2, 2, '$650.00', 13, 'AVAILABLE', 'Walk in Sochi');
INSERT INTO public.tours VALUES (0, 0, 'not defined', 'not defined', 'not defined', 'not defined', 'not defined', 0, 0, '$0.00', 0, 'not defined', 'not defined');
INSERT INTO public.tours VALUES (1566511200000, 1567116000000, 'Minsk', 'Moscow', 'Russia', 'Moscow city', 'RO', 2, 1, '$586.00', 11, 'AVAILABLE', 'Tour in Moscow');
INSERT INTO public.tours VALUES (1566424800000, 1567029600000, 'Minsk', 'Tokio', 'Japan', 'Tokio', 'AI', 2, 0, '$840.00', 20, 'AVAILABLE', 'Beautiful Japan');
INSERT INTO public.tours VALUES (1565128800000, 1566597600000, 'Minsk', 'Moscow', 'Russia', 'Европа', 'HB+', 4, 2, '$700.00', 14, 'AVAILABLE', 'Beautiful MSK');
INSERT INTO public.tours VALUES (1566684000000, 1567288800000, 'Minsk', 'Singapore', 'Singapore', 'AndazSingapore', 'AI', 3, 0, '$840.00', 17, 'NOT_AVAILABLE', 'PicturesqueSingapore');
INSERT INTO public.tours VALUES (1565820000000, 1565215200000, 'Minsk', 'Test', 'Test', 'Test', 'AI', 2, 0, '$300.00', 21, 'NOT_AVAILABLE', 'Test tour');
INSERT INTO public.tours VALUES (100000, 100100, 'Minsk', 'Moscow', 'Russia', 'Москва', 'RO', 2, 1, '$500.00', 15, 'AVAILABLE', 'beautiful MSK');
INSERT INTO public.tours VALUES (1566597600000, 1567202400000, 'Minsk', 'Chernovtsy', 'Ukrainee', 'Москва', 'RO', 2, 1, '$300.00', 10, 'AVAILABLE', 'tour in Ukraine');
INSERT INTO public.tours VALUES (100000, 100100, 'Minsk', 'Moscow', 'Russia', 'Москва', 'RO', 2, 1, '$500.00', 12, 'AVAILABLE', 'Walk in MSK');


--
-- TOC entry 2883 (class 0 OID 16420)
-- Dependencies: 204
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders VALUES (13, 'false', 13, 57, 16, 0);
INSERT INTO public.orders VALUES (14, 'false', 20, 61, 16, 0);
INSERT INTO public.orders VALUES (16, 'false', 11, 0, 1, 3);
INSERT INTO public.orders VALUES (17, 'false', 0, 61, 15, 0);
INSERT INTO public.orders VALUES (5, 'true', 10, 0, 15, 3);


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 197
-- Name: admins_id_admin_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admins_id_admin_seq', 2, true);


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 199
-- Name: agents_id_agent_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.agents_id_agent_seq', 4, true);


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 201
-- Name: clients_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_client_seq', 17, true);


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 203
-- Name: credit_cards_id_card_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.credit_cards_id_card_seq', 1, false);


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 205
-- Name: orders_id_client_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_client_seq', 1, false);


--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 206
-- Name: orders_id_order_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_order_seq', 17, true);


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 207
-- Name: orders_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_ticket_seq', 1, false);


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 208
-- Name: orders_id_tour_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_tour_seq', 1, false);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 212
-- Name: tickets_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_ticket_seq', 65, true);


--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 213
-- Name: tours_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tours_id_seq', 21, true);


-- Completed on 2019-09-12 10:24:37

--
-- PostgreSQL database dump complete
--

