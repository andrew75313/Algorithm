WITH
valid_user_average AS (
    SELECT
        driver_id,
        CASE
            WHEN (MONTH(trip_date) >= 1 AND MONTH(trip_date) <= 6) THEN 'first'
        ELSE 'second' 
        END AS half,
        distance_km / fuel_consumed AS fuel_efficiency
    FROM trips
),
valid_user_total_average AS (
    SELECT
        driver_id,
        COALESCE(AVG(CASE WHEN half = 'first' THEN fuel_efficiency ELSE NULL END), 0) AS first_half_avg,
        COALESCE(AVG(CASE WHEN half = 'second' THEN fuel_efficiency ELSE NULL END), 0) AS second_half_avg
    FROM valid_user_average
    GROUP BY 1
    HAVING first_half_avg < second_half_avg
        AND first_half_avg !=0 AND second_half_avg !=0
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

