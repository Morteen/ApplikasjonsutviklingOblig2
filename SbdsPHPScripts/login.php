<?php


require "init.php";


$sql_query="SELECT Fornavn FROM Bruker WHERE username LIKE 'tine'AND Passord LIKE 'tine';" ; 

$result=mysqli_query($con,$sql_query);

if(mysqli_num_rows($result)>0)
{
	
	$row = mysqli_fetch_assoc($result);
	$name=$row["Fornavn"];
	
}

mysqli_close($con);
 ?>