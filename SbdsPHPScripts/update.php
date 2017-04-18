<?php


require "init.php";


$fname=$_POST["fname"];
$ename=$_POST["etternavn"];
$user_pass=$_POST["passW"];
$epost=$_POST["email"];
$user_name=$_POST["username"];
$Alder=(int)$_POST["age"];
$Nr=(int)$_POST["nr"];
 


$sql_query ="UPDATE db210144.Bruker SET Epost = '$epost',Passord = '$user_pass',Fornavn = '$fname',Etternavn='$ename' ,`Alder` = '$Alder' WHERE Bruker.Nr ='$Nr'";


if(mysqli_query($con,$sql_query)){
	
	
}
mysqli_close($con);

 ?>