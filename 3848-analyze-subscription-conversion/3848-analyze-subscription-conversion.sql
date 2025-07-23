# excluse free -> cancel
WITH invalid_subscription AS (
    SELECT
        user_id AS invalid_user_id
    FROM (
        SELECT DISTINCT
            user_id,
            activity_type AS first_type,
            LEAD(activity_type) OVER (PARTITION BY user_id ORDER BY activity_date ASC) As next_type
        FROM UserActivity
    ) SUB
    WHERE first_type = 'free_trial' AND next_type = 'cancelled'
)

SELECT
    user_id,
    ROUND(AVG(CASE WHEN activity_type = 'free_trial' THEN activity_duration END), 2) AS trial_avg_duration,
    ROUND(AVG(CASE WHEN activity_type = 'paid' THEN activity_duration END), 2) AS paid_avg_duration 
FROM UserActivity
GROUP BY 1
HAVING user_id NOT IN (SELECT * FROM invalid_subscription)
ORDER BY 1 ASC