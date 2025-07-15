WITH first_half AS (
    SELECT *
    FROM trips
    WHERE MONTH(trip_date) >= 1 AND MONTH(trip_date) <= 6
),
second_half AS (
    SELECT *
    FROM trips
    WHERE MONTH(trip_date) >= 7 AND MONTH(trip_date) <= 12
),
valid_user_average AS (
    SELECT
        driver_id,
        CASE
            WHEN (MONTH(trip_date) >= 1 AND MONTH(trip_date) <= 6) THEN 'first'
        ELSE 'second' 
        END AS half,
        distance_km / fuel_consumed AS fuel_efficiency
    FROM trips
    WHERE driver_id IN (SELECT driver_id FROM first_half) AND driver_id IN (SELECT driver_id FROM second_half)
),
valid_user_total_average AS (
    SELECT
        driver_id,
        AVG(CASE WHEN half = 'first' THEN fuel_efficiency ELSE NULL END) AS first_half_avg,
        AVG(CASE WHEN half = 'second' THEN fuel_efficiency ELSE NULL END) AS second_half_avg
    FROM valid_user_average
    GROUP BY 1
    HAVING first_half_avg < second_half_avg
)

SELECT
    V.driver_id,
    D.driver_name,
    ROUND(V.first_half_avg, 2) AS first_half_avg,
    ROUND(V.second_half_avg, 2) AS second_half_avg,
    ROUND(V.second_half_avg - V.first_half_avg, 2) AS efficiency_improvement
FROM valid_user_total_average V
JOIN drivers D ON V.driver_id = D.driver_id
ORDER BY 5 DESC, 2 ASC