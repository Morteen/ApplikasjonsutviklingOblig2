<?php


require "init.php";



$fname=$_POST["fname"];
$ename=$_POST["etternavn"];
$user_pass=$_POST["passW"];
$epost=$_POST["email"];
$user_name=$_POST["username"];
$Alder=(int)$_POST["age"];


$sql_query ="INSERT INTO db210144.Bruker (Nr,Epost,Passord,username,Fornavn,Etternavn,Alder) VALUES (NULL,'$epost','$user_pass','$user_name','$fname','$ename','$Alder')";




if(mysqli_query($con,$sql_query)){
	
}
mysqli_close($con);

 ?>