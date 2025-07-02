SELECT DISTINCT
    num AS ConsecutiveNums
FROM    (
    SELECT
        num,
        LEAD(num,1) OVER (ORDER BY id) AS num2,
        LEAD(num,2) OVER (ORDER BY id) AS num3
    FROM Logs
) AS consecutive
WHERE
    num = num2 AND num = num3
