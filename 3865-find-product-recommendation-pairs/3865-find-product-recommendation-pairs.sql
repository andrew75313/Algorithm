WITH category_pairs AS (
    SELECT
        P1.user_id,
        P1.product_id AS product1_id,
        P2.product_id AS product2_id    
    FROM ProductPurchases  P1
    JOIN ProductPurchases  P2
    ON P1.user_id = P2.user_id
    WHERE P1.product_id < P2.product_id
)

SELECT
    C.product1_id,
    C.product2_id,
    P1.category AS product1_category,
    P2.category AS product2_category,
    COUNT(*) AS customer_count 
FROM category_pairs C
JOIN ProductInfo P1 ON C.product1_id = P1.product_id 
JOIN ProductInfo P2 ON C.product2_id = P2.product_id
GROUP BY 1, 2, 3, 4
HAVING customer_count >= 3
ORDER BY customer_count DESC, product1_id ASC, product2_id ASC