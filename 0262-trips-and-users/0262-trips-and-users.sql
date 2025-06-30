WITH
valid_users AS (
    SELECT users_id
    FROM Users
    WHERE banned = "No"
),
valid_trips AS (
    SELECT *
    FROM Trips
    WHERE client_id IN (SELECT users_id FROM valid_users)
    AND driver_id IN (SELECT users_id FROM valid_users)
)

SELECT
    request_at AS Day,
    ROUND(
        (COUNT(CASE WHEN status LIKE "cancelled%" THEN 1 END) / COUNT(*))
    , 2) AS "Cancellation Rate"
FROM valid_trips
WHERE request_at >= '2013-10-01' AND request_at <= '2013-10-03'
GROUP BY request_at