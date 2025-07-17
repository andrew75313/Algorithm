WITH orders_in_2019 AS (
    SELECT
        buyer_id,
        COUNT(*) AS total_orders
    FROM Orders
    WHERE YEAR(order_date) = 2019
    GROUP BY 1
)

SELECT
    U.user_id AS buyer_id,
    U.join_date,
    COALESCE(O.total_orders, 0) AS orders_in_2019
FROM Users U
LEFT JOIN orders_in_2019 O
ON U.user_id = O.buyer_id