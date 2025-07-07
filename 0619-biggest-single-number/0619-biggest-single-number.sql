WITH number_counts AS (
    SELECT
        num,
        COUNT(*) cnt
    FROM MyNumbers
    GROUP BY num
)

SELECT
    MAX(num) AS num
FROM number_counts
WHERE cnt = 1
