 
<html>
<?php

$file = 'saved.txt';
// The new person to add to the file
$person = "John Smith\n";
 $content = $_POST["content"];
 $val=file_get_contents("saved.txt");
  $val1=file_get_contents("act.txt");

// Write the contents to the file, 
// using the FILE_APPEND flag to append the content to the end of the file
// and the LOCK_EX flag to prevent anyone else writing to the file at the same time
file_put_contents($file, $content);
 	$date = date("H:i, dS F, Y");
file_put_contents('act.txt', 'last edit was made by Student on:'.$date);
file_put_contents('activit.txt', '***************************last edit was made by Student on: '.$date."\n".PHP_EOL, FILE_APPEND | LOCK_EX);

   
  //  	echo "$date";
// $content = $_POST["content"];

?>
<?php echo $val1; ?>
<form class="form-horizontal" action="try.php" method="post">
<div class="form-group">
<label class="col-md-4 control-label" for="content">Content</label>
	<div class="col-md-4">                     
	<textarea class="form-control" id="content" name="content" ROWS=20 COLS=50><?php
echo $val;

?></textarea>
	</div>

	<button class="btn btn-primary btn-lg" type="submit" value="Submit">
	Submit</button>
	</div>
	</form>
    
<script>
function myFunction() {
	location.reload();
}
</script>
    
	<?php
	echo "</br>";
echo file_get_contents("saved.txt");
echo "</br>";
?>

<button onclick="myFunction()">Reload page</button>
<form action="redirect.php">
<input type="submit" value="Show activity log">
</form> 
