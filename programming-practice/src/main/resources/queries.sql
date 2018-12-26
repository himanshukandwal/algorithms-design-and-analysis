-- link : https://app.codesignal.com/arcade/db/always-leave-table-in-order/WJ2bsam6YCAqgxFS9
CREATE PROCEDURE contestLeaderboard()
BEGIN
	select lb.name
    from ( select @num := @num + 1 as k, name from leaderboard, (select @num := 0) x order by score desc) lb
    where lb.k >= 4 and lb.k <= 8;
END

-- link: https://app.codesignal.com/arcade/db/always-leave-table-in-order/aQJquGtwg4rgXwfqH/description
/*Please add ; after each select statement*/
CREATE PROCEDURE mischievousNephews()
BEGIN
	select weekday(mischief_date) as weekday, mischief_date, author, title
    from mischief
    order by weekday(mischief_date), field(author, 'Huey', 'Dewey', 'Louie'), mischief_date, title;
END
