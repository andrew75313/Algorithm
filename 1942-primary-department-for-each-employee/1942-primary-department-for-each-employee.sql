WITh no_primary_count AS (
    SELECT
        employee_id,
        COUNT(*) AS cnt
    FROM Employee
    GROUP BY 1
)

SELECT
    employee_id,
    department_id
FROM Employee
WHERE primary_flag = 'Y'
    OR employee_id IN (SELECT employee_id FROM no_primary_count WHERE cnt = 1)