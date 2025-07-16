WITH active_users AS (
    SELECT DISTINCT
        activity_date,
        user_id
    FROM Activity
    WHERE activity_date BETWEEN  '2019-07-27' - INTERVAL 29 DAY AND '2019-07-27'
)

SELECT
    activity_date AS day,
    COUNT(*) AS active_users
FROM active_users
GROUP BY 1
ORDER BY 1 ASC