/*This SQL Statement will query all groups that UserA is a member of*/
SELECT  g.name as "groupname",concat(u.fName,u.lName) as "username" FROM sample.groups AS g
JOIN memberships AS m on g.id = m.group_id
JOIN users AS u on m.user_id = u.id
WHERE u.lName = "A"