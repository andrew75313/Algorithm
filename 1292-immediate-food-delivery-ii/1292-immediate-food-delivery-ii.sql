WITH
fast_delivery AS (
    SELECT
        *
    FROM Delivery
    WHERE order_date = customer_pref_delivery_date 
),
first_order AS (
    SELECT
        customer_id,
        MIN(order_date) AS first_order
    FROM Delivery
    GROUP BY 1
)

/*FETCH*/
SELECT
    ROUND(
        COUNT(FD.customer_id) / COUNT(FO.customer_id)
    , 4) * 100 AS immediate_percentage 
FROM first_order FO
LEFT JOIN fast_delivery FD
    ON FO.customer_id = FD.customer_id
    AND FO.first_order = FD.order_date 
