# Equal and more than 3 different subs in 1 cycle
# Equal and more than 2 cycles
# All session gaps must be less than 2 days
# CALCULATE cycle length
# CALCULATE total hours

WITH valid_date_sessions AS (
    SELECT *
    FROM (
        SELECT
            *,
            COALESCE(LAG(session_date) OVER (PARTITION BY student_id ORDER BY session_id ASC), session_date) AS prev_date,
            COALESCE(LEAD(session_date) OVER (PARTITION BY student_id ORDER BY session_id ASC), session_date) AS next_date
        FROM study_sessions
    ) DATES
    WHERE DATEDIFF(session_date, prev_date) <= 2 AND DATEDIFF(next_date, session_date) <= 2
),
cycle_length AS (
    SELECT 
        student_id,
        COUNT(subject) AS total_sessions,
        COUNT(DISTINCT subject) AS cycle_length,
        SUM(hours_studied) AS total_study_hours 
    FROM valid_date_sessions
    GROUP BY 1
    HAVING cycle_length >= 3 AND total_sessions != cycle_length AND (total_sessions % cycle_length) = 0
)

SELECT
    C.student_id,
    S.student_name,
    S.major,
    C.cycle_length,
    C.total_study_hours 
FROM cycle_length C
JOIN students S
ON C.student_id = S.student_id 
ORDER BY 4 DESC, 5 DESC