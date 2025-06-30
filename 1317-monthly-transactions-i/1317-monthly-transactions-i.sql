WITH
total_monthly_order AS (
    SELECT DISTINCT
        DATE_FORMAT(trans_date, "%Y-%m") AS month,
        country
    FROM Transactions
),
total_trans AS (
    SELECT
        TMO.month,
        TMO.country,
        COUNT(*) AS trans_count,
        SUM(T.amount) AS trans_total_amount
    FROM total_monthly_order TMO
    JOIN Transactions T
        ON TMO.month = DATE_FORMAT(T.trans_date, "%Y-%m")
        AND (
            (TMO.country =T.country) OR
            (TMO.country IS NULL AND T.country IS NULL)
        )
    GROUP BY 1, 2
),
total_approval AS (
    SELECT
        TMO.month,
        TMO.country,
        COUNT(*) AS approved_count,
        SUM(T.amount) AS approved_total_amount
    FROM total_monthly_order TMO
    JOIN Transactions T
        ON TMO.month = DATE_FORMAT(T.trans_date, "%Y-%m")
        AND (
            (TMO.country =T.country) OR
            (TMO.country IS NULL AND T.country IS NULL)
        )
        AND T.state = 'approved'
    GROUP BY 1, 2
)

SELECT
    TT.month,
    TT.country,
    TT.trans_count,
    COALESCE(TA.approved_count, 0) AS approved_count,
    TT.trans_total_amount,
    COALESCE(TA.approved_total_amount,0) AS approved_total_amount
FROM total_trans TT
LEFT JOIN total_approval TA
ON TT.month = TA.month
    AND (
        TT.country = TA.country OR
        (TT.country IS NULL AND TA.country IS NULL)
    )
;