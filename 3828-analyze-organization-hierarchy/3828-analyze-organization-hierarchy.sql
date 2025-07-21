WITH RECURSIVE employee_level AS (
    SELECT
        *,
        1 AS level
    FROM Employees
    WHERE manager_id IS NULL

    UNION ALL

    SELECT
        E.employee_id,
        E.employee_name,
        E.manager_id,
        E.salary,
        E.department,
        L.level + 1 AS level
    FROM Employees E
    JOIN employee_level L
    ON E.manager_id = L.employee_id
),
employee_hierarchy AS (
    SELECT
        employee_id AS manager_id,
        employee_id AS team_id    
    FROM Employees

    UNION ALL

    SELECT
        H.manager_id,
        E.employee_id AS team_id
    FROM employee_hierarchy H
    JOIN Employees E
    ON H.team_id = E.manager_id
),
team_info AS (
    SELECT
     H.manager_id,
     COUNT(*) - 1 AS team_size,
     SUM(E.salary) AS budget         
    FROM employee_hierarchy H
    JOIN Employees E
    ON H.team_id = E.employee_id
    GROUP BY 1 
)

SELECT
    E.employee_id,
    E.employee_name,
    L.level,
    COALESCE(T.team_size, 0) AS team_size,
    COALESCE(T.budget, 0) AS budget
FROM Employees E
LEFT JOIN employee_level L ON E.employee_id = L.employee_id
LEFT JOIN team_info T ON E.employee_id = T.manager_id
ORDER BY 3 ASC, 5 DESC, 2 ASC
;