function validacaoPdf(objFileControl){
	
 var file = objFileControl.value;
 var len = file.length;
 var ext = file.slice(len - 4, len);
 if(ext.toUpperCase() == ".PDF"){
   formOK = true;
 }
 else{
   formOK = false;
   alert("Somente arquivos com a extensão PDF são permitidos!");
 }
}