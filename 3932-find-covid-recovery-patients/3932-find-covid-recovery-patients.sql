SELECT
    p.patient_id,
    p.patient_name,
    p.age,
    DATEDIFF(
        MIN(CASE WHEN ct.result = 'Negative' AND ct.test_date > first_pos.first_contam_date THEN ct.test_date END),
        first_pos.first_contam_date
    ) AS recovery_time
FROM patients p
JOIN (
    SELECT
        patient_id,
        MIN(test_date) AS first_contam_date
    FROM covid_tests
    WHERE result = 'Positive'
    GROUP BY patient_id
) AS first_pos
  ON p.patient_id = first_pos.patient_id
JOIN covid_tests ct
  ON ct.patient_id = p.patient_id
GROUP BY p.patient_id, p.patient_name, p.age, first_pos.first_contam_date
HAVING recovery_time IS NOT NULL
ORDER BY recovery_time ASC, p.patient_name ASC;
