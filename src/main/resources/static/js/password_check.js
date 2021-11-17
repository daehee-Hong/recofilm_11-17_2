function check_pw(){

    var pw = document.getElementById('pw').value;
    var SC = ["!","@","#","$","%","&","'","(",")","*","+",",","-",".","/",":",";","<","=",">","?","@","[","＼","]","^","_","`","{","|","}","~"];
    var check_SC = 0;

    if(pw.length < 8 || pw.length>20){
        document.getElementById('check').innerHTML='비밀번호는 8글자 이상, 20글자 이하만 이용 가능합니다.'
        document.getElementById('check').style.color='red';
        document.getElementById('pw').style.marginBottom='5px'
        document.getElementById('pw').value='';
        document.getElementById('btn_login').disabled = false;
    }
    for(var i=0;i<SC.length;i++){
        if(pw.indexOf(SC[i]) != -1){
            check_SC = 1;
        }
    }
    if(check_SC == 0){
        document.getElementById('check').innerHTML='사용불가 특수문자이거나 특수문자가 들어가 있지 않습니다.'
        document.getElementById('check').style.color='red';
        document.getElementById('pw').style.marginBottom='5px'
        document.getElementById('pw').value='';
        document.getElementById('btn_login').disabled = false;
    }
    if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
        if(document.getElementById('pw').value==document.getElementById('pw2').value){
            document.getElementById('check2').innerHTML='비밀번호가 일치합니다.'
            document.getElementById('check2').style.color='blue';
            document.getElementById('pw2').style.marginBottom='5px'
            document.getElementById('btn_login').disabled = false;
        }
        else{
            document.getElementById('check2').innerHTML='비밀번호가 일치하지 않습니다.';
            document.getElementById('check2').style.color='red';
            document.getElementById('pw2').style.marginBottom='5px'
            document.getElementById('btn_login').disabled = true;
        }
    }
}