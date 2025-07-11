WITH more_exam AS (
    SELECT
        student_id,
        subject,
        COUNT(*) AS cnt
    FROM Scores
    GROUP BY 1, 2
    HAVING cnt >= 2
),
total_scores AS (
    SELECT DISTINCT
        student_id,
        subject,
        FIRST_VALUE(score) OVER (PARTITION BY student_id, subject ORDER BY exam_date ASC) AS first_score,
        FIRST_VALUE(score) OVER (PARTITION BY student_id, subject ORDER BY exam_date DESC) AS latest_score
    FROM Scores
    WHERE (student_id, subject) IN (SELECT student_id, subject FROM more_exam)
    
)

SELECT
    student_id,
    subject,
    first_score,
    latest_score
FROM total_scores
WHERE first_score < latest_score
ORDER BY 1 ASC, 2
