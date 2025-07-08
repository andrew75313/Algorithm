WITH octet AS (
    SELECT DISTINCT
        ip,
        SUBSTRING_INDEX(ip, '.', 1) AS octet1,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) AS octet2,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) AS octet3,
        SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 4), '.', -1) AS octet4
    FROM logs
),
invalid_ip AS (
    SELECT
        ip
    FROM octet
    WHERE
        ((LENGTH(ip) - LENGTH(REPLACE(ip, '.', ''))) != 3)
        OR (255 < octet1 OR 255 < octet2 OR 255 < octet3 OR 255 < octet4)
        OR (LEFT(octet1, 1) = '0' OR LEFT(octet2, 1) = '0' OR LEFT(octet3, 1) = '0' OR LEFT(octet4, 1) = '0')
)

SELECT
    ip,
    COUNT(*) AS invalid_count 
FROM logs 
WHERE ip IN (SELECT * FROM invalid_ip)
GROUP BY 1
ORDER BY 2 DESC, 1 DESC