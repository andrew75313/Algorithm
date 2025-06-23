WITH 2015_duplicated AS (
    SELECT pid
    FROM Insurance
    WHERE
        tiv_2015 IN (
            SELECT tiv_2015
            FROM Insurance
            GROUP BY tiv_2015
            HAVING COUNT(1) > 1
        )
),
2016_unique AS (
    SELECT pid
    FROM Insurance
    WHERE (lat, lon) IN (
        SELECT lat, lon
        FROM Insurance
        GROUP BY 1,2
        HAVING COUNT(*) = 1
    )
)

SELECT
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM
    Insurance IC
INNER JOIN 2015_duplicated
USING (pid)
INNER JOIN 2016_unique
USING (pid)
;