WITH valid_performance_reviews_nums AS (
    SELECT
        *    
    FROM (
            SELECT
            *,
            ROW_NUMBER() OVER (PARTITION BY employee_id ORDER BY review_date DESC) AS num,
            COUNT(*) OVER (PARTITION BY employee_id) AS cnt
        FROM performance_reviews
    ) sub
    WHERE num <= 3 AND cnt >= 3
),
invalid_performance_reviews_increasing AS (
    SELECT DISTINCT
        *
    FROM (
        SELECT
            *,
            LEAD(rating) OVER (PARTITION BY employee_id ORDER BY num DESC) AS next_rating
        FROM valid_performance_reviews_nums
    ) sub
    WHERE next_rating IS NOT NULL AND next_rating <= rating 
)

SELECT
    V.employee_id,
    E.name,
    MAX(CASE WHEN V.num = 1 THEN V.rating END) - MAX(CASE WHEN V.num = 3 THEN V.rating END) AS improvement_score 
FROM valid_performance_reviews_nums V
JOIN employees E
ON V.employee_id = E.employee_id
WHERE V.employee_id NOT IN (SELECT employee_id FROM invalid_performance_reviews_increasing)
GROUP BY 1, 2
ORDER BY 3 DESC, 2 ASC