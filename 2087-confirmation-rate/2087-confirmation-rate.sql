
WITH confimed_count AS (
    SELECT
        user_id,
        COUNT(*) AS total_confirmed
    FROM Confirmations
    WHERE action = 'confirmed'
    GROUP BY 1
),
total_count AS (
    SELECT
        user_id,
        COUNT(*) AS total_count
    FROM Confirmations
    GROUP BY 1
)


SELECT
    S.user_id,
    ROUND(
        COALESCE(CC.total_confirmed, 0) / COALESCE(TC.total_count, 1)
        ,2
    ) AS confirmation_rate
FROM Signups S
LEFT JOIN total_count TC
ON TC.user_id = S.user_id
LEFT JOIN confimed_count CC
ON TC.user_id = CC.user_id
ORDER BY 1