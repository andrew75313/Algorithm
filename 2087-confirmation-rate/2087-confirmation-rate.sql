SELECT
    user_id,
    ROUND (
        SUM(CASE WHEN action = 'confirmed' THEN 1 ELSE 0 END) /
        COALESCE(COUNT(*), 1)
    ,2) AS confirmation_rate
FROM Signups S
LEFT JOIN Confirmations C
USING (user_id)
GROUP BY 1
ORDER BY 1
;