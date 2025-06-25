# Write your MySQL query statement below
WITH salary_rank AS (
    SELECT
        id,
        name,
        salary,
        departmentId,
        DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS RNK
    FROM Employee
)

SELECT
    DP.name AS Department,
    SR.name AS Employee,
    SR.salary AS Salary
FROM salary_rank SR
LEFT JOIN Department DP
ON SR.departmentId  = DP.id
WHERE SR.RNK <= 3
;