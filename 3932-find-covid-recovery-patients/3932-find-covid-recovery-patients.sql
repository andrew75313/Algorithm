WITH first_contam AS (
    SELECT
        patient_id,
        MIN(test_date) AS first_contam_date
    FROM covid_tests
    WHERE result = 'Positive'
    GROUP BY 1
),
first_cured AS (
    SELECT
        CT.patient_id,
        MIN(CT.test_date) AS first_cured_date    
    FROM covid_tests CT
    JOIN first_contam FC
    ON CT.patient_id = FC.patient_id
    WHERE CT.result = 'Negative'
        AND CT.test_date > FC.first_contam_date 
    GROUP BY 1
)

SELECT
    CON.patient_id,
    P.patient_name,
    P.age,
    DATEDIFF(CUR.first_cured_date, CON.first_contam_date) AS recovery_time 
FROM first_contam CON
JOIN first_cured CUR
ON CON.patient_id = CUR.patient_id
JOIN patients P
ON CON.patient_id = P.patient_id
ORDER BY 4 ASC, 2 ASC