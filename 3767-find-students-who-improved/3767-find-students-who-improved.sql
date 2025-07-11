WITH total_scores AS (
    SELECT DISTINCT
        student_id,
        subject,
        FIRST_VALUE(score) OVER (PARTITION BY student_id, subject ORDER BY exam_date ASC) AS first_score,
        FIRST_VALUE(score) OVER (PARTITION BY student_id, subject ORDER BY exam_date DESC) AS latest_score,
        COUNT(*) OVER (PARTITION BY student_id, subject) AS cnt
    FROM Scores    
)

SELECT
    student_id,
    subject,
    first_score,
    latest_score
FROM total_scores
WHERE first_score < latest_score
    AND cnt >= 2
ORDER BY 1 ASC, 2 ASC
