/*This SQL Statement will query all users that are a member of both Group A and Group B*/
SELECT concat(u.fName,u.lName) FROM sample.users AS u
JOIN memberships AS m on u.id = m.user_id
JOIN groups AS g on m.group_id = g.id
WHERE g.name IN ("Group A","Group B")
GROUP BY u.id
HAVING COUNT(*) = 2;