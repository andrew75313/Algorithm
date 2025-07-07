WITH over100 AS (
    SELECT *
    FROM Stadium 
    WHERE people >= 100
    ORDER BY id ASC
),
with_consecutive AS (
    SELECT
        id,
        LAG(id, 2) OVER (ORDER BY id ASC) AS prev2,
        LAG(id, 1) OVER (ORDER BY id ASC) AS prev1,
        LEAD(id, 1) OVER (ORDER BY id ASC) AS next1,
        LEAD(id, 2) OVER (ORDER BY id ASC) AS next2
    FROM over100
)

SELECT
    *
FROM Stadium
WHERE id IN (
    SELECT id
    FROM with_consecutive
    WHERE (prev2 = id-2 AND prev1 = id-1)
        OR (prev1 = id-1 AND next1 = id+1)
        OR (next1 = id+1 AND next2 = id+2)
)
ORDER BY visit_date ASC