<?php

require "init.php";

$userNr=(int)$_POST["nr"];
$KursNr=(int)$_POST["kn"];



if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }


$sql="DELETE FROM db210144.Paakurs WHERE Paakurs.Nr = '$userNr' AND Paakurs.KursNr = '$KursNr' ";


if(mysqli_query($con,$sql)){
 
 
}


mysqli_close($con);



?>