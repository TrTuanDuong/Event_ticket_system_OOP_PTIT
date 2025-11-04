--
-- PostgreSQL database dump
--

\restrict ftRBeQXa7uZuwcUFMxbeTsA81nJCEZ7eYK3osZWK4pEVkR8z3Vgm0iF5hicvErI

-- Dumped from database version 14.19 (Homebrew)
-- Dumped by pg_dump version 14.19 (Homebrew)

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
-- Data for Name: api_auditorium; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_auditorium VALUES ('eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'Cinema Hall 1', 5, 3, 2, 12, '2025-10-16 08:57:05.525488+07', '2025-10-16 08:57:05.525497+07');
INSERT INTO public.api_auditorium VALUES ('4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'Cinema Hall 2', 6, 2, 2, 10, '2025-10-16 08:57:05.596185+07', '2025-10-16 08:57:05.596191+07');
INSERT INTO public.api_auditorium VALUES ('90d82435-d468-45d6-9c9c-b3432bd65a1c', 'Cinema Hall 3', 4, 4, 1, 14, '2025-10-16 08:57:05.643148+07', '2025-10-16 08:57:05.643155+07');
INSERT INTO public.api_auditorium VALUES ('8f9ffc0e-8032-4abf-8828-757eb90dd536', 'CInema Hall 4', 4, 5, 1, 10, '2025-11-02 04:19:25.979256+07', '2025-11-04 14:42:53.842954+07');
INSERT INTO public.api_auditorium VALUES ('deb2285b-882f-4586-bb12-0266b9d6c56d', '5', 5, 3, 2, 10, '2025-11-04 14:48:02.386978+07', '2025-11-04 14:48:02.386978+07');
INSERT INTO public.api_auditorium VALUES ('387a5681-88d1-46e6-8e8b-01666d617581', 'test', 5, 3, 2, 10, '2025-11-04 14:55:25.170153+07', '2025-11-04 14:55:25.170153+07');
INSERT INTO public.api_auditorium VALUES ('6df00e45-aaf2-4b94-96a8-1292fb5b22ed', 'test1', 5, 6, 2, 12, '2025-11-04 22:53:12.546219+07', '2025-11-04 22:53:12.546219+07');


--
-- Data for Name: api_movie; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_movie VALUES ('e892e566-b9c2-4a58-885b-607cf67e8326', 'Inception', 148, 'PG-13', '2010-07-16', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.', 'https://www.cgv.vn/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/i/n/inception.jpg', NULL, NULL);
INSERT INTO public.api_movie VALUES ('0fc89c4b-9228-4c10-b92a-80f94da1de9e', 'The Dark Knight', 152, 'PG-13', '2008-07-18', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 'https://play-lh.googleusercontent.com/qhfncXOqccJ5Y_IBPaRy0O79QZQDl7L5FyKQAsLFICt8c9-2Vfmqd2bniAPESto0ZmSLTOzjl-o1F_jgb2Nr', NULL, NULL);
INSERT INTO public.api_movie VALUES ('2dfd85ae-3674-45d6-af81-fce482b6f25e', 'Spider-Man: No Way Home', 148, 'PG-13', '2021-12-17', 'With Spider-Man''s identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear.', 'https://m.media-amazon.com/images/I/81y0foYjoFL._AC_UF1000,1000_QL80_.jpg', NULL, NULL);
INSERT INTO public.api_movie VALUES ('2babc793-bc35-4ca2-b60c-9402a8d639c1', 'The Avengers', 143, 'PG-13', '2012-05-04', 'Earth''s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.', 'https://play-lh.googleusercontent.com/1VFlYzxbSAmVybT5qwdCI2T5azO8sXpQwn_gp-h0YXCEgmkPG3ihj9x-8PP3Uwj7ag', NULL, NULL);
INSERT INTO public.api_movie VALUES ('f19673f2-af2a-4faa-86b7-841b8ea77836', 'Interstellar', 169, 'PG-13', '2014-11-07', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.', 'https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/i/n/interstellar-poster-up.jpg', NULL, NULL);
INSERT INTO public.api_movie VALUES ('55465414-1a44-4fdc-828c-adddc51ae144', 'Parasite', 132, 'R', '2019-05-30', 'A poor family schemes to become employed by a wealthy family by infiltrating their household and posing as unrelated, highly qualified individuals.', 'https://s3.amazonaws.com/nightjarprod/content/uploads/sites/130/2024/03/29150816/7IiTTgloJzvGI1TAYymCfbfl3vT-scaled.jpg', NULL, NULL);


--
-- Data for Name: api_showtime; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_showtime VALUES ('dfcad975-2ce9-4506-a891-3b4c213048f8', '2025-11-04 14:40:00+07', '2025-11-04 17:03:00+07', 500000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('8a0e2129-c79b-4898-9757-c42879f55162', '2025-11-03 23:03:00+07', '2025-11-04 01:31:00+07', 50000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2dfd85ae-3674-45d6-af81-fce482b6f25e');
INSERT INTO public.api_showtime VALUES ('aa6d2ccd-9e3f-454e-a028-de59e866b3a8', '2030-10-23 04:30:00+07', '2030-10-23 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('903bd28d-b426-49e0-9d4a-5687b9e9ba94', '2030-10-22 17:00:00+07', '2030-10-22 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('40e922dc-a39b-480a-9f9b-436dfb5d9cba', '2030-10-22 21:30:00+07', '2030-10-22 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('ac6fb91e-2531-451a-9389-1d174a06bb6a', '2030-10-23 01:00:00+07', '2030-10-23 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('0820ba50-ea71-4599-a019-aa1fa8cb8e47', '2030-10-23 04:30:00+07', '2030-10-23 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('bf126770-4467-4f3b-8c06-4f47708be308', '2030-10-23 17:00:00+07', '2030-10-23 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('b5798aa9-3005-4326-b77a-97b871fa5973', '2030-10-23 21:30:00+07', '2030-10-23 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('6d2bf971-0e30-4122-a7a0-ae4eb93f85fd', '2030-10-24 01:00:00+07', '2030-10-24 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('8390085d-236c-4583-8df8-d848ac2c9d4e', '2030-10-24 04:30:00+07', '2030-10-24 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('ebb86b8c-3565-4e49-87bb-631658b849f3', '2030-10-23 17:00:00+07', '2030-10-23 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('6c4d526e-12c1-42df-af28-cb11809e7aa3', '2030-10-23 21:30:00+07', '2030-10-23 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('4da9150e-f7fb-4fe7-97c3-d167d59f60c8', '2030-10-24 01:00:00+07', '2030-10-24 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('e1010c1c-855d-4bfd-a504-777e09597b0a', '2030-10-24 04:30:00+07', '2030-10-24 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('048aa51a-22ea-4b12-8c74-fb1e0bcae6f9', '2030-10-23 17:00:00+07', '2030-10-23 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('96ac2de9-e696-4c88-a263-b33fd9e9ca3b', '2030-10-23 21:30:00+07', '2030-10-23 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('a4bf66f6-3e33-4b49-be37-b96a9a02ccbe', '2030-10-24 01:00:00+07', '2030-10-24 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('02601b88-d7a2-45da-afbb-5a14dbec184e', '2030-10-24 04:30:00+07', '2030-10-24 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('aa8af005-c09a-4951-8e4c-d1a2e23096d6', '2030-10-24 17:00:00+07', '2030-10-24 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('203c8d66-8f47-4c2b-9904-3dd8b19eb275', '2030-10-24 21:30:00+07', '2030-10-24 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('3986a545-7ea9-4638-ad8e-d235d8e1e621', '2030-10-25 01:00:00+07', '2030-10-25 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('ea0ab30b-6c46-49b0-b82b-a9e99806fe3e', '2030-10-25 04:30:00+07', '2030-10-25 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('bd60afac-e6a6-47cc-95d6-e378d065309b', '2030-10-24 17:00:00+07', '2030-10-24 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('6e5c94af-f067-446f-b3b1-b7a91c54b01f', '2030-10-24 21:30:00+07', '2030-10-24 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('5ee63154-e861-4ef1-9779-aa629ef6d607', '2030-10-25 01:00:00+07', '2030-10-25 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('7e30c169-edc0-4031-99bb-6aa41cb136ae', '2030-10-25 04:30:00+07', '2030-10-25 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('f14598b6-e873-4d16-8d92-8d037dd1ba60', '2030-10-24 17:00:00+07', '2030-10-24 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('41a4f99b-ed4c-4667-9b0b-8932b4c38fdb', '2030-10-24 21:30:00+07', '2030-10-24 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('c2905ba1-4faa-4178-8d34-f1ce56a8498c', '2030-10-25 01:00:00+07', '2030-10-25 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('c0e4165b-3514-421e-bbb4-23c94e2a5ef9', '2030-10-25 04:30:00+07', '2030-10-25 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('4e231743-b7b0-4f04-81a3-66ae315ec98a', '2030-10-25 17:00:00+07', '2030-10-25 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('7a893efc-5070-42b8-ba2a-9007ceeb76bf', '2030-10-25 21:30:00+07', '2030-10-25 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('fc73681d-074b-4924-9a0a-bf19f3e789c4', '2030-10-26 01:00:00+07', '2030-10-26 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('200b9a0f-0c49-42e8-8a84-67e1cd1fa3b9', '2030-10-26 04:30:00+07', '2030-10-26 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('6f160a1a-3f89-424b-9602-1471a9348969', '2025-11-04 14:43:00+07', '2025-11-04 17:32:00+07', 50000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('7e8c8a25-247c-4add-a3c4-897ea5d39d3b', '2025-11-04 14:46:00+07', '2025-11-04 17:35:00+07', 50000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('a02703e8-ef1b-4494-9ea2-9103def827b5', '2025-11-04 14:50:00+07', '2025-11-04 17:18:00+07', 50000.00, 'scheduled', '8f9ffc0e-8032-4abf-8828-757eb90dd536', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('5ad8c51d-988d-461e-a03a-c01177ed3b28', '2025-11-04 23:00:00+07', '2025-11-05 01:28:00+07', 50000.00, 'scheduled', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('48c39ccd-6cfa-45a8-9bdb-1da0aa9f8133', '2025-11-04 01:00:00+07', '2025-11-04 03:23:00+07', 50000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('5d7ce003-9215-4cf0-95a1-9f0119fdeb5a', '2025-11-04 19:00:00+07', '2025-11-04 21:28:00+07', 50000.00, 'scheduled', 'deb2285b-882f-4586-bb12-0266b9d6c56d', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('36226012-22f3-4bd1-89b6-048beaf58809', '2025-11-04 00:00:00+07', '2025-11-04 02:28:00+07', 50000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2dfd85ae-3674-45d6-af81-fce482b6f25e');
INSERT INTO public.api_showtime VALUES ('d0fb9d82-4cfe-4313-9164-43473ab84c97', '2030-10-16 17:00:00+07', '2030-10-16 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('76b81492-f11b-449d-9a91-9cc56e068fca', '2030-10-16 21:30:00+07', '2030-10-16 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('71b9bb55-acc4-42d4-b114-1db459398788', '2030-10-17 01:00:00+07', '2030-10-17 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('9eb356b9-06ff-4710-9fa1-6bd17be91354', '2030-10-17 04:30:00+07', '2030-10-17 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('2ea80f0e-02f7-4cd6-bc4f-7938fa3c0c54', '2030-10-16 17:00:00+07', '2030-10-16 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('408cf192-34ac-4380-af63-565ebca1fbdc', '2030-10-16 21:30:00+07', '2030-10-16 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('dd1852f1-3c87-49d2-a7da-345f6af8eec6', '2030-10-17 01:00:00+07', '2030-10-17 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('74426f09-0def-4f28-921e-742cdbfba671', '2030-10-17 04:30:00+07', '2030-10-17 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('e7464fa8-1c52-4b16-be93-2535fd68f3ff', '2025-11-04 19:00:00+07', '2025-11-04 21:28:00+07', 50000.00, 'scheduled', '387a5681-88d1-46e6-8e8b-01666d617581', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('0f4589c0-0758-4578-bb87-80b144727c15', '2030-10-20 21:30:00+07', '2030-10-20 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('484ccb81-e986-47ef-91f9-32bc3ec5fe0d', '2030-10-21 01:00:00+07', '2030-10-21 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('2295007f-2f67-49e1-b55d-12e4274d5f20', '2030-10-21 04:30:00+07', '2030-10-21 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('e920da1a-10c7-4da4-9f65-dca3ae411eb6', '2030-10-20 17:00:00+07', '2030-10-20 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('d0a6ba74-af12-4dd7-8535-8a2d29aa76eb', '2030-10-20 21:30:00+07', '2030-10-20 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('444e8798-f7d0-4d7b-8d4d-39da04448ed4', '2030-10-21 01:00:00+07', '2030-10-21 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('abbaea29-736d-4c62-a7ad-0a63809457d6', '2030-10-21 04:30:00+07', '2030-10-21 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('5aca2225-52d4-40c8-a158-88c3f787b9b3', '2030-10-20 17:00:00+07', '2030-10-20 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('d58de2d3-c4ee-40a5-8f4a-60e77a921891', '2030-10-20 21:30:00+07', '2030-10-20 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('b8ab000a-ad5b-41e4-8dc1-8ddbd2e5462c', '2030-10-21 01:00:00+07', '2030-10-21 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('8620a15d-4af6-4621-a4da-4c1f3d8a7d91', '2030-10-21 04:30:00+07', '2030-10-21 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('c335604a-4bb9-49ec-9aea-6c6e3d7426d7', '2030-10-21 17:00:00+07', '2030-10-21 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('3095826d-e100-455c-871b-2106ff003c34', '2030-10-21 21:30:00+07', '2030-10-21 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('5c7a20c0-9205-4591-9dc3-be50347a2ee4', '2030-10-22 01:00:00+07', '2030-10-22 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('26637524-a423-40af-aac4-7eb869d314f9', '2030-10-22 04:30:00+07', '2030-10-22 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('011071b6-fe0e-4490-9837-89eee9e6a48c', '2030-10-21 17:00:00+07', '2030-10-21 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('24862343-4362-44d1-9eb9-02ac3bb0908c', '2030-10-16 17:00:00+07', '2030-10-16 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('0a1f3a8b-b962-40b4-8c27-0ef9e1fdfc56', '2030-10-16 21:30:00+07', '2030-10-16 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('cd0fc189-23ba-4af3-8735-ef14b71dece9', '2030-10-17 01:00:00+07', '2030-10-17 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('c24d9006-04af-4201-82da-53828ea0014e', '2030-10-17 04:30:00+07', '2030-10-17 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('90080ad2-acde-4014-b903-ba0131410604', '2030-10-17 17:00:00+07', '2030-10-17 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('a4096127-8606-4690-aa9b-00701598d357', '2030-10-17 21:30:00+07', '2030-10-17 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('52243121-93d3-4773-9fb7-6e37df85173f', '2030-10-18 01:00:00+07', '2030-10-18 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('12751c97-cc01-4ad7-90a5-a65de34feafe', '2030-10-18 04:30:00+07', '2030-10-18 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('9c95ab65-7634-4a30-ac3d-96fd34a2ea37', '2030-10-17 17:00:00+07', '2030-10-17 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('bad686bb-e742-441c-97b3-06f770740ee5', '2030-10-17 21:30:00+07', '2030-10-17 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('259a4281-dfe7-4f5f-be17-5e3648ae4077', '2030-10-18 01:00:00+07', '2030-10-18 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('4beb4642-d6cb-4c5f-8a96-5199133891eb', '2030-10-18 04:30:00+07', '2030-10-18 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('f5a77cb9-d036-44ab-b4ea-6f0e4c8cf01f', '2030-10-17 17:00:00+07', '2030-10-17 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('a5b0fcf7-14a2-4dbd-a695-50802ac75b44', '2030-10-17 21:30:00+07', '2030-10-17 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('6fa62058-4a74-43af-9c98-530dc051f51d', '2030-10-18 01:00:00+07', '2030-10-18 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('9f85e987-e0ad-4238-8346-ef5e06e038fe', '2030-10-18 04:30:00+07', '2030-10-18 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('0a8c6468-d95f-47e9-9aa4-0288bb1d56b6', '2030-10-18 17:00:00+07', '2030-10-18 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('c6ec755c-a1c8-4e42-9c77-b4ea814f8380', '2030-10-18 21:30:00+07', '2030-10-18 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('331a795d-6274-4cfc-9f26-b3a221dbe0fb', '2030-10-19 01:00:00+07', '2030-10-19 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('36f16aed-b50e-409a-a8f6-253ca7bb6395', '2030-10-19 04:30:00+07', '2030-10-19 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('ce5c2ff7-d80d-417b-9747-b1875b9147f0', '2030-10-18 17:00:00+07', '2030-10-18 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('aa1e970c-6327-4426-a833-ba978ea61b16', '2030-10-18 21:30:00+07', '2030-10-18 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('ad69c4d8-2bb6-4063-96b3-ed020bc3e3a2', '2030-10-19 01:00:00+07', '2030-10-19 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('be555ebb-9e04-4df2-a13a-0557c4ed1451', '2030-10-19 04:30:00+07', '2030-10-19 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('b506ba74-5504-4786-a1aa-bc1a703f3f41', '2030-10-18 17:00:00+07', '2030-10-18 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('fa1702b2-21fa-457f-86f3-099539a7f003', '2030-10-18 21:30:00+07', '2030-10-18 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('3d55e7e6-886f-4683-8863-d8ae62952d23', '2030-10-19 01:00:00+07', '2030-10-19 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('10013138-99fd-4b09-b71c-158c5a150712', '2030-10-19 04:30:00+07', '2030-10-19 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('9f24a5c3-cf7a-4e02-ab69-af33a9b081c6', '2030-10-19 17:00:00+07', '2030-10-19 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('862ac0e2-8d57-49f7-9ec9-53dac73b5c91', '2030-10-19 21:30:00+07', '2030-10-19 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('7fd4cfde-b250-4759-8514-6379d6095fd9', '2030-10-20 01:00:00+07', '2030-10-20 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('943ee495-3a55-4412-882c-05de6fb4e849', '2030-10-20 04:30:00+07', '2030-10-20 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('eb66f2b1-bec6-4955-821d-e44d281bbf8d', '2030-10-19 17:00:00+07', '2030-10-19 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('68d19eff-6b73-4ad0-ab1a-da0503e623b9', '2030-10-19 21:30:00+07', '2030-10-19 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('3008bff6-2696-433c-8346-43fe5b1e44e8', '2030-10-20 01:00:00+07', '2030-10-20 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('73720528-4a10-4158-a304-e813bf940b8d', '2030-10-20 04:30:00+07', '2030-10-20 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('4ad1d20b-0b89-4364-bdab-94f66d0fe4de', '2030-10-19 17:00:00+07', '2030-10-19 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('f4c6998f-3c3c-4eb0-a53d-6d95f82e975c', '2030-10-19 21:30:00+07', '2030-10-19 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('d7275a36-ec7d-4959-a26e-eded8121f726', '2030-10-20 01:00:00+07', '2030-10-20 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('e026f08f-d527-4728-b19f-fc349729907c', '2030-10-20 04:30:00+07', '2030-10-20 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('8e30172b-0769-4262-ac75-e741e213cfc9', '2030-10-20 17:00:00+07', '2030-10-20 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('adc6565e-54e8-4020-a4fc-0bf64af95a4a', '2030-10-21 21:30:00+07', '2030-10-21 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('ae07365d-8f29-4e1d-941c-86b93911d6d3', '2030-10-22 01:00:00+07', '2030-10-22 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('accb9c2b-3bc1-4abd-a0e4-2d0a8231d680', '2030-10-22 04:30:00+07', '2030-10-22 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('c9352d34-df30-4b10-bea6-e6e34ee15a15', '2030-10-21 17:00:00+07', '2030-10-21 19:32:00+07', 80000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('96e4aaa8-7cea-4fff-9e63-cd6f2de86ea7', '2030-10-21 21:30:00+07', '2030-10-21 23:58:00+07', 100000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('4477373c-78c2-4890-ab8f-ae9eddaee063', '2030-10-22 01:00:00+07', '2030-10-22 03:49:00+07', 120000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('eac5b98e-e45b-4af4-8df6-dd06e81af004', '2030-10-22 04:30:00+07', '2030-10-22 06:53:00+07', 150000.00, 'scheduled', '90d82435-d468-45d6-9c9c-b3432bd65a1c', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('85a96a56-6539-4f14-8789-3823460ca29b', '2030-10-22 17:00:00+07', '2030-10-22 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('cff95ff8-f21a-49f2-9322-d72539f5c982', '2030-10-22 21:30:00+07', '2030-10-22 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('7c6b7a2d-06a5-46db-97b3-f05785cc7d8e', '2030-10-23 01:00:00+07', '2030-10-23 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('c96df219-f627-4c54-a521-c3c23549ba88', '2030-10-23 04:30:00+07', '2030-10-23 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('7864f08d-9b70-4661-a25c-5b7305ecefeb', '2030-10-22 17:00:00+07', '2030-10-22 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('6ab75d60-d74f-4fac-b816-f5df156f4f4a', '2030-10-22 21:30:00+07', '2030-10-22 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('3d82cce0-2727-45e4-9db7-a23851c1dad9', '2030-10-23 01:00:00+07', '2030-10-23 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('cfd2bfcf-7e68-4622-b6c9-75aa8fe11146', '2030-10-25 17:00:00+07', '2030-10-25 19:32:00+07', 80000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('eb699b88-5b4d-4673-8388-d8e70eb691cc', '2030-10-25 21:30:00+07', '2030-10-25 23:58:00+07', 100000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('ba5c9365-5b23-4d55-ae25-9b042ba1e4f6', '2030-10-26 01:00:00+07', '2030-10-26 03:49:00+07', 120000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('692f6a09-e9a9-4386-86ba-2ec51934d195', '2030-10-26 04:30:00+07', '2030-10-26 06:53:00+07', 150000.00, 'scheduled', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_showtime VALUES ('8ab64331-9341-4a14-96ad-11356e97c902', '2030-10-25 17:00:00+07', '2030-10-25 19:32:00+07', 80000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_showtime VALUES ('f2449697-e61b-4bbd-b7a0-b8ed7bfa30ac', '2030-10-25 21:30:00+07', '2030-10-25 23:58:00+07', 100000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_showtime VALUES ('53666aca-aac7-485f-9caf-6fa6ab8c911a', '2030-10-26 01:00:00+07', '2030-10-26 03:49:00+07', 120000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_showtime VALUES ('2335c32f-a448-4f2b-bbe1-5363815e6416', '2030-10-26 04:30:00+07', '2030-10-26 06:53:00+07', 150000.00, 'scheduled', '4f3098da-57b2-4100-bb89-4a9a792a5b6e', '2babc793-bc35-4ca2-b60c-9402a8d639c1');


--
-- Data for Name: api_user; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_user VALUES ('pbkdf2_sha256$1000000$fjiagIirU4gwkwVlkdmt7f$8Pzq4qrh2MLnwWVrGsgzXBz0+q7MTvR15GjxtiJrASQ=', '2025-10-18 18:32:50.377159+07', false, 'test1', 'test', '1', false, true, '2025-10-16 08:57:05.874818+07', 'e1a45848-8be0-4cce-8da7-0e6805426e57', NULL, 'test1@example.com', NULL, 'user', '2025-10-16 08:57:05.87489+07', '2025-10-16 08:57:06.050288+07', NULL);
INSERT INTO public.api_user VALUES ('pbkdf2_sha256$390000$V1QK/mS4SU+5$Kpv6rJeLNgB8HIx5ijwRBymd+UVrkyEuUnq348qeAbc=', NULL, false, 'test', 'test', '', false, true, '2025-11-02 02:55:02.107189+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', 'test', 'test@gmail.com', '0123456789', 'user', '2025-11-02 02:55:02.107189+07', '2025-11-02 02:55:02.107189+07', NULL);
INSERT INTO public.api_user VALUES ('pbkdf2_sha256$1000000$jqbiGTYxCAOaoOyfV5zLtt$UFsOCxJAj5AzivSViG/iwMr3rLh+Ks6HWp5bsxFqICs=', '2025-10-17 16:15:54.291803+07', true, 'admin', 'CODESEAT', '', true, true, '2025-10-16 08:43:06.131272+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', 'CODESEAT', 'admin@gmail.com', '0666666666', 'user', '2025-10-16 08:43:06.321703+07', '2025-11-03 22:04:42.898143+07', NULL);


--
-- Data for Name: api_booking; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_booking VALUES ('ac983c49-d44a-4145-963e-ec03fa17a272', 'paid', 120000.00, 'cash', '2025-11-02 01:17:25.229006+07', '2025-11-02 01:27:25.229027+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_booking VALUES ('1d85f46a-6e5f-4b54-9b2a-9176eaab8d69', 'paid', 100000.00, 'cash', '2025-11-02 01:25:57.918305+07', '2025-11-02 01:35:57.918327+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('a306effc-20e1-4b18-8e36-75e22922c3bd', 'paid', 160000.00, 'cash', '2025-11-02 02:14:15.911409+07', '2025-11-02 02:24:15.911445+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_booking VALUES ('fdca7a73-2293-43d1-b705-dbc6917266d3', 'paid', 80000.00, 'cash', '2025-11-02 03:25:17.192243+07', '2025-11-02 03:35:17.192282+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '9f85e987-e0ad-4238-8346-ef5e06e038fe');
INSERT INTO public.api_booking VALUES ('c7321ca5-6bee-4898-b895-d08574a1229f', 'paid', 50000.00, 'cash', '2025-11-02 03:33:25.685816+07', '2025-11-02 03:43:25.685828+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_booking VALUES ('3d960848-3359-4afc-9188-af0f539df473', 'paid', 80000.00, 'cash', '2025-11-02 03:37:37.411236+07', '2025-11-02 03:47:37.411251+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '68d19eff-6b73-4ad0-ab1a-da0503e623b9');
INSERT INTO public.api_booking VALUES ('6f9c5f6f-1245-40b0-91ae-c40591188a3a', 'paid', 50000.00, 'cash', '2025-11-02 03:43:03.849789+07', '2025-11-02 03:53:03.849818+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('723eacd5-dd34-4a83-8776-6fb1b955c89d', 'paid', 80000.00, 'qr_code', '2025-11-02 04:01:12.577008+07', '2025-11-02 04:11:12.577089+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('139de424-37eb-425b-96d7-a95c2f610bb3', 'paid', 50000.00, 'qr_code', '2025-11-02 04:01:59.757463+07', '2025-11-02 04:11:59.7575+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('137c6fac-55b6-41f3-ba82-393e1b68ad8d', 'paid', 160000.00, 'cash', '2025-11-02 04:04:10.197682+07', '2025-11-02 04:14:10.197717+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('4bc09a2c-47b2-4585-9507-7cbe5c64a24c', 'paid', 160000.00, 'qr_code', '2025-11-02 04:03:51.70683+07', '2025-11-02 04:13:51.70685+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('a79b4d45-5c1e-419f-9083-f86ae0c72028', 'canceled', 50000.00, 'qr_code', '2025-11-02 04:05:52.436391+07', '2025-11-02 04:15:52.436431+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('01de54ea-7612-4d24-91a2-edac2ff837b4', 'paid', 50000.00, 'cash', '2025-11-03 21:43:07.009847+07', '2025-11-03 21:53:07.009882+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('f209a690-caed-45e6-9d4b-fd8d72865761', 'paid', 50000.00, 'cash', '2025-11-03 21:53:58.985152+07', '2025-11-03 22:03:58.985169+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('38983d93-f955-441d-a71e-49e6a5d87f44', 'paid', 80000.00, 'qr_code', '2025-11-03 22:05:20.640065+07', '2025-11-03 22:15:20.640088+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '408cf192-34ac-4380-af63-565ebca1fbdc');
INSERT INTO public.api_booking VALUES ('a8cc555c-cbde-4028-a601-1b87c9d9f31f', 'canceled', 50000.00, 'qr_code', '2025-11-03 23:09:54.208912+07', '2025-11-03 23:19:54.208932+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('19c5dc0e-9aa4-44d5-8dc3-1448a8b8198a', 'paid', 160000.00, 'qr_code', '2025-11-04 14:38:59.903978+07', '2025-11-04 14:48:59.903984+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', 'dfcad975-2ce9-4506-a891-3b4c213048f8');
INSERT INTO public.api_booking VALUES ('eed50131-ccf1-4781-ba6d-6ad1c2ae1ca9', 'paid', 160000.00, 'cash', '2025-11-04 14:42:22.948687+07', '2025-11-04 14:52:22.948711+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '7e8c8a25-247c-4add-a3c4-897ea5d39d3b');
INSERT INTO public.api_booking VALUES ('e9ed9796-a2f6-46f0-af58-de74ce451217', 'paid', 160000.00, 'qr_code', '2025-11-04 14:42:05.879592+07', '2025-11-04 14:52:05.879614+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', '6f160a1a-3f89-424b-9602-1471a9348969');
INSERT INTO public.api_booking VALUES ('7c32b859-0626-4f17-80dc-572ec8a2d01b', 'paid', 300000.00, 'qr_code', '2025-11-04 14:56:07.235164+07', '2025-11-04 15:06:07.235189+07', 'c011f803-fc4e-4adf-94cd-304a1edd5d24', 'e7464fa8-1c52-4b16-be93-2535fd68f3ff');
INSERT INTO public.api_booking VALUES ('bf130f1b-caab-452b-ab39-4993ae24f39d', 'paid', 160000.00, 'cash', '2025-11-05 00:15:42.687218+07', '2025-11-05 00:25:42.687254+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_booking VALUES ('0afddc4d-fb4b-444a-bff5-2368661d8db9', 'paid', 160000.00, 'qr_code', '2025-11-05 00:25:52.19836+07', '2025-11-05 00:35:52.198409+07', '7d33fe88-fa1e-484e-82a6-48c7af3bd993', '408cf192-34ac-4380-af63-565ebca1fbdc');


--
-- Data for Name: api_genre; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_genre VALUES ('27db57d8-9402-45d6-ad4e-20fbf4b2b013', 'Action');
INSERT INTO public.api_genre VALUES ('b54f2a72-4145-4a70-82de-aded3084c1e9', 'Comedy');
INSERT INTO public.api_genre VALUES ('f96b6ebb-34c2-4aad-b87f-2c05a875538f', 'Drama');
INSERT INTO public.api_genre VALUES ('9f186a0f-4b29-4ece-a48b-7c0db54da153', 'Horror');
INSERT INTO public.api_genre VALUES ('02ee4021-89f8-406d-ab67-6f2c5dd049da', 'Sci-Fi');
INSERT INTO public.api_genre VALUES ('6d639857-4b6e-4aeb-a603-5f774f6bf618', 'Romance');
INSERT INTO public.api_genre VALUES ('4c94aadf-749b-4c8d-963a-d8684059df99', 'Thriller');
INSERT INTO public.api_genre VALUES ('ec605c93-62d1-473e-b33c-b70f33527c42', 'Animation');
INSERT INTO public.api_genre VALUES ('49ff2e35-d9af-46d5-a6e3-70771692c3a5', 'Adventure');
INSERT INTO public.api_genre VALUES ('50765972-74b2-4ce7-8851-dafaf19769fb', 'Crime');
INSERT INTO public.api_genre VALUES ('16078605-7d6e-43f5-b216-d7b18ef008f0', 'Fantasy');
INSERT INTO public.api_genre VALUES ('15e03d31-aa1f-41f0-82a2-145454fe52e6', 'Mystery');
INSERT INTO public.api_genre VALUES ('0b61d423-d4ec-4ae9-b32e-95b182b2bf89', 'War');
INSERT INTO public.api_genre VALUES ('b0892961-c501-4be3-a96e-6d1e26a0f00f', 'Documentary');
INSERT INTO public.api_genre VALUES ('bde3170e-b914-4be7-99cc-6b62c6a7a75d', 'Musical');


--
-- Data for Name: api_moviegenre; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_moviegenre VALUES (61, '27db57d8-9402-45d6-ad4e-20fbf4b2b013', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_moviegenre VALUES (62, '02ee4021-89f8-406d-ab67-6f2c5dd049da', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_moviegenre VALUES (63, '4c94aadf-749b-4c8d-963a-d8684059df99', 'e892e566-b9c2-4a58-885b-607cf67e8326');
INSERT INTO public.api_moviegenre VALUES (64, '27db57d8-9402-45d6-ad4e-20fbf4b2b013', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_moviegenre VALUES (65, '50765972-74b2-4ce7-8851-dafaf19769fb', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_moviegenre VALUES (66, 'f96b6ebb-34c2-4aad-b87f-2c05a875538f', '0fc89c4b-9228-4c10-b92a-80f94da1de9e');
INSERT INTO public.api_moviegenre VALUES (67, '27db57d8-9402-45d6-ad4e-20fbf4b2b013', '2dfd85ae-3674-45d6-af81-fce482b6f25e');
INSERT INTO public.api_moviegenre VALUES (68, '49ff2e35-d9af-46d5-a6e3-70771692c3a5', '2dfd85ae-3674-45d6-af81-fce482b6f25e');
INSERT INTO public.api_moviegenre VALUES (69, '02ee4021-89f8-406d-ab67-6f2c5dd049da', '2dfd85ae-3674-45d6-af81-fce482b6f25e');
INSERT INTO public.api_moviegenre VALUES (70, '27db57d8-9402-45d6-ad4e-20fbf4b2b013', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_moviegenre VALUES (71, '49ff2e35-d9af-46d5-a6e3-70771692c3a5', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_moviegenre VALUES (72, '02ee4021-89f8-406d-ab67-6f2c5dd049da', '2babc793-bc35-4ca2-b60c-9402a8d639c1');
INSERT INTO public.api_moviegenre VALUES (73, '49ff2e35-d9af-46d5-a6e3-70771692c3a5', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_moviegenre VALUES (74, 'f96b6ebb-34c2-4aad-b87f-2c05a875538f', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_moviegenre VALUES (75, '02ee4021-89f8-406d-ab67-6f2c5dd049da', 'f19673f2-af2a-4faa-86b7-841b8ea77836');
INSERT INTO public.api_moviegenre VALUES (76, 'b54f2a72-4145-4a70-82de-aded3084c1e9', '55465414-1a44-4fdc-828c-adddc51ae144');
INSERT INTO public.api_moviegenre VALUES (77, 'f96b6ebb-34c2-4aad-b87f-2c05a875538f', '55465414-1a44-4fdc-828c-adddc51ae144');
INSERT INTO public.api_moviegenre VALUES (78, '4c94aadf-749b-4c8d-963a-d8684059df99', '55465414-1a44-4fdc-828c-adddc51ae144');


--
-- Data for Name: api_payment; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: api_seat; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_seat VALUES ('409e4b48-8a4e-4dc9-a826-76ea1afbebf6', 'A', 1, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('de8f443b-ee9c-402d-a878-2eb2a14a38bb', 'A', 2, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('52b21a89-d282-42bc-a094-a755f7787f8d', 'A', 3, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d0db4fcf-8ef6-4052-886c-afb5f18d63fe', 'A', 4, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('564b2453-7ff6-451d-9755-4fa8eed9ab45', 'A', 5, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('67e47b13-72c2-458b-9fa5-77b6b827d42c', 'A', 6, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('449048d1-c285-49fd-bb49-c1251e583264', 'A', 7, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('fe1ae6e4-c739-407b-a7e5-30494d5fcefe', 'A', 8, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('38fe57d0-3899-4cb6-b40d-8b58d293bbdf', 'A', 9, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('73124f9e-5d36-46a7-a37e-ed100ad6c902', 'A', 10, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('99d7dcd0-8592-4406-963f-8976f0ba08e0', 'A', 11, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9229f60e-ed8b-4cda-b568-685ff60d9a08', 'A', 12, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9ee92b10-a6a6-4c6c-aa69-9366c7ae7182', 'B', 1, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('fd236466-bbf9-40a2-9496-2e97a1537ac8', 'B', 2, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('fa786a1b-dd9e-4ae7-854b-8efd72080d95', 'B', 3, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('3fd75017-6f5a-4fb2-a95f-5fd15c9e4169', 'B', 4, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('75d1043e-bf12-4fcf-8ae9-6947f0cb43f8', 'B', 5, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9e1a563c-a9bb-4d92-99d7-72c7d6322eff', 'B', 6, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9cb9d54f-e724-486a-9b4d-7fcff5670ceb', 'B', 7, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('c45d7aba-3118-43c6-a319-4b1121388b3a', 'B', 8, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('6c4cf9f6-230b-4d3e-8a7d-1ae0ebc0d679', 'B', 9, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('7c7b6586-c44a-4fef-bab5-c20d633ecab9', 'B', 10, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('66180ae3-5a0c-4340-844c-bdc9e97995dc', 'B', 11, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9e518133-69f7-42fc-8008-656c8b038e0f', 'B', 12, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('c6b5390a-a7e6-4196-a3aa-d938aacdf08d', 'C', 1, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('cc72dfff-989c-46f0-acf8-70564d5a6e2c', 'C', 2, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('7c5fd6fa-70f9-4396-8d0a-41f4bc121ef5', 'C', 3, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('0e1263af-85b8-44f9-8eaf-6f6a57e65ba2', 'C', 4, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('880b4e6f-f718-437d-a882-fe793b0d3020', 'C', 5, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('809c6fbe-882e-4f11-be2b-b2c56d729e56', 'C', 6, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('72829ada-0c57-4173-b420-e3efccf5dc80', 'C', 7, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('c9376489-d474-4c22-9bba-b37a3dfb6fa2', 'C', 8, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d18f2bb4-eb04-4a7e-8f6f-180bc99aa8cc', 'C', 9, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('43397458-6502-43bd-905b-18c763bb6de9', 'C', 10, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('ad2048d7-67d5-477d-ab2b-7c70122bd2b4', 'C', 11, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('44914766-435f-4d71-bdaf-d4bd9563cbc8', 'C', 12, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('4f8ff0ed-b03d-4715-a99c-bd5d2ef4e554', 'D', 1, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('48db04f8-3e0b-488d-b278-e2dad00bbc72', 'D', 2, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('31ebe249-22f0-40b5-bf36-28bf76699824', 'D', 3, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('1b58b2aa-6bc5-4b42-a569-27f9414a34bd', 'D', 4, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('50ed2369-149f-4296-995b-5a27a9f6ca71', 'D', 5, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('78812781-fb21-4ce1-b9e6-aa0884025017', 'D', 6, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('729c67b8-e3ee-4f22-9bbe-5faf26bd6969', 'D', 7, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('118e4ca5-b854-4f5e-a151-8829ed823d09', 'D', 8, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('af2331b2-ae6d-4dcb-9e30-316dcaad415b', 'D', 9, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('32a44e4f-916f-4f63-b8df-404240c3c044', 'D', 10, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('270eed77-8f07-4f41-912b-417dba946b10', 'D', 11, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('3b4d6f01-71e5-4b16-b664-90dfc726743e', 'D', 12, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('a0e4d78f-9a71-4061-8398-f3f90d9e9002', 'E', 1, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('c2e593ce-0755-4bff-9c38-bda332545aef', 'E', 2, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('65cb3748-32f4-433e-b738-72e10b2d9018', 'E', 3, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('c084f714-a689-42c4-b773-4043f35306ac', 'E', 4, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('7ba0c38b-3453-4f96-89dc-63bd4e8beaec', 'E', 5, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f63183a1-bd7e-42a7-bcec-3a26a0b91685', 'E', 6, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d9df3f35-b90d-494d-a8c8-4449cbcd95e5', 'E', 7, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('a415af0d-4094-4336-bfa7-641a692a80dc', 'E', 8, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f726009a-73a2-443f-87d7-2b0d8950ac85', 'E', 9, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('10276615-d41b-4f44-930c-73515c0a14cb', 'E', 10, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('38dc3faa-3176-42c2-976a-b60208b5a15e', 'E', 11, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('fe3c7f0f-b144-431f-9340-dc9683ff1410', 'E', 12, 'standard', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('263d9383-4d51-43ba-9b66-c0cb3278e6a5', 'F', 1, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('b1a512fb-6e98-4b3c-ab8b-cc4ad3885796', 'F', 2, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('39f5395b-4ee0-40a1-9444-8398e5c69480', 'F', 3, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d47cfcdc-7b8a-4a34-8b52-9a1076c71450', 'F', 4, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('9269d79b-2e6f-4bf2-b066-64773b715e6b', 'F', 5, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('42b4f0cf-b124-44dc-8bae-9d1dbdfcd6dd', 'F', 6, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d5fc851b-e408-426a-9f50-e7b1779e43bd', 'F', 7, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('91f8dc8d-90c5-4a53-ad4e-df75d92701f4', 'F', 8, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('1f94e4fc-1678-432b-b2df-63a08a1a69cc', 'F', 9, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('47547ab5-c8cc-40c0-8146-a5165835c606', 'F', 10, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('62df191d-1366-4d62-ae93-a200711ea69a', 'F', 11, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('aae17b47-dfd2-4af0-a717-caf87b3a2127', 'F', 12, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('237ce970-72d7-4aac-a5ee-e785f77fa565', 'G', 1, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('6fbc8345-4851-4516-9719-21ab3d1fcf38', 'G', 2, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('63e3eb71-62be-412a-809a-de8b0d6757e7', 'G', 3, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('dafb1d5b-e234-48cc-84a6-39e8ddea336d', 'G', 4, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('59291a9a-6f2f-441c-8f6f-c327225399c6', 'G', 5, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('05d18288-933c-4750-ada1-735d386bacb4', 'G', 6, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f6b09252-69cf-4cec-9b92-acea7ad627fd', 'G', 7, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('5c4dc173-5f05-494c-9bad-33a7626f4fc3', 'G', 8, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('48b192b2-c93e-45ac-85a0-4f5f393a3ff0', 'G', 9, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('5ca7a630-62ac-440f-acbf-88610edf4f53', 'G', 10, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('24beb5aa-d8d6-4e55-8132-18799045a9a5', 'G', 11, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('22185f78-7281-44bc-b4b8-6f64e839c0f1', 'G', 12, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d091c2d6-37dc-4597-9308-acffae16370b', 'H', 1, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('90966ef8-54ac-4e6a-bc17-dfc6fa8760fa', 'H', 2, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('28445ee9-7d0d-47ea-b05d-04c26d0f1c35', 'H', 3, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('23ab2946-bbef-4c45-9d07-e69fe46bc1a5', 'H', 4, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('2d0b2948-900d-4938-9c44-dc255d9872fb', 'H', 5, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('7dc9fb21-7c29-436a-8090-1b247191b079', 'H', 6, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('4819df7d-e990-4d7b-9457-635ab7186004', 'H', 7, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('7c49ef97-415c-426e-aedc-c198923fb6e4', 'H', 8, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('cfd4ae36-e663-4bee-b3d2-6ba0a014c7fc', 'H', 9, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('50206a43-81a6-4984-bad8-258216757faf', 'H', 10, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f7a0f03c-59ba-49c7-a515-2365754063c4', 'H', 11, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f12ed9f2-e0ac-4ca7-9de2-b0060615684d', 'H', 12, 'vip', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('6108df86-a261-4a95-827d-77b4109c9fd4', 'F', 8, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('112aef31-60f6-4988-bf33-8c0de4118e85', 'F', 9, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('f082dba7-4a45-4d5e-82c3-9b0a0fb29207', 'F', 10, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('e2c6e191-bd53-401e-8615-793886f1699f', 'F', 11, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('49a30c80-51f2-46e0-88b4-bd351be40fa2', 'A', 1, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('98ba6dd7-e2d0-4721-9ffd-b6433e57b5dc', 'A', 2, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('167fd68c-28b8-4995-93e1-d589c79d8c6c', 'A', 3, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('4e4cc2ea-6e15-4b98-96f5-97e83ac7f400', 'A', 4, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('9d20a415-a202-4776-8666-fbd3e1d62a59', 'A', 5, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('823720ac-3147-4419-9f19-2f49e2ea2266', 'A', 6, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f6e39606-f470-4c0c-b466-f69be6f9810e', 'A', 7, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('fb720028-0805-495e-952d-a241d2215196', 'A', 8, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e416264d-e127-4f44-929e-e8c645944039', 'A', 9, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('ee8df26b-16a0-43c4-a1f7-3b565cd8ea3c', 'A', 10, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('0aa9b98f-a4dc-4160-a79b-f49c91d628da', 'A', 11, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f3356bdc-e008-4d04-8f84-12687f3491b1', 'A', 12, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('35d8a732-aa76-491f-8f6d-aca0b1a8db4f', 'B', 1, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('89fb9974-48fe-496e-b3e7-53bf6e327cc3', 'B', 2, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('283e3f89-5143-467b-8b99-13f92bb8f4f6', 'B', 3, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('b3697d98-23fb-4f87-af19-f534599b23e8', 'B', 4, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('8575676a-5c83-4047-ba6a-3daa8f619acd', 'B', 5, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d2fd72ce-276c-40de-b54e-aa8efd59cd63', 'B', 6, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('1c68ece2-dffb-493f-b119-cc73869e6282', 'B', 7, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('9a938c0b-b3f5-494a-804a-4349e144ddc9', 'A', 1, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('2751a99e-a935-4661-bf94-05c9e4dcee92', 'A', 2, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('602a0780-80ff-47e4-83df-163cf913c729', 'A', 3, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e1ac1fc5-ad0d-462b-850a-a1d47cd5f542', 'A', 4, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('1b558eb0-d9d6-4a5a-958e-acc6fe37d518', 'A', 5, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('dea97c83-5d91-4ab9-b82e-c6e3589ed403', 'A', 6, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('155651db-f345-4232-82b9-4d1f26ee14bd', 'A', 7, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('46fca001-15dd-43a0-bd50-432a950b0dac', 'A', 8, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('c900a9a7-725f-4318-8e75-c1e76b9d196f', 'A', 9, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('00c76431-3758-4bbb-9aed-c4c1afa528f8', 'A', 10, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('ff07747f-d05c-46ee-b707-569be02fd64c', 'B', 1, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('0ecbca84-0448-4239-a33b-fee403acbc32', 'B', 2, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('634dcfd9-846c-414c-8e48-d8c3deb2b7b0', 'B', 3, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('1c47861b-6a04-4688-a80c-eb6776fa98f4', 'B', 4, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('de785767-f682-4e5e-87d5-6245ec455f20', 'B', 5, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('42dc98d6-504d-4801-bcc5-06d11df4b9ad', 'B', 6, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('8c24e723-89ea-4c06-bd42-fe921419f64e', 'B', 7, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('3490643d-8e5b-48ee-a107-96662450b8a0', 'B', 8, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('1ba2e24a-21a2-47d4-8b36-9ef570999b61', 'B', 9, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('740ae51b-9991-4d80-a77b-4dc3e152451f', 'B', 10, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('cb968c83-9fae-45e2-afbc-260c9fa225a3', 'C', 1, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('8f931bff-cd59-4b3c-a25c-75f4cf48494c', 'C', 2, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('6c339667-2c66-43a4-b9c9-c54b592a68ea', 'C', 3, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('77415b5a-6916-41c4-9ee2-687377f3a974', 'C', 4, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('396e4817-4130-4263-b5f7-a22226024d6c', 'C', 5, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('1e9a1ab4-2e20-4fda-9bb3-cae8978719c9', 'C', 6, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('dc61bb74-5f69-4fad-956a-246e5d2800c9', 'C', 7, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e10e082e-52ee-4846-b156-5e903c53ba35', 'C', 8, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e5155700-1712-46bd-b2f3-3e22444b49cc', 'C', 9, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('807ef4d0-1709-41cd-88c2-6e00f60f054d', 'C', 10, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('314c612e-fb3b-454c-ba1f-10478f90e5f6', 'D', 1, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('2abfe6bc-fef7-48ff-be52-c821caae9663', 'D', 2, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('c588bfc7-1020-4ff1-80e4-6d466868188d', 'D', 3, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('ed0159b5-c92b-4be8-b27b-90f8245e2e9a', 'D', 4, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('b731fda6-0ff0-46ec-8bc3-ad32d8075677', 'D', 5, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('4be2f9f7-bf44-4afb-bf2a-520c01d639d8', 'D', 6, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('c255ff9b-4e5e-446b-be2f-6f9dafead5b3', 'D', 7, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('21ab5eb0-95f8-4be5-a717-50b89ba6bdc3', 'D', 8, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('5f5e2952-3ba5-4b05-a40e-7c8ca81a8f49', 'D', 9, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('bfdc5879-e813-442c-9e2f-07ec7284056f', 'D', 10, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('60d1ce51-c80d-425d-aa55-26839a50cf5e', 'E', 1, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('0d3be32a-2251-45e2-ba5c-34e01d816c2b', 'E', 2, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('083080be-205c-4dfd-a847-5f3f96acfffb', 'E', 3, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('907dd1af-4d0c-447b-9d86-5fee9b86ff61', 'E', 4, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('f494a276-7d8d-4956-a729-7984d22c8da7', 'E', 5, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('6f9f3cae-4e9a-469e-87b6-a12c0fa1725f', 'E', 6, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('50187af4-48ab-43e8-b30a-7519f27e0df9', 'E', 7, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('889befb1-e12d-4fb6-8078-023725ae7c22', 'E', 8, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('a1abf252-e8f8-4868-a8b3-1dc3d7c24e06', 'E', 9, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('9d07561f-c0c6-4598-8701-af0533be9031', 'E', 10, 'standard', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('4c5c0eb1-e022-435c-8931-61f2697446d1', 'F', 1, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e88455ab-e3cc-4b2e-8810-195e9d8f9c38', 'F', 2, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('bc284bef-bd51-4fd1-b529-f2aed973ca50', 'F', 3, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('870d9a22-7431-46de-a53a-68f012aee107', 'F', 4, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('4f9b7735-adc4-42b9-9342-a905a7f548f3', 'F', 5, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('a426f5ef-4a88-408b-9517-595b3c0df9f7', 'F', 6, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('c30b4e3a-7067-4be6-93ba-eacab38cafc4', 'F', 7, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('f72b5433-e624-4593-999f-a4775e15f5b4', 'F', 8, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('846c7cbb-3129-48a1-8aa7-7bb769ef05af', 'F', 9, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('201e0e87-3255-4137-af05-78311a70c321', 'F', 10, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('68d67465-1cb9-4cdc-b0db-e9f005d88b1d', 'G', 1, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('eb6891ff-69c6-4e24-a367-0314bc338678', 'G', 2, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('baabc59d-145b-4028-b71d-bf412a92390c', 'G', 3, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('91262871-a389-4fbc-b7c6-ee3f83c3a808', 'G', 4, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('d2a44ee3-6713-43cb-bb2b-ad53ba14dfa2', 'G', 5, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('69e7e9a1-df27-4887-b4c5-d259a1fa41b5', 'G', 6, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('57d5c2f6-90ed-4547-9349-08f5925d7863', 'G', 7, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('dd640964-8240-46c1-b654-e2e14f9bde7e', 'G', 8, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('582ed5cd-926e-4c30-b4b4-7a7dc7854668', 'G', 9, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('d0c99e55-d9a2-438f-89c0-b3b5aec9ee1b', 'G', 10, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('8fa4900b-fe55-4ead-a7a3-d3362a672652', 'H', 1, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('8a9956a4-5bae-4ff2-af85-b9e45001edf7', 'H', 2, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('426130e8-326c-4fd3-a6a2-438934dfa333', 'H', 3, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('eafe4e64-3984-4ede-b9c1-3bc52355b08e', 'H', 4, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('b5b3c06d-d35e-4c6e-a306-90185f33505f', 'H', 5, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('bbe55e84-24ef-4b32-a051-0511429117c8', 'H', 6, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('93dc79b5-d61d-492e-bff3-c37509a6bd39', 'H', 7, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('05b2526c-b2b4-486c-a0bd-581025063962', 'H', 8, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('55ac2941-b7ca-4599-9fa3-4d0d63e35a9f', 'H', 9, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('97d22a63-7b43-49de-a2e0-a285019841a8', 'H', 10, 'vip', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('11d2c409-50a8-4d86-b0a5-178218a54691', 'I', 1, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('0b7b7f0a-cf3f-4cc0-8129-9f8160cfb0b8', 'B', 8, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d5b90d5f-0d18-417c-b294-d18693d55441', 'B', 9, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('b8f9caa8-9490-4ce4-bcc2-0cbee91a4bfd', 'B', 10, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7e1ab6bd-a0cf-4ed2-b590-240a8dc84374', 'B', 11, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('91bdc63c-42f1-4b69-934f-73d7f2225963', 'B', 12, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('2f5af4f3-b906-4001-bf19-3b06d40ce2fa', 'C', 1, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('385b2712-2977-4106-b15d-9eac711b3870', 'C', 2, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('8915ef24-0202-483b-89b5-599cc36aca0d', 'C', 3, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('1cc4836b-5ada-4c16-8c59-76b21cf84324', 'C', 4, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('197be9d5-ec53-4527-b8b2-2f7b37bd5830', 'C', 5, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e27a4dd3-ca0b-41ea-8acc-db5874a206a1', 'C', 6, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7d71486e-8075-4a62-b3f7-5aacf3df321a', 'C', 7, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d7c5405f-7415-4335-a663-aa2c82f556c4', 'C', 8, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('4328f7cd-5af9-41ee-ae0d-fe0bced782e5', 'C', 9, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('5ab5fa15-cf03-4539-a37f-b3d10cbd2c69', 'C', 10, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('410e8dea-d010-4a42-bb79-1f6f361e9205', 'C', 11, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('abc1caa8-9631-482f-90f1-628db9f61377', 'I', 2, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('759e9141-5eba-4d8e-8f23-6286f8bbf6af', 'I', 3, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('62751a15-8856-4542-9f50-51ec875a76e0', 'I', 4, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('95fd1881-143f-48fb-b8ca-7fd4a0bd2c9b', 'I', 5, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('54b014ae-8df9-4b02-8636-990df2c2b4d3', 'I', 6, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e3cf4549-beb7-45b1-9089-c482695808fb', 'I', 7, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('3bb1571e-2cf7-41c4-8309-7e0ca999fd1f', 'I', 8, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('9dc2147d-7714-4a40-8f10-5b9a423244c0', 'I', 9, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('68997e8a-180e-45c5-9161-41f7448bdb2e', 'I', 10, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('f81819ab-3faf-4f8b-a6d5-5348211bd969', 'J', 1, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('6c4991ed-d1f0-448f-b668-c53b3c0aa553', 'J', 2, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('cd44860a-fb75-4a94-80f7-38f6171c5e51', 'J', 3, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('36ea46e6-9dc3-4444-b84a-0a6cf236a1bc', 'J', 4, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('936400a9-22dc-416d-b9bb-a1794f8f8055', 'J', 5, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('e32f4cae-b1c5-4f67-9cd1-90c2bb35fcfe', 'J', 6, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('a65b3ad1-27a9-49dc-9dd5-9a4f81a6e2bf', 'J', 7, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('1f6c5834-93d8-4af1-8740-36dfbf60d690', 'J', 8, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('a707867c-dea4-4013-a3a6-64cd3d2b3a1f', 'J', 9, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('7dac584d-fd2a-449c-aceb-c87a43ab76d5', 'J', 10, 'couple', 'deb2285b-882f-4586-bb12-0266b9d6c56d');
INSERT INTO public.api_seat VALUES ('9b946bc7-aba5-4455-a4de-a963429dd375', 'A', 1, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('3a0ba0a2-fe33-4077-8bd2-0700b70749e4', 'A', 2, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('828dfb22-ad87-4718-8d1e-4c387b3aed6f', 'A', 3, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('69d25729-c11f-46a0-9d51-f7cf46b127c5', 'A', 4, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('04b37115-2058-4dfd-9c25-63b074505799', 'A', 5, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('d2a30265-2795-449f-b195-84734b7ae755', 'A', 6, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c7ad1acf-af86-4082-a49e-b5f81360799d', 'A', 7, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('330147f4-5bc7-4d16-8d45-4b58f7fcab4d', 'A', 8, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('0eb50711-abf4-4745-9d0b-631b3688fb7d', 'A', 9, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c9ac6657-f8bf-4edd-972a-5feb19ab9141', 'A', 10, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('73070f22-7aeb-434e-a0d2-8800611e1a5d', 'B', 1, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('daf8e198-f30b-44d6-8f00-06523b25533a', 'B', 2, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('682b5e05-76e6-443c-b129-b5e3a16a8337', 'B', 3, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('83a23a66-7732-4df9-9d7a-8842a639ada1', 'B', 4, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1e95dfdf-f775-43b1-a3a8-58721118e047', 'B', 5, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('bde1655e-f94c-44ff-9ea7-8b2675774777', 'B', 6, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('ebebf4d7-9f28-4cc1-b1b2-c7b3ac408def', 'B', 7, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('5fd4a019-9102-4383-8454-80d79692fd20', 'B', 8, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('05f93c4b-604b-46bf-a1f3-c9d673915993', 'B', 9, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('36aa2736-95c7-4667-8379-84f2e280e0a6', 'B', 10, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('90c3a2aa-ba2e-44e1-81ac-033b5679b604', 'C', 1, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('de4de956-781f-47fb-83d1-e42a0d8d537f', 'C', 2, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('84b963e8-5cd8-4a41-ac89-27480ab0e157', 'C', 3, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('a2efdec9-105b-47bb-bc1a-8035cb786aaa', 'C', 4, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('822563e7-c977-479f-a758-4c43eb885b34', 'C', 5, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c01d4634-bd64-4ba3-ba00-089fb8b813cc', 'C', 6, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('9d602f19-f7d2-4a78-929a-dac3038f4e7e', 'C', 7, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('b4944db0-361e-4495-bbd3-a9cf6bfb003f', 'C', 8, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('2b616e40-569f-4200-8077-9d56b499fbff', 'C', 9, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('43d39292-3587-49ae-8130-c54197388041', 'C', 10, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1bafd14f-fcf0-4564-afc6-1e4bad124f1c', 'D', 1, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('046f2e21-7461-4307-a557-84f9e452baa5', 'D', 2, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1235a3dc-c17d-433b-a73e-8faab8379b91', 'D', 3, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('48526eef-9309-4fad-8012-73035f409136', 'D', 4, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1715e20c-80e7-4b53-a19d-04e70b4d5cec', 'D', 5, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('ee76dc88-0f15-4847-a751-9b8cf12d0848', 'D', 6, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('4d4cc32d-0d66-424d-96ea-e96cb9570541', 'D', 7, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('675cd8a2-b96b-4b5e-9d26-0abd436a3d58', 'D', 8, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('ddccca55-a903-4ae5-b625-1f33a86f4efe', 'D', 9, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('ba2dbb27-1fa0-4aa4-8c6a-cae03f2fd56b', 'D', 10, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('553c8697-c4f0-4ba9-9834-a13300bc58fe', 'E', 1, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('49bab38a-de8f-412f-b9c9-39b55767955b', 'E', 2, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('2640d376-fd1a-4f3c-a7da-3a2c3128af33', 'E', 3, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('e85048d9-8290-419b-8c20-836f75e7a122', 'E', 4, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('646b4172-2cac-4e6c-8cef-ac25ee6e9651', 'E', 5, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('fd4c263b-ee4b-42dc-b57b-a38ee6fa8deb', 'E', 6, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('b8ba5c2d-3d46-4638-a319-cefb2416b877', 'E', 7, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('d2a4a296-71a5-42f2-9326-1c183e0bd3b6', 'E', 8, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1ead5a8c-2b89-49fc-93a4-33e0d16c9354', 'E', 9, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('69296470-8e80-43e3-8a18-5872e03590b6', 'E', 10, 'standard', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('f5cfdc84-8929-4a3b-94f0-922ada166cdd', 'F', 1, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1d8cec89-218b-4ef5-94a9-dd38fa78cbb3', 'F', 2, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('cd5398f6-11da-4fd7-b2e5-de200266e829', 'F', 3, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('b61ae580-d599-4aa1-8914-682883d3a6da', 'F', 4, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('23ba5d81-3fa7-441b-9542-f29e73b5dc97', 'F', 5, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('a2a85427-d8fd-43d0-b2d2-a318f9a076f2', 'F', 6, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('390c6602-aea1-4f17-ad30-f601abb1836e', 'F', 7, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('9db064d4-2253-4c86-aa0e-b61bde36aa37', 'F', 8, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('37a259a0-2bea-492c-9657-f9adb8bbc5f9', 'F', 9, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('304e2f5c-68ec-4646-b6c3-fd4eace422e6', 'F', 10, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('59dca116-99f8-4e52-8754-877ecfe06dd1', 'G', 1, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('9efd61da-01ef-4b77-89e0-77aedbbf4d16', 'G', 2, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c924969e-b243-44f2-97a7-c1a0b76511d1', 'G', 3, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('8d70434f-19c6-4d19-8ea5-f81a7c83315b', 'G', 4, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('741a8f31-a37e-4fee-9606-7595dd29bfef', 'G', 5, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('7eda1594-5374-4dc1-9a5a-fc6357315402', 'I', 1, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('5a9edd13-cf99-49b9-abea-c826e7267d0c', 'I', 2, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('69d68f4e-8463-4594-bf3c-b3dfc4feefd1', 'I', 3, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('5f282d0d-d565-4075-af84-405bbf1a9103', 'I', 4, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('59e193ed-46b3-47b9-afa0-860956ae4a76', 'I', 5, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('d160e318-233f-4e56-9b5e-d3058186b772', 'I', 6, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('a0431004-30cf-40c7-8da8-af447c1b3e1b', 'J', 1, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('e0759067-909c-45c9-b0aa-9828524488c5', 'J', 2, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('aab68549-d648-4972-ac26-2804a5e40414', 'J', 3, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('f0b58c8a-31c4-4f1e-8521-7d697fc32bef', 'J', 4, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('34252b48-f03d-4908-a057-79faefa227e7', 'J', 5, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('75ce5bfc-46f9-4500-981f-c36a32fda430', 'J', 6, 'couple', 'eee1d55a-4fd7-45f8-98f3-fe316bea0b07');
INSERT INTO public.api_seat VALUES ('b9db6e5a-09cb-4781-84cd-7fe19e86acbc', 'C', 12, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('22cc556d-88e6-4ba7-8184-796496221927', 'D', 1, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('3068665b-eef2-410a-a31d-8a0380113559', 'D', 2, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('ea03957c-910f-4c97-b626-68b9c5c4964f', 'D', 3, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('daf0d703-be09-49d8-8791-a8892acc366b', 'D', 4, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('474a7ec3-cfe9-4f39-acac-ec9666b37eb9', 'D', 5, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e1fff6a7-6eb7-4c3c-b70e-cff4e60ea57f', 'D', 6, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('0abf4aa0-cf47-452c-9535-b9b80b6710c4', 'D', 7, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('ff6e0df7-b2fd-40f6-869d-15227e804030', 'D', 8, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('22abfac6-9b7a-40a5-9835-9332774a944f', 'D', 9, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d4a9e8b9-d81d-4a29-a50c-29d120358ff9', 'D', 10, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7eadd042-5f55-4f20-8519-e84d0471f69f', 'D', 11, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('2b74796c-2775-44ba-90d9-1731187d7cb8', 'D', 12, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('657cca1e-3ff9-49a1-aa1b-d633f4fb1bc9', 'E', 1, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('02f4d052-4a0e-45cc-83ad-d1c786820de0', 'E', 2, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('fbab84be-f1a6-4b40-8b03-e3eebe5dee4a', 'E', 3, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('bbbd9a63-594e-4610-bf27-939927f15781', 'E', 4, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e01dc4af-4bd4-4e80-a905-4320d310e32c', 'E', 5, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('182fa2b8-9fd7-4e73-944e-6d82ae7a955d', 'E', 6, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('ccdf977d-93d3-4bfe-b553-dbe8d036b9fc', 'E', 7, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('b970f088-4625-40e2-9a5f-a39d8a21594d', 'E', 8, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('fd2a998b-9312-4f04-915d-be58e147db23', 'E', 9, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('92d7ee69-302c-465e-b08a-461151c9682e', 'E', 10, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('b7ae360a-75ce-485c-970e-2f4f2a36c0bd', 'E', 11, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d42be618-259e-42d7-af7e-8b8d7fbef831', 'E', 12, 'standard', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('0ef41953-da93-4543-aba8-d90c31c8f302', 'F', 1, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('8bae2aca-84cb-4e1a-abf0-0b5d689e9e4b', 'F', 2, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('c5373290-7d89-4346-bb6f-5696d82e389f', 'F', 3, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('1035870c-4d30-44bc-a1e6-d4575ebb26d3', 'F', 4, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('a3fb9ba1-efc0-4d82-b7a9-ba662cda2181', 'F', 5, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('84e01c14-5642-40b4-a33f-fdff23358d39', 'F', 6, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('94a9f2f9-0b04-45f7-bf37-436f5d1d2f3f', 'F', 7, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('df069ca4-c2e1-4328-b3ba-080729b48eef', 'F', 8, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('506768f9-c4ab-41c2-96f2-e231be6c40c9', 'F', 9, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('152ada8d-8864-4872-82e8-6b1076a10c4b', 'F', 10, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7e50e0c7-9aef-47ad-8696-f73f16d38a0b', 'F', 11, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f69194f7-8471-4fa5-b2ac-68ceceab6499', 'F', 12, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('9b8162f3-1447-49b2-bb10-fd6816ae66ea', 'G', 1, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('bb1fe99b-c33a-4791-931b-fea30937f7ac', 'G', 2, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7ed36b35-c8d7-4584-b323-bdae1bf738ff', 'G', 3, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('975b0669-7985-434f-bc8c-1c560b3b27f9', 'G', 4, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('7f408c28-4960-4003-a491-81ab30599382', 'G', 5, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('57c0a7f7-0d9a-4b4c-8cae-b72087e38800', 'G', 6, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('2040640d-211d-43b2-9914-d013b924b49b', 'G', 7, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('fdadddad-deb4-449d-8b69-9d49c80edf44', 'G', 8, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('990caaef-6451-4548-9be4-ecab609e3510', 'G', 9, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d294f7ff-0d1f-4ec7-8a3f-9c4bcab62bab', 'G', 10, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('4faeabf9-1281-4d61-9d31-1177415e7824', 'G', 11, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('296e3472-7d2f-449d-9d1d-0569440fb8b2', 'G', 12, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('1554e7f0-4690-460a-a730-ad6c2db29a8c', 'H', 1, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('ff2bd1bd-9379-4687-9105-812b5f2547c5', 'H', 2, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('c63e5cc5-db07-4951-a695-cd671daf10bd', 'H', 3, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('4dfdfa8c-3d23-4288-8eb9-b023ccbc352e', 'H', 4, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f56cab3e-479b-47d8-aee3-3607f291167e', 'H', 5, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f93d216b-9da7-4e52-b2fd-bf0c7e403147', 'H', 6, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('460fced6-947a-4846-a7c4-f7fe984c7d0e', 'H', 7, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('32fb5516-2a32-476f-91f9-78cfa749d336', 'H', 8, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('4279fa02-92a4-467a-889d-87471e3cf1b0', 'H', 9, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('a8aae687-dc1c-4534-a839-206a28190abb', 'H', 10, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e4b26163-6ad8-4907-9ab9-473c9d638ceb', 'H', 11, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d64acd53-678a-4afa-8711-a5fd6633df8b', 'H', 12, 'vip', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('c50281f5-f245-44e3-80e7-19016961ed7b', 'I', 1, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d6488d2c-b62c-49df-949d-bbbbdc81a454', 'I', 2, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('9f92a7a3-524f-424a-8b7a-a5af786f3219', 'I', 3, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('5446cb27-0f8f-43d9-a668-12d1557bf624', 'I', 4, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e181d3c4-e54a-4188-bc52-349a25d1b681', 'I', 5, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('27651a4f-563e-4fde-a795-8a7b903095ee', 'I', 6, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('32b4d005-2d4c-4b87-8c60-ef38a7d287ac', 'J', 1, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('e28d9b83-a569-4524-9ef2-a8132cf8d3d4', 'J', 2, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('2b055986-c253-42b8-ac39-85e9cceee05d', 'J', 3, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('8a77729e-534b-44dc-964b-496bee5b8ac9', 'J', 4, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('d4157489-4d3d-4293-a360-1e4ab0d5481f', 'J', 5, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('f9dc056a-f7d6-4169-85ea-d2146bdd1dbc', 'J', 6, 'couple', '4f3098da-57b2-4100-bb89-4a9a792a5b6e');
INSERT INTO public.api_seat VALUES ('1a1d28ed-42f3-4495-9faf-fff4da98bc01', 'A', 1, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('73384fc0-503b-4bc1-a486-b0cc03454349', 'A', 2, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('cc446531-f407-40c4-8028-e4c4478a4ab1', 'A', 3, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('b6f555d0-218f-4a2f-ae74-bcd30c2082c2', 'A', 4, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('b1267be8-a077-4992-98dc-0981fe0d73ab', 'A', 5, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('a870d7d2-1cd1-4935-b734-a12848872ddf', 'A', 6, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('df4c37c5-132b-4c5d-bc88-39dcec732cec', 'A', 7, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('8d069dd0-11c8-4c5e-b56d-6e21d904920c', 'A', 8, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('6c62a6fa-5451-4563-abe2-1c2c4e49f332', 'A', 9, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0709c71e-f881-4131-a5c0-a0c0813ab16f', 'A', 10, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('eca0f2f7-0e83-4434-a770-8ee447e7b27e', 'A', 11, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('c568dc8b-a00f-469e-92a3-47dffe918f8e', 'A', 12, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('9df01c3b-b058-44c2-ad1a-0c111065be78', 'B', 1, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('8a83ea6c-0d44-4522-8c51-4e6d15d9a4a3', 'B', 2, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('71573055-05fd-410a-9938-5df88ec29789', 'B', 3, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('9e2eac76-bc03-4ade-a4df-60f000a02914', 'B', 4, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0ed1e1a0-8050-432c-8b4b-b38e9d5dfe3b', 'B', 5, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('76dcd7c4-5a09-411a-869f-a655e58eeab4', 'B', 6, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('6c51655d-26ea-4021-8d67-a8b739a11baa', 'B', 7, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('84449c3b-4cb5-4524-a28a-59d1806b79eb', 'B', 8, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('04946c6f-a80b-4d0b-90dc-fb686f58b153', 'B', 9, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('f1d88310-d299-4257-969b-387a72326d6d', 'B', 10, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('3d46ad68-fb33-4bc4-a0a2-3f5d577652b3', 'B', 11, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1a6fea31-ab9c-44d9-aa9a-293bcb5fbb5f', 'B', 12, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('e265e8a4-8dc6-4e05-8136-d8fad66c4c45', 'C', 1, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('2bc5f6d8-041e-4f91-b6fb-d0fbdca91f1b', 'C', 2, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('e87c4920-c2e4-49d5-90d2-853625c00ea9', 'C', 3, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('029b4efa-5ec9-4637-bc06-dc12f079d067', 'C', 4, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('918fb2ab-db36-4973-b38c-b722079916bc', 'C', 5, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1e0c2b44-8e90-4929-a697-bc546e025056', 'C', 6, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('ca23c1d4-3a8a-44d8-806b-df3e1454eeca', 'C', 7, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('6debf177-d02e-433d-abda-b3e57d4c0f5a', 'C', 8, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1b0a252d-b1ab-41e9-bfe8-eae76a345093', 'C', 9, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0260e309-e8c3-418c-84a2-063b5dd1446b', 'C', 10, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('af6566b4-73a2-489b-bcbe-3d2e8c758f29', 'C', 11, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1837478f-0ed9-412f-a89b-1f86d0811b71', 'C', 12, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('fb39199c-4743-4499-a31a-f1c114ce1b15', 'D', 1, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('ebab93b1-fa37-44a5-9660-86ccc2056370', 'D', 2, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('bccd136d-06cb-4058-8063-140e3da7f71f', 'D', 3, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1c06961e-48b2-4616-bb38-7a12abb41573', 'D', 4, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('88e167e8-0a76-4524-a1bf-89725b4c09be', 'D', 5, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('02be4d13-a04c-4df4-ba87-30c7b7e20d50', 'D', 6, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('9e3e2bd3-04dc-4ee1-bceb-fbaf6c2c11f5', 'D', 7, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('9efda884-7c22-4ee8-b3ab-76421f9f6ba6', 'D', 8, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('be09f726-aac5-4634-9b5c-9217432a4ad9', 'D', 9, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('6e64e2d1-0aae-4b81-bd57-762412b17769', 'D', 10, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('2b7ed56c-3768-40b1-9cce-97b13faa8b51', 'D', 11, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('732c0d93-378b-4062-90a8-4df7f6a560d9', 'D', 12, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('fa3882c8-13f4-4fff-a95d-bcc2e212d70b', 'E', 1, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('c6c46f3b-2017-430e-b81c-9302a6b22213', 'E', 2, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('8396aede-2302-4742-823d-7e63dbe9fb3d', 'E', 3, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('16c57f52-d816-425c-bb2d-5be6f3479e7b', 'E', 4, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('f3020f7c-bd6b-4c68-a462-9b92970363c0', 'E', 5, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('93a33736-9f7d-4597-9710-34a888a71ff2', 'E', 6, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('9706ba21-453a-4c3d-8f3d-ba25c8be15df', 'E', 7, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('bccfff44-3f99-47cd-be2c-c51dd0d5be92', 'E', 8, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('f102c597-430e-4779-ba44-0beff05a94b8', 'E', 9, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0d02313c-5826-4794-9400-59143627b52b', 'E', 10, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('bfcb32df-0dfe-4632-8a4b-3a18e6e3b8d8', 'E', 11, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('53ab3035-8b1f-4f60-b69f-01a03ea0d646', 'E', 12, 'standard', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('85eee8fc-1c7d-4832-8a91-9818042d4360', 'F', 1, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1df30c48-56ad-4951-978d-95dfccee1806', 'F', 2, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('d678d2b8-7052-4abd-afcd-062f408a33d4', 'F', 3, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('7cfac4a0-41ba-433d-8a92-ac07f965ff4a', 'F', 4, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('887c7bd5-5d23-4b33-94b9-7099edc890db', 'F', 5, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('76cc53f8-a79f-496d-b1fe-e4941fe408ef', 'F', 6, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('02ec87cc-358f-4689-b6f2-f604cb0f9c0c', 'F', 7, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0120cc59-2c5a-413f-bc5c-801d3c71c907', 'F', 12, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('848519ba-1865-407b-a381-a71f238ef260', 'G', 1, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('775ea287-0e0c-4081-980a-60604416d94b', 'G', 2, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('c2c5c674-d227-43a6-a323-f53a83e4a74a', 'G', 3, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('8d516217-4fd9-4957-89a6-09e24c8f0d0d', 'G', 4, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('ab334360-d41f-4308-97b8-aa30bd54d4d1', 'G', 5, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('ed5d32e5-7435-4f0c-9f54-018164d78a3c', 'G', 6, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('842ce91c-eb0d-497d-8e16-461898c818fe', 'G', 7, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('bd2fc49a-ecb4-4335-87e1-b7873807d4fd', 'G', 8, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('306dd912-de9a-4e51-bb60-58731a5aa808', 'G', 9, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('44fefe95-8ac5-46e0-84be-54261a7dd6df', 'G', 10, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('babde26e-25de-4fdc-b1ec-60b513d17f6f', 'G', 11, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('04f4bab5-53aa-4ff0-a87a-c3a062deeff3', 'G', 12, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('73fa5cb4-f6c8-488a-b949-0de55593ab4b', 'H', 1, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('e6d2bdf8-950e-4a75-ae83-b6d55f18c7eb', 'H', 2, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('dfbe92d1-7797-4e00-b009-4c184f52afae', 'H', 3, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('4b5eda65-ea4c-4787-8758-e8db6c342a8a', 'H', 4, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('fe070212-75d9-4d37-aeb4-569d08587173', 'H', 5, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('01673f8b-7fb4-4f33-b2b9-1247fa5a1bc5', 'H', 6, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('4c2cb8f4-7f36-4993-a8b2-a923839a7966', 'H', 7, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('aa9a129d-30f2-4c20-8d9d-c811d85667e2', 'H', 8, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0c9235df-966d-4507-ac5f-ea337c1a0607', 'H', 9, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('03c430c9-5608-4779-844f-e509a3f67974', 'H', 10, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('68c89608-1f7e-4455-8cf8-630ec05e6369', 'H', 11, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('ea6a6574-0746-4ae7-b83d-e04ab8379199', 'H', 12, 'vip', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('53e28562-6c85-4afe-9a32-95f375247180', 'I', 1, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('1e0ed0a4-7f37-4586-80c8-9214f6c79616', 'I', 2, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('35a636f2-5190-45ca-a6b3-1dd12710209b', 'I', 3, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('7ac6e47b-720f-4dd1-84e0-5ef2bc2618cc', 'I', 4, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('4d9ff6f8-f4ed-4ea1-b668-105078d007a6', 'I', 5, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('893ac152-e326-43ff-b2ef-2d528427cb68', 'I', 6, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('16b2df61-1a8d-40aa-9c9a-6b1da381de28', 'J', 1, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('5f658a9b-fe2c-4f93-944b-af075c5f08e4', 'J', 2, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('049b6d07-f8c4-4db1-9c06-e98b4a182f11', 'J', 3, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0b921099-0f85-4d12-9f75-b110b1ca9cdc', 'J', 4, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('cec38daa-b4a3-4bae-b3d8-7a36766edc44', 'J', 5, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('fae95fb2-fe98-4620-adbc-0d5cd30d011f', 'J', 6, 'couple', '90d82435-d468-45d6-9c9c-b3432bd65a1c');
INSERT INTO public.api_seat VALUES ('0074eb6e-7e48-4369-9f9f-636964bb025e', 'G', 6, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('17da6fb5-b44b-42ac-8205-b416d41bfd15', 'G', 7, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('aca56b5f-0a9e-4a53-a55d-6a165fadcf88', 'G', 8, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('92caf4f8-c07b-4c7c-a6fc-6245233dbfe1', 'G', 9, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c403c902-7dff-46f2-87db-955ca39fd4d2', 'G', 10, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('b141552b-7066-4458-8fef-39e39511db19', 'H', 1, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c37b19a5-7a8c-4aef-8c19-e23ccb97007d', 'H', 2, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('a4bc3d89-df32-4f93-b8d7-9cdcb7d2cb06', 'H', 3, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('b6bf13f6-19dd-4951-b04b-a50b5a627caf', 'H', 4, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('75c0b94b-b8f9-43f7-b224-5c05a3fdb34a', 'H', 5, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('827d7be5-1f88-49e2-a928-60ecb7c084a8', 'H', 6, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('28c1eed1-3d26-4c88-9031-932cb28f15be', 'H', 7, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('ff3aa7d1-54db-41e1-9231-44ea90222266', 'H', 8, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c0eb35c6-75ee-4a17-8e99-626ffae3b8ca', 'H', 9, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('db4f41d6-1a7f-47d7-912f-d421b76dc7d3', 'H', 10, 'vip', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('58248106-5eab-42b5-a0ad-aa988cee1ed2', 'I', 1, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('6b3274c5-5a25-4f2f-beeb-42a066ee217f', 'I', 2, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('c42d7d78-15ed-4260-a301-fa8bb43227c1', 'I', 3, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('aaa02e2e-199e-40c4-9c03-712f199681f5', 'I', 4, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('cae19f33-8425-4177-9122-81d64f78cb4e', 'I', 5, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('50465f1b-7595-4cf6-a2af-8119417d2d6d', 'J', 1, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('d1723830-c615-4c35-a47b-3bdb0999afa1', 'J', 2, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('1865818a-98d8-4101-b08c-17605941a770', 'J', 3, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('60682511-d85a-4f50-8855-94002c98a040', 'J', 4, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('159f85b7-c7c1-433e-858c-1f1d75ffe896', 'J', 5, 'couple', '387a5681-88d1-46e6-8e8b-01666d617581');
INSERT INTO public.api_seat VALUES ('4f4cf0ec-792b-47bd-b166-00a0def2b92b', 'A', 1, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d2e9a242-66cd-4c86-a81d-b4f7ab29a495', 'A', 2, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a93c5da4-707c-4ed5-90f5-a69f9e6cd91d', 'A', 3, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d46bbdbd-c274-4674-bba5-a20428eb919f', 'A', 4, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('f414a6ab-6713-495a-9671-96c8597067da', 'A', 5, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('8e9d8d4f-b1b9-4fdc-a3a5-67c9c0e530a9', 'A', 6, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3b3f22b4-e6d4-412c-bdb4-7864121e58f2', 'A', 7, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('40b0de9c-e948-4e87-8f62-01b8e03ab186', 'A', 8, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b9a6a751-ea47-462c-9818-1e64c3ce2f09', 'A', 9, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('453d5b74-c41d-484c-b5ad-6e853a76b558', 'A', 10, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('31ec5481-2987-4045-8222-2326bd90885b', 'A', 11, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('88309a9e-519a-46f7-a328-54e3501a8d38', 'A', 12, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d5e2aa87-d5f6-48ea-a813-a65f1e8a9105', 'B', 1, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('e9d0c6c0-9e85-439c-8f3c-28f894580bc5', 'B', 2, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('79863d63-32a0-418b-8bdd-a18a81e52632', 'B', 3, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('819fedd1-af79-40a8-9466-fe65c6699bea', 'B', 4, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('e7691d1d-99eb-438a-a517-89d5bf6f03a4', 'B', 5, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('18535192-e295-4c0f-9898-6a21cec9023a', 'B', 6, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('58a4c4ad-33e4-42b2-a247-ae750060ff23', 'B', 7, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('49e95cef-b662-4855-8468-6b2de01e2392', 'B', 8, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('175c3f71-02ab-4bc7-b44b-4d653070cc81', 'B', 9, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('0844cad8-29c1-418f-b930-f87a76f6b5d3', 'B', 10, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('49a740f5-2ea0-4ddc-ada9-f98e5967cf81', 'B', 11, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('2a804b43-f0b9-4b9a-8b3f-1b61c1e9bc15', 'B', 12, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('41635c7c-102d-40dd-b6b3-6cef6f74acaa', 'C', 1, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('77e1c109-904e-460e-aafd-df1b1032a6a4', 'C', 2, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('412dda6d-01c9-4c95-a5b0-21caa4359e8b', 'C', 3, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3894ac10-213a-49dd-ba22-244976b4f305', 'C', 4, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b1052545-4581-448d-b98e-5103710342a4', 'C', 5, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('8794470c-f57e-4b86-bef8-1fdd9ab31791', 'C', 6, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('5a16d5f5-97bc-4a68-ac0b-b18a792962ea', 'C', 7, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('cd10cd41-ea69-4cf1-8381-2e01920f82dd', 'C', 8, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('0b77c205-c2e4-4084-84f0-320991ebc593', 'C', 9, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('7ff4f734-7dd8-455c-8523-70049a6df918', 'C', 10, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3abb5b6c-e84c-43e4-a129-b0d1c432908f', 'C', 11, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a7e0c088-ba1e-4e71-b671-515424776719', 'C', 12, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('dbb3ad59-07f6-4c07-b44e-67edd1b4d168', 'D', 1, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('6f8d05f4-c881-4e5d-9278-b56fde474638', 'D', 2, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c72322bb-8447-429e-828a-4fa73b7c6a9c', 'D', 3, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('f24f18e1-16d4-40b3-81b0-c4923ba87923', 'D', 4, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d1e4c951-21cf-498d-8c7c-0c0649bf735f', 'D', 5, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('46feb948-ad88-4d10-8b7b-88301a293998', 'D', 6, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('0b775c88-2b79-4cb8-bc5c-230488020320', 'D', 7, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('41539508-a8ad-4ec1-800f-d18183adbedc', 'D', 8, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('422af2bd-b66f-4b4a-a6e9-0cf42a0fe7d5', 'D', 9, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('dd150a30-d49f-4d09-8b10-16d3cfee3e73', 'D', 10, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('e485c4ac-bfb2-45e1-b485-442964a7bae2', 'D', 11, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d9de55fc-5b51-4df8-a6b7-24447c254d66', 'D', 12, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('19386ce2-62cc-44bf-9f02-d1dc4566e554', 'E', 1, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('4e369089-873c-4d08-ac67-859a25d3d682', 'E', 2, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c9c9b95d-96cd-48b4-ad97-5ca5bd4932e1', 'E', 3, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('9d7b6f3d-00a6-4001-b04b-24f293bdfa05', 'E', 4, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('d0cc32b2-69d1-4805-b88a-bb7c1c8d5374', 'E', 5, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a75f65aa-8412-4f3f-af05-aafba1532d3e', 'E', 6, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('45c378bb-3e1c-4e15-a4fb-651993d0bce2', 'E', 7, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('96fdf9c1-1211-4241-822b-bcc15a494f9c', 'E', 8, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('8c3222c7-e483-453e-90ba-4b2bc789d346', 'E', 9, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('e9547cbd-d136-4bcd-93cf-7efa05145b98', 'E', 10, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('6ad750c9-82dc-4a80-be90-8e27850b77b6', 'E', 11, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('5ad6a87e-58cc-46c5-8e67-6346d98e3b1c', 'E', 12, 'standard', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('be6b3f0f-d66d-41c8-963f-e673825277a4', 'F', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b4828b3b-d987-4077-89a0-b41fa0f901d6', 'F', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('5d6e8eb6-b560-48cb-9938-7b0ace83f3dc', 'F', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('cee3e71d-6d61-4c07-b9d2-392a535d00fb', 'F', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('00d3216d-8a14-4b31-950a-fc6af179d117', 'F', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c6e5cad7-62d1-4179-913e-b3d46a676942', 'F', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('16d69f0c-c392-4c46-be27-79922dd5bfd7', 'F', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('faa370a2-8c5a-4a92-bbb4-9dc92c888328', 'F', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('f90d2786-bbb6-4262-a47e-1bd26c075943', 'F', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('652a9152-453a-4620-9509-b578b960a2ec', 'F', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('714385f4-f575-40a4-9e1f-59d196051030', 'F', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('1450fb80-f3b7-47e2-aa6a-8e2692f3a87c', 'F', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('7ce1ee66-534a-478c-b0a6-cfe085608ba7', 'G', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('9a3afed4-895e-4f1b-96ba-f59c43334d0a', 'G', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('1e6e2b50-4bf7-4e53-9ad3-6ac9dc92af7f', 'G', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('fa6b28bf-f5fa-48a0-ba23-4a6b033eaaef', 'G', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('63b58655-4096-4156-9e6e-c997f132e85e', 'G', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('97b59efd-957b-4e88-ab3d-5caf9dd5de83', 'G', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3624a334-562b-4985-a737-0da6e7ecc7e4', 'G', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('dcfcadd9-1d9b-4cc7-8705-35363f23b228', 'G', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('4c225de4-7ad5-48a8-a573-23dbfd2a7157', 'G', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('97b7c023-f90a-4456-bf3b-dbe8c71a7aa6', 'G', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('5376f388-82fa-42c1-b506-e9f22525b860', 'G', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('9f169b19-3153-4b89-af5a-4e19930b4cad', 'G', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c5e29a84-f730-407a-bd71-95b248d63e3c', 'H', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('7590d9e3-f2c6-40ce-9470-939b8d41adf2', 'H', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a258fdfa-6e16-4ad4-b106-6d82e45797f1', 'H', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('88104c55-b923-4ebc-8751-bae04ee4e80a', 'H', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3083f7f1-2ccb-40d3-9415-0cdf889ecb50', 'H', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('abca94c1-5110-4bae-9738-9723cb1bf706', 'H', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('336ba936-42a4-4aaa-9509-694c5dd07040', 'H', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('f15f9428-5654-4e3d-85eb-b9eca6e8def2', 'H', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('4a28dada-ffc4-4d14-8c37-4de8fdb4cee0', 'H', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('821cc18b-d97e-42af-8076-feb657c49ebc', 'H', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('29e4c928-fe8e-4000-b216-7944a290c14d', 'H', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('8038a1bf-522f-4b4e-9e3f-14a0e589548e', 'H', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('84ece504-6326-4815-89c9-6687d38a3a85', 'I', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('02d8795a-c36b-410c-b662-5f58cda50f79', 'I', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c33e9f83-e59c-409c-86f3-e29b44cf0de0', 'I', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('18fd1b94-8f73-42ef-bc28-b4928fa7d717', 'I', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('bc25905a-6bc5-45f4-9590-12957e670d83', 'I', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('928f4dc2-0b6a-45c5-a3fb-211f1b96b3db', 'I', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('6cddae45-daec-4b55-8a91-c43780fb99ed', 'I', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('4ba561ea-b5cf-4b5e-815d-49a475f9d6a3', 'I', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b19aaf32-41c6-4393-80d7-380d2898bdd9', 'I', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('1cec9ada-3fec-4e2f-8021-75c90faff203', 'I', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3b5c17ac-8ec5-46ca-ac75-ab8b90c35a4a', 'I', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('09d36e0a-5817-4d3c-8dec-85b09f37bed3', 'I', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('cbd96101-dcc4-4069-8663-6f892c95a7d7', 'J', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('c28f70d7-3b4f-4df3-b9d8-a1772ae17e48', 'J', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('5b020ec1-d01e-4fb0-adc8-239c7aae7fb8', 'J', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('49da2f99-7725-481d-a1df-f27dd6bc4af2', 'J', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('3ade783a-9000-48e9-9e83-62e6b6d72bc5', 'J', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('29216c38-d55d-44af-b21b-256dd179b8cf', 'J', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a2428c94-d20e-40ef-85d9-e8126f756290', 'J', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('6a661045-4b99-41eb-b3a1-4411250d52e6', 'J', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('32944d09-93c4-44a4-b466-8e07fa0ede97', 'J', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('12f220c5-b8e6-4b9b-9416-69e5fbd95826', 'J', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('9dcf52b0-47c8-4a5a-9999-af0fe8417b3f', 'J', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('cb79fc10-b641-49ce-8b48-6f4b3e73bbdf', 'J', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('6726ea8e-1963-4569-9a1f-51265a0b7aa3', 'K', 1, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('bb1a072b-8a7f-4b4a-b3b7-76fae715d62e', 'K', 2, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('af53fe60-2fe8-4806-96b8-3b5b72cdcbbb', 'K', 3, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('042164f0-6ad1-4b0f-804a-3c60349d7d48', 'K', 4, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('20687f24-611a-45f7-adb0-d6d3127b80da', 'K', 5, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('bde15eee-3f89-4e7f-bafd-b103043d79c7', 'K', 6, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('f6af4a0d-ad47-40da-96c6-67b6f3666e02', 'K', 7, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('89a452c1-a7ff-4187-8a0f-9e49aa5383e5', 'K', 8, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('2d971a6a-587f-443d-b3cd-f1f85e9c61c8', 'K', 9, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a51e0fbe-2917-4d82-98e3-2106e9ecde7e', 'K', 10, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('dc929c3d-b190-458a-8183-55c2ff48d973', 'K', 11, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b8e25148-83f5-4bca-b284-67e4ccfcb151', 'K', 12, 'vip', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('aac315ce-8f83-4ee8-afea-9e9c07127b06', 'L', 1, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('4e1885da-109c-4da4-80a4-49897280496e', 'L', 2, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('0aecc445-d1a5-4c9f-8bd7-b231c1ea9947', 'L', 3, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('a16cebff-c967-4420-8ae7-e347267a646c', 'L', 4, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('98047147-4adc-436d-b072-018363321233', 'L', 5, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('180ca064-4aa1-413e-b56c-b478ae091ca1', 'L', 6, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b0b91640-b6cf-43a0-a572-9422ca8c7b00', 'M', 1, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('b1d21de6-9248-4ab4-b34d-02e16f86b87d', 'M', 2, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('762c88c3-892c-4fe1-998f-f7d47895f0f8', 'M', 3, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('1152b2ac-376e-44df-8285-d201de1e20b0', 'M', 4, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('82e24b01-39e4-4ab2-8ddc-ab2ccf2ed46e', 'M', 5, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');
INSERT INTO public.api_seat VALUES ('697c9ab4-4a0b-441e-a3f8-54f069d23f02', 'M', 6, 'couple', '6df00e45-aaf2-4b94-96a8-1292fb5b22ed');


--
-- Data for Name: api_ticket; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.api_ticket VALUES ('7898c3f9-269f-406c-8d6f-812b44ad6c87', 100000.00, NULL, 'booked', '2025-11-02 01:25:57.920029+07', '1d85f46a-6e5f-4b54-9b2a-9176eaab8d69', '3b4d6f01-71e5-4b16-b664-90dfc726743e', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('d8d44de8-f618-4c02-97d8-10c5d391f525', 80000.00, NULL, 'booked', '2025-11-02 02:14:15.914305+07', 'a306effc-20e1-4b18-8e36-75e22922c3bd', 'fdadddad-deb4-449d-8b69-9d49c80edf44', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_ticket VALUES ('16960c60-b1da-4690-a606-b0d68f61d319', 80000.00, NULL, 'booked', '2025-11-02 02:14:15.91526+07', 'a306effc-20e1-4b18-8e36-75e22922c3bd', '990caaef-6451-4548-9be4-ecab609e3510', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_ticket VALUES ('f85b93fb-c077-4265-9cf1-695bf75eedd8', 80000.00, NULL, 'booked', '2025-11-02 03:25:17.195755+07', 'fdca7a73-2293-43d1-b705-dbc6917266d3', '01673f8b-7fb4-4f33-b2b9-1247fa5a1bc5', '9f85e987-e0ad-4238-8346-ef5e06e038fe');
INSERT INTO public.api_ticket VALUES ('82ef1518-d9eb-4f6f-a159-95cc075f54d1', 50000.00, NULL, 'booked', '2025-11-02 03:33:25.687364+07', 'c7321ca5-6bee-4898-b895-d08574a1229f', '92d7ee69-302c-465e-b08a-461151c9682e', 'dd1852f1-3c87-49d2-a7da-345f6af8eec6');
INSERT INTO public.api_ticket VALUES ('1d8fb196-3136-4390-ad57-f4e085ae2ed3', 80000.00, NULL, 'booked', '2025-11-02 03:37:37.412996+07', '3d960848-3359-4afc-9188-af0f539df473', '84e01c14-5642-40b4-a33f-fdff23358d39', '68d19eff-6b73-4ad0-ab1a-da0503e623b9');
INSERT INTO public.api_ticket VALUES ('ca3c481c-f6bb-4510-8dec-da345a287e1a', 50000.00, NULL, 'booked', '2025-11-02 03:43:03.851998+07', '6f9c5f6f-1245-40b0-91ae-c40591188a3a', '270eed77-8f07-4f41-912b-417dba946b10', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('c6ab31d6-ded2-4f34-a913-5f22de65b29c', 80000.00, NULL, 'booked', '2025-11-02 04:01:12.581721+07', '723eacd5-dd34-4a83-8776-6fb1b955c89d', 'd5fc851b-e408-426a-9f50-e7b1779e43bd', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('170e706e-c1d4-43fa-bb7c-bf41eec3a8fa', 50000.00, NULL, 'booked', '2025-11-02 04:01:59.758352+07', '139de424-37eb-425b-96d7-a95c2f610bb3', 'd9df3f35-b90d-494d-a8c8-4449cbcd95e5', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('abe8d002-a594-45be-bea1-3d5497470f59', 80000.00, NULL, 'booked', '2025-11-02 04:03:51.708109+07', '4bc09a2c-47b2-4585-9507-7cbe5c64a24c', '59291a9a-6f2f-441c-8f6f-c327225399c6', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('5edf01c4-2925-4656-8355-29ca60ef2595', 80000.00, NULL, 'booked', '2025-11-02 04:03:51.708641+07', '4bc09a2c-47b2-4585-9507-7cbe5c64a24c', '05d18288-933c-4750-ada1-735d386bacb4', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('ed006e80-dc12-4b86-aab8-bd8ba7be1a55', 80000.00, NULL, 'booked', '2025-11-02 04:04:10.199095+07', '137c6fac-55b6-41f3-ba82-393e1b68ad8d', '91f8dc8d-90c5-4a53-ad4e-df75d92701f4', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('c4dd0c48-45cd-492c-be56-f481459fcb4a', 80000.00, NULL, 'booked', '2025-11-02 04:04:10.200043+07', '137c6fac-55b6-41f3-ba82-393e1b68ad8d', '47547ab5-c8cc-40c0-8146-a5165835c606', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('8ae8aa7a-2b6a-49e5-b8c4-351091458b0b', 50000.00, NULL, 'booked', '2025-11-02 04:05:52.438104+07', 'a79b4d45-5c1e-419f-9083-f86ae0c72028', '65cb3748-32f4-433e-b738-72e10b2d9018', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('399869ff-75d0-4344-9af3-21e9657c8103', 50000.00, NULL, 'booked', '2025-11-03 21:43:07.016479+07', '01de54ea-7612-4d24-91a2-edac2ff837b4', 'f63183a1-bd7e-42a7-bcec-3a26a0b91685', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('49e17b88-1dcd-45e5-8ec1-26e5c527647a', 50000.00, NULL, 'booked', '2025-11-03 21:53:58.986675+07', 'f209a690-caed-45e6-9d4b-fd8d72865761', '7ba0c38b-3453-4f96-89dc-63bd4e8beaec', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('d316e885-99aa-4cbd-a23f-eccb59cfafd7', 80000.00, NULL, 'booked', '2025-11-03 22:05:20.641914+07', '38983d93-f955-441d-a71e-49e6a5d87f44', '990caaef-6451-4548-9be4-ecab609e3510', '408cf192-34ac-4380-af63-565ebca1fbdc');
INSERT INTO public.api_ticket VALUES ('d62de7fa-a3e7-45c4-8a89-b15df1281286', 50000.00, NULL, 'booked', '2025-11-03 23:09:54.212005+07', 'a8cc555c-cbde-4028-a601-1b87c9d9f31f', 'f726009a-73a2-443f-87d7-2b0d8950ac85', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('8c640811-456f-46ef-bbb9-55715debb519', 80000.00, NULL, 'booked', '2025-11-04 14:38:59.906837+07', '19c5dc0e-9aa4-44d5-8dc3-1448a8b8198a', '2d0b2948-900d-4938-9c44-dc255d9872fb', 'dfcad975-2ce9-4506-a891-3b4c213048f8');
INSERT INTO public.api_ticket VALUES ('94690af8-dfbd-4fad-9d0f-bf0d85512997', 80000.00, NULL, 'booked', '2025-11-04 14:38:59.907605+07', '19c5dc0e-9aa4-44d5-8dc3-1448a8b8198a', '7dc9fb21-7c29-436a-8090-1b247191b079', 'dfcad975-2ce9-4506-a891-3b4c213048f8');
INSERT INTO public.api_ticket VALUES ('5870c73e-d168-4f72-b511-2e89c6f55837', 80000.00, NULL, 'booked', '2025-11-04 14:42:05.88054+07', 'e9ed9796-a2f6-46f0-af58-de74ce451217', 'f93d216b-9da7-4e52-b2fd-bf0c7e403147', '6f160a1a-3f89-424b-9602-1471a9348969');
INSERT INTO public.api_ticket VALUES ('75cd5db3-b266-4d5b-bf1d-dd170a5e6804', 80000.00, NULL, 'booked', '2025-11-04 14:42:05.881114+07', 'e9ed9796-a2f6-46f0-af58-de74ce451217', 'f56cab3e-479b-47d8-aee3-3607f291167e', '6f160a1a-3f89-424b-9602-1471a9348969');
INSERT INTO public.api_ticket VALUES ('b93c1036-e567-4528-99a9-1644c29039b0', 80000.00, NULL, 'booked', '2025-11-04 14:42:22.949728+07', 'eed50131-ccf1-4781-ba6d-6ad1c2ae1ca9', '01673f8b-7fb4-4f33-b2b9-1247fa5a1bc5', '7e8c8a25-247c-4add-a3c4-897ea5d39d3b');
INSERT INTO public.api_ticket VALUES ('4be71005-15f2-421e-9a0c-d4e1223d4296', 80000.00, NULL, 'booked', '2025-11-04 14:42:22.950302+07', 'eed50131-ccf1-4781-ba6d-6ad1c2ae1ca9', '4c2cb8f4-7f36-4993-a8b2-a923839a7966', '7e8c8a25-247c-4add-a3c4-897ea5d39d3b');
INSERT INTO public.api_ticket VALUES ('42a54432-65bb-48b1-a4e5-13c7a8968399', 150000.00, NULL, 'booked', '2025-11-04 14:56:07.237523+07', '7c32b859-0626-4f17-80dc-572ec8a2d01b', '6b3274c5-5a25-4f2f-beeb-42a066ee217f', 'e7464fa8-1c52-4b16-be93-2535fd68f3ff');
INSERT INTO public.api_ticket VALUES ('b61426cd-cad2-495a-ba5a-83d5ff6a2f03', 150000.00, NULL, 'booked', '2025-11-04 14:56:07.238694+07', '7c32b859-0626-4f17-80dc-572ec8a2d01b', 'c42d7d78-15ed-4260-a301-fa8bb43227c1', 'e7464fa8-1c52-4b16-be93-2535fd68f3ff');
INSERT INTO public.api_ticket VALUES ('354aae86-e9e5-4f9e-bf65-549330756954', 80000.00, NULL, 'booked', '2025-11-05 00:15:42.708785+07', 'bf130f1b-caab-452b-ab39-4993ae24f39d', '42b4f0cf-b124-44dc-8bae-9d1dbdfcd6dd', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('cee8a60e-f4b5-4af1-85da-16893027f1bb', 80000.00, NULL, 'booked', '2025-11-05 00:15:42.711126+07', 'bf130f1b-caab-452b-ab39-4993ae24f39d', '9269d79b-2e6f-4bf2-b066-64773b715e6b', '76b81492-f11b-449d-9a91-9cc56e068fca');
INSERT INTO public.api_ticket VALUES ('59a9fb89-d625-4481-bb86-71918e46abe5', 80000.00, NULL, 'booked', '2025-11-05 00:25:52.202015+07', '0afddc4d-fb4b-444a-bff5-2368661d8db9', '57c0a7f7-0d9a-4b4c-8cae-b72087e38800', '408cf192-34ac-4380-af63-565ebca1fbdc');
INSERT INTO public.api_ticket VALUES ('fa311a0d-ae09-4b70-9d27-30c7de39c716', 80000.00, NULL, 'booked', '2025-11-05 00:25:52.202953+07', '0afddc4d-fb4b-444a-bff5-2368661d8db9', '7f408c28-4960-4003-a491-81ab30599382', '408cf192-34ac-4380-af63-565ebca1fbdc');


--
-- Data for Name: auth_group; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: api_user_groups; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: django_content_type; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.django_content_type VALUES (1, 'admin', 'logentry');
INSERT INTO public.django_content_type VALUES (2, 'auth', 'permission');
INSERT INTO public.django_content_type VALUES (3, 'auth', 'group');
INSERT INTO public.django_content_type VALUES (4, 'contenttypes', 'contenttype');
INSERT INTO public.django_content_type VALUES (5, 'sessions', 'session');
INSERT INTO public.django_content_type VALUES (6, 'api', 'auditorium');
INSERT INTO public.django_content_type VALUES (7, 'api', 'genre');
INSERT INTO public.django_content_type VALUES (8, 'api', 'movie');
INSERT INTO public.django_content_type VALUES (9, 'api', 'user');
INSERT INTO public.django_content_type VALUES (10, 'api', 'seat');
INSERT INTO public.django_content_type VALUES (11, 'api', 'showtime');
INSERT INTO public.django_content_type VALUES (12, 'api', 'booking');
INSERT INTO public.django_content_type VALUES (13, 'api', 'ticket');
INSERT INTO public.django_content_type VALUES (14, 'api', 'moviegenre');
INSERT INTO public.django_content_type VALUES (15, 'api', 'payment');


--
-- Data for Name: auth_permission; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.auth_permission VALUES (1, 'Can add log entry', 1, 'add_logentry');
INSERT INTO public.auth_permission VALUES (2, 'Can change log entry', 1, 'change_logentry');
INSERT INTO public.auth_permission VALUES (3, 'Can delete log entry', 1, 'delete_logentry');
INSERT INTO public.auth_permission VALUES (4, 'Can view log entry', 1, 'view_logentry');
INSERT INTO public.auth_permission VALUES (5, 'Can add permission', 2, 'add_permission');
INSERT INTO public.auth_permission VALUES (6, 'Can change permission', 2, 'change_permission');
INSERT INTO public.auth_permission VALUES (7, 'Can delete permission', 2, 'delete_permission');
INSERT INTO public.auth_permission VALUES (8, 'Can view permission', 2, 'view_permission');
INSERT INTO public.auth_permission VALUES (9, 'Can add group', 3, 'add_group');
INSERT INTO public.auth_permission VALUES (10, 'Can change group', 3, 'change_group');
INSERT INTO public.auth_permission VALUES (11, 'Can delete group', 3, 'delete_group');
INSERT INTO public.auth_permission VALUES (12, 'Can view group', 3, 'view_group');
INSERT INTO public.auth_permission VALUES (13, 'Can add content type', 4, 'add_contenttype');
INSERT INTO public.auth_permission VALUES (14, 'Can change content type', 4, 'change_contenttype');
INSERT INTO public.auth_permission VALUES (15, 'Can delete content type', 4, 'delete_contenttype');
INSERT INTO public.auth_permission VALUES (16, 'Can view content type', 4, 'view_contenttype');
INSERT INTO public.auth_permission VALUES (17, 'Can add session', 5, 'add_session');
INSERT INTO public.auth_permission VALUES (18, 'Can change session', 5, 'change_session');
INSERT INTO public.auth_permission VALUES (19, 'Can delete session', 5, 'delete_session');
INSERT INTO public.auth_permission VALUES (20, 'Can view session', 5, 'view_session');
INSERT INTO public.auth_permission VALUES (21, 'Can add auditorium', 6, 'add_auditorium');
INSERT INTO public.auth_permission VALUES (22, 'Can change auditorium', 6, 'change_auditorium');
INSERT INTO public.auth_permission VALUES (23, 'Can delete auditorium', 6, 'delete_auditorium');
INSERT INTO public.auth_permission VALUES (24, 'Can view auditorium', 6, 'view_auditorium');
INSERT INTO public.auth_permission VALUES (25, 'Can add genre', 7, 'add_genre');
INSERT INTO public.auth_permission VALUES (26, 'Can change genre', 7, 'change_genre');
INSERT INTO public.auth_permission VALUES (27, 'Can delete genre', 7, 'delete_genre');
INSERT INTO public.auth_permission VALUES (28, 'Can view genre', 7, 'view_genre');
INSERT INTO public.auth_permission VALUES (29, 'Can add movie', 8, 'add_movie');
INSERT INTO public.auth_permission VALUES (30, 'Can change movie', 8, 'change_movie');
INSERT INTO public.auth_permission VALUES (31, 'Can delete movie', 8, 'delete_movie');
INSERT INTO public.auth_permission VALUES (32, 'Can view movie', 8, 'view_movie');
INSERT INTO public.auth_permission VALUES (33, 'Can add User', 9, 'add_user');
INSERT INTO public.auth_permission VALUES (34, 'Can change User', 9, 'change_user');
INSERT INTO public.auth_permission VALUES (35, 'Can delete User', 9, 'delete_user');
INSERT INTO public.auth_permission VALUES (36, 'Can view User', 9, 'view_user');
INSERT INTO public.auth_permission VALUES (37, 'Can add seat', 10, 'add_seat');
INSERT INTO public.auth_permission VALUES (38, 'Can change seat', 10, 'change_seat');
INSERT INTO public.auth_permission VALUES (39, 'Can delete seat', 10, 'delete_seat');
INSERT INTO public.auth_permission VALUES (40, 'Can view seat', 10, 'view_seat');
INSERT INTO public.auth_permission VALUES (41, 'Can add showtime', 11, 'add_showtime');
INSERT INTO public.auth_permission VALUES (42, 'Can change showtime', 11, 'change_showtime');
INSERT INTO public.auth_permission VALUES (43, 'Can delete showtime', 11, 'delete_showtime');
INSERT INTO public.auth_permission VALUES (44, 'Can view showtime', 11, 'view_showtime');
INSERT INTO public.auth_permission VALUES (45, 'Can add booking', 12, 'add_booking');
INSERT INTO public.auth_permission VALUES (46, 'Can change booking', 12, 'change_booking');
INSERT INTO public.auth_permission VALUES (47, 'Can delete booking', 12, 'delete_booking');
INSERT INTO public.auth_permission VALUES (48, 'Can view booking', 12, 'view_booking');
INSERT INTO public.auth_permission VALUES (49, 'Can add ticket', 13, 'add_ticket');
INSERT INTO public.auth_permission VALUES (50, 'Can change ticket', 13, 'change_ticket');
INSERT INTO public.auth_permission VALUES (51, 'Can delete ticket', 13, 'delete_ticket');
INSERT INTO public.auth_permission VALUES (52, 'Can view ticket', 13, 'view_ticket');
INSERT INTO public.auth_permission VALUES (53, 'Can add movie genre', 14, 'add_moviegenre');
INSERT INTO public.auth_permission VALUES (54, 'Can change movie genre', 14, 'change_moviegenre');
INSERT INTO public.auth_permission VALUES (55, 'Can delete movie genre', 14, 'delete_moviegenre');
INSERT INTO public.auth_permission VALUES (56, 'Can view movie genre', 14, 'view_moviegenre');
INSERT INTO public.auth_permission VALUES (57, 'Can add payment', 15, 'add_payment');
INSERT INTO public.auth_permission VALUES (58, 'Can change payment', 15, 'change_payment');
INSERT INTO public.auth_permission VALUES (59, 'Can delete payment', 15, 'delete_payment');
INSERT INTO public.auth_permission VALUES (60, 'Can view payment', 15, 'view_payment');


--
-- Data for Name: api_user_user_permissions; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: auth_group_permissions; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: django_admin_log; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Data for Name: django_migrations; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--

INSERT INTO public.django_migrations VALUES (1, 'contenttypes', '0001_initial', '2025-10-16 08:42:49.702322+07');
INSERT INTO public.django_migrations VALUES (2, 'contenttypes', '0002_remove_content_type_name', '2025-10-16 08:42:49.708639+07');
INSERT INTO public.django_migrations VALUES (3, 'auth', '0001_initial', '2025-10-16 08:42:49.732256+07');
INSERT INTO public.django_migrations VALUES (4, 'auth', '0002_alter_permission_name_max_length', '2025-10-16 08:42:49.734384+07');
INSERT INTO public.django_migrations VALUES (5, 'auth', '0003_alter_user_email_max_length', '2025-10-16 08:42:49.735886+07');
INSERT INTO public.django_migrations VALUES (6, 'auth', '0004_alter_user_username_opts', '2025-10-16 08:42:49.737379+07');
INSERT INTO public.django_migrations VALUES (7, 'auth', '0005_alter_user_last_login_null', '2025-10-16 08:42:49.738848+07');
INSERT INTO public.django_migrations VALUES (8, 'auth', '0006_require_contenttypes_0002', '2025-10-16 08:42:49.739425+07');
INSERT INTO public.django_migrations VALUES (9, 'auth', '0007_alter_validators_add_error_messages', '2025-10-16 08:42:49.741242+07');
INSERT INTO public.django_migrations VALUES (10, 'auth', '0008_alter_user_username_max_length', '2025-10-16 08:42:49.742618+07');
INSERT INTO public.django_migrations VALUES (11, 'auth', '0009_alter_user_last_name_max_length', '2025-10-16 08:42:49.743972+07');
INSERT INTO public.django_migrations VALUES (12, 'auth', '0010_alter_group_name_max_length', '2025-10-16 08:42:49.746805+07');
INSERT INTO public.django_migrations VALUES (13, 'auth', '0011_update_proxy_permissions', '2025-10-16 08:42:49.748159+07');
INSERT INTO public.django_migrations VALUES (14, 'auth', '0012_alter_user_first_name_max_length', '2025-10-16 08:42:49.749563+07');
INSERT INTO public.django_migrations VALUES (15, 'api', '0001_initial', '2025-10-16 08:42:49.849101+07');
INSERT INTO public.django_migrations VALUES (16, 'admin', '0001_initial', '2025-10-16 08:42:49.858967+07');
INSERT INTO public.django_migrations VALUES (17, 'admin', '0002_logentry_remove_auto_add', '2025-10-16 08:42:49.861589+07');
INSERT INTO public.django_migrations VALUES (18, 'admin', '0003_logentry_add_action_flag_choices', '2025-10-16 08:42:49.864426+07');
INSERT INTO public.django_migrations VALUES (19, 'sessions', '0001_initial', '2025-10-16 08:42:49.86833+07');


--
-- Data for Name: django_session; Type: TABLE DATA; Schema: public; Owner: trantuanduong
--



--
-- Name: api_moviegenre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.api_moviegenre_id_seq', 78, true);


--
-- Name: api_user_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.api_user_groups_id_seq', 1, false);


--
-- Name: api_user_user_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.api_user_user_permissions_id_seq', 1, false);


--
-- Name: auth_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.auth_group_id_seq', 1, false);


--
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.auth_group_permissions_id_seq', 1, false);


--
-- Name: auth_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.auth_permission_id_seq', 60, true);


--
-- Name: django_admin_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.django_admin_log_id_seq', 1, false);


--
-- Name: django_content_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.django_content_type_id_seq', 15, true);


--
-- Name: django_migrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: trantuanduong
--

SELECT pg_catalog.setval('public.django_migrations_id_seq', 19, true);


--
-- PostgreSQL database dump complete
--

\unrestrict ftRBeQXa7uZuwcUFMxbeTsA81nJCEZ7eYK3osZWK4pEVkR8z3Vgm0iF5hicvErI

