SELECT
    DATE_FORMAT(trans_date, "%Y-%m") AS month,
    country,
    /*COUNT with conditions*/
    COUNT(*) AS trans_count,
    COUNT(CASE WHEN state = 'approved' THEN 1 END) AS approved_count,
    /*SUM with conditions*/
    SUM(amount) AS trans_total_amount,
    SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END) AS approved_total_amount
FROM Transactions
GROUP BY month, country;
