WITH with_season AS (
    SELECT
        *,
        CASE
            WHEN DATE_FORMAT(sale_date, '%M') IN ('December', 'January', 'February') THEN 'Winter'
            WHEN DATE_FORMAT(sale_date, '%M') IN ('March', 'April', 'May') THEN 'Spring'
            WHEN DATE_FORMAT(sale_date, '%M') IN ('June', 'July', 'August') THEN 'Summer'
            ELSE 'Fall'
        END AS season,
        quantity * price AS revenue
    FROM sales
),
with_popularity AS (
    SELECT DISTINCT
        S.product_id,
        P.category,
        S.season,
        SUM(S.quantity) AS total_quantity,
        SUM(S.revenue) AS total_revenue
    FROM with_season S
    JOIN products P
    ON S.product_id = P.product_id
    GROUP BY 2, 3
),
with_revenue_ranking AS (
    SELECT 
        *,
        ROW_NUMBER() OVER (PARTITION BY season ORDER BY total_quantity DESC,total_revenue DESC) AS rnk
    FROM with_popularity
)

SELECT
    season,
    category,
    total_quantity,
    total_revenue
FROM with_revenue_ranking
WHERE rnk = 1
ORDER BY 1 ASC;

