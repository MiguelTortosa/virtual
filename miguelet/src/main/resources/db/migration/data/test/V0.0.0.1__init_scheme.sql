INSERT INTO public.brand
(ID, NAME) VALUES( 1, 'MARCA 1');

INSERT INTO public.brand
(ID, NAME) VALUES( 2, 'MARCA 2');
INSERT INTO public.product
(ID, NAME) VALUES( 1, 'PRODUCT 1');

INSERT INTO public.product
(ID, NAME) VALUES( 2, 'PRODUCT 2');
INSERT INTO public.currency
(CODE, SYMBOL,DECIMALS) VALUES( 'EUR', '€','2');
INSERT INTO public.currency
(CODE, SYMBOL,DECIMALS) VALUES( 'USD', '$','3');

INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 1, 1, '2022-01-01', '2022-05-31', 1550, 'EUR');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 1, 1, '2022-06-01', '2022-12-31', 1850, 'USD');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 2, 1, '2022-01-01', '2022-05-31', 2050, 'EUR');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 2, 1, '2022-06-01', '2022-12-31', 2250, 'USD');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 1, 2, '2022-01-01', '2022-05-31', 2550, 'EUR');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 1, 2, '2022-06-01', '2022-12-31', 2850, 'USD');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 2, 2, '2022-01-01', '2022-05-31', 3050, 'EUR');
INSERT INTO public.t_rates
(brand_id, product_id, start_date, end_date, price, currency_code)
VALUES( 2, 2, '2022-06-01', '2022-12-31', 3250, 'USD');