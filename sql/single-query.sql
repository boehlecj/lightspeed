/*This SQL Statement will cover 3 conditions is one query*/
/*First: All groups UserA belongs to*/
SELECT  g.name as col1,"Has UserA as a member" as col2 FROM sample.groups AS g
JOIN memberships AS m on g.id = m.group_id
JOIN users AS u on m.user_id = u.id
WHERE u.lName = "A"
UNION ALL
/*Second: Users that are members of Group A or Group B*/
SELECT concat(u.fName,u.lName), "Is a member of EITHER Group A or Group B" FROM sample.users AS u
JOIN memberships AS m on u.id = m.user_id
JOIN groups AS g on m.group_id = g.id
WHERE g.name="Group A" OR g.name="Group B"
GROUP BY u.id
UNION ALL
/*Third: Users that are members of both Group A and Group B */
SELECT concat(u.fName,u.lName), "Is a member of BOTH Group A and Group B" FROM sample.users AS u
JOIN memberships AS m on u.id = m.user_id
JOIN groups AS g on m.group_id = g.id
WHERE g.name IN ("Group A","Group B")
GROUP BY u.id
HAVING COUNT(*) = 2;
/*All resultsets are two columns*/
/*col1 contains result values*/
/*col2 contains condition descriptions*/