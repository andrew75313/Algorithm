WITh buy_total AS (
    SELECT
        stock_name,
        operation,
        SUM(price) AS buy_sum
    FROM Stocks
    WHERE operation = 'Buy'
    GROUP BY 1, 2
),
sell_total AS (
    SELECT
        stock_name,
        operation,
        SUM(price) AS sell_sum
    FROM Stocks
    WHERE operation = 'Sell'
    GROUP BY 1, 2
)


SELECT
    N.stock_name,
    (COALESCE(S.sell_sum, 0) - COALESCE(B.buy_sum, 0)) AS capital_gain_loss 
FROM (
    SELECT DISTINCT stock_name FROM Stocks 
) N
LEFT JOIN sell_total S
ON N.stock_name = S.stock_name
LEFT JOIN buy_total B
ON B.stock_name = S.stock_name
