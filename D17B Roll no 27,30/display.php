<?php  
if ($handle = opendir('uploads/')) { 
    while (false !== ($file = readdir($handle))) {  
        if ($file != "." && $file != "..") {  
            echo "$file";
	    echo "<br>"; 
        }  
    } 
    closedir($handle);  
} 
?>