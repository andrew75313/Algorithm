    WITH
    review_count AS (
        SELECT
            user_id,
            COUNT(user_id) AS review_counts
        FROM MovieRating
        GROUP BY user_id
    ),
    movie_rating AS (
        SELECT
            movie_id,
            ROUND(AVG(rating), 1) AS movie_average_rating
        FROM MovieRating
        WHERE DATE_FORMAT(created_at, '%Y-%m') = '2020-02'
        GROUP BY movie_id
    )



    /*User who reviewed the most*/
    /*UNION ALL*/
    /*Movie rated the highest*/ 
SELECT * FROM (
    SELECT
        U.name AS results
    FROM review_count R
    LEFT JOIN Users U ON R.user_id = U.user_id
    ORDER BY R.review_counts DESC, U.name ASC
    LIMIT 1
) AS top_reviewer

UNION ALL

SELECT * FROM (
    SELECT
        M.title AS results
    FROM movie_rating R
    LEFT JOIN Movies M ON R.movie_id = M.movie_id
    ORDER BY R.movie_average_rating DESC, M.title ASC
    LIMIT 1
) AS top_moive
;