WITH
salary_ranking AS (
    SELECT DISTINCT
        salary,
        DENSE_RANK() OVER (ORDER BY salary DESC ) AS salary_rank
    FROM Employee
)

SELECT (
    SELECT DISTINCT
    salary
FROM salary_ranking
WHERE salary_rank = 2
) AS SecondHighestSalary
