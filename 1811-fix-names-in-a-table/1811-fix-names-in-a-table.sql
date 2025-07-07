SELECT
    user_id,
    CONCAT(
        LEFT(UPPER(name), 1),
        SUBSTRING(LOWER(name), 2)
    ) AS name
FROM Users
ORDER BY 1 ASC