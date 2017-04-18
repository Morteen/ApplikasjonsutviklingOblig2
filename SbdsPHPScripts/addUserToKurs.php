<?php

require "init.php";

$userNr=(int)$_POST["nr"];
$KursNr=(int)$_POST["kn"];


if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }


$sql="INSERT INTO db210144.Paakurs(Nr,KursNr,Betalt) VALUES('$userNr','$KursNr','0')";



if(mysqli_query($con,$sql)){
 
 
}


mysqli_close($con);



?>