# Write your MySQL query statement below
WITH
TotalByDate AS (
    SELECT
    visited_on,
    SUM(amount) AS TOTAL
    FROM Customer
    GROUP BY visited_on
),
TotalSevenDays AS (
    SELECT
        visited_on,
        SUM(TOTAL) OVER(ORDER BY visited_on
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS TOTAL_SEVENDAYS,
        COUNT(*) OVER(
            ORDER BY visited_on
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS TOTAL_COUNT
    FROM TotalByDate
)

SELECT
    visited_on,
    TOTAL_SEVENDAYS AS amount,
    ROUND(TOTAL_SEVENDAYS/7, 2 ) AS average_amount
FROM  TotalSevenDays
WHERE TOTAL_COUNT = 7
;
