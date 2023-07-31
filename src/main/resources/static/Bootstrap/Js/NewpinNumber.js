const form =document.querySelector('#form')
const Acnumber =document.querySelector('#Accountnumber')
const PIN =document.querySelector('#pinNumber')
form.addEventListener('submit',(e)=>{
    
    

    if(!validateInputs()){
        e.preventDefault()
    }
})


function validateInputs(){
    const Acnumberval=Acnumber.value.trim();
    const PINval=PIN.value.trim();
    let success=true;

    if(Acnumberval===''){
        success=false;
        seterror(Acnumber,'*AccountNumber is required*')
    }else if(!validateAcnumber(Acnumberval)){
        success=false;
        seterror(Acnumber,'* AccountNumber 11 digites only required')
    }
    else{
        setsuccess(Acnumber)
    }

    if(PINval===''){
        success=false;
        seterror(PIN,'*PIN number is required*')
    }else if(!validatepin(PINval)){
        success=false;
        seterror(PIN,'* PIN number 4 digites only is required')
    }
    else{
        setsuccess(PIN)
    }
    return success;

}

function seterror(element,message){
    const inputgroup=element.parentElement;
    const errorelement=inputgroup.querySelector('.error')

    errorelement.innerText=message;
    inputgroup.classList.add('error')
    inputgroup.classList.remove('success')
}
function setsuccess(element){
    const inputgroup=element.parentElement;
    const errorelement=inputgroup.querySelector('.error')

    errorelement.innerText='';
    inputgroup.classList.add('success')
    inputgroup.classList.remove('error')
}

const validatepin = (Pin) => {
    return String(Pin)
    .match("^[0-9]{4}$");
    };
const validateAcnumber = (Acnumber) => {
        return String(Acnumber)
        .match("^[1-9]{1}[0-9]{10}$");
    };