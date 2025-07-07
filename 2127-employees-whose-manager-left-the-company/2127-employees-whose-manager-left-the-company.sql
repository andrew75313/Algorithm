WITH less_30000 AS (
    SELECT
        employee_id,
        manager_id
    FROM Employees
    WHERE salary < 30000
        AND manager_id IS NOT NULL
)

SELECT
    L.employee_id
FROM less_30000 L
LEFT JOIN Employees E
ON L.manager_id = E.employee_id
WHERE E.employee_id IS NULL
ORDER BY 1 ASC

