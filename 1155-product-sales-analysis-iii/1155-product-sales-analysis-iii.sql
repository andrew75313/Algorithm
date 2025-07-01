WITH
first AS (
    SELECT
        product_id,
        MIN(year) AS first_year
    FROM Sales
    GROUP BY 1
)

SELECT
    F.product_id,
    F.first_year,
    S.quantity,
    S.price 
FROM first F
LEFT JOIN Sales S
    ON F.product_id = S.product_id
    AND F.first_year = S.year 