WITH price_rank AS (
    SELECT
        *
    FROM (
        SELECT
            *,
            ROW_NUMBER() OVER (PARTITION BY store_id ORDER BY PRICE ASC) AS cheap_rnk,
            ROW_NUMBER() OVER (PARTITION BY store_id ORDER BY PRICE DESC) AS expensive_rnk,
            COUNT(*) OVER (PARTITION BY store_id) AS cnt
        FROM inventory
    ) sub
    WHERE cnt >= 3 AND (cheap_rnk = 1 OR expensive_rnk = 1)
),
max_min AS (
    SELECT
        C.store_id,
        C.cheapest_product,
        E.most_exp_product,
        C.cheapest_quantity,
        E.most_expensive_quantity
    FROM (
    SELECT
        store_id,
        product_name AS cheapest_product,
        quantity AS cheapest_quantity 
    FROM price_rank
    WHERE cheap_rnk = 1) C
    JOIN (
    SELECT
        store_id,
        product_name AS most_exp_product,
        quantity AS most_expensive_quantity
    FROM price_rank
    WHERE expensive_rnk = 1) E
    ON C.store_id = E.store_id
    WHERE C.cheapest_quantity > E.most_expensive_quantity
)

SELECT
    M.store_id,
    S.store_name,
    S.location,
    M.most_exp_product,
    M.cheapest_product,
    ROUND((M.cheapest_quantity/M.most_expensive_quantity), 2) AS imbalance_ratio  
FROM max_min M
JOIN stores S
ON M.store_id = S.store_id
ORDER BY 6 DESC, 2 ASC
;