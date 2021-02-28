<?php
   $acc = $_POST["acc"];
   $sell = $_POST["sell"];
   $amt = $_POST["amt"];
   $mon_trans = exec("python3 /home/pi/dmr_pur_comm/money_transfer.py '$acc' '$sell' '$amt'");
   echo $mon_trans;
?>
