SELECT DISTINCT
    player_id,
    event_date AS first_login
FROM (
    SELECT
        player_id,
        event_date,
        ROW_NUMBER() OVER (PARTITION BY player_id ORDER BY event_date ASC) AS ordr
    FROM Activity
) sub
WHERE ordr = 1