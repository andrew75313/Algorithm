WITH distinct_user AS (
    SELECT DISTINCT
        P.user_id,
        I.category AS category
    FROM ProductPurchases P
    JOIN ProductInfo I
    ON P.product_id = I.product_id
),
category_pairs AS (
    SELECT
        DU1.user_id,
        DU1.category AS category1,
        DU2.category AS category2
    FROM distinct_user DU1
    JOIN distinct_user DU2
    ON DU1.user_id = DU2.user_id
        AND DU1.category < DU2.category
)

SELECT
    category1,
    category2,
    COUNT(*) AS customer_count 
FROM category_pairs
GROUP BY 1,2
HAVING customer_count >=3
ORDER BY 3 DESC, 1 ASC, 2 ASC