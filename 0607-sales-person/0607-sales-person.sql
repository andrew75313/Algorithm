WITH wrong_order_use AS (
    SELECT DISTINCT
        sales_id
    FROM Orders
    WHERE com_id IN (
            SELECT
                com_id
            FROM Company
            WHERE name LIKE 'RED'
    )
)

SELECT
    name
FROM SalesPerson
WHERE sales_id NOT IN (
    SELECT * FROM wrong_order_use
);