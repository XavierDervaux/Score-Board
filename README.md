# Score-Board
Android application develloped as a school project.

I created custom RPCs based on the given ones, they can be found in the RPC folder.
Also, theres's a SQL file containing the DB's  creation script, including my INSERTs

The only file ou would need to modify to test this would be :

app/src/main/java/com/cours/dervaux/projetandroid/model/RPC_FETCH.java

Which contains the absolute path to connect to my RPCs.
Additionnaly you might want to modify the database access to reflect your own, the file is :

rpc/inc/db.inc
