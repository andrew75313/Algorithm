WITH manager AS (
    SELECT
        reports_to AS manager_id,
        COUNT(*) AS reports_count,
        ROUND(AVG(age)) AS average_age
    FROM Employees
    GROUP BY 1
    HAVING reports_to IS NOT NULL
)

SELECT
    E.employee_id,
    E.name,
    M.reports_count,
    M.average_age
FROM manager M
JOIN Employees E
ON M.manager_id = E.employee_id
ORDER BY 1 ASC