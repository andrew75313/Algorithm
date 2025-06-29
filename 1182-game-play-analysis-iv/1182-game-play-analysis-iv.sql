WITH
firstday AS (
    SELECT
        player_id,
        MIN(event_date) AS first_date
    FROM Activity
    GROUP BY 1
)
,
retention AS (
    SELECT
        A.player_id
    FROM Activity A
    INNER JOIN firstday F
        ON A.player_id = F.player_id
        AND A.event_date = F.first_date + INTERVAL 1 DAY
)


/*BODY*/
SELECT
    ROUND(
        COUNT(R.player_id) / COUNT(F.player_id) ,
        2
    ) AS fraction
FROM firstday F
LEFT JOIN retention R
    ON F.player_id = R.player_id
;