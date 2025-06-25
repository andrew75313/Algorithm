# Write your MySQL query statement below
WITH
REQtoACP AS (
    SELECT
        requester_id AS id,
        COUNT(DISTINCT accepter_id ) AS num
    FROM RequestAccepted
    GROUP BY requester_id
),
ACPtoREQ AS (
    SELECT
        accepter_id AS id,
        COUNT(DISTINCT requester_id ) AS num
    FROM RequestAccepted
    GROUP BY accepter_id
)

SELECT
    id,
    SUM(num) AS num
FROM (
    SELECT * FROM REQtoACP
    UNION ALL
    SELECT * FROM ACPtoREQ
) AS TOTAL
GROUP BY 1
ORDER BY 2 DESC
LIMIT 1;
