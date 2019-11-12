function doDisplay(num){

	var con = document.getElementById("myDIV"+num);
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}