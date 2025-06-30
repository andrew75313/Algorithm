WITH
latest_update AS (
    SELECT
        product_id,
        new_price,
        ROW_NUMBER() OVER (PARTITION BY product_id ORDER BY change_date DESC) AS ranking
    FROM Products
    WHERE change_date <= "2019-08-16"
)

SELECT DISTINCT
    P.product_id,
    COALESCE(L.new_price, 10) AS price
FROM Products P
LEFT JOIN latest_update L
ON P.product_id = L.product_id
AND L.ranking = 1