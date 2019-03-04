//var idIndicator = document.getElementById('_id');
//var pwdIndicator = document.getElementById('_pwd');

function check_input_len(val1, val2) {
    var len =  val1.length;
    var _val = document.getElementById('_'+val2);

    if(len > 0) {
        _val.classList.add('value', 'focus_in');
    } else {
        _val.classList.remove('value', 'focus_in');
    }
}
function focus_in(val1, val2) {
    var len =  val1.length;
    var layout_val = document.getElementById(val2);
    var _val = document.getElementById('_'+val2);

    layout_val.classList.add('focus_layout');
    if(len > 0) {
        _val.classList.remove('focus_out');
        _val.classList.add('focus_in');
    } else {
        _val.classList.remove('focus_out');
    }

}

function focus_out(val) {
    var _val = document.getElementById('_'+val);
    var layout_val = document.getElementById(val);

    layout_val.classList.remove('focus_layout');
    _val.classList.add('focus_out');
}

function show_or_hide(e) {
    var pwd = document.getElementById('pwd');
    if (e.target.checked==true){
        pwd.type = "text";
    } else {
        pwd.type = "password";
    }
}
function show_checkbox() {
    var show = document.getElementById('show_check');
    show.classList.add('show');
}

function hide_checkbox() {
    var pwd = document.getElementById('pwd');
    var isFocus = pwd.classList.contains('focus_layout');
    var len = pwd.value.length;
    if ((isFocus == false) && (len == 0)){
        var show = document.getElementById('show_check');
        show.classList.remove('show');
    }
}

function  login_proc() {

    var id = document.getElementById('id');
    var pwd = document.getElementById('pwd');
    var layer = document.getElementById('layer2');
    layer.classList.remove('motion');
    var valID = id_validate();
    var valPWD = pwd_validate_out();

    if((valID == true) && (valPWD == true)){
        alert("환영합니다");
        return true;
    } else {
        layer.classList.add('motion');

        if (valID == false) {
            id.focus();
        } else if (valPWD == false) {
            pwd.focus();
        }

        layer.classList.remove('motion');
        return false;
    }
}

function id_validate() {
    var id = document.getElementById('id');
    var ch;
    var len = id.value.length;
    var extraInfo_val = document.getElementById('id_extraInfo');
    id.classList.add('alert_input_layout');
    extraInfo_val.classList.add('alert_extraInfo_layout');
    extraInfo_val.classList.add('focus_extraInfo');

    if(len <= 2 ) {
        if(len == 0) {
            extraInfo_val.innerHTML = '';
            extraInfo_val.classList.remove('focus_extraInfo');
            id.classList.remove('alert_input_layout');
            extraInfo_val.classList.remove('alert_extraInfo_layout');
            return false;
        }
        extraInfo_val.innerHTML = "Your username is probably longer than this. can you double check?";
        return false;
    } else {
        for(i=0; i<len; i++){
            ch = id.value.charAt(i);
            if(!(ch>='0' && ch<='9') && !(ch>='a' && ch<='z') && !(ch>='A' && ch<='Z') && !(ch=='_')) {
                extraInfo_val.classList.add('focus_extraInfo');
                extraInfo_val.innerHTML = "use letters, numbers or '_'. (e.g. arya_starks)";
                return false;
            }
        }
        extraInfo_val.innerHTML = '';
        extraInfo_val.classList.remove('focus_extraInfo');
        id.classList.remove('alert_input_layout');
        extraInfo_val.classList.remove('alert_extraInfo_layout');
        return true;
    }
}


function pwd_validate_in() {
    var extraInfo_val = document.getElementById('pwd_extraInfo');
    extraInfo_val.classList.add('focus_extraInfo');
    extraInfo_val.innerHTML = 'Type 6 characters or more';
}

function pwd_validate_out() {
    var extraInfo_val = document.getElementById('pwd_extraInfo');
    var pwd = document.getElementById('pwd');
    extraInfo_val.classList.remove('focus_extraInfo');
    if(pwd.value.length < 6) {
        return false;
    } else {
        return true;
    }
}




function point_cursor() {
    var btn = document.getElementById('btn');
    btn.classList.add('btn_cursor');
}