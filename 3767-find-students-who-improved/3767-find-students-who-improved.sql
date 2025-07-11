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
        S.student_id,
        S.subject,
        FIRST_VALUE(S.score) OVER (PARTITION BY S.student_id, S.subject ORDER BY S.exam_date ASC) AS first_score,
        FIRST_VALUE(S.score) OVER (PARTITION BY S.student_id, S.subject ORDER BY S.exam_date DESC) AS latest_score
    FROM Scores S
    JOIN more_exam M
    ON S.student_id = M.student_id AND S.subject = M.subject
    WHERE M.cnt >= 2
)

SELECT
    student_id,
    subject,
    first_score,
    latest_score
FROM total_scores
WHERE first_score < latest_score
ORDER BY 1 ASC, 2
