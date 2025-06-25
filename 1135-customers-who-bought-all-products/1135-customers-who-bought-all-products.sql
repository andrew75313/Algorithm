# Write your MySQL query statement below
WITH tatal_num AS (
    SELECT
        customer_id,
        COUNT(DISTINCT product_key ) AS TOTAL_PRODUCT
     FROM Customer
     GROUP BY 1
)

SELECT
    customer_id
FROM tatal_num
WHERE TOTAL_PRODUCT = (
    SELECT
        COUNT(*)
    FROM Product
)