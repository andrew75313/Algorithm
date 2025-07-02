WITH
order_number AS (
    SELECT
        id,
        student,
        FLOOR( (id + 1) / 2) AS score,
        CASE
            WHEN (id % 2) = 1 THEN 2
            ELSE 1
        END AS type 
    FROM Seat
),
reorder AS (
    SELECT
        ROW_NUMBER() OVER (ORDER BY score ASC, type ASC) AS new_id,
        student
    FROM order_number
)

SELECT
    new_id AS id,
    student
FROM reorder
ORDER BY id ASC