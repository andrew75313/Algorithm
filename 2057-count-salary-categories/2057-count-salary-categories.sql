# Write your MySQL query statement below
WITH
salaryCategory AS (
    SELECT
        account_id,
        income,
        CASE
            WHEN income < 20000 THEN 'Low Salary'
            WHEN income >= 20000 AND income <= 50000 THEN 'Average Salary'
            ELSE 'High Salary'
        END AS category
    FROM Accounts
),
allCategory AS (
    SELECT 'Low Salary' AS category
    UNION
    SELECT 'Average Salary' AS category
    UNION
    SELECT 'High Salary' AS category
),
accountsWithCategory AS (
    SELECT
        category,
        COUNT(*) AS accounts_count
    FROM salaryCategory
    GROUP BY category
)

/*BODY*/
SELECT
    AC.category,
    COALESCE(WC.accounts_count, 0) AS accounts_count
FROM allCategory AC
LEFT JOIN accountsWithCategory WC
USING (category)