WITh report_counts AS (
    SELECT
        managerId,
        COUNT(*) AS cnt
    FROM Employee
    WHERE managerId IS NOT NULL
    GROUP BY 1
)

SELECT
    name
FROM report_counts R
JOIN Employee E
ON R.managerId = E.id
WHERE R.managerId IN (
    SELECT managerId
    FROM report_counts
    WHERE cnt >= 5
)