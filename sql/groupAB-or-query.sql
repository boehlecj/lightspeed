/*This SQL Statement will query all users that are a member of either Group A or Group B*/
SELECT concat(u.fName,u.lName) as "username" FROM sample.users AS u
JOIN memberships AS m on u.id = m.user_id
JOIN groups AS g on m.group_id = g.id
WHERE g.name="Group A" OR g.name="Group B"
GROUP BY u.id