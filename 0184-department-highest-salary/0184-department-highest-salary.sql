WITH salary_rank AS (
    SELECT
        *,
        RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
    FROM Employee
)

SELECT
    D.name AS Department,
    S.name AS Employee,
    S.salary AS Salary
FROM salary_rank S
JOIN Department D ON S.departmentId = D.id
WHERE S.rnk = 1
