WITH cnts AS (
    SELECT
        book_id,
        COUNT(CASE WHEN return_date IS NULL THEN 1 END) AS null_cnt,
        COUNT(CASE WHEN return_date IS NOT NULL THEN 1 END) AS return_cnt
    FROM borrowing_records
    GROUP BY 1
),
all_borrowed AS (
    SELECT
        C.book_id
    FROM cnts C
    JOIN library_books L
    ON C.book_id = L.book_id
    WHERE L.total_copies - C.null_cnt = 0
)

SELECT
    L.book_id,
    L.title,
    L.author,
    L.genre,
    L.publication_year,
    C.null_cnt AS current_borrowers 
FROM cnts C
JOIN library_books L ON C.book_id = L.book_id
WHERE C.book_id IN (SELECT * FROM all_borrowed)
ORDER BY current_borrowers DESC, 2 ASC


